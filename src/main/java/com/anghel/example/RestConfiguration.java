package com.anghel.example;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RestConfiguration extends RepositoryRestMvcConfiguration {
 
    @Override
    protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.setReturnBodyOnCreate(true);
        config.setReturnBodyOnUpdate(true);
        
        config.exposeIdsFor(Parent.class);
        config.exposeIdsFor(Child.class);
    }
    
    @Override
    public void configureJacksonObjectMapper(ObjectMapper objectMapper) {
        objectMapper.configure(MapperFeature.REQUIRE_SETTERS_FOR_GETTERS, true);
    }
    
}
