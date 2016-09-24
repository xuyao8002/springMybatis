package com.xy.sm.entity;

public abstract class Entity {
public abstract String getEntityName();
public abstract Integer getKey();
public int index;
public int pageSize = 10;
public int getIndex() {
	return index;
}
public void setIndex(int index) {
	this.index = index;
}
public int getPageSize() {
	return pageSize;
}
public void setPageSize(int pageSize) {
	this.pageSize = pageSize;
}

}
