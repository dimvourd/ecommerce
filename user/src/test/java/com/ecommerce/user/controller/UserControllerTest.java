//package com.ecommerce.user.controller;
//
//import com.ecommerce.user.dto.AddressDto;
//import com.ecommerce.user.dto.UserRequestDto;
//import com.ecommerce.user.dto.UserResponseDto;
//import com.ecommerce.user.service.UserService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import static org.hamcrest.Matchers.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@Deprecated
//class UserControllerTest {
//
//    private MockMvc mockMvc;
//    private ObjectMapper objectMapper;
//    private UserService userService;
//
//    private UserRequestDto user1Req;
//    private UserRequestDto user2Req;
//
//    private UserResponseDto user1Resp;
//    private UserResponseDto user2Resp;
//
//    @BeforeEach
//    void setUp() {
//        userService = Mockito.mock(UserService.class);
//        UserController controller = new UserController(userService);
//        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
//        objectMapper = new ObjectMapper();
//
//        AddressDto addr1 = AddressDto.builder()
//                .street("123 Main St")
//                .city("Athens")
//                .state("Attica")
//                .postalCode("11111")
//                .country("GR")
//                .build();
//        AddressDto addr2 = AddressDto.builder()
//                .street("456 Side St")
//                .city("Thessaloniki")
//                .state("Central Macedonia")
//                .postalCode("22222")
//                .country("GR")
//                .build();
//
//        user1Req = UserRequestDto.builder()
//                .firstName("Alice")
//                .lastName("Doe")
//                .email("alice@example.com")
//                .address(addr1)
//                .build();
//        user2Req = UserRequestDto.builder()
//                .firstName("Bob")
//                .lastName("Smith")
//                .email("bob@example.com")
//                .address(addr2)
//                .build();
//
//        user1Resp = UserResponseDto.builder()
//                .id(1L)
//                .firstName(user1Req.getFirstName())
//                .lastName(user1Req.getLastName())
//                .email(user1Req.getEmail())
//                .address(addr1)
//                .createdAt(LocalDate.parse("2024-01-01").atStartOfDay())
//                .updatedAt(LocalDate.parse("2024-01-01").atStartOfDay())
//                .build();
//        user2Resp = UserResponseDto.builder()
//                .id(2L)
//                .firstName(user2Req.getFirstName())
//                .lastName(user2Req.getLastName())
//                .email(user2Req.getEmail())
//                .address(addr2)
//		        .createdAt(LocalDate.parse("2024-01-01").atStartOfDay())
//		        .updatedAt(LocalDate.parse("2024-01-01").atStartOfDay())
//                .build();
//    }
//
//    @Test
//    @DisplayName("POST /api/users creates a user and returns 201 with body")
//    void createUser_returns201() throws Exception {
//        Mockito.when(userService.create(any(UserRequestDto.class))).thenReturn(user1Resp);
//
//        mockMvc.perform(post("/api/users")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(user1Req)))
//                .andExpect(status().isCreated())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id", is(1)))
//                .andExpect(jsonPath("$.firstName", is("Alice")))
//                .andExpect(jsonPath("$.email", is("alice@example.com")));
//    }
//
//    @Test
//    @DisplayName("GET /api/users/{id} returns user by id")
//    void getUserById_returns200() throws Exception {
//        Mockito.when(userService.getById(1L)).thenReturn(user1Resp);
//
//        mockMvc.perform(get("/api/users/{id}", 1))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id", is(1)))
//                .andExpect(jsonPath("$.firstName", is("Alice")));
//    }
//
//    @Test
//    @DisplayName("GET /api/users returns a list with two users")
//    void getAllUsers_returnsTwo() throws Exception {
//        Mockito.when(userService.getAll()).thenReturn(List.of(user1Resp, user2Resp));
//
//        mockMvc.perform(get("/api/users"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].email", is("alice@example.com")))
//                .andExpect(jsonPath("$[1].email", is("bob@example.com")));
//    }
//
//    @Test
//    @DisplayName("PUT /api/users/{id} updates and returns 200")
//    void updateUser_returns200() throws Exception {
//        // Prepare updated response for user1
//        AddressDto newAddr = AddressDto.builder()
//                .street("789 New Rd")
//                .city("Patras")
//                .state("Western Greece")
//                .postalCode("33333")
//                .country("GR")
//                .build();
//
//        UserRequestDto updateReq = UserRequestDto.builder()
//                .firstName("Alice")
//                .lastName("Johnson")
//                .email("alice@example.com")
//                .address(newAddr)
//                .build();
//
//        UserResponseDto updatedResp = UserResponseDto.builder()
//                .id(1L)
//                .firstName("Alice")
//                .lastName("Johnson")
//                .email("alice@example.com")
//                .address(newAddr)
//                .createdAt(user1Resp.getCreatedAt())
//                .updatedAt(LocalDate.parse("2024-01-03").atStartOfDay())
//                .build();
//
//        Mockito.when(userService.update(eq(1L), any(UserRequestDto.class))).thenReturn(updatedResp);
//
//        mockMvc.perform(put("/api/users/{id}", 1)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(updateReq)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.lastName", is("Johnson")))
//                .andExpect(jsonPath("$.address.street", is("789 New Rd")));
//    }
//
//    @Test
//    @DisplayName("DELETE /api/users/{id} returns 204 No Content")
//    void deleteUser_returns204() throws Exception {
//        Mockito.doNothing().when(userService).delete(1L);
//
//        mockMvc.perform(delete("/api/users/{id}", 1))
//                .andExpect(status().isNoContent());
//    }
//}
