package entidades;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.LocalDateTime;

import org.junit.jupiter.api.*;

class TestNotificacion {
    // Abreviaturas
    String M100C = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean m";
    String M101C = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean ma";
    LocalDateTime FechaValida = LocalDateTime.of(2007, 12, 3, 10, 15, 30);
    LocalDateTime FechaPosterior = LocalDateTime.of(2047, 12, 3, 10, 15, 30);

    private static Usuario usuarioMocked;

    @BeforeAll
    static void setUp() {
        usuarioMocked = mock(Usuario.class);
    }

    @Nested
    @DisplayName("CP_N1: Casos de prueba del constructor")
    class ConstructorTest {
        @Nested
        @DisplayName("Clases de equivalencia no válidas de: Destinatario")
        class CPDestinatario {
            @DisplayName("CP_N1_01: Destinatario nulo")
            @Test
            void CP_N1_01() {
                assertThrows(IllegalArgumentException.class, () -> {
                    new Notificacion(null, M100C, FechaValida);
                }, "El destinatario no es nulo.");
            }
        }

        @Nested
        @DisplayName("Clases de equivalencia no válidas de: Mensaje")
        class CPMensaje {
            @DisplayName("CP_N1_02: Mensaje nulo")
            @Test
            void CP_N1_02() {
                assertThrows(IllegalArgumentException.class, () -> {
                    new Notificacion(usuarioMocked, null, FechaValida);
                }, "El mensaje no es nulo.");
            }

            @DisplayName("CP_N1_03: Mensaje vacío")
            @Test
            void CP_N1_03() {
                assertThrows(IllegalArgumentException.class, () -> {
                    new Notificacion(usuarioMocked, "", FechaValida);
                });
            }

            @DisplayName("CP_N1_04: Mensaje demasiado largo (101 caracteres)")
            @Test
            void CP_N1_04() {
                assertThrows(IllegalArgumentException.class, () -> {
                    new Notificacion(usuarioMocked, M101C, FechaValida);
                }, "El mensaje no es suficientemente largo.");
            }
        }

        @Nested
        @DisplayName("Clases de equivalencia no válidas de: Mensaje")
        class CPFecha {
            @DisplayName("CP_N1_05: Fecha nula")
            @Test
            void CP_N1_05() {
                assertThrows(IllegalArgumentException.class, () -> {
                    new Notificacion(usuarioMocked, M100C, null);
                });
            }

            @DisplayName("CP_N1_06: Fecha posterior")
            @Test
            void CP_N1_06() {
                assertThrows(IllegalArgumentException.class, () -> {
                    new Notificacion(usuarioMocked, M100C, FechaPosterior);
                }, "La fecha no es posterior.");
            }
        }

        @Nested
        @DisplayName("Clases de equivalencia válidas")
        class CPValidas {
            @DisplayName("CP_N1_07: Mensaje máximo")
            @Test
            void CP_GA1_04() {
                assertDoesNotThrow(() -> {
                    new Notificacion(usuarioMocked, M100C, FechaValida);
                }, "El caso no es válido.");
            }
        }
    }
}

