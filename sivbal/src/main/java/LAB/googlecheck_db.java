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
		String teacher = "임미영강사";
		String teacherPresent = "임미영강사님(프레젠테이션)";
		String[] student ={"박용타","강희원","김동기","김민창","김수현","김영덕","김운용_모바일","김재현","박종봉","박종성","심정훈","안원균","유희진","윤서빈","이강훈","이문현","이예인","이정욱","진승희","최지은","하도형"};
		List<String> studentALL= Arrays.asList(student);
		Connection conn = null;
		StringBuffer sql = new StringBuffer();
		StringBuffer sql2 = new StringBuffer();
		StringBuffer sql3 = new StringBuffer();
		PreparedStatement pstmt_create = null;
		PreparedStatement pstmt_insert = null;
		PreparedStatement pstmt_commit = null;
		LocalDateTime now= LocalDateTime.now();
		
		sql.append("create table class_"+now.format(DateTimeFormatter.ofPattern("a_HH시_mm분"))+" (NAME VARCHAR2(50))");
		sql2.append("insert into class_"+now.format(DateTimeFormatter.ofPattern("a_HH시_mm분"))+" values(?)");
		sql3.append("commit");
		
		System.out.println(now.format(DateTimeFormatter.ofPattern("a_HH시_mm분")));
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

			System.out.println("인원 검색 전에 구글미팅에 접속하여 모든 인원이 보이게 만들어 준 후 x를 제외한 아무카나 누르세요.");
			System.out.println("프렌테이션이 고정으로 켜져있는 경우 에러가 발생하여 자동 종료합니다.");
			System.out.println("종료하시려면 x 키를, 계속 검색하시려면 아무키나 입력해주세요");
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


				// System.out.println("-----------------------접속중인
				// 명단-------------------------");
				WebElement person = driver.findElement(By.xpath("//*[@class='axUSnc  P9KVBf']"));// 전체명단

				List<WebElement> personName = person.findElements(By.xpath("//*[@class='XEazBc adnwBd']"));// 이름
				camOn = person.findElements(By.xpath("//*[@class='Gv1mTb-aTv5jf']"));// 비디오
				// stream = personName.stream().distinct().toArray();

//				resultList=null;
//				resultCamon=null;
//				resultCamoff=null;
				data.removeAll(data);// 실시간 인원 확인
				for (i = 0; i < personName.size(); i++) { // 이름 찾기
					online = personName.get(i).getText();
					if (!online.equals(teacher)) { // 강사님 이름 제외
						if (!online.equals(teacherPresent)) { // 강사님 프레젠테이션 제외
							data.add(online);// 이름을 데이터 리스트에 저장
						}
					}
				}
				resultList = data.stream().distinct().collect(Collectors.toList());// 최종인원 확인, 중복 데이터 검열

				nameData.removeAll(nameData);// 실시간으로 캠킨사람 체크
				// System.out.println("----------------캠 킨 사람 ---------------------------");
				for (i = 0; i < camOn.size(); i++) {// 비디오 있는인원 찾기
					cam = camOn.get(i).getAriaRole();
					String mot = camOn.get(i).findElement(By.xpath("./../../../..")).getText();// 비디오의 부모 이름이 나옴.
					if (cam.equals("Video")) {
						nameData.add(mot);
						camData.add(cam);
						if (!nameData.equals("")) {
							resultCamon = nameData.stream().distinct().collect(Collectors.toList());// 중복 리스트 제거
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
				resultCamoff = noCamData.stream().distinct().collect(Collectors.toList()); // 중복 리스트 제거
				
				//----------------------------------회의접속안한인원---------------------------------------------
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
				
				resultOnline = onlineList.stream().distinct().collect(Collectors.toList()); // 중복 리스트 제거
				System.out.println(resultOnline);

				System.out.println(
						"------------------------------------------------SOLEDESK STUDENT CHECKER----------------------------------------------------------------");

				try {
					System.out.println("접속 인원 명단\t:" + resultList);// 전체 리스트
					System.out.println("총" + resultList.size() + "명");
				} catch (NullPointerException e) {
					System.out.println("인원 없음");
				}
				System.out.println();
				System.out.println();
				try {
					System.out.println("캠 ON\t\t:" + resultCamon);// 캠 인원 이름
					System.out.println("총" + resultCamon.size() + "명");

				} catch (NullPointerException e) {
					System.out.println("인원 없음");
				}
				System.out.println();
				System.out.println();
				try {

					System.out.println("캠 OFF\t\t: " + resultCamoff);// 캠 안킨 인원
					System.out.println("총" + resultCamoff.size() + "명");
				} catch (NullPointerException e) {
					System.out.println("인원 없음");
				}
				System.out.println();
				System.out.println();
				try {

					System.out.println("미접속\t\t: " + resultOnline);// 캠 안킨 인원
					System.out.println("총" + resultOnline.size() + "명");
				} catch (NullPointerException e1) {
					System.out.println("인원 없음");
				}
				System.out.println(
						"-----------------------------------------------------------------------------------------------------------------------------------------");

				System.out.println("종료하시려면 x 키를, 계속 검색하시려면 아무키나 입력해주세요");
				s = bf.readLine();

				driver.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("모든 인원이 보이도록 해주세요. 재검색하려면 z를 입력하세요");
			s = bf.readLine();

			if (s.equalsIgnoreCase("z")) {
				while (!s.equals("x")) {
					ChromeOptions options = new ChromeOptions();
					options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
					ChromeDriver driver = new ChromeDriver(options);
					driver.manage().window();


					// System.out.println("-----------------------접속중인
					// 명단-------------------------");
					WebElement person = driver.findElement(By.xpath("//*[@class='axUSnc  P9KVBf']"));// 전체명단

					List<WebElement> personName = person.findElements(By.xpath("//*[@class='XEazBc adnwBd']"));// 이름
					camOn = person.findElements(By.xpath("//*[@class='Gv1mTb-aTv5jf']"));// 비디오
					// stream = personName.stream().distinct().toArray();

//					resultList=null;
//					resultCamon=null;
//					resultCamoff=null;
					data.removeAll(data);// 실시간 인원 확인
					for (i = 0; i < personName.size(); i++) { // 이름 찾기
						online = personName.get(i).getText();
						if (!online.equals(teacher)) { // 강사님 이름 제외
							if (!online.equals(teacherPresent)) { // 강사님 프레젠테이션 제외
								data.add(online);// 이름을 데이터 리스트에 저장
							}
						}
					}
					resultList = data.stream().distinct().collect(Collectors.toList());// 최종인원 확인, 중복 데이터 검열

					nameData.removeAll(nameData);// 실시간으로 캠킨사람 체크
					// System.out.println("----------------캠 킨 사람 ---------------------------");
					for (i = 0; i < camOn.size(); i++) {// 비디오 있는인원 찾기
						cam = camOn.get(i).getAriaRole();
						String mot = camOn.get(i).findElement(By.xpath("./../../../..")).getText();// 비디오의 부모 이름이 나옴.
						if (cam.equals("Video")) {
							nameData.add(mot);
							camData.add(cam);
							if (!nameData.equals("")) {
								resultCamon = nameData.stream().distinct().collect(Collectors.toList());// 중복 리스트 제거
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
					resultCamoff = noCamData.stream().distinct().collect(Collectors.toList()); // 중복 리스트 제거
					//----------------------------------접속안한인원---------------------------------------------
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
					
					resultOnline = onlineList.stream().distinct().collect(Collectors.toList()); // 중복 리스트 제거

					System.out.println(
							"------------------------------------------------SOLEDESK STUDENT CHECKER----------------------------------------------------------------");
						System.out.println("총 인원 :"+studentALL.size()+"명");
					try {
						System.out.println("접속 인원 명단\t:" + resultList);// 전체 리스트
						System.out.println("총" + resultList.size() + "명");
					} catch (NullPointerException e1) {
						System.out.println("인원 없음");
					}
					System.out.println();
					System.out.println();
					try {
						System.out.println("캠 ON\t\t:" + resultCamon);// 캠 인원 이름
						System.out.println("총" + resultCamon.size() + "명");

					} catch (NullPointerException e1) {
						System.out.println("인원 없음");
					}
					System.out.println();
					System.out.println();
					try {

						System.out.println("캠 OFF\t\t: " + resultCamoff);// 캠 안킨 인원
						System.out.println("총" + resultCamoff.size() + "명");
					} catch (NullPointerException e1) {
						System.out.println("인원 없음");
					}
					System.out.println();
					System.out.println();
					try {

						System.out.println("미접속\t\t: " + resultOnline);// 캠 안킨 인원
						System.out.println("총" + resultOnline.size() + "명");
					} catch (NullPointerException e1) {
						System.out.println("인원 없음");
					}
					
					
					System.out.println(
							"-----------------------------------------------------------------------------------------------------------------------------------------");

					System.out.println("종료하시려면 x 키를, 계속 검색하시려면 아무키나 입력해주세요");
					s = bf.readLine();
					if(s.equals("x")) {
					driver.get("https://meet.google.com/aue-jqnh-trq");
					driver.close();
					}
					
				}
				//테이블 생성
				try {
					pstmt_create.executeUpdate();
				} catch (SQLException e2) {
					System.out.println("테이블 생성 실패");
				}
				
				
				//테이블 인서트
				for(i=0;i<resultList.size();i++) {
					try {
						Object[] today=resultList.toArray();
						pstmt_insert.setString(1,(String) today[i]);
						pstmt_insert.executeUpdate();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				//커밋
				try {
					pstmt_commit.executeUpdate();
				}catch (Exception e3) {
					System.out.println("커밋실패");
				}
				
				

			}
		}

	}

}