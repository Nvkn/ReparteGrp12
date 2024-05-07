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
        @DisplayName("CP_I2_03: Editar un gasto VALIDO")
        void CP_I2_03() {
            Gasto gasto = usuario.crearGasto(50.0, grupo);
            assertEquals(50.0, gasto.getCantidad(), "El gasto debería ser el inicial.");
            double nuevaCantidad = 20.0;
        	grupo.editarGasto(gasto,nuevaCantidad);
            assertEquals(nuevaCantidad, gasto.getCantidad(), "El gasto debería haber sido editado a la nueva cantidad.");
        }

        @Test
        @DisplayName("CP_I2_04: CAJA BLANCA - Editar un gasto que no pertenece a niguno de los grupos del usuario")
        void CP_I2_04() {
        	Usuario usuario2 = new Usuario("Ivalidsks","correovalido@gmail.com","Contraseña_2");
            Grupo grupo2 = new Grupo("Grupo Test", "Descripción Test", usuario2);
            Gasto gastoInexistente=usuario.crearGasto(50.0,grupo2);
            assertThrows(IllegalArgumentException.class, () -> usuario.editarGasto(gastoInexistente,40), "Debería lanzarse una excepción indicando que el gasto no pertenece a ningun grupo del usuario.");
        }
        @Test
        @DisplayName("CP_I2_05: CAJA BLANCA - Editar un gasto nulo desde Usuario")
        void CP_I2_05() {
            assertThrows(IllegalArgumentException.class, () -> usuario.editarGasto(null,20), "Debería lanzarse una excepción indicando que el gasto no puede ser nulo.");
        }
        @Test
        @DisplayName("CP_I2_06: CAJA BLANCA - Editar un gasto que no pertenece al usuario")
        void CP_I2_06() {
        	Usuario usuario2 = new Usuario("Ivalidsks","correovalido@gmail.com","Contraseña_2");
        	Gasto gasto= usuario2.crearGasto(10, grupo);
            assertThrows(IllegalArgumentException.class, () -> usuario.editarGasto(gasto,40), "Debería lanzarse una excepción indicando que el gasto no pertenece al usuario.");

        }
        @Test
        @DisplayName("CP_I2_07: CAJA BLANCA - Editar un gasto desde Usuario VALIDO")
        void CP_I2_07() {
        	Gasto gasto=usuario.crearGasto(10, grupo);
        	assertEquals(10,gasto.getCantidad(),"El gasto debería de ser el inicial");
        	double nuevaCantidad = 20.0;
        	usuario.editarGasto(gasto, nuevaCantidad);
            assertEquals(nuevaCantidad, gasto.getCantidad(), "El gasto debería haber sido editado a la nueva cantidad.");
            
        }
    }
}