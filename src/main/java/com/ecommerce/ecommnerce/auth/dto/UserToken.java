package com.ecommerce.ecommnerce.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@EqualsAndHashCode(callSuper=false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserToken {
	
	@NonNull
	private long id;
	
	@NonNull
	private String username;
	
	@NonNull
	private Byte status;
	
	@NonNull
	private String channel;
	
	@NonNull
	private Object user;


	private String token;

}
