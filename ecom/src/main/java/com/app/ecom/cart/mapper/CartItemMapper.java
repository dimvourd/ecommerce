package com.app.ecom.cart.mapper;


import com.app.ecom.cart.entity.CartItem;
import com.app.ecom.cart.entity.dto.CartItemResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CartItemMapper {

	@Mapping(target = "totalProductPrice", source = "price")
	@Mapping(target = "totalProductQuantity", source = "quantity")
	@Mapping(target = "productName", source = "product.name")
	@Mapping(target = "productPrice", source = "product.price")
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
	CartItemResponse toCartItemResponse(CartItem cartItem);
}
