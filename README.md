# 프로젝트 소개
보드 게임 중 하나인 부루마블을 모티브를 얻었고 이클립스를 활용하여 자바로 제작한 게임입니다.
플레이어에게 기본 자금을 나눠 주며 게임이 시작됩니다.
주사위를 굴려 나온 수 만큼 이동해 땅을 구매할 수 있고 본인이 구매한 땅이 아니라면 통행료를 지불합니다.
파산이 된 플레이어가 나온다면 게임이 종료됩니다. 

# 플로우 차트
![bluemarblescreenshot](https://user-images.githubusercontent.com/61133793/119252199-df1c2280-bbe5-11eb-8f9e-b4a61e75d280.PNG){: width="100" height="100"}

# 사용된 프로그램 정보
- JDK 1.8 
- IDE : Eclipse

# 프로젝트의 특징 
## 초기화면

- Login class 에서 프로젝트를 실행 시키면 인원 수를 띄우는 메시지 창이 나옵니다. 
![bluemarblescreenshot](https://user-images.githubusercontent.com/61133793/119252199-df1c2280-bbe5-11eb-8f9e-b4a61e75d280.PNG)
- 

## 특수지역

- 총 4군데의 특수지역이 있고 해당 특수 지역에 도착했을 시 다양한 메시지 창이 발생 합니다.
<pre><code>
switch (player.playerState) {
		case 0: {
			break;
		}
		case 1: {
			
			int position = (player.getPosition() + num) % 24;
			player.setPosition(player.getPosition() + num);

			players[i].moveTo(position); // 좌표

			System.out.println("이동 후 pos : " + player.getPosition());
			if (position == 0 || position == 6 || position == 12 || position == 18 || position == 3) { // 특수 지역이니?
				if (position == 0) {
					message = "월급을 받습니다!";
					player.plusMoney(10);
				}			
				if (position == 6) {
					message = "무인도에 갇혔습니다ㅠㅠ \n다음턴은 쉽니다";
					player.playerState = 0;
				}
				if (position == 12) {
					message = "복권 당첨!! \n(축하금 20만원)";
					player.plusMoney(20);
				}
				if (position == 18) {
					message = "병원 도착! 건강검진을 받으세요! \n(비용 20만원)";
					player.minusMoney(20);
				}
				JOptionPane.showMessageDialog(null, message); </code></pre>

### 무인도 (6번째 칸)에 갇혔을 시 해당 메시지 창이 발생 합니다.
 ![image](https://user-images.githubusercontent.com/61133793/119252288-5b166a80-bbe6-11eb-9fcc-62819120b853.png)
 ![image](https://user-images.githubusercontent.com/61133793/119252762-dd079300-bbe8-11eb-88c2-537869af1c9e.png)
- playerstate가 0일때 무인도, 1일때 게임 가능을 기준으로 두었습니다.
<pre><code> 
switch (players[turn].playerState) {
			case 0: {
				JOptionPane.showMessageDialog(dice, "무인도에 갇혔으므로 이번턴은 쉽니다!");
				players[turn].playerState = 1;
				turn = ++turn % 4;
				break;
			}
			case 1: {
				switch (diceStatus) {
				case PAUSE:
					timer.start();
					throwbtn.setText("스탑!");
					diceStatus = RUN;

					break;

				case RUN:

					timer.stop();
					throwbtn.setText("던지기!");

					playerSet(turn);
					turn = ++turn % 4;

					repaint();

					diceStatus = PAUSE;
					break;
				} </code></pre>
## 사회복지기금 (12번째 칸)에 도착했을 시 해당 메시지 창이 발생 합니다.

## 세금 (18번째 칸)에 도착했을 시 해당 메시지 창이 발생 합니다.

## 최종 순위
        
# 어려웠던 점
- 좌표값 설정
땅에 대한 정보가 필요했습니다. 마우스 포인터의 위치를 통해 X값과 Y값을 알아냈고,

- 게임말 이동 
처음 설계는 게임말이 해당 위치에 곧바로 나오게 했습니다. 

- 보유 금액 갱신
각 플레이어의 금액이 제대로 갱신되지 않고 마지막 플레이어 라벨에만 금액이 변경되었습니다. 두번 형변환 포인트 컴포넌트로 문제 해결
</pre></code> zz </code></pre>


# 앞으로 개선 해야할 것

# 시연 영상

