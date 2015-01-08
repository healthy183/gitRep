package com.kang.system.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class URLFilterInvocationSecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource, InitializingBean {

	private final static List<ConfigAttribute> NULL_CONFIG_ATTRIBUTE = Collections.emptyList();

	// 权限集合
	private Map<RequestMatcher, Collection<ConfigAttribute>> requestMap;

	// @Autowired
	// private SysResourceRepository sysResourceRepository;

	// 获取所有权限集合
	public Collection<ConfigAttribute> getAllConfigAttributes() {

		Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();

		for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
			allAttributes.addAll(entry.getValue());
		}

		return allAttributes;

	}

	// 根据request请求获取访问资源所需权限
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {

		final HttpServletRequest request = ((FilterInvocation) object).getRequest();

		Collection<ConfigAttribute> attrs = NULL_CONFIG_ATTRIBUTE;

		for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
			if (entry.getKey().matches(request)) {
				attrs = entry.getValue();
				break;
			}
		}
		// logger.info("URL资源："+request.getRequestURI()+ " -> " + attrs);
		return attrs;

	}

	private Map<String, String> loadResuorce() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		/*
		 * SELECT S3.RESOURCE_PATH, S2.AUTHORITY_MARK FROM
		 * SYS_AUTHORITIES_RESOURCES S1 JOIN SYS_AUTHORITIES S2 ON
		 * S1.AUTHORITY_ID = S2.AUTHORITY_ID JOIN SYS_RESOURCES S3 ON
		 * S1.RESOURCE_ID = S3.RESOURCE_ID where S3.RESOURCE_TYPE='URL' ORDER BY
		 * S3.PRIORITY DESC
		 */
		// List<Map<String,String>> list =
		// this.sysResourceRepository.getURLResourceMapping();
		List<Map<String, String>> list = null;

		Iterator<Map<String, String>> it = list.iterator();
		while (it.hasNext()) {
			Map<String, String> rs = it.next();
			String resourcePath = rs.get("resourcePath");
			String authorityMark = rs.get("authorityMark");

			if (map.containsKey(resourcePath)) {
				String mark = map.get("resourcePath");
				map.put(resourcePath, mark + "," + authorityMark);
			} else {
				map.put(resourcePath, authorityMark);
			}
		}
		return map;
	}

	protected Map<RequestMatcher, Collection<ConfigAttribute>> bindRequestMap() {
		Map<RequestMatcher, Collection<ConfigAttribute>> map = new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>();

		Map<String, String> resMap = this.loadResuorce();
		for (Map.Entry<String, String> entry : resMap.entrySet()) {
			String key = entry.getKey();
			Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
			atts = SecurityConfig.createListFromCommaDelimitedString(entry.getValue());
			map.put(new AntPathRequestMatcher(key), atts);
		}

		return map;
	}

	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

	public void afterPropertiesSet() throws Exception {
		this.requestMap = this.bindRequestMap();
		// logger.info("资源权限列表"+this.requestMap);
	}

	public void refreshResuorceMap() {
		this.requestMap = this.bindRequestMap();
	}

}
