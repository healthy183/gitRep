package com.kang.boot.comon.dao;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public abstract class BaseJpaDaoImpl<M extends java.io.Serializable, PK extends java.io.Serializable> {

	//private EntityManager superEntityManager;
	public abstract EntityManager getEntityManager();

	// 创建标准hqlQuery
	public List queryHql(String hql, Object... paramlist) {
		return createQuery(hql, paramlist).getResultList();
	}

	// 使用hql.分页查询函数
	public Page pageHqlQuery(String hql, int pageNo, int pageSize,
			Object... paramlist) {
		// Assert.hasText(hql);
		// Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
		// Count查询
		String countQueryString = " select count (1) "
				+ removeSelect(removeOrders(hql));
		Query query = null;

		query = createQuery(countQueryString, paramlist);
		List countlist = query.getResultList();
		long totalCount = (Long) countlist.get(0);// 这样子也行啊？

		if (totalCount < 1)// 没有对应记录
			return new Page();
		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);
		query = createQuery(hql, paramlist);
		List list = query.setFirstResult(startIndex).setMaxResults(pageSize)
				.getResultList();

		return new Page(startIndex, totalCount, pageSize, list);
	}

	// 创建hql的query对象
	private Query createQuery(String hql, Object[] paramlist) {
		// Assert.hasText(hql);
		Query query = getEntityManager().createQuery(hql);
		setParameters(query, paramlist);
		return query;
	}

	/**
	 * 去除hql的select 子句，未考虑union的情况,用于pagedQuery.
	 *
	 * @see #pagedQuery(String,int,int,Object[])
	 */
	private static String removeSelect(String hql) {
		// Assert.hasText(hql);
		int beginPos = hql.toLowerCase().indexOf("from");
		// Assert.isTrue(beginPos != -1, " hql : " + hql +
		// " must has a keyword 'from'");
		return hql.substring(beginPos);
	}

	/**
	 * 去除hql的orderby 子句，用于pagedQuery.
	 *
	 * @see #pagedQuery(String,int,int,Object[])
	 */
	private static String removeOrders(String hql) {
		// Assert.hasText(hql);
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*",
				Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}

	/** 使用hql执行批处理语句.如 之间insert, update, delete 等. 返回影响行数 */
	public int execteBulk(final String hql, final Object... paramlist) {
		Query query = createQuery(hql, paramlist);
		Object result = query.executeUpdate();
		return result == null ? 0 : ((Integer) result).intValue();
	}

	/** 使用标准sql执行批处理语句.如 之间insert, update, delete 等. 返回影响行数 */
	public int execteNativeBulk(final String natvieSQL,
			final Object... paramlist) {
		Query query = createSqlQuery(natvieSQL, paramlist);
		Object result = query.executeUpdate();
		return result == null ? 0 : ((Integer) result).intValue();
	}

	// 执行标准sql查询
	public List querySql(String natvieSQL, Object... paramlist) {
		return createSqlQuery(natvieSQL, paramlist).getResultList();
	}

	// 使用sql.分页查询函数
	public Page pageSqlQuery(String natvieSQL, int pageNo, int pageSize,
			Object... paramlist) {
		// Assert.hasText(hql);
		// Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
		// Count查询
		String countQueryString = " select count (1) "
				+ removeSelect(removeOrders(natvieSQL));
		Query query = null;

		query = createSqlQuery(countQueryString, paramlist);
		List countlist = query.getResultList();
		long totalCount = (Long) countlist.get(0);// 这样子也行啊？

		if (totalCount < 1)// 没有对应记录
			return new Page();
		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);
		query = createSqlQuery(natvieSQL, paramlist);
		List list = query.setFirstResult(startIndex).setMaxResults(pageSize)
				.getResultList();

		return new Page(startIndex, totalCount, pageSize, list);
	}

	// 创建标准sqlQuery
	private Query createSqlQuery(String natvieSQL, Object[] paramlist) {
		Query query = getEntityManager().createNativeQuery(natvieSQL);
		setParameters(query, paramlist);
		return query;
	}

	// 为query对象设置Parameters
	private void setParameters(Query query, Object[] paramlist) {
		if (paramlist != null) {
			for (int i = 0; i < paramlist.length; i++) {
				/*
				 * if(paramlist[i] instanceof Date) { //TODO 难道这是bug
				 * 使用setParameter不行？？ query.setTimestamp(i, (Date)paramlist[i]);
				 * } else { query.setParameter(i, paramlist[i]); }
				 */
				query.setParameter(i, paramlist[i]);
			}
		}
	}

	// 使用带in()函数的hql.分页查询函数
	/*
	 * public Page pageQueryWithIn(String hql, int pageNo, int pageSize, final
	 * Map<String, Collection<?>> map, final Object... paramlist){
	 * 
	 * String countQueryString = " select count (1) " +
	 * removeSelect(removeOrders(hql)); Query query = null;
	 * 
	 * query = createQuery(countQueryString, paramlist);
	 * setParameterList(query,map); List countlist = query.getResultList();
	 * 
	 * long totalCount = (Long) countlist.get(0);//这样子也行啊？
	 * 
	 * if (totalCount < 1)//没有对应记录 return new Page();
	 * 
	 * int startIndex = Page.getStartOfPage(pageNo, pageSize); query =
	 * createQuery(hql, paramlist); setParameterList(query,map);
	 * 
	 * List list =
	 * query.setFirstResult(startIndex).setMaxResults(pageSize).getResultList();
	 * return new Page(startIndex, totalCount, pageSize, list); }
	 */

	// 创建带集合Collection 的hqlQuery
	/*
	 * public List queryHqlWithIn(final String hql, final Map<String,
	 * Collection<?>> map, Object... paramlist){ Query query =
	 * createQuery(hql,paramlist); setParameterList(query,map); return
	 * query.getResultList(); }
	 */

	//
	// 设置带in函数的hql语句ParameterList
	/*
	 * public void setParameterList(Query query,Map<String, Collection<?>> map){
	 * for (Entry<String, Collection<?>> e : map.entrySet()) {
	 * query.setParameterList(e.getKey(), e.getValue()); } }
	 */

}
