package Cryptography;

import Utils.Algorithms;
import javax.crypto.Cipher;
import java.security.PrivateKey;
import java.security.PublicKey;

public abstract class CipherInterface { 

    protected Cipher cipher;

    public CipherInterface() {
        this(Algorithms.AlgoRSA());
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
