package entidades;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import java.util.Map;
import java.util.List;


class TestGrupo {
    static Usuario usuarioValido;

    @BeforeAll
    static void setUp() {
        usuarioValido = new Usuario("ID1234", "correo@ejemplo.com", "Abc123..");
    }

    @Nested
    @DisplayName("CP_GR1: Casos de prueba del constructor")
    class ConstructorTest {
        @Nested
        @DisplayName("Clases de equivalencia no válidas de: Título")
        class CPTitulo {
            @DisplayName("CP_GR1_01: Título nulo")
            @Test
            void CP_GR1_01() {
                assertThrows(IllegalArgumentException.class, () -> {
                    new Grupo(null, "Descripcion", usuarioValido);
                }, "El Titulo no es nulo.");
            }

            @DisplayName("CP_GR1_02: Título vacío")
            @Test
            void CP_GR1_02() {
                assertThrows(IllegalArgumentException.class, () -> {
                    new Grupo("", "Descripcion", usuarioValido);
                }, "El Titulo no está vacío.");
            }
        }

        @Nested
        @DisplayName("Clases de equivalencia no válidas de: Descripcion")
        class CPDescripcion {
            @DisplayName("CP_GR1_03: Descripción nula")
            @Test
            void CP_GR1_03() {
                assertThrows(IllegalArgumentException.class, () -> {
                    new Grupo("Título", null, usuarioValido);
                }, "La descripción no es nula.");
            }

            @DisplayName("CP_GR1_04: Descripción vacía")
            @Test
            void CP_GR1_04() {
                assertThrows(IllegalArgumentException.class, () -> {
                    new Grupo("Título", "", usuarioValido);
                }, "La descripción no está vacía.");
            }
        }

        @Nested
        @DisplayName("Clases de equivalencia no válidas de: Admin")
        class CPAdmin {
            @DisplayName("CP_GR1_05: Admin null")
            @Test
            void CP_GR1_05() {
                assertThrows(IllegalArgumentException.class, () -> {
                    new Grupo("Título", "Descripción", null);
                }, "El admin no es nulo.");
            }
        }

        @Nested
        @DisplayName("Clases de equivalencia válidas")
        class CPValidas {
            @DisplayName("CP_GR1_06: Todo válido - ID y Password Min")
            @Test
            void CP_GR1_06() {
                assertDoesNotThrow(() -> {
                    new Grupo("Título", "Descripción", usuarioValido);
                }, "El caso no es válido.");
            }
        }
    }
    
    @Nested
    @DisplayName("CP_GR2: Casos de prueba de repartir gastos")
    class RepartirGastosTest {
        private Grupo grupo;
        private Usuario usuario1;
        private Usuario usuario2;
        private Usuario usuario3;

        @BeforeEach
        public void setUp() {
            usuario1 = mock(Usuario.class);
            usuario2 = mock(Usuario.class);

            // Configurar comportamiento de los usuarios simulados
            when(usuario1.getId()).thenReturn("usuario1");
            when(usuario2.getId()).thenReturn("usuario2");

            grupo = new Grupo("Grupo de prueba", "Descripción de prueba", usuario1);
            grupo.agregarUsuario(usuario2);
        }
        
        @Nested
        @DisplayName("CP_GR2_1: Repartir gasto")
        class CPRepartirGasto{
        	@Test
            @DisplayName("CP_GR2_1_1: Repartir gasto")
            void CP_GR2_01() {
                // Configurar gasto simulado
                Gasto gasto = mock(Gasto.class);
                when(gasto.getCantidad()).thenReturn(20.0); // Simular un gasto de 100 unidades
                when(gasto.getUsuario()).thenReturn(usuario1); // Simular usuario1 como pagador del gasto

                // Llamar al método repartirGasto(), interno en agregarGasto
                grupo.agregarGasto(gasto);

                // Verificar que se haya actualizado correctamente el balance de los usuarios
                Map<Usuario, Double> balances = grupo.getBalances();
                double expectedBalanceUsuario1 = 10.0; // Usuario1 es el pagador, por lo que su balance debería disminuir a la mitad
                double expectedBalanceUsuario2 = -10.0; // Los otros usuarios deben asumir la otra mitad del gasto

                assertEquals(expectedBalanceUsuario1, balances.get(usuario1), "El balance de usuario1 no es el esperado");
                assertEquals(expectedBalanceUsuario2, balances.get(usuario2), "El balance de usuario2 no es el esperado");
            }
        }
        
        @Nested
        @DisplayName("CP_GR2_2: Calcular transacciones")
        class CPCalcularTransacciones{
        	@Test
            @DisplayName("CP_GR2_2_01: Devolución 2 usuarios - Simple")
            void CP_GR2_02() {
                // Arrange - Se envía un balance en el que el usuario 2 debe 50€ al usuario 1
                Map<Usuario, Double> balances = grupo.getBalances();
                balances.put(usuario1, 10.0); // Saldo negativo para usuario1
                balances.put(usuario2, -10.0); // Saldo positivo para usuario2

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

