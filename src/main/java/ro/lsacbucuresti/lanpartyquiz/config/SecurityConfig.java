package ro.lsacbucuresti.lanpartyquiz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ro.lsacbucuresti.lanpartyquiz.security.JwtAuthenticationEntryPoint;
import ro.lsacbucuresti.lanpartyquiz.security.JwtAuthenticationTokenFilter;
import ro.lsacbucuresti.lanpartyquiz.service.UserService;

/**
 * Created by cristi  04 - October - 2017
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorisedHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http
                .exceptionHandling().authenticationEntryPoint(unauthorisedHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/user/validateToken").permitAll()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/loginWithFacebook/**").permitAll()
                .antMatchers("/user/register/**").permitAll()
                .antMatchers("/user/confirm/**").permitAll()
                .antMatchers("/user/passwordReset/**").permitAll()
                .antMatchers("user/resetPassword/**").permitAll()
                .antMatchers("/main").permitAll()
//                .antMatchers("/dashboard/**").access("hasRole('ADMIN')")
                .antMatchers("/**").authenticated();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        http.headers().cacheControl();
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web
//                .ignoring()
//                .antMatchers("/user/validateToken");
//    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilter(){
        return new JwtAuthenticationTokenFilter();
    }
}
