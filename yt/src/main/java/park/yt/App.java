package park.yt;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class App {
	public static void main(String[] args) {
		List<Object> data = new ArrayList<>();
		List<Object> nameData = new ArrayList<>();
		List<Object> camData = new ArrayList<>();
		String[] name1;
		Object[] cam1;
		String online = null;
		try {

			Path path = Paths.get(System.getProperty("user.dir"), "sec/main/resouce/chromdriver.exe");

			System.setProperty("webdriver.chrom.driver", path.toString());
			// Runtime.getRuntime().exec("C:/ProgramFiles/Google/Chrome/Application/chrome.exe
			// --remote-debugging-port=9222--user-data-dir=\"C:/selenum/AutomationProfile\"");

			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
			ChromeDriver driver = new ChromeDriver(options);

			driver.manage().window();
			// driver.get("https://meet.google.com/aue-jqnh-trq");

			Thread.sleep(500);

			System.out.println("-----------------------접속중인 명단-------------------------");
			WebElement person = driver.findElement(By.xpath("//*[@class='axUSnc  P9KVBf']"));// 전체명단

			List<WebElement> personName = person.findElements(By.xpath("//*[@class='XEazBc adnwBd']"));// 이름
			List<WebElement> camOn = person.findElements(By.xpath("//*[@class='Gv1mTb-aTv5jf']"));// 비디오
			//stream = personName.stream().distinct().toArray();
			int i = 0;
			for (i = 0; i < personName.size(); i++) { // 이름 찾기
				online = personName.get(i).getText();

				// if (!online.equals("")) {
				data.add(online);// 이름을 데이터 리스트에 저장
				System.out.println(online);
				// }
			}
			System.out.println("총 인원 :" + i + "명");

			int j = 0;
			System.out.println("----------------캠 킨 사람 ---------------------------");
			for (i = 0; i < camOn.size(); i++) {// 비디오 있는인원 찾기
				String cam = camOn.get(i).getAriaRole();
				String mot = camOn.get(i).findElement(By.xpath("./../../../..")).getText();// 비디오의 부모 이름이 나옴.
				if (cam.equals("Video")) {
					++j;
					nameData.add(mot);
					camData.add(cam);
					System.out.println(mot + " =" + cam);
				}
			}
			System.out.println(j + "명");

			System.out.println(data);//전체 리스트
			System.out.println(nameData);//캠 인원 이름
			//System.out.println(camData);// 캠킨 여부
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}