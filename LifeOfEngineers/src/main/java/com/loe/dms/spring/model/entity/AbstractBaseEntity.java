package com.loe.dms.spring.model.entity;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.loe.dms.spring.util.ApplicationUtil;

public class AbstractBaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(AbstractBaseEntity.class);

	public AbstractBaseEntity() {
		ApplicationUtil.setDefaultValues(this);
	}
}
