package entidades;

public class Gasto {
	private String id;
	private double cantidad;
	private String idUsuario;
	private String idGrupoGasto;

	public Gasto(String id, double cantidad, String idUsuario, String idGrupoGasto) {
		super();
		// Id
		if (esIdValido(id))
			this.id = id;
		else
            throw new IllegalArgumentException("El identificador del gasto no puede ser nulo ni vacío.");
		// Cantidad
		if (esCantidadValida(cantidad))
			this.cantidad = cantidad;
		else
            throw new IllegalArgumentException("La cantidad debe ser un número positivo.");
		// IdUsuario
		if (esIdUsuarioValido(idUsuario))
			this.idUsuario = idUsuario;
		else
            throw new IllegalArgumentException("El identificador del usuario no puede ser nulo ni vacío.");
		// IdGrupoGasto
		if (esIdGrupoGastoValido(idGrupoGasto))
			this.idGrupoGasto = idGrupoGasto;
		else
            throw new IllegalArgumentException("El identificador del grupo de gasto no puede ser nulo ni vacío.");
	}

    private static boolean esIdValido(String id) {
        return !(id == null || id.trim().isEmpty());
    }
    
	private static boolean esCantidadValida(double cantidad) {
        return cantidad > 0;
    }
	
    private static boolean esIdUsuarioValido(String idUsuario) {
        return !(idUsuario == null || idUsuario.trim().isEmpty());
    }

    private static boolean esIdGrupoGastoValido(String idGrupoGasto) {
        return !(idGrupoGasto == null || idGrupoGasto.trim().isEmpty());
    }

	public String getId() {
		return id;
	}


	public void setId(String id) {
		if (esIdValido(id))
			this.id = id;
		else
            throw new IllegalArgumentException("El identificador del gasto no puede ser nulo ni vacío.");
	}


	public double getCantidad() {
		return cantidad;
	}


	public void setCantidad(double cantidad) {
		if (esCantidadValida(cantidad))
			this.cantidad = cantidad;
		else
            throw new IllegalArgumentException("La cantidad debe ser un número positivo.");
	}


	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		if (esIdUsuarioValido(idUsuario))
			this.idUsuario = idUsuario;
		else
            throw new IllegalArgumentException("El identificador del usuario no puede ser nulo ni vacío.");
	}

	public String getIdGrupoGasto() {
		return idGrupoGasto;
	}

	public void setIdGrupoGasto(String idGrupoGasto) {
		if (esIdGrupoGastoValido(idGrupoGasto))
			this.idGrupoGasto = idGrupoGasto;
		else
            throw new IllegalArgumentException("El identificador del grupo de gasto no puede ser nulo ni vacío.");
	}
	
}
