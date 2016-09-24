package com.xy.sm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.xy.sm.dao.BaseDao;
import com.xy.sm.entity.Entity;
import com.xy.sm.service.BaseService;

@Service
public class BaseServiceImpl implements BaseService {

	@Autowired
	@Qualifier("baseDaoImpl")
	private BaseDao baseDao;
	
	@Override
	public <T extends Entity> List<T> findListByCondition(T condition) {
		// TODO Auto-generated method stub
		return baseDao.findList(condition);
	}

	@Override
	public <T extends Entity> List<T> findPageByCondition(T condition) {
		// TODO Auto-generated method stub
		return baseDao.findPage(condition);
	}

	@Override
	public <T extends Entity> T findOne(T condition) {
		return baseDao.findOne(condition);
	}

	@Override
	public <T extends Entity> void deleteOne(T condition) {
		baseDao.delete(condition);
	}

	@Override
	public <T extends Entity> T saveOrUpdate(T condition) {
		if(condition.getKey() != null){
			return this.baseDao.update(condition);
		}else{
			return this.baseDao.insert(condition);
		}
	}


	
	
}
