package com.loe.dms.spring.model.data;

import java.io.Serializable;

public class ServiceResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private ErrorInfoData errorInfoData;

	public ErrorInfoData getErrorInfoData() {
		return errorInfoData;
	}

	public void setErrorInfoData(ErrorInfoData errorInfoData) {
		this.errorInfoData = errorInfoData;
	}

	public boolean hasErrors() {
		if (errorInfoData != null && !errorInfoData.getErrorInfos().isEmpty()) {
			return true;
		}
		return false;
	}

}
