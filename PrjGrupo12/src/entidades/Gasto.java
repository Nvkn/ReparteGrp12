package entidades;

public class Gasto {
    private double cantidad;
    private Usuario usuario;
    private Grupo grupo;

    public Gasto(double cantidad, Usuario usuario, Grupo grupo) {
        // Cantidad
        if (esCantidadValida(cantidad))
            this.cantidad = cantidad;
        else
            throw new IllegalArgumentException("La cantidad debe ser un número positivo.");
        // Usuario
        if (esUsuarioValido(usuario))
            this.usuario = usuario;
        else
            throw new IllegalArgumentException("El usuario no puede ser nulo.");
        // Grupo
        if (esGrupoValido(grupo)) {
            this.grupo = grupo;
            grupo.agregarGasto(this);
        } else
            throw new IllegalArgumentException("El grupo no puede ser nulo.");
    }

    private static boolean esCantidadValida(double cantidad) {
        return cantidad > 0;
    }

    private static boolean esUsuarioValido(Usuario usuario) {
        return usuario != null;
    }

    private static boolean esGrupoValido(Grupo grupo) {
        return grupo != null;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
}
