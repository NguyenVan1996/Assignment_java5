package anhvanmobile.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import anhvanmobile.service.user.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;

	private PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	private static final String[] PUBLIC_MATCHERS = { "/", "/about", "/contact", "/products**", "/products/**",
			"/login", "/register" };

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
			authorizeRequests()
				.antMatchers(PUBLIC_MATCHERS).permitAll()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/profile**").hasRole("USER")
				.and()
			.formLogin().permitAll()
				.usernameParameter("username")
				.passwordParameter("password")
				.loginPage("/login")
				.failureUrl("/login-error")
				.defaultSuccessUrl("/")
				.and()
			.rememberMe()
				.key("rememberMe")
				.rememberMeParameter("remember-me")
				.and()
			.logout()
				.permitAll().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/?logout").deleteCookies("remember-me")
				.and()
			.exceptionHandling()
				.accessDeniedPage("/403")
				.and()
			.csrf().disable().cors().disable();

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**", "/webfonts/**", "/images/**");
	}

}
