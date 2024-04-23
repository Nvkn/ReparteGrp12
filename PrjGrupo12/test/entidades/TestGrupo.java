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
	            new Grupo("Título", null, "Descripción");
	        });
	    }
	
	    @DisplayName("CP2_002: ID vacío")
	    @Test
	    void testIdVacio() {
	        assertThrows(IllegalArgumentException.class, () -> {
	            new Grupo("Título", "", "Descripción");
	        });
	    }
	
	    @DisplayName("CP2_003: ID corto")
	    @Test
	    void testIdIdCorto() {
	        assertThrows(IllegalArgumentException.class, () -> {
	            new Grupo("Título", "IDG", "Descripción");
	        });
	    }
	
	    @DisplayName("CP2_004: ID largo")
	    @Test
	    void testIdIdLargo() {
	        assertThrows(IllegalArgumentException.class, () -> {
	            new Grupo("Título", "ID_G56789012345678901", "Descripción");
	        });
	    }
	
	    @DisplayName("CP2_005: Título null")
	    @Test
	    void testTituloNull() {
	        assertThrows(IllegalArgumentException.class, () -> {
	            new Grupo(null, "ID_G", "Descripción");
	        });
	    }
	
	    @DisplayName("CP2_006: Título vacío")
	    @Test
	    void testTituloVacio() {
	        assertThrows(IllegalArgumentException.class, () -> {
	            new Grupo("", "ID_G", "Descripción");
	        });
	    }
	
	    @DisplayName("CP2_007: Descripción null")
	    @Test
	    void testDescripcionNull() {
	        assertThrows(IllegalArgumentException.class, () -> {
	            new Grupo("Título", "ID_G", null);
	        });
	    }
	
	    @DisplayName("CP2_008: Descripción vacía")
	    @Test
	    void testDescripcionVacia() {
	        assertThrows(IllegalArgumentException.class, () -> {
	            new Grupo("Título", "ID_G", "");
	        });
	    }
	
	    @DisplayName("CP2_009: Todo válido - ID Min")
	    @Test
	    void testValidoIdMin() {
	        assertDoesNotThrow(() -> {
	            new Grupo("Título", "ID_G", "Descripción");
	        });
	    }
	
	    @DisplayName("CP2_010: Todo válido - ID Max")
	    @Test
	    void testValidoIdMax() {
	        assertDoesNotThrow(() -> {
	            new Grupo("Título", "ID_G5678901234567890", "Descripción");
	        });
	    }
	}

}
