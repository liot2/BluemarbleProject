package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;



public class Map extends JPanel {

	private BufferedImage img;
	private BufferedImage diceImage;

	private JLabel dice;
	private JButton throwbtn;

	private static Tile tile = new Tile();

	private int playersNum;
	
	private Player[] players;
	private static int num = 0; // 주사위 눈금
	private static int[] coordinate; // 플레이어 좌표
	
	private static int turn = 0;
	private int now = 0;

	private static final int PAUSE = 0;
	private static final int RUN = 1;
	private int diceStatus = PAUSE;

	
	private JLabel[] infoPanels;
	private ImageIcon imageIcon;
	private JLabel imgLabel;
	private JLabel infoLabel;
	private JLabel moneyLabel;
	private JPanel infoPanel;
	private JLabel panel;

	private String message = "";

	private int position;
	private int playerMoney;
	

	private Login login;

	public Player getPlayerByIndex(int idx) {
		return players[idx];
	}
	
	
	
	
	

	public Map(int playersNum) {
		
		// 레이아웃 설정
		super(true);
		this.playersNum = playersNum;

		JOptionPane.showMessageDialog(null, "게임을 시작합니다! \n  	GoodLuck!");
		
		Player.setParentMap(this);

		setSize(900, 600);
		this.setLayout(new BorderLayout());

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(10, 10, 600, 600);
		layeredPane.setLayout(null);
		
		
		coordinate = new int[playersNum];

		// 플레이어 말

		players = new Player[playersNum];
		infoPanels = new JLabel[playersNum];

		
		for (int i = 0; i < playersNum; i++) {
			
			players[i]= new Player(i, tile.tileList[coordinate[i]].x, tile.tileList[coordinate[i]].y, coordinate[i]); // (플레이어, x좌표, y좌표, 말위치)
			infoPanels[i] = new JLabel();
			
			
			layeredPane.add(players[i]);
			

			imageIcon = new ImageIcon("img/piece_" + i + ".png"); // 이미지 불러오기
			Image image = imageIcon.getImage(); // 이미지 세팅
			Image newimg = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH); // 라벨 크기에 맞춰서 세팅
			imageIcon = new ImageIcon(newimg); // 이미지 되돌리기

			imgLabel = new JLabel();
			imgLabel.setPreferredSize(new Dimension(100, 100));
			imgLabel.setIcon(imageIcon);
			imgLabel.setBorder(new LineBorder(new Color(254, 246, 213)));
			imgLabel.setOpaque(true);

			infoLabel = new JLabel();
			infoLabel.setBackground(Color.WHITE);
			infoLabel.setFont(new Font("CookieRun BLACK", Font.BOLD, 17));
			infoLabel.setText((i+1) + " P  : " + players[i].getName());
			infoLabel.setOpaque(true);

			playerMoney = players[i].getMoney();
			moneyLabel = new JLabel();
			moneyLabel.setBackground(Color.GREEN);
			moneyLabel.setFont(new Font("CookieRun BLACK", Font.BOLD, 14));
			moneyLabel.setText("보유 금액 : " + playerMoney);
			moneyLabel.setOpaque(true);

			infoPanel = new JPanel();
			infoPanel.setPreferredSize(new Dimension(210, 100));
			infoPanel.setLayout(new GridLayout(2, 1));
			infoPanel.add(infoLabel);
			infoPanel.add(moneyLabel);

			panel = infoPanels[i];
			panel.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel.setBounds(572, i * 100, 310, 100);
			panel.setLayout(new BorderLayout());
			panel.add(imgLabel, BorderLayout.WEST);
			panel.add(infoPanel, BorderLayout.CENTER);
			add(panel);

		}

		// 배경
		try {
			img = ImageIO.read(new File("img/gamemap_re2.png"));
		} catch (IOException e) {
			System.exit(0);
		}

		// 주사위 이미지
		try {
			diceImage = ImageIO.read(new File("img/dice_1.png"));
		} catch (IOException e) {
			System.exit(0);
		}
		dice = new JLabel();
		dice.setBounds(215, 105, 130, 120);
		dice.setLayout(null);
		dice.setIcon(new ImageIcon(diceImage));

		// 던지기 버튼
		throwbtn = new JButton("던지기");
		throwbtn.setBounds(240, 220, 90, 35);
		throwbtn.addActionListener(throwListener);
		throwbtn.setFocusPainted(false);
		throwbtn.setContentAreaFilled(false);
		throwbtn.setFont(new Font("BusanFont_Provisional", Font.PLAIN, 15));

		layeredPane.add(dice);
		layeredPane.add(throwbtn);

		add(layeredPane, BorderLayout.CENTER);
		JFrame frame = new JFrame("신나는 부루마블");
		frame.setLayout(null);
		frame.setBounds(0, 0, 900, 600);
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private ActionListener throwListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

			switch (players[turn].playerState) {
			case 0: {
				JOptionPane.showMessageDialog(dice, "무인도에 갇혔으므로 이번턴은 쉽니다!");
				players[turn].playerState = 1;
				turn = ++turn % playersNum;
				break;
			}
			case 1: {
				switch (diceStatus) {
				case PAUSE:

					timer.start();
					login.playSound("sound/dice_sound.wav", false);
					throwbtn.setText("스탑!");
					diceStatus = RUN;

					break;

				case RUN:

					timer.stop();
					throwbtn.setText("던지기!");

					playerSet(turn);
				
					
					turn = ++turn % playersNum;
					diceStatus = PAUSE;
					break;

				}

			}
			}
		}

	};

	private Timer timer = new Timer(5, new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			num = (int) ((Math.random() * 6) + 1);
			ImageIcon icon = new ImageIcon(String.format("img/dice_%d.png", num));
			dice.setIcon(icon);
			

		}
	});

	@Override
	protected void paintComponent(Graphics g) {

		g.drawImage(img, 0, 0, this); // see javadoc for more info on the parameters
		for (Player playerimg : players) {
			g.drawImage(playerimg.getIcPlayer().getImage(), playerimg.getX(), playerimg.getY(), 38, 38, null);
		}

	}

	private void playerSet(int i) {
		this.now = i;

		Player player = players[i];
		String message = "";

	

		System.out.println(player.getName() + "이동 전 pos : " + player.getPosition());
		switch (player.playerState) {
		case 0: {
			break;
		}
		case 1: {

			position = (player.getPosition() + num) % 24;

			player.setPosition(position);
			player.moveTo(player.getPosition()); // 좌표

			System.out.println(player.getName() + " 이동 후 pos : " + player.getPosition());

		}

		}

	}

	public void showWindow() {

		Player player = players[now];

		switch (player.playerState) {
		case 0: {
			break;
		}
		case 1: {
			
			if (player.getPosition() == 0 || player.getPosition() == 6 || player.getPosition() == 12 || player.getPosition() == 18) { // 특수지역이니?
				specialTile();

			} else if (player.getPosition() == 3 || player.getPosition() == 9 || player.getPosition() == 15) { // 황금열쇠니?
				boolean b = goldKey();
				if (b && (player.getPosition() == 0 || player.getPosition() == 6 || player.getPosition() == 12 || player.getPosition() == 18)) { // 특수지역이니?
					specialTile();
				}
			} else if (Tile.tileList[player.getPosition()].getOwner() == null) { // 비어있는 땅이니?
				emptyTile();

			} else if (!Tile.tileList[player.getPosition()].getOwner().equals(players[now])) { // 다른사람 땅이니?
				otherTile();

			} else if (Tile.tileList[player.getPosition()].getOwner().equals(players[now])) { // 내땅이니?
				myTile();
			}

			if (players[now].getMoney() <= 0) { // 게임 종료
				JOptionPane.showMessageDialog(null, players[now].getName() + "의 파산! \n게임이 종료되었습니다!");
				String endGame = "";
				int max = 0;
				String winner = "";

				for (int j = 0; j < players.length; ++j) {

					max = players[j].getMoney();

					if (max < players[j].getMoney()) {
						max = players[j].getMoney();
					}
					if (max == players[j].getMoney()) {
						winner = players[j].getName();
					}

					endGame += players[j].getName() + "님의 보유금액 : " + players[j].getMoney() + "원 \n";
				}

				endGame += "\n승자는 " + winner + "입니다!";
				JOptionPane.showMessageDialog(null, endGame);
				System.exit(1);// 게임 종료

			}

			break;
		}
		}

	}

	public void specialTile() { // 특수지역이니?
		
		Player player = players[now];

		if (player.getPosition() == 0) {
			message = "월급을 받습니다!";
			System.out.println(players[now].getName() + " 출발점 ");
			players[now].plusMoney(10);
		} else if (player.getPosition() == 6) {
			message = "무인도에 갇혔습니다ㅠㅠ \n다음턴은 쉽니다";
			System.out.println(players[now].getName() + " 무인도 ");
			players[now].playerState = 0;
		} else if (player.getPosition() == 12) {
			message = "복권 당첨!! \n(축하금 20만원)";
			System.out.println(players[now].getName() + " 복권 ");
			players[now].plusMoney(20);
		} else if (player.getPosition() == 18) {
			message = "병원 도착! 건강검진을 받으세요! \n(비용 20만원)";
			System.out.println(players[now].getName() + " 병원 ");
			players[now].minusMoney(20);
		}

		JOptionPane.showMessageDialog(null, message);

	}

	public void emptyTile() { // 비어있는 땅이니?
		Player player = players[now];

		if (players[now].getMoney() < Tile.tileList[player.getPosition()].toll) {
			JOptionPane.showMessageDialog(null, "돈이 부족해서 땅을 구매할 수 없습니다!");
			System.out.println(players[now].getName() + " 돈부족 ");
		} else {
			new PurchaseWindow(position, now, Tile.tileList[player.getPosition()], this);
			System.out.println(players[now].getName() + " 땅삼 ");
		}
	}

	public void otherTile() { // 다른사람 땅이니?
		Player player = players[now];

		message = Tile.tileList[player.getPosition()].getOwner().getName() + "의 땅입니다! \n통행료 " + Tile.tileList[player.getPosition()].toll
				+ "만원을 지불하세요";

		players[now].minusMoney(Tile.tileList[player.getPosition()].toll);
		Tile.tileList[player.getPosition()].getOwner().plusMoney(Tile.tileList[player.getPosition()].toll);
		System.out.println(player.getName() + " 이 " + Tile.tileList[player.getPosition()].getOwner().getName() + " 에게 통행료 납부");
		JOptionPane.showMessageDialog(null, message);

	}

	public void myTile() { // 내땅이니?
		Player player = players[now];
		message = "자신의 땅에 돌아왔습니다! 통행료가 2배가 됩니다!";
		Tile.tileList[player.getPosition()].toll = Tile.tileList[player.getPosition()].toll * 2;
		System.out.println(players[now].getName() + "의 땅 두번 밟음");
		JOptionPane.showMessageDialog(null, message);

	}

	public boolean goldKey() { // 황금열쇠니?
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
	
	public void changeMoney() {
		for (int k = 0; k < playersNum; ++k) {
			((JLabel) ((JPanel) infoPanels[k].getComponent(1)).getComponent(1)).setText("보유 금액 : " + players[k].getMoney());}
	}
	
//
//	public static void main(String[] args) {
//		new Map(4);
//
//	}

}
