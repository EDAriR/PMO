package com.syntrontech.pmo.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.syntrontech.microservice.MicroService;
import com.syntrontech.pmo.properties.RegistryProperties;

@Component
public class ServiceRegistryListener implements ServletContextListener {
	
	private static final Logger logger = LoggerFactory.getLogger(ServiceRegistryListener.class);

	@Autowired
	protected RegistryProperties registryProperties;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		
		if (!registryProperties.getAddress().isEmpty()) {
			
			String[] address = registryProperties.getAddress().toArray(new String[registryProperties.getAddress().size()]);
			
			MicroService.server(address)
//						.address(serviceProperties.getProtocol(), serviceProperties.getHost(), serviceProperties.getPort())
//						.name(serviceProperties.getName())
						.success(optional -> {})
						.error(err -> {})
						.register();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}

}
