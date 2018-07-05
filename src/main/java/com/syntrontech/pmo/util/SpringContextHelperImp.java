package com.syntrontech.pmo.util;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

//import com.syntrontech.autoTool.util.SpringContextHelper;

@Component
public class SpringContextHelperImp implements ApplicationContextAware
//, SpringContextHelper 
{
	private static ApplicationContext _context;
	private static SpringContextHelperImp helper;

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		_context = context;
	}

	public static SpringContextHelperImp getInstance(){
		if(helper==null){
			helper = new SpringContextHelperImp();
		}
		return helper;
	}
	
//	@SuppressWarnings("unchecked")
//	@Override
//	public <T> T getBean(String id){
//		return (T) _context.getBean(id);
//	}
//	
//	@Override
//	public <T> T getBean(Class<T> clazz){
//		return _context.getBean(clazz);
//	}
	
}
