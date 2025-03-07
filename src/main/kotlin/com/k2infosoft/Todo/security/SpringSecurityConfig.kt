package com.k2infosoft.Todo.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer.AuthorizationManagerRequestMatcherRegistry
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableMethodSecurity
class SpringSecurityConfig(@Lazy private val userDetailsService: UserDetailsService) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it.requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN")
                it.requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN")
                it.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")
                it.requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN", "USER")
                it.requestMatchers(HttpMethod.PATCH, "/api/**").hasAnyRole("ADMIN", "USER")
                it.requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                    .anyRequest().authenticated()
            }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .httpBasic {}  // Enables basic authentication

        return http.build()
    }

//    @Bean
//    @Throws(java.lang.Exception::class)
//    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain? {
//        http.csrf { it.disable() }
//            .authorizeHttpRequests(Customizer { authorize ->
//                    authorize.requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN");
//                    authorize.requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN");
//                    authorize.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN");
//                    authorize.requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN", "USER");
//                    authorize.requestMatchers(HttpMethod.PATCH, "/api/**").hasAnyRole("ADMIN", "USER");
//                    authorize.requestMatchers(HttpMethod.GET, "/api/**").permitAll();
//                authorize.anyRequest().authenticated()
//            }).httpBasic(Customizer.withDefaults())
//        return http.build()
//    }


    @Bean
    @Throws(Exception::class)
    fun authenticationManager(configuration: AuthenticationConfiguration): AuthenticationManager? {
        return configuration.authenticationManager
    }     @Bean
         fun  userDetailsService(): UserDetailsService{
            val ramesh: UserDetails = User.builder()
                    .username("kaushal")
                    .password(passwordEncoder().encode("password"))
                    .roles("USER")
                    .build();

            val admin: UserDetails = User.builder()
                    .username("admin")
                    .password(passwordEncoder().encode("admin"))
                    .roles("ADMIN")
                    .build();

            return InMemoryUserDetailsManager(ramesh, admin)
        }

    companion object {
        @Bean
        fun passwordEncoder(): PasswordEncoder {
            return BCryptPasswordEncoder()
        }
    }
}