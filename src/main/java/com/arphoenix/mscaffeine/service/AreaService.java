package com.arphoenix.mscaffeine.service;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arphoenix.mscaffeine.converter.AreaConverter;
import com.arphoenix.mscaffeine.dto.AreaDTO;
import com.arphoenix.mscaffeine.entity.Area;
import com.arphoenix.mscaffeine.exception.NotFoundException;
import com.arphoenix.mscaffeine.repository.AreaJpaRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Classe responsável em gerenciar os services de negócio.
 * 
 * @author rsdelia
 *
 */
@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class AreaService {

	private final AreaJpaRepository repo;

	private final AreaConverter converter;

	/**
	 * Consulta pela chave
	 * 
	 * @param id
	 * @return Registry
	 * @throws NotFoundException
	 */
	@Cacheable(value = "areaCache", key = "'area_' + #area")
	public Area findById(String areaId) throws NotFoundException {
		log.info("Find By Area: {}", areaId);
		return repo.findById(areaId).orElseThrow(() -> new NotFoundException("Erro ao tentar recuperar uma área para atualização"));
	}

	/**
	 * Consulta todos os registros uma vez, depois utiliza o cache. Vai recuperar novamente na base caso tenha uma atualização em algum
	 * registro pelo método update ou save.
	 * 
	 * @return List<Registry>
	 */
	@Cacheable(value = "areaCache", key = "'all'")
	public List<AreaDTO> findAll() {
		log.info("Recuperando no findAll");
		List<Area> list = repo.findAll();
		return list.stream().map(converter::toDTO).toList();
	}

	/**
	 * Cria um registro e libera o cache.
	 * 
	 * @param registry
	 * @return Registry
	 */
	@Async
	@Caching(evict = { @CacheEvict(value = "areaCache", key = "'all'"),
			@CacheEvict(value = "areaCache", key = "'area_' + #areaDTO.codigoArea") })
	@Transactional(rollbackFor = Exception.class)
	public AreaDTO save(AreaDTO areaDTO) {
		log.info("Salvando no save");
		return saveArea(areaDTO);
	}

	/**
	 * Atualiza um registro e libera o cache.
	 * 
	 * @param registry
	 * @return Registry
	 * @throws NotFoundException
	 */
	@Transactional(rollbackFor = Exception.class)
	@Caching(evict = { @CacheEvict(value = "areaCache", key = "'all'"),
			@CacheEvict(value = "areaCache", key = "'area_' + #areaDTO.codigoArea") })
	public AreaDTO update(AreaDTO areaDTO) throws NotFoundException {
		log.info("Atualizando no update");
		// simulando um erro proposital!
		areaDTO = null;
		Optional<Area> opt = buscarArea(areaDTO.getCodigoArea());
		if (opt.isPresent()) {
			return saveArea(areaDTO);
		}
		throw new NotFoundException("Erro ao tentar recuperar uma área para atualização");
	}

	/**
	 * Persiste a Area no banco.
	 * 
	 * @param areaDTO
	 * @return AreaDTO
	 */
	private AreaDTO saveArea(AreaDTO areaDTO) {
		// Adiciona um atraso de 5 segundos
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// Trate a exceção, se necessário
			e.printStackTrace();
		}
		Area area = converter.toModel(areaDTO);
		repo.save(area);
		return converter.toDTO(area);
	}

	/**
	 * Busca a área pelo código (chave)
	 * 
	 * @param codigoArea
	 * @return Optional<Area>
	 */
	private Optional<Area> buscarArea(String codigoArea) {
		log.info("Buscando no buscarArea:{}", codigoArea);
		return repo.findByCodigoAreaIgnoreCase(codigoArea);
	}

	/**
	 * Deleta pela chave que é uma string
	 * 
	 * @param codigoArea
	 */
	@Transactional(rollbackFor = Exception.class)
	@Caching(evict = { @CacheEvict(value = "areaCache", key = "'all'"), @CacheEvict(value = "areaCache", key = "'area_' + #codigoArea") })
	public void delete(String codigoArea) {
		log.info("Deletando o codigo da Area:{}", codigoArea);
		repo.deleteById(codigoArea);
	}
}