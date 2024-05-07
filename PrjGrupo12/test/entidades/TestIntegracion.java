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
    @Nested
    @DisplayName("CP_I2: Casos de prueba de la historia de usuario: Editar Gasto")
    class EditarGastoTest {
        private Grupo grupo;
        private Usuario usuario;

        @BeforeEach
        void setUp() {
        	usuario = new Usuario("Ivalido_2","correovalido@gmail.com","Contraseña_1");
        	grupo=usuario.crearGrupo("Grupo Test", "Descripción Test");
        }
        
        @Test
        @DisplayName("CP_I2_01: Intentar editar un gasto que no existe")
        void CP_I2_01() {
            Grupo grupo2 = new Grupo("Grupo Test", "Descripción Test", usuario);
            Gasto gastoInexistente=usuario.crearGasto(50.0,grupo2);
            assertThrows(IllegalArgumentException.class, () -> grupo.editarGasto(gastoInexistente,40), "Debería lanzarse una excepción indicando que el gasto no pertenece al grupo.");
        }
        @Test
        @DisplayName("CP_I2_02: Editar un gasto nulo")
        void CP_I2_02() {
            assertThrows(IllegalArgumentException.class, () -> grupo.editarGasto(null,20), "Debería lanzarse una excepción indicando que el gasto no puede ser nulo.");
        }

        @Test
        @DisplayName("CP_I2_03: Editar un gasto existente")
        void CP_I2_03() {
        	
        	  // Arrange
            Grupo grupo2 = usuario.crearGrupo("Grupo2", "Segundo grupo");
            Usuario usuarioValido2 = new Usuario("ID1235", "correo@ejemplo.com", "Abc123..");
            grupo2.agregarUsuario(usuarioValido2);
            Gasto g2 = usuario.crearGasto(50.0, grupo2);
            
         // Act
            usuario.editarGasto(g2, 40);
            assertEquals(grupo2.getBalances().get(usuario), 20, "El balance del usuario debería haber sido ajustado a 0.");
            assertEquals(grupo2.getBalances().get(usuarioValido2), -20, "El balance del usuario debería haber sido ajustado a 0.");

        }   
    }
}