package com.example.authentication.authentication.userService;



import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDate;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.token.Token;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class jwtService {
	private int Expiration_time=1000*60*60;
	@Value("${jwt.secretkey}")
	String secret_key;
	private SecretKey getSigningKey() {
		
		return Keys.hmacShaKeyFor(secret_key.getBytes(StandardCharsets.UTF_8));
	}

public String generateToken(UserDetails userDetails) {
	return Jwts.builder()
			.subject(userDetails.getUsername())
			.issuedAt(new Date())
			.expiration(new Date(System.currentTimeMillis() + Expiration_time))
			.signWith(getSigningKey())
	        .compact();
}

public boolean isTokenValid(String token,UserDetails userDetails) {
	String name=userDetails.getUsername();
	return 
			name.equals(extractAllClaims(token).getSubject()) && !isTokenExpired(token);
}
	public String extractUsername(String token) {
		 String username=extractAllClaims(token).getSubject();
		 return username;
	}
		
private Claims extractAllClaims(String token) {
	return 
			Jwts.parser()
			.verifyWith(getSigningKey())
			.build()
			.parseSignedClaims(token)
			.getPayload();
}
private boolean isTokenExpired(String token) {
	return extractAllClaims(token).getExpiration().before(new Date());
}

}

