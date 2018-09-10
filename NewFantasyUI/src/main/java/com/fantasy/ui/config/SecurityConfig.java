package com.fantasy.ui.config;

import java.util.HashMap;
import java.util.Map;

//@Configuration
//@EnableWebSecurity
//@Order(SecurityProperties.)
public class SecurityConfig /*extends WebSecurityConfigurerAdapter*/ {
	
//	@Bean(name = "authStatus")
	public Map<String, Boolean> getAuthStatus() {
		Map<String, Boolean> authenticatedStatus = new HashMap<String, Boolean>();
		authenticatedStatus.put("user1", true);
		return authenticatedStatus;
	}

	/*@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("{noop}password").roles("USER");
		// User.withDefaultPasswordEncoder().username("user").password("user").roles("USER").build();
	}*/

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
////		http.authorizeRequests().anyRequest().authenticated().and().httpBasic()
////		.and().csrf()
////        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
//		
//		http
//        .httpBasic()
//      .and().authorizeRequests()
//          .antMatchers("com/**/*").permitAll()
//          .anyRequest().authenticated()
//      .and().authorizeRequests()
//          .antMatchers("**/dashboard/**/*").permitAll()
//          .anyRequest().authenticated()
//   .and().authorizeRequests()
//       .antMatchers("**/userPreview/**/*").permitAll()
//       .anyRequest().authenticated()
//   .and().authorizeRequests()
//       .antMatchers("**/login/**/*").permitAll()
//       .anyRequest().authenticated()
//    .and().formLogin()
//          .loginPage("/login")
//          .permitAll()
//      .and().logout()
//          .permitAll()
//      .and().csrf()
//          .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
//	}
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//	    http.sessionManagement()
//	        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
//	}

//	@Bean
//	public HttpSessionEventPublisher httpSessionEventPublisher() {
//	    return new HttpSessionEventPublisher();
//	}
	
	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.sessionManagement().maximumSessions(2)
	}*/
}
