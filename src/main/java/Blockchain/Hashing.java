package Blockchain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import Blockchain.Salt;
import org.apache.commons.codec.binary.Hex;

public class Hashing {

    //hashing with salt
    public static String hashBlock(byte[] blockBytes, String algorithm) {
        MessageDigest md;
        try {
            //instantiate the MD object
            md = MessageDigest.getInstance(algorithm);

            //fetch block as bytes and updated into MD
            md.update(blockBytes);
            md.update("SAIMS".getBytes());
            md.update(Salt.generate());

            //digest it
            byte[] hashBytes = md.digest();

            //convert to Hex format with Hex API from Apache common
            return String.valueOf(Hex.encodeHex(hashBytes));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //standard hashing
    public static String hashContentData(byte[] contentDataBytes, String algorithm) {
        MessageDigest md;
        try {
            //instantiate the MD object
            md = MessageDigest.getInstance(algorithm);

            //fetch input to MD
            md.update(contentDataBytes);
            md.update("SAIMS".getBytes());

            //digest it
            byte[] hashBytes = md.digest();
            //convert to Hex format with Hex API from Apache common
            return String.valueOf(Hex.encodeHex(hashBytes));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
