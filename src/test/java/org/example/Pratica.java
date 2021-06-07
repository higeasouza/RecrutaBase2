package org.example;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Pratica {
    @Test
    public void cadastrarNovaOcorrenciaComSucesso(){
        WebDriver driver = new ChromeDriver();

        driver.navigate().to("https://mantis-prova.base2.com.br/");

        WebElement usernameField = driver.findElement(By.name("username"));

        WebElement passwordField = driver.findElement(By.name("password"));

        WebElement loginButton = driver.findElement(By.xpath("//input[@value='Login']"));

        usernameField.sendKeys("treinamento04");
        passwordField.sendKeys("123456");
        loginButton.click();

        WebElement usuarioLogadoText = driver.findElement(By.xpath("//td[@class='login-info-left']/span[@class='italic']"));
        String usuarioLogado = usuarioLogadoText.getText();

        Assert.assertEquals("Treinamento04",usuarioLogado);

        //Clicar em Relatar Caso
        WebElement relatarCasoButton = driver.findElement(By.linkText("Relatar Caso"));
        relatarCasoButton.click();

        //Selecionar Categoria
        WebElement categoria = driver.findElement(By.name("category_id"));

        Select combo = new Select(categoria);
        combo.selectByVisibleText("[Todos os Projetos] Apptest");

        //Selecionar Frequência
        WebElement frequencia = driver.findElement(By.name("reproducibility"));

        Select frequenciaCombo = new Select(frequencia);
        frequenciaCombo.selectByVisibleText("aleatório");

        //Selecionar Gravidade
        WebElement gravidade = driver.findElement(By.name("severity"));

        Select gravidadeCombo = new Select (gravidade);
        gravidadeCombo.selectByVisibleText("pequeno");

        //Selecionar Prioridade
        WebElement prioridade = driver.findElement(By.name("priority"));

        Select prioridadeCombo = new Select(prioridade);
        prioridadeCombo.selectByVisibleText("alta");

        //Preencher Resumo
        WebElement resumo = driver.findElement(By.name("summary"));
        resumo.sendKeys("Teste Recruta Base2");

        //Preencher Descrição
        WebElement descricao = driver.findElement(By.name("description"));
        descricao.sendKeys("Teste realizado para o treinamento Recruta Base2");

        //Preencher Passos para reproduzir
        WebElement passosReproduzir = driver.findElement(By.name("steps_to_reproduce"));
        passosReproduzir.sendKeys("Preencher todos os campos");

        //Clicar em nova tarefa
        WebElement enviarRelatorioButton = driver.findElement(By.xpath("//input[@value='Enviar Relatório']"));
        enviarRelatorioButton.click();

        //System.out.println(driver.getPageSource());

        //Verificar se é exibida a mensagem "Operação realizada com sucesso"
        WebElement mensagemSucesso = driver.findElement(By.xpath("//div[@align='center']"));
        String valida = mensagemSucesso.getText();
        Assert.assertTrue(valida.contains("Operação realizada com sucesso."));

        valida = valida.replace("Operação realizada com sucesso.\n" +
                "[ Visualizar Caso Enviado ", "");
        String numeroCaso = valida.replace(" ] [ Ver Casos ]", "");

        WebElement acessarCaso = driver.findElement(By.cssSelector("a[href$='" + numeroCaso + "']"));
        acessarCaso.click();

        WebElement verificaCategoria = driver.findElement(By.xpath("//tr[@class='row-1']//td[3]"));
        String validarCategoria = verificaCategoria.getText();
        Assert.assertEquals("[Todos os Projetos] Apptest",validarCategoria);

        WebElement verificaFrequencia = driver.findElement(By.xpath("//tr[7]/td[6]"));
        String validarFrequencia = verificaFrequencia.getText();
        Assert.assertEquals("aleatório",validarFrequencia);

        WebElement verificaGravidade = driver.findElement(By.xpath("//tr[7]/td[4]"));
        String validarGravidade = verificaGravidade.getText();
        Assert.assertEquals("pequeno",validarGravidade);

        WebElement verificaResumo = driver.findElement(By.xpath("//tr[11]/td[2]"));
        String validarResumo = verificaResumo.getText();
        Assert.assertTrue(validarResumo.contains("Teste Recruta Base2"));


    }



}
