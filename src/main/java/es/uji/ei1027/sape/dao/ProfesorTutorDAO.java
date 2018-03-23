package es.uji.ei1027.sape.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.sape.model.ProfesorTutor;

@Repository
public class ProfesorTutorDAO {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
	        this.jdbcTemplate = new JdbcTemplate(dataSource); 
	}
	
	private static final class ProfesorTutorMapper implements RowMapper<ProfesorTutor>{

		@Override
		public ProfesorTutor mapRow(ResultSet rs, int arg1) throws SQLException {
			ProfesorTutor profesor = new ProfesorTutor();
			profesor.setId(rs.getInt("id"));
			profesor.setNombre(rs.getString("nombre"));
			profesor.setDepartamento(rs.getString("departamento"));
			profesor.setDespacho("despacho");
			profesor.setEmail(rs.getString("email"));
			return profesor;
		}
		
	}
	
	public ProfesorTutor getProfesorTutor(int id){
		return this.jdbcTemplate.queryForObject("SELECT id, nombre, departamento, despacho, email FROM ProfesorTutor WHERE id=?",
								new Object[]{id}, new ProfesorTutorMapper());
	}
	
	public void addProfesorTutor(ProfesorTutor p){
		this.jdbcTemplate.update("INSERT INTO ProfesorTutor(nombre,departamento,despacho,email) values (?,?,?,?);",
								p.getNombre(), p.getDepartamento(), p.getDespacho(), p.getEmail());
	}
	
	public void updateProfesorTutor(ProfesorTutor p){
		this.jdbcTemplate.update("UPDATE ProfesorTutor SET nombre=?, departamento=?, despacho=?, email=? WHERE id=?;",
								p.getNombre(), p.getDepartamento(), p.getDespacho(), p.getEmail(), p.getId());
	}
	
	public void deleteProfesorTutor(ProfesorTutor p){
		this.jdbcTemplate.update("DELETE FROM ProfesorTutor WHERE id=?;", p.getId());
	}
}

