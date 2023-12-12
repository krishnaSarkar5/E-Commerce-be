package com.ecommerce.ecommnerce.common.config.security.securitycomponents;




import com.ecommerce.ecommnerce.common.exception.ServiceException;
import com.ecommerce.ecommnerce.user.entity.User;
import com.ecommerce.ecommnerce.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service
public class JwtUserDetailService implements UserDetailsService{


	@Autowired
	private UserRepository userRepository;

	@Autowired
	private Environment environment;

	@Value("${active}")
	private Integer active;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


		return getUser(username);
	}




	private UserDetails getUser(String email){

		User user = userRepository.findByEmailAndStatus(email, (byte)1).orElseThrow(() -> new ServiceException("Invalid User", HttpStatus.UNAUTHORIZED));



		boolean isActive = user.getStatus().equals(active)?true:false;

		UserDetails userDetails = new JwtUserDetails(user, Arrays.asList(user.getRole()),isActive);

		return  userDetails;

	}


}
