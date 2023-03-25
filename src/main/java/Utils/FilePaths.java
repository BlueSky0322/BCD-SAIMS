/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

/**
 *
 * @author ryann
 */
public class FilePaths {

    private static final String ACADEMIC_INFO = ".\\AcademicInfo\\Blockchain\\Ledger.txt";
    private static final String PRIVATE_KEY = ".\\AcademicInfo\\Key\\PrivateKey\\PrivateKey.txt";
    private static final String PUBLIC_KEY = ".\\AcademicInfo\\Key\\PublicKey\\PublicKey.txt";
    private static final String EMAIL_ENCRYPTED = ".\\AcademicInfo\\Userdata\\EmailEncrypted.txt";
    private static final String XLSX = ".\\AcademicInfo\\XLSX\\StudentAcademicInfo.xlsx";
    private static final String CHAIN_FILE = ".\\MasterBinary\\ChainFile.bin";

    public static String getAcaInfoPath() {
        return ACADEMIC_INFO;
    }

    public static String getPvtKeyPath() {
        return PRIVATE_KEY;
    }

    public static String getPbcKeyPath() {
        return PUBLIC_KEY;
    }

    public static String getEncryptEmailPath() {
        return EMAIL_ENCRYPTED;
    }

    public static String getXLSXPath() {
        return XLSX;
    }

    public static String getChainFilePath() {
        return CHAIN_FILE;
    }
}
