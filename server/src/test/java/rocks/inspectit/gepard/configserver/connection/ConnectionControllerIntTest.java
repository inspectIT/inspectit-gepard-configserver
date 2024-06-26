/* (C) 2024 */
package rocks.inspectit.gepard.configserver.connection;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ConnectionController.class)
public class ConnectionControllerIntTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private ConnectionService connectionService;

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
}
