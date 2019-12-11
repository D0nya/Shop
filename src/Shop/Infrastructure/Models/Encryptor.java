package Shop.Infrastructure.Models;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Encryptor
{
    private static Encryptor instance = null;
    private KeyGenerator keyGenerator = null;
    private SecretKey secretKey = null;
    private byte[] IV = new byte[16];


    private Encryptor() throws NoSuchAlgorithmException
    {
        keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        secretKey = keyGenerator.generateKey();
        SecureRandom random = new SecureRandom();
        random.nextBytes(IV);
    }

    public static Encryptor getInstance() throws NoSuchAlgorithmException
    {
        if(instance == null)
            instance = new Encryptor();
        return instance;
    }
    public String Encrypt(String data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException
    {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec secretKeySpec = new SecretKeySpec(this.secretKey.getEncoded(), "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(this.IV);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        return new String(cipher.doFinal(data.getBytes()));
    }
}
