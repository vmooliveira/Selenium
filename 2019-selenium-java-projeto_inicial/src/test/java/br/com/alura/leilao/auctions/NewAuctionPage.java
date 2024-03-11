package br.com.alura.leilao.auctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewAuctionPage extends PageObject {
	private static final String URL_LEILOES = "http://localhost:8080/leiloes";
	
	public NewAuctionPage(WebDriver browser){
		super(browser);
	}
	
	public void fillInNewAuctionForm(String name, String date, String startingBid) {
		this.browser.findElement(By.id("nome")).sendKeys(name);
		this.browser.findElement(By.id("valorInicial")).sendKeys(startingBid);
		this.browser.findElement(By.id("dataAbertura")).sendKeys(date);
	}
	
	public void saveForm() {
		this.browser.findElement(By.id("button-submit")).click();
	}
	
	public boolean blankFormFieldMessagesAreVisible() {
		String pageSource =  this.browser.getPageSource();
		
		return 	pageSource.contains("não deve estar em branco")
				&& pageSource.contains("minimo 3 caracteres")
				&& pageSource.contains("deve ser um valor maior de 0.1")
				&& pageSource.contains("deve ser uma data no formato dd/MM/yyyy");
	}
	
	public boolean isNewAuctionPageUrl() {
		return this.browser.getCurrentUrl().contains(URL_LEILOES);
	}
}
