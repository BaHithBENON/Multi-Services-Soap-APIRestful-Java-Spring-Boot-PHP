package com.api.dao;

import java.util.List;

public interface IDao<T> {
	
	/**
	 * @param obj
	 * @throws Exception
	 */
	public void create(T obj) throws Exception;
	
	/**
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public T read(int id) throws Exception;
	
	/**
	 * @param obj
	 * @throws Exception
	 */
	public void update(T obj) throws Exception;
	
	/**
	 * @param id
	 * @throws Exception
	 */
	public void delete(Integer id) throws Exception;
	
	/**
	 * @return
	 * @throws Exception
	 */
	public List<T> list() throws Exception;
}