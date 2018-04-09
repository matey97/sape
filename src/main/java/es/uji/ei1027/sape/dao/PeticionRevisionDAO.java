package es.uji.ei1027.sape.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;
import javax.sql.rowset.serial.SerialRef;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.sape.model.PeticionRevision;

@Repository
public class PeticionRevisionDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
	        this.jdbcTemplate = new JdbcTemplate(dataSource); 
	}
	
	private static final class PeticionRevisionMapper implements RowMapper<PeticionRevision>{

		@Override
		public PeticionRevision mapRow(ResultSet rs, int arg1) throws SQLException {
			PeticionRevision peticionrevision = new PeticionRevision();
			peticionrevision.setId(rs.getString("id"));
			peticionrevision.setNumeroProyecto(rs.getInt("numeroProyecto"));
			peticionrevision.setFecha(rs.getDate("fecha"));
			peticionrevision.setTextoPeticion(rs.getString("textoPeticion"));
			return peticionrevision;
		}
	}
	
	public List<PeticionRevision> getPeticionRevisions(){
		return this.jdbcTemplate.query("SELECT id, numeroProyecto, fecha, textoPeticion FROM PeticionRevision;", new PeticionRevisionMapper());
	}
	
	public PeticionRevision getPeticionRevision(int id){
		return this.jdbcTemplate.queryForObject("id, numeroProyecto, fecha, textoPeticion FROM PeticionRevision"
												+ "WHERE id = ?;", new Object[]{id}, new PeticionRevisionMapper());
	}
	
	public void addPeticionRevision(PeticionRevision p){
		this.jdbcTemplate.update("INSERT INTO PeticionRevision(id, numeroProyecto, fecha, textoPeticion) values (?,?,?,?);",
							p.getId(), p.getNumeroProyecto(), p.getFecha(), p.getTextoPeticion());
	}
	
	public void updatePeticionRevision(PeticionRevision p){
		this.jdbcTemplate.update("UPDATE PeticionRevision SET numeroProyecto=?, objetivo=?, estado=?, fecha=?, fechaUltimoCambio=?, textoPeticion=?, idEstancia=? WHERE id=?;",
				p.getNumeroProyecto(), p.getFecha(), p.getTextoPeticion(), p.getId());
	}
	
	public void deletePeticionRevision(PeticionRevision p){
		this.jdbcTemplate.update("DELETE FROM PeticionRevision WHERE id=?;", p.getId());
	}
}