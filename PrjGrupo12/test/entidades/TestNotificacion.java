package entidades;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class TestNotificacion {

	@Nested
    class CrearNotificacion {
        @DisplayName("CP4_001: ID nulo")
        @Test
        void testIdNulo() {
            LocalDateTime fecha = LocalDateTime.of(2007, 12, 3, 10, 15, 30);
            assertThrows(IllegalArgumentException.class, () -> {
                new Notificacion(null, "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean m", fecha);
            });
        }

        @DisplayName("CP4_002: ID vacío")
        @Test
        void testIdVacio() {
            LocalDateTime fecha = LocalDateTime.of(2007, 12, 3, 10, 15, 30);
            assertThrows(IllegalArgumentException.class, () -> {
                new Notificacion("", "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean m", fecha);
            });
        }

        @DisplayName("CP4_003: Mensaje nulo")
        @Test
        void testMensajeNulo() {
            LocalDateTime fecha = LocalDateTime.of(2007, 12, 3, 10, 15, 30);
            assertThrows(IllegalArgumentException.class, () -> {
                new Notificacion("ID", null, fecha);
            });
        }

        @DisplayName("CP4_004: Mensaje vacío")
        @Test
        void testMensajeVacio() {
            LocalDateTime fecha = LocalDateTime.of(2007, 12, 3, 10, 15, 30);
            assertThrows(IllegalArgumentException.class, () -> {
                new Notificacion("ID", "", fecha);
            });
        }

        @DisplayName("CP4_005: Mensaje largo")
        @Test
        void testMensajeLargo() {
            LocalDateTime fecha = LocalDateTime.of(2007, 12, 3, 10, 15, 30);
            assertThrows(IllegalArgumentException.class, () -> {
                new Notificacion("ID", "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean mA", fecha);
            });
        }

        @DisplayName("CP4_006: Fecha nula")
        @Test
        void testFechaNula() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Notificacion("ID", "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean m", null);
            });
        }

        @DisplayName("CP4_007: Fecha futura")
        @Test
        void testFechaFutura() {
            LocalDateTime fechaFutura = LocalDateTime.of(2047, 12, 3, 10, 15, 30);
            assertThrows(IllegalArgumentException.class, () -> {
                new Notificacion("ID", "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean m", fechaFutura);
            });
        }

        @DisplayName("CP4_008: Caso válido")
        @Test
        void testValidoFechaCorrecta() {
            LocalDateTime fechaCorrecta = LocalDateTime.of(2007, 12, 3, 10, 15, 30);
            assertDoesNotThrow(() -> {
                new Notificacion("ID", "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean m", fechaCorrecta);
            });
        }
	}

}
