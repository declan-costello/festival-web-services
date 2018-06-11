package com.festivalapp.service.festivalservices;

import java.util.List;

import com.festivalapp.service.datamodel.StageTime;
import com.festivalapp.service.datamodel.User;
import com.festivalapp.service.datamodel.UserSchedule;

public interface FestivalServices {

	public List<StageTime> getStageTimes();
	
	public List<User> getUserInfo();
	
	public List<UserSchedule> getUserSchedule();
	
}
