package rocks.inspectit.gepard.configserver.connection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ConnectionController.class)
public class ConnectionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConnectionService connectionService;

    @Test
    public void connect_whenFieldIsMissing_shouldReturnBadRequest() throws Exception {
        String requestJson = "{\n" +
                "    \"serviceName\": \"customer-service-e\",\n" +
                "    \"pid\": \"67887\",\n" +
                "    \"gepardVersion\": \"0.0.1\",\n" +
                "    \"otelVersion\": \"1.26.8\"\n" +
                "}"; // JSON with lastJVMRestart missing

        mockMvc.perform(post("/api/v1/connection/connect")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isBadRequest());
    }

}
