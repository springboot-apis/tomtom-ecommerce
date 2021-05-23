package com.tomtom.ecommerce.security.authorization;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.tomtom.ecommerce.exceptions.JwtAuthorizationFailedException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtAuthorizationManager {
	private static final Logger LOGGER = LogManager.getLogger(JwtAuthorizationManager.class);

	@Value("${auth.manager.jwt.secret.key}")
	private  String secretKey;

	@Value("${auth.manager.jwt.secret.id}")
	private  String jwtId;

	@Value("${auth.manager.jwt.secret.issuer}")
	private  String jwtIssuer;

	@Value("${auth.manager.jwt.secret.TTLms}")
	private  String jwtTTLms;

	public String createJWT(String subject) throws JwtAuthorizationFailedException {
		JwtBuilder builder;
		try {
			SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

			long nowMillis = System.currentTimeMillis();
			Date now = new Date(nowMillis);

			byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
			Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

			builder = Jwts.builder().setId(jwtId).setIssuedAt(now).setSubject(subject).setIssuer(jwtIssuer)
					.signWith(signatureAlgorithm, signingKey);

			long expMillis = nowMillis + Long.parseLong(jwtTTLms);
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);

			LOGGER.debug("Jwt data signing performed: " + builder.compact());
			LOGGER.info("Customer data signed for payment");

		} catch (UnsupportedJwtException | SignatureException | IllegalArgumentException e) {
			throw new JwtAuthorizationFailedException(e.getMessage());
		}
		return builder.compact();
	}

	public Claims decodeJWT(String jwt) throws JwtAuthorizationFailedException {
		Claims claims = null;
		try {
			claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretKey)).parseClaimsJws(jwt)
					.getBody();
			LOGGER.debug("Jwt data verified " + claims);
			LOGGER.info("Customer authorised sucessfully, payment done");

		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException
				| IllegalArgumentException e) {
			throw new JwtAuthorizationFailedException(e.getMessage());
		}
		return claims;
	}

//	public static void main(String[] a) { //tested }

}