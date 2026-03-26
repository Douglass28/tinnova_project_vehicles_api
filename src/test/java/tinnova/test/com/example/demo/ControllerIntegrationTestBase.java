package tinnova.test.com.example.demo;

import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.http.MediaType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import tinnova.test.com.example.demo.application.integrations.IDollarApiIntegration;
import tinnova.test.com.example.demo.application.persistence.vehicles.VehicleRepository;

@SpringBootTest
@AutoConfigureMockMvc
@Import({TestcontainersConfiguration.class, ControllerIntegrationTestBase.ControllerTestConfiguration.class})
public abstract class ControllerIntegrationTestBase {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected VehicleRepository vehicleRepository;

    @BeforeEach
    void setup() {
        vehicleRepository.deleteAll();
    }

    protected String adminToken() throws Exception {
        return loginAndGetToken("admin", "admin123");
    }

    protected String userToken() throws Exception {
        return loginAndGetToken("user", "user123");
    }

    private String loginAndGetToken(String username, String password) throws Exception {
        String body = objectMapper.writeValueAsString(new LoginPayload(username, password));
        String response = mockMvc.perform(post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();
        return objectMapper.readTree(response).get("accessToken").asText();
    }

    private record LoginPayload(String username, String password) {
    }

    @TestConfiguration
    static class ControllerTestConfiguration {
        @Bean
        @Primary
        ObjectMapper testObjectMapper() {
            return new ObjectMapper();
        }

        @Bean
        @Primary
        IDollarApiIntegration testDollarApiIntegrationForControllers() {
            return () -> new BigDecimal("5.00");
        }
    }
}
