/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gguo.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Properties;
import java.util.logging.Level;
import javax.swing.DefaultListModel;
import org.apache.log4j.Logger;

/**
 *
 * @author gguo
 */
public class Utilities {

    final static Logger logger = Logger.getLogger(Utilities.class.getName());

    public static boolean fileExist(String path) {
        boolean flag = false;
        File f = new File(path);
        if (f.exists() && !f.isDirectory()) {
            flag = true;
        }
        return flag;
    }
    
   

    public static boolean fileExist(String path, boolean create) {
        boolean flag = false;
        File f = new File(path);
        if (f.exists() && !f.isDirectory()) {
            flag = true;
        } else {
            try {
                f.createNewFile();
                flag = true;
            } catch (IOException ex) {
                logger.error("IOException");
            }
        }
        return flag;
    }

    public static boolean FolderExist(String path, boolean create) {
        boolean flag = false;

        File theDir = new File(path);

        // if the directory does not exist, create it
        if (!theDir.exists()) {
            System.out.println("creating directory: " + path);

            try {
                theDir.mkdir();
                flag = true;
            } catch (SecurityException se) {
                //handle it
            }
            if (flag) {
                System.out.println("DIR created");
            }
        } else {
            System.out.println("DIR EXIST!");
            flag = true;
        }
        return flag;
    }

    public static String readFileToString(String filePath) {

        String out = "";
        BufferedReader br = null;
        if (fileExist(filePath)) {
            try {
                br = new BufferedReader(new FileReader(filePath));
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    sb.append(System.getProperty("line.separator"));
                    line = br.readLine();
                }
                out = sb.toString();
            } catch (FileNotFoundException e) {
                logger.error("FileNotFoundException", e);
            } catch (IOException e) {
                logger.error("IOException", e);
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException ex) {
                        logger.error("IOException", ex);
                    }
                }
            }
        } else {
            logger.info(filePath + " doesn't exists.");
        }
        return out;
    }

    public static String readFileToString(File f) {

        String out = "";
        BufferedReader br = null;
        if (f.exists() && !f.isDirectory()) {
            try {
                br = new BufferedReader(new FileReader(f));
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    sb.append(System.getProperty("line.separator"));
                    line = br.readLine();
                }
                out = sb.toString();
            } catch (FileNotFoundException e) {
                logger.error("FileNotFoundException", e);
            } catch (IOException e) {
                logger.error("IOException", e);
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException ex) {
                        logger.error("IOException", ex);
                    }
                }
            }
        } else {
            logger.info(f.getAbsolutePath() + " doesn't exists.");
        }
        return out;
    }

    public static String setJTextAreaText(String tabName) {
        String rootDir = "C:/TrackingGuru/";
        String filePathString = rootDir + tabName + ".txt";
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
                logger.error("FileNotFoundException", e);
            } catch (IOException e) {
                logger.error("IOException", e);
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException ex) {
                        logger.error("IOException", ex);
                    }
                }
            }
        } else {
            logger.info(filePathString + " doesn't exists.");
        }
        return out;
    }

    public static void saveJTextAreaToFile(String cleanedTracking, String tabName) {
        FileOutputStream fop = null;
        File file;
        String content = cleanedTracking;

        String rootDir = "C:/TrackingGuru/";
        String filePathString = rootDir + tabName + ".txt";
        try {

            file = new File(filePathString);

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
            logger.error("IOException", e);
        } finally {
            try {
                if (fop != null) {
                    fop.close();
                }
            } catch (IOException e) {
                logger.error("IOException", e);
            }
        }
    }

    public static Properties ReadPropFile(String filePath) {
        Properties prop = new Properties();
        InputStream input = null;
        try {

            input = new FileInputStream(filePath);

            // load a properties file
            prop.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return prop;
    }
    
    public static Properties ReadPropFile(File file) {
        Properties prop = new Properties();
        InputStream input = null;
        try {

            input = new FileInputStream(file);

            // load a properties file
            prop.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return prop;
    }

    public static void WriteToPropFile(Properties property, String filePath) {

        Properties prop = property;

        OutputStream output = null;

        try {
            output = new FileOutputStream(filePath);
            // save properties to project root folder
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void PrintClasspath() {
        ClassLoader cl = ClassLoader.getSystemClassLoader();

        URL[] urls = ((URLClassLoader) cl).getURLs();

        for (URL url : urls) {
            logger.debug(url);
        }
    }

    public static String getPropValueByKey(String tableName, String key) {
        String rootDir = "C:/TrackingGuru/";
        String filePathString = rootDir + tableName + ".properties";
        String value = "";
        Properties props = ReadPropFile(filePathString);
        if (props.containsKey(key)) {
            value = props.getProperty(key);
        }

        return value;
    }
}
