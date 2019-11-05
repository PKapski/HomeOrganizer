package pl.polsl.security.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.polsl.model.User;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.Map;


public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl("/users/auth");
    }

    private static final String ERROR_MESSAGE = "Something went wrong while parsing /login request body";

    @Value("${security.jwt.token.expire-length}")
    private long validityInMilliseconds= 3600000L;

    @Value("${security.jwt.token.jwt-secret}")
    private String secret= "n2r5u8x/A%D*G-KaPdSgVkYp3s6v9y$B&E(H+MbQeThWmZq4t7w!z%C*F-J@NcRf";

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        String requestBody;
        try {
             requestBody = IOUtils.toString(request.getReader());
             Map values = objectMapper.readValue(requestBody,Map.class);
            var authenticationToken = new UsernamePasswordAuthenticationToken(values.get("username"), values.get("password"));
            return authenticationManager.authenticate(authenticationToken);
        } catch (IOException e) {
            e.printStackTrace();
            throw new InternalAuthenticationServiceException(ERROR_MESSAGE, e);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain filterChain, Authentication authentication) {
        var user = ((User) authentication.getPrincipal());

        System.out.println(authentication);

        var signingKey = secret.getBytes();

        var authorities = Collections.singletonList(new SimpleGrantedAuthority("user"));

        var token = Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
                .setHeaderParam("typ", "JWT")
                .setIssuer("secure-api")
                .setAudience("secure-app")
                .setSubject(user.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + validityInMilliseconds))
                .claim("rol",authorities)
                .compact();


        response.addHeader("Access-Control-Allow-Headers", "*");
        response.addHeader("Access-Control-Expose-Headers", "Authorization,Username,Household,Expiration");
        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("Username",user.getUsername());
        response.addHeader("Household", user.getHouseholdId());
        response.addHeader("Expiration", String.valueOf(validityInMilliseconds));
    }
}