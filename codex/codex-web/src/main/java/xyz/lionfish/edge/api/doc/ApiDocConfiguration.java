package xyz.lionfish.edge.api.doc;

import me.parakh.core.api.doc.BasePathAwareRelativePathProvider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * set a System property (spring.profiles.active) 
 * or an OS environment variable (SPRING_PROFILES_ACTIVE).
 * -Dspring.profiles.active=dev 
 * 
 * @author Kevendra Patidar
 */
@Configuration
public class ApiDocConfiguration {

	//@Profile({"dev", "gae"})
	@Bean
	public BasePathAwareRelativePathProvider basePathAwareRelativePathProviderDev(){
		return new BasePathAwareRelativePathProvider("/");
	}
	//@Profile({"dit-ord", "prod-las"})
	//@Bean
	public BasePathAwareRelativePathProvider basePathAwareRelativePathProviderOrd(){
		return new BasePathAwareRelativePathProvider("/edge");
	}
}
