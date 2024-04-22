package entidades;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestGasto {

    @Test
    public void testIdInvalidoNulo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Gasto(null, 10, "Id_U_Ejemplo", "Id_G_Ejemplo");
        });
    }

    @Test
    public void testIdInvalidoVacio() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Gasto("", 10, "Id_U_Ejemplo", "Id_G_Ejemplo");
        });
    }

    @Test
    public void testCantidadInvalidaCero() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Gasto("asd", 0, "Id_U_Ejemplo", "Id_G_Ejemplo");
        });
    }

    @Test
    public void testCantidadInvalidaNegativa() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Gasto("asd", -1, "Id_U_Ejemplo", "Id_G_Ejemplo");
        });
    }

    @Test
    public void testIdUsuarioInvalidoNulo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Gasto("asd", 10, null, "Id_G_Ejemplo");
        });
    }

    @Test
    public void testIdUsuarioInvalidoVacio() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Gasto("asd", 10, "", "Id_G_Ejemplo");
        });
    }

    @Test
    public void testrIdUsuarioInvalidoCorto() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Gasto("asd", 10, "IDU", "Id_G_Ejemplo");
        });
    }

    @Test
    public void testIdUsuarioInvalidoLargo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Gasto("asd", 10, "ID_G56789012345678901", "Id_G_Ejemplo");
        });
    }

    @Test
    public void testIdGrupoGastoInvalidoNulo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Gasto("asd", 10, "Id_U_Ejemplo", null);
        });
    }

    @Test
    public void testIdGrupoGastoInvalidoVacio() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Gasto("asd", 10, "Id_U_Ejemplo", "");
        });
    }

    @Test
    public void testIdGrupoGastoInvalidoCorto() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Gasto("asd", 10, "Id_U_Ejemplo", "IDG");
        });
    }

    @Test
    public void testIdGrupoGastoInvalidoLargo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Gasto("asd", 10, "Id_U_Ejemplo", "ID_U56789012345678901");
        });
    }

    // Pruebas para casos válidos
    @Test
    public void testValidoAvlIdUsuarioGrupo1() {
        assertDoesNotThrow(() -> {
            new Gasto("asd", 10, "ID_U", "Id_G");
        });
    }
    
    @Test
    public void testValidoAvlIdUsuarioGrupo2() {
        assertDoesNotThrow(() -> {
            new Gasto("asd", 10, "idde20caracteresssss", "ID_G5678901234567890");
        });
    }

}
