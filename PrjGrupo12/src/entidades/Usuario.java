package entidades;

public class Usuario {
	private String id;
	private 
	
	private Usuario(String id) {
		if (id == null) {
            throw new IllegalArgumentException("El identificador del usuario no puede ser nulo ni vacío.");
        }
		this.id = id;
	}
	
	public static Usuario crearUsuario(String id) {
		return new Usuario(id);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
