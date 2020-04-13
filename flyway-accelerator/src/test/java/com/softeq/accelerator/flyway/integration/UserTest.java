/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.integration;

import com.softeq.accelerator.flyway.TestWebApplication;
import com.softeq.accelerator.flyway.dto.CreateUserDto;
import com.softeq.accelerator.flyway.dto.PageDto;
import com.softeq.accelerator.flyway.dto.SearchUserRequestDto;
import com.softeq.accelerator.flyway.dto.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

/**
 * Tests User related logic
 * <p/>
 * Created on 4/8/2020.
 * <p/>
 *
 * @author slapitsky
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {TestWebApplication.class})
@Profile(TestWebApplication.INTEGRATION_TEST_PROFILE)
public class UserTest extends AbstractIntegrationTest {

    @Test
    public void testGetAllUsersOk() {
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<List<UserDto>> response = template
            .exchange(base + contextPath + "/api/v1/users",
                GET, new HttpEntity<>(null, headers), new ParameterizedTypeReference<List<UserDto>>() {
                });

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        List<UserDto> users = response.getBody();
        assertFalse(users.isEmpty());
    }

    @Test
    public void testGetUserByIdOk() {
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<UserDto> response = template
            .exchange(base + contextPath + "/api/v1/users/1",
                GET, new HttpEntity<>(null, headers), UserDto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        UserDto user = response.getBody();
        assertNotNull(user);
        assertEquals(Integer.valueOf(1), user.getId());
    }

    @Test
    public void testCreateUserOk() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        CreateUserDto request = new CreateUserDto();
        request.setEmail("some@email.test.com");
        request.setFirstName("FFF");
        request.setLastName("LLL");
        ResponseEntity<String> response = template
            .exchange(base + contextPath + "/api/v1/users",
                POST, new HttpEntity<>(request, headers), String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testSearchUsersOk() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        SearchUserRequestDto request = new SearchUserRequestDto();
        request.setEmail("default.admin");
        request.setPage(0);
        request.setSize(20);
        request.setSort("email");
        request.setSortOrder("DESC");
        ResponseEntity<PageDto<UserDto>> response = template
            .exchange(base + contextPath + "/api/v1/users/search",
                POST, new HttpEntity<>(request, headers), new ParameterizedTypeReference<PageDto<UserDto>>() {
                });

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getNumberOfElements());
    }
}
