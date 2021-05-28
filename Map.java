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
	private static int num = 0; // �ֻ��� ����
	private static int[] coordinate; // �÷��̾� ��ǥ
	
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
		
		// ���̾ƿ� ����
		super(true);
		this.playersNum = playersNum;

		JOptionPane.showMessageDialog(null, "������ �����մϴ�! \n  	GoodLuck!");
		
		Player.setParentMap(this);

		setSize(900, 600);
		this.setLayout(new BorderLayout());

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(10, 10, 600, 600);
		layeredPane.setLayout(null);
		
		
		coordinate = new int[playersNum];

		// �÷��̾� ��

		players = new Player[playersNum];
		infoPanels = new JLabel[playersNum];

		
		for (int i = 0; i < playersNum; i++) {
			
			players[i]= new Player(i, tile.tileList[coordinate[i]].x, tile.tileList[coordinate[i]].y, coordinate[i]); // (�÷��̾�, x��ǥ, y��ǥ, ����ġ)
			infoPanels[i] = new JLabel();
			
			
			layeredPane.add(players[i]);
			

			imageIcon = new ImageIcon("img/piece_" + i + ".png"); // �̹��� �ҷ�����
			Image image = imageIcon.getImage(); // �̹��� ����
			Image newimg = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH); // �� ũ�⿡ ���缭 ����
			imageIcon = new ImageIcon(newimg); // �̹��� �ǵ�����

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
			moneyLabel.setText("���� �ݾ� : " + playerMoney);
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

		// ���
		try {
			img = ImageIO.read(new File("img/gamemap_re2.png"));
		} catch (IOException e) {
			System.exit(0);
		}

		// �ֻ��� �̹���
		try {
			diceImage = ImageIO.read(new File("img/dice_1.png"));
		} catch (IOException e) {
			System.exit(0);
		}
		dice = new JLabel();
		dice.setBounds(215, 105, 130, 120);
		dice.setLayout(null);
		dice.setIcon(new ImageIcon(diceImage));

		// ������ ��ư
		throwbtn = new JButton("������");
		throwbtn.setBounds(240, 220, 90, 35);
		throwbtn.addActionListener(throwListener);
		throwbtn.setFocusPainted(false);
		throwbtn.setContentAreaFilled(false);
		throwbtn.setFont(new Font("BusanFont_Provisional", Font.PLAIN, 15));

		layeredPane.add(dice);
		layeredPane.add(throwbtn);

		add(layeredPane, BorderLayout.CENTER);
		JFrame frame = new JFrame("�ų��� �η縶��");
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
				JOptionPane.showMessageDialog(dice, "���ε��� �������Ƿ� �̹����� ���ϴ�!");
				players[turn].playerState = 1;
				turn = ++turn % playersNum;
				break;
			}
			case 1: {
				switch (diceStatus) {
				case PAUSE:

					timer.start();
					login.playSound("sound/dice_sound.wav", false);
					throwbtn.setText("��ž!");
					diceStatus = RUN;

					break;

				case RUN:

					timer.stop();
					throwbtn.setText("������!");

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

	

		System.out.println(player.getName() + "�̵� �� pos : " + player.getPosition());
		switch (player.playerState) {
		case 0: {
			break;
		}
		case 1: {

			position = (player.getPosition() + num) % 24;

			player.setPosition(position);
			player.moveTo(player.getPosition()); // ��ǥ

			System.out.println(player.getName() + " �̵� �� pos : " + player.getPosition());

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
			
			if (player.getPosition() == 0 || player.getPosition() == 6 || player.getPosition() == 12 || player.getPosition() == 18) { // Ư�������̴�?
				specialTile();

			} else if (player.getPosition() == 3 || player.getPosition() == 9 || player.getPosition() == 15) { // Ȳ�ݿ����?
				boolean b = goldKey();
				if (b && (player.getPosition() == 0 || player.getPosition() == 6 || player.getPosition() == 12 || player.getPosition() == 18)) { // Ư�������̴�?
					specialTile();
				}
			} else if (Tile.tileList[player.getPosition()].getOwner() == null) { // ����ִ� ���̴�?
				emptyTile();

			} else if (!Tile.tileList[player.getPosition()].getOwner().equals(players[now])) { // �ٸ���� ���̴�?
				otherTile();

			} else if (Tile.tileList[player.getPosition()].getOwner().equals(players[now])) { // �����̴�?
				myTile();
			}

			if (players[now].getMoney() <= 0) { // ���� ����
				JOptionPane.showMessageDialog(null, players[now].getName() + "�� �Ļ�! \n������ ����Ǿ����ϴ�!");
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

					endGame += players[j].getName() + "���� �����ݾ� : " + players[j].getMoney() + "�� \n";
				}

				endGame += "\n���ڴ� " + winner + "�Դϴ�!";
				JOptionPane.showMessageDialog(null, endGame);
				System.exit(1);// ���� ����

			}

			break;
		}
		}

	}

	public void specialTile() { // Ư�������̴�?
		
		Player player = players[now];

		if (player.getPosition() == 0) {
			message = "������ �޽��ϴ�!";
			System.out.println(players[now].getName() + " ����� ");
			players[now].plusMoney(10);
		} else if (player.getPosition() == 6) {
			message = "���ε��� �������ϴ٤Ф� \n�������� ���ϴ�";
			System.out.println(players[now].getName() + " ���ε� ");
			players[now].playerState = 0;
		} else if (player.getPosition() == 12) {
			message = "���� ��÷!! \n(���ϱ� 20����)";
			System.out.println(players[now].getName() + " ���� ");
			players[now].plusMoney(20);
		} else if (player.getPosition() == 18) {
			message = "���� ����! �ǰ������� ��������! \n(��� 20����)";
			System.out.println(players[now].getName() + " ���� ");
			players[now].minusMoney(20);
		}

		JOptionPane.showMessageDialog(null, message);

	}

	public void emptyTile() { // ����ִ� ���̴�?
		Player player = players[now];

		if (players[now].getMoney() < Tile.tileList[player.getPosition()].toll) {
			JOptionPane.showMessageDialog(null, "���� �����ؼ� ���� ������ �� �����ϴ�!");
			System.out.println(players[now].getName() + " ������ ");
		} else {
			new PurchaseWindow(position, now, Tile.tileList[player.getPosition()], this);
			System.out.println(players[now].getName() + " ���� ");
		}
	}

	public void otherTile() { // �ٸ���� ���̴�?
		Player player = players[now];

		message = Tile.tileList[player.getPosition()].getOwner().getName() + "�� ���Դϴ�! \n����� " + Tile.tileList[player.getPosition()].toll
				+ "������ �����ϼ���";

		players[now].minusMoney(Tile.tileList[player.getPosition()].toll);
		Tile.tileList[player.getPosition()].getOwner().plusMoney(Tile.tileList[player.getPosition()].toll);
		System.out.println(player.getName() + " �� " + Tile.tileList[player.getPosition()].getOwner().getName() + " ���� ����� ����");
		JOptionPane.showMessageDialog(null, message);

	}

	public void myTile() { // �����̴�?
		Player player = players[now];
		message = "�ڽ��� ���� ���ƿԽ��ϴ�! ����ᰡ 2�谡 �˴ϴ�!";
		Tile.tileList[player.getPosition()].toll = Tile.tileList[player.getPosition()].toll * 2;
		System.out.println(players[now].getName() + "�� �� �ι� ����");
		JOptionPane.showMessageDialog(null, message);

	}

	public boolean goldKey() { // Ȳ�ݿ����?
		JOptionPane.showMessageDialog(null, "¹�� Ȳ�ݿ��� ����! >_<");
		Player player = players[now];
		int gold = (int) ((Math.random() * 9) + 1);
		boolean b = false;
		switch (gold) {
		case 1: {
			message = "Ȳ�ݿ����Դϴ�. \n�뵷 20������ �޽��ϴ�!";
			players[now].plusMoney(20);
			b = false;
			break;
		}
		case 2: {
			message = "Ȳ�ݿ����Դϴ�. \n�ӵ�����! ������ ���ϴ�! (��� 10����)";
			players[now].minusMoney(10);
			b = false;
			break;
		}
		case 3: {
			message = "Ȳ�ݿ����Դϴ�. \n��������� �̵��մϴ�!";
			players[now].moveTo(0);
			players[now].setPosition(0);
			break;
		}
		case 4: {
			message = "Ȳ�ݿ����Դϴ�. \n���ε��� �̵��մϴ�!";
			players[now].moveTo(6);
			players[now].setPosition(6);
			break;
		}
		case 5: {
			message = "Ȳ�ݿ����Դϴ�. \n �������� �̵��մϴ�!";
			players[now].moveTo(12);
			players[now].setPosition(12);
			break;
		}
		case 6: {
			message = "Ȳ�ݿ����Դϴ�. \n�������� �̵��մϴ�!";
			players[now].moveTo(18);
			players[now].setPosition(18);
			break;
		}
		case 7: {
			message = "�������� �̵��մϴ�!";
			JOptionPane.showMessageDialog(null, message);
			players[now].setPosition(22);
			players[now].moveTo(22);
			
			showWindow();
			break;
		}
		case 8: {
			message = "�ڷ� 3ĭ �̵� �մϴ�!";
			JOptionPane.showMessageDialog(null, message);
			players[now].setPosition((player.getPosition() - 3) % 24);
			players[now].moveTo((player.getPosition() - 3) % 24);
			b = false;
			break;
		}

		case 9: {

			message = "Ȳ�ݿ����Դϴ�. \n���������մϴ�! �ٸ� �÷��̾�鿡�� ���ϱ��� ��������! (��� �� 5����)";
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
			((JLabel) ((JPanel) infoPanels[k].getComponent(1)).getComponent(1)).setText("���� �ݾ� : " + players[k].getMoney());}
	}
	
//
//	public static void main(String[] args) {
//		new Map(4);
//
//	}

}
