package com.arphoenix.mscaffeine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.arphoenix.mscaffeine.controller.AreaController;
import com.arphoenix.mscaffeine.dto.AreaDTO;
import com.arphoenix.mscaffeine.entity.Area;
import com.arphoenix.mscaffeine.exception.NotFoundException;
import com.arphoenix.mscaffeine.service.AreaService;

class AreaControllerTests {

	@Test
	void getById_ReturnsArea_WhenValidId() throws NotFoundException {
		// Arrange
		String codigoArea = "1";
		Area expectedArea = new Area();
		expectedArea.setCodigoArea(codigoArea);
		AreaService areaService = mock(AreaService.class);
		when(areaService.findById(codigoArea)).thenReturn(expectedArea);
		AreaController controller = new AreaController(areaService);

		// Act
		ResponseEntity<Area> response = controller.getById(codigoArea);

		// Assert
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedArea, response.getBody());
	}

	@Test
	void listAll_ReturnsAllAreas() {
		// Arrange
		List<AreaDTO> expectedAreas = Arrays.asList(new AreaDTO(), new AreaDTO());
		AreaService areaService = mock(AreaService.class);
		when(areaService.findAll()).thenReturn(expectedAreas);
		AreaController controller = new AreaController(areaService);

		// Act
		ResponseEntity<List<AreaDTO>> response = controller.listAll();

		// Assert
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedAreas, response.getBody());
	}

	@Test
	void save_CreatesNewArea() {
		// Arrange
		AreaDTO dto = new AreaDTO();
		AreaDTO expectedSavedArea = new AreaDTO();
		AreaService areaService = mock(AreaService.class);
		when(areaService.save(dto)).thenReturn(expectedSavedArea);
		AreaController controller = new AreaController(areaService);

		// Act
		ResponseEntity<AreaDTO> response = controller.save(dto);

		// Assert
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(expectedSavedArea, response.getBody());
	}

	@Test
	void updateRegistry_UpdatesArea_WhenValidId() throws NotFoundException {
		// Arrange
		AreaDTO dto = new AreaDTO();
		AreaDTO expectedUpdatedArea = new AreaDTO();
		AreaService areaService = mock(AreaService.class);
		when(areaService.update(dto)).thenReturn(expectedUpdatedArea);
		AreaController controller = new AreaController(areaService);

		// Act
		ResponseEntity<AreaDTO> response = controller.updateRegistry(dto);

		// Assert
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedUpdatedArea, response.getBody());
	}

	@Test
	void delete_RemovesArea_WhenValidId() {
		// Arrange
		String codigoArea = "1";
		AreaService areaService = mock(AreaService.class);
		AreaController controller = new AreaController(areaService);

		// Act
		ResponseEntity<String> response = controller.delete(codigoArea);

		// Assert
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Area deleted", response.getBody());
		verify(areaService, times(1)).delete(codigoArea);
	}

	@Test
	void getById_ThrowsNotFoundException_WhenInvalidId() throws NotFoundException {
		// Arrange
		String codigoArea = "1";
		AreaService areaService = mock(AreaService.class);
		when(areaService.findById(codigoArea)).thenThrow(new NotFoundException("Area not found"));
		AreaController controller = new AreaController(areaService);

		// Act & Assert
		assertThrows(NotFoundException.class, () -> controller.getById(codigoArea));
	}
}