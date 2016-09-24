package com.xy.sm.service;

import java.util.List;

import com.xy.sm.entity.Entity;

public interface BaseService {

	public <T extends Entity> List<T> findListByCondition(T condition);
	
	public <T extends Entity> List<T> findPageByCondition(T condition);
	
	public <T extends Entity> T findOne(T condition);
	
	public <T extends Entity> void deleteOne(T condition);
	
	public <T extends Entity> T saveOrUpdate(T condition);
}
