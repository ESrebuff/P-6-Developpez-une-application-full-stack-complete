package com.openclassrooms.mddapi.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

/**
 * Service class for managing JWT (JSON Web Token) generation and validation.
 */
@Service
public class JwtService {

    @Value("${secret-key}")
    private String SECRET_KEY;

    /**
     * Generates an access token for the given user with default claims.
     *
     * @param user The UserDetails object representing the user.
     * @return The generated access token.
     */
    public String getAccessToken(UserDetails user) {
        return getAccessToken(new HashMap<>(), user);
    }

    /**
     * Generates an access token for the given user with additional claims.
     *
     * @param extraClaims Additional claims to include in the token.
     * @param user        The UserDetails object representing the user.
     * @return The generated access token.
     */
    private String getAccessToken(Map<String, Object> extraClaims, UserDetails user) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 60 minutes
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Generates a refresh token for the given user.
     *
     * @param user The UserDetails object representing the user.
     * @return The generated refresh token.
     */
    public String getRefreshToken(UserDetails user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 30)) // 30 days
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Retrieves the username from a given token.
     *
     * @param token The JWT token.
     * @return The username extracted from the token.
     */
    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    /**
     * Checks if a token is valid for the given user.
     *
     * @param token       The JWT token.
     * @param userDetails The UserDetails object representing the user.
     * @return True if the token is valid for the user, otherwise false.
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Parses and retrieves all claims from a given token.
     *
     * @param token The JWT token.
     * @return The Claims object containing all claims from the token.
     */
    private Claims getAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Retrieves a specific claim from a given token.
     *
     * @param token          The JWT token.
     * @param claimsResolver The function to extract a specific claim from the
     *                       Claims object.
     * @param <T>            The type of the claim.
     * @return The specific claim extracted from the token.
     */
    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Retrieves the expiration date from a given token.
     *
     * @param token The JWT token.
     * @return The expiration date of the token.
     */
    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    /**
     * Checks if a token has expired.
     *
     * @param token The JWT token.
     * @return True if the token has expired, otherwise false.
     */
    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }

    /**
     * Checks if a token is about to expire within the next 30 minutes.
     *
     * @param token The JWT token.
     * @return True if the token is about to expire, otherwise false.
     */
    public boolean isTokenAboutToExpire(String token) {
        final Date expiration = getExpiration(token);
        final long expirationInMillis = expiration.getTime();
        final long currentTimeInMillis = System.currentTimeMillis();

        final long thresholdInMillis = 1000 * 60 * 30;

        return expirationInMillis - currentTimeInMillis <= thresholdInMillis;
    }

    /**
     * Retrieves the key used for signing JWT tokens.
     *
     * @return The Key object used for signing.
     */
    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
