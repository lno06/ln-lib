package com.lnlib.springboot.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter
{

    /**
     * All pages are allowed, except /requires-login which needs authentication, so it redirects to (fake) login page
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        //@formatter:off
        http
                .authorizeRequests()
                    // antMatchers matches exactly the string
                    // matches anything matching requires-login
                    .mvcMatchers("/requires-login").authenticated()
                    .anyRequest().permitAll()
                .and()
                .formLogin()
                    .loginPage("/login").permitAll()
                .and()
                .logout().permitAll();
        //@formatter:on
    }

    /**
     * To define the login credentials.
     * <p>
     * Alternative, you can override 'userDetailsService()'
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        //@formatter:off
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("user") // encoder.encode("user")
                .roles("USER")
             .and()
                .withUser("admin")
                .password("admin") // encoder.encode("admin")
                .roles("USER,ADMIN");
        //@formatter:on
    }

//    /**
//     * This is an alternative to method configure(AuthenticationManagerBuilder)
//     */
//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService()
//    {
//        return new InMemoryUserDetailsManager(
//                User.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("user")
//                        .roles("USER")
//                        .build());
//    }

}
