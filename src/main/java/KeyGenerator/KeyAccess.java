/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KeyGenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 *
 * @author ryann
 */
public class KeyAccess {

    public static PublicKey getPublicKey(String path) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keyBytes = Files.readAllBytes(Paths.get(Utils.FilePaths.getPbcKeyPath()));
        //check if the public key file exists and contains data
        if (keyBytes.length < 1) {
            //if no, create new key pair
            MyKeyPair.create();
            keyBytes = Files.readAllBytes(Paths.get(Utils.FilePaths.getPbcKeyPath()));
        }
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        return KeyFactory.getInstance(Utils.GeneralOperation.getRsa_Algo()).generatePublic(keySpec);
    }

    public static PrivateKey getPrivateKey(String path) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keyBytes = Files.readAllBytes(Paths.get(Utils.FilePaths.getPvtKeyPath()));
        //check if the public key file exists and contains data
        if (keyBytes.length < 1) {
            //if no, create new key pair
            MyKeyPair.create();
            keyBytes = Files.readAllBytes(Paths.get(Utils.FilePaths.getPvtKeyPath()));
        }
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        return KeyFactory.getInstance(Utils.GeneralOperation.getRsa_Algo()).generatePrivate(keySpec);
    }
}
