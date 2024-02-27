package com.arphoenix.mscaffeine.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.arphoenix.mscaffeine.interceptor.LoggingInterceptor;

/**
 * A classe WebConfig é uma configuração do Spring MVC que registra o interceptor para processar solicitações HTTP. O interceptor é usado
 * para extrair o ID do locatário do cabeçalho HTTP e configurá-lo no contexto do locatário durante o processamento da solicitação.
 * 
 * @author rsdelia
 * 
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	private final LoggingInterceptor interceptor;

	/**
	 * Construtor para a classe WebConfig.
	 *
	 * @param interceptor
	 *            O interceptor do locatário que será registrado para processar solicitações HTTP.
	 */
	public InterceptorConfig(LoggingInterceptor interceptor) {
		this.interceptor = interceptor;
	}

	/**
	 * Registra o interceptor do locatário (interceptor) no registro de interceptadores do Spring MVC.
	 *
	 * @param registry
	 *            O registro de interceptadores do Spring MVC onde o interceptor do locatário será adicionado.
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor);
	}
}