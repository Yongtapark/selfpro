package park.yt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FINAL_RESULT0 {
	public static void main(String[] args) {
		List<Object> data = new ArrayList<>();
		List<Object> nameData = new ArrayList<>();
		List<Object> camData = new ArrayList<>();
		List<Object> noCamData = new ArrayList<>();
		String online = null;
		String teacher = "임미영강사";
		String teacherPresent ="임미영강사님(프레젠테이션)";
		int i = 0;
		int j = 0;
		String x="t";
		BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
		try {

			Path path = Paths.get(System.getProperty("user.dir"), "sec/main/resouce/chromdriver.exe");
			System.out.println(path.toString());
			System.setProperty("webdriver.chrom.driver", path.toString());
			
			while(!x.equals("z")) {
			try {
				System.out.println("디버깅모드를 실행하려면 'a'를 실행이 안된다면 b를 입력하세요. 실행중이라면 z키를 누르세요");
				x=bf.readLine();
				if(x.equals("b")) {
					Runtime.getRuntime().exec("C:/Program Files/Google/Chrome/Application/chrome.exe --remote-debugging-port=9222 --user-data-dir=\"C:/selenum/AutomationProfile\"");
				}else if(x.equals("a")) {
					Runtime.getRuntime().exec("C:/Program Files (x86)/Google/Chrome/Application/chrome.exe --remote-debugging-port=9222 --user-data-dir=\"C:/selenum/AutomationProfile\"");
				}
				
			}catch (IOException e) {
				System.out.println("디버깅모드를 실행하려면 'a'를 실행이 안된다면 b를 입력하세요. 실행중이라면 z키를 누르세요");
				x=bf.readLine();
				if(x.equals("b")) {
					Runtime.getRuntime().exec("C:/Program Files/Google/Chrome/Application/chrome.exe --remote-debugging-port=9222 --user-data-dir=\"C:/selenum/AutomationProfile\"");
				}else if(x.equals("a")) {
					Runtime.getRuntime().exec("C:/Program Files (x86)/Google/Chrome/Application/chrome.exe --remote-debugging-port=9222 --user-data-dir=\"C:/selenum/AutomationProfile\"");
				}
			}
				
			}
			
			
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
			ChromeDriver driver = new ChromeDriver(options);

			driver.manage().window();
			//driver.get("https://meet.google.com/aue-jqnh-trq");

			System.out.println("구글 로그인 후에 구글미트 회의에 접속한 후 아무키를 눌러주세요, 단, 전체 인원이 모두 보여야 합니다.(고정스크린이 있으면 안됩니다.)");
			
			String z=bf.readLine();
			
		
			//-----------------------접속중인 명단-------------------------
			WebElement person = driver.findElement(By.xpath("//*[@class='axUSnc  P9KVBf']"));// 전체명단

			List<WebElement> personName = person.findElements(By.xpath("//*[@class='XEazBc adnwBd']"));// 이름
			List<WebElement> camOn = person.findElements(By.xpath("//*[@class='Gv1mTb-aTv5jf']"));// 비디오
			
			for (i = 0; i < personName.size(); i++) { // 이름 찾기
				online = personName.get(i).getText();
				if(!online.equals(teacher)) { //강사님 이름 제외
					if(!online.equals(teacherPresent)) { //강사님 프레젠테이션 제외
						data.add(online);// 이름을 데이터 리스트에 저장
					}
				}
			}
			List<Object> result =data.stream().distinct().collect(Collectors.toList());//최종인원 확인, 중복 데이터 검열



			
			//----------------------캠 킨 명단-------------------------
			for (i = 0; i < camOn.size(); i++) {// 비디오 있는인원 찾기
				String cam = camOn.get(i).getAriaRole();
				String mot = camOn.get(i).findElement(By.xpath("./../../../..")).getText();// 비디오의 부모 이름이 나옴.
				if (cam.equals("Video")) {
					++j;
					nameData.add(mot);
					camData.add(cam);
				}
			}
			
			for(Object a : result) {
				boolean isEquals=false;
				for(Object b: nameData) {
					if(a.equals(b)) isEquals =true;
				}
				if(!isEquals) {
					noCamData.add(a);
				}
			}
			
			
			System.out.println("------------------------------------------------SOLEDESK STUDENT CHECKER----------------------------------------------------------------");

			System.out.println("접속 인원 명단\t\t:"+result);//전체 리스트
			System.out.println("총"+result.size()+"명");
			System.out.println();
			System.out.println("캠 ON\t\t\t:"+nameData);//캠 인원 이름
			System.out.println("총"+nameData.size()+"명");
			System.out.println();
			System.out.println("캠 OFF\t\t\t: "+noCamData);//캠 안킨 인원
			System.out.println("총"+noCamData.size()+"명");
			System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
			
			System.out.println("끝내려면 아무키나 누르세요");
			bf.readLine();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}