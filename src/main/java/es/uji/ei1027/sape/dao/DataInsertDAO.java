package es.uji.ei1027.sape.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.sape.model.Empresa;
import es.uji.ei1027.sape.model.EstadoOferta;
import es.uji.ei1027.sape.model.Estancia;
import es.uji.ei1027.sape.model.OfertaProyecto;

@Repository
public class DataInsertDAO {

	private static final boolean INSERT_DATA = false;
	private String[][] empresas= new String[][] {{"Cantón, Amaya and Llopis","G90423351","C/ Cantón Nº4","Castelló","792804601"},
										{"Pinto Inc","A24092579","C/ Vilavella Nº24","Castelló","728420369"},
										{"Rius-Giménez","H28869931","C/ Hernan Cortes Nº14","Burriana","664584177"},
										{"Haro, Llobet and Sabater","J8637345C","Plaza de España","Villareal","791650761"}};
										
	private String[][] estancias = new String[][] {{"G90423351", "Juan José de Calatayud", "jjose@gmail.com", "Innovar aplicacions d'e-business"},
													{"A24092579", "Margarita Lamas Aroca", "mlamas@gmail.com", "Condueix usuaris personalitzats"},
													{"A24092579", "Margarita Lamas Aroca", "mlamas@gmail.com", "Condueix usuaris personalitzats"},
													{"H28869931", "Patricia Llamas Ruano", "pllamas@gmail.com", "Sintetitzar arquitectures proactives"},
													{"J8637345C", "Luis Miguel Macias Guitart", "lmiguel@gmail.com", "Capacitar vortals de classe mundial"},
													{"J8637345C", "Luis Miguel Macias Guitart", "lmiguel@gmail.com", "Capacitar vortals de classe mundial"},
													{"J8637345C", "Luis Miguel Macias Guitart", "lmiguel@gmail.com", "Capacitar vortals de classe mundial"}};
	
	private String[][] ofertas = new String[][] {{"Organització de la tercera generació","Objetivo...","2","Tecnologías de la Comunicación","1"},
												{"Iniciativa direccional implementada","Objetivo...","2","Ingeniería de Software","2"},
												{"Funció dinàmica orientada a l'equip","Objetivo...","2","Ingeniería de computadores","3"},
												{"Existència contextualitzada d'intel·ligència artificial","Objetivo...","2","Sistemas de Información","4"},
												{"Orquestració asíncrona preventiva","Objetivo...","2","Tecnologías de la Comunicación","5"},
												{"Aplicació sistèmica autònoma","Objetivo...","2","Ingeniería de computadores","6"},
												{"Força de treball coherent distribuïda","Objetivo...","2","Ingeniería de Software","7"}};
												
	private String[] estados = new String[] {"Sin Definir",
												"Introducida",
												"Pendiente revisión",
												"Aceptada",
												"Rechazada",
												"Visible a alumnos",
												"Asignada"};
	
	@Autowired
	private EmpresaDAO empresaDAO;
	@Autowired
	private EstanciaDAO estanciaDAO;
	@Autowired
	private OfertaProyectoDAO ofertaDAO;
	@Autowired
	private EstadoOfertaDAO estadoOfertaDAO;
	
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
	        this.jdbcTemplate = new JdbcTemplate(dataSource); 
	}

	public void inserts() {
		if (INSERT_DATA)
			doInserts();
	}
	
	private void doInserts() {
//		for (String[] empresa:empresas) {
//			Empresa e = new Empresa();
//			e.setCif(empresa[1]);
//			e.setNombre(empresa[0]);
//			e.setDomicilio(empresa[2]);
//			e.setPoblacion(empresa[3]);
//			e.setTelefonoPrincipal(empresa[4]);
//			empresaDAO.addEmpresa(e);
//		}
//		System.out.println("Empresas añadidas.");
//		
//		for (String[] estancia:estancias) {
//			Estancia e = new Estancia();
//			e.setCifEmpresa(estancia[0]);
//			e.setContactPerson(estancia[1]);
//			e.setMailContactPerson(estancia[2]);
//			e.setInternshipDescription(estancia[3]);
//			estanciaDAO.addEstancia(e);
//		}
//		System.out.println("Estancias añadidas.");
		
		for (String estado:estados) {
			EstadoOferta e = new EstadoOferta();
			e.setEstado(estado);
			estadoOfertaDAO.addEstadoOferta(e);
		}
		System.out.println("Estados añadidas.");
		
		for (String[] oferta:ofertas) {
			OfertaProyecto o = new OfertaProyecto();
			o.setTarea(oferta[0]);
			o.setObjetivo(oferta[1]);
			o.setEstado(oferta[2]);
			o.setItinerario(oferta[3]);
			o.setIdEstancia(Integer.valueOf(oferta[4]));
			ofertaDAO.addOfertaProyecto(o);
		}
		System.out.println("Ofertas añadidas.");
	
	}
}
