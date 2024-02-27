package com.arphoenix.mscaffeine.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arphoenix.mscaffeine.dto.AreaDTO;
import com.arphoenix.mscaffeine.entity.Area;
import com.arphoenix.mscaffeine.exception.NotFoundException;
import com.arphoenix.mscaffeine.service.AreaService;

import lombok.RequiredArgsConstructor;

/**
 * Controller principal.
 * 
 * @author rsdelia
 *
 */
@RestController
@RequestMapping("/v1/area")
@RequiredArgsConstructor
public class AreaController {

	private final AreaService jpaService;

	@GetMapping("/{codigoArea}")
	public ResponseEntity<Area> getById(@PathVariable String codigoArea) throws NotFoundException {
		Area retorno = jpaService.findById(codigoArea);
		return new ResponseEntity<>(retorno, HttpStatus.OK);
	}

	@GetMapping(path = "/list-all")
	public ResponseEntity<List<AreaDTO>> listAll() {
		return new ResponseEntity<>(jpaService.findAll(), HttpStatus.OK);
	}

	@Async
	@PostMapping(path = "/save")
	public ResponseEntity<AreaDTO> save(@RequestBody AreaDTO dto) {
		AreaDTO reg = jpaService.save(dto);
		return new ResponseEntity<>(reg, HttpStatus.CREATED);
	}

	@PutMapping(path = "/update")
	public ResponseEntity<AreaDTO> updateRegistry(@RequestBody AreaDTO dto) throws NotFoundException {
		AreaDTO reg = jpaService.update(dto);
		return new ResponseEntity<>(reg, HttpStatus.OK);
	}

	@DeleteMapping(path = "/remove/{codigoArea}")
	public ResponseEntity<String> delete(@PathVariable("codigoArea") String codigoArea) {
		jpaService.delete(codigoArea);
		return new ResponseEntity<>("Area deleted", HttpStatus.OK);
	}
}