package com.festivalapp.service.user.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.festivalapp.service.dao.jdbc.RowMapper;
import com.festivalapp.service.datamodel.User;

public class UserRowMapper implements RowMapper<User> {
	
	private static final String USER_ID = "USER_ID";
	private static final String NAME = "NAME";
	private static final String NICKNAME = "NICKNAME";
	private static final String DATE_OF_BIRTH = "DATE_OF_BIRTH";
	private static final String LOCATION = "LOCATION";
	
    List<User> userList = new ArrayList<User>();

	public List<User> mapRow(ResultSet rs){
		
		try {
			while (rs.next()) {
				User userInfo = new User();
				userInfo.setUserId(rs.getString(USER_ID));
				userInfo.setName(rs.getString(NAME));
				userInfo.setNickname(rs.getString(NICKNAME));
				userInfo.setDob(rs.getDate(DATE_OF_BIRTH));
				userInfo.setLocation(rs.getString(LOCATION));
				userList.add(userInfo);
			}
		} catch (SQLException e) {
			System.out.println("Error mapping result list");
			e.printStackTrace();
		}
		
		return userList;
	}
	
}