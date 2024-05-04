package entidades;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TestIntegracion {
    @Nested
    @DisplayName("CP_I1: INTEGRACION - Casos de prueba de la historia de usuario: Agregar Usuario a Grupo")
    class UnirUsuarioAGrupoTests {
        private Grupo grupo;
        private Usuario usuario1;
        private Usuario usuario2;

        @BeforeEach
        void setUp() {
            usuario1 = new Usuario("ID1234", "correo@ejemplo.com", "Abc123..");
            usuario2 = new Usuario("ID5678", "correo2@ejemplo.com", "Cba456..");
            grupo = new Grupo("GrupoPrueba", "Este es un grupo para pruebas", usuario1);
        }

        @Test
        @DisplayName("Prueba de integración de unir usuario a grupo")
        void testUnirUsuarioAGrupo() {

            grupo.agregarUsuario(usuario2);

            // Verificar que el usuario2 esté en el grupo
            Map<Usuario, Double> balances = grupo.getBalances();
            assertTrue(balances.containsKey(usuario2), "El usuario debe estar en el grupo.");
            assertEquals(0.0, balances.get(usuario2), "El balance del usuario debe ser cero inicialmente");

            //Verificar que el usuario2 tiene el grupo en su lista
            assertTrue(usuario2.getGrupos().contains(grupo), "El usuario debe tener el grupo en su lista.");
        }
    }
}
