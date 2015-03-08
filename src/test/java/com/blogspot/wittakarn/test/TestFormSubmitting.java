/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blogspot.wittakarn.test;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Wittakarn
 */
public class TestFormSubmitting {

    public TestFormSubmitting() {
    }

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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void submittingForm() throws Exception {
        final WebClient webClient = new WebClient();

        // Get the first page
        final HtmlPage page1 = webClient.getPage("http://localhost/captchaConcept/no-captcha.php");

        // Get the form that we are dealing with and within that form, 
        // find the submit button and the field that we want to change.
        final HtmlForm form = page1.getForms().get(0);

        final HtmlSubmitInput button = form.getInputByName("submit");
        final HtmlTextInput userField = form.getInputByName("user");
        final HtmlPasswordInput passwordField = form.getInputByName("password");

        // Change the value of the text field
        userField.setValueAttribute("captcha");
        passwordField.setValueAttribute("captcha");

        // Now submit the form by clicking the button and get back the second page.
        final HtmlPage page2 = button.click();
        
        System.out.println(page2.asXml());

        webClient.closeAllWindows();
    }
}
