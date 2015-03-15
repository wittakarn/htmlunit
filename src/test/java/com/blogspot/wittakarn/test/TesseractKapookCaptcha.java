/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blogspot.wittakarn.test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.UnexpectedPage;
import com.gargoylesoftware.htmlunit.WebClient;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import net.sourceforge.tess4j.Tesseract;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Wittakarn
 */
public class TesseractKapookCaptcha {

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void submittingForm() {
        
        for (int i = 0; i < 10; i++) {
            try {
                Tesseract tesseract = Tesseract.getInstance();
                String result;
                
                final WebClient webClient = new WebClient(BrowserVersion.INTERNET_EXPLORER_11);

                CookieManager cookieMan = new CookieManager();
                cookieMan = webClient.getCookieManager();
                cookieMan.setCookiesEnabled(true);
                
                // Get the first page
                final UnexpectedPage unexpeted = webClient.getPage("http://hilight.kapook.com/view/libs/img.php?id=117399");

                BufferedImage bufferedImage = ImageIO.read(unexpeted.getInputStream());
                result = tesseract.doOCR(bufferedImage);
                
                System.out.println("CAPTCHA result : " + result);
                
                result = result.replaceAll(" ", "");

                System.out.println("CAPTCHA result(trim) : " + result);

                webClient.closeAllWindows();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

}
