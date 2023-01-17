/**
 * 
 */
package com.forrest.cinema.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.forrest.cinema.repos.FileRepository;
import com.forrest.cinema.repos.FileRepositoryImpl;

/**
 * @author martin
 *
 */
@Configuration
public class MyConfig {

    @Bean
    FileRepository fileRepository() {
        return new FileRepositoryImpl();
    }
}
