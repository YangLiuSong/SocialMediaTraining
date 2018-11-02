package com.yls.utils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class imageUtil {


	public static String imgHttp="http://ww3.sinaimg.cn/large/";
	public static String imgtype=".jpg";
	public static void downloadImgs(List<String> picurl,String savePath){
		for(int i=0;i<picurl.size();i++){
			try {
				String filename = picurl.get(i) + imgtype;
				String urlString = imgHttp + filename;
				String pic_load = savePath + "\\" + filename;
				try {
					download(urlString, pic_load);
					System.out.println("img success download!");
				} catch (Exception e) {
					System.err.println(urlString + " error");
					e.printStackTrace();
				}
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	public static void download(String urlString,String savePath) throws Exception {

		URL url = null;
		try {
			url = new URL(urlString);
			DataInputStream dataInputStream = new DataInputStream(url.openStream());
			FileOutputStream fileOutputStream = new FileOutputStream(new File(savePath));


			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] bs = new byte[1024];

			int len;
			while ((len = dataInputStream.read(bs)) > 0) {
				output.write(bs, 0, len);
			}
			fileOutputStream.write(output.toByteArray());
			dataInputStream.close();
			fileOutputStream.close();
		}catch (MalformedURLException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
	}

//	public static void deletPic(String filename,String savePath){
//		File pic = new File(savePath+"/"+filename);
//		//System.out.println("size:"+getFileSize(savePath+"/"+filename));
//		System.out.println(pic.getPath()+" delete!");
//		pic.deleteOnExit();
//	}
//	/**
//	 * ��ȡ�����ļ���С
//	 * @param filename
//	 * @return
//	 */
//	public static long getFileSize(String filename){
//		long size = 0;
//		File file = new File(filename);
//		if(file.exists()){
//			size = file.length();
//		}
//		return size;
//	}
//	/**
//	 * ��ȡ�ļ��д�С
//	 * @param file
//	 * @return
//	 */
//	public static double getDirSize(File file) {
//		//�ж��ļ��Ƿ����
//		if (file.exists()) {
//			//�����Ŀ¼��ݹ���������ݵ��ܴ�С
//			if (file.isDirectory()) {
//				File[] children = file.listFiles();
//				double size = 0;
//				for (File f : children)
//					size += getDirSize(f);
//				return size;
//			} else {//������ļ���ֱ�ӷ������С,�ԡ�b��Ϊ��λ
//				double size =(double)file.length()/1024;
//				return size;
//			}
//		} else {
//			System.out.println("�ļ������ļ��в����ڣ�����·���Ƿ���ȷ��");
//			return 0.0;
//		}
//	}
}
