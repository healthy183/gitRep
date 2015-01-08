package com.kang.user.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.kang.base.dao.IbaseDao;
import com.kang.base.service.ext.BaseHibernateService;
import com.kang.model.test.TbUserDetail;
import com.kang.model.test.TdUser;
import com.kang.user.dao.ITDuserDao;
import com.kang.user.dao.ITbUserDetailDao;
import com.kang.user.service.ITDuserService;


@Scope("prototype")
@Service("tduserServiceImpl")
public class TDuserServiceImpl
	extends BaseHibernateService<TdUser, Integer>
		implements ITDuserService {

	//ITbUserDetailService
	private  ITDuserDao tduserDaoImpl;
	
	@Autowired
	private ITbUserDetailDao tbUserDetailDao;
	
	@Override
	@Autowired
	public void setBaseDao(IbaseDao<TdUser, Integer> baseDao) {
		this.baseDao =  baseDao;
		this.tduserDaoImpl = (ITDuserDao) baseDao;
	}

	public String login(TdUser user) {
		
		List<TdUser> userList = tduserDaoImpl.login(user);
		
			if(CollectionUtils.isEmpty(userList)){
				return "user no found!";
			}
			
			TdUser tduser = userList.get(0);
			
			TdUser thistduser = tduserDaoImpl.get(tduser.getId());
			
			String flag =  thistduser.getFlag();
			
			if(StringUtils.equals("1", flag)){
				return "用户已经被锁定，请与管理员联系！";
			}
			
			if(StringUtils.equals(user.getPassword(),thistduser.getPassword())){
				
				thistduser.setNumber("0");
				 
				 //TbUserDetail TbUserDetail = 
				 tduserDaoImpl.merge(thistduser);
				 
				 TbUserDetail detail = tbUserDetailDao.get(thistduser.getId());
				 detail.setSuccess((Integer.valueOf(detail.getSuccess())+1)+"");
				 
				return "success";
			}else{
				
				String  number = thistduser.getNumber();
				
				 int num = Integer.valueOf(number) +1;
				 
				 if(num == 3){
					 thistduser.setFlag("1");
					 thistduser.setNumber("0");
				 }else{
					 thistduser.setNumber(num+"");
				 }
				 
				 tduserDaoImpl.save(thistduser);
				 
				 
				 return "密码错误！";
			}
			
			

			
	}

	public List<TdUser> allInfo() {
		return tduserDaoImpl.allInfo();
	}
}
