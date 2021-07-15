package ru.skorikov.demoKafka.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper
public interface UserMapper {
    // User DTO.
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "referalLink", source = "partnerLink")
    ReferalToPartnerDto userDtoFromUserEntity(UserEntity userEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "region")
    @Mapping(target = "login", source = "userId")
    @Mapping(target = "registrationCode")
    @Mapping(target = "registrationDate")
    @Mapping(target = "keycloackUserId", ignore = true)
    @Mapping(target = "amount", ignore = true)
    @Mapping(target = "partnerLink", source = "referalLink")
    @Mapping(target = "phoneNumber", ignore = true)
    UserEntity userEntityFromUserDto(ReferalToPartnerDto userDto);



}
