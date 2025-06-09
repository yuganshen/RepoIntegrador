package com.example.Integrador;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class controller {
	private static final String FACADE_SESSION_KEY = "registroServicio";
	
	//navegacion por el menu del administrador
	@GetMapping("/registro_maquinaria.html")
	public String Inicio() {
		return "registro_maquinaria";
	}	
	@GetMapping("/Pago(1).html")
	public String Pago() {
		return "Pago(1)";
	}
	@GetMapping("/gestionar_tecnico.html")
	public String gestionarTecnico(Model model) {
		 model.addAttribute("dtoTecnico", new DtoTecnico());
	        model.addAttribute("tecnicos", listaTecnicos); // para mostrar la tabla actualizada
		return "gestionar_tecnico";
	}
	//navegacion por el menu del tecnico	
	@GetMapping("/index.html")
	public String index() {
	    return "index"; 
	}
	@GetMapping("/realizados.html")
	public String realizados() {
	    return "realizados"; 
	}
	@GetMapping("/correo.html")
	public String recuperarCorreo() {
	    return "correo"; 
	}
	@GetMapping("/nueva-contraseña")
	public String nuevaContraseña() {
	    return "recuperar-contra"; 
	}
	@PostMapping("/actualizar-contraseña")
	public String actualizarContraseña() {
		return "Login";
	}
	
	@GetMapping("/menu-trabjador.html")
	public String menuTrabajador() {
	    return "menu-trabjador"; 
	}
	//navegacion
	@GetMapping("/Inicio.html")
	public String ini() {
		return "Inicio";
	}
	@GetMapping("/Historial-pago.html")
	public String historialPago() {
		return "Historial-pago";
	}
	@GetMapping("/empezar-administrador")
	public String empezar() {
		return "registro_maquinaria";
	}
	@GetMapping("/Empezar-trabajador")
	public String empezarTrabajador() {
		return "index";
	}

	//navegacion
	@GetMapping("/")
	public String login() {
	    return "Login"; // 
	}
	@GetMapping("/Login.html")
	public String loginNav() {
	    return"Login";// 
	}
	//validar tipo de usuario
	private List<TipoUsuario> usuarios = new ArrayList<>();

	public controller() {
	    usuarios.add(UsuarioFactory.crearUsuario("admin", "admin", "admin"));
	    usuarios.add(UsuarioFactory.crearUsuario("tecnico", "tecnico1", "1234"));
	    usuarios.add(UsuarioFactory.crearUsuario("tecnico", "123", "123"));
	    usuarios.add(UsuarioFactory.crearUsuario("tecnico", "lucho", "lucho"));
	}

	//validar tipo de usuario
	    @PostMapping("/procesar-login")
	    public String procesarLogin(
	            @RequestParam(name = "usuario") String usuario,
	            @RequestParam(name = "contra") String contraseña
	    ) {
	        for (TipoUsuario u : usuarios) {
	            if (u instanceof UsuarioConCredenciales) {
	                UsuarioConCredenciales user = (UsuarioConCredenciales) u;
	                if (user.getUsuario().equals(usuario) && user.getContraseña().equals(contraseña)) {
	                    return user.redirigir();
	                }
	            }
	        }
	        
	        return "Login"; // Vista de error si no encuentra usuario
	    }
	//USA el facade
	/*
		@PostMapping("/registrar")
		public String registrarDatos(@ModelAttribute DTOMaquinaria maquinaria, HttpSession session) {
		    RegistroServicioFacade facade = new RegistroServicioFacade();
		    facade.registrarMaquinaria(maquinaria);

		    session.setAttribute(FACADE_SESSION_KEY, facade); // guardamos el facade en la sesión

		    return "SelectMantenimiento";
		}*/
		
	 @PostMapping("/registrar")
	public String registrarDatos(@ModelAttribute DTOMaquinaria maquinaria,  HttpSession session) {
	    try {
	    	 RegistroServicioFacade facade = new RegistroServicioFacade();
			    facade.registrarMaquinaria(maquinaria);

			    session.setAttribute(FACADE_SESSION_KEY, facade); // guardamos el facade en la sesión
	        // Registrar en el archivo de log con todos los campos
	        LoggerHelper.logInfo(
	            "Registro de maquinaria:\n" +
	            "Nombre: " + maquinaria.getNombre() + "\n" +
	            "Apellido: " + maquinaria.getApellido() + "\n" +
	            "Celular: " + maquinaria.getNumeroCelular() + "\n" +
	            "Correo: " + maquinaria.getGmail() + "\n" +
	            "ID Maquinaria: " + maquinaria.getIDMaquinaria() + "\n" +
	            "Marca: " + maquinaria.getMarca() + "\n" +
	            "Modelo: " + maquinaria.getModelo() + "\n" +
	            "Número de Serie: " + maquinaria.getNumeroSerie() + "\n" +
	            "Tipo Maquinaria: " + maquinaria.getTipoMaquinaria() + "\n" +
	            "Año de Fabricación: " + maquinaria.getAnioFabricacion() + "\n" +
	            "Número de Motor: " + maquinaria.getNumeroMotor() + "\n" +
	            "Horas de Operación: " + maquinaria.getHorasOperación() + "\n" +
	            "Kilometraje: " + maquinaria.getKilometraje()
	        );

	        return "SelectMantenimiento";
	    } catch (Exception e) {
	        // Ignorar errores, pero registrar por si acaso
	        LoggerHelper.logError("Error inesperado al registrar maquinaria", e);
	        return "ErrorPage";
	    }
	}
	//navegacion
	@GetMapping("/fecha-seleccionada")
	public String fechaSeleccionada() {
	    return "SelecTecnico"; 
	}
	//navegacion
	@GetMapping("/registro-exitoso")
	public String registroExitoso() {
	    return "Pago(1)"; 
	}
	/*
	//navegacion
	@GetMapping("/pagar")
	public String procesarPago() {
	    return "Inicio";
	}*/
	@GetMapping("/pagar")
	public void procesarPago(HttpServletResponse response) {
	    // Aquí se usa información real de la sesión o del modelo; a modo de ejemplo se define la tabla fija.
	    String[][] tableData = {
	        {"ID Pago", "Cliente", "Maquinaria ID", "Fecha de Registro", "Tipo de Mantenimiento", "Subtotal", "IGV", "Total a Pagar"},
	        {"001", "Juan Pérez", "CAT 320D", "05/04/2025", "Predictivo", "800$", "144$", "944$"}
	    };

	    try {
	        byte[] excelBytes = ExcelGenerator.generateExcel(tableData);
	        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	        response.setHeader("Content-Disposition", "attachment; filename=\"pago.xlsx\"");
	        response.getOutputStream().write(excelBytes);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	}
	
	//Usa el facade
		@PostMapping("/procesar-mantenimiento")
		public String procesarMantenimiento(@ModelAttribute SeleccionMantenimientoDTO seleccion,
		                                    HttpSession session, Model model) {
		    RegistroServicioFacade facade = (RegistroServicioFacade) session.getAttribute(FACADE_SESSION_KEY);
		    if (facade == null) return "redirect:/Login"; // manejo de error básico

		    facade.seleccionarTiposMantenimiento(seleccion.getTiposSeleccionados(), seleccion);
		    facade.establecerFecha(seleccion.getFechaSalida());

		    model.addAttribute("tecnicos", listaTecnicos); // aún necesitas mostrar técnicos
		    return "SelecTecnico";
		}
		/*
	@PostMapping("/procesar-mantenimiento")
  	public String procesarMantenimiento(@ModelAttribute SeleccionMantenimientoDTO seleccion, Model model, HttpSession session) {
  		System.out.println("Tipos seleccionados: " + seleccion.getTiposSeleccionados());
  	    System.out.println("Fecha de salida: " + seleccion.getFechaSalida());
  	    // Crea una lista de mantenimeintos 
  	    List<Mantenimiento> mantenimientos = MantenimientoFactory.crearMantenimientos(seleccion.getTiposSeleccionados()); 
  	    // Puedes almacenar esta lista en la sesión, o pasarla al modelo
  	    model.addAttribute("mantenimientos", mantenimientos);
  	    model.addAttribute("fechaSalida", seleccion.getFechaSalida());

  	    return "SelecTecnico"; // Pasas los datos a la siguiente vista
  	}*/
	
	
	
	
	private List<Tecnico> listaTecnicos = new ArrayList<>();
  	@PostMapping("/agregar-tecnico")
  	public String agregarTecnico(@ModelAttribute DtoTecnico dto, Model model) {
  	    System.out.println("nombre: " + dto.getNombre());

  	    Tecnico nuevo = new Tecnico(
  	        dto.getId(),
  	        dto.getNombre(),
  	        dto.getApellido(),
  	        dto.getEspecialidad(),
  	        dto.getTelefono(),
  	        dto.getEmail()
  	    );

  	    listaTecnicos.add(nuevo);

  	    // Volver a cargar el modelo con la lista actualizada
  	    model.addAttribute("dtoTecnico", new DtoTecnico());
  	    model.addAttribute("tecnicos", listaTecnicos);

  	    return "gestionar_tecnico";
  	}
  	
        /*
  	    @PostMapping("/tecnico-seleccionado")
  	    public String recibirTecnicoSeleccionado(@ModelAttribute DtoTecnico tecnicoSeleccionado,
  	                                             HttpSession session) {
  	        // Guardamos el técnico seleccionado en sesión
  	    	System.out.println(tecnicoSeleccionado.getNombre());
  	        session.setAttribute("tecnicoSeleccionado", tecnicoSeleccionado);

  	        // Redireccionar a la siguiente vista que quieras mostrar
  	        return "Pago(1)"; // cámbialo por la ruta real
  	    
  	}*/
  	//Usa el facade
  	  	@PostMapping("/tecnico-seleccionado")
  	  	public String recibirTecnicoSeleccionado(@ModelAttribute DtoTecnico tecnicoSeleccionado,
  	  	                                         HttpSession session, Model model) {
  	  	    RegistroServicioFacade facade = (RegistroServicioFacade) session.getAttribute(FACADE_SESSION_KEY);
  	  	    if (facade == null) return "redirect:/Login";

  	  	    // Imprimir los datos del DTO desde el Facade
  	  	    facade.seleccionarTecnicoDesdeDTO(tecnicoSeleccionado);

  	  	    // Buscar el objeto real (de la lista)
  	  	    Tecnico tecnico = listaTecnicos.stream()
  	  	        .filter(t -> t.getId().equals(tecnicoSeleccionado.getId()))
  	  	        .findFirst()
  	  	        .orElse(null);


  	  	    return "Pago(1)";
  	  	}
	
}
