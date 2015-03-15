/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blogspot.wittakarn.app;

import java.io.File;
import net.sourceforge.tess4j.Tesseract;

/**
 *
 * @author Wittakarn
 */
public class TesseractExample {

    public static void main(String[] args) {

//        System.setProperty("jna.library.path", "32".equals(System.getProperty("sun.arch.data.model")) ? "lib/win32-x86" : "lib/win32-x86-64");
        Tesseract tesseract = Tesseract.getInstance();
        tesseract.setDatapath(null);
        File file;
        String result;
        try {
            file = new File("C:\\Users\\Wittakarn\\Desktop\\img.png");
            result = tesseract.doOCR(file);
            System.out.println(result);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
//
//    protected static void setEnv(Map<String, String> newenv) {
//        try {
//            Class<?> processEnvironmentClass = Class.forName("java.lang.ProcessEnvironment");
//            Field theEnvironmentField = processEnvironmentClass.getDeclaredField("theEnvironment");
//            theEnvironmentField.setAccessible(true);
//            Map<String, String> env = (Map<String, String>) theEnvironmentField.get(null);
//            env.putAll(newenv);
//            Field theCaseInsensitiveEnvironmentField = processEnvironmentClass.getDeclaredField("theCaseInsensitiveEnvironment");
//            theCaseInsensitiveEnvironmentField.setAccessible(true);
//            Map<String, String> cienv = (Map<String, String>) theCaseInsensitiveEnvironmentField.get(null);
//            cienv.putAll(newenv);
//        } catch (NoSuchFieldException e) {
//            try {
//                Class[] classes = Collections.class.getDeclaredClasses();
//                Map<String, String> env = System.getenv();
//                for (Class cl : classes) {
//                    if ("java.util.Collections$UnmodifiableMap".equals(cl.getName())) {
//                        Field field = cl.getDeclaredField("m");
//                        field.setAccessible(true);
//                        Object obj = field.get(env);
//                        Map<String, String> map = (Map<String, String>) obj;
//                        map.clear();
//                        map.putAll(newenv);
//                    }
//                }
//            } catch (Exception e2) {
//                e2.printStackTrace();
//            }
//        } catch (Exception e1) {
//            e1.printStackTrace();
//        }
//    }

}
