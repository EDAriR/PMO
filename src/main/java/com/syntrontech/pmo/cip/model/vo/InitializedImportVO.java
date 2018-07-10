package com.syntrontech.pmo.cip.model.vo;

import java.io.InputStream;

import com.syntrontech.pmo.cip.model.common.InitializedImportType;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.syntrontech.autoTool.annotation.ParamEnumRequired;
import com.syntrontech.autoTool.annotation.ParamNotNull;

public class InitializedImportVO {
	
	@ParamNotNull
	@FormDataParam("file")
	private InputStream file;
	
	@ParamNotNull
	@FormDataParam("file")
	private FormDataContentDisposition fileMeta;
	
	@ParamNotNull
	@ParamEnumRequired(InitializedImportType.class)
	@FormDataParam("type")
	private String type;

	public InputStream getFile() {
		return file;
	}

	public void setFile(InputStream file) {
		this.file = file;
	}

	public FormDataContentDisposition getFileMeta() {
		return fileMeta;
	}

	public void setFileMeta(FormDataContentDisposition fileMeta) {
		this.fileMeta = fileMeta;
	}

	public InitializedImportType getType() {
		return InitializedImportType.valueOf(type);
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
