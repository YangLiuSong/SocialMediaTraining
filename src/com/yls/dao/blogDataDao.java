package com.yls.dao;

import java.util.List;
import java.util.Map;

import com.yls.domain.blogData;

/**
 * ����΢�����ݵĽӿ�
 * @author Administrator
 *
 */
public interface blogDataDao {
	public List<blogData> queryAll();
	public blogData queryOne(Map<String, Object> map);
	public boolean add(blogData data);
	public boolean update(Map<String, Object> map);
	public boolean deleteOne(String Id);
	public List<blogData>  pagination(String mon,int offset);
	public List<blogData> pageData();

}
