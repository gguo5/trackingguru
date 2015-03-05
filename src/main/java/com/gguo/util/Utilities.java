/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gguo.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gguo
 */
public class Utilities {

    public static boolean fileExist(String path) {
        boolean flag = false;
        File f = new File(path);
        if (f.exists() && !f.isDirectory()) {
            flag = true;
        }
        return flag;
    }

    public static String setJTextAreaText() {
        String filePathString = "C:/temp/tn.txt";
        String out = "";
        BufferedReader br = null;
        if (fileExist(filePathString)) {
            try {
                br = new BufferedReader(new FileReader(filePathString));
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    sb.append(System.getProperty("line.separator"));
                    line = br.readLine();
                }
                out = sb.toString();
            } catch (FileNotFoundException e) {
                Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, e);
            } catch (IOException e) {
               Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, e);
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return out;
    }

    public static void saveJTextAreaToFile(String cleanedTracking) {
        FileOutputStream fop = null;
		File file;
		String content = cleanedTracking;
 
		try {
 
			file = new File("c:/temp/tn.txt");
			
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
                        
                        fop = new FileOutputStream(file);
			// get the content in bytes
			byte[] contentInBytes = content.getBytes();
 
			fop.write(contentInBytes);
			fop.flush();
			fop.close();
 
			//System.out.println("Write Done");
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fop != null) {
					fop.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }
}
