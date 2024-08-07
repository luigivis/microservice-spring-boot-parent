package com.luigivismara.microservice.utils.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luigivismara.microservice.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;

import static com.luigivismara.microservice.dto.Times.ONE_DAY;

/**
 * The type Jwt Impl.
 */
@Slf4j
@Service
@PropertySource("classpath:application.properties")
public class JwtUtilsImpl implements JwtUtils {
    @Value("${jwt.hash-key}")
    String secretKeyString = "Rb7wZF0a1O0LK3GmJVrf8NvpfCzmAYeyXE35ELE06GQoAi6MESkYBb3gjr6dOZJW";

    /**
     * Gets the key.
     *
     * @return the key
     */
    @Override
    public SecretKey getKey() {
        return new SecretKeySpec(
                secretKeyString.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS512.getId());
    }

    /**
     * Extract username.
     *
     * @param token the token
     * @return the string
     */
    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extract expiration.
     *
     * @param token the token
     * @return the date
     */
    @Override
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extract claim.
     *
     * @param <T>            the generic type
     * @param token          the token
     * @param claimsResolver the claims resolver
     * @return the t
     */
    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extract all claims.
     *
     * @param token the token
     * @return the claims
     */
    @Override
    public Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload();
    }

    /**
     * Checks if is token expired.
     *
     * @param token the token
     * @return true, if is token expired
     */
    @Override
    public boolean isTokenExpired(String token) {
        var status = true;
        try {
            status = extractExpiration(token).before(new Date());
        } catch (Exception e) {
            log.error("Ocurrio un error {}", e.getMessage());
        }
        return status;
    }

    /**
     * Generate token.
     *
     * @param username the username
     * @param roles    the roles
     * @return the string
     */
    @Override
    public String generateToken(String username, List<String> roles) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username, roles);
    }

    /**
     * Creates the token.
     *
     * @param claims  the claims
     * @param subject the subject
     * @param roles   the roles
     * @return the string
     */
    @Override
    public String createToken(Map<String, Object> claims, String subject, List<String> roles) {

        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .header().add("roles", roles)
                .and()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 365 * ONE_DAY))
                .signWith(getKey())
                .compact();
    }

    /**
     * Validate token.
     *
     * @param token the token
     * @param user  the user
     * @return the boolean
     */
    @Override
    public Boolean validateToken(String token, String user) {
        var username = extractUsername(token);
        return (username.equals(user) && !isTokenExpired(token));
    }

    /**
     * Gets the roles token.
     *
     * @param token the token
     * @return the roles token
     */
    @Override
    public List<String> getRolesToken(String token) {
        var headerList = new ArrayList<String>();
        try {
            log.info("Getting roles from token {}", token);

            var splitString = token.split("\\.");
            var base64EncodedHeader = splitString[0];

            var header = new String(Base64.getDecoder().decode(base64EncodedHeader));
            var headerValues = new ObjectMapper().readTree(header);

            for (JsonNode jsonNode : headerValues.path("roles")) {
                headerList.add(jsonNode.asText());
            }
            log.info("JWT Header : {}", headerList);
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return headerList;
        }

        return headerList;
    }
}
