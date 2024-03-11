package br.com.alura.leilao.auctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AuctionsPage extends PageObject {
	private static final String URL_LEILOES = "http://localhost:8080/leiloes";
	
	public AuctionsPage(WebDriver browser) {
		super(browser);
	}
	
	public NewAuctionPage clickOnNewAuctionButton() {
		this.browser.findElement(By.id("novo_leilao_link")).click();
		return new NewAuctionPage(browser);
	}
	
	public boolean isAuctionRegistered(String name, String date, String value) {
		WebElement tableLine = this.browser.findElement(By.cssSelector("#auction-table tbody tr:last-child"));
		WebElement columnName = tableLine.findElement(By.cssSelector("td:nth-child(1)"));
		WebElement columnDate = tableLine.findElement(By.cssSelector("td:nth-child(2)"));
		WebElement columnValue = tableLine.findElement(By.cssSelector("td:nth-child(3)"));
		
		System.out.println("columnName = " + columnName);
		System.out.println("name = " + name);
		System.out.println("columnDate = " + columnDate);
		System.out.println("date = " + date);
		System.out.println("columnValue = " + columnValue);
		System.out.println("value = " + value);
		return columnName.getText().equals(name) && columnDate.getText().equals(date) && columnValue.getText().equals(value);	
	}
	
}
