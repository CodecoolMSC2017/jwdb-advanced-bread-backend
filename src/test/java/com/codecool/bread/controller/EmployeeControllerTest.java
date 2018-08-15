package com.codecool.bread.controller;

import com.codecool.bread.AbstractTest;
import com.codecool.bread.Application;
import com.codecool.bread.MockSecurityContext;
import com.codecool.bread.model.Address;
import com.codecool.bread.model.Employee;
import com.codecool.bread.model.Role;
import com.codecool.bread.service.RestaurantService;
import com.codecool.bread.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockServletContext;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.ServletContext;

import java.util.Map;

import static java.util.Map.entry;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeControllerTest extends AbstractTest {

    private Employee newEmployee;
    private Employee moddedEmployee;
    private String wrongRegister;
    private String properRegister;

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    UserService userService;

    @Before
    public void mockEmployee() throws JSONException {
        Address address = new Address(0, true, "3 Asd Street", "AsdTown", "4025", "Asd", "Hungary");

        newEmployee = new Employee(0, true, null, "asd@asd.com",
                "Lakatos", "Nintendo",
                Role.BARTENDER, null, address);

        moddedEmployee = new Employee(8, true, null, "notregistered@gmail.com",
                "Not", "Pista", Role.WAITER,
                restaurantService.getById(3), address);

        wrongRegister = new JSONObject(Map.ofEntries(
                entry("username", "murvai"),
                entry("password", "password"),
                entry("confirmationPassword", "password"))).toString();

        properRegister = new JSONObject(Map.ofEntries(
                entry("username", "lakatos"),
                entry("password", "password"),
                entry("confirmationPassword", "password"))).toString();
    }

    @Test
    public void testA_givenWac_whenServletContext_thenItProvidesEmployeeController() {
        ServletContext servletContext = webappContext.getServletContext();
        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(webappContext.getBean("employeeController"));
    }


    @Test
    public void testB_shouldReturnLoggedInOwnersEmployeeSet() throws Exception {
        this.mvc.perform(get("/owner/restaurant/{restaurantId}/employee", "1")
                .principal(principal)
                .session(session))
                .andDo(print()).andExpect(mvcMatcher.status().isOk())
                .andExpect(mvcMatcher.content().contentType("application/json;charset=UTF-8"))
                .andExpect(mvcMatcher.jsonPath("$.*", hasSize(6)));
    }

    @Test
    public void testC_shouldReturnNoEmployeeForRestaurantException_NOT_FOUND() throws Exception {
        this.mvc.perform(get("/owner/restaurant/{restaurantId}/employee", "3")
                .principal(principal)
                .session(session))
                .andDo(print()).andExpect(mvcMatcher.status().isNotFound());
    }

    @Test
    public void testD_ShouldReturnAnEmployee() throws Exception {
        this.mvc.perform(get("/owner/restaurant/{restaurantId}/employee/{employeeId}", 1, 1)
                .principal(principal)
                .session(session))
                .andDo(print()).andExpect(mvcMatcher.status().isOk())
                .andExpect(mvcMatcher.content().contentType("application/json;charset=UTF-8"))
                .andExpect(mvcMatcher.jsonPath("$.id", is(1)))
                .andExpect(mvcMatcher.jsonPath("$.email", is("andrew.fuller@gmail.com")));
    }

    @Test
    public void testE_ShouldReturnEmployeeNotFoundException_NOT_FOUND() throws Exception {
        this.mvc.perform(get("/owner/restaurant/{restaurantId}/employee/{employeeId}", 1, 25)
                .principal(principal)
                .session(session))
                .andDo(print()).andExpect(mvcMatcher.status().isNotFound());
    }

    @Test
    public void testF_shouldAddNewEmployeeToRestaurant() throws Exception {
        this.mvc.perform(post("/owner/restaurant/{restaurantId}/employee", 1)
                .principal(principal)
                .session(session)
                .content(asJsonString(newEmployee))
                .contentType("application/json;charset=UTF-8"))
                .andDo(print()).andExpect(mvcMatcher.status().isOk())
                .andExpect(mvcMatcher.content().contentType("application/json;charset=UTF-8"))
                .andExpect(mvcMatcher.jsonPath("$.id", is(8)))
                .andExpect(mvcMatcher.jsonPath("$.email", is("asd@asd.com")));
    }

    @Test
    public void testG_shouldReturnUsernameIsTaken_CONFLICT() throws Exception {
        this.mvc.perform(put("/owner/restaurant/{restaurantId}/employee/{employeeId}/addusername", 1, 8)
                .principal(principal)
                .session(session)
                .content(wrongRegister)
                .contentType("application/json;charset=UTF-8"))
                .andDo(print()).andExpect(mvcMatcher.status().isConflict());
        /*
                .andExpect(mvcMatcher.content().contentType("application/json;charset=UTF-8"))
                .andExpect(mvcMatcher.jsonPath("$.id", is(8)))
                .andExpect(mvcMatcher.jsonPath("$.email", is("murvai.gergely@gmail.com")));
                */
    }

    @Test
    public void testH_shouldAddUserToEmployee() throws Exception {
        this.mvc.perform(put("/owner/restaurant/{restaurantId}/employee/{employeeId}/addusername", 1, 8)
                .principal(principal)
                .session(session)
                .content(properRegister)
                .contentType("application/json;charset=UTF-8"))
                .andDo(print()).andExpect(mvcMatcher.status().isOk())
                .andExpect(mvcMatcher.content().contentType("application/json;charset=UTF-8"))
                .andExpect(mvcMatcher.jsonPath("$.id", is(8)));

    }

    @Test
    public void testI_shouldModifyEmployeeLastName() throws Exception {
        this.mvc.perform(put("/owner/restaurant/{restaurantId}/employee/{employeeId}", 1, 8)
                .principal(principal)
                .session(session)
                .content(asJsonString(moddedEmployee))
                .contentType("application/json;charset=UTF-8"))
                .andDo(print()).andExpect(mvcMatcher.status().isOk())
                .andExpect(mvcMatcher.jsonPath("$.lastName", is("Pista")));
    }

    @Test
    public void testJ_shouldReturnRestaurantAccessDeniedException_FORBIDDEN() throws Exception {
        this.mvc.perform(put("/owner/restaurant/{restaurantId}/employee/{employeeId}", 3, 7)
                .principal(principal)
                .session(session)
                .content(asJsonString(moddedEmployee))
                .contentType("application/json;charset=UTF-8"))
                .andDo(print()).andExpect(mvcMatcher.status().isForbidden());
    }
}
