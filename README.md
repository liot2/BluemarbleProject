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
## 초기 화면
- 아이디 입력 창이 나옵니다


## 게임 화면 
![bluemarblescreenshot](https://user-images.githubusercontent.com/61133793/119252199-df1c2280-bbe5-11eb-8f9e-b4a61e75d280.PNG)
- 

## 특수지역

- 총 4군데의 특수 지역이 있으며 해당 특수 지역에 도착했을 때, 지역에 맞는 메시지 창이 발생 합니다.
<pre><code>
switch (player.playerState) {
		case 0: {
			break;
		}
		case 1: {
			
			int position = (player.getPosition() + num) % 24;
			player.setPosition(player.getPosition() + num);

			players[i].moveTo(position);

			System.out.println("이동 후 pos : " + player.getPosition());
			if (position == 0 || position == 6 || position == 12 || position == 18 || position == 3) { 
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
				JOptionPane.showMessageDialog(null, message); <code><pre>
### 출발지
![월급](https://user-images.githubusercontent.com/61133793/119317799-79966780-bcb3-11eb-99ac-7c67bc1e1e68.PNG)

### 무인도 (6번째 칸)에 갇혔을 시 해당 메시지 창이 발생 합니다.
 ![무인도에 갇힘](https://user-images.githubusercontent.com/61133793/119319793-d561f000-bcb5-11eb-9f99-858009bcf872.PNG)
 ![이번판 쉼](https://user-images.githubusercontent.com/61133793/119319832-dd219480-bcb5-11eb-858c-8ff55dc6fdc3.PNG)
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
				} <code><pre>
##  (12번째 칸)에 도착했을 시 해당 메시지 창이 발생 합니다.
![12번째](https://user-images.githubusercontent.com/61133793/119318252-080ae900-bcb4-11eb-8776-305c690000bf.PNG)
<pre><code>
<code><pre>
## 병원 (18번째 칸)에 도착했을 시 해당 메시지 창이 발생 합니다.
![18번째칸](https://user-images.githubusercontent.com/61133793/119318403-3a1c4b00-bcb4-11eb-9c3c-7381439c9e93.PNG)

## 통행료 2배
![통행료2배](https://user-images.githubusercontent.com/61133793/119317944-ae0a2380-bcb3-11eb-9ee9-8ca29f702ae0.PNG)
<pre><code>if (Tile.tileList[position].getOwner().equals(player)) { // 자신의 땅이니?
				message = "자신의 땅에 돌아왔습니다! 통행료가 2배가 됩니다!";
				Tile.tileList[position].toll = Tile.tileList[position].toll * 2;
				JOptionPane.showMessageDialog(null, message);
			} <code><pre>
## 자금 부족
![돈업승ㅁ](https://user-images.githubusercontent.com/61133793/119317900-9a5ebd00-bcb3-11eb-88db-f60ceddfcde2.PNG)
<pre><code> if (players[i].getMoney() < Tile.tileList[position].toll) {
					JOptionPane.showMessageDialog(null, "돈이 부족해서 땅을 구매할 수 없습니다!"); <code><pre>

## 파산과 최종 순위
![파산](https://user-images.githubusercontent.com/61133793/119318008-c0845d00-bcb3-11eb-918d-00caacc5b48a.PNG)
![최종 순위](https://user-images.githubusercontent.com/61133793/119318034-c8dc9800-bcb3-11eb-83b1-18af9b7950f2.PNG)
<pre><code>if (player.getMoney() <= 0) {
				JOptionPane.showMessageDialog(null, player.getName() + "의 파산! \n게임이 종료되었습니다!");
				String endGame = "";
				int max = 0 ;
				String winner = "";		
				for (int j = 0; j < players.length; ++j)	{				
					max = players[j].getMoney();
					if (max < players[j].getMoney()) {
						max = players[j].getMoney();
					}
					if (max == players[j].getMoney()) {
						winner = players[j].getName();
					}	
					endGame += players[j].getName() +"님의 보유금액 : "+ players[j].getMoney() +"원 \n";
				}
				endGame += "\n승자는 " + winner + "입니다!";
				JOptionPane.showMessageDialog(null, endGame);
				System.exit(1);
			}
		}
	}
<code><pre>
        
# 어려웠던 점
- 좌표값 설정
땅에 대한 정보가 필요했습니다. 마우스 포인터의 위치를 통해 X값과 Y값을 알아냈고,

- 게임말 이동 
처음 설계는 게임말이 해당 위치에 곧바로 나오게 했습니다. 

- 플레이어가 땅을 구매했을 때 플레이어 땅이라거 저장 안됨 

- 보유 금액 갱신
 플레이어의 금액이 제대로 갱신되지 않고 마지막 플레이어 라벨에서만  금액이 변경되었습니다. 
<pre><code> zz <code><pre>

- 플레이어가 구매한다고했을때 돈이 깎이는거 




# 앞으로 개선 해야할 것
 통신 써야되니까

# 시연 영상

