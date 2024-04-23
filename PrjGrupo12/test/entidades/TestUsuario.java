package entidades;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestUsuario {

	@DisplayName("CP1_001: ID nulo")
    @Test
    void CP1_001_IdNull_ThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Usuario(null, "correo@ejemplo.com", "Password2");
        });
        assertTrue(exception.getMessage().contains("El identificador del usuario es inválido"));
    }
	
	@DisplayName("CP1_002: ID vacío")
    @Test
    void CP1_002_IdEmpty_ThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Usuario(" ", "correo@ejemplo.com", "Password2");
        });
        assertTrue(exception.getMessage().contains("El identificador del usuario es inválido"));
    }
	
	@DisplayName("CP1_003: ID corto ")
    @Test
    void CP1_003_IdTooShort_ThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("id", "correo@ejemplo.com", "Password2");
        });
        assertTrue(exception.getMessage().contains("El identificador del usuario es inválido"));
    }

	@DisplayName("CP1_004: ID largo")
    @Test
    void CP1_004_IdTooLong_ThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("iddemasde20caracteres", "correo@ejemplo.com", "Password2");
        });
        assertTrue(exception.getMessage().contains("El identificador del usuario es inválido"));
    }

	@DisplayName("CP1_005: Correo nulo")
    @Test
    void CP1_005_CorreoNull_ThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("idcorrecto", null, "Password2");
        });
        assertTrue(exception.getMessage().contains("El correo electrónico no tiene un formato válido"));
    }

	@DisplayName("CP1_006: Correo vacío ")
    @Test
    void CP1_006_CorreoEmpty_ThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("idcorrecto", " ", "Password2");
        });
        assertTrue(exception.getMessage().contains("El correo electrónico no tiene un formato válido"));
    }

	@DisplayName("CP1_007: Correo con formato inválido ")
    @Test
    void CP1_007_CorreoInvalidFormat_ThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("idcorrecto", "correosinarroba.com", "Password2");
        });
        assertTrue(exception.getMessage().contains("El correo electrónico no tiene un formato válido"));
    }

	@DisplayName("CP1_008: Password nulo ")
    @Test
    void CP1_008_PasswordNull_ThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("idcorrecto", "correo@ejemplo.com", null);
        });
        assertTrue(exception.getMessage().contains("La contraseña proporcionada no es segura"));
    }

	@DisplayName("CP1_009: Password vacío ")
    @Test
    void CP1_009_PasswordEmpty_ThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("idcorrecto", "correo@ejemplo.com", " ");
        });
        assertTrue(exception.getMessage().contains("La contraseña proporcionada no es segura"));
    }

	@DisplayName("CP1_010: Password solo minúsculas ")
    @Test
    void CP1_010_PasswordAllMinus_ThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("idcorrecto", "correo@ejemplo.com", "password");
        });
        assertTrue(exception.getMessage().contains("La contraseña proporcionada no es segura"));
    }
	
	@DisplayName("CP1_011: Password solo mayúsculas ")
    @Test
    void CP1_011_PasswordMissingDigits_ThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("idcorrecto", "correo@ejemplo.com", "PASSWORD");
        });
        assertTrue(exception.getMessage().contains("La contraseña proporcionada no es segura"));
    }

	@DisplayName("CP1_012: Password solo dígitos ")
    @Test
    void CP1_012_PasswordDigitsOnly_ThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("idcorrecto", "correo@ejemplo.com", "12345678");
        });
        assertTrue(exception.getMessage().contains("La contraseña proporcionada no es segura"));
    }

	@DisplayName("CP1_013: Password corta ")
    @Test
    void CP1_013_PasswordTooShort_ThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("idcorrecto", "correo@ejemplo.com", "pP34567 ");
        });
        assertTrue(exception.getMessage().contains("La contraseña proporcionada no es segura"));
    }

	@DisplayName("CP1_014:  Password larga ")
    @Test
    void CP1_014_PasswordTooLong_ThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("idcorrecto", "correo@ejemplo.com", "pP3456789012345622222");
        });
        assertTrue(exception.getMessage().contains("La contraseña proporcionada no es segura"));
    }

	@DisplayName("CP1_015: Todo válido - ID y Password Min ")
    @Test
    void CP1_015_PasswordAVLMin_SuccessfulCreation() {
        assertDoesNotThrow(() -> {
            Usuario validUsuario = new Usuario("id4s", "correo@ejemplo.com", "Passwor1");
        });
    }
	@DisplayName("CP1_016: Todo válido - ID y Password Max ")
    @Test
    void CP1_016_PasswordAVLMax_SuccessfulCreation() {
        assertDoesNotThrow(() -> {
            Usuario validUsuario = new Usuario("idde20caracteresssss ", "correo@ejemplo.com", "pP3456789012345 ");
        });
    }
}
