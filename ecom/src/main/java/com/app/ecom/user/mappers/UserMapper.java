package com.app.ecom.user.mappers;

import com.app.ecom.user.entity.User;
import com.app.ecom.user.entity.dto.UserRequest;
import com.app.ecom.user.entity.dto.UserResponse;
import com.app.ecom.user.entity.dto.UserUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = AddressMapper.class)
public interface UserMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	User toUser(UserRequest userRequest);

	UserResponse toUserResponse(User user);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	@Mapping(target = "address", ignore = true)
	@Mapping(target = "role", ignore = true)
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateUserFromRequest(UserUpdateRequest userUpdateRequest, @MappingTarget User user);
}
