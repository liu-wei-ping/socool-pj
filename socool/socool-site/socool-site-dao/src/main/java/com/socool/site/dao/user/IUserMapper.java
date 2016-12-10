/*** */
package com.socool.site.dao.user;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author mr.lwp
 * @see 2016年12月10日
 */
@Repository
public interface IUserMapper {
	/**
	 * @param userName
	 * @param pwd
	 * @return
	 */
	@Select("SELECT username from qdm20999941_db.socool_user where username=#{userName} and password=#{pwd}")
	String getUserName(@Param("userName") String userName, @Param("pwd") String pwd);
}
