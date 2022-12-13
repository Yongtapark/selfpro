package LAB;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TEST {
	public static void main(String[] args) {
		List<Object> data = new ArrayList<>();
		String[] priceArr = new String[5];
		try {

			Path path = Paths.get(System.getProperty("user.dir"), "sec/main/resouce/chromdriver.exe");

			System.setProperty("webdriver.chrom.driver", path.toString());

			// Runtime.getRuntime().exec("C:/Program Files
			// (x86)/Google/Chrome/Application/chrome.exe --remote-debugging-port=9222
			// --user-data-dir=\"C:/selenum/AutomationProfile\"");

			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
			ChromeDriver driver = new ChromeDriver(options);

			driver.manage().window();
			// driver.get("https://www.ekapepia.com/index.do");

			Thread.sleep(500);

			System.out.println("-----------------------접속중인 명단-------------------------");
			WebElement person = driver.findElement(By.xpath("//*[@class='box-wrap']"));

			List<WebElement> personName = person.findElements(By.xpath("//*[@class='price']"));// LogedinUserList

			for (int i = 0; i < personName.size(); i++) {
				String online = personName.get(i).getText();
				if (!online.equals("")) {
					data.add(online);
					System.out.println(online);
				}
			}
			System.out.println(data);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}