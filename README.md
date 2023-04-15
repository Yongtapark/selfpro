# Google Meet Student Checker

Google Meet에서 학생들의 캠 상태와 미접속 학생 명단을 확인하는 Java 프로그램입니다.

## 주의사항

현재 이 코드는 해당 오류로 인해 실행되지 않습니다.

"현재 연결은 원격 호스트에 의해 강제로 끊겼습니다"



## 개발 동기

나는 지난 한 해 동안 온라인으로 수업을 진행하면서, 학생들이 화면을 공유하는 경우와 공유하지 않는 경우를 비롯하여 다양한 상황을 경험했습니다. 그러나 이러한 상황들을 모두 직접 확인하기에는 시간과 노력이 많이 들어갔습니다. 이에 따라 학생들의 캠 상태와 미접속 학생 명단을 확인하는 프로그램이 필요하다고 생각하게 되었습니다.

## 사용 방법
1.chromedriver.exe 파일이 위치한 경로를 path 변수에 입력합니다.

2.강사의 이름은 teacher 변수에, 강사의 프레젠테이션 이름은 teacherPresent 변수에 입력합니다.

3.학생들의 이름은 student 배열에 입력합니다.

4.프로그램을 실행합니다.

5.Google Meet에 접속하고, 모든 참가자가 보이도록 설정합니다.

6.프로그램에서 'z' 키를 눌러 참석자 목록을 확인합니다.

7.프로그램을 종료하려면 'x' 키를 누릅니다.

## 실행 예시

인원 검색 전에 구글미팅에 접속하여 모든 인원이 보이게 만들어 준 후 z키를 누르세요.

종료하시려면 x 키를, 계속 검색하시려면 아무키나 입력해주세요

z

------------------------------------------------ STUDENT CHECKER----------------------------------------------------------------

접속 인원 명단    : [학생1, 학생2, 학생3, 학생4, 학생5, 학생6, 학생7, 학생8, 학생9, 학생10]

총 10명

캠 ON           : [학생1, 학생3, 학생4, 학생5, 학생7, 학생10]

총 6명

캠 OFF          : [학생2, 학생6, 학생8, 학생9]

총 4명

미접속          : [학생11, 학생12, 학생13, 학생14, 학생15, 학생16, 학생17, 학생18, 학생19, 학생20, 학생21]

총 11명

----------------------------------------------------------------------------------------------------------------------------------.



