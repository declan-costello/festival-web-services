package com.festivalapp.service.stagetime.dao.jdbc;

import java.util.List;

import com.festivalapp.service.dao.jdbc.StandardDaoJdbcImpl;
import com.festivalapp.service.datamodel.StageTime;
import com.festivalapp.service.stagetime.dao.StageTimeDao;

public class StageTimeDaoJdbcImpl extends StandardDaoJdbcImpl implements StageTimeDao {

	protected final String getAllStageTimesQuery = "SELECT STAGE_NAME,ARTIST_NAME,SET_TIME,DAY FROM STAGE_TIMES";
	
	protected final StageTimeRowMapper stageTimeRowMapper = new StageTimeRowMapper();

	public List<StageTime> getStageTimes() {
		return queryForListBySqlStmt(getAllStageTimesQuery, stageTimeRowMapper);
	}
	
}
