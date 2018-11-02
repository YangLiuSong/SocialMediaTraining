package com.yls.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import com.yls.domain.new_blog_data;

import com.google.gson.Gson;
import com.yls.base.BaseAction;
import com.yls.domain.blogData;
import com.yls.services.blogDataService;
import com.yls.utils.blogDataUtil;

public class blogDataAction extends BaseAction<blogData>{

	private static final long serialVersionUID = 1L;

	private int offset;
	private String mon;

	private blogDataService service;

	public blogDataService getService() {
		return service;
	}

	public void setService(blogDataService service) {
		this.service = service;
	}
	//���÷�ҳ����
	private Long total;

	private int limit=0;


	private String order ="asc" ;

	public String getMon() {
		return mon;
	}

	public void setMon(String mon) {
		this.mon = mon;
	}

	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}

	private String  id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	private String tag_ids;
	public String getTag_ids() {
		return tag_ids;
	}

	public void setTag_ids(String tag_ids) {
		this.tag_ids = tag_ids;
	}
	
	private String tag_names;
	public String getTag_names() {
		return tag_names;
	}

	public void setTag_names(String tag_names) {
		this.tag_names = tag_names;
	}




	public String save(){
//		System.out.println(model);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mon",mon);
		map.put("tag_ids",tag_ids);
		map.put("tag_names",tag_names);
		map.put("id", id);
		boolean b =service.updateOne(map);
		PrintWriter out=null;
		HttpServletResponse response=null;
		try {
			response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			out = ServletActionContext.getResponse().getWriter();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		String result;
		if(b){
			result = "success";
		}else{
			result="fail";
		}
		out.print(result);
		out.flush();
		out.close();
		return "save";
	}


	public String list(){
		PrintWriter out=null;
			HttpServletResponse response=null;
			String webPath = ServletActionContext.getServletContext().getRealPath("");
			blogDataUtil.webPath = webPath;
			try {
				response = ServletActionContext.getResponse();
				response.setContentType("text/html;charset=utf-8");
				out = ServletActionContext.getResponse().getWriter();
			} catch (IOException e) {
				throw new RuntimeException(e);
		}
		int curoffset = offset;
		String curmon = mon;
		List<blogData> page = service.pagination(curmon,curoffset);
		new_blog_data[] pd = new new_blog_data[10];
		for(int i = 0;i<page.size();i++){
			pd[i] = blogDataUtil.bluidOneBlogUnTagDatas(page.get(i));
		}
		String data = new Gson().toJson(pd);
		System.out.println(data);
		out.print(data);
		out.flush();
		out.close();
		return "list";
	}
	
	public String getOne(){
		PrintWriter out=null;
		HttpServletResponse response=null;
		try {
			response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			out = ServletActionContext.getResponse().getWriter();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mon",mon);
		map.put("id",id);
		blogData bg = service.getOne(map);
		String data = new Gson().toJson(bg);
		out.print(data);
		out.flush();
		out.close();
		return "getOne";
	}
}
