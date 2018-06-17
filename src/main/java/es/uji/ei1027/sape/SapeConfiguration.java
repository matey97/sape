package es.uji.ei1027.sape;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.uji.ei1027.sape.services.OfertaProyectoService;
import es.uji.ei1027.sape.services.PreferenciaAlumnoService;
import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
public class SapeConfiguration {

	@Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }
	
	@Bean
    public LayoutDialect layoutDialect(){
    	return new LayoutDialect();
    }
	
	@Bean
	public OfertaProyectoService ofertaProyectoService(){
		return new OfertaProyectoService();
	}
	
	@Bean
	public PreferenciaAlumnoService preferenciaAlumnoService() {
		return new PreferenciaAlumnoService();
	}
}
