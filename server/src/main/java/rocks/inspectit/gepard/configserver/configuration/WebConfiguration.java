package rocks.inspectit.gepard.configserver.configuration;


import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;

import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.resource.ResourceResolverChain;

import java.util.List;

import static java.util.Objects.nonNull;


@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    // Von jedem Host darauf zugreifen
    @Override
    public void addCorsMappings(final CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOriginPatterns("*");
    }

    // Überschrieben vom WebMvcConfigurer
    // aufruf von Serve Directory
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        this.serveDirectory(registry, "/", "classpath:/static/");
    }

    // Endpoint Patterns
    // Ressource Handlers für diese Endpunkte festlegen
    // Location der Ressourcen festlegen
    // Logik um fallback auf react zu ermöglichen.
    private void serveDirectory(final ResourceHandlerRegistry registry, final String endpoint, final String location) {
        // This would match the following array of patterns
        // ['', '/**]
        // ['/scopes' ,/scopes/**']
        String[] endpointPatterns = endpoint.endsWith("/") ? new String[]{
                endpoint.substring(0, endpoint.length() - 1),
                endpoint,
                endpoint + "**" }
                : new String[] {endpoint, endpoint + "/" + "/**"};
        registry
                // add the patterns
                .addResourceHandler(endpointPatterns)
                // add the resources from /static/[location]
                .addResourceLocations(location.endsWith("/") ? location : location + "/")
                .resourceChain(false)
                // When the static assets are found return them,
                // Else fallback to 404.html and let next handle the rest.
                .addResolver(new PathResourceResolver() {
                    @Override
                    public Resource resolveResource(HttpServletRequest request, @Nonnull String requestPath, @Nonnull List<? extends Resource> locations, @Nonnull ResourceResolverChain chain) {
                        Resource resource = super.resolveResource(request, requestPath, locations, chain);
                        if(nonNull(resource)) {
                            return resource;
                        }
                        return super.resolveResource(request, "/404.html", locations, chain);
                    }
                });
    }

}

