/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gguo.util;

import java.io.File;

/**
 *
 * @author gguo
 */
public class Utilities {

    public static boolean fileExist(String path) {
        boolean flag = false;
        File f = new File(path);
        if (f.exists() && !f.isDirectory()) { flag = true; }
        return flag;
    }
}
