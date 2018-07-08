package com.syntrontech.pmo.syncare1.model;

import java.util.List;
import java.util.stream.Collectors;

public class ObjectConverterUtils {
	public static final ObjectConverterUtils utils = new ObjectConverterUtils();
	
	public static <T> List<T> transfer(List<? extends ObjectConverter<T>> list){
		return utils.transferList(list);
	}
	
	public <T> List<T> transferList(List<? extends ObjectConverter<T>> list){
		return list.stream()
			.map(obj -> obj.convert(true))
			.collect(Collectors.toList());
	}
}
