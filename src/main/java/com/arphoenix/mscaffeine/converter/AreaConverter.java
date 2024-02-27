package com.arphoenix.mscaffeine.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.arphoenix.mscaffeine.dto.AreaDTO;
import com.arphoenix.mscaffeine.entity.Area;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AreaConverter {
	private final ModelMapper modelMapper;

	public AreaDTO toDTO(Area area) {
		AreaDTO areaDTO = modelMapper.map(area, AreaDTO.class);
		areaDTO.setStatus(area.getStatus().trim());
		return areaDTO;
	}

	public Area toModel(AreaDTO dto) {
		return modelMapper.map(dto, Area.class);
	}
}