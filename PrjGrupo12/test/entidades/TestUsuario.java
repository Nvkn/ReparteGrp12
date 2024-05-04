package entidades;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.*;

public class TestUsuario {
    @Nested
    @DisplayName("CP_U1: Casos de prueba de la historia de usuario: Registrar usuario")
    class ConstructorTest {
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
        @DisplayName("Clases de equivalencia no válidas de: ID")
        class CPID {
            String mensajeEsperado = "El identificador del usuario es inválido. Debe tener entre 4 y 20 caracteres y no puede estar vacío.";

            @DisplayName("CP_U1_01: ID nulo")
            @Test
            void CP_U1_01() {
                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    new Usuario(null, "correo@ejemplo.com", "Password1");
                }, ("ID no es nulo"));
                assertTrue(mensajeEsperado.contains(exception.getMessage()), "El mensaje de error no es el esperado.");
            }

            @DisplayName("CP_U1_02: ID vacío")
            @Test
            void CP_U1_02() {
                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    new Usuario("", "correo@ejemplo.com", "Password1");
                }, ("ID no es vacío"));
                assertTrue(mensajeEsperado.contains(exception.getMessage()), "El mensaje de error no es el esperado.");
            }

            @DisplayName("CP_U1_03: ID corto ")
            @Test
            void CP_U1_03() {
                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    new Usuario(ID3C, "correo@ejemplo.com", "Password1");
                }, ("ID no es corto"));
                assertTrue(mensajeEsperado.contains(exception.getMessage()), "El mensaje de error no es el esperado.");
            }

            @DisplayName("CP_U1_04: ID largo")
            @Test
            void CP_U1_04() {
                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    new Usuario(ID21C, "correo@ejemplo.com", "Password1");
                }, ("ID no es largo"));
                assertTrue(mensajeEsperado.contains(exception.getMessage()), "El mensaje de error no es el esperado.");
            }
        }

        @Nested
        @DisplayName("Clases de equivalencia no válidas de: Correo")
        class CPCorreo {
            String mensajeEsperado = "El correo electrónico no tiene un formato válido.";

            @DisplayName("CP_U1_05: Correo nulo")
            @Test
            void CP_U1_05() {
                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    new Usuario("Ejemplo_id", null, "Password1");
                }, ("Correo no es nulo"));
                assertTrue(mensajeEsperado.contains(exception.getMessage()), "El mensaje de error no es el esperado.");
            }

            @DisplayName("CP_U1_06: Correo vacío ")
            @Test
            void CP_U1_06() {
                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    new Usuario("Ejemplo_id", " ", "Password1");
                }, ("Correo no es vacío"));
                assertTrue(mensajeEsperado.contains(exception.getMessage()), "El mensaje de error no es el esperado.");
            }

            @DisplayName("CP_U1_07: Correo con formato inválido ")
            @Test
            void CP_U1_07() {
                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    new Usuario("Ejemplo_id", "correosinarroba.com", "Password1");
                }, ("Correo no tiene formato no válido"));
                assertTrue(mensajeEsperado.contains(exception.getMessage()), "El mensaje de error no es el esperado.");
            }
        }

        @Nested
        @DisplayName("Clases de equivalencia no válidas de: Password")
        class CPPassword {
            String mensajeEsperado = "La contraseña proporcionada no es segura. Debe tener entre 8 y 20 caracteres e incluir mayúsculas, minúsculas y al menos un número o símbolo.";

            @DisplayName("CP_U1_08: Password nulo ")
            @Test
            void CP_U1_08() {
                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    new Usuario("Ejemplo_id", "correo@ejemplo.com", null);
                }, ("Password no es nula"));
                assertTrue(mensajeEsperado.contains(exception.getMessage()), "El mensaje de error no es el esperado.");
            }

            @DisplayName("CP_U1_09: Password vacío ")
            @Test
            void CP_U1_09() {
                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    new Usuario("Ejemplo_id", "correo@ejemplo.com", " ");
                }, ("Password no es vacía"));
                assertTrue(mensajeEsperado.contains(exception.getMessage()), "El mensaje de error no es el esperado.");
            }

            @DisplayName("CP_U1_10: Password sin mayúsculas ")
            @Test
            void CP_U1_10() {
                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    new Usuario("Ejemplo_id", "correo@ejemplo.com", "password1");
                }, ("Password tiene mayúsculas"));
                assertTrue(mensajeEsperado.contains(exception.getMessage()), "El mensaje de error no es el esperado.");
            }

            @DisplayName("CP_U1_11: Password sin minúsculas ")
            @Test
            void CP_U1_11() {
                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    new Usuario("Ejemplo_id", "correo@ejemplo.com", "PASSWORD1");
                }, ("Password tiene minúsculas"));
                assertTrue(mensajeEsperado.contains(exception.getMessage()), "El mensaje de error no es el esperado.");
            }

            @DisplayName("CP_U1_12: Password sin dígitos ")
            @Test
            void CP_U1_12() {
                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    new Usuario("Ejemplo_id", "correo@ejemplo.com", "passwords");
                }, ("Password tiene dígitos"));
                assertTrue(mensajeEsperado.contains(exception.getMessage()), "El mensaje de error no es el esperado.");
            }

            @DisplayName("CP_U1_13: Password demasiado corta (7 caracteres)")
            @Test
            void CP_U1_13() {
                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    new Usuario("Ejemplo_id", "correo@ejemplo.com", P7C);
                }, ("Password no es corta"));
                assertTrue(mensajeEsperado.contains(exception.getMessage()), "El mensaje de error no es el esperado.");
            }

            @DisplayName("CP_U1_14:  Password demasiado larga (15 caracteres)")
            @Test
            void CP_U1_14() {
                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    new Usuario("Ejemplo_id", "correo@ejemplo.com", P21C);
                }, ("Password no es larga"));
                assertTrue(mensajeEsperado.contains(exception.getMessage()), "El mensaje de error no es el esperado.");
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
                }, ("No todos los argumentos son válidos"));
            }

            @DisplayName("CP_U1_16: Todo válido - ID y Password Max ")
            @Test
            void CP_U1_16() {
                assertDoesNotThrow(() -> {
                    Usuario validUsuario = new Usuario(ID20C, "correo@ejemplo.com", P20C);
                }, ("No todos los argumentos son válidos"));
            }
        }
    }

    @Nested
    @DisplayName("CP_U2: CAJA BLANCA - Casos de prueba de la historia de usuario: Crear grupo de gasto")
    class CrearGrupoGastoTest {
        Usuario usuarioValido;

        @BeforeEach
        public void setUp() {
            usuarioValido = new Usuario("ID1234", "correo@ejemplo.com", "Abc123..");
        }

        @Nested
        @DisplayName("CP_U2_1: Casos de prueba del método Usuario.crearGrupo")
        class CrearGrupoTest {
            @Test
            @DisplayName("CP_U2_1_01: Comprobar que se añade el grupo a la lista de grupos del usuario")
            public void CP_U2_1_01() {
                // Arrange
                String titulo = "Grupo de prueba";
                String descripcion = "Descripción de prueba";

                // Act + Assert
                assertDoesNotThrow(() -> {
                    Grupo grupo = usuarioValido.crearGrupo(titulo, descripcion);
                    assertTrue(usuarioValido.getGrupos().contains(grupo), "El grupo no se agregó a la lista de grupos del usuario.");
                }, "El método lanzó una excepción inesperada.");
            }
        }

        @Nested
        @DisplayName("CP_U2_2: Casos de prueba del método Usuario.agregarUsuarioAGrupo")
        class AgregarUsuarioAGrupoTest {
            @Test
            @DisplayName("CP_U2_2_01: Comprobar que se lanza una excepción si el grupo es nulo")
            public void CP_U2_2_01() {
                // Act + Assert
                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    usuarioValido.agregarUsuarioAGrupo(null);
                }, "No se lanzó una excepción cuando el grupo es nulo.");
                assertEquals("El grupo no puede ser nulo.", exception.getMessage(), "El mensaje de error no es el esperado.");
            }
        }
    }

    @Nested
    @DisplayName("CP_U3: CAJA BLANCA - Casos de prueba de la historia de usuario: Registrar gasto - Usuario.crearGasto()")
    class RegistrarGastoTest {
        @Test
        @DisplayName("CP_U3_1_01: Comprobar que se crea un gasto correctamente")
        public void CP_U3_1_01() {
            // Arrange
            Usuario usuarioValido = new Usuario("ID1234", "correo@ejemplo.com", "Abc123..");
            double cantidad = 100.0;
            Grupo grupoValido = mock(Grupo.class);

            // Act + Assert
            assertDoesNotThrow(() -> {
                usuarioValido.crearGasto(cantidad, grupoValido);
            }, "El método lanzó una excepción inesperada.");
        }
    }

    @Nested
    @DisplayName("CP_U4: CAJA BLANCA - Casos de prueba de la historia de usuario: Notificar deudas - Usuario.agregarNotificacion()")
    class NotificarDeudasTest {
        private Usuario usuarioValido;

        @BeforeEach
        public void setUp() {
            usuarioValido = new Usuario("ID1234", "correo@ejemplo.com", "Abc123..");
        }

        @Test
        @DisplayName("CP_U4_1_01: Comprobar que se lanza una excepción si la notificación es nula")
        public void CP_U4_1_01() {
            // Arrange
            String mensajeEsperado = "La notificación no puede ser nula.";

            // Act + Assert
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                usuarioValido.agregarNotificacion(null);
            }, "No se lanzó una excepción cuando la notificación es nula.");
            assertEquals(mensajeEsperado, exception.getMessage(), "El mensaje de error no es el esperado.");
        }

        @Test
        @DisplayName("CP_U4_1_02: Comprobar que se añade la notificación a la lista de notificaciones del usuario")
        public void CP_U4_1_02() {
            // Arrange
            Notificacion notificacionMocked = mock(Notificacion.class);

            // Act + Assert
            assertDoesNotThrow(() -> {
                usuarioValido.agregarNotificacion(notificacionMocked);
                assertTrue(usuarioValido.getNotificaciones().contains(notificacionMocked), "La notificación no se agregó a la lista de notificaciones del usuario.");
            }, "El método lanzó una excepción inesperada.");
        }
    }
}


