package entidades;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

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
                });
            }

            @DisplayName("CP_GR1_02: Título vacío")
            @Test
            void CP_GR1_02() {
                assertThrows(IllegalArgumentException.class, () -> {
                    new Grupo("", "Descripcion", usuarioValido);
                });
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
                });
            }

            @DisplayName("CP_GR1_04: Descripción vacía")
            @Test
            void CP_GR1_04() {
                assertThrows(IllegalArgumentException.class, () -> {
                    new Grupo("Título", "", usuarioValido);
                });
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
                });
            }
        }

        @Nested
        @DisplayName("Clases de equivalencia válidas")
        class CPValidas {
            @DisplayName("CP_GR1_06: Todo válido - ID y Password Min ")
            @Test
            void CP_GR1_06() {
                assertDoesNotThrow(() -> {
                    new Grupo("Título", "Descripción", usuarioValido);
                });
            }
        }
    }

}

