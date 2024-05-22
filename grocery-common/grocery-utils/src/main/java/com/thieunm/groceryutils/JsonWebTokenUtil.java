package com.thieunm.groceryutils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonWebTokenUtil {

    public static String getPhoneNumber(String token) {
        return getClaims(token).getClaimValueAsString("preferred_username");
    }

    public static String getUserId(String token) {
        try {
            return getClaims(token).getSubject();
        } catch (MalformedClaimException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> getRoleList(String token) {
        try {
            Map<String, Object> roleClaimMap = getClaims(token).getClaimValue("realm_access", HashMap.class);
            return (List<String>) roleClaimMap.get("roles");
        } catch (MalformedClaimException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static JwtClaims getClaims(String token) {
        try {
            JwtConsumer consumer = new JwtConsumerBuilder()
                    .setSkipAllValidators()
                    .setDisableRequireSignature()
                    .setSkipSignatureVerification()
                    .build();
            return consumer.processToClaims(token);
        } catch (InvalidJwtException e) {
            e.printStackTrace();
        }
        return null;
    }
}