package com.festivalapp.service.dao.jdbc;

import java.sql.ResultSet;
import java.util.List;

public interface RowMapper<R> {
	
	public List<R> mapRow(ResultSet rs);
	
}