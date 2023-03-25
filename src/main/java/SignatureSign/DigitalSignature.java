package SignatureSign;

import KeyGenerator.KeyGenerator;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class DigitalSignature {

    private Signature sig;
    private KeyGenerator keyGen;
    private KeyPair keyPair;
    private static PublicKey publicKey;
    private static PrivateKey privateKey;

    public DigitalSignature() {
        super();
        try {
            keyGen = new KeyGenerator();
            keyPair = keyGen.getKeyGen().generateKeyPair();
            sig = Signature.getInstance(Utils.GeneralOperation.getSha_rsa_Algo());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //set the private key for one-time use sign-in
    public void setPrivateKey() throws Exception {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyPair.getPrivate().getEncoded());
        KeyFactory keyFactory = KeyFactory.getInstance(Utils.GeneralOperation.getRsa_Algo());
        this.privateKey = keyFactory.generatePrivate(keySpec);
    }
    
    //set the public key for one-time use login
    public void setPublicKey() throws Exception {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyPair.getPublic().getEncoded());
        KeyFactory kf = KeyFactory.getInstance(Utils.GeneralOperation.getRsa_Algo());
        this.publicKey = kf.generatePublic(keySpec);
    }

    //used to sign the provided data using the private key
    public String sign(String data) throws Exception {
        setPrivateKey();
        sig.initSign(privateKey);
        sig.update(data.getBytes());
        return Base64.getEncoder().encodeToString(sig.sign());
    }

    //used to verify the provided signature against the provided data using the public key
    public boolean verify(String data, String signature) throws Exception {
        setPublicKey();
        System.out.println("Data: " + data);
        System.out.println("Signature: " + signature);
        sig.initVerify(publicKey);
        sig.update(data.getBytes());
        return sig.verify(Base64.getDecoder().decode(signature));
    }

}
