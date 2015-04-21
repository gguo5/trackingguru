/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gguo.ftp;

import com.gguo.util.Utilities;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author gguo
 */
public class UploadOnExit {

    private static final int BUFFER_SIZE = 4096;

    private static final String host = "ftp.guangzeguo.com";
    private static final int port = 21;
    private static final String username = "dianzhang@guangzeguo.com";
    private static final String password = "k,y_s_Ia*kUU";

    private static final String destDir = "/upload";
    private static final File uploadFile = new File("C:\\TrackingGuru\\BlueSky.properties");

    public static void Save() {

        FTPUtility util = new FTPUtility(host, port, username, password);
        try {
            util.connect();
            util.uploadFile(uploadFile, destDir);

            String tnStr = Utilities.getTrackingNumnberJSONString(uploadFile.getAbsolutePath());
            ByteArrayInputStream is = new ByteArrayInputStream(tnStr.getBytes());

            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;
            long totalBytesRead = 0;
            int percentCompleted = 0;
            long fileSize = tnStr.length();
            //long fileSize = uploadFile.length();

            while ((bytesRead = is.read(buffer)) != -1) {
                util.writeFileBytes(buffer, 0, bytesRead);
                totalBytesRead += bytesRead;
                //percentCompleted = (int) (totalBytesRead * 100 / fileSize);

            }

            is.close();

            util.finish();
        } catch (FTPException ex) {
            JOptionPane.showMessageDialog(null, "Error uploading file: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();

        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error uploading file: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();

        }finally {
            try {
                util.disconnect();
            } catch (FTPException ex) {
                Logger.getLogger(UploadOnExit.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
