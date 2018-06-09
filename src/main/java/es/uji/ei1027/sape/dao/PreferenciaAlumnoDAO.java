package es.uji.ei1027.sape.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.sape.model.PreferenciaAlumno;

/**
 * DAO para las preferencias de los alumnos
 * Operaciones: listado, obtención, inserción, actualización y borrado
 * @author Nacho
 *
 */
@Repository
public class PreferenciaAlumnoDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
	        this.jdbcTemplate = new JdbcTemplate(dataSource); 
	}
	
	private static final class PreferenciaAlumnoMapper implements RowMapper<PreferenciaAlumno>{

		@Override
		public PreferenciaAlumno mapRow(ResultSet rs, int arg1) throws SQLException {
			PreferenciaAlumno preferenciaalumno = new PreferenciaAlumno();
			preferenciaalumno.setId(rs.getInt("id"));
			preferenciaalumno.setOrden(rs.getInt("orden"));
			preferenciaalumno.setFechaUltimoCambio(rs.getDate("fechaUltimoCambio"));
			preferenciaalumno.setEstado(rs.getString("estado"));
			preferenciaalumno.setDni(rs.getString("dni"));
			preferenciaalumno.setNumeroProyecto(rs.getInt("numeroProyecto"));
			return preferenciaalumno;
		}
	}
	
	public List<PreferenciaAlumno> getPreferenciaAlumnos(){
		return this.jdbcTemplate.query("SELECT id, orden, fechaUltimoCambio, estado, dni, numeroProyecto FROM PreferenciaAlumno;", new PreferenciaAlumnoMapper());
	}
	
	public PreferenciaAlumno getPreferenciaAlumno(int orden){
		return this.jdbcTemplate.queryForObject("SELECT id, orden, fechaUltimoCambio, estado, dni, numeroProyecto FROM PreferenciaAlumno"
												+ " WHERE orden = ?;", new Object[]{orden}, new PreferenciaAlumnoMapper());
	}
	
	public void addPreferenciaAlumno(PreferenciaAlumno p){
		this.jdbcTemplate.update("INSERT INTO PreferenciaAlumno(orden, fechaUltimoCambio, estado, dni, numeroProyecto) values (?,?,?,?,?);",
							p.getOrden(), p.getFechaUltimoCambio(), p.getEstado(), p.getDni(), p.getNumeroProyecto());
		int id = this.jdbcTemplate.queryForObject("SELECT currval(pg_get_serial_sequence('PreferenciaAlumno', 'id'))", Integer.class);
		p.setId(id);;
	}
	
	public void updatePreferenciaAlumno(PreferenciaAlumno p){
		this.jdbcTemplate.update("UPDATE PreferenciaAlumno SET orden=?, fechaUltimoCambio=?, estado=?, dni=?, numeroProyecto=? WHERE id=?;",
				p.getOrden(), p.getFechaUltimoCambio(), p.getEstado(), p.getDni(), p.getNumeroProyecto(), p.getId());
	}
	
	public void deletePreferenciaAlumno(PreferenciaAlumno p){
		this.jdbcTemplate.update("DELETE FROM PreferenciaAlumno WHERE id=?;", p.getId());
	}
}