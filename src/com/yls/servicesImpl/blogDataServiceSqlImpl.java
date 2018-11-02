package com.yls.servicesImpl;

import java.util.List;
import java.util.Map;

import com.yls.dao.blogDataDao;
import com.yls.domain.blogData;
import com.yls.services.blogDataService;

public class blogDataServiceSqlImpl implements blogDataService{

	private blogDataDao dao;
	public blogDataDao getDao() {
		return dao;
	}

	public void setDao(blogDataDao dao) {
		this.dao = dao;
	}
	@Override
	public List<blogData> getAll() {
		return dao.queryAll();
	}

	public List<blogData> getPageData() {
		return dao.pageData();
	}

	@Override
	public blogData getOne(Map<String, Object> map) {
		return dao.queryOne(map);
	}

	@Override
	public boolean deleteOne(String  id) {
		return dao.deleteOne(id);
	}

	@Override
	public boolean addOne(blogData tag) {
		return dao.add(tag);
	}

	@Override
	public boolean updateOne(Map<String, Object> map) {
		return dao.update(map);
	}

	@Override
	public List<blogData> pagination(String mon,int offset) {
		
		return dao.pagination(mon,offset);
	}


}
