package Utils;

public class Algorithms {

    private static final String SHA256 = "SHA-256";
    private static final String RSA = "RSA";
    private static final String SHA256_RSA = "SHA256WithRSA";

    public static String AlgoSHA256() {
        return SHA256;
    }

    public static String AlgoRSA() {
        return RSA;
    }

    public static String AlgoSHA256_RSA() {
        return SHA256_RSA;
    }

}
