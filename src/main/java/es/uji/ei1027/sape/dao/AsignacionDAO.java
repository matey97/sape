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

import es.uji.ei1027.sape.model.Asignacion;

@Repository
public class AsignacionDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
	        this.jdbcTemplate = new JdbcTemplate(dataSource); 
	}
	
	private static final class AsignacionMapper implements RowMapper<Asignacion>{

		@Override
		public Asignacion mapRow(ResultSet rs, int arg1) throws SQLException {
			Asignacion asignacion = new Asignacion();
			asignacion.setId(rs.getInt("id"));
			asignacion.setFechaPropuesta(rs.getDate("fechaPropuesta"));
			asignacion.setFechaAceptacion(rs.getDate("fechaAceptacion"));
			asignacion.setFechaRechazo(rs.getDate("fechaRechazo"));
			asignacion.setFechaTraspasoIGLU(rs.getDate("fechaTraspasoIGLU"));
			asignacion.setComentarioPetCambio(rs.getString("comentarioPetCambio"));
			asignacion.setEstadoAceptadaRechazada(rs.getString("estadoAceptadaRechazada"));
			asignacion.setDni(rs.getString("dni"));
			asignacion.setIdTutor(rs.getInt("idTutor"));
			asignacion.setNumeroProyecto(rs.getInt("numeroProyecto"));
			return asignacion;
		}
	}
	
	public List<Asignacion> getAsignacions(){
		return this.jdbcTemplate.query("SELECT id, fechaPropuesta, fechaAceptacion, fechaRechazo, fechaTraspasoIGLU, comentarioPetCambio, estadoAceptadaRechazada, dni, idTutor, numeroProyecto FROM Asignacion;", new AsignacionMapper());
	}
	
	public Asignacion getAsignacion(int id){
		return this.jdbcTemplate.queryForObject("SELECT id, fechaPropuesta, fechaAceptacion, fechaRechazo, fechaTraspasoIGLU, comentarioPetCambio, estadoAceptadaRechazada, dni, idTutor, numeroProyecto FROM Asignacion"
												+ " WHERE id = ?;", new Object[]{id}, new AsignacionMapper());
	}
	
	public void addAsignacion(Asignacion a){
		this.jdbcTemplate.update("INSERT INTO Asignacion(fechaPropuesta, fechaAceptacion, fechaRechazo, fechaTraspasoIGLU, comentarioPetCambio, estadoAceptadaRechazada, dni, idTutor, numeroProyecto) values (?,?,?,?,?,?,?,?,?);",
							 a.getFechaPropuesta(), a.getFechaAceptacion(), a.getFechaRechazo(), a.getFechaTraspasoIGLU(), a.getComentarioPetCambio(), a.getEstadoAceptadaRechazada(), a.getDni(), a.getIdTutor(), a.getNumeroProyecto());
		int id = this.jdbcTemplate.queryForObject("SELECT currval(pg_get_serial_sequence('Asignacion', 'id'))", Integer.class);
		a.setId(id);
	}
	
	public void updateAsignacion(Asignacion a){
		this.jdbcTemplate.update("UPDATE Asignacion SET fechaPropuesta=?, fechaAceptacion=?, fechaRechazo=?, fechaTraspasoIGLU=?, comentarioPetCambio=?, estadoAceptadaRechazada=?, dni=?, idTutor=?, numeroProyecto=? WHERE id=?;",
				a.getFechaPropuesta(), a.getFechaAceptacion(), a.getFechaRechazo(), a.getFechaTraspasoIGLU(), a.getComentarioPetCambio(), a.getEstadoAceptadaRechazada(), a.getDni(), a.getIdTutor(), a.getNumeroProyecto(), a.getId());
	}
	
	public void deleteAsignacion(Asignacion a){
		this.jdbcTemplate.update("DELETE FROM Asignacion WHERE id=?;", a.getId());
	}
}