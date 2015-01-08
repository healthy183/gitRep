package com.kang.user.dao.ext;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.kang.base.dao.ext.BaseHibernateDao;
import com.kang.model.test.TdUser;
import com.kang.user.dao.ITDuserDao;

@Scope("prototype")
@Repository("tdUserDaoImpl")
public class TdUserDaoImpl extends BaseHibernateDao<TdUser,Integer> implements
		ITDuserDao {

	public List<TdUser> login(final TdUser user) {
		//and  password= :password
		String sql = "select  id,userName,password,number,flag from  tb_user where userName = :userName ";
		
		List<Object[]> list = getSession().createSQLQuery(sql)
					.setParameter("userName",  user.getUserName())
					//.setParameter("password",  user.getPassword())
				.list();
				
		List<TdUser> userList =  new ArrayList<TdUser>();	
		
		if(CollectionUtils.isEmpty(list)){
			return userList;
		}
		
		TdUser tdUser = null;
		
		for(Object[] obj :list){
			
			tdUser   = new TdUser();
			tdUser.setId(Integer.valueOf(obj[0].toString()));
			userList.add(tdUser);
		}
		
		return userList;
	}

	public List<TdUser> allInfo() {
		// TODO Auto-generated method stub
		return null;
	}}
