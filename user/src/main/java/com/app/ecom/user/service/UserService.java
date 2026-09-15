package com.app.ecom.user.service;

import com.app.ecom.user.entity.User;
import com.app.ecom.user.entity.dto.UserRequest;
import com.app.ecom.user.entity.dto.UserResponse;
import com.app.ecom.user.entity.dto.UserUpdateRequest;
import com.app.ecom.user.mappers.UserMapper;
import com.app.ecom.user.repository.AddressRepository;
import com.app.ecom.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository repository;
	private final AddressRepository addressRepository;
	private final UserMapper mapper;

	public List<UserResponse> getAllUsers() {
		return repository.findAll().stream()
				.map(mapper::toUserResponse)
				.toList();
	}

	@Transactional
	public UserResponse createUser(UserRequest userRequest) {
		User newUser = mapper.toUser(userRequest);
		repository.save(newUser);

		return mapper.toUserResponse(newUser);
	}

	@Transactional
	public void deleteUser(String id) {

		repository.delete(this.fetchUser(id));
	}

	public UserResponse getUserById(String id) throws RuntimeException {
		return mapper.toUserResponse(this.fetchUser(id));

	}

	@Transactional
	public UserResponse updateUser(@Valid UserUpdateRequest user, String id) {
		User userDB = this.fetchUser(id);
		mapper.updateUserFromRequest(user, userDB);
		repository.save(userDB);
		return mapper.toUserResponse(userDB);
	}

	public User fetchUser(String id) throws RuntimeException {
		return repository.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found: " + id));
	}
}
