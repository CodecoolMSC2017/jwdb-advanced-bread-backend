package com.codecool.bread.controller;

import com.codecool.bread.AbstractTest;
import com.codecool.bread.Application;
import com.codecool.bread.model.dto.ChangePasswordDto;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
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
public class RegistrationControllerTest extends AbstractTest {


    private JSONObject properReg;
    private JSONObject passwordMisMatchReg;
    private JSONObject usernameExistReg;
    private ChangePasswordDto changePassword;

    @Before
    public void createMockRegisters() {
        properReg = new JSONObject(Map.ofEntries(
                entry("username", "lakatos"),
                entry("password", "password"),
                entry("confirmationPassword", "password")));

        passwordMisMatchReg = new JSONObject(Map.ofEntries(
                entry("username", "lakatos"),
                entry("password", "password"),
                entry("confirmationPassword", "notpassword")));

        usernameExistReg = new JSONObject(Map.ofEntries(
                entry("username", "robking"),
                entry("password", "password"),
                entry("confirmationPassword", "password")));
    }

    @Before
    public void createPasswordChangeMap() {
        String[] authorities = {"ROLE_USER"};
        changePassword = new ChangePasswordDto("stan","asd",
                "asd",authorities);
    }

    @Test
    public void testA_givenWac_whenServletContext_thenItProvidesEmployeeController() {
        ServletContext servletContext = webappContext.getServletContext();
        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(webappContext.getBean("registrationController"));
    }


    @Test
    public void testB_ShouldReturnEmployee() throws Exception {
        this.mvc.perform(get("/register").param("employeeId", "8")
                .param("restaurantId", "1"))
                .andDo(print()).andExpect(mvcMatcher.status().isOk())
                .andExpect(mvcMatcher.content().contentType("application/json;charset=UTF-8"))
                .andExpect(mvcMatcher.jsonPath("$.id", is(8)));
    }

    @Test
    public void testC_ReturnsEmployeeNotFoundException_Not_FOUND() throws Exception {
        this.mvc.perform(get("/register").param("employeeId", "1")
                .param("restaurantId", "1"))
                .andDo(print()).andExpect(mvcMatcher.status().isNotFound());
    }

    @Test
    public void testD_ReturnsUsernameAlreadyExistException_CONFLICT() throws Exception {
        this.mvc.perform(put("/register/{employeeId}", 8)
                .content(usernameExistReg.toString())
                .contentType("application/json;charset=UTF-8"))
                .andDo(print()).andExpect(mvcMatcher.status().isConflict());
    }

    @Test
    public void testE_ReturnsPasswordNotMatchException_CONFLICT() throws Exception {
        this.mvc.perform(put("/register/{employeeId}", 8)
                .content(passwordMisMatchReg.toString())
                .contentType("application/json;charset=UTF-8"))
                .andDo(print()).andExpect(mvcMatcher.status().isConflict());
    }

    @Test
    public void testF_ShouldReturnEmployee() throws Exception {
        this.mvc.perform(put("/register/{employeeId}", 8)
                .content(properReg.toString())
                .contentType("application/json;charset=UTF-8"))
                .andDo(print()).andExpect(mvcMatcher.status().isOk())
                .andExpect(mvcMatcher.jsonPath("$.id", is(8)))
                .andExpect(mvcMatcher.jsonPath("$.email", is("notregistered@gmail.com")));
    }

    @Test
    public void testG_ShouldReturnUserForChangePassword() throws Exception {
        this.mvc.perform(get("/changepw").param("userId", "5")
                .param("username", "stan"))
                .andDo(print()).andExpect(mvcMatcher.status().isOk())
                .andExpect(mvcMatcher.content().contentType("application/json;charset=UTF-8"))
                .andExpect(mvcMatcher.jsonPath("$.id", is(5)));
    }

    @Test
    public void testH_ShouldReturnUser_PasswordChangeSuccess() throws Exception {
        this.mvc.perform(put("/changepw")
                .content(asJsonString(changePassword))
                .contentType("application/json;charset=UTF-8"))
                .andDo(print()).andExpect(mvcMatcher.status().isOk())
                .andExpect(mvcMatcher.jsonPath("$.id", is(5)))
                .andExpect(mvcMatcher.jsonPath("$.username", is("stan")));
    }

}