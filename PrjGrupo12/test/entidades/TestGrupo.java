package entidades;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class TestGrupo {

	@Nested
	class RegistrarGrupo {
	    @DisplayName("CP2_001: ID null")
	    @Test
	    void testIdNull() {
	        assertThrows(IllegalArgumentException.class, () -> {
	            new Grupo("asd", null, "asd");
	        });
	    }
	
	    @DisplayName("CP2_002: ID vacío")
	    @Test
	    void testIdEmpty() {
	        assertThrows(IllegalArgumentException.class, () -> {
	            new Grupo("asd", "", "asd");
	        });
	    }
	
	    @DisplayName("CP2_003: Título null")
	    @Test
	    void testTituloNull() {
	        assertThrows(IllegalArgumentException.class, () -> {
	            new Grupo(null, "asd", "asd");
	        });
	    }
	
	    @DisplayName("CP2_004: Título vacío")
	    @Test
	    void testTituloEmpty() {
	        assertThrows(IllegalArgumentException.class, () -> {
	            new Grupo("", "asd", "asd");
	        });
	    }
	
	    @DisplayName("CP2_005: Descripción null")
	    @Test
	    void testDescripcionNull() {
	        assertThrows(IllegalArgumentException.class, () -> {
	            new Grupo("asd", "asd", null);
	        });
	    }
	
	    @DisplayName("CP2_006: Descripción vacía")
	    @Test
	    void testDescripcionEmpty() {
	        assertThrows(IllegalArgumentException.class, () -> {
	            new Grupo("asd", "asd", "");
	        });
	    }
	
	    @DisplayName("CP2_007: Todo válido")
	    @Test
	    void testValidInputs() {
	        assertDoesNotThrow(() -> {
	            new Grupo("asd", "asd", "asd");
	        });
	    }
	}

}
