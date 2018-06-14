package com.festivalapp.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.festivalapp.service.datamodel.StageTime;
import com.festivalapp.service.datamodel.User;
import com.festivalapp.service.datamodel.UserSchedule;
import com.festivalapp.service.festivalservices.FestivalServicesImpl;

@RestController
public class StageTimeController {
	
	private static final String URL_GET_STAGE_TIMES = "/getStageTimes";
	private static final String URL_GET_USERS = "/getUserInfo";
	private static final String URL_GET_USER_SCHEDULES = "/getUserScheduleInfo";
	
	private FestivalServicesImpl festivalServicesImpl = new FestivalServicesImpl();

	@RequestMapping(URL_GET_STAGE_TIMES)
	public Object getAllStageTimes() {
		Map<String,Object> resultsMap = new HashMap<String, Object>();
		List<StageTime> stageTimes = getStageTimes();
		resultsMap.put("data", stageTimes);
		return resultsMap;
	}
	
//	@RequestMapping(URL_GET_STAGE_TIMES)
//	public Object getStageTimes() {
//		Map<String,Object> resultsMap = new HashMap<String, Object>();
//		List<StageTime> stageTimes = festivalServicesImpl.getStageTimes();
//		resultsMap.put("data", stageTimes);
//		return resultsMap;
//	}
	
	@RequestMapping(URL_GET_USERS)
	public Object getUsers() {
		Map<String,Object> resultsMap = new HashMap<String, Object>();
		List<User> userList = festivalServicesImpl.getUserInfo();
		resultsMap.put("data", userList);
		return resultsMap;
	}
	
	@RequestMapping(URL_GET_USER_SCHEDULES)
	public Object getUserSchedules() {
		Map<String,Object> resultsMap = new HashMap<String, Object>();
		List<UserSchedule> userScheduleList = festivalServicesImpl.getUserSchedule();
		resultsMap.put("data", userScheduleList);
		return resultsMap;
	}

	private List<StageTime> getStageTimes() {
		// Local db settings
		//String dbUrl = "jdbc:mysql://localhost:3306/festival";
        //String dbUsername = "root";
		//String dbPassword = "password";
		
		// Openshift db settings
		//String dbHost = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
		//String dbPort = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
		//String dbUrl = "jdbc:mysql://" + dbHost + ":" + dbPort + "/jbossews";
		//String dbUsername = "adminSvb4x5j";
		//String dbPassword = "ZCrrXCB652HH";
		
		String dbHost = "jws-app-mysql";
	    String DB_NAME = "lllc";
	    String dbPort = "3306";
	    String dbUrl = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + DB_NAME;
	    String dbUsername = "user";
	    String dbPassword = "password";
	    String DRIVER = "com.mysql.jdbc.Driver";
		
		String getAllStageTimesQuery = "SELECT * FROM STAGE_TIMES";
		
		ResultSet rs = null;
		Connection conn = null;
		
		List<StageTime> stageTimes = new ArrayList<StageTime>();

		// ensure mysql jdbc connector jar is available, build path etc
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Cant load database driver");
		}

		// attempt to connect to the db		
		try {
			conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		} catch (SQLException e) {
			System.out.println("Cant connect to database");
		}

		// Query the db
		try {
			PreparedStatement statement = conn.prepareStatement(getAllStageTimesQuery);
			rs = statement.executeQuery();
			while (rs.next()) {
				StageTime stageTime = new StageTime();
				stageTime.setSetTime(rs.getString("SET_TIME"));
				stageTime.setStage(rs.getString("STAGE_NAME"));
				stageTime.setArtistName(rs.getString("ARTIST_NAME"));
				stageTime.setDay(rs.getString("DAY"));
				stageTimes.add(stageTime);
			}
		} catch (SQLException e1) {
			System.out.println("Error executing query");
			e1.printStackTrace();
		} finally {
	        if (conn != null) {
	        	try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    }		
		return stageTimes;
	}

}