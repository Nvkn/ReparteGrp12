package entidades;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class TestGrupo {

	@Nested
	class RegistrarGrupo {
	    @DisplayName("CP2_001: ID is null")
	    @Test
	    void testIdNull() {
	        assertThrows(IllegalArgumentException.class, () -> {
	            new Grupo("asd", null, "asd");
	        });
	    }
	
	    @DisplayName("CP2_002: ID is empty")
	    @Test
	    void testIdEmpty() {
	        assertThrows(IllegalArgumentException.class, () -> {
	            new Grupo("asd", "", "asd");
	        });
	    }
	
	    @DisplayName("CP2_003: Title is null")
	    @Test
	    void testTituloNull() {
	        assertThrows(IllegalArgumentException.class, () -> {
	            new Grupo(null, "asd", "asd");
	        });
	    }
	
	    @DisplayName("CP2_004: Title is empty")
	    @Test
	    void testTituloEmpty() {
	        assertThrows(IllegalArgumentException.class, () -> {
	            new Grupo("", "asd", "asd");
	        });
	    }
	
	    @DisplayName("CP2_005: Description is null")
	    @Test
	    void testDescripcionNull() {
	        assertThrows(IllegalArgumentException.class, () -> {
	            new Grupo("asd", "asd", null);
	        });
	    }
	
	    @DisplayName("CP2_006: Description is empty")
	    @Test
	    void testDescripcionEmpty() {
	        assertThrows(IllegalArgumentException.class, () -> {
	            new Grupo("asd", "asd", "");
	        });
	    }
	
	    @DisplayName("CP2_007: All inputs are valid")
	    @Test
	    void testValidInputs() {
	        assertDoesNotThrow(() -> {
	            new Grupo("asd", "asd", "asd");
	        });
	    }
	}

}
