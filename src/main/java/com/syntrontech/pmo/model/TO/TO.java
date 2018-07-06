package com.syntrontech.pmo.model.TO;

public interface TO<T> {
	TO<T> convertFrom(T model);
}
