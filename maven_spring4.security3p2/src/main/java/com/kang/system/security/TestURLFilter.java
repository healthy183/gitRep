package com.kang.system.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class TestURLFilter {

	// 权限集合
	private  static Map<RequestMatcher, Collection<ConfigAttribute>> requestMap;
	
	public static void main(String[] args) {
		
		TestURLFilter test = new TestURLFilter();
		//String str = test.bindRequestMap().toString();
		//System.out.println(str);
		requestMap = test.bindRequestMap();
		//System.out.println(requestMap.toString());
		/**
		 {Ant [pattern='0_path']=[0_mark, 0_mark], Ant [pattern='1_path']=[1_mark], Ant [pattern='2_path']=[2_mark], Ant [pattern='3_path']=[3_mark]}
		 * **/
		
		Collection<ConfigAttribute>  collection = test.getAllConfigAttributes();
		
	}
	
	public Collection<ConfigAttribute> getAllConfigAttributes() {

		Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();

		//requestMap.entrySet();
		System.out.println(requestMap.entrySet());
		
		for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
			allAttributes.addAll(entry.getValue());
		}

		return allAttributes;

	}

	protected Map<RequestMatcher, Collection<ConfigAttribute>> bindRequestMap() {
		Map<RequestMatcher, Collection<ConfigAttribute>> map = new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>();

		Map<String, String> resMap = this.loadResuorce();
		
		//String str =  resMap.toString();
		//System.out.println(str);
		
		for (Map.Entry<String, String> entry : resMap.entrySet()) {
			String key = entry.getKey();
			Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
			atts = SecurityConfig.createListFromCommaDelimitedString(entry.getValue());
			map.put(new AntPathRequestMatcher(key), atts);
		}

		return map;
	}

	
	private Map<String,String> loadResuorce(){  
		
        Map<String,String> map = new LinkedHashMap<String,String>();  
        
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        
        for(int i = 0;i<=3;i++){
        	
        	Map<String,String> listMap = new HashMap<String,String>();  
        	listMap.put("resourcePath", i+"_path");  
        	listMap.put("authorityMark",i+"_mark");
        	
        	list.add(listMap);
        }  
        
        Map<String,String> listMap = new HashMap<String,String>();  
    	listMap.put("resourcePath", 0+"_path");  
    	listMap.put("authorityMark",0+"_mark");
    	list.add(listMap);
        
        Iterator<Map<String,String>> it = list.iterator();  
        while(it.hasNext()){  
            Map<String,String> rs = it.next();  
            String resourcePath = rs.get("resourcePath");  
            String authorityMark = rs.get("authorityMark");  
              
            if(map.containsKey(resourcePath)){  
                String mark = map.get(resourcePath);  
                map.put(resourcePath, mark+","+authorityMark);  
            }else{  
                map.put(resourcePath, authorityMark);  
            }  
        }  
        return map;  
    } 
	

}
