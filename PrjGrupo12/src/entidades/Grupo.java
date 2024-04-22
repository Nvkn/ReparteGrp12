package entidades;
import java.util.ArrayList;
import java.util.List;

public class Grupo {
    private String titulo;
    private String id;
    private String descripcion;
    private List<String> usuarios; // ids usuarios
    private List<String> gastos;   // ids gastos 

          
    public Grupo(String titulo, String id, String descripcion) {
    	 // Validación del identificador (ID)
        if (!esIdValido(id)) {
            throw new IllegalArgumentException("El identificador del grupo no puede ser nulo ni vacío.");
        }
        
        // Validación del Titulo
        if (!esTituloValido(titulo)) {
            throw new IllegalArgumentException("El Titulo del grupo no puede ser nulo ni vacío.");
        }

        // Validación de la Descripcion
        if (!esDescripcionValida(descripcion)) {
            throw new IllegalArgumentException("La descripcion del grupo no puede ser nula ni vacía.");
        }

        this.titulo = titulo;
        this.id = id;
        this.descripcion = descripcion;
        this.usuarios = new ArrayList<>();
        this.gastos = new ArrayList<>();
    }


    
    private static boolean esIdValido(String id) {
        return !(id == null || id.trim().isEmpty());
    }
    
	private static boolean esTituloValido(String titulo) {
		return !(titulo == null || titulo.trim().isEmpty());    }
	
    private static boolean esDescripcionValida(String descripcion) {
        return !(descripcion == null || descripcion.trim().isEmpty());
    }


    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
		if (esTituloValido(titulo))
			this.titulo = titulo;
		else
            throw new IllegalArgumentException("El Titulo del grupo no puede ser nulo ni vacío.");
	}


    public String getId() {
        return id;
    }

    public void setId(String id) {
		if (esIdValido(id))
			this.id = id;
		else
            throw new IllegalArgumentException("El identificador del grupo no puede ser nulo ni vacío.");
	}


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
		if (esDescripcionValida(descripcion))
			this.descripcion = descripcion;
		else
            throw new IllegalArgumentException("La descripcion del grupo no puede ser nula ni vacía.");
	}

}