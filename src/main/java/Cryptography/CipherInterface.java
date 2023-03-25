package Cryptography;

import javax.crypto.Cipher;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;

public abstract class CipherInterface { //this one done

    protected Cipher cipher;

    public CipherInterface() {
        this(Utils.GeneralOperation.getRsa_Algo());
    }

    public CipherInterface(String algo) {
        try {
            cipher = Cipher.getInstance(algo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract String encrypt(String data, PublicKey key) throws Exception;

    public abstract String decrypt(String cipherText, PrivateKey key) throws Exception;
}
