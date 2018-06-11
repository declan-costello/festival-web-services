package com.festivalapp.service.userschedule.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.festivalapp.service.dao.jdbc.RowMapper;
import com.festivalapp.service.datamodel.UserSchedule;

public class UserScheduleRowMapper implements RowMapper<UserSchedule> {
	
	private static final String USER_ID = "USER_ID";
	private static final String STAGE_NAME = "STAGE_NAME";
	private static final String ARTIST_NAME = "ARTIST_NAME";
	private static final String SET_TIME = "SET_TIME";
	private static final String DAY = "DAY";
	
    List<UserSchedule> userScheduleList = new ArrayList<UserSchedule>();

	public List<UserSchedule> mapRow(ResultSet rs){
		
		try {
			while (rs.next()) {
				UserSchedule userSchedule = new UserSchedule();
				userSchedule.setUserId(rs.getString(USER_ID));
				userSchedule.setStage(rs.getString(STAGE_NAME));
				userSchedule.setArtistName(rs.getString(ARTIST_NAME));
				userSchedule.setSetTime(rs.getString(SET_TIME));
				userSchedule.setDay(rs.getString(DAY));
				userScheduleList.add(userSchedule);
			}
		} catch (SQLException e) {
			System.out.println("Error mapping result list");
			e.printStackTrace();
		}
		
		return userScheduleList;
	}
	
}