package com.yls.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.yls.domain.new_blog_data;
import com.yls.domain.blogData;

/**
 * ��װһϵ�в���΢�����ݵķ���
 * ��Ҫ�ǽ���΢�����ݣ���ȡ��Ҫ�Ĺؼ�������
 * @author Administrator
 *http://ww3.sinaimg.cn/thumb180/4cfa62f8gw1f3iw5vbrksj21iu2e9kij.jpg
 */
public class blogDataUtil {
	public static String imgtype=".jpg";
	public static String webPath;
	public static String imgSaveFile = "blogimgs";

	public static new_blog_data bluidOneBlogUnTagDatas(blogData data) {
		new_blog_data nd;
		JSONObject jsb = builblogData(data);
		String new_id = data.getId();
		String new_context = jsb.toString();
		nd = new new_blog_data(new_id,new_context);
		return nd;
	}


	public static JSONObject builblogData(blogData blogData){
		System.out.println(blogData);
		JSONObject rObject = new JSONObject();
		try {
//			rObject.put("id",blogData.getId());
			rObject.put("user_name", blogData.getUser_name());
			rObject.put("gender", blogData.getGender());
//			rObject.put("pic_ids", context[4]);

			rObject.put("pic_ids",getImgs(blogData));
			rObject.put("title", blogData.getTitle());
//			rObject.put("year", context[6]);
//			rObject.put("month", context[7]);
//			rObject.put("date", context[8]);
//			rObject.put("day", context[9]);
//			rObject.put("time", context[10]);
			rObject.put("time",getTime(blogData));
			rObject.put("text",blogData.getText());
//			rObject.put("tag_ids", context[12]);
//			rObject.put("tag_names", context[13]);
//			data = rObject.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return rObject;
	}
//	/**
//	 * ����û��ͼƬ��΢�� ����Ҫչʾ
//	 * @param object
//	 * @return
//	 */
//	public static boolean hasPics(String context){
//		JSONObject object ;
//		try {
//			object = new JSONObject(context);
//			if(object.has("pic_ids")){
//				try {
//					JSONArray array = object.getJSONArray("pic_ids");
//					if(array.length()>0){
//						return true;
//					}else{
//						return false;
//					}
//
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
//			}else{
//				return false;
//			}
//		} catch (JSONException e1) {
//			e1.printStackTrace();
//		}
//
//		return false;
//	}
	/**
	 * �ж������Ƿ��б�ǩ
//	 * @param context
	 * @return
	 */
//	public static boolean hasTag(String[] context){
//		try {
//			if(context[12] != null){
//						return true;
//			}else{
//				return false;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
//	public static String getId(JSONObject object) {
//			try {
//				JSONObject id = object.getJSONObject("id");
//				return id.getString("id");
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		return "";
//	}
//	public static String getName(JSONObject object) {
//		try {
//			JSONObject user_name = object.getJSONObject("user_name");
//			return user_name.getString("user_name");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "";
//	}
//	public static String getText(JSONObject object) {
//		try {
//			JSONObject text = object.getJSONObject("text");
//			return text.getString("text");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "";
//	}
//	public static String getGender(JSONObject object) {
//		try {
//			JSONObject gender = object.getJSONObject("gender");
//			return gender.getString("gender");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "";
//	}

	public static String getTime(blogData object) {
		String title = object.getTitle();
			String year = object.getYear();
			String month = object.getMonth();
			String date = object.getDate();
			String time = object.getTime();

			String blog_time =title + "     " + year + "-" + month + "-" + date + "  " + time;

			return blog_time;
	}


	public static JSONArray getImgs(blogData object){
		JSONArray rArray = new JSONArray();
		String pic = object.getPic_ids();

		String[] p1 = pic.split("\\[");
		String[] p2 = p1[1].split("\\]");
		String[] p3 = p2[0].split(",");
		List<String> pic_url = new ArrayList<>();
		for(int i = 0;i<p3.length;i++){
			String pi = p3[i].split("\"")[1];
			pic_url.add(pi);
		}
//		imageUtil.downloadImgs(pic_url,webPath + "\\" + imgSaveFile);
//		System.out.println(webPath + imgSaveFile);
		for(int i =0;i<pic_url.size();i++){
			rArray.put("http://ww3.sinaimg.cn/large/"+pic_url.get(i)+imgtype);
		}
		return rArray;
	}
}
