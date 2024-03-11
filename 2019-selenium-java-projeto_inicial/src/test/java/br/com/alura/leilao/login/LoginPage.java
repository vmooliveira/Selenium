package br.com.alura.leilao.login;

import org.openqa.selenium.By;
import br.com.alura.leilao.auctions.AuctionsPage;
import br.com.alura.leilao.auctions.PageObject;

public class LoginPage extends PageObject {
	
	private static final String URL_LOGIN = "http://localhost:8080/login";
	
	public LoginPage() {
		super(null);
		this.browser.navigate().to(URL_LOGIN);
	}	
	
	public void fillInLoginForm(String username, String password) {
		this.browser.findElement(By.cssSelector("input[name='username']")).sendKeys(username);
		this.browser.findElement(By.name("password")).sendKeys(password);
	}
	
	public AuctionsPage sendLoginForm() {
		this.browser.findElement(By.xpath("//button[text()='Login']")).submit();
		return new AuctionsPage(this.browser);
	}
	
	public boolean isAuctionsPage() {
		return this.browser.getCurrentUrl().equals("http://localhost:8080/leiloes") ;
	}
	
	public boolean isPlaceABidPage() {
		return this.browser.getPageSource().contains("Dados do Leilão");
	}
	
	public boolean isLoginPage() {
		return this.browser.getCurrentUrl().equals("http://localhost:8080/login");
	}
	
	public String getLoggedUser() {
		try {
			return this.browser.findElement(By.id("usuario-logado")).getText();
		} catch (Exception e) {
			return null;
		}
	}
	
	public boolean loginErrorMessageIsVisible() {
		return this.browser.getPageSource().contains("Usuário e senha inválidos.");
	}
	
	public boolean isLoginErrorUrl() {
		return this.browser.getCurrentUrl().equals("http://localhost:8080/login?error");
	}
	
	public boolean isEnterLinkVisible() {
		return this.browser.findElement(By.xpath("//a[contains(text(),'Entrar')]")).isDisplayed();
	}
	
	public void goToRestrictedPage() {
		this.browser.navigate().to("http://localhost:8080/leiloes/2");
	}
}