package es.uji.ei1027.sape.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.sape.model.UserDetails;

@Repository
public class UserDAO {
	
	private final BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
	        this.jdbcTemplate = new JdbcTemplate(dataSource); 
	}
	
	public static final class UserMapper implements RowMapper<UserDetails>{

		@Override
		public UserDetails mapRow(ResultSet rs, int arg1) throws SQLException {
			UserDetails u = new UserDetails();
			u.setUsername(rs.getString("usuario"));
			u.setPassword(rs.getString("password"));
			u.setDni(rs.getString("dniEstudiante"));
			u.setType(rs.getInt("tipo"));
			return u;
		}
		
	}
	
	public UserDetails loadUserByUsername(String username, String password){
		UserDetails user;
		try {
			user = this.jdbcTemplate.queryForObject("SELECT * FROM Usuario WHERE usuario=?;", new Object[] {username.trim()}, new UserMapper());
		}catch(EmptyResultDataAccessException e) {
			return null;
		}
		if (passwordEncryptor.checkPassword(password, user.getPassword()))
			return user; 
		else
			return null;
	}
	
	public void addUser(UserDetails u) {
		this.jdbcTemplate.update("INSERT INTO Usuario (usuario, password, dniEstudiante, tipo) values (?,?,?,?)",
				u.getUsername(), passwordEncryptor.encryptPassword(u.getPassword()), u.getDni(), u.getType());
	}
	
}
