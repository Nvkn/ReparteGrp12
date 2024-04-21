package entidades;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestUsuario {
    private Usuario usuario;

    @BeforeEach
    void setUp() throws Exception {
        // Inicializamos un usuario con valores válidos antes de cada prueba
        usuario = new Usuario("usuario1", "correo@dominio.com", "Contraseña123!");
    }
    
    @AfterEach
    void tearDown() throws Exception {
    	usuario=null;
    }

    @Test
    void testCrearUsuarioConDatosValidos() {
        assertDoesNotThrow(() -> new Usuario("usuario1", "correo@dominio.com", "Contraseña123!"));
    }

    @Test
    void testCrearUsuarioConIdInvalido() {
        assertThrows(IllegalArgumentException.class, () -> new Usuario("u", "correo@dominio.com", "Contraseña123!"));
    }

    @Test
    void testCrearUsuarioConCorreoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> new Usuario("usuario1", "correo-novalido@", "Contraseña123!"));
    }

    @Test
    void testCrearUsuarioConPasswordInvalido() {
        assertThrows(IllegalArgumentException.class, () -> new Usuario("usuario1", "correo@dominio.com", "pass"));
    }

    @Test
    void testGetId() {
        assertEquals("usuario1", usuario.getId(), "El ID obtenido debe coincidir con el que se ha configurado.");
    }

    @Test
    void testSetIdConIdValido() {
        assertDoesNotThrow(() -> usuario.setId("usuario2"), "Cambiar el ID a un valor válido no debe lanzar excepciones.");
    }

    @Test
    void testSetIdConIdInvalido() {
        assertThrows(IllegalArgumentException.class, () -> usuario.setId("u"), "Cambiar el ID a un valor inválido debe lanzar una excepción.");
    }

    @Test
    void testGetCorreo() {
        assertEquals("correo@dominio.com", usuario.getCorreo(), "El correo obtenido debe coincidir con el que se ha configurado.");
    }

    @Test
    void testSetCorreoConCorreoValido() {
        assertDoesNotThrow(() -> usuario.setCorreo("nuevo@correo.com"), "Cambiar el correo a un valor válido no debe lanzar excepciones.");
    }

    @Test
    void testSetCorreoConCorreoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> usuario.setCorreo("correo-novalido@"), "Cambiar el correo a un valor inválido debe lanzar una excepción.");
    }

    @Test
    void testGetPassword() {
        assertEquals("Contraseña123!", usuario.getPassword(), "La contraseña obtenida debe coincidir con la que se ha configurado.");
    }

    @Test
    void testSetPasswordConPasswordValida() {
        assertDoesNotThrow(() -> usuario.setPassword("NuevaContraseña123!"), "Cambiar la contraseña a un valor válido no debe lanzar excepciones.");
    }

    @Test
    void testSetPasswordConPasswordInvalida() {
        assertThrows(IllegalArgumentException.class, () -> usuario.setPassword("pass"), "Cambiar la contraseña a un valor inválido debe lanzar una excepción.");
    }
}
