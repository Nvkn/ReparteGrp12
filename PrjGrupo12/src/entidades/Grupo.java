package entidades;

import java.time.LocalDateTime;
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
        repartirGasto(gasto);
    }

    private boolean esGastoModificable(Gasto gasto) {
        if (gasto == null) {
            throw new IllegalArgumentException("El gasto no puede ser nulo.");
        }
        if (!gastos.contains(gasto)) {
            throw new IllegalArgumentException("El gasto no pertenece al grupo.");
        }
        return true;
    }
    
    public void eliminarGasto(Gasto gasto) {
        if (esGastoModificable(gasto)) {
            gastos.remove(gasto);
            deshacerRepartoGasto(gasto);
        }
    }

    // Repartir cada gasto y actualizar balance
    private void repartirGasto(Gasto gasto){
        double cantidad = gasto.getCantidad();
        Usuario pagador = gasto.getUsuario();
        double cantidadPorUsuario = cantidad / balances.size();
        for (Usuario u : balances.keySet()) {
            if (u.equals(pagador)) {
                balances.put(u, balances.get(u) + cantidad - cantidadPorUsuario);
            } else {
                balances.put(u, balances.get(u) - cantidadPorUsuario);
            }
        }
        calcularTransacciones();
    }
    
    // Deshacer reparto de gasto y actualizar balance
    public void deshacerRepartoGasto(Gasto gasto){
        double cantidad = gasto.getCantidad();
        Usuario pagador = gasto.getUsuario();
        double cantidadPorUsuario = cantidad / balances.size();
        for (Usuario u : balances.keySet()) {
            if (u.equals(pagador)) {
                balances.put(u, balances.get(u) - cantidad + cantidadPorUsuario);
            } else {
                balances.put(u, balances.get(u) + cantidadPorUsuario);
            }
        }
    }

    public List<String> calcularTransacciones() {
        List<String> transacciones = new ArrayList<>();
        // Copia de balances
        Map<Usuario, Double> balances_aux = new HashMap<>(this.balances);
        // Iterar sobre los balances para determinar quién debe pagar a quién
        for (Usuario deudor : balances_aux.keySet()) {
            double balanceDeudor = balances_aux.get(deudor);
            if (balanceDeudor < 0) {
                for (Usuario acreedor : balances_aux.keySet()) {
                    double balanceAcreedor = balances_aux.get(acreedor);
                    if (balanceAcreedor > 0) {
                        // Calcular el monto a transferir
                        double monto = Math.min(Math.abs(balanceDeudor), balanceAcreedor);
                        // Agregar la transacción a la lista
                        transacciones.add(deudor.getId() + " debe pagar " + String.format("%.2f€", monto) + " a " + acreedor.getId());
                        // Crear notificaciones
                        crearNotificacionDeudor(deudor, acreedor, monto);
                        crearNotificacionAcreedor(deudor, acreedor, monto);
                        // Actualizar los balances
                        balances_aux.put(deudor, balanceDeudor + monto);
                        balances_aux.put(acreedor, balanceAcreedor - monto);
                        // Actualizar el balance del deudor para evitar duplicados
                        balanceDeudor += monto;
                        if (balanceDeudor >= 0) {
                            break; // El deudor ya ha pagado suficiente
                        }
                    }
                }
            }
        }
        return transacciones;
    }

    private void crearNotificacionDeudor(Usuario deudor, Usuario acreedor, double monto) {
        String mensaje = "Debes pagar " + String.format("%.2f€", monto) + " a " + acreedor.getId();
        new Notificacion(deudor, mensaje, LocalDateTime.now().minusMinutes(1));
    }

    private void crearNotificacionAcreedor(Usuario deudor, Usuario acreedor, double monto) {
        String mensaje = deudor.getId() + " te debe pagar " + String.format("%.2f€", monto);
        new Notificacion(acreedor, mensaje, LocalDateTime.now().minusMinutes(1));
    }

}