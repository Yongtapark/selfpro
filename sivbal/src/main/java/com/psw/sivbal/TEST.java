package com.psw.sivbal;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class TEST {
	public static void main(String[] args) {
		try {

			Path path = Paths.get(System.getProperty("user.dir"), "sec/main/resouce/chromdriver.exe");

			System.setProperty("webdriver.chrom.driver", path.toString());

			// Runtime.getRuntime().exec("C:/Program Files (x86)/Google/Chrome/Application/chrome.exe --remote-debugging-port=9222 --user-data-dir=\"C:/selenum/AutomationProfile\"");

			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
			ChromeDriver driver = new ChromeDriver(options);

			driver.manage().window();
			 //driver.get("https://meet.google.com/aue-jqnh-trq");

	

			Thread.sleep(500);

			System.out.println("-----------------------접속중인 명단-------------------------");
			WebElement person = driver.findElement(By.xpath("//*[@id=\"ow3\"]/div[1]/div/div[13]/div[3]/div[2]/div[1]"));

			List<WebElement> personName = person.findElements(By.xpath("//*[@class='XEazBc adnwBd']"));//LogedinUserList
			
			

			int count = 0;
			for (int i = 0; i < personName.size(); i++) {
				String online= personName.get(i).getText();
				
				System.out.println(++count + "번 " + online+" ");

			}

			System.out.println("-----------------------얼굴나온 명단-------------------------");
			

			List<WebElement> personName1 = person.findElements(By.xpath("//*[@class='XEazBc adnwBd']"));
			
			List<WebElement> videoname = person.findElements(By.xpath("//*[@class='Gv1mTb-aTv5jf']")); //videoList
			
		

			int count1 = 0;
			for (int i = 0; i < videoname.size(); i++) {
				String onAir =videoname.get(i).getAriaRole();
				//onAir 부모찾기
				WebElement onAir_Parrents_Element =videoname.get(i).findElement(By.xpath("./../../../../.."));
				WebElement onAir_Parrents = onAir_Parrents_Element.findElement(By.xpath("//*[@class='XEazBc adnwBd']"));
				String on =onAir_Parrents.getText();
				
				//online 부모찾기
				String online= personName.get(i).getText();
				WebElement online_Parrents_Elment =personName1.get(i).findElement(By.xpath("./../../../../.."));
				WebElement online_Parrents= online_Parrents_Elment.findElement(By.xpath("//*[@class='XEazBc adnwBd']"));
				String line =online_Parrents.getText();
				
				if(on.equals(line)) {
				System.out.println(++count1 + "번 " + on +" : "+onAir);}
				System.out.println("onAir_parrents ="+on+"\nonline_Parrents ="+line);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}