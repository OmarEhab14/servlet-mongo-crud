package com.example.servletmongocrud.configs;

import com.example.servletmongocrud.application.exceptions.InternalServerErrorException;
import com.example.servletmongocrud.application.services.UserService;
import com.example.servletmongocrud.application.services.impl.UserServiceImpl;
import com.example.servletmongocrud.domain.daos.UserDAO;
import com.example.servletmongocrud.infrastructure.daos.UserMongoDAO;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebListener
public class AppInitializer implements ServletContextListener {
    private MongoClient mongoClient;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // initialize database connection
        final String uri = System.getenv("MONGO_URI");
        if (uri == null) {
            log.error("MONGO_URI environment variable not set");
            throw new IllegalStateException("MONGO_URI environment variable is not set");
        }
        try {
            mongoClient = MongoClients.create(uri);
            sce.getServletContext().setAttribute("mongoClient", mongoClient);
        } catch (MongoException e) {
            throw new InternalServerErrorException("Couldn't connect to the database: " + e.getMessage());
        }

        UserDAO userDAO = new UserMongoDAO(mongoClient);
        UserService userService = new UserServiceImpl(userDAO);
        sce.getServletContext().setAttribute("userService", userService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}
