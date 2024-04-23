package entidades;

import java.time.LocalDateTime;

public class Notificacion {
    private String id;
    private String mensaje;
    private LocalDateTime fecha;

    // Constructor
    public Notificacion(String id, String mensaje, LocalDateTime fecha) {
        if (!esIdValido(id)) {
            throw new IllegalArgumentException("El identificador de la notificación no puede ser nulo o vacío y debe ser único para cada notificación.");
        }

        if (!esMensajeValido(mensaje)) {
            throw new IllegalArgumentException("El mensaje de la notificación no puede ser nulo o vacío y debe tener una longitud máxima de 100 caracteres.");
        }

        if (!esFechaValida(fecha)) {
            throw new IllegalArgumentException("La fecha de la notificación debe seguir el formato YYYY-MM-DD HH:MM:SS y ser anterior a la fecha actual en el momento de la creación.");
        }

        // Asignación de valores a las propiedades del objeto
        this.id = id;
        this.mensaje = mensaje;
        this.fecha = fecha;
    }

    // Validación del ID de la notificación
    private static boolean esIdValido(String id) {
        return id != null && !id.trim().isEmpty();
    }

    // Validación del mensaje de la notificación
    private static boolean esMensajeValido(String mensaje) {
        return mensaje != null && !mensaje.trim().isEmpty() && mensaje.trim().length() <= 100;
    }

    // Validación de la fecha de la notificación
    private static boolean esFechaValida(LocalDateTime fecha) {
        return fecha != null && fecha.isBefore(LocalDateTime.now());
    }

    // Getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (!esIdValido(id)) {
            throw new IllegalArgumentException("El identificador de la notificación no puede ser nulo o vacío y debe ser único para cada notificación.");
        }
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        if (!esMensajeValido(mensaje)) {
            throw new IllegalArgumentException("El mensaje de la notificación no puede ser nulo o vacío y debe tener una longitud máxima de 100 caracteres.");
        }
        this.mensaje = mensaje;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        if (!esFechaValida(fecha)) {
            throw new IllegalArgumentException("La fecha de la notificación debe seguir el formato YYYY-MM-DD HH:MM:SS y ser anterior a la fecha actual en el momento de la creación.");
        }
        this.fecha = fecha;
    }
}
