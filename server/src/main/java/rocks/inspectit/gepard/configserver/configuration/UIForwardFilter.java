/* (C) 2024 */
package rocks.inspectit.gepard.configserver.configuration;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Filter for forwarding all requests which URI is on a folder on the /ui/ endpoint to be forwarded
 * to its 'index.html' file. Example: /ui/example/ => /ui/example/index.html
 */
@Component
@Log
@RequestMapping("/")
public class UIForwardFilter implements Filter {

  /**
   * Redirects the root path to the /ui/ endpoint.
   *
   * @param model the model
   * @return the model and view
   */
  @GetMapping
  public ModelAndView redirectRoot(Model model) {
    RedirectView redirectView = new RedirectView("/ui/");
    redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
    return new ModelAndView(redirectView);
  }

  /**
   * Forwards the request to the 'index.html' file if the request URI is a folder.
   *
   * @param request the ServletRequest
   * @param response the ServletResponse
   * @param chain the FilterChain
   * @throws IOException the io exception
   * @throws ServletException the servlet exception
   */
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    String requestURI = ((HttpServletRequest) request).getRequestURI();

    if (requestURI.startsWith("/ui/") && requestURI.endsWith("/")) {
      String forwardURI = requestURI + "index.html";
      log.info("Forwarding '" + requestURI + "' to '" + forwardURI + "'");
      request.getRequestDispatcher(forwardURI).forward(request, response);
    } else {
      chain.doFilter(request, response);
    }
  }
}
