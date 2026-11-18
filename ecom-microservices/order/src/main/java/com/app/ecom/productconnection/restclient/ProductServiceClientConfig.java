package com.app.ecom.productconnection.restclient;


import io.micrometer.observation.ObservationRegistry;
import io.micrometer.tracing.TraceContext;
import io.micrometer.tracing.Tracer;
import io.micrometer.tracing.propagation.Propagator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.client.RestClientBuilderConfigurer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.Optional;

@Configuration
public class ProductServiceClientConfig {

	@Autowired(required = false)
	private ObservationRegistry observationRegistry;

	@Autowired(required = false)
	private Tracer tracer;

	@Autowired(required = false)
	private Propagator propagator;

	@Bean
	@LoadBalanced
	public RestClient.Builder restClientBuilder() {
		RestClient.Builder builder = RestClient.builder();

		if (observationRegistry != null && tracer != null && propagator != null) {
			builder.requestInterceptor(createTracingInterceptor());
		}

		return builder;
	}

	private ClientHttpRequestInterceptor createTracingInterceptor() {

		ClientHttpRequestInterceptor interceptor =
				(request, body, execution) -> {

					HttpHeaders headers = request.getHeaders();

					TraceContext traceContext =
							tracer.currentTraceContext().context();

					Propagator.Setter<HttpHeaders> headerSetter =
							(carrier, key, value) -> carrier.add(key, value);

					propagator.inject(
							traceContext,
							headers,
							headerSetter
					);

					ClientHttpResponse response =
							execution.execute(request, body);

					return response;
				};

		return interceptor;
	}

	@Bean
	public ProductHttpClient productHttpClient(@LoadBalanced RestClient.Builder clientBuilder) {

		RestClient restClient = clientBuilder.baseUrl("http://ecomproduct")
				.defaultStatusHandler( HttpStatusCode::is5xxServerError, (request, response) -> Optional.of(HttpStatus.SERVICE_UNAVAILABLE))
				.build();
//		Adapter
		RestClientAdapter adapter = RestClientAdapter.create(restClient);
//      Factory
		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
//		Proxy
		ProductHttpClient productClientProxy = factory.createClient(ProductHttpClient.class);
//		Client interface
		return productClientProxy;
	}
}
