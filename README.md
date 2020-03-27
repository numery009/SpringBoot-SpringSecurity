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
    
      
## Note: 
   For non SpringBoot App we have to add the Spring Security Filters in the web.xml. But in the SpringBoot app we do            not need to. 
       
       <Filter-Name> SpringSecurityFilterChain</Filter-Name>
       <filter-class>org.springframework.web.filter.delegatingFilterProxy<filter-class>
       
       <Filter>
       	<filter-mapping>
       		<filter-name> springsecurityfilterchain</filter-name>			
       	</filter-mapping>
       </filter-mapping>

## JWT -> JSON WEB TOKEN

	Authorization strategies
	1) Session Token -> Reference Token
	2) JSON Web Token -> Value Token
	
	Both will run in the HTTP. HTTP is staleless protocal. No state will remember for multiple request.
	
	JWT impementation Process --
	
	1) Authentication API endpoints
		- Accepts userId and Password.
		- Returns JWT as response.
	
	2) Intercept all incoming requests
		- Extract JWT from the header
		- Validate and set in execution context.
		
## JWT Structure
	
	1) Header: Algorithm and tocken secrects.
	2) Payload: Data -> Base64Encoded.
	3) Verify Signature -> This string only compute by the server for the authentity. It is a algorithm with secret key.
	
## Question about JWT

	1) How secure is a JWT if it readble by anyone?
	Ans: No confidential/ sensitive information in a JWT. Just enough into for the server to know who the user is.
	
	2) What if someone steals my JWT and uses it themselves?
	Ans: Server just verifies if JWT is correct. Does not know who sent it.
	
	3) Who do you disable JWT?
	Ans: If someone steal JWT we have to disabled/ blacklisted JWT. It's a workaround.

## OAUTH:
	
	OAuth-> is for Authorization.
	OAuth-> Authorization between services.
	
	OAuth Access Token-> Contains user-allowed permissions trustable. (Cannot be tampered). OAuth uses JWT.

## Terminology and Steps of OAuth:

	Term 1: Resource -> Photo in the google drive that is the resource.
	
	Term 2: Resource Owner -> Who has the access to the resource right now.
	
	Term 3: Resource Server -> The server that has hosted all the resource. Google Drive is the hosted server.
	
	Term 4: Client -> An application making protected resource requests on behalf of the resource owner and with in Authorization. 
