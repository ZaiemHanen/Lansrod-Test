package com.testapp.companymanagement.controllers;

import java.util.Date;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.testapp.companymanagement.config.JwtToken;
import com.testapp.companymanagement.entities.User;
import com.testapp.companymanagement.services.JwtUserDetailsService;

@RestController
public class AuthenticationController {
	
	private static final Logger logger =LoggerFactory.getLogger(AuthenticationController.class);
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
	@Autowired
	JwtUserDetailsService us;
	@Autowired
	private JwtToken jwtToken;
	@Autowired
    private AuthenticationManager authenticationManager;

	@GetMapping(value= "/signin")
	public ResponseEntity<String> getAuthenticationToken(
			@RequestHeader(name="username" ) String username,
			@RequestHeader(name="password") String password) 
	{
		
		 try {
				authenticate(username, password);
				final UserDetails userDetails = us.loadUserByUsername(username);
				User user = us.getByUsername(username);
				Integer id_user = user.getId();
				final String token = jwtToken.generateToken(userDetails, id_user);
				JSONObject response = new JSONObject();
				response.put("token", token);
				response.put("expiration", new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000));
				return ResponseEntity.ok(response.toString());
			} catch (Exception e) {
				System.out.println(e.toString());
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unauthorised");
			}

	}
	
	@PostMapping(value= "/signup")
	   public User create(@RequestBody String body) throws Exception {
			 JSONObject jbody=new JSONObject(body);
	   	return us.firstLoginSave(jbody.getString("username"),jbody.getString("password"));
	   }
	
	 private Authentication authenticate(String username, String password) throws Exception {
	        try {
	            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	       
	        } catch (DisabledException e) {
	            throw new Exception("USER_DISABLED", e);
	        
	        } catch (BadCredentialsException e) {
	            throw new Exception("INVALID_CREDENTIALS", e);

	        }

	    }

	

}
