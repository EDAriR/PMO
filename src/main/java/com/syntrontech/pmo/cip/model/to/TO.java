package com.syntrontech.pmo.cip.model.to;

public interface TO<T> {
	TO<T> convertFrom(T model);
}
