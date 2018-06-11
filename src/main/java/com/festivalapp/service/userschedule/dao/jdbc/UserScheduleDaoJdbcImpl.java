package com.festivalapp.service.userschedule.dao.jdbc;

import java.util.List;

import com.festivalapp.service.dao.jdbc.StandardDaoJdbcImpl;
import com.festivalapp.service.datamodel.UserSchedule;
import com.festivalapp.service.userschedule.dao.UserScheduleDao;

public class UserScheduleDaoJdbcImpl extends StandardDaoJdbcImpl implements UserScheduleDao {
	
	protected final String getUserScheduleQuery = "SELECT USER_ID,STAGE_NAME,ARTIST_NAME,SET_TIME,DAY FROM USER_SCHEDULES;";

	protected final UserScheduleRowMapper userScheduleRowMapper = new UserScheduleRowMapper();

	public List<UserSchedule> getUserScheduleInfo() {
		return queryForListBySqlStmt(getUserScheduleQuery, userScheduleRowMapper);
	}
	
}
