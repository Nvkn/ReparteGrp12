package entidades;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class TestUsuario {
    // Abreviaturas
    String ID3C = "IDU";
    String ID4C = "ID_U";
    String ID20C = "ID_U5678901234567890";
    String ID21C = "ID_U56789012345678901";
    String P7C = "pP34567";
    String P8C = "pP345678";
    String P20C = "pP345678901234567890";
    String P21C = "pP3456789012345678901";


    @Nested
    @DisplayName("CP1: Casos de prueba del constructor")
    class ConstructorTest {
        @Nested
        @DisplayName("Clases de equivalencia no válidas de: ID")
        class CPID {
            @DisplayName("CP_U1_01: ID nulo")
            @Test
            void CP_U1_01() {
                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    new Usuario(null, "correo@ejemplo.com", "Password1");
                });
                assertTrue(exception.getMessage().contains("El identificador del usuario es inválido"));
            }

            @DisplayName("CP_U1_02: ID vacío")
            @Test
            void CP_U1_02() {
                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    new Usuario("", "correo@ejemplo.com", "Password1");
                });
                assertTrue(exception.getMessage().contains("El identificador del usuario es inválido"));
            }

            @DisplayName("CP_U1_03: ID corto ")
            @Test
            void CP_U1_03() {
                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    new Usuario(ID3C, "correo@ejemplo.com", "Password1");
                });
                assertTrue(exception.getMessage().contains("El identificador del usuario es inválido"));
            }

            @DisplayName("CP_U1_04: ID largo")
            @Test
            void CP_U1_04() {
                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    new Usuario(ID21C, "correo@ejemplo.com", "Password1");
                });
                assertTrue(exception.getMessage().contains("El identificador del usuario es inválido"));
            }
        }

        @Nested
        @DisplayName("Clases de equivalencia no válidas de: Correo")
        class CPCorreo {
            @DisplayName("CP_U1_05: Correo nulo")
            @Test
            void CP_U1_05() {
                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    new Usuario("Ejemplo_id", null, "Password1");
                });
                assertTrue(exception.getMessage().contains("El correo electrónico no tiene un formato válido"));
            }

            @DisplayName("CP_U1_06: Correo vacío ")
            @Test
            void CP_U1_06() {
                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    new Usuario("Ejemplo_id", " ", "Password1");
                });
                assertTrue(exception.getMessage().contains("El correo electrónico no tiene un formato válido"));
            }

            @DisplayName("CP_U1_07: Correo con formato inválido ")
            @Test
            void CP_U1_07() {
                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    new Usuario("Ejemplo_id", "correosinarroba.com", "Password1");
                });
                assertTrue(exception.getMessage().contains("El correo electrónico no tiene un formato válido"));
            }
        }

        @Nested
        @DisplayName("Clases de equivalencia no válidas de: Password")
        class CPPassword {
            @DisplayName("CP_U1_08: Password nulo ")
            @Test
            void CP_U1_08() {
                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    new Usuario("Ejemplo_id", "correo@ejemplo.com", null);
                });
                assertTrue(exception.getMessage().contains("La contraseña proporcionada no es segura"));
            }

            @DisplayName("CP_U1_09: Password vacío ")
            @Test
            void CP_U1_09() {
                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    new Usuario("Ejemplo_id", "correo@ejemplo.com", " ");
                });
                assertTrue(exception.getMessage().contains("La contraseña proporcionada no es segura"));
            }

            @DisplayName("CP_U1_10: Password sin mayúsculas ")
            @Test
            void CP_U1_10() {
                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    new Usuario("Ejemplo_id", "correo@ejemplo.com", "password1");
                });
                assertTrue(exception.getMessage().contains("La contraseña proporcionada no es segura"));
            }

            @DisplayName("CP_U1_11: Password sin minúsculas ")
            @Test
            void CP_U1_11() {
                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    new Usuario("Ejemplo_id", "correo@ejemplo.com", "PASSWORD1");
                });
                assertTrue(exception.getMessage().contains("La contraseña proporcionada no es segura"));
            }

            @DisplayName("CP_U1_12: Password sin dígitos ")
            @Test
            void CP_U1_12() {
                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    new Usuario("Ejemplo_id", "correo@ejemplo.com", "passwords");
                });
                assertTrue(exception.getMessage().contains("La contraseña proporcionada no es segura"));
            }

            @DisplayName("CP_U1_13: Password demasiado corta (7 caracteres)")
            @Test
            void CP_U1_13() {
                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    new Usuario("Ejemplo_id", "correo@ejemplo.com", P7C);
                });
                assertTrue(exception.getMessage().contains("La contraseña proporcionada no es segura"));
            }

            @DisplayName("CP_U1_14:  Password demasiado larga (15 caracteres)")
            @Test
            void CP_U1_14() {
                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    new Usuario("Ejemplo_id", "correo@ejemplo.com", P21C);
                });
                assertTrue(exception.getMessage().contains("La contraseña proporcionada no es segura"));
            }
        }

        @Nested
        @DisplayName("Clases de equivalencia válidas")
        class CPValidas {
            @DisplayName("CP_U1_15: Todo válido - ID y Password Min ")
            @Test
            void CP_U1_15() {
                assertDoesNotThrow(() -> {
                    Usuario validUsuario = new Usuario(ID4C, "correo@ejemplo.com", P8C);
                });
            }

            @DisplayName("CP_U1_16: Todo válido - ID y Password Max ")
            @Test
            void CP_U1_16() {
                assertDoesNotThrow(() -> {
                    Usuario validUsuario = new Usuario(ID20C, "correo@ejemplo.com", P20C);
                });
            }
        }
    }
}
