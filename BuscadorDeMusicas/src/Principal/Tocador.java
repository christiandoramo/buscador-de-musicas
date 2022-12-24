package Principal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tocador {
	WebDriver navegador;
	float contador;

	public Tocador() {
		navegador = new ChromeDriver();
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Paulo\\Desktop\\Buscador de Músicas Automático\\chromedriver.exe");
	}

	public Boolean pularAnuncio() {
		esperar(16000);
		String anuncioXpath = "/div/div[3]/div/div[2]/span/button/div"; 
		Object pula = null;
		try {
			pula = navegador.findElement(By.xpath(anuncioXpath));
		} catch (Exception e) {
			System.out.println("NÃO PEGOU O PULAR ANUNCIO " + e);
		}
		if (pula != null) {
			WebElement pulaAnuncioElement = (WebElement) pula;
			pulaAnuncioElement.click();
			return false;
		}
		return true;
	}

	public Boolean tocarMusica() {
		esperar(8000);
		String playXpath = "/html/body/ytd-app/div[1]/ytd-page-manager/ytd-watch-flexy/div[5]/div[1]/div/div[1]/div[2]/div/div/ytd-player/div/div/div[4]/button";
		Object play = null;
		try {
			play = navegador.findElement(By.xpath(playXpath));
		} catch (Exception e) {
			System.out.println("NÃO PEGOU O PLAY DA MUSICA" + e);
		}
		if (play != null) {
			WebElement playElement = (WebElement) play;
			playElement.click();
			contadorMusica();
			esperar(20000);
			return true;
		}
		return false;
	}

	public void trocarMusica(String url) {
		navegador.get(url);
		Boolean executou = false;
		executou = tocarMusica(); // se executou executou recebe true
		// testa se tem anuncio e se deve tentar tocar novamente
		if (!executou) {
			executou = pularAnuncio(); // se executou executou recebe false
		}
		if (!executou) {
			executou = tocarMusica();
		}

	}

	public void esperar(int miselesimos) {
		try {
			Thread.sleep(miselesimos);
		} catch (Exception e) {
			System.out.println("BUG EM ESPERAR" + e);
		}
	}

	public void contadorMusica() {
		String duracaoXpath = "/html/body/ytd-app/div[1]/ytd-page-manager/ytd-watch-flexy/div[5]/div[1]/div/div[1]/div[2]/div/div/ytd-player/div/div/div[27]/div[2]/div[1]/div[1]/span[2]/span[3]";
		Object duracao = navegador.findElement(By.xpath(duracaoXpath));
		if (duracao != null) {
			WebElement duracaoElem = (WebElement) duracao;
			String duracaoString = duracaoElem.getAttribute("innerHTML");
			System.out.println(duracaoString);
		}
	}

	public void encerrarMusicas() {
		navegador.close();
	}

}