package ntduong.movieappserver.util;

import io.jsonwebtoken.*;
import ntduong.movieappserver.config.AppProperties;
import ntduong.movieappserver.model.User;
import ntduong.movieappserver.security.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private static Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    private AppProperties appProperties;

    public JwtTokenProvider(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    // Generate Token
    public String createToken(UserPrincipal userPrincipal) {
        System.out.println(appProperties.getAuth().getTokenSecret() + appProperties.getAuth().getTokenExpirationMsec());

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + appProperties.getAuth().getTokenExpirationMsec());
        // Generate jwt token
        return Jwts.builder()
                .setSubject(Integer.toString(userPrincipal.getUserId()))
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getTokenSecret())
                .compact();
    }

    // Decode token to get UserID
    public int getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                            .setSigningKey(appProperties.getAuth().getTokenSecret())
                            .parseClaimsJws(token)
                            .getBody();
        return Integer.parseInt(claims.getSubject());
    }

    // Decode to validate token
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }

}
