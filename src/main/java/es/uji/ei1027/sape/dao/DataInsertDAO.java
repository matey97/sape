package es.uji.ei1027.sape.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.sape.model.Empresa;
import es.uji.ei1027.sape.model.EstadoOferta;
import es.uji.ei1027.sape.model.Estancia;
import es.uji.ei1027.sape.model.Estudiante;
import es.uji.ei1027.sape.model.OfertaProyecto;
import es.uji.ei1027.sape.model.ProfesorTutor;
import es.uji.ei1027.sape.model.UserDetails;

@Repository
public class DataInsertDAO {

	private static final boolean INSERT_EMPRESAS = false;
	private static final boolean INSERT_ESTANCIAS = false;
	private static final boolean INSERT_OFERTAS = false;
	private static final boolean INSERT_ESTADOS = false;
	private static final boolean INSERT_ESTUDIANTES = false;
	private static final boolean INSERT_USUARIOS = false;
	private static final boolean INSERT_TUTORES = false;
	
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
	
	private String[][] ofertas = new String[][] {{"Organització de la tercera generació","Objetivo...","2","Tecnologías de la Información","1"},
												{"Iniciativa direccional implementada","Objetivo...","2","Ingeniería del Software","2"},
												{"Funció dinàmica orientada a l'equip","Objetivo...","2","Ingeniería de computadores","3"},
												{"Existència contextualitzada d'intel·ligència artificial","Objetivo...","2","Sistemas de Información","4"},
												{"Orquestració asíncrona preventiva","Objetivo...","2","Tecnologías de la Información","5"},
												{"Aplicació sistèmica autònoma","Objetivo...","2","Ingeniería de computadores","6"},
												{"Força de treball coherent distribuïda","Objetivo...","2","Ingeniería del Software","7"}};
												
	private String[] estados = new String[] {"Sin Definir",
												"Introducida",
												"Pendiente revisión",
												"Aceptada",
												"Rechazada",
												"Visible a alumnos",
												"Asignada"};
	
	private String[][] estudiantes = new String[][] {{"73466412L", "Nuria Castillo Figueroa", "200", "7.3", "Ingeniería del Software", "0", "2", "true"},
													{"12732420B", "Julio Reyes Sanchez", "210", "6.8", "Ingeniería de Computadores", "0", "1", "true"},
													{"59709693Z", "Carmen Burgos Vazquez", "216", "8.7", "Sistemas de Información", "0", "2", "true"},
													{"00000001A", "Jordi Hurtado Torres", "216", "8.5", "Ingeniería del Software", "0", "2", "true"},
													{"49692421R", "Ramiro Soler Domingo", "200", "6.5", "Tecnologías de la Información", "0", "1" , "true"},
													{"35717650F", "Silvia Arias Rodriguez", "216", "9.3", "Ingeniería de Computadores", "0", "2", "true"}};
													
	private String[][] usuarios = new String[][] {{"nuria", "nuria", "73466412L", "1"},
													{"julio", "julio", "12732420B", "1"},
													{"carmen", "carmen", "59709693Z", "1"},
													{"jordi", "jordi", "00000001A", "1"},
													{"ramiro", "ramiro", "49692421R", "1"},
													{"silvia", "silvia", "35717650F", "1"},
													{"btc", "btc", null, "2"},
													{"dcc", "dcc", null, "3"}};
													
	private String[][] tutores = new String[][] {{"Daniel David Campuzano", "Sistemas y Lenguajes Informáticos", "TI2001", "ddavid@uji.es"},
													{"Josep Torrecilla Pradas", "Ingeniería y Ciencia de los Computadores", "TI2002", "jtorrez@uji.es"},
													{"Nicolás Rodenas Mauri", "Sistemas y Lenguajes Informáticos", "TI2003", "nrodenas@uji.es"},
													{"Alberto Sendra Parejo", "Ingeniería y Ciencia de los Computadores", "TI2004", "asendra@uji.es"},
													{"Eduardo Lacalle Matilla", "Sistemas y Lenguajes Informáticos", "TI2005", "elacalle@uji.es"},
													{"Maria Elena Nieto Dios", "Ingeniería y Ciencia de los Computadores", "TI2006", "mnieto@uji.es"},
													{"Inés Palma Lacalle", "Sistemas y Lenguajes Informáticos", "TI2007", "ipalma@uji.es"},
													{"Sandra Bertran Sendra", "Sistemas y Lenguajes Informáticos", "TI2008", "sbertran@uji.es"},
													{"Carolina Poza Cerro", "Ingeniería y Ciencia de los Computadores", "TI2009", "cpoza@uji.es"},
													{"Nerea Medina MArtinez", "Ingeniería y Ciencia de los Computadores", "TI2010", "nmedina@uji.es"}};
	
	@Autowired
	private EmpresaDAO empresaDAO;
	@Autowired
	private EstanciaDAO estanciaDAO;
	@Autowired
	private OfertaProyectoDAO ofertaDAO;
	@Autowired
	private EstadoOfertaDAO estadoOfertaDAO;
	@Autowired
	private EstudianteDAO estudianteDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private ProfesorTutorDAO tutorDao;
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
	        this.jdbcTemplate = new JdbcTemplate(dataSource); 
	}

	public void inserts() {
		if (INSERT_EMPRESAS)
			insertEmpresas();
		if (INSERT_ESTANCIAS)
			insertEstancias();
		if (INSERT_ESTADOS)
			insertEstados();
		if (INSERT_OFERTAS)
			insertOfertas();
		if (INSERT_ESTUDIANTES) 
			insertEstudiantes();
		if (INSERT_USUARIOS)
			insertUsuarios();
		if (INSERT_TUTORES)
			insertTutores();
	}
	
	private void insertEmpresas() {
		for (String[] empresa:empresas) {
			Empresa e = new Empresa();
			e.setCif(empresa[1]);
			e.setNombre(empresa[0]);
			e.setDomicilio(empresa[2]);
			e.setPoblacion(empresa[3]);
			e.setTelefonoPrincipal(empresa[4]);
			empresaDAO.addEmpresa(e);
		}
		System.out.println("Empresas añadidas.");
	}
	
	private void insertEstancias() {
		for (String[] estancia:estancias) {
			Estancia e = new Estancia();
			e.setCifEmpresa(estancia[0]);
			e.setContactPerson(estancia[1]);
			e.setMailContactPerson(estancia[2]);
			e.setInternshipDescription(estancia[3]);
			estanciaDAO.addEstancia(e);
		}
		System.out.println("Estancias añadidas.");
	}
	
	private void insertEstados() {
		for (String estado:estados) {
			EstadoOferta e = new EstadoOferta();
			e.setEstado(estado);
			estadoOfertaDAO.addEstadoOferta(e);
		}
		System.out.println("Estados añadidas.");
	}
	
	private void insertOfertas() {
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
	
	private void insertEstudiantes() {
		for (String[] estudiante: estudiantes) {
			Estudiante e = new Estudiante();
			e.setDni(estudiante[0]);
			e.setNombre(estudiante[1]);
			e.setNumeroCreditosAprob(Integer.valueOf(estudiante[2]));
			e.setNotaMedia(Double.parseDouble(estudiante[3]));
			e.setItinerario(estudiante[4]);
			e.setNumAsignaturasPendientes4t(Integer.valueOf(estudiante[5]));
			e.setSemestreInicioEstancia(estudiante[6]);
			e.setBloqueado(Boolean.valueOf(estudiante[7]));
			estudianteDAO.addEstudiante(e);
		}
		System.out.println("Estudiantes añadidos.");
	}
	
	private void insertUsuarios() {
		for (String[] usuario: usuarios) {
			UserDetails u = new UserDetails();
			u.setUsername(usuario[0]);
			u.setPassword(usuario[1]);
			u.setDni(usuario[2]);
			u.setType(Integer.valueOf(usuario[3]));
			userDAO.addUser(u);
		}
		System.out.println("Usuarios añadidos.");
	}
	
	private void insertTutores() {
		for (String[] tutor : tutores) {
			ProfesorTutor t = new ProfesorTutor();
			t.setNombre(tutor[0]);
			t.setDepartamento(tutor[1]);
			t.setDespacho(tutor[2]);
			t.setEmail(tutor[3]);
			tutorDao.addProfesorTutor(t);
		}
		System.out.println("Tutores añadidos.");
	}

}
