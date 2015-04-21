/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gguo.ftp;

import com.gguo.util.Utilities;
import java.io.ByteArrayInputStream;
import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

/**
 *
 * @author gguo
 */
public class UploadTask extends SwingWorker<Void, Void> {
    private static final int BUFFER_SIZE = 4096;
     
    private String host;
    private int port;
    private String username;
    private String password;
     
    private String destDir;
    private File uploadFile;
    private UploadGUI mainFrame;
     
    public UploadTask(String host, int port, String username, String password,
            String destDir, File uploadFile, UploadGUI guiFrame) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.destDir = destDir;
        this.uploadFile = uploadFile;
        this.mainFrame = guiFrame;
    }

    public UploadTask() {
         this.host = "ftp.guangzeguo.com";
        this.port = 21;
        this.username = "dianzhang@guangzeguo.com";
        this.password = "k,y_s_Ia*kUU";
        this.destDir = "/upload";
        this.uploadFile = new File("C:\\TrackingGuru\\BlueSky.properties");     
    }
 
    
    /**
     * Executed in background thread
     */
    @Override
    protected Void doInBackground() throws Exception {
        FTPUtility util = new FTPUtility(host, port, username, password);
        try {
            util.connect();
            util.uploadFile(uploadFile, destDir);
            
            
            
            //FileInputStream inputStream = new FileInputStream(uploadFile);
            
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
                percentCompleted = (int) (totalBytesRead * 100 / fileSize);
                setProgress(percentCompleted);
            }
 
            is.close();
             
            util.finish();
        } catch (FTPException ex) {
            JOptionPane.showMessageDialog(null, "Error uploading file: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);           
            ex.printStackTrace();
            setProgress(0);
            cancel(true);          
        } finally {
            util.disconnect();
        }
         
        return null;
    }
 
    /**
     * Executed in Swing's event dispatching thread
     */
    @Override
    protected void done() {
        if (!isCancelled()) {
//            JOptionPane.showMessageDialog(null,
//                    "File has been uploaded successfully!", "Message",
//                    JOptionPane.INFORMATION_MESSAGE);
            if(mainFrame != null){
            mainFrame.setInfoLabelText("File has been uploaded successfully!");
            }
        }
    }  
}