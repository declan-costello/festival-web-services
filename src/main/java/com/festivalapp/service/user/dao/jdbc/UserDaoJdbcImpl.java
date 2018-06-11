package com.festivalapp.service.user.dao.jdbc;

import java.util.List;

import com.festivalapp.service.dao.jdbc.StandardDaoJdbcImpl;
import com.festivalapp.service.datamodel.User;
import com.festivalapp.service.user.dao.UserDao;

public class UserDaoJdbcImpl extends StandardDaoJdbcImpl implements UserDao {

	protected final String getUserQuery = "SELECT USER_ID,NAME,NICKNAME,DATE_OF_BIRTH,LOCATION FROM USERS;";

	protected final UserRowMapper userRowMapper = new UserRowMapper();

	public List<User> getUserInfo() {
		return queryForListBySqlStmt(getUserQuery, userRowMapper);
	}

}
