package com.example.movie.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws  Exception {
      return httpSecurity.authorizeHttpRequests((registry) -> {
            registry.requestMatchers("/home").permitAll();
            registry.requestMatchers("/admin/**").hasRole("ADMIN");
            registry.requestMatchers("/api/v1/users/**").hasRole("USER");
            registry.anyRequest().authenticated();
      })
        .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
        .build();

    }

//  @Bean
//  public UserDetailsService userDetailsService() {
//    UserDetails normalUser = User.builder()
//      .username("gc")
//      .password("$2a$12$JRdcIg8LwhkANs69S5JX4.OJzkdpum6.HZm8aA4nQgdiXpnK3kQ.e")
//      .roles("USER")
//      .build();
//
//    UserDetails adminUser = User.builder()
//      .username("admin")
//      .password("$2a$12$fSBjNB4483o9y7t2bMOnNedo23PT6fCsHoNJcGM4ymR9l/Ueq1WkS")
//      .roles("ADMIN")
//      .build();
//    return new InMemoryUserDetailsManager(normalUser, adminUser);
//  }


  @Bean
  public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }


  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }

}
