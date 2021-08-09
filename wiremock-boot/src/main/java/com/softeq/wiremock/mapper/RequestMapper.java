package com.softeq.wiremock.mapper;

import com.softeq.wiremock.dto.Request;
import com.softeq.wiremock.entity.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RequestMapper {

    RequestMapper INSTANCE = Mappers.getMapper(RequestMapper.class);

    @Mappings({
            @Mapping(source = "name", target = "cityName"),
            @Mapping(source = "currency", target = "currencyAbbreviation")
    })
    Request cityToRequest(City city);
}
