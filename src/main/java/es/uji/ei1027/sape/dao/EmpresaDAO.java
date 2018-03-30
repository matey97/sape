package es.uji.ei1027.sape.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.sape.model.Empresa;

@Repository
public class EmpresaDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
	        this.jdbcTemplate = new JdbcTemplate(dataSource); 
	}
	
	private static final class EmpresaMapper implements RowMapper<Empresa>{

		@Override
		public Empresa mapRow(ResultSet rs, int arg1) throws SQLException {
			Empresa empresa = new Empresa();
			empresa.setCif(rs.getString("cif"));
			empresa.setNombre(rs.getString("nombre"));
			empresa.setDomicilio(rs.getString("domicilio"));
			empresa.setTelefonoPrincipal(rs.getString("telefonoPrincipal"));
			return empresa;
		}
		
	}
	
	public List<Empresa> getEmpresas(){
		return this.jdbcTemplate.query("SELECT cif, nombre, domicilio, telefonoPrincipal FROM Empresa;", new EmpresaMapper());
	}
	
	public Empresa getEmpresa(String cif){
		return this.jdbcTemplate.queryForObject("SELECT cif, nombre, domicilio, telefonoPrincipal"
												+ " FROM Empresa WHERE cif = ?;", new Object[]{cif}, new EmpresaMapper());
	}
	
	public void addEmpresa(Empresa e){
		this.jdbcTemplate.update("INSERT INTO Empresa(cif, nombre, domicilio, telefonoPrincipal) values (?,?,?,?);",
							e.getCif(), e.getNombre(), e.getDomicilio(), e.getTelefonoPrincipal());
	}
	
	public void updateEmpresa(Empresa e){
		this.jdbcTemplate.update("UPDATE Empresa SET nombre=?, domicilio=?, telefonoPrincipal=? WHERE cif=?;",
							e.getNombre(), e.getDomicilio(), e.getTelefonoPrincipal(), e.getCif());
	}
	
	public void deleteEmpresa(Empresa e){
		this.jdbcTemplate.update("DELETE FROM Empresa WHERE cif=?;", e.getCif());
	}
}
