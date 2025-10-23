package com.abpro.proyecto.config;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Inicializador de la aplicación web - Reemplaza web.xml
 * Esta clase configura el DispatcherServlet de Spring MVC programáticamente
 *
 * @author Equipo de Desarrollo ABPRO
 */
public class WebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {
        // Crear el contexto de Spring usando configuración basada en anotaciones
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebConfig.class);

        // Crear y registrar el DispatcherServlet
        DispatcherServlet servlet = new DispatcherServlet(context);
        ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcher", servlet);

        registration.setLoadOnStartup(1);
        registration.addMapping("/");

        System.out.println("=================================================");
        System.out.println("Spring MVC REST API - Iniciado correctamente");
        System.out.println("Contexto: /api");
        System.out.println("Puerto: 8080");
        System.out.println("=================================================");
    }
}
