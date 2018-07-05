package com.syntrontech.pmo.mv.syncare1.model;

public interface ObjectConverter<T> {
	public static final ObjectConverterUtils utils = new ObjectConverterUtils();
	
	public T convert(boolean relation);
}
