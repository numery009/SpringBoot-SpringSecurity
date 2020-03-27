# SpringBoot-SpringSecurity

## Spring Security Concepts
    
    1) Authentication -> Who is the user?
    2) Authorization -> Are they allowed to do this?
    3) Principal -> Currently logged in user.
    4) Granted Authority -> User went to do something on the application. The application allows the user to do so.
                            If the application has the granted authority or authority.
    5) Roles -> Group of Authorities.

## Spring Security Default Behavior
    
    1) Add mandatory authentication for URLs.    
    2) Adds login form.
    3) Handles login error.
    4) Creates a user and sets a default password.
    
## How to Configure Spring Security Authentication
    
    The way to configure authentication in spring security is by effecting by Authentication Manager. A authentication manager is something that manages authentication on the spring security. 
    
## Development Steps of Spring Security
    
    1) We have to extend the WebSecurityConfigurationAdapter Class.
    2) @EnableWebSecurity annotation in the class. We have to tell the spring security that this is the spring security            class.
    3) Override the method configure. 
       
       protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       }       
    4) Add the Spring Security dependency on the POM file.
    5) OverRide the method configure for role and intercep the requests.
       
       protected void configure(HttpSecurity http) throws Exception {
       } 
       
       Full example is given below--
       
       
       @EnableWebSecurity
       public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
        
          @Override
	        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		          auth.inMemoryAuthentication().withUser("num").password("num").roles("USER").and().withUser("foo")
				      .password("foo").roles("ADMIN");
	        }
          
          @Override
	        protected void configure(HttpSecurity http) throws Exception {
		      http.authorizeRequests().antMatchers("/admin").hasRole("ADMIN").antMatchers("/user").hasAnyRole("USER", "ADMIN")
				  .antMatchers("/").permitAll().and().formLogin();
	        
          }
          
          @Bean
	        public PasswordEncoder getPasswordEncoder() {
		        return NoOpPasswordEncoder.getInstance();
	        }
        }
    
      
