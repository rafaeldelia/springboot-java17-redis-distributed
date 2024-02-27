package com.arphoenix.mscaffeine.entity;

import java.io.Serializable;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.data.redis.core.index.Indexed;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CRP_AREA")
public class Area implements Serializable {

	private static final long serialVersionUID = -2898263862438521483L;

	private static final AtomicLong counter = new AtomicLong(0);

	@Transient
	private final String id = generateUniqueId(); // Gerando um ID único

	@Id
	@Indexed
	@Column(name = "area")
	private String codigoArea;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "fl_Ativo")
	private String status;

	private String generateUniqueId() {
		return Instant.now().toEpochMilli() + "-" + counter.incrementAndGet();
	}
}