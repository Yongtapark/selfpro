package LAB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class googlecheck_db {
	public static void main(String[] args) throws IOException {
		List<Object> data = new ArrayList<>();
		List<Object> nameData = new ArrayList<>();
		List<Object> camData = new ArrayList<>();
		List<Object> noCamData = new ArrayList<>();
		List<Object> resultList = null;
		List<Object> onlineList =new ArrayList<>();
		List<Object> resultCamon = null;
		List<Object> resultCamoff = null;
		List<Object> resultOnline = null;
		List<WebElement> camOn;
		String cam;
		String online = null;
		String teacher = "�ӹ̿�����";
		String teacherPresent = "�ӹ̿������(���������̼�)";
		String[] student ={"�ڿ�Ÿ","�����","�赿��","���â","�����","�迵��","����_�����","������","������","������","������","�ȿ���","������","������","�̰���","�̹���","�̿���","������","������","������","�ϵ���"};
		List<String> studentALL= Arrays.asList(student);
		Connection conn = null;
		StringBuffer sql = new StringBuffer();
		StringBuffer sql2 = new StringBuffer();
		StringBuffer sql3 = new StringBuffer();
		PreparedStatement pstmt_create = null;
		PreparedStatement pstmt_insert = null;
		PreparedStatement pstmt_commit = null;
		LocalDateTime now= LocalDateTime.now();
		
		sql.append("create table class_"+now.format(DateTimeFormatter.ofPattern("a_HH��_mm��"))+" (NAME VARCHAR2(50))");
		sql2.append("insert into class_"+now.format(DateTimeFormatter.ofPattern("a_HH��_mm��"))+" values(?)");
		sql3.append("commit");
		
		System.out.println(now.format(DateTimeFormatter.ofPattern("a_HH��_mm��")));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String s = null;
		int i=0;
		try {
			conn = DBUtil.getConnection();
			pstmt_create = conn.prepareStatement(sql.toString());
			pstmt_insert=conn.prepareStatement(sql2.toString());
			pstmt_commit=conn.prepareStatement(sql3.toString());
			
			Path path = Paths.get(System.getProperty("user.dir"), "sec/main/resouce/chromdriver.exe");

			System.setProperty("webdriver.chrom.driver", path.toString());

			System.out.println("�ο� �˻� ���� ���۹��ÿ� �����Ͽ� ��� �ο��� ���̰� ����� �� �� x�� ������ �ƹ�ī�� ��������.");
			System.out.println("�������̼��� �������� �����ִ� ��� ������ �߻��Ͽ� �ڵ� �����մϴ�.");
			System.out.println("�����Ͻ÷��� x Ű��, ��� �˻��Ͻ÷��� �ƹ�Ű�� �Է����ּ���");
			s = bf.readLine();

			try {
				Runtime.getRuntime().exec(
						"C:/Program Files (x86)/Google/Chrome/Application/chrome.exe --remote-debugging-port=9222 --user-data-dir=\"C:/selenum/AutomationProfile\"");

			} catch (Exception e) {
				Runtime.getRuntime().exec(
						"C:/Program Files/Google/Chrome/Application/chrome.exe --remote-debugging-port=9222 --user-data-dir=\"C:/selenum/AutomationProfile\"");
			}

			while (!s.equals("x")) {

				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
				ChromeDriver driver = new ChromeDriver(options);
				driver.manage().window();

				driver.get("https://meet.google.com/aue-jqnh-trq");

				Thread.sleep(2000);
				
				try {
					driver.findElement(By.xpath("//*[@id=\"yDmH0d\"]/div[3]/div[2]/div/div[2]/button/span")).click();
				}catch(Exception e){
					
					driver.findElement(By.xpath(
							"//*[@id=\"yDmH0d\"]/c-wiz/div/div/div[13]/div[3]/div/div[1]/div[4]/div/div/div[2]/div/div[2]/div/div[1]/div[1]/button/span"))
					.click();
				}
				Thread.sleep(2000);
				driver.findElement(By.xpath(
						"//*[@id=\"yDmH0d\"]/c-wiz/div/div/div[13]/div[3]/div/div[1]/div[4]/div/div/div[2]/div/div[2]/div/div[1]/div[1]/button/span"))
				.click();


				// System.out.println("-----------------------��������
				// ���-------------------------");
				WebElement person = driver.findElement(By.xpath("//*[@class='axUSnc  P9KVBf']"));// ��ü���

				List<WebElement> personName = person.findElements(By.xpath("//*[@class='XEazBc adnwBd']"));// �̸�
				camOn = person.findElements(By.xpath("//*[@class='Gv1mTb-aTv5jf']"));// ����
				// stream = personName.stream().distinct().toArray();

//				resultList=null;
//				resultCamon=null;
//				resultCamoff=null;
				data.removeAll(data);// �ǽð� �ο� Ȯ��
				for (i = 0; i < personName.size(); i++) { // �̸� ã��
					online = personName.get(i).getText();
					if (!online.equals(teacher)) { // ����� �̸� ����
						if (!online.equals(teacherPresent)) { // ����� ���������̼� ����
							data.add(online);// �̸��� ������ ����Ʈ�� ����
						}
					}
				}
				resultList = data.stream().distinct().collect(Collectors.toList());// �����ο� Ȯ��, �ߺ� ������ �˿�

				nameData.removeAll(nameData);// �ǽð����� ķŲ��� üũ
				// System.out.println("----------------ķ Ų ��� ---------------------------");
				for (i = 0; i < camOn.size(); i++) {// ���� �ִ��ο� ã��
					cam = camOn.get(i).getAriaRole();
					String mot = camOn.get(i).findElement(By.xpath("./../../../..")).getText();// ������ �θ� �̸��� ����.
					if (cam.equals("Video")) {
						nameData.add(mot);
						camData.add(cam);
						if (!nameData.equals("")) {
							resultCamon = nameData.stream().distinct().collect(Collectors.toList());// �ߺ� ����Ʈ ����
						}
					}
				}
				noCamData.removeAll(noCamData);
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
				
				//----------------------------------ȸ�����Ӿ����ο�---------------------------------------------
				onlineList.removeAll(onlineList);
				for (Object c : studentALL) {
					boolean isEquals = false;
					for (Object d : resultList) {
						if (c.equals(d))
							isEquals = true;
					}
					if (!isEquals) {
						onlineList.add(c);
					}
				}
				
				resultOnline = onlineList.stream().distinct().collect(Collectors.toList()); // �ߺ� ����Ʈ ����
				System.out.println(resultOnline);

				System.out.println(
						"------------------------------------------------SOLEDESK STUDENT CHECKER----------------------------------------------------------------");

				try {
					System.out.println("���� �ο� ���\t:" + resultList);// ��ü ����Ʈ
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
				System.out.println();
				System.out.println();
				try {

					System.out.println("������\t\t: " + resultOnline);// ķ ��Ų �ο�
					System.out.println("��" + resultOnline.size() + "��");
				} catch (NullPointerException e1) {
					System.out.println("�ο� ����");
				}
				System.out.println(
						"-----------------------------------------------------------------------------------------------------------------------------------------");

				System.out.println("�����Ͻ÷��� x Ű��, ��� �˻��Ͻ÷��� �ƹ�Ű�� �Է����ּ���");
				s = bf.readLine();

				driver.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("��� �ο��� ���̵��� ���ּ���. ��˻��Ϸ��� z�� �Է��ϼ���");
			s = bf.readLine();

			if (s.equalsIgnoreCase("z")) {
				while (!s.equals("x")) {
					ChromeOptions options = new ChromeOptions();
					options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
					ChromeDriver driver = new ChromeDriver(options);
					driver.manage().window();


					// System.out.println("-----------------------��������
					// ���-------------------------");
					WebElement person = driver.findElement(By.xpath("//*[@class='axUSnc  P9KVBf']"));// ��ü���

					List<WebElement> personName = person.findElements(By.xpath("//*[@class='XEazBc adnwBd']"));// �̸�
					camOn = person.findElements(By.xpath("//*[@class='Gv1mTb-aTv5jf']"));// ����
					// stream = personName.stream().distinct().toArray();

//					resultList=null;
//					resultCamon=null;
//					resultCamoff=null;
					data.removeAll(data);// �ǽð� �ο� Ȯ��
					for (i = 0; i < personName.size(); i++) { // �̸� ã��
						online = personName.get(i).getText();
						if (!online.equals(teacher)) { // ����� �̸� ����
							if (!online.equals(teacherPresent)) { // ����� ���������̼� ����
								data.add(online);// �̸��� ������ ����Ʈ�� ����
							}
						}
					}
					resultList = data.stream().distinct().collect(Collectors.toList());// �����ο� Ȯ��, �ߺ� ������ �˿�

					nameData.removeAll(nameData);// �ǽð����� ķŲ��� üũ
					// System.out.println("----------------ķ Ų ��� ---------------------------");
					for (i = 0; i < camOn.size(); i++) {// ���� �ִ��ο� ã��
						cam = camOn.get(i).getAriaRole();
						String mot = camOn.get(i).findElement(By.xpath("./../../../..")).getText();// ������ �θ� �̸��� ����.
						if (cam.equals("Video")) {
							nameData.add(mot);
							camData.add(cam);
							if (!nameData.equals("")) {
								resultCamon = nameData.stream().distinct().collect(Collectors.toList());// �ߺ� ����Ʈ ����
							}
						}
					}
					noCamData.removeAll(noCamData);
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
					//----------------------------------���Ӿ����ο�---------------------------------------------
					onlineList.removeAll(onlineList);
					for (Object c : studentALL) {
						boolean isEquals = false;
						for (Object d : resultList) {
							if (c.equals(d))
								isEquals = true;
						}
						if (!isEquals) {
							onlineList.add(c);
						}
					}
					
					resultOnline = onlineList.stream().distinct().collect(Collectors.toList()); // �ߺ� ����Ʈ ����

					System.out.println(
							"------------------------------------------------SOLEDESK STUDENT CHECKER----------------------------------------------------------------");
						System.out.println("�� �ο� :"+studentALL.size()+"��");
					try {
						System.out.println("���� �ο� ���\t:" + resultList);// ��ü ����Ʈ
						System.out.println("��" + resultList.size() + "��");
					} catch (NullPointerException e1) {
						System.out.println("�ο� ����");
					}
					System.out.println();
					System.out.println();
					try {
						System.out.println("ķ ON\t\t:" + resultCamon);// ķ �ο� �̸�
						System.out.println("��" + resultCamon.size() + "��");

					} catch (NullPointerException e1) {
						System.out.println("�ο� ����");
					}
					System.out.println();
					System.out.println();
					try {

						System.out.println("ķ OFF\t\t: " + resultCamoff);// ķ ��Ų �ο�
						System.out.println("��" + resultCamoff.size() + "��");
					} catch (NullPointerException e1) {
						System.out.println("�ο� ����");
					}
					System.out.println();
					System.out.println();
					try {

						System.out.println("������\t\t: " + resultOnline);// ķ ��Ų �ο�
						System.out.println("��" + resultOnline.size() + "��");
					} catch (NullPointerException e1) {
						System.out.println("�ο� ����");
					}
					
					
					System.out.println(
							"-----------------------------------------------------------------------------------------------------------------------------------------");

					System.out.println("�����Ͻ÷��� x Ű��, ��� �˻��Ͻ÷��� �ƹ�Ű�� �Է����ּ���");
					s = bf.readLine();
					if(s.equals("x")) {
					driver.get("https://meet.google.com/aue-jqnh-trq");
					driver.close();
					}
					
				}
				//���̺� ����
				try {
					pstmt_create.executeUpdate();
				} catch (SQLException e2) {
					System.out.println("���̺� ���� ����");
				}
				
				
				//���̺� �μ�Ʈ
				for(i=0;i<resultList.size();i++) {
					try {
						Object[] today=resultList.toArray();
						pstmt_insert.setString(1,(String) today[i]);
						pstmt_insert.executeUpdate();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				//Ŀ��
				try {
					pstmt_commit.executeUpdate();
				}catch (Exception e3) {
					System.out.println("Ŀ�Խ���");
				}
				
				

			}
		}

	}

}