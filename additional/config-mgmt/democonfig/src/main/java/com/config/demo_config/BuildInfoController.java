package com.config.demo_config;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/build-info")
@RequiredArgsConstructor
public class BuildInfoController {


	private final BuildInfo buildInfo;
//	@Value("${build.id:not a profile build id}")
//	private String buildId;
//
//	@Value("${build.name:not a profile build name}")
//	private String buildName;
//
//	@Value("${build.version:not a profile build version}")
//	private String buildVersion;

	@Value("${JAVA_HOME:not applicable}")
	private String osInfo;

	@Value("${CONF_ENV_IDE_VALUE:default term config env}")
	private String terminalValue;

	@Value("${configFromProp:DefaultFromProperties}")
	private String configFromProperties;

	@Value("${argumentParam: default arg param}")
	private String argumentsParam;

	@Value("${fromVMOptions: defaultVMValue}")
	private String fromVm;

//	@GetMapping
//	public String getBuildInfo() {
//		return String.format("Build Id=%s%n, Build Name=%s%n, Build Version=%s", buildId, buildName, buildVersion)
//				+ String.format("%nOS Info=%s", osInfo)
//				+ String.format("%nConfiguration environment ide Value=%s", terminalValue)
//				+ String.format("%nConfig From Properties=%s", configFromProperties)
//				+ String.format("%nArgument Param=%s", argumentsParam)
//				+ String.format("%nFrom VM Options=%s", fromVm)
//				;
//	}
@GetMapping
public String getBuildInfo() {
	return String.format("Build Id=%s%n, Build Name=%s%n, Build Version=%s", buildInfo.getId(), buildInfo.getName(), buildInfo.getVersion())
			+ String.format("%nOS Info=%s", osInfo)
			+ String.format("%nConfiguration environment ide Value=%s", terminalValue)
			+ String.format("%nConfig From Properties=%s", configFromProperties)
			+ String.format("%nArgument Param=%s", argumentsParam)
			+ String.format("%nFrom VM Options=%s", fromVm)
			;
}

}
