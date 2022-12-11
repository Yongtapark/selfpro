package com.psw.sivbal;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Hello world!
 *
 */
public class App2 {
	public static void main(String[] args) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO SOLDLOGIN VALUES(?,?,SYSDATE)");
		String on = "on";
		String off= "off";
		try {
			// DB---------------------------------------------------------------
			conn = ConUtill.getConnection();
			pstmt = conn.prepareStatement(sql.toString());

			// SELENIUM--------------------------------------------------------

			Path path = Paths.get(System.getProperty("user.dir"), "sec/main/resouce/chromdriver.exe");

			System.setProperty("webdriver.chrom.driver", path.toString());
			// Runtime.getRuntime().exec("C:/Program Files
			// (x86)/Google/Chrome/Application/chrome.exe --remote-debugging-port=9222
			// --user-data-dir=\"C:/selenum/AutomationProfile\"");

			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
			ChromeDriver driver = new ChromeDriver(options);

			driver.manage().window();
			// driver.get("https://meet.google.com/aue-jqnh-trq");

			Thread.sleep(500);

			System.out.println("-----------------------접속중인 명단-------------------------");
			WebElement person = driver
					.findElement(By.xpath("//*[@id=\"ow3\"]/div[1]/div/div[13]/div[3]/div[2]/div[1]"));
			List<WebElement> personName = person.findElements(By.xpath("//*[@class='XEazBc adnwBd']"));

			int count = 0;
			for (int i = 0; i < personName.size(); i++) {
				String online = personName.get(i).getText();

				System.out.println(++count + "번 " + online + " ");
				pstmt.setString(1, online);

			}

		//	System.out.println("-----------------------얼굴나온 명단-------------------------");

//			try {
//				List<WebElement> videoname = person.findElements(By.xpath("//*[@class='Gv1mTb-aTv5jf']"));
//				WebElement videohuman = person.findElement(By.xpath("//*[@class='XEazBc adnwBd']"));
//
//				int count1 = 0;
//				for (int i = 0; i < videoname.size(); i++) {
//					String onAir = videoname.get(i).getAriaRole();
//					// if(onAir.equals("Video"))
////					String onAir =videoname.get(i).getAriaRole();
//
//					if (onAir.equals("Video")){
//						
//						System.out.println(++count1 + "번 " + videohuman.getText() + onAir);
//						//DB로 저장
//						pstmt.setString(2, "ON");
//						
//						//DB로 저장 끝
//					} else
//						
//						System.out.println(++count1 + "번 " + videohuman.getText());
//					//DB로 저장
//					pstmt.setString(2, "OFF");
//					//DB 저장 끝
//				}
//				int j = pstmt.executeUpdate();
//				System.out.println(j + "개의 행이 추가되었습니다.");
//			} catch (NoSuchElementException e) {
//				System.out.println("화면 켜진 사람 없음");
//				e.printStackTrace();
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			int j = pstmt.executeUpdate();
//			System.out.println(j + "개의 행이 추가되었습니다.");
	//	}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}