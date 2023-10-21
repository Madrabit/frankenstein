package ru.madrabit.frankenstein.integration.http.Controller;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.madrabit.frankenstein.database.entity.Roles;
import ru.madrabit.frankenstein.integration.IntegrationTestBase;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.madrabit.frankenstein.dto.UserCreateEditDto.Fields.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@AutoConfigureMockMvc
@RequiredArgsConstructor
class UserControllerTest extends IntegrationTestBase {

    private final MockMvc mockMvc;

//    @BeforeEach
//    void init() {
////        List<GrantedAuthority> roles = Arrays.asList(Roles.ADMIN, Roles.USER);
////        User testUser = new User("test@gmail.com", "test", roles);
////        TestingAuthenticationToken authenticationToken = new TestingAuthenticationToken(testUser,
////                testUser.getPassword(), roles);
////        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
////        securityContext.setAuthentication(authenticationToken);
////        SecurityContextHolder.setContext(securityContext);
//    }


    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/users")
                .with(user("test@gmail.com").authorities(Roles.ADMIN)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("user/users"))
                .andExpect(model().attributeExists("users"));
    }

    @Test
    void findById() {
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/users")
                        .param(username, "test@gmail.com")
                        .param(firstname, "test")
                        .param(lastname, "test")
                        .param(role, "ADMIN")
                        .param(companyId, "1")
                        .param(birthDate, "2000-01-01"))
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrlPattern("/users/{\\d+}")
                );
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}