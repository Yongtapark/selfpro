package LAB;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TEST_DB_CONNECT {
	public static void main(String[] args) {
		String dml = "insert into STUDENT_ATTEND values(?,sysdate)";
		String USER_CAM;
		Connection con = null;
		PreparedStatement pstmt = null;

		List<Object> data = new ArrayList<>();
		List<Object> nameData = new ArrayList<>();
		List<Object> camData = new ArrayList<>();
		String online = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(dml);


			Path path = Paths.get(System.getProperty("user.dir"), "sec/main/resouce/chromdriver.exe");

			System.setProperty("webdriver.chrom.driver", path.toString());
			
			
			 Runtime.getRuntime().exec("C:/Program Files (x86)/Google/Chrome/Application/chrome.exe --remote-debugging-port=9222 --user-data-dir=\"C:/selenum/AutomationProfile\"");

			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
			ChromeDriver driver = new ChromeDriver(options);

			driver.manage().window();
			// driver.get("https://meet.google.com/aue-jqnh-trq");

			Thread.sleep(500);

			System.out.println("-----------------------�������� ���-------------------------");
			WebElement person = driver.findElement(By.xpath("//*[@class='axUSnc  P9KVBf']"));// ��ü���

			List<WebElement> personName = person.findElements(By.xpath("//*[@class='XEazBc adnwBd']"));// �̸�
			List<WebElement> camOn = person.findElements(By.xpath("//*[@class='Gv1mTb-aTv5jf']"));// ����
			// stream = personName.stream().distinct().toArray();
			int i = 0;
			for (i = 0; i < personName.size(); i++) { // �̸� ã��
				online = personName.get(i).getText();

				// if (!online.equals("")) {
				data.add(online);// �̸��� ������ ����Ʈ�� ����
				pstmt.setString(1, online);
				pstmt.executeUpdate();
				System.out.println(online);
				// }
			}
			System.out.println("�� �ο� :" + i + "��");

			int j = 0;
			System.out.println("----------------ķ Ų ��� ---------------------------");
			 dml = "insert into ATTEND_LIST values(?,?,NULL)";
			 con = DBUtil.getConnection();
			pstmt = con.prepareStatement(dml);
			 
			for (i = 0; i < camOn.size(); i++) {// ���� �ִ��ο� ã��
				String cam = camOn.get(i).getAriaRole();
				String mot = camOn.get(i).findElement(By.xpath("./../../../..")).getText();// ������ �θ� �̸��� ����.
				if (cam.equals("Video")) {
					++j;
					nameData.add(mot);
					camData.add(cam);
					pstmt.setString(1, mot);
					pstmt.setString(2, cam);
					System.out.println(mot + " =" + cam);
					pstmt.executeUpdate();
				}
			}
			System.out.println(j + "��");

			System.out.println(data);// ��ü ����Ʈ
			System.out.println(nameData);// ķ �ο� �̸�
			// System.out.println(camData);// ķŲ ����
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				// 6. �����ͺ��̽����� ���ῡ ���Ǿ��� ������Ʈ�� ����
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
}