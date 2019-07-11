package com.wengzhoujun.vechat.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Map;

/**
 * Created on 2019/7/8.
 *
 * @author WengZhoujun
 */
public class JwtTokenUtil {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static final String AUTH_HEADER = "Authorization";

    public static final String TOKEN_HEAD = "Bearer ";

    private static String secret = "1U5f6lBaofA50KQ7";

    //默认1天有效时间
    private static int expiration = 1 * 24 * 60 * 60;

    /**
     * 解析token
     *
     * @param token
     * @return
     */
    public static Claims parseToken(String token) {
        Jws<Claims> jws = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token);
        Claims claims = jws.getBody();
        return claims;
    }

    /**
     * 创建token
     *
     * @param claims
     * @return
     */
    public static String generateToken(Map<String, Object> claims) {
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(DateUtils.createBySecond(now, expiration))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

}
