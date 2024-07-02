/* (C) 2024 */
package rocks.inspectit.gepard.configserver.connection.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.NoSuchElementException;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import rocks.inspectit.gepard.configserver.connection.service.UIConnectionService;

@WebMvcTest(UIConnectionController.class)
public class UIConnectionControllerIntTest {
  @Autowired private MockMvc mockMvc;

  @MockBean private UIConnectionService uiConnectionService;

  @Test
  public void GET_connections_shouldReturnOk() throws Exception {
    mockMvc.perform(get("/api/v1/connections")).andExpect(status().isOk());
  }

  @Test
  public void GET_connection_shouldReturnNotFound() throws Exception {
    when(uiConnectionService.getConnection(any(UUID.class)))
        .thenThrow(new NoSuchElementException("Connection not found"));
    mockMvc
        .perform(get("/api/v1/connections/00000000-0000-0000-0000-000000000000"))
        .andExpect(status().isNotFound());
  }

  @Test
  public void GET_connection_shouldReturnBadRequest_onUnvalidUUID() throws Exception {
    when(uiConnectionService.getConnection(any(UUID.class)))
        .thenThrow(new IllegalArgumentException("Invalid UUID"));
    mockMvc.perform(get("/api/v1/connections/64536436")).andExpect(status().isBadRequest());
  }

  @Test
  public void GET_connection_shouldReturnOk() throws Exception {
    mockMvc
        .perform(get("/api/v1/connections/00000000-0000-0000-0000-000000000001"))
        .andExpect(status().isOk());
  }
}
