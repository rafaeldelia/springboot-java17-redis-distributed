package com.arphoenix.mscaffeine.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.arphoenix.mscaffeine.entity.Area;
import com.arphoenix.mscaffeine.exception.NotFoundException;

@Repository
public interface AreaJpaRepository extends JpaRepository<Area, String> {

	@Transactional(readOnly = true, noRollbackFor = NotFoundException.class)
	Optional<Area> findByCodigoAreaIgnoreCase(String codigoArea);
}