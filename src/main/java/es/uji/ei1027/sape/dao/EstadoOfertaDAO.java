package es.uji.ei1027.sape.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.sape.model.EstadoOferta;

@Repository
public class EstadoOfertaDAO {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
	        this.jdbcTemplate = new JdbcTemplate(dataSource); 
	}
	
	private static final class EstadoOfertaMapper implements RowMapper<EstadoOferta>{

		@Override
		public EstadoOferta mapRow(ResultSet rs, int arg1) throws SQLException {
			EstadoOferta estado = new EstadoOferta();
			estado.setId(rs.getInt("id"));
			estado.setEstado(rs.getString("estado"));
			return estado;
		}
		
	}
	
	public EstadoOferta getEstadoOferta(int id){
		return this.jdbcTemplate.queryForObject("SELECT id, estado FROM EstadoOferta WHERE id=?;", new Object[]{id}, new EstadoOfertaMapper());
	}
	
	public void addEstadoOferta(EstadoOferta e){
		this.jdbcTemplate.update("INSERT INTO EstadoOferta(estado) values(?);", e.getEstado());
	}
	
	public void updateEstadoOferta(EstadoOferta e){
		this.jdbcTemplate.update("UPDATE EstadoOferta SET estado=? WHERE id=?;", e.getEstado(), e.getId());
	}
	
	public void deleteEstadoOferta(EstadoOferta e){
		this.jdbcTemplate.update("DELETE FROM EstadoOferta WHERE id=?;", e.getId());
	}
}
