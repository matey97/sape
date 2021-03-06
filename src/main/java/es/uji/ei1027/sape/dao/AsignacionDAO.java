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

/**
 * DAO de las asignaciones de proyectos
 * Operaciones: listado, obtención, inserción, actualización y borrado
 * @author Nacho
 *
 */
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
			asignacion.setNombreEstudiante(rs.getString("nombreA"));
			asignacion.setIdTutor(rs.getInt("idTutor"));
			asignacion.setNombreTutor(rs.getString("nombreT"));
			asignacion.setNumeroProyecto(rs.getInt("numeroProyecto"));
			asignacion.setTitulo(rs.getString("titulo"));
			return asignacion;
		}
	}
	
	public List<Asignacion> getAsignacions(){
		return this.jdbcTemplate.query("SELECT a.id, fechaPropuesta, fechaAceptacion, fechaRechazo, fechaTraspasoIGLU, comentarioPetCambio, estadoAceptadaRechazada, a.dni, e.nombre AS nombreA, idTutor, t.nombre AS nombreT, numeroProyecto, op.titulo FROM Asignacion AS a JOIN Estudiante AS e ON(a.dni=e.dni) JOIN ProfesorTutor AS t ON(a.idTutor=t.id) JOIN OfertaProyecto AS op ON(a.numeroProyecto=op.numero);", new AsignacionMapper());
	}
	
	public Asignacion getAsignacion(int id){
		return this.jdbcTemplate.queryForObject("SELECT a.id, fechaPropuesta, fechaAceptacion, fechaRechazo, fechaTraspasoIGLU, comentarioPetCambio, estadoAceptadaRechazada, a.dni, e.nombre AS nombreA, idTutor, t.nombre AS nombreT, numeroProyecto, op.titulo FROM Asignacion AS a JOIN Estudiante AS e ON(a.dni=e.dni) JOIN ProfesorTutor AS t ON(a.idTutor=t.id) JOIN OfertaProyecto AS op ON(a.numeroProyecto=op.numero)"
												+ " WHERE a.id = ?;", new Object[]{id}, new AsignacionMapper());
	}
	
	public List<Asignacion> getAsignacionByDni(String dni){
		return this.jdbcTemplate.query("SELECT a.id, fechaPropuesta, fechaAceptacion, fechaRechazo, fechaTraspasoIGLU, comentarioPetCambio, estadoAceptadaRechazada, a.dni, e.nombre AS nombreA, idTutor, t.nombre AS nombreT, numeroProyecto, op.titulo FROM Asignacion AS a JOIN Estudiante AS e ON(a.dni=e.dni) JOIN ProfesorTutor AS t ON(a.idTutor=t.id) JOIN OfertaProyecto AS op ON(a.numeroProyecto=op.numero) WHERE a.dni=?;", new Object[] {dni},new AsignacionMapper());
	}
	
	/**
	 * Inserta una asignación en la BBDD, y como la clave primaria de dicha tabla es un SERIAL, se consulta posteriormente el id asignado
	 * @param a -> Asignación a insertar en la BBDD
	 */
	public void addAsignacion(Asignacion a){
		this.jdbcTemplate.update("INSERT INTO Asignacion(fechaPropuesta, fechaAceptacion, fechaRechazo, fechaTraspasoIGLU, comentarioPetCambio, estadoAceptadaRechazada, dni, idTutor, numeroProyecto) values (now(),?,?,?,?,?,?,?,?);",
							 a.getFechaAceptacion(), a.getFechaRechazo(), a.getFechaTraspasoIGLU(), a.getComentarioPetCambio(), a.getEstadoAceptadaRechazada(), a.getDni(), a.getIdTutor(), a.getNumeroProyecto());
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
	
	public void aceptaAsignacion(int id) {
		this.jdbcTemplate.update("UPDATE Asignacion SET estadoaceptadarechazada = 'aceptada', fechaaceptacion = now() WHERE id = ?;", id);
	}
	
	public void rechazaAsignacion(int id) {
		this.jdbcTemplate.update("UPDATE Asignacion SET estadoaceptadarechazada = 'rechazada', fecharechazo = now() WHERE id = ?;", id);
	}
}