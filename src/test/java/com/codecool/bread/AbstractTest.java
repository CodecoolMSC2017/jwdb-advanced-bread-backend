package com.codecool.bread;

import javax.annotation.Resource;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class AbstractTest {

    @Resource
    private FilterChainProxy springSecurityFilterChain;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    protected WebApplicationContext webappContext;

    @Autowired
    protected DataSource dataSource;

    protected MockMvc mvc;

    protected MockHttpSession session;

    protected MockMvcResultMatchers mvcMatcher;

    protected UsernamePasswordAuthenticationToken principal;

    //protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    //Returns Authentication token to the mock Session
    private UsernamePasswordAuthenticationToken getPrincipal(String username) {

        UserDetails user = this.userDetailsService.loadUserByUsername(username);

        return new UsernamePasswordAuthenticationToken(
                        user,
                        user.getPassword(),
                        user.getAuthorities());
    }

    protected static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Before
    public void setupMockMvc() throws NamingException {

        // setup mock MVC
        this.mvc = MockMvcBuilders
                .webAppContextSetup(this.webappContext)
                .addFilters(this.springSecurityFilterChain)
                .build();
    }

    @Before
    public void signIn() {
        principal = getPrincipal("robking");
        session = new MockHttpSession();
        session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, new MockSecurityContext(principal));
    }

    @Test
    public void signedIn() throws Exception {
        this.mvc.perform(get("/auth")
                .principal(principal)
                .session(session))
                .andExpect(mvcMatcher.status().isOk());
    }
}