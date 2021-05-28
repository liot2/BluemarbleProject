# 프로젝트 소개
보드 게임 중 하나인 부루마블을 모티브로 삼었고 이클립스를 활용하여 자바로 제작한 게임입니다.
플레이어에게 기본 자금을 나누어 주며 게임이 시작됩니다.
주사위를 굴려 나온 수 만큼 이동해 땅을 구매할 수 있고 본인이 구매한 땅이 아니라면 통행료를 지불합니다.
파산이 된 플레이어가 나온다면 게임이 즉시 종료됩니다. 

# 플로우 차트
![순서도](https://user-images.githubusercontent.com/61133793/119939169-642d8000-bfc8-11eb-81f0-25fdfe553c5b.PNG)

# 사용된 프로그램 정보
- JDK 1.8 
- IDE : Eclipse

# 프로젝트의 특징 
## 초기 화면
![초기화면](https://user-images.githubusercontent.com/61133793/119909803-bbaff980-bf90-11eb-9560-0beb0fcae656.PNG)
- 프로젝트 실행 시 플레이어의 인원을 정할 수 있는 윈도우 창이 실행됩니다.


## 게임 화면 
![게임화면](https://user-images.githubusercontent.com/61133793/119909820-c66a8e80-bf90-11eb-8833-04f0d9f76585.PNG)
- 초기 게임 화면

## 특수지역
- 총 4군데의 특수 지역이 있으며 해당 특수 지역에 도착했을 때, 지역에 맞는 메시지 창이 발생 합니다.
```java
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
				JOptionPane.showMessageDialog(null, message);
				}
			}
```			
### 출발지
![월급](https://user-images.githubusercontent.com/61133793/119317799-79966780-bcb3-11eb-99ac-7c67bc1e1e68.PNG)

### 무인도 (6번째 칸)에 갇혔을 시 해당 메시지 창이 발생 합니다.
 ![무인도에 갇힘](https://user-images.githubusercontent.com/61133793/119319793-d561f000-bcb5-11eb-9f99-858009bcf872.PNG)
 ![이번판 쉼](https://user-images.githubusercontent.com/61133793/119319832-dd219480-bcb5-11eb-858c-8ff55dc6fdc3.PNG)
- playerstate가 0일때 무인도, 1일때 게임 가능을 기준으로 두었습니다.
```java
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
				}
```
### 복권 (12번째 칸)에 도착했을 시 해당 메시지 창이 발생 합니다.
![복권](https://user-images.githubusercontent.com/61133793/119520186-0a08a100-bdb5-11eb-9015-7762e0d84601.PNG)
![12번째](https://user-images.githubusercontent.com/61133793/119318252-080ae900-bcb4-11eb-8776-305c690000bf.PNG)
### 병원 (18번째 칸)에 도착했을 시 해당 메시지 창이 발생 합니다.
![병원](https://user-images.githubusercontent.com/61133793/119520244-15f46300-bdb5-11eb-91d6-c7f6ed09bbad.PNG)
![18번째칸](https://user-images.githubusercontent.com/61133793/119318403-3a1c4b00-bcb4-11eb-9c3c-7381439c9e93.PNG)
## 황금열쇠
![황금열쇠](https://user-images.githubusercontent.com/61133793/119520150-ffe6a280-bdb4-11eb-8c94-fe2c0e53bee4.PNG)
- 총 9가지의 황금열쇠가 있으며, 황금열쇠 내용에 따라 플레이어에게 다양한 영향을 끼칠 수 있습니다. 
```java
	public boolean goldKey() { 
		JOptionPane.showMessageDialog(null, "쨔란 황금열쇠 도착! >_<");
		Player player = players[now];
		int gold = (int) ((Math.random() * 9) + 1);
		boolean b = false;
		switch (gold) {
		case 1: {
			message = "황금열쇠입니다. \n용돈 20만원을 받습니다!";
			players[now].plusMoney(20);
			b = false;
			break;
		}
		
		case 2: {
			message = "황금열쇠입니다. \n속도위반! 벌금을 냅니다! (비용 10만원)";
			players[now].minusMoney(10);
			b = false;
			break;
		}
		
		case 3: {
			message = "황금열쇠입니다. \n출발지으로 이동합니다!";
			players[now].moveTo(0);
			players[now].setPosition(0);
			break;
		}
		
		case 4: {
			message = "황금열쇠입니다. \n무인도로 이동합니다!";
			players[now].moveTo(6);
			players[now].setPosition(6);
			break;
		}
		
		case 5: {
			message = "황금열쇠입니다. \n 복권으로 이동합니다!";
			players[now].moveTo(12);
			players[now].setPosition(12);
			break;
		}
		
		case 6: {
			message = "황금열쇠입니다. \n병원으로 이동합니다!";
			players[now].moveTo(18);
			players[now].setPosition(18);
			break;
		}
		
		case 7: {
			message = "신촌으로 이동합니다!";
			JOptionPane.showMessageDialog(null, message);
			players[now].setPosition(22);
			players[now].moveTo(22);		
			showWindow();
			break;
		}
		
		case 8: {
			message = "뒤로 3칸 이동 합니다!";
			JOptionPane.showMessageDialog(null, message);
			players[now].setPosition((player.getPosition() - 3) % 24);
			players[now].moveTo((player.getPosition() - 3) % 24);
			b = false;
			break;
		}

		case 9: {
			message = "황금열쇠입니다. \n생일축하합니다! 다른 플레이어들에게 축하금을 받으세요! (비용 각 5만원)";
			players[now].plusMoney(5);
			
			switch(playersNum) {
			case 2 : {
				now = ++now % playersNum;
				players[now].minusMoney(5);
			}
			case 3: {
				now = ++now % playersNum;
				players[now].minusMoney(5);
				now = ++now % playersNum;
				players[now].minusMoney(5);
			}
			case 4 : {
				now = ++now % playersNum;
				players[now].minusMoney(5);
				now = ++now % playersNum;
				players[now].minusMoney(5);
				now = ++now % playersNum;
				players[now].minusMoney(5);
			}
			}
			b = false;
			break;
		}
	}
		JOptionPane.showMessageDialog(null, message);
		return b;
}
```

## 통행료 두배
![통행료2배](https://user-images.githubusercontent.com/61133793/119317944-ae0a2380-bcb3-11eb-9ee9-8ca29f702ae0.PNG)
- 본인 소유 토지를 다시 밟았을 때, 통행료 두배를 받을 수 있게 됩니다.  
```java
if (Tile.tileList[position].getOwner().equals(player)) { 
				message = "자신의 땅에 돌아왔습니다! 통행료가 2배가 됩니다!";
				Tile.tileList[position].toll = Tile.tileList[position].toll * 2;
				JOptionPane.showMessageDialog(null, message);
			} 
```
## 파산과 최종 순위
![파삱](https://user-images.githubusercontent.com/61133793/119909976-18abaf80-bf91-11eb-8d2b-b3070efd4fe9.PNG)
![순위](https://user-images.githubusercontent.com/61133793/119909989-1ea19080-bf91-11eb-8f0f-069534e26347.PNG)
- 게임 도중 파산한 플레이어가 나온다면 곧바로 게임이 종료되고 현재 보유 금액이 많은 순서로 순위를 매깁니다. 
```java
if (player.getMoney() <= 0) {
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
```
## 자금 부족
![돈업승ㅁ](https://user-images.githubusercontent.com/61133793/119317900-9a5ebd00-bcb3-11eb-88db-f60ceddfcde2.PNG)
- 보유 금액이 토지의 금액보다 적다면 땅을 구매할 수 없다는 메시지 창이 나옵니다.
```java 
			if (players[i].getMoney() < Tile.tileList[position].toll) {
				JOptionPane.showMessageDialog(null, "돈이 부족해서 땅을 구매할 수 없습니다!"); 
				} 
```
# 어려웠던 점
## 1. 좌표값 설정	
토지에 대한 정보가 필요했습니다. MouseListener를 사용하여 마우스 커서의 위치에 대한 좌표를 알아낼 수 있었습니다.
```java	private static TileInfo start = new TileInfo ("출발점", 0, 484, 516, 2);
	private static TileInfo Taipei = new TileInfo ("타이페이", 1, 400, 516, 4);
	private static TileInfo Hongkong = new TileInfo ("홍콩", 2, 330, 516, 4);
	private static TileInfo Bucheon = new TileInfo ("부천", 3, 260, 516, 5);
	private static TileInfo Manila = new TileInfo ("마닐라", 4, 190, 516, 6);
	private static TileInfo Singapore = new TileInfo ("싱가포르", 5, 120, 516, 8);
	private static TileInfo Island = new TileInfo ("무인도", 6, 14, 516, 0);
	private static TileInfo Tokyo = new TileInfo ("도쿄", 7, 14, 400, 10);
	private static TileInfo Copenhagen = new TileInfo ("코펜하겐", 8, 14, 330, 12);
	private static TileInfo Incheon = new TileInfo ("인천", 9, 14, 260, 15);
	private static TileInfo Zurich = new TileInfo ("취리히", 10, 14, 190, 14);
	private static TileInfo Prague = new TileInfo ("프라하", 11, 14, 120, 16);
	private static TileInfo Reward = new TileInfo ("사회복지센터", 12, 21, 14, 0);
	private static TileInfo Berlin = new TileInfo ("베를린", 13, 120, 14, 18);
	private static TileInfo Lisbon = new TileInfo ("리스본", 14, 190, 14, 20);
	private static TileInfo Busan = new TileInfo ("부산", 15, 260, 14, 21);
	private static TileInfo Madrid = new TileInfo ("마드리드", 16, 330, 14, 22);
	private static TileInfo Paris = new TileInfo ("파리", 17, 400, 14, 24);
	private static TileInfo Fine = new TileInfo ("병원", 18, 512, 27, 0);
	private static TileInfo Rome = new TileInfo ("로마", 19, 512, 120, 26);
	private static TileInfo London = new TileInfo ("런던", 20, 512, 190, 28);
	private static TileInfo Newyork = new TileInfo ("뉴욕", 21, 512, 260, 28);
	private static TileInfo Sinchon = new TileInfo ("신촌", 22, 512, 330, 29);
	private static TileInfo Seoul = new TileInfo ("서울", 23, 512, 400, 30);	
```
## 2. 게임말 이동 
처음 설계는 게임말이 해당 위치에 곧바로 나오게 했습니다. 하지만 생동감 있는 게임을 위해 Timer 클래스를 사용하여, 말 이동이 애니메이션으로 보여지도록 추가 하였습니다.  
```java
	public void moveTo(int tileNo) {
		TileInfo tileInfo = Tile.tileList[tileNo];
		destX = tileInfo.getX();
		destY = tileInfo.getY();

		final Timer timer = new Timer(0, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (destX == playerX && destY == playerY) {
					
					parentMap.showWindow(/*position, i*/);
					((Timer) e.getSource()).stop();
				}
				
				if (playerX < destX) {
					++playerX;
				} else {
					--playerX;
				}

				if (playerY < destY) {
					++playerY;
				} else {
					--playerY;
				}
				setBounds(new Rectangle(playerX, playerY, 40, 40));			
			}
		});
		timer.start();
	}
```
## 3. 땅 소유 정보 저장 
플레이어가 토지를 구매했을 때 구매한 내용이 저장되지 않았습니다. Tile 이라는 클래스에 정보가 저장되야 하는데 Tile 클래스에 너무나 많은 내용들이 있어 명확하게 구별하는 것이 힘들었습니다. 그래서 Tileinfo라는 클래스를 따로 만들고 그 안에 땅 소유 정보를 저장하는 메서드를 만들었습니다.
```java
public class TileInfo {
	String name;
	int tNum;
	int x;
	int y;
	int toll;
	private Player owner = null;
	
	public void buy(Player owner) {
		this.setOwner(owner);
		owner.setMoney(owner.minusMoney(toll));
	}
	
	TileInfo(String name, int tNum, int x, int y, int toll){
		this.name = name;
		this.tNum = tNum;
		this.x = x;
		this.y = y;
		this.toll = toll;
	}
	
	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int gettNum() {
		return tNum;
	}

	public void settNum(int tNum) {
		this.tNum = tNum;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getToll() {
		return toll;
	}

	public void setToll(int toll) {
		this.toll = toll;
	}
}
```
## 4. 보유 금액 갱신  
 플레이어의 금액이 제대로 갱신되지 않고 마지막 플레이어 라벨에서만 금액이 변경되었습니다. 
 라벨값 변경을 위해 컴포넌트 이벤트를 발생시켰고 이벤트를 발생한 컴포넌트를 반환시켰습니다.
```java
public void changeMoney() {
		for (int k = 0; k < playersNum; ++k) {
			((JLabel) ((JPanel) infoPanels[k].getComponent(1)).getComponent(1)).setText("보유 금액 : " + players[k].getMoney());}
	}
```
# 앞으로 개선 해야할 것


# 시연 영상
<a href="https://www.youtube.com/watch?v=Eo4YASzyXxA" target="_blank"><image src = "https://img.youtube.com/vi/Eo4YASzyXxA/mqdefault.jpg"></a>
# JAVADOC
