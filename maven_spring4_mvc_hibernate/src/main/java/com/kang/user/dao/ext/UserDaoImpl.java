package com.kang.user.dao.ext;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.kang.base.dao.ext.BaseHibernateDao;
import com.kang.model.SysUsers;
import com.kang.model.vo.SysUsersVo;
import com.kang.tool.Page;
import com.kang.user.dao.IuserDao;


@Scope("prototype")
@Repository("userDaoImpl")
public class UserDaoImpl 
	extends BaseHibernateDao<SysUsers,Integer>
			implements IuserDao {

	  //使用存储过程查询
	public List<SysUsersVo> findAllUserByPro() {
		
		//System.out.println("wfh");
		
		 List<SysUsersVo> voList = new ArrayList<SysUsersVo>();
		 
		//使用存储过程查询
		/*List<Object[]> objArray = getSession()
			.createSQLQuery("{call findAllUserByPro()}").list();*/
		//带模糊查询参数的存储过程
		 List<Object[]> objArray =
				 getSession().createSQLQuery("{call testParameter(?)}")
				 	.addScalar("usrname",StringType.INSTANCE)
				 	.addScalar("usrpwd",StringType.INSTANCE)
				 	.addScalar("usrtype",IntegerType.INSTANCE)
						.setString(0, "user%").list();//
		 
		SysUsersVo vo = null;
		for(Object[] obj : objArray){
			vo = new SysUsersVo();
			vo.setUsrname(obj[0] != null?obj[0].toString():null);
			vo.setUsrpwd(obj[1] !=null?obj[1].toString():null);
			vo.setUsrtype(obj[2]!=null?(Integer)obj[2]:null);
			voList.add(vo);
		}
		
		return voList;
	}
	
		
	
}
