package com.syntrontech.pmo.provider;

import java.lang.reflect.InvocationHandler;

import org.glassfish.jersey.server.model.Invocable;
import org.glassfish.jersey.server.spi.internal.ResourceMethodInvocationHandlerProvider;

//import com.syntrontech.autoTool.resourceInvoke.JsonObjectHandler;
//import com.syntrontech.autoTool.service.SessionValidationService;
import com.syntrontech.pmo.util.SpringContextHelperImp;

public class JsonObjectHandlerProvider implements ResourceMethodInvocationHandlerProvider {
	
//	private static final InvocationHandler handler = new JsonObjectHandler();
	
	@Override
	public InvocationHandler create(Invocable method) {
//		SessionValidationService validateService =SpringContextHelperImp.getInstance().getBean(SessionValidationService.class);
//		JsonObjectHandler jsonObjectHandler = (JsonObjectHandler) handler;
//		jsonObjectHandler.bindValidateService(validateService);
//		return handler;
		return null;
	}

}

