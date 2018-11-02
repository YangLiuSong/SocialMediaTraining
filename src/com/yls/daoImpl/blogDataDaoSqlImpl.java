package com.yls.daoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.yls.dao.blogDataDao;
import com.yls.domain.blogData;


public class blogDataDaoSqlImpl implements blogDataDao{

	private SqlSessionTemplate sqlSession;
	public SqlSessionTemplate getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<blogData> queryAll() {
		return sqlSession.selectList("com.lijian.daoImpl.blogDataSqlMapper.getAll");
	}

	public List<blogData> pageData() {
		return sqlSession.selectList("com.lijian.daoImpl.blogDataSqlMapper.getPageData");
	}


	@Override
	public blogData queryOne(Map<String, Object> map) {
//		System.out.println("query id :"+Id);
		return sqlSession.selectOne("com.lijian.daoImpl.blogDataSqlMapper.getOne",map);
	}

	@Override
	public boolean add(blogData data) {
		boolean b = (sqlSession.update("com.lijian.daoImpl.blogDataSqlMapper.add", data)==1);
		return b;
	}

	@Override
	public boolean update(Map<String, Object> map) {
		boolean b = (sqlSession.update("com.lijian.daoImpl.blogDataSqlMapper.update", map)==1);
//		sqlSession.commit();
		return b;
	}

	@Override
	public List<blogData> pagination(String mon,int offset) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mon",mon);
		map.put("offset", offset);
		List<blogData> list = sqlSession.selectList("com.lijian.daoImpl.blogDataSqlMapper.pagination",map);
		return list;
	}

	@Override
	public boolean deleteOne(String Id) {
		boolean b = (sqlSession.delete("com.lijian.daoImpl.blogDataSqlMapper.delete", Id)==1);
		return b;
	}

	

}
