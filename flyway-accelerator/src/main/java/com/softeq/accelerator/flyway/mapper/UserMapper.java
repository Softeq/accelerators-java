package com.softeq.accelerator.flyway.mapper;

import com.softeq.accelerator.flyway.dto.UserDto;
import com.softeq.accelerator.flyway.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Maps user related objects
 * <p/>
 * Created on 4/6/2020.
 * <p/>
 *
 * @author slapitsky
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "email", source = "dto.email")
    @Mapping(target = "firstName", source = "dto.firstName")
    @Mapping(target = "lastName", source = "dto.lastName")
    User toEntity(UserDto dto);

    @Mapping(target = "id", source = "user.id")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "firstName", source = "user.firstName")
    @Mapping(target = "lastName", source = "user.lastName")
    UserDto toDto(User user);
}
