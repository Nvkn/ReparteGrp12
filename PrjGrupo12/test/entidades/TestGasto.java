package entidades;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TestGasto {

    @DisplayName("CP3_001: ID nulo")
    @Test
    public void testIdInvalidoNulo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Gasto(null, 10, "Id_U_Ejemplo", "Id_G_Ejemplo");
        });
    }

    @DisplayName("CP3_002: ID vacio")
    @Test
    public void testIdInvalidoVacio() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Gasto("", 10, "Id_U_Ejemplo", "Id_G_Ejemplo");
        });
    }
        
    @DisplayName("CP3_003: Cantidad igual a 0")
    @Test
    public void testCantidadInvalidaCero() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Gasto("asd", 0, "Id_U_Ejemplo", "Id_G_Ejemplo");
        });
    }
    
    @DisplayName("CP3_004: ID Usuario nulo")
    @Test
    public void testIdUsuarioInvalidoNulo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Gasto("asd", 10, null, "Id_G_Ejemplo");
        });
    }

    @DisplayName("CP3_005: ID Usuario vacío ")
    @Test
    public void testIdUsuarioInvalidoVacio() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Gasto("asd", 10, "", "Id_G_Ejemplo");
        });
    }

    @DisplayName("CP3_006: ID Usuario con insuficientes caracteres (3) ")
    @Test
    public void testrIdUsuarioInvalidoCorto() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Gasto("asd", 10, "IDU", "Id_G_Ejemplo");
        });
    }

    @DisplayName("CP3_007: ID Usuario con demasiados caracteres (21) ")
    @Test
    public void testIdUsuarioInvalidoLargo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Gasto("asd", 10, "ID_G56789012345678901", "Id_G_Ejemplo");
        });
    }

    @DisplayName("CP3_008: ID Grupo nulo ")
    @Test
    public void testIdGrupoGastoInvalidoNulo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Gasto("asd", 10, "Id_U_Ejemplo", null);
        });
    }

    @DisplayName("CP3_009: ID Grupo vacío")
    @Test
    public void testIdGrupoGastoInvalidoVacio() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Gasto("asd", 10, "Id_U_Ejemplo", "");
        });
    }

    @DisplayName("CP3_010: ID Grupo con insuficientes caracteres (3)")
    @Test
    public void testIdGrupoGastoInvalidoCorto() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Gasto("asd", 10, "Id_U_Ejemplo", "IDG");
        });
    }

    @DisplayName("CP3_011: ID Grupo con demasiados caracteres (21)")
    @Test
    public void testIdGrupoGastoInvalidoLargo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Gasto("asd", 10, "Id_U_Ejemplo", "ID_U56789012345678901");
        });
    }

    @DisplayName("CP3_012: IDs con el mínimo número de caracteres permitido")
    // Pruebas para casos válidos
    @Test
    public void testValidoAvlIdUsuarioGrupo1() {
        assertDoesNotThrow(() -> {
            new Gasto("asd", 0.01, "ID_U", "Id_G");
        });
    }
    
    @DisplayName("CP3_013: IDs con el máximo número de caracteres permitido")
    @Test
    public void testValidoAvlIdUsuarioGrupo2() {
        assertDoesNotThrow(() -> {
            new Gasto("asd", 0.01, "idde20caracteresssss", "ID_G5678901234567890");
        });
    }

}

