/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KeyGenerator;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.*;
import java.util.Arrays;

/**
 *
 * @author ryann
 */
public class KeyGenerator {

    private KeyPairGenerator keyGen;
    private KeyPair keyPair;
    private static PublicKey publicKey;
    private static PrivateKey privateKey;

    public static PublicKey getPublicKey() {
        return publicKey;
    }

    public static PrivateKey getPrivateKey() {
        return privateKey;
    }

    public KeyGenerator() {
        try {
            keyGen = KeyPairGenerator.getInstance(Utils.GeneralOperation.getRsa_Algo());
            keyGen.initialize(1024);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void create() {
        KeyGenerator keyMaker = new KeyGenerator();

        //generate key pair
        keyMaker.keyPair = keyMaker.keyGen.generateKeyPair();

        //get public key
        publicKey = keyMaker.keyPair.getPublic();

        //get private key
        privateKey = keyMaker.keyPair.getPrivate();

        //write public/private keys to file for storing
        writeKeyToFile(publicKey.getEncoded(), Utils.FilePaths.getPbcKeyPath());
        writeKeyToFile(privateKey.getEncoded(), Utils.FilePaths.getPvtKeyPath());
    }

    public static void writeKeyToFile(byte[] keyBytes, String path) {
        byte[] keyBytesCopy = Arrays.copyOf(keyBytes, keyBytes.length + 1);
        //append the copy with new line
        System.arraycopy("\n".getBytes(), 0, keyBytesCopy, keyBytes.length, 1);
        
        File f = new File(path);
        f.getParentFile().mkdirs();

        try {
            Files.write(Paths.get(path), keyBytesCopy, StandardOpenOption.CREATE);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public KeyPairGenerator getKeyGen() {
        return keyGen;
    }
}
