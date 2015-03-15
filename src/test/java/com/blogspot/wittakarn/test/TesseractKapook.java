/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blogspot.wittakarn.test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextArea;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import java.awt.image.BufferedImage;
import javax.imageio.ImageReader;
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
public class TesseractKapook {

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
        
//        for (int i = 0; i < 10; i++) {
            try {
                Tesseract tesseract = Tesseract.getInstance();
                String result;
                
                final WebClient webClient = new WebClient(BrowserVersion.INTERNET_EXPLORER_11);

                CookieManager cookieMan = new CookieManager();
                cookieMan = webClient.getCookieManager();
                cookieMan.setCookiesEnabled(true);
                
                // Get the first page
                final HtmlPage page1 = webClient.getPage("http://hilight.kapook.com/view/117399");
                HtmlImage image = page1.<HtmlImage>getFirstByXPath("//img[@src='http://hilight.kapook.com/view/libs/img.php?id=117399']");
                
                ImageReader imageReader = image.getImageReader();
                BufferedImage bufferedImage = imageReader.read(0);

                // Get the form that we are dealing with and within that form, 
                // find the submit button and the field that we want to change.
                final HtmlForm form = page1.getFormByName("follow");

                final HtmlSubmitInput button = form.getInputByName("btnSubmit");
                final HtmlTextInput name = form.getInputByName("name");
                final HtmlTextArea answer = form.getTextAreaByName("answer");
                final HtmlTextInput inputCaptcha = form.getInputByName("inputCaptcha");

                // Change the value of the text field
                name.setValueAttribute("xxxxx");
                answer.setText("setText setText");

                result = tesseract.doOCR(bufferedImage);
                
                System.out.println("CAPTCHA result : " + result);
                
                result = result.replaceAll(" ", "");

                System.out.println("CAPTCHA result(trim) : " + result);

                inputCaptcha.setValueAttribute(result);

                // Now submit the form by clicking the button and get back the second page.
                final HtmlPage page2 = button.click();

                System.out.println(page2.asXml());

                webClient.closeAllWindows();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException ex) {
//                ex.printStackTrace();
//            }
//        }

    }

}
