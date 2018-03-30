package es.uji.ei1027.sape.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public final class SerialMapper implements RowMapper<Integer> {

	@Override
	public Integer mapRow(ResultSet rs, int arg1) throws SQLException {
		return rs.getInt("currval");
	}
}
