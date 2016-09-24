package com.xy.sm.dao;

import java.util.List;

import com.xy.sm.entity.Entity;

public interface BaseDao {

	public <T extends Entity> List<T> findList(T condition);
	
	public <T extends Entity> T findOne(T condition);
	
	public <T extends Entity> List<T> findPage(T condition);
	
	public <T extends Entity> T update(T entity);
	
	public <T extends Entity> T insert(T entity);
	
	public <T extends Entity> void delete(T entity);
}
