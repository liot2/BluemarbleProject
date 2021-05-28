package main;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Player extends JLabel {

	private ImageIcon icPlayer; // 플레이어 이미지 구현
	private int playerX; // 플레이어의 (그래픽 상의) 좌표
	private int playerY;
	private int destX; // 움직일 좌표
	private int destY;
	private int position; // 플레이어가 몇 번째 칸에 있는지
	private int money; // 보유 현금
	int playerState = 1; // 플레이어 상태 [0:무인도, 1:게임 가능]
	private static Map parentMap;
	
	Tile tile = new Tile();

	public Player(int i, int x, int y, int position) {

		this.playerX = x;
		this.playerY = y;
		
		icPlayer = new ImageIcon(String.format("img/piece_%d.png", i));
		
		pName(i); // 캐릭터 이름
		setIcon(icPlayer); // 기본이미지(오른쪽)
		setLocation(playerX, playerY); // 시작좌표 설정
		setPosition(position);
		setVisible(true);
		setMoney(100);

	}


	public Player(int i, int x, int y) {
		this(i, x, y, 0);
	}

	public void plusMoney(int money) {
		this.money += money;
	}

	public int minusMoney(int money) { // 출금
		this.money -= money;
		return this.money;
	}

	public int getPosition() {
		return position;
	}

	public int setPosition(int position) {
		return this.position = position;

	}

	public Player(String format) {

	}

	public ImageIcon getIcPlayer() {
		return icPlayer;
	}

	public void setIcPlayer(ImageIcon icPlayer) {
		this.icPlayer = icPlayer;
	}

	public int getPlayerX() {
		return playerX;
	}

	public void setPlayerX(int playerX) {
		this.playerX = playerX;
	}

	public int getPlayerY() {
		return playerY;
	}

	public void setPlayerY(int playerY) {
		this.playerY = playerY;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}
	
	public static void setParentMap(Map map) {
		if(Player.parentMap != null) return;
		Player.parentMap = map;
	} 

	public void moveTo(int tileNo) {
		TileInfo tileInfo = Tile.tileList[tileNo];
		destX = tileInfo.getX();
		destY = tileInfo.getY();
		position = tileNo;
		
		final Timer timer = new Timer(0, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
		
				if (destX == playerX && destY == playerY) {
					
					// 목적 좌표에 도달하면
					parentMap.showWindow();
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
	
	public void pName(int playerNum) {
		if (playerNum == 0) {
			setName("피카츄");
		} else if (playerNum == 1) {
			setName("푸린");
		} else if (playerNum == 2) {
			setName("파이리");
		} else {
			setName("꼬부기");
		}
	}
	
	

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null) {
			return false;
		}

		if (obj instanceof Player) {
			return getName().equals(((Player) obj).getName());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return getName().hashCode();
	}
}
