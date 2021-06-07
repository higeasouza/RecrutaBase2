package org.example;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExemplosSelenium {

    @Test
    public void helloWorld(){
        WebDriver driver = new ChromeDriver();

        driver.navigate().to("http://google.com");

        Assert.assertEquals("Google", driver.getTitle());

        driver.quit();
    }

    //Acessar o sistema Mantis
    //Preencher usuário
    //Preencher senha
    //Clicar em login

    //Usuário autenticado com sucesso
    @Test
    public void efetuarLoginComSucesso(){
        WebDriver driver = new ChromeDriver();

        driver.navigate().to("https://mantis-prova.base2.com.br/");

        WebElement usernameField = driver.findElement(By.name("username"));

        WebElement passwordField = driver.findElement(By.name("password"));

        WebElement loginButton = driver.findElement(By.xpath("//input[@value='Login']"));

        usernameField.sendKeys("treinamento01");
        passwordField.sendKeys("123456");
        loginButton.click();

        WebElement usuarioLogadoText = driver.findElement(By.xpath("//td[@class='login-info-left']/span[@class='italic']"));
        String usuarioLogado = usuarioLogadoText.getText();

        Assert.assertEquals("Treinamento01",usuarioLogado);
    }

    @Test
    public void exemploSincronizacao(){
        WebDriver driver = new ChromeDriver ();

        driver.manage().window().maximize();

        driver.navigate().to("http://blackmirror.crowdtest.me.s3-website-us-east-1.amazonaws.com/auth");

        WebElement prosseguirButton = driver.findElement(By.linkText("Prosseguir"));

        prosseguirButton.click();

        WebElement emailField = driver.findElement(By.id ("login"));
        WebElement passwordField = driver.findElement(By.id ("password"));
        WebElement entrarButton = driver.findElement(By.xpath( "//button[text()='ENTRAR']"));

        emailField.sendKeys("jose.mario@base2.com.br");
        passwordField.sendKeys("123456"); //senha invalida
        entrarButton.click();

        //Instancia objeto de espera
        WebDriverWait wait = new WebDriverWait(driver,10);

        //Esperar o elemento existir no HTML
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[@class='login-error']/span[@class='error-msg']")));

        //Existe? Se sim, instancia o WebElement
        WebElement erroText = driver.findElement(By.xpath("//p[@class='login-error']/span[@class='error-msg']"));

        //Espera o elemento ser visível
        wait.until(ExpectedConditions.visibilityOf(erroText));

        //Realizo a interação com o elemento
        String erroExibido = erroText.getText();

        Assert.assertEquals("E-mail ou senha inválidos.",erroExibido);
    }


}
