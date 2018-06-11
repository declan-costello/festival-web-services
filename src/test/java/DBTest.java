import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

import com.festivalapp.service.datamodel.StageTime;

public class DBTest {
	
	@Test
	public void testDb(){
		String dbUrl = "jdbc:mysql://localhost:3306/festival";
		String dbUsername = "root";
		String dbPassword = "password";
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
				stageTime.setStage(rs.getString("STAGE_NAME"));
				stageTime.setArtistName(rs.getString("ARTIST_NAME"));
				stageTime.setSetTime(rs.getString("SET_TIME"));
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
	}
	/*
	@Test
	public void DBTest2(){
		Map<String,Object> resultsMap = new HashMap<String, Object>();
		List<StageTime> stageTimes = getStageTimes();
		resultsMap.put("data", stageTimes);
		assertEquals(resultsMap.get("data"), stageTimes);
	}

	private List<StageTime> getStageTimes() {
		String dbUrl = "jdbc:mysql://localhost:3306/festival";
		String dbUsername = "root";
		String dbPassword = "password";
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
				stageTime.setStage(rs.getString("STAGE_NAME"));
				stageTime.setArtistName(rs.getString("ARTIST_NAME"));
				stageTime.setSetTime(rs.getString("SET_TIME"));
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
	*/
}
