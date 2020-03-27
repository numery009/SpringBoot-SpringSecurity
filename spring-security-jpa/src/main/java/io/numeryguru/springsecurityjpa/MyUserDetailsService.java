package io.numeryguru.springsecurityjpa;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.numeryguru.springsecurityjpa.models.MyUserDetails;
import io.numeryguru.springsecurityjpa.models.User;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {		
		//		return new MyUserDetails(username);
		Optional<User> user= userRepository.findByUserName(username);		
		user.orElseThrow(() -> new UsernameNotFoundException("Not Found :" + username));
		return user.map(MyUserDetails::new).get();
	}

}
