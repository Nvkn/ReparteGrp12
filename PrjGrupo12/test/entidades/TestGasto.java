package entidades;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestGasto {
    Usuario usuarioValido;
    Grupo grupoValido;

    @BeforeEach
    void setUp() {
        usuarioValido = new Usuario("ID1234", "correo@ejemplo.com", "Abc123..");
        grupoValido = new Grupo("LosCuatro", "Cuatro amigos", usuarioValido);
    }

    @Nested
    @DisplayName("CP_GA1: Casos de prueba del constructor")
    class ConstructorTest {
        @Nested
        @DisplayName("Clases de equivalencia no válidas de: Cantidad")
        class CPCantidad {
            @DisplayName("CP_GR1_01: Cantidad 0")
            @Test
            void CP_GA1_01() {
                assertThrows(IllegalArgumentException.class, () -> {
                    new Gasto(0, usuarioValido, grupoValido);
                });
            }
        }

        @Nested
        @DisplayName("Clases de equivalencia no válidas de: Usuario")
        class CPUsuario {
            @DisplayName("CP_GA1_02: Usuario nulo")
            @Test
            void CP_GA1_02() {
                assertThrows(IllegalArgumentException.class, () -> {
                    new Gasto(0.01, null, grupoValido);
                });
            }
        }

        @Nested
        @DisplayName("Clases de equivalencia no válidas de: Grupo")
        class CPGrupo {
            @DisplayName("CP_GA1_03: Grupo nulo")
            @Test
            void CP_GA1_03() {
                assertThrows(IllegalArgumentException.class, () -> {
                    new Gasto(0.01, usuarioValido, null);
                });
            }
        }

        @Nested
        @DisplayName("Clases de equivalencia válidas")
        class CPValidas {
            @DisplayName("CP_GA1_04: Todo válido (AVL - cantidad mínima permitida 0.01)")
            @Test
            void CP_GA1_04() {
                assertDoesNotThrow(() -> {
                    new Gasto(0.01, usuarioValido, grupoValido);
                });
            }
        }
    }
}
