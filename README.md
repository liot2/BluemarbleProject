# 프로젝트 소개
보드 게임 중 하나인 부루마블을 모티브를 얻었고 이클립스를 활용하여 자바로 제작한 게임입니다.
플레이어에게 기본 자금을 나눠 주며 게임이 시작됩니다.
주사위를 굴려 나온 수 만큼 이동해 땅을 구매할 수 있고 본인이 구매한 땅이 아니라면 통행료를 지불합니다.
파산이 된 플레이어가 나온다면 게임이 종료됩니다. 

# 플로우 차트
![bluemarblescreenshot](https://user-images.githubusercontent.com/61133793/119252199-df1c2280-bbe5-11eb-8f9e-b4a61e75d280.PNG)

# 사용된 프로그램 정보
- JDK 1.8 
- IDE : Eclipse

# 프로젝트의 특징 
## 초기화면
![bluemarblescreenshot](https://user-images.githubusercontent.com/61133793/119252199-df1c2280-bbe5-11eb-8f9e-b4a61e75d280.PNG)
- 

## 무인도에 갇혔을 시 해당 메시지 창이 발생 합니다.
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
##
        
# 어려웠던 점

# 앞으로 개선 해야할 것

# 시연 영상

