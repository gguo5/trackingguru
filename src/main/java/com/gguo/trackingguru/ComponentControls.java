/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gguo.trackingguru;

import com.gguo.trackingguru.Tracking.Logistics;
import com.gguo.util.Utilities;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import javax.swing.DefaultListModel;
import org.apache.log4j.Logger;

/**
 *
 * @author Grant
 */
public class ComponentControls {

    final static Logger logger = Logger.getLogger(ComponentControls.class.getName());

    public static boolean setSearchBtnIntialStatus(int count) {
        boolean status = count > 0 ? true : false;
        return status;
    }

    public static DefaultListModel setJListModel(String tabName) {
        DefaultListModel listModel = new DefaultListModel();

        String rootDir = "C:/TrackingGuru/";
        String filePathString = rootDir + tabName + ".properties";
        if (Utilities.fileExist(filePathString)) {
            Properties prop = Utilities.ReadPropFile(filePathString);
            for (String name : prop.stringPropertyNames()) {
                listModel.addElement(name);
            }
        }
        return listModel;
    }

    public static void AddNewProperty(String key, String value, String tabName) {

        String rootDir = "C:/TrackingGuru/";
        String filePathString = rootDir + tabName + ".properties";
        Properties prop;
        if (Utilities.fileExist(filePathString)) {
            prop = Utilities.ReadPropFile(filePathString);
        } else {
            prop = new Properties();
        }
        if (!prop.containsKey(key)) {
            prop.setProperty(key, value);
            Utilities.WriteToPropFile(prop, filePathString);
        }

    }

    static boolean removeProperty(String removedKey, String currentTabName) {
        boolean flag = true;
        String rootDir = "C:/TrackingGuru/";
        String filePathString = rootDir + currentTabName + ".properties";
        Properties prop;
        if (Utilities.fileExist(filePathString)) {
            prop = Utilities.ReadPropFile(filePathString);
            if (prop.containsKey(removedKey)) {
                prop.remove(removedKey);
                Utilities.WriteToPropFile(prop, filePathString);
            } else {
                logger.info("key doesn't exist!");
                flag = false;
            }
        } else {
            logger.info(filePathString + " not found!");
            flag = false;
        }
        return flag;
    }

    static boolean validateTrackingNumber(String tn, String currentTabName) {
        Logistics logi = Tracking.Logistics.valueOf(currentTabName);
        String regex = "";
        switch (logi) {
            case BlueSky:
                regex = "BS\\d{9}MEL";
                break;

            case EFS:
                regex = "EF\\d{9}AU";
                break;
            default:
                regex = "[a-zA-Z]{2}\\d{9}[a-zA-Z]{2,3}+";
                break;
        }       
        return tn.trim().matches(regex);
    }
}
