package www.project;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws IOException, InterruptedException {
		Path path = Paths.get(System.getProperty("user.dir"), "sec/main/resouce/chromdriver.exe");

		System.setProperty("webdriver.chrom.driver", path.toString());
	Runtime.getRuntime().exec(
				"C:/Program Files/Google/Chrome/Application/chrome.exe --remote-debugging-port=9222 --user-data-dir=\"C:/selenum/AutomationProfile\"");

		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
		ChromeDriver driver = new ChromeDriver(options);

		// driver.executeScript("window.open('about:blink','_blank)');");

		List<String> tabs = new ArrayList<String>(driver.getWindowHandles());

		driver.switchTo().window(tabs.get(0));

		driver.get("https://meet.google.com/aue-jqnh-trq");
		
		Thread.sleep(1000);
		
		/* WebElement elements = */ driver.findElement(By.className("VfPpkd-vQzf8d")).click();
		
		Thread.sleep(1000);
		
		if(
				driver.findElement(By.className("Gv1mTb-aTv5jf")) != null
				) {
			
			WebElement elements =driver.findElement(By.xpath("//*[@id=\"ow3\"]/div[1]/div/div[13]/div[3]"));
			if(elements !=null) {
				System.out.println(elements.getText());
			}
		}
			
		
		
		

	}
}
