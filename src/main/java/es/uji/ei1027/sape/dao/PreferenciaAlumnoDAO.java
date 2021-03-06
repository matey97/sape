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
			preferenciaalumno.setTituloProyecto(rs.getString("titulo"));
			return preferenciaalumno;
		}
	}
	
	public List<PreferenciaAlumno> getPreferenciaAlumnos(){
		return this.jdbcTemplate.query("SELECT id, orden, pa.fechaUltimoCambio, pa.estado, dni, pa.numeroProyecto, op.titulo FROM PreferenciaAlumno as pa JOIN OfertaProyecto as op ON(pa.numeroProyecto = op.numero);", new PreferenciaAlumnoMapper());
	}
	
	public PreferenciaAlumno getPreferenciaAlumno(int id){
		return this.jdbcTemplate.queryForObject("SELECT id, orden, pa.fechaUltimoCambio, pa.estado, dni, pa.numeroProyecto, op.titulo FROM PreferenciaAlumno as pa JOIN OfertaProyecto as op ON(pa.numeroProyecto = op.numero)"
												+ " WHERE id = ?;", new Object[]{id}, new PreferenciaAlumnoMapper());
	}
	
	public List<PreferenciaAlumno> getPreferenciasAlumno(String dni){
		return this.jdbcTemplate.query("SELECT id, orden, pa.fechaUltimoCambio, pa.estado, dni, pa.numeroProyecto, op.titulo FROM OfertaProyecto as op JOIN PreferenciaAlumno as pa ON(pa.numeroProyecto = op.numero)"
				+ " WHERE dni = ?;", new Object[]{dni}, new PreferenciaAlumnoMapper());
	}
	
	public List<PreferenciaAlumno> getPreferenciasFinalesAlumno(String dni){
		return this.jdbcTemplate.query("SELECT id, orden, pa.fechaUltimoCambio, pa.estado, dni, pa.numeroProyecto, op.titulo FROM OfertaProyecto as op JOIN PreferenciaAlumno as pa ON(pa.numeroProyecto = op.numero)"
				+ " WHERE dni = ? AND (orden BETWEEN 1 AND 5) AND op.estado = 6 ORDER BY orden ASC;", new Object[]{dni}, new PreferenciaAlumnoMapper());
	}
	
	public void addPreferenciaAlumno(PreferenciaAlumno p){
		//String titulo = this.jdbcTemplate.queryForObject("SELECT titulo FROM OfertaProyecto WHERE numero = ?;", String.class, p.getNumeroProyecto());
		this.jdbcTemplate.update("INSERT INTO PreferenciaAlumno(orden, fechaUltimoCambio, estado, dni, numeroProyecto) values (?,?,'abierta',?,?);",
							p.getOrden(), p.getFechaUltimoCambio(), p.getDni(), p.getNumeroProyecto());
		int id = this.jdbcTemplate.queryForObject("SELECT currval(pg_get_serial_sequence('PreferenciaAlumno', 'id'))", Integer.class);
		p.setId(id);;
	}
	
	public void updatePreferenciaAlumno(PreferenciaAlumno p){
		int numero = this.jdbcTemplate.queryForObject("SELECT numero FROM OfertaProyecto WHERE titulo = ?;", Integer.class, p.getTituloProyecto());
		this.jdbcTemplate.update("UPDATE PreferenciaAlumno SET orden=?, fechaUltimoCambio=now() WHERE id=?;",
				p.getOrden(), p.getId());
	}
	
	public void deletePreferenciaAlumno(PreferenciaAlumno p){
		this.jdbcTemplate.update("DELETE FROM PreferenciaAlumno WHERE id=?;", p.getId());
	}
}