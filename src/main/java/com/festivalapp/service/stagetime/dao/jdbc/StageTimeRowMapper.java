package com.festivalapp.service.stagetime.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.festivalapp.service.dao.jdbc.RowMapper;
import com.festivalapp.service.datamodel.StageTime;

public class StageTimeRowMapper implements RowMapper<StageTime> {
	
	private static final String STAGE_NAME = "STAGE_NAME";
	private static final String ARTIST_NAME = "ARTIST_NAME";
	private static final String SET_TIME = "SET_TIME";
;	private static final String DAY = "DAY";
	
    List<StageTime> stageTimes = new ArrayList<StageTime>();

	public List<StageTime> mapRow(ResultSet rs){
		
		try {
			while (rs.next()) {
				StageTime stageTime = new StageTime();
				stageTime.setStage(rs.getString(STAGE_NAME));
				stageTime.setArtistName(rs.getString(ARTIST_NAME));
				stageTime.setSetTime(rs.getString(SET_TIME));
				stageTime.setDay(rs.getString(DAY));
				stageTimes.add(stageTime);
			}
		} catch (SQLException e) {
			System.out.println("Error executing query");
			e.printStackTrace();
		}
		
		return stageTimes;
	}
	
}