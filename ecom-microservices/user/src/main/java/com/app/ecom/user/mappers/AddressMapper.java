package com.app.ecom.user.mappers;

import com.app.ecom.user.entity.Address;
import com.app.ecom.user.entity.dto.AddressDTO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface AddressMapper {

	AddressDTO toAddressDTO(Address address);

	@Mapping(target = "id", ignore = true)
	Address toAddress(AddressDTO addressDTO);
}
