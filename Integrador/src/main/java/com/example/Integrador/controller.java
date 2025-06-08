package com.example.Integrador;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.List;

@Controller
public class controller {
	
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
	//validar tipo de usuario
	@GetMapping("/procesar-login")
	public String procesarLogin(
	        @RequestParam(name = "usuario") String usuario,
	        @RequestParam(name = "contra") String contraseña,
	        @RequestParam(name = "tipoUsuario") String tipoUsu
	) {
	    System.out.println("usuario: " + usuario);
	    System.out.println("Contraseña: " + contraseña);
	    System.out.println("Tipo usuario: " + tipoUsu);

	    TipoUsuario user = UsuarioFactory.crearUsuario(tipoUsu);

	    if (user != null) {
	        return user.redirigir(); // Devuelve la vista correspondiente
	    }

	    return "Login"; // Vista por defecto si el tipo no es válido
	}
	 @PostMapping("/registrar")
	    public String registrarDatos(DTOMaquinaria maquinaria) {
	        System.out.println("Nombre: " + maquinaria.getNombre());
	        System.out.println("Apellido: " + maquinaria.getApellido());
	        System.out.println("Celular: " + maquinaria.getNumeroCelular());
	        System.out.println("Gmail: " + maquinaria.getGmail());
	        System.out.println("ID Maquinaria: " + maquinaria.getIDMaquinaria());
	        System.out.println("Marca: " + maquinaria.getMarca());
	        System.out.println("Modelo: " + maquinaria.getModelo());
	        System.out.println("Número de Serie: " + maquinaria.getNumeroSerie());
	        System.out.println("Tipo: " + maquinaria.getTipoMaquinaria());
	        System.out.println("Año: " + maquinaria.getAnioFabricacion());
	        System.out.println("Motor: " + maquinaria.getNumeroMotor());
	        System.out.println("Horas: " + maquinaria.getHorasOperación());
	        System.out.println("Kilometraje: " + maquinaria.getKilometraje());

	        return "SelectMantenimiento"; // nombre de la vista (archivo .html o .jsp)
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
	//navegacion
	@GetMapping("/pagar")
	public String procesarPago() {
	    return "Inicio";
	}
	@PostMapping("/procesar-mantenimiento")
  	public String procesarMantenimiento(@ModelAttribute SeleccionMantenimientoDTO seleccion, Model model) {
  		System.out.println("Tipos seleccionados: " + seleccion.getTiposSeleccionados());
  	    System.out.println("Fecha de salida: " + seleccion.getFechaSalida());
  	    // Crea una lista de mantenimeintos 
  	    List<Mantenimiento> mantenimientos = MantenimientoFactory.crearMantenimientos(seleccion.getTiposSeleccionados()); 
  	    // Puedes almacenar esta lista en la sesión, o pasarla al modelo
  	    model.addAttribute("mantenimientos", mantenimientos);
  	    model.addAttribute("fechaSalida", seleccion.getFechaSalida());

  	    return "SelecTecnico"; // Pasas los datos a la siguiente vista
  	}
	
	
	
	
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
	
	
	
	
	
	
}
