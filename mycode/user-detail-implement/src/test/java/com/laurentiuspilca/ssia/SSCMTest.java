package com.laurentiuspilca.ssia;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.keygen.KeyGenerators;

import java.util.Arrays;

/**
 * test Spring security crypto module
 * focus on encrypt and decrypt
 */
public class SSCMTest {
    @Test
    @DisplayName("byte encryptor with string key generator")
    public void testBytesEncryptor() {
        String salt = KeyGenerators.string().generateKey();
        String password = "secret";
        String valueToEncrypt = "HELLO";
        {
            BytesEncryptor e = Encryptors.standard(password, salt);

            byte[] encrypted = e.encrypt(valueToEncrypt.getBytes());
            System.out.println("Standard encrypt  " + Arrays.toString(encrypted));
            byte[] decrypted = e.decrypt(encrypted);
            System.out.println(Arrays.toString(decrypted));
            System.out.println("------");
        }

        {
            BytesEncryptor e = Encryptors.stronger(password, salt);

            byte[] encrypted = e.encrypt(valueToEncrypt.getBytes());
            System.out.println("Stronger encrypt  " + Arrays.toString(encrypted));
            byte[] decrypted = e.decrypt(encrypted);
            System.out.println(Arrays.toString(decrypted));
            System.out.println("------");
        }


    }

}
