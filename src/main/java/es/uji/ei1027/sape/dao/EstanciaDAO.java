package es.uji.ei1027.sape.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
	
	public List<Estancia> getEstancias(){
		return this.jdbcTemplate.query("SELECT * FROM Estancia;", new EstanciaMapper());
	}
	
	public Estancia getEstancia(int id){
		return this.jdbcTemplate.queryForObject("SELECT id, cifEmpresa, contactPerson, mailContactPerson, internshipDescription FROM Estancia WHERE id=?;",
								new Object[]{id}, new EstanciaMapper());
	}
	
	public void addEstancia(Estancia e){
		this.jdbcTemplate.update("INSERT INTO Estancia(cifEmpresa, contactPerson, mailContactPerson, internshipDescription) values(?,?,?,?);",
								e.getCifEmpresa(), e.getContactPerson(), e.getMailContactPerson(), e.getInternshipDescription());
		List<Integer> id = this.jdbcTemplate.query("SELECT currval(pg_get_serial_sequence('Estancia', 'id'))", new SerialMapper());
		e.setId(id.get(0));
	}
	
	public void updateEstancia(Estancia e){
		this.jdbcTemplate.update("UPDATE Estancia SET cifEmpresa=?, contactPerson=?, mailContactPerson=?, internshipDescription=? WHERE id=?;",
								e.getCifEmpresa(), e.getContactPerson(), e.getMailContactPerson(), e.getInternshipDescription(), e.getId());
	}
	
	public void deleteEstancia(Estancia e){
		this.jdbcTemplate.update("DELETE FROM Estancia WHERE id=?;", e.getId());
	}
}
