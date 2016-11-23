package com.netcracker.ca.dao;

public interface Dao<Entity, Id> {
	
	Entity getById(Id id);
	
	void add(Entity entity);
	
	void update(Entity entity);
	
	void delete(Id id);
	
}
