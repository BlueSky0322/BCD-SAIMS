package BasicFunctions;

import Cryptography.Asymmetric;
import KeyGenerator.KeyAccess;
import SignatureSign.DigitalSignature;
import Utils.FilePaths;
import Utils.Algorithms;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

public class Auth {

    private static final DigitalSignature DIGITAL_SIGNATURE = new DigitalSignature();
    private static final Asymmetric ASYMMETRIC = new Asymmetric(Algorithms.AlgoRSA());
    private static final String CREDENTIAL_PATH = FilePaths.getEncryptEmailPath();
    private static String signature;

    public static boolean login(String email) {
        try {
            File f = new File(CREDENTIAL_PATH);
            BufferedReader br = new BufferedReader(new FileReader(f));
            String retrievedLine = br.readLine();
            while (retrievedLine != null) {
                if (ASYMMETRIC.decrypt(retrievedLine, KeyAccess.getPrivateKey()).equals(email)) {
                    signature = DIGITAL_SIGNATURE.signIn(email);
                    //System.out.println("Auth Email: " + email + "\nAuth Signature: " + signature);
                    return true;
                }
                retrievedLine = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean register(String email) {
        try {

            File f = new File(CREDENTIAL_PATH);
            BufferedReader br = new BufferedReader(new FileReader(f));
            String checkLine = br.readLine();
            while (checkLine != null) {
                if (ASYMMETRIC.decrypt(checkLine, KeyAccess.getPrivateKey()).equals(email)) {
                    //JOptionPane.showMessageDialog(null,"This is a registered email\nDirecting to Login Page!");
                    //System.out.println(false);
                    return false;
                }
                checkLine = br.readLine();
            }
            String cipherText = ASYMMETRIC.encrypt(email, KeyAccess.getPublicKey());
            if (f.exists()) {
                Files.writeString(Paths.get(CREDENTIAL_PATH), cipherText + "\n", StandardOpenOption.APPEND);
            } else {
                Files.writeString(Paths.get(CREDENTIAL_PATH), cipherText + "\n", StandardOpenOption.CREATE);
            }
            return true;
            //JOptionPane.showMessageDialog(null,"Email registered successfully.\nDirecting to Login Page");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean verifyEmail(String email, String sign) {
        try {
            //System.out.println("Auth class verify: " + DIGITAL_SIGNATURE.DSverify(email, sign));
            return DIGITAL_SIGNATURE.DSverify(email, sign);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public void logout() {
        System.out.println("Logout successfully");
    }

    public static String getSignature() {
        return signature;
    }
}
