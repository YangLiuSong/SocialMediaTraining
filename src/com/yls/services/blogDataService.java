package com.yls.services;

import java.util.List;
import java.util.Map;

import com.yls.domain.blogData;

public interface blogDataService {
	public List<blogData> getAll();
	
	public blogData getOne(Map<String, Object> map);
	
	public boolean deleteOne(String id);
		
	public boolean addOne(blogData tag);
	
	public boolean updateOne(Map<String, Object> map);
	
	public List<blogData> pagination(String mon,int offset);

}
