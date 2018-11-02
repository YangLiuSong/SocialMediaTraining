package com.yls.base;

import java.lang.reflect.ParameterizedType;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 *  ����action ר���ڼ̳�
 * @author Administrator
 *
 */
public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T>,Preparable {

	private static final long serialVersionUID = 1L;
	public T model;
	public BaseAction(){
		try {
			ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class clazz = (Class) type.getActualTypeArguments()[0];
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void prepare() throws Exception {		
	}

	@Override
	public T getModel() {
		return model;
	}
	

}
