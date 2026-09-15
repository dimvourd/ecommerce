package com.config.demo_config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/practise-build-info")
public class PracticeBuildInfoController {

	@Value("${PROPERTY_VALUE:default property}")
	private String propertyValue;

	@GetMapping
	public ResponseEntity<String> practiceBuildInfo() {

		return ResponseEntity.ok(this.toString());
	}

	/**
	 * =====HIERARCHY=====
	 * 1. Command-line arguments --PROPERTY_VALUE=CommandLineValue >
	 * 2. Java System Properties -DPROPERTY_VALUE=SystemValue >
	 * 3. Environment Variables PROPERTY_VALUE=EnvironmentValue >
	 * 4. Profile-specific config application-{profile}.properties/yml >
	 * 5. Application config application.properties/yml >
	 * 6. Default value στο @Value, π.χ. ${foo.bar:123}
	 */

	@Override
	public String toString() {

		return "Dimitris Test: \n"
				+ String.format("%nproperty value=%s%n", propertyValue)
				+ ""
				;
	}
}
