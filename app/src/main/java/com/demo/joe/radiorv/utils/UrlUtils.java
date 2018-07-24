package com.demo.joe.radiorv.utils;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 判断一些url的方法
 * @author gong
 *
 */
public class UrlUtils {

	public static boolean isEmpty(String str) {
		return str == null || "".equals(str.trim());
	}
	/**
	 * 拼接新的Url, 在这个方法里，可以不用在乎是否/
	 * @param domain 域名
	 * @param file 目录
	 * @return
	 */
	public static String newUrl(String domain, String file) {
		if(isEmpty(domain)) {
			return file;
		}
		if(isEmpty(file)) {
			return domain;
		}
		
		if(domain.endsWith("/")) {
			domain = domain.substring(0, domain.length() - 1);
		}
		
		if(file.startsWith("/")) {
			file = file.substring(1, file.length());
		}
		
		return domain + "/" + file;
	}


    /**
     * 推荐使用addReplaceParam
     * 为url增加参数
     * @param url
     * @param param 参数
     * @return
     */
    public static String addParam(String url, String param){
    	if(isEmpty(url)){
    		return null;
    	}
    	if(TextUtils.isEmpty(param)){
    		return url;
		}
        if (!url.contains((param.split("="))[0]+"=")) {
			//链接里面的锚点"#ccc"
			String anchors = "";
			//链接里存在锚点
			if (url.contains("#")) {
				//锚点索引
				int anchorsIndex = url.indexOf('#');
				//截出不包含锚点的部分
				url = url.substring(0, anchorsIndex);
				//截出锚点
				anchors = url.substring(anchorsIndex,url.length() );
			}
			if(url.contains("?")){
                url= url+"&"+param;
            }else{
                url= url+"?"+param;
            }
			//将拼接完的结果和锚点合并
			if(!TextUtils.isEmpty(anchors)){
				url = url+anchors;
			}
		}
        return url;
    }

    public static String addParams(String url, Map<Object,Object> param){
        if(isEmpty(url)){
            return null;
        }
        if(null==param || param.isEmpty()){
        	return url;
		}
		//链接里面的锚点"#ccc"
		String anchors = "";
		//链接里存在锚点
		if (url.contains("#")) {
			//锚点索引
			int anchorsIndex = url.indexOf('#');
			//截出不包含锚点的部分
			url = url.substring(0, anchorsIndex);
			//截出锚点
			anchors = url.substring(anchorsIndex,url.length() );
		}
        for (Map.Entry entry:param.entrySet()){
			if(url.contains("?")){
				url= url+"&"+entry.getKey()+"="+entry.getValue();
			}else{
				url= url+"?"+entry.getKey()+"="+entry.getValue();
            }
        }
		//将拼接完的结果和锚点合并
		if(!TextUtils.isEmpty(anchors)){
			url = url+anchors;
		}
        return url;
    }
    /**
     * 为URL添加参数，参数已经存在，则不进行替换
     * * @param url
     * @param params
     * @return
     */
    public static String addParam(String url, String... params){
        if(isEmpty(url)){
            return null;
        }
        if (params.length > 0) {
        	//链接里面的锚点"#ccc"
        	String anchors = "";
        	//链接里存在锚点
			if (url.contains("#")) {
				//锚点索引
				int anchorsIndex = url.indexOf('#');
				//截出不包含锚点的部分
				url = url.substring(0, anchorsIndex);
				//截出锚点
				anchors = url.substring(anchorsIndex,url.length() );
			}
			//在不包含锚点的部分拼接参数
            for (String param : params) {
                if (!url.contains((param.split("="))[0]+"=")) {
                    if (url.contains("?")) {
                        url = url + "&" + param;
                    } else {
                        url = url + "?" + param;
                    }
                }
            }
            //将拼接完的结果和锚点合并
			if(!TextUtils.isEmpty(anchors)){
				url = url+anchors;
			}
        }
        return url;
    }

    
    /**
     * 清除Url尾部的&
     * @param url
     * @return
     */
    public static String clearUrl(String url)
    {
    	if(url.endsWith("&")){
        	url = url.substring(0, url.length()-1);
        }
    	return url;
    }
    
    /**
     * 清除Url尾部的/
     * @param url
     * @return
     */
	public static String deleteLastSlash(String url) {
		if(isEmpty(url) || !url.endsWith("/")) {
			return url;
		}
		return url.substring(0, url.length() - 1);
	}
	
    /**
     * 为url去掉固定条件的param
     * @param url
     * @return
     */
    public static String removeParam(String url, String param){
        if (url.contains(param)) {
            if (url.contains("?"+param+"&")) {
                url = url.replace(param+"&", "");
            } else if (url.contains("?"+param)) {
                url = url.replace("?"+param, "");
            } else {
                url = url.replace("&"+param, "");
            }
        }
        return url;
    }
    /**
     * 为url去掉固定条件的param
     * @param url
     * @return
     */
    public static String removeParam(String url, String... params){
        if(isEmpty(url)) return "";
        for (String param : params) {
            if (params.length > 0) {
                if (url.contains(param)) {
                    if (url.contains("?" + param + "&")) {
                        url = url.replace(param + "&", "");
                    } else if (url.contains("?" + param)) {
                        url = url.replace("?" + param, "");
                    } else {
                        url = url.replace("&" + param, "");
                    }
                }
            }
        }
        return url;
    }

    /**
     * 为url增加参数如果有则替换
     * @param url
     * @param param 参数
     * @return
     */
    public static String addReplaceParam(String url, String param){
    	if(isEmpty(url)) return "";
        if(!isEmpty(param)){// param 为空，直接返回
            String s[] = param.split("=");
            if(s.length>1){
                String key = s[0];
                if(!isEmpty(key)){
                    url=removeNewUrlParam(url, key);
                }
            }
            url = addParam(url, param);
        }
        return url;
    }
    
    /**
     * 为url增加参数如果有则替换
     * @param url
     * @param params
     * @return
     */
    public static String addReplaceParam(String url, String... params) {
    	if(isEmpty(url)) return "";
    	if (params.length > 0) {
    		for (String param : params) {
    			if(!isEmpty(param)){// param 为空，直接返回
    	    		String s[] = param.split("=");
    	    		if(s.length>1){
    	    			String key = s[0];
    	    			if(!isEmpty(key)){
                                url=removeNewUrlParam(url, key);
    	    			}
    	    		}
    	    		url = addParam(url, param);
    	    	}
    		}
    	}
    	return url;
    }
	/**
	 * 为url增加参数如果有则替换
	 * @param url
	 * @param params
	 * @return
	 */
	public static String newAddReplaceParam(String url, String... params) {
		if(isEmpty(url)) return "";
		if (url.contains("#")){
			String urls=url.substring(0, url.indexOf('#'));
			if (params.length > 0) {
				for (String param : params) {
					if (!isEmpty(param)) {// param 为空，直接返回
						String s[] = param.split("=");
						if (s.length > 1) {
							String key = s[0];
							if (!isEmpty(key)) {
								urls = removeNewUrlParam(urls, key);
							}
						}
						urls = addParam(urls, param);
					}
				}
			}
			url=urls+url.substring(url.indexOf('#'));
		}else {
			if (params.length > 0) {
				for (String param : params) {
					if (!isEmpty(param)) {// param 为空，直接返回
						String s[] = param.split("=");
						if (s.length > 1) {
							String key = s[0];
							if (!isEmpty(key)) {
								url = removeNewUrlParam(url, key);
							}
						}
						url = addParam(url, param);
					}
				}
			}
		}

		return url;
	}
    
    /**
     * 获取url上某一个参数的值
     * @param url
     * @param key  url上需要获取其值的参数
     * @return
     */
    public static String getUrlParam(String url, String key){
    	String res = "";
        try {
            if (url != null && url.indexOf("?") > 0) {
                Uri uri = Uri.parse(url);
                res = uri.getQueryParameter(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    
    /**
     * 根据url上的一个参数来删除url上有关这个参数的单元
     * 先获取该参数对应的值，然后将这个参数单元移除
     * @param url
     * @param key
     * @return
     */
    public static String removeUrlParam(String url, String key){
    	String keyValue = getUrlParam(url, key);
    	if(isEmpty(keyValue)){
    		return url;
    	}
    	StringBuffer sbu = new StringBuffer().append(key).append("=").append(keyValue);
    	return removeParam(url, sbu.toString().trim());
    }
    
    public static String removeNewUrlParam(String url, String key){
    	String keyValue = getUrlParam(url, key);
        if(keyValue==null)
            return url;
    	StringBuffer sbu = new StringBuffer().append(key).append("=").append(keyValue);
    	return removeParam(url, sbu.toString().trim());
    }


    /**
     * 根据url上的一个参数(encode)来删除url上有关这个参数的单元
     * 先获取该参数对应的值，然后将这个参数单元移除
     * @param url
     * @param key
     * @return
     */
    public static String removeUrlEncodeParam(String url, String key){
    	if(isEmpty(getUrlParam(url, key))){
    		return url;
    	}
    	String keyValue = URLEncoder.encode(getUrlParam(url, key));
    	StringBuffer sbu = new StringBuffer().append(key).append("=").append(keyValue);
    	return removeParam(url, sbu.toString().trim());
    }
    
    public static Bundle parseUrl(String url) {
        url = url.replace("weiboconnect", "http");
        try {
            URL u = new URL(url);
            Bundle b = decodeUrl(u.getQuery());
            b.putAll(decodeUrl(u.getRef()));
            return b;
        } catch (MalformedURLException e) {
            return new Bundle();
        }
    }

	public static Bundle decodeUrl(String s) {
		Bundle params = new Bundle();
		if (s != null) {
			String array[] = s.split("&");
			for (String parameter : array) {
				String v[] = parameter.split("=");
				params.putString(URLDecoder.decode(v[0]), URLDecoder.decode(v[1]));
			}
		}
		return params;
	}
	/**
	 * 构造url
	 * @param url  原始url
	 * @param showpic  是否拼接图文浏览模式参数，
	 * <true表示拼接showpic=1,表示显示图片>
	 * <false表示拼接showpic=0,表示不显示图片>
	 * @return
	 */
	public static String formatShowPicUrl(String url, boolean showpic){
		String pageUrl = url;
		if(!pageUrl.contains("showpic")){
        	if(showpic){
        		pageUrl = UrlUtils.addParam(pageUrl, "showpic=1");
        	}else{
        		pageUrl = UrlUtils.addParam(pageUrl, "showpic=0");
        	}
        }
		pageUrl = UrlUtils.clearUrl(pageUrl);
		return pageUrl;
	}
	/**
	 * 构造url
	 * @param url 原始url
	 * @param isDetailSlid  是否拼接详情页上下翻页的参数，
	 * <true表示拼接pager=1,表示翻页>
	 * <false表示拼接pager=0,表示不翻页>
	 * @return
	 */
	public static String formatSlidUrl(String url, boolean isDetailSlid){
		String pageUrl = url;
		if(isDetailSlid){
			pageUrl = UrlUtils.addReplaceParam(pageUrl, "pager=1");
        }else{
        	pageUrl = UrlUtils.addReplaceParam(pageUrl, "pager=0");
        }
		pageUrl = UrlUtils.clearUrl(pageUrl);
		return pageUrl;
	}
	
	
	
	public static boolean isCacheVer(String url){
		if(isEmpty(url)) return false;
		return url.startsWith("http://") && url.contains("cachevers");
	}
	
	public static String createCacheversUrl(String url){
		int end = url.indexOf("?");
        final String fullUrl = url.substring(0, end);//拦截的全地址
        String version = UrlUtils.getUrlParam(url, "cachevers");
        String requestUrl = createAndroidUrl(fullUrl, version);
        return requestUrl;
	}
    
	private static String createAndroidUrl(String url, String version){
		return version+"."+url.substring(7);
	}
	
	public static String createCacheversFile(String url){
		return url+".cachevers";
	}
	
	

	
	public static String getType(String path){
		String type = "text/html";
		if(!isEmpty(path)){
			if(path.endsWith(".css")){
				type = "text/css";
			} else if(path.endsWith(".js")){
				type = "text/javascript";
			} else if(path.endsWith(".png")){
				type = "image/jpeg";
			}
		}
		return type;
	}
	
	/**
	 * 判断是否html模板而不是css,js,或图片
	 * @param url
	 * @return
	 */
	public static boolean isHtmlEnd(String url){
		String regex = "\\w+\\.(css|js|png|png1|jpg|gif)$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(url);
		return m.find();
	}
	
	/**
	 * 获取url中的参数并填充到hashmap中
	 * @param url
	 * @return
	 */
	public static Map<String, String> getQueryParams(String url) {
	    try {
	        Map<String, String> params = new HashMap<String, String>();
	        String[] urlParts = url.split("\\?");
	        if (urlParts.length > 1) {
	            String query = urlParts[1];
	            for (String param : query.split("&")) {
	                String[] pair = param.split("=");
	                String key = URLDecoder.decode(pair[0], "UTF-8");
	                String value = URLDecoder.decode(pair[1], "UTF-8");
	                params.put(key, value);
	            }
	        }

	        return params;
	    } catch (UnsupportedEncodingException ex) {
	        throw new AssertionError(ex);
	    }
	}
    /**
     * 获取url中的参数并填充到hashmap中，value为object
     * @param url
     * @return
     */
    public static Map<String, Object> getNewQueryParams(String url) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            String[] urlParts = url.split("\\?");
            if (urlParts.length > 1) {
                String query = urlParts[1];
                for (String param : query.split("&")) {
                    String[] pair = param.split("=");
                    String key = URLDecoder.decode(pair[0], "UTF-8");
                    Object value;
                    if (pair.length>1)
                     value = URLDecoder.decode(pair[1], "UTF-8");
                    else
                     value="";
                    params.put(key, value);
                }
            }

            return params;
        } catch (UnsupportedEncodingException ex) {
            throw new AssertionError(ex);
        }
    }
	
}
