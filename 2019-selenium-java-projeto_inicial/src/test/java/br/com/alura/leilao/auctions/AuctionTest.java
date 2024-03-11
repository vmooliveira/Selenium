package br.com.alura.leilao.auctions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.login.LoginPage;

public class AuctionTest {
	private AuctionsPage auctionsPage;
	
	@AfterEach
	public void afterEach() {
		auctionsPage.quit();
	}
	
	@Test
	public void createNewAuction(){
		LoginPage loginPage = new LoginPage();
		loginPage.fillInLoginForm("fulano", "pass");
		this.auctionsPage = loginPage.sendLoginForm();
		NewAuctionPage newAuctionPage = auctionsPage.clickOnNewAuctionButton();
		
		String today = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	    String name = "Leilao do dia " + today;
	    String value = "500.00";
	    
		newAuctionPage.fillInNewAuctionForm(name, today, value);
		newAuctionPage.saveForm();
		
		Assertions.assertTrue(auctionsPage.isAuctionRegistered(name, today, value));
	}
	
	@Test
	public void blankFormFieldMessages() {
		LoginPage loginPage = new LoginPage();
		loginPage.fillInLoginForm("fulano", "pass");
		this.auctionsPage = loginPage.sendLoginForm();
		NewAuctionPage newAuctionPage = auctionsPage.clickOnNewAuctionButton();  
		newAuctionPage.fillInNewAuctionForm("", "", "");
		newAuctionPage.saveForm();
		Assertions.assertTrue(newAuctionPage.blankFormFieldMessagesAreVisible());
		Assertions.assertTrue(newAuctionPage.isNewAuctionPageUrl());
	}
}