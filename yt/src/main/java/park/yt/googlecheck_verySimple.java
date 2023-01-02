package park.yt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class googlecheck_verySimple {
	static List<Object> data = new ArrayList<>();
	static List<Object> nameData = new ArrayList<>();
	static List<Object> camData = new ArrayList<>();
	static List<Object> noCamData = new ArrayList<>();
	static List<Object> resultList;
	static List<Object> onlineList =new ArrayList<>();
	static List<Object> resultCamon = null;
	static List<Object> resultCamoff = null;
	static List<Object> resultOffline = null;
	static List<WebElement> camOn;
	static WebElement person =null;
	static String cam;
	static String online = null;
	static String teacher = "임미영강사"; //강사님 이름을 적으세요
	static String teacherPresent = "임미영강사님(프레젠테이션)"; //강사님 이름을 적으세요(프레젠테이션)은 지우지 말아주세요. ex)ㅇㅇㅇ강사님(프레젠테이션)
	//학생 이름을 기입해 주세요
	static String[] student ={"박용타","강희원","김동기","김민창","김수현","김영덕","김운용_모바일","김재현","박종봉","박종성","심정훈","안원균","유희진","윤서빈","이강훈","이문현","이예인","이정욱","진승희","최지은","하도형"};
	static List<String> studentALL= Arrays.asList(student);
	public static void main(String[] args) throws IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String s = null;
		int i=0;
		try {

			Path path = Paths.get(System.getProperty("user.dir"), "sec/main/resouce/chromdriver.exe");

			System.setProperty("webdriver.chrom.driver", path.toString());


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

				driver.get("https://meet.google.com/aue-jqnh-trq");//구글 미트 주소

				Thread.sleep(2000);

				driver.findElement(By.xpath(
						"//*[@id=\"yDmH0d\"]/c-wiz/div/div/div[13]/div[3]/div/div[1]/div[4]/div/div/div[2]/div/div[2]/div/div[1]/div[1]/button/span"))
						.click();

				// System.out.println("-----------------------접속중인
				// 명단-------------------------");
				try{
					person = driver.findElement(By.xpath("//*[@class='axUSnc  P9KVBf']"));// 전체명단
				}catch (Exception e1) {
					System.out.println("고정된 프레젠테이션이나 동영상이 없어야합니다");
				}finally {
					person = driver.findElement(By.xpath("//*[@class='axUSnc  P9KVBf']"));// 전체명단
				}

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
							if(!online.equals("나")) {
								data.add(online);// 이름을 데이터 리스트에 저장
							}
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
				
				resultOffline = onlineList.stream().distinct().collect(Collectors.toList()); // 중복 리스트 제거
				System.out.println(resultOffline);

				System.out.println(
						"------------------------------------------------ STUDENT CHECKER----------------------------------------------------------------");

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

					System.out.println("미접속\t\t: " + resultOffline);// 미접속 인원
					System.out.println("총" + resultOffline.size() + "명");
				} catch (NullPointerException e1) {
					System.out.println("인원 없음");
				}
				System.out.println(
						"----------------------------------------------------------------------------------------------------------------------------------");

				System.out.println("종료하시려면 x 키를, 계속 최신화 하시려면 z키를 입력해주세요");
				s = bf.readLine();

				driver.close();
			}

		} catch (Exception e) {
			e.printStackTrace();

			if (s.equals("z")) {
				while (!s.equals("x")) {
					ChromeOptions options = new ChromeOptions();
					options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
					ChromeDriver driver = new ChromeDriver(options);
					driver.manage().window();


					// System.out.println("-----------------------접속중인
					// 명단-------------------------");
					try{
						person = driver.findElement(By.xpath("//*[@class='axUSnc  P9KVBf']"));// 전체명단
					}catch (Exception e1) {
					e1.printStackTrace();
					}finally {
						person = driver.findElement(By.xpath("//*[@class='axUSnc  P9KVBf']"));// 전체명단
					}

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
								if(!online.equals("나")) {
									data.add(online);// 이름을 데이터 리스트에 저장
								}
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
					
					resultOffline = onlineList.stream().distinct().collect(Collectors.toList()); // 중복 리스트 제거

					System.out.println(
							"------------------------------------------------STUDENT CHECKER----------------------------------------------------------------");
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

						System.out.println("미접속\t\t: " + resultOffline);// 미접속 인원
						System.out.println("총" + resultOffline.size() + "명");
					} catch (NullPointerException e1) {
						System.out.println("인원 없음");
					}
					
					
					System.out.println(
							"----------------------------------------------------------------------------------------------------------------------------------");

					System.out.println("종료하시려면 x 키를, 계속 최신화 하시려면 z키를 입력해주세요");
				
				
					driver.get("https://meet.google.com/aue-jqnh-trq");
					driver.close();
					
					
				}
				

			}
		}

			
		}
	List<String> studentALL(){
	
		return studentALL;
	}
	List<Object> resultList(){
		return resultList;
	}
	List<Object> resultCamon(){
		return resultCamon;
	}
	
	List<Object> resultCamoff(){
		return resultCamoff;
	}
	List<Object> resultOffline(){
		return resultOffline;
	}

}
