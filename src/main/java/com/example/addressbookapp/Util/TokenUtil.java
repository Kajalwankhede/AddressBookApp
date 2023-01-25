package com.example.addressbookapp.Util;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.springframework.stereotype.Component;
@Component
public class TokenUtil {
//Everytime you add data,need to create token and  will be used multiple times .
private static final String TOKEN_SECRET = "Secure";
//Token Encode part
public  String createToken(int id)   {
    try {
       //to set algorithm
           Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
           String token = JWT.create()
           .withClaim("user_id", id)
           .sign(algorithm);
           return token;
 } catch (JWTCreationException exception) {
         exception.printStackTrace();
        //log Token Signing Failed
 } catch (IllegalArgumentException e) {
        // TODO Auto-generated catch block
            e.printStackTrace();
 }
    return null;
 }

//Token Decode part
public int decodeToken(String token)
 {
     int userid;
     Verification verification = null;   //for verification algorithm
 try {
        verification = JWT.require(Algorithm.HMAC256(TOKEN_SECRET));
 } catch (IllegalArgumentException  e) {
         // TODO Auto-generated catch block
     e.printStackTrace();
 }
     JWTVerifier jwtverifier=verification.build();
        //to decode token
     DecodedJWT decodedjwt=jwtverifier.verify(token);
     Claim claim=decodedjwt.getClaim("user_id");
     userid=claim.asInt();
     return userid;
 }
}

