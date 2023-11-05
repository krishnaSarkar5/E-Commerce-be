package com.ecommerce.ecommnerce.common.config.security.securitycomponents;

import lombok.Data;

@Data
public class JwtAuthRequest {

	private String email;
	
	private String password;
}
