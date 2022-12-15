package LAB;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class test2hop {
	public static void main(String[] args) {
		String dml = "insert into STUDENT_ATTEND (NAME,CAMON,CAMOFF,TODAY)values(?,?,?,sysdate)";
		String USER_CAM;
		Connection con = null;
		PreparedStatement pstmt = null;
		List<Object> data = new ArrayList<>();
		List<Object> nameData = new ArrayList<>();
		List<Object> camData = new ArrayList<>();
		List<Object> noCamData = new ArrayList<>();
		List<Object> resultList = null;
		;
		List<Object> resultCamon = null;
		List<Object> resultCamoff = null;

		String online = null;
		String teacher = "�ӹ̿�����";
		String teacherPresent = "�ӹ̿������(���������̼�)";
		String camF = null;
		String sqlList;
		String sqlCamOn;
		String sqlCamoff;
		int i = 0;
		int j = 0;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(dml);

			Path path = Paths.get(System.getProperty("user.dir"), "sec/main/resouce/chromdriver.exe");

			System.setProperty("webdriver.chrom.driver", path.toString());

			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("ũ�� ����� ��带 �����մϴ�. �� â�� �����Ϸ��� a��, �̹� �������� ����� ��尡 �ִٸ� �ƹ�Ű�� ��������");
			String s = bf.readLine();
			if (s.equalsIgnoreCase("a")) {

				try {
					Runtime.getRuntime().exec(
							"C:/Program Files (x86)/Google/Chrome/Application/chrome.exe --remote-debugging-port=9222 --user-data-dir=\"C:/selenum/AutomationProfile\"");

				} catch (Exception e) {
					Runtime.getRuntime().exec(
							"C:/Program Files/Google/Chrome/Application/chrome.exe --remote-debugging-port=9222 --user-data-dir=\"C:/selenum/AutomationProfile\"");
				}
			} else {

			}
			System.out.println("�ο� �˻� ���� ���۹��ÿ� �����Ͽ� ��� �ο��� ���̰� ����� �� �� x�� ������ �ƹ�ī�� ��������.");
			System.out.println("�����Ͻ÷��� x Ű��, ��� �˻��Ͻ÷��� �ƹ�Ű�� �Է����ּ���");
			s = bf.readLine();
			while (!s.equalsIgnoreCase("x")) {
				resultList = null;
				resultCamon = null;
				resultCamoff = null;
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
				ChromeDriver driver = new ChromeDriver(options);

				driver.manage().window();
				// driver.get("https://meet.google.com/aue-jqnh-trq");

				// System.out.println("-----------------------��������
				// ���-------------------------");
				WebElement person = driver.findElement(By.xpath("//*[@class='axUSnc  P9KVBf']"));// ��ü���

				List<WebElement> personName = person.findElements(By.xpath("//*[@class='XEazBc adnwBd']"));// �̸�
				List<WebElement> camOn = person.findElements(By.xpath("//*[@class='Gv1mTb-aTv5jf']"));// ����
				// stream = personName.stream().distinct().toArray();

				for (i = 0; i < personName.size(); i++) { // �̸� ã��
					online = personName.get(i).getText();
					if (!online.equals(teacher)) { // ����� �̸� ����
						if (!online.equals(teacherPresent)) { // ����� ���������̼� ����
							data.add(online);// �̸��� ������ ����Ʈ�� ����
						}
					}
				}
				resultList = data.stream().distinct().collect(Collectors.toList());// �����ο� Ȯ��, �ߺ� ������ �˿�

				// j = 0;
				// System.out.println("----------------ķ Ų ��� ---------------------------");
				for (i = 0; i < camOn.size(); i++) {// ���� �ִ��ο� ã��
					String cam = camOn.get(i).getAriaRole();
					String mot = camOn.get(i).findElement(By.xpath("./../../../..")).getText();// ������ �θ� �̸��� ����.
					if (cam.equals("Video")) {
						++j;
						nameData.add(mot);
						camData.add(cam);
						if (!nameData.equals("")) {
							resultCamon = nameData.stream().distinct().collect(Collectors.toList());// �ߺ� ����Ʈ ����
						}
					}
				}

				for (Object a : resultList) {
					boolean isEquals = false;
					for (Object b : nameData) {
						if (a.equals(b))
							isEquals = true;
					}
					if (!isEquals) {
						noCamData.add(a);
					}
				}
				resultCamoff = noCamData.stream().distinct().collect(Collectors.toList()); // �ߺ� ����Ʈ ����

				System.out.println(
						"------------------------------------------------SOLEDESK STUDENT CHECKER----------------------------------------------------------------");

				try {
					System.out.println("���� �ο� ���\t:" + resultList);// ���� ���� ����Ʈ
					System.out.println("��" + resultList.size() + "��");
				} catch (NullPointerException e) {
					System.out.println("�ο� ����");
				}
				System.out.println();
				System.out.println();
				try {
					System.out.println("ķ ON\t\t:" + resultCamon);// ķ �ο� �̸�
					System.out.println("��" + resultCamon.size() + "��");

				} catch (NullPointerException e) {
					System.out.println("�ο� ����");
				}
				System.out.println();
				System.out.println();
				try {

					System.out.println("ķ OFF\t\t: " + resultCamoff);// ķ ��Ų �ο�
					System.out.println("��" + resultCamoff.size() + "��");
				} catch (NullPointerException e) {
					System.out.println("�ο� ����");
				}
				System.out.println(
						"-----------------------------------------------------------------------------------------------------------------------------------------");

				System.out.println("�����Ͻ÷��� x Ű��, ��� �˻��Ͻ÷��� �ƹ�Ű�� �Է����ּ���");
				s = bf.readLine();

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				for (i = 0; i < resultList.size(); i++) {
					try {
						sqlList = resultList.get(i).toString();
						pstmt.setString(1, sqlList);
					} catch (NullPointerException e) {
						pstmt.setString(1, "����");
					} finally {

						try {
							sqlCamOn = resultCamon.get(i).toString();
							pstmt.setString(2, sqlCamOn);
						} catch (NullPointerException e) {
							pstmt.setString(2, "����");
						} finally {

							try {
								sqlCamoff = resultCamoff.get(i).toString();
								pstmt.setString(3, sqlCamoff);

							} catch (NullPointerException e) {
								pstmt.setString(3, "����");
							}
						}
					}
				}
				pstmt.executeUpdate();

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