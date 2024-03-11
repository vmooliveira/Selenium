package br.com.alura.leilao.login;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest {
	private LoginPage loginPage;
	
	@BeforeEach
	public void beforEach() {
		this.loginPage = new LoginPage();
	}
	
	@AfterEach
	public void afterEach() {
		this.loginPage.quit();
	}
	
	@Test
	public void loginHappyPath(){
		this.loginPage.fillInLoginForm("fulano", "pass");
		this.loginPage.sendLoginForm();

	    Assertions.assertTrue(this.loginPage.isAuctionsPage());
	    Assertions.assertEquals("fulano", this.loginPage.getLoggedUser()); 
	}
	
	@Test
	public void loginInvalidUserOrPassword() {
		this.loginPage.fillInLoginForm("teste", "123");
		this.loginPage.sendLoginForm();

		Assertions.assertTrue(this.loginPage.loginErrorMessageIsVisible());
	    Assertions.assertTrue(this.loginPage.isLoginErrorUrl());
	    Assertions.assertNull(this.loginPage.getLoggedUser());
	    Assertions.assertTrue(this.loginPage.isEnterLinkVisible());
	}
	
	@Test
	public void accessDeniedToRestirctedUrl() {
		this.loginPage.goToRestrictedPage();
	    Assertions.assertTrue(this.loginPage.isLoginPage());
	    Assertions.assertFalse(this.loginPage.isPlaceABidPage());
	}
}
