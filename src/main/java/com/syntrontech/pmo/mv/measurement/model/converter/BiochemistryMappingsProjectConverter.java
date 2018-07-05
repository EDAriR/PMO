package com.syntrontech.pmo.mv.measurement.model.converter;

import java.util.Objects;
import java.util.Optional;

import javax.persistence.AttributeConverter;

import com.syntrontech.measurement.model.common.BiochemistryMappingsProject;

public class BiochemistryMappingsProjectConverter implements AttributeConverter<BiochemistryMappingsProject, String>{

	@Override
	public String convertToDatabaseColumn(BiochemistryMappingsProject attribute) {
		return Optional.ofNullable(attribute)
						.filter(attr -> Objects.nonNull(attr))
						.map(attr -> attr.getProject())
						.orElse(null);
	}

	@Override
	public BiochemistryMappingsProject convertToEntityAttribute(String dbData) {
		return Optional.ofNullable(dbData)
						.filter(db -> Objects.nonNull(db))
						.map(db -> BiochemistryMappingsProject.convertFrom(db))
						.orElse(null);
	}

}
