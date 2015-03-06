/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gguo.trackingguru;

import com.gguo.util.Utilities;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import javax.swing.DefaultListModel;

/**
 *
 * @author Grant
 */
public class ComponentControls {

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
}
