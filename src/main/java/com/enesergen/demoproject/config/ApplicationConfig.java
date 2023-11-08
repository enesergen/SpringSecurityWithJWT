package com.enesergen.demoproject.config;


import com.enesergen.demoproject.exception.ExceptionConstant;
import com.enesergen.demoproject.exception.UserRoleNotFoundException;
import com.enesergen.demoproject.repository.UserRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Collection;
import java.util.List;

@Configuration
public class ApplicationConfig {
    private static final Logger logger = LogManager.getLogger(ApplicationConfig.class);

    private final UserRepository userRepository;

    public ApplicationConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return
                new UserDetailsService() {
                    @Override
                    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                        var user = userRepository.findUserByUsername(username).orElseThrow(()->new UsernameNotFoundException(ExceptionConstant.ERR_001));
                        return new UserDetails() {
                            @Override
                            public Collection<? extends GrantedAuthority> getAuthorities() {
                                if(user.getRole()!=null){
                                    return List.of(new SimpleGrantedAuthority(user.getRole()));
                                }else{
                                    throw new UserRoleNotFoundException(ExceptionConstant.ERR_002);
                                }

                            }

                            @Override
                            public String getPassword() {
                                return user.getPassword();
                            }

                            @Override
                            public String getUsername() {
                                return user.getUsername();
                            }

                            @Override
                            public boolean isAccountNonExpired() {
                                return user.isActive();
                            }

                            @Override
                            public boolean isAccountNonLocked() {
                                return user.isActive();
                            }

                            @Override
                            public boolean isCredentialsNonExpired() {
                                return user.isActive();
                            }
                            @Override
                            public boolean isEnabled() {
                                return user.isActive();
                            }};
                    }
                };
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=
                new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
