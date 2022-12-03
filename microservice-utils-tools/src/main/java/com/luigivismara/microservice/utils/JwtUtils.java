package com.luigivismara.microservice.utils;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import io.jsonwebtoken.Claims;

/**
 * The Interface JwtUtils.
 */
public interface JwtUtils {

  /**
   * Gets the key.
   *
   * @return the key
   */
  public Key getKey();

  /**
   * Extract username.
   *
   * @param token the token
   * @return the string
   */
  public String extractUsername(String token);

  /**
   * Extract expiration.
   *
   * @param token the token
   * @return the date
   */
  public Date extractExpiration(String token);

  /**
   * Extract claim.
   *
   * @param <T> the generic type
   * @param token the token
   * @param claimsResolver the claims resolver
   * @return the t
   */
  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

  /**
   * Extract all claims.
   *
   * @param token the token
   * @return the claims
   */
  public Claims extractAllClaims(String token);

  /**
   * Checks if is token expired.
   *
   * @param token the token
   * @return true, if is token expired
   */
  public boolean isTokenExpired(String token);

  /**
   * Generate token.
   *
   * @param username the username
   * @param roles the roles
   * @return the string
   */
  public String generateToken(String username, List<String> roles);

  /**
   * Creates the token.
   *
   * @param claims the claims
   * @param subject the subject
   * @param roles the roles
   * @return the string
   */
  public String createToken(Map<String, Object> claims, String subject, List<String> roles);

  /**
   * Validate token.
   *
   * @param token the token
   * @param user the user
   * @return the boolean
   */
  public Boolean validateToken(String token, String user);

  /**
   * Gets the roles token.
   *
   * @param token the token
   * @return the roles token
   */
  public List<String> getRolesToken(String token);
}
