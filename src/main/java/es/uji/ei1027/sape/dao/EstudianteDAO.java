package es.uji.ei1027.sape.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.sape.model.Estudiante;

/**
 * DAO para los estudiantes
 * Operaciones: listado, obtención, inserción, actualización y borrado
 * @author Miguel
 *
 */
@Repository
public class EstudianteDAO {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
	        this.jdbcTemplate = new JdbcTemplate(dataSource); 
	}
	
	private static final class EstudianteMapper implements RowMapper<Estudiante>{

		@Override
		public Estudiante mapRow(ResultSet rs, int arg1) throws SQLException {
			Estudiante estudiante = new Estudiante();
			estudiante.setDni(rs.getString("dni"));
			estudiante.setNombre(rs.getString("nombre"));
			estudiante.setNumeroCreditosAprob(rs.getInt("numeroCreditosAprob"));
			estudiante.setNotaMedia(rs.getDouble("notaMedia"));
			estudiante.setItinerario(rs.getString("itinerario"));
			estudiante.setNumAsignaturasPendientes4t(rs.getInt("numAsignaturasPendientes4t"));
			estudiante.setSemestreInicioEstancia(rs.getString("semestreInicioEstancia"));
			estudiante.setBloqueado(rs.getBoolean("bloqueado"));
			return estudiante;			
		}
		
	}
	
	public List<Estudiante> getEstudiantes(){
		return this.jdbcTemplate.query("SELECT * FROM Estudiante;", new EstudianteMapper());
	}
	
	public Estudiante getEstudiante(String dni){
		return this.jdbcTemplate.queryForObject("SELECT dni, nombre, numeroCreditosAprob, notaMedia, itinerario, numAsignaturasPendientes4t, semestreInicioEstancia, bloqueado "
												+ "FROM Estudiante WHERE dni=?;", new Object[]{dni}, new EstudianteMapper());
	}
	
	public void addEstudiante(Estudiante e){
		this.jdbcTemplate.update("INSERT INTO Estudiante(dni, nombre, numeroCreditosAprob, notaMedia, itinerario, numAsignaturasPendientes4t, semestreInicioEstancia, bloqueado) "
										+ "values(?,?,?,?,?,?,?,?);", e.getDni(), e.getNombre(), e.getNumeroCreditosAprob(), e.getNotaMedia(), e.getItinerario(), e.getNumAsignaturasPendientes4t(), e.getSemestreInicioEstancia(),e.isBloqueado());
	}
	
	public void updateEstudiante(Estudiante e){
		this.jdbcTemplate.update("UPDATE Estudiante SET nombre=?, numeroCreditosAprob=?, notaMedia=?, itinerario=?, numAsignaturasPendientes4t=?, semestreInicioEstancia=? bloquado=? WHERE dni=?;",
									e.getNombre(), e.getNumeroCreditosAprob(), e.getNotaMedia(), e.getItinerario(), e.getNumAsignaturasPendientes4t(), e.getSemestreInicioEstancia(), e.isBloqueado(), e.getDni());
	}
	
	public void deleteEstudiante(Estudiante e){
		this.jdbcTemplate.update("DELETE FROM Estudiante WHERE dni=?;", e.getDni());
	}
}
