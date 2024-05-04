package entidades;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import static org.mockito.Mockito.*;

import java.util.Map;
import java.util.List;


class TestGrupo {
    private static Usuario usuarioMocked;
    private static Gasto gastoMocked;

    @BeforeAll
    static void setUp() {
        usuarioMocked = mock(Usuario.class);
        gastoMocked = mock(Gasto.class);
    }

    @Nested
    @DisplayName("CP_GR1: Casos de prueba de la historia de usuario: Crear Grupo de Gasto")
    class CrearGrupoGastoTest {

        @Nested
        @DisplayName("CP_GR1_1: Casos de prueba del constructor")
        class ConstructorTest {
            @Nested
            @DisplayName("Clases de equivalencia no válidas de: Título")
            class CPTitulo {
                @DisplayName("CP_GR1_1_01: Título nulo")
                @Test
                void CP_GR1_1_01() {
                    assertThrows(IllegalArgumentException.class, () -> {
                        new Grupo(null, "Descripcion", usuarioMocked);
                    }, "El Titulo no es nulo.");
                }

                @DisplayName("CP_GR1_1_02: Título vacío")
                @Test
                void CP_GR1_1_02() {
                    assertThrows(IllegalArgumentException.class, () -> {
                        new Grupo("", "Descripcion", usuarioMocked);
                    }, "El Titulo no está vacío.");
                }
            }

            @Nested
            @DisplayName("Clases de equivalencia no válidas de: Descripcion")
            class CPDescripcion {
                @DisplayName("CP_GR1_1_03: Descripción nula")
                @Test
                void CP_GR1_1_03() {
                    assertThrows(IllegalArgumentException.class, () -> {
                        new Grupo("Título", null, usuarioMocked);
                    }, "La descripción no es nula.");
                }

                @DisplayName("CP_GR1_1_04: Descripción vacía")
                @Test
                void CP_GR1_1_04() {
                    assertThrows(IllegalArgumentException.class, () -> {
                        new Grupo("Título", "", usuarioMocked);
                    }, "La descripción no está vacía.");
                }
            }

            @Nested
            @DisplayName("Clases de equivalencia no válidas de: Admin")
            class CPAdmin {
                @DisplayName("CP_GR1_1_05: Admin null")
                @Test
                void CP_GR1_1_05() {
                    assertThrows(IllegalArgumentException.class, () -> {
                        new Grupo("Título", "Descripción", null);
                    }, "El admin no es nulo.");
                }
            }

            @Nested
            @DisplayName("Clases de equivalencia válidas")
            class CPValidas {
                @DisplayName("CP_GR1_1_06: Todo válido - ID y Password Min")
                @Test
                void CP_GR1_1_06() {
                    assertDoesNotThrow(() -> {
                        new Grupo("Título", "Descripción", usuarioMocked);
                    }, "El caso no es válido.");
                }
            }
        }

        @Nested
        @DisplayName("CP_GR1_2: Casos de prueba de Agregar usuario")
        class AgregarUsuarioTest {
            private Grupo grupo;

            @BeforeEach
            public void setUp() {
                grupo = new Grupo("Grupo de prueba", "Descripción de prueba", usuarioMocked);
            }

            @Test
            @DisplayName("CP_GR1_2_01: Usuario nulo")
            void CP_GR1_2_01() {
                assertThrows(IllegalArgumentException.class, () -> {
                    grupo.agregarUsuario(null);
                }, "El usuario no se reconoce como nulo.");
            }

            @Test
            @DisplayName("CP_GR1_2_02: Usuario válido")
            void CP_GR1_2_02() {
                Usuario usuario2 = mock(Usuario.class);
                assertDoesNotThrow(() -> {
                    grupo.agregarUsuario(usuario2);
                }, "El usuario no se reconoce como válido.");
            }

            @Test
            @DisplayName("CP_GR1_2_03: CAJA BLANCA - Usuario repetido")
            void CP_GR1_2_03() {
                // Se mete el usuarioValido dos veces (ya creó el grupo)
                Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    grupo.agregarUsuario(usuarioMocked);
                }, "El usuario no se reconoce como repetido.");

                String mensajeEsperado = "No se pudo agregar el usuario al grupo.";
                assertTrue(mensajeEsperado.contains(exception.getMessage()), "El mensaje de error no es el esperado.");
            }
        }
    }

    @Nested
    @DisplayName("CP_GR2: CAJA BLANCA - Casos de prueba de la historia de usuario: Registrar gasto - Grupo.agregarGasto()")
    class RegistrarGastoTest {
        @Test
        @DisplayName("CP_GR2_1_01: Gasto nulo")
        void CP_GR2_1_01() {
            Grupo grupo = new Grupo("Grupo de prueba", "Descripción de prueba", usuarioMocked);
            assertThrows(IllegalArgumentException.class, () -> {
                grupo.agregarGasto(null);
            }, "El gasto no se reconoce como nulo.");
        }
    }

    @Nested
    @DisplayName("CP_GR3: Casos de prueba de la historia de usuario: Repartir gastos")
    class RepartirGastosTest {
        private Grupo grupo;
        private Usuario usuarioMocked2;

        @BeforeEach
        public void setUp() {
            usuarioMocked2 = mock(Usuario.class);

            // Configurar comportamiento de los usuarios simulados
            when(usuarioMocked.getId()).thenReturn("usuario1");
            when(usuarioMocked2.getId()).thenReturn("usuario2");

            grupo = new Grupo("Grupo de prueba", "Descripción de prueba", usuarioMocked);
            grupo.agregarUsuario(usuarioMocked2);
        }

        @Nested
        @DisplayName("CP_GR3_1: Repartir gasto")
        class CPRepartirGasto {
            @Test
            @DisplayName("CP_GR3_1_01: Repartir gasto")
            void CP_GR3_1_01() {
                // Configurar gasto simulado
                when(gastoMocked.getCantidad()).thenReturn(20.0); // Simular un gasto de 100 unidades
                when(gastoMocked.getUsuario()).thenReturn(usuarioMocked); // Simular usuario1 como pagador del gasto

                // Llamar al método repartirGasto(), interno en agregarGasto
                grupo.agregarGasto(gastoMocked);

                // Verificar que se haya actualizado correctamente el balance de los usuarios
                Map<Usuario, Double> balances = grupo.getBalances();
                double expectedBalanceUsuario1 = 10.0; // Usuario1 es el pagador, por lo que su balance debería disminuir a la mitad
                double expectedBalanceUsuario2 = -10.0; // Los otros usuarios deben asumir la otra mitad del gasto

                assertEquals(expectedBalanceUsuario1, balances.get(usuarioMocked), "El balance de usuario1 no es el esperado");
                assertEquals(expectedBalanceUsuario2, balances.get(usuarioMocked2), "El balance de usuario2 no es el esperado");
            }
        }

        @Nested
        @DisplayName("CP_GR3_2: Calcular transacciones")
        class CPCalcularTransacciones {
            @Test
            @DisplayName("CP_GR3_2_01: Devolución 2 usuarios - Simple")
            void CP_GR3_2_01() {
                // Arrange - Se envía un balance en el que el usuario 2 debe 50€ al usuario 1
                Map<Usuario, Double> balances = grupo.getBalances();
                balances.put(usuarioMocked, 10.0); // Saldo negativo para usuario1
                balances.put(usuarioMocked2, -10.0); // Saldo positivo para usuario2

                // Act
                List<String> transacciones = grupo.calcularTransacciones();

                // Assert
                assertEquals(1, transacciones.size(), "Hay un número de transacciones inesperado."); // Debería haber una transacción pendiente
                assertTrue(transacciones.get(0).contains("usuario1"), "El usuario1 no está involucrado en la transacción"); // La transacción debería involucrar al usuario1
                assertTrue(transacciones.get(0).contains("usuario2"), "El usuario2 no está involucrado en la transacción"); // La transacción debería involucrar al usuario2
            }

            @Test
            @DisplayName("CP_GR3_2_02: CAJA BLANCA - Devolución 2 usuarios - Balance descompensado")
            void CP_GR2_2_02() {
                // Arrange - Se envía un balance "incorrecto" para que balanceDeudor acabe siendo distinto de 0
                Map<Usuario, Double> balances = grupo.getBalances();
                balances.put(usuarioMocked, 10.0); // Saldo negativo para usuario1
                balances.put(usuarioMocked2, -20.0); // Saldo positivo para usuario2

                // Act
                List<String> transacciones = grupo.calcularTransacciones();

                // Assert
                assertEquals(1, transacciones.size(), "Hay un número de transacciones inesperado."); // Debería haber una transacción pendiente
                assertTrue(transacciones.get(0).contains("usuario1"), "El usuario1 no está involucrado en la transacción"); // La transacción debería involucrar al usuario1
                assertTrue(transacciones.get(0).contains("usuario2"), "El usuario2 no está involucrado en la transacción"); // La transacción debería involucrar al usuario2
            }

        }
    }


}

