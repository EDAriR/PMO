package com.syntrontech.pmo.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spi.internal.ResourceMethodInvocationHandlerProvider;
import org.springframework.stereotype.Component;

import com.syntrontech.pmo.provider.JsonObjectHandlerProvider;
import com.syntrontech.pmo.util.SpringContextHelperImp;

/*
 * register the Endpoints in Jersey
 */

@Component
@ApplicationPath("")
public class JerseyConfig extends ResourceConfig{
	
//	private SpringContextHelper helper = SpringContextHelperImp.getInstance();
	
	public JerseyConfig(){
		registerResourcesDependOnAutoTool();
		registerResourcesDependOnJersey();	
		registerProvider();
		register(new AbstractBinder() {
			@Override
			protected void configure() {
				bind(JsonObjectHandlerProvider.class)
				.to(ResourceMethodInvocationHandlerProvider.class);
			}
		});
		registOther();
	}
	
	private void registerResourcesDependOnAutoTool(){

	}
	
	private void registerResourcesDependOnJersey(){
		
	}
		
	private void registerProvider(){
//		register(new ClientExceptionMapper());
//		register(new ServerExceptionMapper());
//		register(new ForbiddenExceptionMapper());
//		register(new UnauthorizedExceptionMapper());
//		register(new NotFoundExceptionMapper());
	}
	
	private void registOther(){
		register(MultiPartFeature.class);
	}
}
