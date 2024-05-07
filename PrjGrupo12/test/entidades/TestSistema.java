package entidades;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSistema {
    @Nested
    @DisplayName("CP_ACEPTACION: Prueba de aceptación del segundo sprint")
    class AceptacionSegundoSprint {
        private Usuario eva, luis, marta, juan;
        private Grupo grupo;
        private Gasto ge1, gl1, gm1, gj1, ge2, gl2, ge3;

        @BeforeEach
        void setUp() {
            // Crear usuarios
            eva = new Usuario("@eva", "user@em.com", "1234p56P");
            luis = new Usuario("@luis", "user@em.com", "1234p56P");
            marta = new Usuario("@marta", "user@em.com", "1234p56P");
            juan = new Usuario("@juan", "user@em.com", "1234p56P");

            // Crear grupo
            grupo = juan.crearGrupo("LosCuatro", "Cuatro colegas");
            grupo.agregarUsuario(eva);
            grupo.agregarUsuario(luis);
            grupo.agregarUsuario(marta);

            // Crear gastos
            ge1 = eva.crearGasto(11.30, grupo);
            gl1 = luis.crearGasto(12.0, grupo);
            gm1 = marta.crearGasto(20.22, grupo);
            gj1 = juan.crearGasto(5.75, grupo);
            ge2 = eva.crearGasto(23.15, grupo);
            gl2 = luis.crearGasto(17.49, grupo);
            ge3 = eva.crearGasto(2.05, grupo);
        }

        @Test
        @DisplayName("Prueba de aceptación del segundo sprint")
        void PruebaAceptacionTest() {
            testCreacionUsuarios();
            testCreacionGastos();
            testBalances();
            EditarGastoLuisTest();
            EliminarGastoEvaTest();
        }

        @Disabled
        @Test
        @DisplayName("Prueba de creación correcta de usuarios")
        void testCreacionUsuarios() {
            Integer usuariosEsperados = 4;
            assertEquals(usuariosEsperados, grupo.getBalances().size(), "El número de usuarios no es el esperado.");
            // Comprobar que los usuarios están en el grupo - Asumimos misma funcionalidad para el resto de gastos
            assertTrue(grupo.getBalances().containsKey(eva), "El usuario eva no está en el grupo.");
        }

        @Disabled
        @Test
        @DisplayName("Prueba de creación correcta de gastos")
        void testCreacionGastos() {
            Integer gastosEsperados = 7;
            assertEquals(gastosEsperados, grupo.getGastos().size(), "El número de gastos no es el esperado.");

            // Comprobar que los gastos están en el grupo - Asumimos misma funcionalidad para el resto de gastos
            assertTrue(grupo.getGastos().contains(ge1), "El primer gasto de eva no está en el grupo.");
        }

        @Disabled
        @Test
        @DisplayName("Prueba de balances correctos")
        void testBalances() {
            Double balanceEva = 13.51;
            Double balanceLuis = 6.50;
            Double balanceMarta = -2.77;
            Double balanceJuan = -17.24;
            assertEquals(balanceEva, grupo.getBalances().get(eva), 0.01, "El balance de eva no es el esperado.");
            assertEquals(balanceLuis, grupo.getBalances().get(luis), 0.01, "El balance de luis no es el esperado.");
            assertEquals(balanceMarta, grupo.getBalances().get(marta), 0.01, "El balance de marta no es el esperado.");
            assertEquals(balanceJuan, grupo.getBalances().get(juan), 0.01, "El balance de juan no es el esperado.");
        }

        @Disabled
        @Test
        @DisplayName("Prueba de edición de gasto de Luis")
        void testEditarGasto() {
            Double nuevaCantidad = 12.49;
            luis.editarGasto(gl2, nuevaCantidad);
            assertEquals(nuevaCantidad, gl2.getCantidad(), 0.01, "La cantidad del gasto no fue editada correctamente.");
        }

        @Disabled
        @Test
        @DisplayName("Prueba de eliminación de gasto de Eva")
        void testEliminarGasto() {
            eva.eliminarGasto(ge3);
            assertFalse(grupo.getGastos().contains(ge3), "El gasto no fue eliminado correctamente.");
        }

        @Disabled
        @Test
        @DisplayName("Segunda parte de la prueba de aceptación: Editar gasto Luis")
        void EditarGastoLuisTest() {
            //Arrange
            Double valorInicial = 17.49;
            Double valorFinal = 12.49;
            Double diferencia = valorInicial - valorFinal;
            Double diferenciaTotal = diferencia / 4;

            Double balanceEva = grupo.getBalances().get(eva) + diferenciaTotal;
            Double balanceLuis = grupo.getBalances().get(luis) + diferenciaTotal - diferencia;
            Double balanceMarta = grupo.getBalances().get(marta) + diferenciaTotal;
            Double balanceJuan = grupo.getBalances().get(juan) + diferenciaTotal;

            //Act
            testEditarGasto();

            //Assert
            assertEquals(balanceEva, grupo.getBalances().get(eva), 0.01, "El balance de eva no es el esperado.");
            assertEquals(balanceLuis, grupo.getBalances().get(luis), 0.01, "El balance de luis no es el esperado.");
            assertEquals(balanceMarta, grupo.getBalances().get(marta), 0.01, "El balance de marta no es el esperado.");
            assertEquals(balanceJuan, grupo.getBalances().get(juan), 0.01, "El balance de juan no es el esperado.");
        }

        @Disabled
        @Test
        @DisplayName("Segunda parte de la prueba de aceptación: Editar gasto Luis")
        void EliminarGastoEvaTest() {
            //Arrange
            Double valorInicial = 2.05;
            Double diferenciaTotal = valorInicial / 4;

            Double balanceEva = grupo.getBalances().get(eva) + diferenciaTotal - valorInicial;
            Double balanceLuis = grupo.getBalances().get(luis) + diferenciaTotal;
            Double balanceMarta = grupo.getBalances().get(marta) + diferenciaTotal;
            Double balanceJuan = grupo.getBalances().get(juan) + diferenciaTotal;

            //Act
            testEliminarGasto();

            //Assert
            assertEquals(balanceEva, grupo.getBalances().get(eva), 0.01, "El balance de eva no es el esperado.");
            assertEquals(balanceLuis, grupo.getBalances().get(luis), 0.01, "El balance de luis no es el esperado.");
            assertEquals(balanceMarta, grupo.getBalances().get(marta), 0.01, "El balance de marta no es el esperado.");
            assertEquals(balanceJuan, grupo.getBalances().get(juan), 0.01, "El balance de juan no es el esperado.");
        }
    }
}
