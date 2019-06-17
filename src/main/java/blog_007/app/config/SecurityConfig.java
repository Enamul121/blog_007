package blog_007.app.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public static BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }



    @Configuration
    @Order(1)
public static class UserConfig extends WebSecurityConfigurerAdapter{

        @Autowired
        private BCryptPasswordEncoder bCryptPasswordEncoder;
        @Autowired
        private DataSource dataSource;
        private final String USERS_QUERY = "select email, password, active from user where email=?";
        private final String ROLES_QUERY = "select u.email, r.role from user u inner join user_role ur on (u.id = ur.user_id) inner join role r on (ur.role_id=r.role_id) where u.email=?";

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.jdbcAuthentication()
                    .usersByUsernameQuery(USERS_QUERY)
                    .authoritiesByUsernameQuery(ROLES_QUERY)
                    .dataSource(dataSource)
                    .passwordEncoder(bCryptPasswordEncoder);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/allArticles","/error","/post/**", "/user/login","/signup","/","/entry").permitAll()
                    .antMatchers("**/webjars/**","/webjars/**","/resources/static/**").permitAll()
                    .antMatchers("/","/resources/**", "/img/**", "/fonts/**", "/css/**").permitAll()
                    .antMatchers("/admin","/admin/**","/admin/home").hasAnyAuthority("ADMIN")
                    .anyRequest().authenticated()
                    .antMatchers("/user","/user/**").hasAnyAuthority("USER")
                    .anyRequest()
                    .authenticated()
                    .and()
                    .csrf().disable()
                    .formLogin().loginPage("/user/login").failureUrl("/user/login?error=true")
                    .defaultSuccessUrl("/user/home", true)
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .and().logout().logoutUrl("/user/logout");
        }

   /*     @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/resources/static/css/**","/static/**","/webjars/**");//.anyRequest();
        }
        */

    }
}





