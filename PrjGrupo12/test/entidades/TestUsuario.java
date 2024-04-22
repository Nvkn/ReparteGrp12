package entidades;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TestUsuario {

    @Test
    void CP1_001_IdNull_ThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Usuario(null, "correo@ejemplo.com", "Password2");
        });
        assertTrue(exception.getMessage().contains("El identificador del usuario es inválido"));
    }

    @Test
    void CP1_002_IdEmpty_ThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Usuario(" ", "correo@ejemplo.com", "Password2");
        });
        assertTrue(exception.getMessage().contains("El identificador del usuario es inválido"));
    }

    @Test
    void CP1_003_IdTooShort_ThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("id", "correo@ejemplo.com", "Password2");
        });
        assertTrue(exception.getMessage().contains("El identificador del usuario es inválido"));
    }

    @Test
    void CP1_004_IdTooLong_ThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("iddemasde20caracteres", "correo@ejemplo.com", "Password2");
        });
        assertTrue(exception.getMessage().contains("El identificador del usuario es inválido"));
    }

    @Test
    void CP1_005_CorreoNull_ThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("idcorrecto", null, "Password2");
        });
        assertTrue(exception.getMessage().contains("El correo electrónico no tiene un formato válido"));
    }

    @Test
    void CP1_006_CorreoEmpty_ThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("idcorrecto", " ", "Password2");
        });
        assertTrue(exception.getMessage().contains("El correo electrónico no tiene un formato válido"));
    }

    @Test
    void CP1_007_CorreoInvalidFormat_ThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("idcorrecto", "correosinarroba.com", "Password2");
        });
        assertTrue(exception.getMessage().contains("El correo electrónico no tiene un formato válido"));
    }

    @Test
    void CP1_008_PasswordNull_ThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("idcorrecto", "correo@ejemplo.com", null);
        });
        assertTrue(exception.getMessage().contains("La contraseña proporcionada no es segura"));
    }

    @Test
    void CP1_009_PasswordEmpty_ThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("idcorrecto", "correo@ejemplo.com", " ");
        });
        assertTrue(exception.getMessage().contains("La contraseña proporcionada no es segura"));
    }

    @Test
    void CP1_010_PasswordTooSimple_ThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("idcorrecto", "correo@ejemplo.com", "password");
        });
        assertTrue(exception.getMessage().contains("La contraseña proporcionada no es segura"));
    }

    @Test
    void CP1_011_PasswordMissingDigits_ThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("idcorrecto", "correo@ejemplo.com", "PASSWORD");
        });
        assertTrue(exception.getMessage().contains("La contraseña proporcionada no es segura"));
    }

    @Test
    void CP1_012_PasswordDigitsOnly_ThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("idcorrecto", "correo@ejemplo.com", "12345678");
        });
        assertTrue(exception.getMessage().contains("La contraseña proporcionada no es segura"));
    }

    @Test
    void CP1_013_PasswordTooShort_ThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("idcorrecto", "correo@ejemplo.com", "pP34567 ");
        });
        assertTrue(exception.getMessage().contains("La contraseña proporcionada no es segura"));
    }

    @Test
    void CP1_014_PasswordTooLong_ThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("idcorrecto", "correo@ejemplo.com", "pP3456789012345622222");
        });
        assertTrue(exception.getMessage().contains("La contraseña proporcionada no es segura"));
    }

    @Test
    void CP1_017_PasswordAVLMin_SuccessfulCreation() {
        assertDoesNotThrow(() -> {
            Usuario validUsuario = new Usuario("idcorrecto", "correo@ejemplo.com", "Passwor1");
        });
    }

    @Test
    void CP1_018_PasswordAVLMax_SuccessfulCreation() {
        assertDoesNotThrow(() -> {
            Usuario validUsuario = new Usuario("idcorrecto", "correo@ejemplo.com", "pP3456789012345 ");
        });
    }
}
