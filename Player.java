package login;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Player extends JLabel {


	private ImageIcon icPlayer; // �÷��̾� �̹��� ����
	private int playerX; // �÷��̾��� (�׷��� ����) ��ǥ
	private int playerY; 
	private int destX; // ������ ��ǥ
	private int destY;
	private int position; // �÷��̾ �� ��° ĭ�� �ִ���
	private int money = 100; // ���� ����
	int playerState = 1; // �÷��̾� ���� 0:���ε�, 1:���� ����


	Tile tile = new Tile();


	public Player(int i, int x, int y, int position, String name) {

		this.playerX = x;
		this.playerY = y;

		icPlayer = new ImageIcon(String.format("img/piece_%d.png", i));
		setName(name);
		setIcon(icPlayer); // �⺻�̹���(������)
		setLocation(playerX, playerY); // ������ǥ ����
		setPosition(position);
		setVisible(true);
		System.out.println("userId : " + getName());
	}

	public Player(int i, int x, int y, int position) {
		this(i, x, y, position, "Player" + i);
	}

	public Player(int i, int x, int y) {
		this(i, x, y, 0);
	}

	public void plusMoney(int money) {
		this.money += money;
	}

	public int minusMoney(int money) { // ���
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

	public void moveTo(int tileNo) {
		TileInfo tileInfo = Tile.tileList[tileNo];
		destX = tileInfo.getX();
		destY = tileInfo.getY();
//		while(true) {
////			System.out.println("destX : " + destX + ", pX : " + playerX);
////			System.out.println("destY : " + destY + ", pY : " + playerY);
//
//			if (destX == playerX && destY == playerY) {
//				// ���� ��ǥ�� �����ϸ�
//				break;
//			}
//
//			if (playerX < destX) {
//				++playerX;
//			} else if (playerX > destX){
//				--playerX;
//			}
//
//			if (playerY < destY) {
//				++playerY;
//			} else if (playerY > destY){
//				--playerY;
//			}
//			try {
//				Thread.sleep(5);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			setBounds(new Rectangle(playerX, playerY, 40, 40));
//		}
		final Timer timer = new Timer(0, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				System.out.println("destX : " + destX + ", pX : " + playerX);
//				System.out.println("destY : " + destY + ", pY : " + playerY);

				if (destX == playerX && destY == playerY) {
					// ���� ��ǥ�� �����ϸ�
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
