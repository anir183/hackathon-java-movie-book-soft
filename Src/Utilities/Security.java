package Utilities;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.spec.KeySpec;
import java.util.Base64;

public class Security
{
    private static final String UNICODE_FORMAT = "UTF8";
    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
    private static final String ENCRYPTION_KEY = "MovieBookingSystemEncryptionKey";

    private static KeySpec keySpec;
    private static SecretKeyFactory secretKeyFactory;
    private static Cipher cipher;
    private static byte[] arrayBytes;
    private static SecretKey key;

    public static void Initialize()
    {
        try
        {
            arrayBytes = ENCRYPTION_KEY.getBytes(UNICODE_FORMAT);
            keySpec = new DESedeKeySpec(arrayBytes);
            secretKeyFactory = SecretKeyFactory.getInstance(DESEDE_ENCRYPTION_SCHEME);
            cipher = Cipher.getInstance(DESEDE_ENCRYPTION_SCHEME);
            key = secretKeyFactory.generateSecret(keySpec);
        }
        catch (Exception e)
        {
            System.out.println(e + "\nError Initializing Security Object");
            System.exit(0);
        }
    }

    public static String encrypt(String unencryptedString)
    {
        String encryptedString = null;
        try
        {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
            byte[] encryptedText = cipher.doFinal(plainText);
            encryptedString = Base64.getEncoder().encodeToString(encryptedText);
        }
        catch (Exception e)
        {
            System.out.println(e + "\nError During Encryption");
            System.exit(0);
        }
        return encryptedString;
    }

    public static String decrypt(String encryptedString)
    {
        String decryptedText = null;
        try
        {
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encryptedText = Base64.getDecoder().decode(encryptedString);
            byte[] plainText = cipher.doFinal(encryptedText);
            decryptedText= new String(plainText);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return decryptedText;
    }
}