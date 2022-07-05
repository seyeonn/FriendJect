# Friendject (프렌젝트)

SSAFY 6기 공통 프로젝트 (2022.01.10 ~ 2022.02.18)


## 1️⃣  기획 배경

### 📊  자료 분석

[온라인 쌍방향수업 확대 필요..](https://m.yna.co.kr/view/AKR20210310060200054)

['줌 피로증' 코로나 신종 스트레스..](https://www.joongang.co.kr/article/24010920#home)

- '웹엑스'나 '줌'을 이용한 비대면 만남이 일상화되었으나 점점 한계가 드러나고 있음
- 대면만큼의 높은 효율성을 가질 수 있는 온라인 WebRTC 플랫폼이 필요함

### 😔  현재 상황의 아쉬운 점

- 비대면의 불편사항
	1. 소통의 불편함
	2. 소속감을 느끼기 어려움
	3. 팀원의 딴 짓 감시 불가

- 기존 플랫폼의 불편사항
	1. 자료 공유 불가
	2. 화이트 보드 필요
	3. 공부 시간 기록

### 💡  아이디어 구상 및 확정

소통이 원활하고 소속감이 느껴지는 서비스 구현을 목표로 친근한 화상 채팅 프로그램을 기획하게 되었습니다.


## 2️⃣  프로젝트 소개

> 비대면 프로젝트 진행도 대면처럼 개발 능률을 높일 수 있는 **화상 채팅 프로그램**

### 🔔  핵심 포인트

1. 아이스 브레이킹을 활용한 친근한 분위기 조성
2. 실행 파일 추적을 통한 팀원의 딴짓 미리 방지
3. 빠른 투표 기능을 활용한 신속한 프로젝트 진행 
4. 귀여운 미니미를 통한 친근한 디자인

### 🏫 구성

1. 팀원들과 함께 '모각코(모여서 각자 코딩)'  할 수 있는 **열람실**
2. 화이트보드 기능을 도입하여 매끄럽게 회의를 진행할 수 있는 **회의실**
3. 파일을 업/다운로드 할 수 있고 진행 상황을 공유할 수 있는 **프로젝트실**
4. 화면을 공유하고 코드 에디터를 통해 질의응답을 편하게 할 수 있는 **상담실**

## 3️⃣  서비스 소개

### 1. 팀 생성 및 참가

![friendject1](https://user-images.githubusercontent.com/38287375/177260527-92b41664-4b2d-4301-b25a-69861afdef68.gif)

### 2. 초기 미니미 선택 및 접속

![friendject2](https://user-images.githubusercontent.com/38287375/177261512-25676c0b-1fe2-487d-85be-ab06f3b7aa49.gif)

### 3. 아이스 브레이킹

![friendject10](https://user-images.githubusercontent.com/38287375/177261983-76cdaad5-710d-40d5-96d5-14ca81dcdd3b.gif)


### 4. 열람실

![friendject7](https://user-images.githubusercontent.com/38287375/177260782-f624de9f-be76-4fb1-81dd-f422213b3463.gif)

### 5. 회의실 

- 회의록 기록
![friendject5](https://user-images.githubusercontent.com/38287375/177261049-74262759-072c-4649-a1e7-124aeb26e67f.gif)

- 화이트 보드 기능
![friendject6](https://user-images.githubusercontent.com/38287375/177261118-543d5a2c-ea78-48d2-98c8-afdd23ac0d15.gif)

### 6. 프로젝트실

- 메모 보드
![friendject3](https://user-images.githubusercontent.com/38287375/177261254-064f0b84-4d61-45df-a796-e0ebb11d4f6b.gif)

- 자료실
![friendject4](https://user-images.githubusercontent.com/38287375/177261377-fda9b815-b73b-4e67-9046-d36f72b22b07.gif)

### 7. 상담실

- 해결의 책
![friendject8](https://user-images.githubusercontent.com/38287375/177261641-60010168-f05d-4c0e-9026-d667d81096dc.gif)

- 상담 저장 및 코드 에디터
![friendject9](https://user-images.githubusercontent.com/38287375/177261804-5cd92768-1389-49be-bec4-9c70c7e100ac.gif)

### 8. 빠른 투표 기능

![friendject11](https://user-images.githubusercontent.com/38287375/177262141-a8621e88-8ff0-4280-9691-f5f1b5508693.gif)

## 4️⃣  개발 플로우

### ⚙️  Architecture

<img width="782" alt="스크린샷 2022-07-05 오후 3 22 44" src="https://user-images.githubusercontent.com/38287375/177262828-d0e0a086-8d24-499b-8342-b221bf24d181.png">

### 🎮Tech Stack

#### Front-End

- OpenVidu
- Vue.js
- HTML5
- CSS3
- JavaScript
- Sass
- Bootstrap

#### Back-End

- Java 8
- SpringBoot
- JPA
- MySQL 5.7
- Postman

#### DevOps

- AWS EC2
- Docker
- Nginx
- Jenkins

#### Team Collaboration

- GitLab
- Jira
- MatterMost
- Notion

## 5️⃣ 요구 사항 정의서

<img width="723" alt="스크린샷 2022-07-05 오후 3 39 46" src="https://user-images.githubusercontent.com/38287375/177266355-1fcc9609-ebb6-427c-8f0a-1607f89efa77.png">

<img width="723" alt="스크린샷 2022-07-05 오후 3 39 21" src="https://user-images.githubusercontent.com/38287375/177266389-ee4b8d6b-68fd-4d2c-ba85-188d0f067a43.png">

## 6️⃣ REST API

<img width="485" alt="스크린샷 2022-07-05 오후 4 14 08" src="https://user-images.githubusercontent.com/38287375/177271208-27fdb9ab-79c3-4ee2-a38d-d66f03a2db2d.png">

<img width="597" alt="스크린샷 2022-07-05 오후 4 22 34" src="https://user-images.githubusercontent.com/38287375/177272578-56664470-0e72-4bca-8c37-6b5fc587ceb1.png">

## 7️⃣ 와이어 프레임

<img width="779" alt="스크린샷 2022-07-05 오후 3 53 12" src="https://user-images.githubusercontent.com/38287375/177267792-46be9645-f642-49fa-9a75-3b6fd1648163.png">

## 8️⃣ ERD

![FriendJect_erd](https://user-images.githubusercontent.com/38287375/177268357-69240f86-2293-4015-8681-3b24b3ce83d6.png)

## 9️⃣ 팀원 소개

### 팀원 역할

<img width="616" alt="스크린샷 2022-07-05 오후 3 56 14" src="https://user-images.githubusercontent.com/38287375/177268232-da3b5b6f-0272-463c-a228-c875246907d4.png">

### 내가 맡은 상세 역할

- 아이스 브레이킹 기능 BE/FE
- 열람실 학습 시간 기록 저장 BE/FE
- 열람실 오늘의 학습왕 BE/FE
- Back-End 배포 담당
- DB 구축
- 프로젝트 총괄 디자인

### 소감

- 김주향 : 너무 짧은 6주였습니다. 팀원들을 믿고 자유로운 개인 학습시간을 많이 드렸는데 다들 암묵적으로 밤새워가며 개발해주셔서 감사했습니다. 프로젝트 마무리 짓고 퇴소하게 되어서 다행입니다. 잘 따라와준 팀원들에게 가장 고맙고 초기에 생각했던 기능 대부분 구현하게 되어 기쁩니다! 개발 외의 것들(문서, Jira, Git, 발표..)이 익숙치 않아서 시간을 많이 뺏겼습니다. 개발에 빨리 들어간 편으로 생각했는데 결과적으론 순수 개발 시간이 부족하다고 느껴서 좀 아쉬웠습니다.
- 송진섭 : 팀원들이 주말까지 열심히 개발할지 몰랐는데 월요일에 주말동안 개발한 것을 보니 다들 열심히 했습니다. 주말에도 모르는 게 있을 때 팀원들에게 물어봤다면 더 빠르게 해결할 수 있었을 것 같은데 과도하게 배려를 한 것이 아쉽습니다. 팀원들이 실력이 좋아서 처음 하는 스프링 부트 프로젝트에서 쉬운 기능을 맡아 스프링 부트에 대해 기초부터 공부할 수 있었던 점이 좋았습니다.
- 안세연 : 짧은 시간 안에 많은 기능을 구현하느라 밤을 지새운 적이 많지만 그래도 초기 기획 목표를 거의 달성한 것 같아 뿌듯합니다. 팀원 모두 열심히 잘 해줘서 좋은 결과를 이룰 수 있었다고 생각합니다. 이번 프로젝트를 통해 한층 또 배우게 된 것 같습니다. 아쉬운 점이 있다면, 서버 배포를 처음 시도해본 터라 사전 지식이 부족해서 완벽하게 하지 못했던 점이 아쉽습니다. 스스로 많이 부족했다고 느끼는데 팀원들이 정말 많이 도와주고 함께 해주셔서 감사하다는 말을 꼭 하고 싶습니다.
- 이소라 : 프로젝트를 진행함에 있어서 팀장님께서 가이드 라인을 잘 잡아주셔서 어떤 방식으로 개발할지 기획하고 구현하는 게 한결 수월했던 것 같습니다. 팀원들이 주말까지 열심히 공부하고 개발하는 모습을 보면서 반성도 되고 많은 자극을 받게 되었던 프로젝트였습니다. 아쉬웠던 점은 좀 더 빨리 개발을 시작하고 배포와 테스트 일정을 더 잡았으면 더 훌륭한 결과물이 나오지 않았을까하는 아쉬움이 있습니다.
- 정채은 : 프로젝트 덕분에 JPA도 배워서 써먹고 Jira나 Jenkins도 처음 사용해서 배운 게 많았던 첫 번째 프로젝트였습니다. JPA는 미리 배워놓고 좀 더 견고하게 적용했으면 좋았겠다는 생각입니다. 실제 코딩한 시간은 너무 짧아서 코드 리뷰를 거의 못 하고 컨벤션 등을 제대로 못 지킨 것 같아 아쉽습니다. 그래도 팀원 모두가 후회 없이 각자 열심히 노력한 게 보여서 감동입니다. 특히 우리 팀장님이 이제 떠나시지만, 끝까지 제일 많은 일을 맡으시면서 최고의 책임감을 보여주셨습니다. 최고의 리더! 모두 수고 많았습니다.

## 🔟 번외

<img width="676" alt="스크린샷 2022-07-05 오후 4 10 31" src="https://user-images.githubusercontent.com/38287375/177270605-9fec1a55-b23d-446c-9788-db0d59f2f473.png">

<img width="663" alt="스크린샷 2022-07-05 오후 4 10 44" src="https://user-images.githubusercontent.com/38287375/177270549-101d241b-2d6b-4a2e-934c-483d1bd7c41d.png">

<img width="670" alt="스크린샷 2022-07-05 오후 4 10 52" src="https://user-images.githubusercontent.com/38287375/177270484-1b4436c5-dd50-4ac4-99d9-1a557205e3cc.png">

