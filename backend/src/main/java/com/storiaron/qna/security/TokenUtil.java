package com.storiaron.qna.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class TokenUtil {
    private final String SECRET_KEY = System.getenv("SECRET_KEY");

    private UserDetailsService userDetailsService;

    public TokenUtil(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public UserDetails parseToken(String token) {
        String splitToken = token.substring(7);

        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY.getBytes());

            JWTVerifier verifier = JWT.require(algorithm)
                    .build();

            DecodedJWT jwt = verifier.verify(splitToken);

            String employeeName = jwt.getSubject();
            return userDetailsService.loadUserByUsername(employeeName);

        } catch (Exception e) {
            return null;
        }
    }
}
