/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import UI.CreateRecordPage;
import UI.LoginPage;
import Utils.FilePaths;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author ryann
 */
public class App {

    public static void main(String[] args) {
        // Create empty files if they don't exist
        try {
            File academicInfoFile = new File(FilePaths.getAcaInfoPath());
            if (!academicInfoFile.exists()) {
                academicInfoFile.getParentFile().mkdirs();
                academicInfoFile.createNewFile();
            }

            File privateKeyFile = new File(FilePaths.getPvtKeyPath());
            if (!privateKeyFile.exists()) {
                privateKeyFile.getParentFile().mkdirs();
                privateKeyFile.createNewFile();
            }

            File publicKeyFile = new File(FilePaths.getPbcKeyPath());
            if (!publicKeyFile.exists()) {
                publicKeyFile.getParentFile().mkdirs();
                publicKeyFile.createNewFile();
            }

            File emailEncryptedFile = new File(FilePaths.getEncryptEmailPath());
            if (!emailEncryptedFile.exists()) {
                emailEncryptedFile.getParentFile().mkdirs();
                emailEncryptedFile.createNewFile();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        LoginPage lp = new LoginPage();
        lp.setVisible(true);

//        CreateRecordPage cr = new CreateRecordPage();
//        cr.setVisible(true);
    }
}
