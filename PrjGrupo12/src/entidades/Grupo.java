package entidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grupo {
    private String titulo;
    private String descripcion;
    private final List<Gasto> gastos;
    private final Map<Usuario, Double> balances;

    public Grupo(String titulo, String descripcion, Usuario admin) {
        // Validación del Titulo
        if (!esTituloValido(titulo)) {
            throw new IllegalArgumentException("El Titulo no tiene un formato válido.");
        }

        // Validación de la Descripcion
        if (!esDescripcionValida(descripcion)) {
            throw new IllegalArgumentException("La descripción tiene un formato inválido");
        }

        // Validación de la lista de usuarios
        if (!esUsuarioValido(admin)) {
            throw new IllegalArgumentException("El administrador no es válido.");
        }

        this.titulo = titulo;
        this.descripcion = descripcion;
        this.gastos = new ArrayList<>();
        this.balances = new HashMap<>();
        agregarUsuario(admin);
    }

    private static boolean esTituloValido(String titulo) {
        return !(titulo == null || titulo.trim().isEmpty());
    }

    private static boolean esDescripcionValida(String descripcion) {
        return !(descripcion == null || descripcion.trim().isEmpty());
    }

    private static boolean esUsuarioValido(Usuario usuario) {
        return usuario != null;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        if (esDescripcionValida(descripcion))
            this.descripcion = descripcion;
        else
            throw new IllegalArgumentException("La descripcion del grupo no puede ser nula ni vacía.");
    }

    public Map<Usuario, Double> getBalances() {
        return balances;
    }

    public List<Gasto> getGastos() {
        return gastos;
    }

    // MÉTODOS ADICIONALES
    // 1. USUARIO
    public void agregarUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo.");
        }
        try {
            if (balances.containsKey(usuario)) {
                throw new IllegalArgumentException("El usuario ya está en el grupo.");
            }
            usuario.agregarUsuarioAGrupo(this);
            balances.put(usuario, 0.0);
        } catch (Exception e) {
            throw new IllegalArgumentException("No se pudo agregar el usuario al grupo.");
        }
    }

    // 2. GASTO
    public void agregarGasto(Gasto gasto) {
        if (gasto == null) {
            throw new IllegalArgumentException("El gasto no puede ser nulo.");
        }
        gastos.add(gasto);
        //repartirGasto(gasto);
    }

}