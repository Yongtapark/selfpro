package www.project.copy;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sun.org.apache.xpath.internal.operations.Div;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		try {

			Path path = Paths.get(System.getProperty("user.dir"), "sec/main/resouce/chromdriver.exe");

			System.setProperty("webdriver.chrom.driver", path.toString());
			//Runtime.getRuntime().exec(
					//"C:/Program Files/Google/Chrome/Application/chrome.exe --remote-debugging-port=9222 --user-data-dir=\"C:/selenum/AutomationProfile\"");

			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
			ChromeDriver driver = new ChromeDriver(options);

			driver.manage().window();
			 //driver.get("https://meet.google.com/aue-jqnh-trq");

	

			Thread.sleep(500);

			System.out.println("-----------------------접속중인 명단-------------------------");
			WebElement person = driver.findElement(By.xpath("//*[@id=\"ow3\"]/div[1]/div/div[13]/div[3]/div[2]/div[1]"));

			List<WebElement> personName = person.findElements(By.xpath("//*[@class='XEazBc adnwBd']"));
			
			

			int count = 0;
			for (int i = 0; i < personName.size(); i++) {
				String online= personName.get(i).getText();
				
				System.out.println(++count + "번 " + online+" ");

			}

			System.out.println("-----------------------얼굴나온 명단-------------------------");
			
			
			List<WebElement> videoname = person.findElements(By.xpath("//*[@class='Gv1mTb-aTv5jf']"));
		

			int count1 = 0;
			for (int i = 0; i < videoname.size(); i++) {
				String onAir =videoname.get(i).getAriaRole();
				if(onAir.equals("Video"))
			
				System.out.println(++count1 + "번 " + onAir);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
