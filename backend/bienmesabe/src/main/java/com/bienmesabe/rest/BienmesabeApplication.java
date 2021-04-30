package com.bienmesabe.rest;

import com.bienmesabe.rest.controller.UserController;
import com.bienmesabe.rest.security.JWTAuthorizationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
/**
 * Main method for the application
 * @author RAUL RAMOS CENDRERO
 * @version 11/04/2021
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.bienmesabe.rest")
public class BienmesabeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BienmesabeApplication.class, args);
	}
        @EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
				.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
                                .antMatchers(HttpMethod.GET, "/api/recipe").permitAll()
                                .antMatchers(HttpMethod.GET, "/api/recipe/getRecipes").permitAll()
                                .antMatchers(HttpMethod.GET, "/api/recipe/getRecipesByAssessment").permitAll()
                                .antMatchers(HttpMethod.GET, "/api/recipe/getRecipeByIngredients/{ingredients}").permitAll()
                                .antMatchers(HttpMethod.GET, "/api/recipe/getRecipesByKCal/{kcalMin}/{kcalMax}").permitAll()
                                .antMatchers(HttpMethod.GET, "/api/recipe/getRecipesByType/{type}").permitAll()
                                .antMatchers(HttpMethod.GET, "/api/recipe/getRecipesByDinners/{dinners}").permitAll()
                                .antMatchers(HttpMethod.GET, "/api/recipe/getRecipesById/{id}").permitAll()
                                .antMatchers(HttpMethod.GET, "/api/recipe/getRecipesByFilters/{data}").permitAll()
                                .antMatchers(HttpMethod.GET, "/api/recipe/getRecipesOfOtherUsers/{userId}").permitAll()
                                .antMatchers(HttpMethod.POST, "/api/user/addUser").permitAll()
                                .antMatchers(HttpMethod.POST, "/api/user/loginUser").permitAll()
                                .antMatchers(HttpMethod.POST, "/api/user/loginUser/{data}").permitAll()
                                
                                .antMatchers(HttpMethod.GET, "/api/ingredient/getIngredients").permitAll()
                                .antMatchers(HttpMethod.GET, "/api/recipeTypes/getRecipeTypes").permitAll()
                                .antMatchers(HttpMethod.GET, "/api/recipe/getRecipes").permitAll()
                                .antMatchers(HttpMethod.GET, "/api/user/getUsers").permitAll()
                                .antMatchers(HttpMethod.GET, "/api/recipe/getRecipesByAssessment").permitAll()
                                .antMatchers(HttpMethod.GET, "/api/recipeTypes/getRecipeTypes").permitAll()
                                .antMatchers(HttpMethod.GET, "/api/ingredient/getIngredients").permitAll()
				.anyRequest().authenticated();
		}
	}

}
