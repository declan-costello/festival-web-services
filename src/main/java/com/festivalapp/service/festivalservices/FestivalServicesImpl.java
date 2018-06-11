package com.festivalapp.service.festivalservices;

import java.util.List;

import com.festivalapp.service.datamodel.StageTime;
import com.festivalapp.service.datamodel.User;
import com.festivalapp.service.datamodel.UserSchedule;
import com.festivalapp.service.stagetime.dao.jdbc.StageTimeDaoJdbcImpl;
import com.festivalapp.service.user.dao.jdbc.UserDaoJdbcImpl;
import com.festivalapp.service.userschedule.dao.jdbc.UserScheduleDaoJdbcImpl;

public class FestivalServicesImpl implements FestivalServices {

	private StageTimeDaoJdbcImpl stageTimeDaoJdbcImpl = new StageTimeDaoJdbcImpl();
	private UserDaoJdbcImpl userDaoJdbcImpl = new UserDaoJdbcImpl();
	private UserScheduleDaoJdbcImpl userScheduleDaoJdbcImpl = new UserScheduleDaoJdbcImpl();
	
	public List<StageTime> getStageTimes() {
		return stageTimeDaoJdbcImpl.getStageTimes();
	}

	public List<User> getUserInfo() {
		return userDaoJdbcImpl.getUserInfo();
	}

	public List<UserSchedule> getUserSchedule() {
		return userScheduleDaoJdbcImpl.getUserScheduleInfo();
	}
	
}
