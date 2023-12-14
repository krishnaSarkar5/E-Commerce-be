package com.ecommerce.ecommnerce.common.config.security.securitycomponents;



import com.ecommerce.ecommnerce.auth.entity.TokenMaintainTable;
import com.ecommerce.ecommnerce.auth.repository.TokenMaintainRepository;
import com.ecommerce.ecommnerce.common.config.security.SecurityConfig;

import com.ecommerce.ecommnerce.auth.dto.UserToken;
import com.ecommerce.ecommnerce.user.entity.User;
import com.ecommerce.ecommnerce.user.repository.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.*;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private JwtUserDetailService jwtUserDetailService;
	
	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private TokenMaintainRepository tokenMaintainRepository;

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

		final String requestTokenHeader = request.getHeader("authorization");

		System.out.println("++ donot filter ++ "+ requestTokenHeader);

		if(Arrays.asList(SecurityConfig.URLS_THAT_DONT_NEED_AUTHENTICATION).contains(request.getRequestURI()))
		{
			System.out.println("true");
		}else
		{
			System.out.println("false");
		}
         System.out.println(request.getRequestURI().toString());
		return Arrays.asList(SecurityConfig.URLS_THAT_DONT_NEED_AUTHENTICATION).contains(request.getRequestURI());
	}
	@Autowired
	private UserRepository userRepository;

	@Value("${active}")
	private Byte active;
	

	
	@Autowired
	private Environment environment;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String requestTokenHeader = request.getHeader("authorization");
		System.out.println(request.getRequestURI());
		String mobile = null;
		String jwtToken = null;

		Enumeration<String> headerNames = request.getHeaderNames();

		while (headerNames.hasMoreElements()){
			System.out.println(("+-+++  request "+headerNames.nextElement()));
		}



		// JWT Token is in the form "Bearer token". Remove Bearer word and get
		// only the Token
		logger.info(" incoming token = "+requestTokenHeader);
		if (requestTokenHeader != null) {

			if(requestTokenHeader.startsWith("Bearer ")){
				jwtToken = requestTokenHeader.substring(7);
			}else {
				jwtToken=requestTokenHeader;
			}


			try {
				mobile = jwtUtils.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				System.out.println("JWT Token has expired");
//				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token Expired");
				throw  new AccessDeniedException("Don't have permission");
			}
		} else {
			logger.warn("JWT Token does not begin with Bearer String");
		}

		// Once we get the token validate it.
		if (mobile != null && SecurityContextHolder.getContext().getAuthentication() == null) {


			// this will come from respective service

			Map<String, Object> responseMap = isTokenActiveAndUserActive(mobile, jwtToken);

			boolean isTokenActiveAndUserActiveBool = Boolean.parseBoolean(responseMap.get("isTokenActiveAndUserActive").toString());

			Object principle = responseMap.get("principle");

			UserDetails userDetails = (UserDetails) responseMap.get("userDetails");

			if (isTokenActiveAndUserActiveBool)
			{
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						principle, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// After setting the Authentication in the context, we specify
				// that the current user is authenticated. So it passes the
				// Spring Security Configurations successfully.
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				filterChain.doFilter(request, response);
			}else {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid User");
			}
		}else {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid User");
		}
		System.out.println("out of filter");
	}


	private Map<String,Object> isTokenActiveAndUserActive(String email, String token){


		UserDetails userDetails = this.jwtUserDetailService.loadUserByUsername(email);


		//validates if the username is phone number or mobile address
		User user = userRepository.findByEmailAndStatus(email, active).orElseThrow(() -> new UsernameNotFoundException("User name not found"));




		Map<String,Object> responseMap = new HashMap<>();






		boolean isTokenValidForActiveUsers = jwtUtils.validateTokenWExpirationValidation(token, userDetails)
				&& user.getStatus().equals(active);

		// TODO also validate the token from db

		Optional<TokenMaintainTable> tokenDetails = tokenMaintainRepository.findByTokenAndStatus(token, 1);

		if(tokenDetails.isEmpty()){
			isTokenValidForActiveUsers = false;
		}

		UserToken principleForUser = getPrinciple(user,token);

		responseMap.put("isTokenActiveAndUserActive",isTokenValidForActiveUsers);
		responseMap.put("principle",principleForUser);
		responseMap.put("userDetails",userDetails);

		return responseMap;
	}



	private UserToken getPrinciple(User user,String token){

		UserToken principal=new UserToken();
		principal.setId(user.getId());
		principal.setStatus(user.getStatus());
		principal.setUsername(user.getEmail());
		principal.setUser(user);
		principal.setToken(token);

		return principal;
	}




}
