package com.business.manager.gateway.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRoutesConfig {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${api.empleado.version:v1}")
    private String empleadoApiVersion;

    @Value("${api.horario.version:v1}")
    private String horarioApiVersion;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        Integer bmHorarioPort  = discoveryClient.getInstances("BMHORARIO").stream().map(ServiceInstance::getPort).findAny().orElse(8001);
        Integer bmEmpleadoPort  = discoveryClient.getInstances("BMEMPLEADO").stream().map(ServiceInstance::getPort).findAny().orElse(8000);

        return builder
                .routes()
                .route("BMEMPLEADO",
                        r ->
                        r.path("/BMEMPLEADO/**")
                                .filters(f ->
                                        f.rewritePath("/BMEMPLEADO/(?<remaining>.*)",
                                                "/"+empleadoApiVersion+"/${remaining}"))
                                .uri("lb://BMEMPLEADO")
                        .metadata("management.port", bmEmpleadoPort)
                )
                .route("BMHORARIO",
                        r ->
                                r.path("/BMHORARIO/**")
                                        .filters(f ->
                                                f.rewritePath("/BMHORARIO/(?<remaining>.*)",
                                                        "/"+horarioApiVersion+"/${remaining}"))
                                        .uri("lb://BMHORARIO")
                                        .metadata("management.port", bmHorarioPort)

                )
                .build();
    }
}
