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
			ofertaproyecto.setTarea(rs.getString("tarea"));
			ofertaproyecto.setObjetivo(rs.getString("objetivo"));
			ofertaproyecto.setEstado(rs.getInt("estado"));
			ofertaproyecto.setFechaAlta(rs.getDate("fechaAlta"));
			ofertaproyecto.setFechaUltimoCambio(rs.getDate("fechaUltimoCambio"));
			ofertaproyecto.setItinerario(rs.getString("itinerario"));
			ofertaproyecto.setIdEstancia(rs.getInt("idEstancia"));
			return ofertaproyecto;
		}
	}
	
	public List<OfertaProyecto> getOfertaProyectos(){
		return this.jdbcTemplate.query("SELECT numero, tarea, objetivo, estado, fechaAlta, fechaUltimoCambio, itinerario, idEstancia FROM OfertaProyecto;", new OfertaProyectoMapper());
	}
	
	public OfertaProyecto getOfertaProyecto(int numero){
		return this.jdbcTemplate.queryForObject("SELECT numero, tarea, objetivo, estado, fechaAlta, fechaUltimoCambio, itinerario, idEstancia FROM OfertaProyecto"
												+ " WHERE numero = ?;", new Object[]{numero}, new OfertaProyectoMapper());
	}
	
	public void addOfertaProyecto(OfertaProyecto o){
		this.jdbcTemplate.update("INSERT INTO OfertaProyecto(tarea, objetivo, estado, fechaAlta, fechaUltimoCambio, itinerario, idEstancia) values (?,?,?,?,?,?,?);",
							 o.getTarea(), o.getObjetivo(), o.getEstado(), o.getFechaAlta(), o.getFechaUltimoCambio(), o.getItinerario(), o.getIdEstancia());
		int numero = this.jdbcTemplate.queryForObject("SELECT currval(pg_get_serial_sequence('OfertaProyecto', 'numero'))", Integer.class);
		o.setNumero(numero);;
	}
	
	public void updateOfertaProyecto(OfertaProyecto o){
		this.jdbcTemplate.update("UPDATE OfertaProyecto SET tarea=?, objetivo=?, estado=?, fechaAlta=?, fechaUltimoCambio=?, itinerario=?, idEstancia=? WHERE numero=?;",
				o.getTarea(), o.getObjetivo(), o.getEstado(), o.getFechaAlta(), o.getFechaUltimoCambio(), o.getItinerario(), o.getIdEstancia(), o.getNumero());
	}
	
	public void deleteOfertaProyecto(OfertaProyecto o){
		this.jdbcTemplate.update("DELETE FROM OfertaProyecto WHERE numero=?;", o.getNumero());
	}
}