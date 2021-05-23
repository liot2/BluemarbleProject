package login;

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
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import com.sun.tools.javac.Main;

public class Map extends JPanel {

	private BufferedImage img;
	private BufferedImage diceImage;

	private JLabel dice;
	private JButton throwbtn;

	private static Tile tile = new Tile();
	private PurchaseWindow purchaseWindow;

	private Player[] players;
	private static int num = 0; // 주사위 눈금
	private static int p1 = 0; // 플레이어 좌표
	private static int p2 = 0;
	private static int p3 = 0;
	private static int p4 = 0;

	private static int turn = 0;

	private static final int PAUSE = 0;
	private static final int RUN = 1;
	private int diceStatus = PAUSE;

	
	private JPanel[] infoPanels;

	ImageIcon imageIcon;
	JLabel imgLabel;
	JLabel infoLabel;
	JLabel moneyLabel;
	JPanel infoPanel;
	JPanel panel;
	
	
	int xSpeed;
	int ySpeed;
	
	String playerMoney = "";
	
	
	
	public Player getPlayerByIndex(int idx) {
		return players[idx];
	}
	

	
	public Map(String userId) {

		// 레이아웃 설정
		super(true);
		setSize(900, 600);
		this.setLayout(new BorderLayout());

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(10, 10, 600, 600);
		layeredPane.setLayout(null);
		
		
		
		// 플레이어 말
		players = new Player[] { 
				new Player(0, tile.tileList[p1].x, tile.tileList[p1].y, p1, userId), // (1번 플레이어, x좌표,
				new Player(1, tile.tileList[p2].x, tile.tileList[p2].y), // (2번 플레이어, x좌표, y좌표)
				new Player(2, tile.tileList[p3].x, tile.tileList[p3].y), // (3번 플레이어, x좌표, y좌표)
				new Player(3, tile.tileList[p4].x, tile.tileList[p4].y) // (4번 플레이어, x좌표, y좌표)
			};
				
				
		for(Player player : players) {
			layeredPane.add(player);
		}
		
	
		infoPanels = new JPanel[] { new JPanel(), new JPanel(), new JPanel(), new JPanel() };
		for (int i = 0; i < infoPanels.length; ++i) {

			imageIcon = new ImageIcon("img/piece_" + i + ".png"); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it
			Image newimg = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
			imageIcon = new ImageIcon(newimg); // transform it back

			imgLabel = new JLabel();
			imgLabel.setPreferredSize(new Dimension(100, 100));
			imgLabel.setIcon(imageIcon);
			imgLabel.setBorder(new LineBorder(new Color(254, 246, 213)));
			imgLabel.setOpaque(true);

			infoLabel = new JLabel();
			infoLabel.setBackground(Color.WHITE);
			infoLabel.setFont(new Font("CookieRun BLACK", Font.BOLD, 17));
			infoLabel.setText("		ID : " + players[i].getName());
			infoLabel.setOpaque(true);

			
			playerMoney = Integer.toString(players[i].getMoney());
			
			moneyLabel = new JLabel();
			moneyLabel.setBackground(Color.GREEN);
			moneyLabel.setFont(new Font("CookieRun BLACK", Font.BOLD, 14));
			moneyLabel.setText(playerMoney);
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
			img = ImageIO.read(new File("img/gamemap_re1.png"));
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
		JFrame frame = new JFrame("부루마블");
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
		for (Player player1 : players) {
			g.drawImage(player1.getIcPlayer().getImage(), player1.getX(), player1.getY(), 38, 38, null);
		}

	}

	private void playerSet(int i) {

		Player player = players[i];
		String message = "";
		System.out.println("이동 전 pos : " + player.getPosition());
		switch (player.playerState) {
		case 0: {
			
			break;
		}
		case 1: {
			
			int position = (player.getPosition() + num) % 24;
			player.setPosition(player.getPosition() + num);

			players[i].moveTo(position); // 좌표

			System.out.println("이동 후 pos : " + player.getPosition());
			if (position == 0 || position == 6 || position == 12 || position == 18) { // 특수 지역이니?
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

			} else if (Tile.tileList[position].getOwner() == null) { // 땅 살래?
				
				if (players[i].getMoney() < Tile.tileList[position].toll) {
					JOptionPane.showMessageDialog(null, "돈이 부족해서 땅을 구매할 수 없습니다!");
				} else {
				purchaseWindow = new PurchaseWindow(i, Tile.tileList[position], this);
				
				}
				
			
				
			} else if (!Tile.tileList[position].getOwner().equals(player)) { // 다른사람 땅이니?
				message = Tile.tileList[position].getOwner().getName() + "의 땅입니다! \n통행료 " + Tile.tileList[position].toll
						+ "만원을 지불하세요";

				player.minusMoney(Tile.tileList[position].toll);
				Tile.tileList[position].getOwner().plusMoney(Tile.tileList[position].toll);
				JOptionPane.showMessageDialog(null, message);
			} else if (Tile.tileList[position].getOwner().equals(player)) { // 자신의 땅이니?
				message = "자신의 땅에 돌아왔습니다! 통행료가 2배가 됩니다!";
				Tile.tileList[position].toll = Tile.tileList[position].toll * 2;
				JOptionPane.showMessageDialog(null, message);
			}			
			
//			playerMoney = Integer.toString(players[i].getMoney());
			moneyLabel.setText(players[i].getName()+" 의 보유 금액 : "+ players[i].getMoney());
			

			if (player.getMoney() <= 0) { // 게임 종료
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
				System.exit(1);// 게임 종료
			
			}

		}
		
		}

	}


	public static void main(String[] args) {
		new Map("testUser");

	}

	
}
