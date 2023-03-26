package SignatureSign;

import KeyGenerator.KeyGenerator;
import Utils.Algorithms;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DigitalSignature {

    private static Signature signature;
    private static KeyGenerator keyGen = new KeyGenerator();
    private static KeyPair keyPair = keyGen.getKeyGen().generateKeyPair();

    public DigitalSignature() {
        super();
        try {
            signature = Signature.getInstance(Algorithms.AlgoSHA256_RSA());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //set the private key for one-time use signIn-in
    public PrivateKey setPrivateKey() {
        try {
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyPair.getPrivate().getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance(Algorithms.AlgoRSA());
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
            return privateKey;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(DigitalSignature.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //set the public key for one-time use login
    public PublicKey setPublicKey() {
        try {
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyPair.getPublic().getEncoded());
            KeyFactory kf = KeyFactory.getInstance(Algorithms.AlgoRSA());
            PublicKey publicKey = kf.generatePublic(keySpec);
            return publicKey;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //used to signIn the provided data using the private key
    public String signIn(String data) {
        try {
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyPair.getPrivate().getEncoded());
            KeyFactory kf = KeyFactory.getInstance(Algorithms.AlgoRSA());
            PrivateKey privateKey = kf.generatePrivate(keySpec);
            signature.initSign(privateKey);
            signature.update(data.getBytes());

            return Base64.getEncoder().encodeToString(signature.sign());
        } catch (InvalidKeyException | SignatureException | NoSuchAlgorithmException | InvalidKeySpecException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //used to DSverify the provided signature against the provided data using the public key
    public static boolean DSverify(String data, String sign){
        try {
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyPair.getPublic().getEncoded());
            KeyFactory kf = KeyFactory.getInstance(Algorithms.AlgoRSA());
            PublicKey publicKey = kf.generatePublic(keySpec);
            //System.out.println("Data: " + data);
            //System.out.println("Signature: " + sign);
            signature.initVerify(publicKey);
            signature.update(data.getBytes());
            //System.out.println("DS class verify: " + signature.verify(Base64.getDecoder().decode(sign)));
            return signature.verify(Base64.getDecoder().decode(sign));
        } catch (InvalidKeyException | NoSuchAlgorithmException | SignatureException | InvalidKeySpecException ex) {
            ex.printStackTrace();
        }
        return false;
    }

}
