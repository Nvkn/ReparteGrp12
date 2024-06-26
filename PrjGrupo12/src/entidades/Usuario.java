package entidades;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Usuario {
    private String id;
    private String correo;
    private String password;
    private ArrayList<Grupo> grupos;
    private ArrayList<Notificacion> notificaciones;

    
    public Usuario(String id, String correo, String password) {
        // Validación del identificador (ID)
        if (!esIdValido(id)) {
            throw new IllegalArgumentException("El identificador del usuario es inválido. Debe tener entre 4 y 20 caracteres y no puede estar vacío.");
        }
        
        // Validación del correo electrónico
        if (!esCorreoValido(correo)) {
            throw new IllegalArgumentException("El correo electrónico no tiene un formato válido.");
        }

        // Validación de la contraseña
        if (!esPasswordValida(password)) {
            throw new IllegalArgumentException("La contraseña proporcionada no es segura. Debe tener entre 8 y 20 caracteres e incluir mayúsculas, minúsculas y al menos un número o símbolo.");
        }

        this.id = id;
        this.correo = correo;
        this.password = password;
        this.grupos = new ArrayList<>();
        this.notificaciones = new ArrayList<>();
    }


	// Validación del formato del id
    private static boolean esIdValido(String id) {
        final int MIN_LONGITUD_ID = 4;
        final int MAX_LONGITUD_ID = 20;

        if (id == null) {
            return false;
        }

        String trimmedId = id.trim();
        return trimmedId.length() >= MIN_LONGITUD_ID && trimmedId.length() <= MAX_LONGITUD_ID;
    }

    // Validación del formato del correo electrónico
    private static boolean esCorreoValido(String correo) {
        if (correo == null || correo.trim().isEmpty()) {
            return false;
        }
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(correo);
        return matcher.matches();
    }

    // Validación de la seguridad de la contraseña
    private static boolean esPasswordValida(String password) {
        final int MIN_LONGITUD_PASSWORD = 8;
        final int MAX_LONGITUD_PASSWORD = 20;

        if (password == null) {
            return false;
        }

        String trimmedPassword = password.trim();
        if (trimmedPassword.length() < MIN_LONGITUD_PASSWORD || trimmedPassword.length() > MAX_LONGITUD_PASSWORD) {
            return false;
        }

        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasNumberOrSymbol = false;

        for (char c : trimmedPassword.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            } else if (Character.isDigit(c) || !Character.isLetterOrDigit(c)) {
                hasNumberOrSymbol = true;
            }
        }

        return hasUpperCase && hasLowerCase && hasNumberOrSymbol;
    }


    // Getters y setters

    public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public ArrayList<Grupo> getGrupos() {
		return grupos;
	}


	public void setGrupos(ArrayList<Grupo> grupos) {
		this.grupos = grupos;
	}


	public ArrayList<Notificacion> getNotificaciones() {
		return notificaciones;
	}


	public void setNotificaciones(ArrayList<Notificacion> notificaciones) {
		this.notificaciones = notificaciones;
	}

    // Métodos adicionales
    // GRUPO
    public Grupo crearGrupo(String titulo, String descripcion) {
        Grupo grupo = new Grupo(titulo, descripcion, this);
        grupos.add(grupo);
        return grupo;
    }

	public void agregarUsuarioAGrupo(Grupo grupo) {
        if (grupo == null) {
            throw new IllegalArgumentException("El grupo no puede ser nulo.");
        }
        grupos.add(grupo);
    }

    // GASTO
    public Gasto crearGasto(double cantidad, Grupo grupo) { 
        return new Gasto(cantidad, this, grupo);
    }
    
    private boolean puedeModificarGasto(Gasto gasto) {
        if (gasto == null) {
            throw new IllegalArgumentException("El gasto no puede ser nulo.");
        }
        if (!grupos.contains(gasto.getGrupo())) {
            throw new IllegalArgumentException("El gasto no pertenece a ninguno de los grupos del usuario.");
        }
        if (!gasto.getUsuario().equals(this)) {
            throw new IllegalArgumentException("El gasto no fue creado por el usuario.");
        }
        return true;
    }
      
    public void eliminarGasto(Gasto gasto) {
        if (puedeModificarGasto(gasto)) {
            gasto.getGrupo().eliminarGasto(gasto);
        }
    }

    public void editarGasto(Gasto gasto, double cantidad) {
        if (puedeModificarGasto(gasto)) {
            gasto.getGrupo().editarGasto(gasto, cantidad);
        }
    }
    
    // NOTIFICACION
    public void agregarNotificacion(Notificacion notificacion) {
        if (notificacion == null) {
            throw new IllegalArgumentException("La notificación no puede ser nula.");
        }
        notificaciones.add(notificacion);
    }
}
