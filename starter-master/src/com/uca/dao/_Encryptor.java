package com.uca.dao;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class _Encryptor
{
    private static final Random RANDOM       = new SecureRandom();
    private static final String CHARS        = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int    CHARS_LENGTH = CHARS.length();
    private static final int    ITERATIONS   = 10000;
    private static final int    KEY_LENGTH   = 256;

    public static String generateSalt(int size)
    {
        if (size < 1)
        {
            throw new IllegalArgumentException("la longueur du 'salt' doit etre positive");
        }
        StringBuilder salt = new StringBuilder(size);
        for (int i = 0; i < size; i++)
        {
            salt.append(CHARS.charAt(RANDOM.nextInt(CHARS_LENGTH)));
        }
        return new String(salt);
    }

    public static byte[] generateHash(char[] password, byte[] salt)
    {
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        Arrays.fill(password, Character.MIN_VALUE);
        try
        {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e)
        {
            throw new AssertionError("erreur pendant le hashage : " + e.getMessage(), e);
        } finally
        {
            spec.clearPassword();
        }
    }

    public static String generateSecurePassword(String password, String salt)
    {
        return Base64.getEncoder().encodeToString(generateHash(password.toCharArray(), salt.getBytes()));
    }

    public static boolean verifyUserPassword(String providedPassword, String securedPassword, String salt)
    {
        return securedPassword.equalsIgnoreCase(generateSecurePassword(providedPassword, salt));
    }
}
