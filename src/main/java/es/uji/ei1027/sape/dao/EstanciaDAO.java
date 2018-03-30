package es.uji.ei1027.sape.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.sape.model.Estancia;

@Repository
public class EstanciaDAO {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
	        this.jdbcTemplate = new JdbcTemplate(dataSource); 
	}
	
	private static final class EstanciaMapper implements RowMapper<Estancia>{

		@Override
		public Estancia mapRow(ResultSet rs, int arg1) throws SQLException {
			Estancia estancia = new Estancia();
			estancia.setId(rs.getInt("id"));
			estancia.setCifEmpresa(rs.getString("cifEmpresa"));
			estancia.setContactPerson(rs.getString("contactPerson"));
			estancia.setMailContactPerson(rs.getString("mailContactPerson"));
			estancia.setInternshipDescription(rs.getString("internshipDescription"));
			return estancia;
		}
		
	}
	
	public Estancia getEstancia(int id){
		return this.jdbcTemplate.queryForObject("SELECT id, cifEmpresa, contactPerson, mailContactPerson, internshipDescription WHERE id=?;",
								new Object[]{id}, new EstanciaMapper());
	}
	
	public void addEstancia(Estancia e){
		this.jdbcTemplate.update("INSERT INTO Estancia(cifEmpresa, contactPerson, mailContactPerson, internshipDescription) value (?,?,?,?);",
								e.getCifEmpresa(), e.getContactPerson(), e.getMailContactPerson(), e.getInternshipDescription());
	}
	
	public void updateEstancia(Estancia e){
		this.jdbcTemplate.update("UPDATE Estancia SET cifEmpresa=?, contactPerson=?, mailContactPerson=?, internshipDescription=? WHERE id=?;",
								e.getCifEmpresa(), e.getContactPerson(), e.getMailContactPerson(), e.getInternshipDescription(), e.getId());
	}
	
	public void deleteEstancia(Estancia e){
		this.jdbcTemplate.update("DELETE FROM Estancia WHERE id=?;", e.getId());
	}
}