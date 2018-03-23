package es.uji.ei1027.sape.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.sape.model.Estudiante;

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
			return estudiante;			
		}
		
	}
	
	public Estudiante getEstudiante(String dni){
		return this.jdbcTemplate.queryForObject("SELECT dni, nombre, numeroCreditosAprob, notaMedia, itinerario, numAsignaturasPendientes4t, semestreInicioEstancia "
												+ "FROM Estudiante WHERE dni=?;", new Object[]{dni}, new EstudianteMapper());
	}
	
	public void addEstudiante(Estudiante e){
		this.jdbcTemplate.update("INSERT INTO Estudiante(dni, nombre, numeroCreditosAprob, notaMedia, itinerario, numAsignaturasPendientes4t, semestreInicioEstancia) "
										+ "values(?,?,?,?,?,?,?);", e.getDni(), e.getNombre(), e.getNumeroCreditosAprob(), e.getNotaMedia(), e.getItinerario(), e.getNumAsignaturasPendientes4t(), e.getSemestreInicioEstancia());
	}
	
	public void updateEstudiante(Estudiante e){
		this.jdbcTemplate.update("UPDATE Estudiante SET nombre=?, numeroCreditosAprob=?, notaMedia=?, itinerario=?, numAsignaturasPendientes4t=?, semestreInicioEstancia=? WHERE dni=?;",
									e.getNombre(), e.getNumeroCreditosAprob(), e.getNotaMedia(), e.getItinerario(), e.getNumAsignaturasPendientes4t(), e.getSemestreInicioEstancia(), e.getDni());
	}
	
	public void deleteEstudiante(Estudiante e){
		this.jdbcTemplate.update("DELETE FROM Estudiante WHERE dni=?;", e.getDni());
	}
}
