package com.loe.dms.spring.model.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ErrorInfoData implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<String> errorInfos = new ArrayList<String>();

	public List<String> getErrorInfos() {
		return errorInfos;
	}

	public void setErrorInfos(List<String> errorInfos) {
		this.errorInfos = errorInfos;
	}

	public void addErrorInfo(String errorInfo) {
		errorInfos.add(errorInfo);
	}

	public boolean hasErrors() {
		if (!errorInfos.isEmpty()) {
			return true;
		}
		return false;
	}

}
