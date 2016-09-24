package com.xy.sm.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xy.sm.dao.BaseDao;
import com.xy.sm.entity.Entity;

@Repository
public class BaseDaoImpl implements BaseDao {

	@Autowired
	protected SqlSessionTemplate sessionTemplate;
	
	@Override
	public <T extends Entity> List<T> findList(T entity) {
		return sessionTemplate.selectList("selectList_"+entity.getEntityName(), entity);
	}

	@Override
	public <T extends Entity> T findOne(T entity) {
		return sessionTemplate.selectOne("selectOne_"+entity.getEntityName(), entity);
	}

	@Override
	public <T extends Entity> List<T> findPage(T entity) {
		return sessionTemplate.selectList("selectPage_"+entity.getEntityName(), entity);
	}

	@Override
	public <T extends Entity> T update(T entity) {
		sessionTemplate.update("update_"+entity.getEntityName(), entity);
		return entity;
	}

	@Override
	public <T extends Entity> T insert(T entity) {
		sessionTemplate.insert("insert_"+entity.getEntityName(), entity);
		return entity;
	}

	@Override
	public <T extends Entity> void delete(T entity) {
		sessionTemplate.delete("delete_"+entity.getEntityName(), entity);
	}
	
	
}
