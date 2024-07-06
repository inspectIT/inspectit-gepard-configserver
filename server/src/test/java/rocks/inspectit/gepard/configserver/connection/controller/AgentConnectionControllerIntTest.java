/* (C) 2024 */
package rocks.inspectit.gepard.configserver.connection.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import rocks.inspectit.gepard.configserver.connection.service.AgentConnectionService;

@WebMvcTest(AgentConnectionController.class)
public class AgentConnectionControllerIntTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private AgentConnectionService agentConnectionService;

  @Test
  public void connect_whenFieldIsMissing_shouldReturnBadRequest() throws Exception {
    String requestJson =
        """
                {
                    "serviceName": "customer-service-e",
                    "pid": "67887",
                    "gepardVersion": "0.0.1",
                    "otelVersion": "1.26.8"
                }"""; // JSON with some fields missing

    mockMvc
        .perform(
            post("/api/v1/connections")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void connect_whenFieldIsInvalid_shouldReturnBadRequest() throws Exception {
    String requestJson =
        """
                    {
                        "serviceName": "customer-service-e",
                        "pid": "67887",
                        "gepardVersion": "0.0.1",
                        "otelVersion": "1.26.8",
                        "startTime": "invalid"
                        "javaVersion": "11.0.12"
                    }"""; // JSON with invalid startTime

    mockMvc
        .perform(
            post("/api/v1/connections")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void connect_whenEverythingIsValid_shouldReturnOk() throws Exception {
    String requestJson =
        """
                    {
                        "serviceName": "customer-service-e",
                        "pid": "67887",
                        "gepardVersion": "0.0.1",
                        "otelVersion": "1.26.8",
                        "startTime": 213423,
                        "javaVersion": "11.0.12"
                    }"""; // JSON with all fields

    mockMvc
        .perform(
            post("/api/v1/connections")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
        .andExpect(status().isOk());
  }
}
