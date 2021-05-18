package com.bienmesabe.rest;

import com.bienmesabe.rest.DAO.UserDAO;
import com.bienmesabe.rest.security.JWTAuthorizationFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Main method for the application
 *
 * @author RAUL RAMOS CENDRERO
 * @version 11/04/2021
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.bienmesabe.rest")
public class BienmesabeApplication {

    @Autowired
    private UserDAO userDAO;

    public static void main(String[] args) {
        SpringApplication.run(BienmesabeApplication.class, args);
    }

    @EnableGlobalMethodSecurity(prePostEnabled = true)
    @EnableWebSecurity
    @Configuration
    class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.cors().and().csrf().disable()
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
                    .antMatchers(HttpMethod.GET, "/api/user/getUserById/{userId}").permitAll()
                    .antMatchers(HttpMethod.POST, "/api/user/addUser").permitAll()
                    .antMatchers(HttpMethod.POST, "/api/user/loginUser").permitAll()
                    .antMatchers(HttpMethod.POST, "/api/user/loginUser/{data}").permitAll()
                    .antMatchers(HttpMethod.GET, "/api/ingredient/getIngredients").permitAll()
                    .antMatchers(HttpMethod.GET, "/api/recipeTypes/getRecipeTypes").permitAll()
                    .antMatchers(HttpMethod.GET, "/api/recipe/getRecipes").permitAll()
                    .antMatchers(HttpMethod.GET, "/api/user/getUsers").permitAll()
                    .antMatchers(HttpMethod.GET, "/api/comment/getCommentsByRecipeId/{id}").permitAll()
                    .antMatchers(HttpMethod.GET, "/api/user/findUserByAlias/{alias}").permitAll()
                    .antMatchers(HttpMethod.GET, "/api/user/findUserByEmail/{email}").permitAll()
                    .antMatchers(HttpMethod.POST, "/api/user/addUser/").permitAll()
                    .antMatchers(HttpMethod.POST, "/api/nutricionist/addNutricionist/").permitAll()
                    .antMatchers(HttpMethod.GET, "/api/recipe/getRecipesByAssessment").permitAll()
                    .antMatchers(HttpMethod.GET, "/api/recipeTypes/getRecipeTypes").permitAll()
                    .antMatchers(HttpMethod.GET, "/api/ingredient/getIngredients").permitAll()
                    .antMatchers(HttpMethod.POST, "/api/comment/addComment/{recipeId}/{comment}/{userId}").permitAll()
                    .antMatchers(HttpMethod.GET, "/api/tokenValidity/validateDate/").permitAll()
                    .antMatchers(HttpMethod.GET, "/api/tokenValidity/validateDate").permitAll()
                    .anyRequest().authenticated();
        }
    }
}
