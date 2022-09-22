package uz.pdp.myroleandpermission.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.stereotype.Component;
import uz.pdp.myroleandpermission.dto.UserJwtDto;
import uz.pdp.myroleandpermission.entity.RoleEntity;
import uz.pdp.myroleandpermission.enums.RoleName;
import uz.pdp.myroleandpermission.exception.TokenNotValidException;

import javax.xml.transform.sax.SAXResult;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Component
public class JwtProvider {

    private String secret = "Humoyun";
    private long expireDate = 1000 * 60 * 60 * 24;

    public String generateToken(String username, List<RoleEntity> roles) {

        String jws = Jwts.builder().
                setSubject(username)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expireDate))
                .signWith(
                        SignatureAlgorithm.HS512,
                        TextCodec.BASE64.decode(secret)
                ).compact();
        return jws;
    }

    public String decodeJwt(String jwt) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(jwt);
            String username = claimsJws.getBody().getSubject();

            Claims body = claimsJws.getBody();
            String roles = String.valueOf(body.get("roles"));
            return username;
        } catch (Exception e) {
            throw new TokenNotValidException("token not valid");
        }
    }

}
