/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blogspot.wittakarn.test;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
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
public class TesseractUrlExample {

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
        Tesseract tesseract = Tesseract.getInstance();
        tesseract.setDatapath(null);
        String result;
        try {
            final WebClient webClient = new WebClient();

            // Get the first page
            final HtmlPage page1 = webClient.getPage("http://localhost/captchaConcept/image-captcha.php");
            HtmlImage image = page1.<HtmlImage>getFirstByXPath("//img[@src='image.php']");
            ImageReader imageReader = image.getImageReader();
            BufferedImage bufferedImage = imageReader.read(0);
            
            // Get the form that we are dealing with and within that form, 
            // find the submit button and the field that we want to change.
            final HtmlForm form = page1.getForms().get(0);

            final HtmlSubmitInput button = form.getInputByName("submit");
            final HtmlTextInput userField = form.getInputByName("user");
            final HtmlPasswordInput passwordField = form.getInputByName("password");
            final HtmlTextInput requestField = form.getInputByName("requestField");

            // Change the value of the text field
            userField.setValueAttribute("captcha");
            passwordField.setValueAttribute("captcha");

            result = tesseract.doOCR(bufferedImage);
            
            System.out.println("CAPTCHA result : " + result);
            
            requestField.setValueAttribute(result.trim());

            // Now submit the form by clicking the button and get back the second page.
            final HtmlPage page2 = button.click();

            System.out.println(page2.asXml());

            webClient.closeAllWindows();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
