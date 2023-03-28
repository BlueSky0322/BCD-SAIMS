package SignatureSign;

import KeyGenerator.KeyGenerator;
import Utils.Algorithms;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

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

    //used to signIn the provided data using the private key
    public String signIn(String data) {
        try {
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyPair.getPrivate().getEncoded());
            KeyFactory kf = KeyFactory.getInstance(Algorithms.AlgoRSA());
            PrivateKey privateKey = kf.generatePrivate(keySpec);
            signature.initSign(privateKey);
            signature.update(data.getBytes());

            return Base64.getEncoder().encodeToString(signature.sign());
        } catch (Exception ex) {
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
            signature.initVerify(publicKey);
            signature.update(data.getBytes());
            return signature.verify(Base64.getDecoder().decode(sign));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
