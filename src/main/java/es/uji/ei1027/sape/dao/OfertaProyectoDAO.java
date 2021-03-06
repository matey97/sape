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

import es.uji.ei1027.sape.model.OfertaProyecto;

/**
 * DAO de las ofertas de proyectos
 * Operaciones: listado, obtención, inserción, actualización y borrado
 * @author Nacho
 *
 */
@Repository
public class OfertaProyectoDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
	        this.jdbcTemplate = new JdbcTemplate(dataSource); 
	}
	
	private static final class OfertaProyectoMapper implements RowMapper<OfertaProyecto>{

		@Override
		public OfertaProyecto mapRow(ResultSet rs, int arg1) throws SQLException {
			OfertaProyecto ofertaproyecto = new OfertaProyecto();
			ofertaproyecto.setNumero(rs.getInt("numero"));
			ofertaproyecto.setTitulo(rs.getString("titulo"));
			ofertaproyecto.setObjetivo(rs.getString("objetivo"));
			ofertaproyecto.setEstado(rs.getString("estado"));
			ofertaproyecto.setFechaAlta(rs.getDate("fechaAlta"));
			ofertaproyecto.setFechaUltimoCambio(rs.getDate("fechaUltimoCambio"));
			ofertaproyecto.setItinerario(rs.getString("itinerario"));
			ofertaproyecto.setIdEstancia(rs.getInt("idEstancia"));
			ofertaproyecto.setTarea(rs.getString("tarea"));
			ofertaproyecto.setEmpresa(rs.getString("nombre"));
			ofertaproyecto.setCiudad(rs.getString("población"));
			return ofertaproyecto;
		}
	}
	
	
	public List<OfertaProyecto> getOfertaProyectos(){
		return this.jdbcTemplate.query("SELECT numero, titulo, tarea, objetivo, eo.estado, fechaAlta, fechaUltimoCambio, itinerario, idEstancia, nombre, población " +
				"FROM Empresa AS e JOIN Estancia AS es ON(e.cif = es.cifEmpresa) JOIN OfertaProyecto AS o ON(es.id = o.idEstancia) JOIN EstadoOferta AS eo ON(o.estado = eo.id)", new OfertaProyectoMapper());
	}
	
	public OfertaProyecto getOfertaProyecto(int numero){
		return this.jdbcTemplate.queryForObject("SELECT numero, titulo, tarea, objetivo, eo.estado, fechaAlta, fechaUltimoCambio, itinerario, idEstancia, nombre, población " +
						"FROM Empresa AS e JOIN Estancia AS es ON(e.cif = es.cifEmpresa) JOIN OfertaProyecto AS o ON(es.id = o.idEstancia) JOIN EstadoOferta AS eo ON(o.estado = eo.id)"
												+ " WHERE numero = ?;", new Object[]{numero}, new OfertaProyectoMapper());
	}
	
	public List<OfertaProyecto> getOfertasEmpresa(String cif) {
		return this.jdbcTemplate.query("SELECT DISTINCT numero,titulo, tarea, objetivo, eo.estado, fechaAlta, fechaUltimoCambio, itinerario, idEstancia, e.nombre, e.población "+
									"FROM EstadoOferta As eo JOIN OfertaProyecto AS o ON(eo.id = o.estado) JOIN Estancia AS es ON (o.idEstancia = es.id) JOIN Empresa AS e ON (e.cif = es.cifEmpresa) WHERE e.cif = ?;", new Object[]{cif}, new OfertaProyectoMapper());
	}
	
	public List<OfertaProyecto> getOfertasItinerario(String itinerario){
		return this.jdbcTemplate.query("SELECT DISTINCT numero,titulo, tarea, objetivo, eo.estado, fechaAlta, fechaUltimoCambio, itinerario, idEstancia, e.nombre, e.población "+
				"FROM EstadoOferta As eo JOIN OfertaProyecto AS o ON(eo.id = o.estado) JOIN Estancia AS es ON (o.idEstancia = es.id) JOIN Empresa AS e ON (e.cif = es.cifEmpresa) WHERE lower(itinerario) = ? AND eo.estado = 'Visible a alumnos';", new Object[]{itinerario}, new OfertaProyectoMapper());
	}
	
	public void addOfertaProyecto(OfertaProyecto o){
		this.jdbcTemplate.update("INSERT INTO OfertaProyecto(titulo, tarea, objetivo, estado, fechaAlta, fechaUltimoCambio, itinerario, idEstancia) values (?,?,?,?,now(),null,?,?);",
							 o.getTitulo(),o.getTarea(), o.getObjetivo(), Integer.valueOf(o.getEstado()), o.getItinerario(), o.getIdEstancia());
		int numero = this.jdbcTemplate.queryForObject("SELECT currval(pg_get_serial_sequence('OfertaProyecto', 'numero'))", Integer.class);
		o.setNumero(numero);
	}
	
	public void updateOfertaProyecto(OfertaProyecto o){
		int estado = this.jdbcTemplate.queryForObject("SELECT id FROM EstadoOferta WHERE estado = ?;", Integer.class, o.getEstado());
		this.jdbcTemplate.update("UPDATE OfertaProyecto SET titulo=?, tarea=?, objetivo=?, estado=?,fechaUltimoCambio=now(), itinerario=?, idEstancia=? WHERE numero=?;",
				o.getTitulo() ,o.getTarea(), o.getObjetivo(), estado, o.getItinerario(), o.getIdEstancia(), o.getNumero());
	}
	
	public void deleteOfertaProyecto(OfertaProyecto o){
		this.jdbcTemplate.update("DELETE FROM OfertaProyecto WHERE numero=?;", o.getNumero());
	}
	
	public void visibilizaOfertas() {
		this.jdbcTemplate.update("UPDATE OfertaProyecto SET estado = 6 WHERE estado = 4;");
	}
	
	public void ofertaAsignada(int id) {
		this.jdbcTemplate.update("UPDATE OfertaProyecto SET estado = 7 WHERE numero = ?", id);
	}
	
	public void ofertaDesasginada(int id) {
		this.jdbcTemplate.update("UPDATE OfertaProyecto SET estado = 6 WHERE numero = ?", id);
	}
}