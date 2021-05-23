package login;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Login extends JFrame {

	private BufferedImage img = null;
	private String userId = "";

	private JPanel background;
	private JLabel id;
	private JLabel message;
	private JTextField userTextField;
	private JButton loginbtn;
	private JButton resetbtn;
	private ImageIcon icon;

	public Login() {

		setTitle("�η縶��");
		setSize(440, 600);

		// ���̾ƿ� ����
		setLayout(null);
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 440, 600);
		layeredPane.setLayout(null);
		
		

		// ���
		try {
			img = ImageIO.read(new File("img/login_1_1.png"));
		} catch (IOException e) {
			System.out.println("�̹��� �ҷ����� ����");
			System.exit(0);
		}

		MainPanel panel = new MainPanel();
		panel.setBounds(0, 0, 440, 600);
		
		
		
//		ImageIcon icon = new ImageIcon("img/gamemap_1.png");
//		background = new JPanel((LayoutManager) icon);
//		            public void paintComponent(Graphics g) {
//		                g.drawImage(icon.getImage(), 0, 0, null); //�̹��� ����������� �ֱ�
//		               
//		  Dimension d = getSize();
//		                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null); // ������Ʈ ����� �°�
//		               
//		                Point p = scrollPane.getViewport().getViewPosition();
//		                g.drawImage(icon.getImage(), p.x, p.y, null); //��ũ�Ѿȿ� ��ġ�� �����ؼ�
//		
//		            }
//		        }
		        
		        
//		        icon = new ImageIcon("img/login_1.png");
//		        JPanel panel = new JPanel() {
//		        	   public void paintComponent(Graphics g) {
//		        	    // Approach 1: Dispaly image at at full size
////		        	    g.drawImage(icon.getImage(), 0, 0, null);
//		        	    // Approach 2: Scale image to size of component
//		        	     Dimension d = getSize();
//		        	     g.drawImage(icon.getImage(), 0, 0, 440, 600, null);
//		        	    // Approach 3: Fix the image position in the scroll pane
//		        	    // Point p = scrollPane.getViewport().getViewPosition();
//		        	    // g.drawImage(icon.getImage(), p.x, p.y, null);
//		        	    setOpaque(false);
//		        	    super.paintComponent(g);
//		        	   }
//		        	  };
		
		
		
		
		

		// �α���
		id = new JLabel("ID   :   ");
		id.setBounds(80, 400, 80, 30);
		id.setFont(new Font("BusanFont_Provisional", Font.PLAIN, 20));
		layeredPane.add(id);

		message = new JLabel("2 ~ 6���ڷ� �Է����ּ���!!");
		message.setBounds(120, 500, 300, 30);
		message.setFont(new Font("BusanFont_Provisional", Font.PLAIN, 15));
		layeredPane.add(message);

		userTextField = new JTextField(15);
		userTextField.setBounds(120, 400, 150, 35);

		layeredPane.add(userTextField);
		userTextField.setFont(new Font("BusanFont_Provisional", Font.PLAIN, 15));
		userTextField.setForeground(new Color(47, 79, 145));
//		userText.setBorder(null);

		// �α��� ��ư
		loginbtn = new JButton("�α���");
		loginbtn.setBounds(270, 400, 90, 35);
		loginbtn.addActionListener(loginListener);

//		loginbtn.setBorderPainted(false);	
		loginbtn.setFocusPainted(false); // ��Ŀ�� ���ֱ�
		loginbtn.setContentAreaFilled(false); // ������ �����ϰ�
		loginbtn.setFont(new Font("BusanFont_Provisional", Font.PLAIN, 15));
		layeredPane.add(loginbtn);
		
		
		// ���� ��ư
		resetbtn = new JButton("����");
		resetbtn.setBounds(270, 440, 90, 35);
		resetbtn.addActionListener(resetListener);

		resetbtn.setFocusPainted(false);
		resetbtn.setContentAreaFilled(false);
		resetbtn.setFont(new Font("BusanFont_Provisional", Font.PLAIN, 15));
		layeredPane.add(resetbtn);
		

		layeredPane.add(panel);
		add(layeredPane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	class MainPanel extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
		}
	}

	private ActionListener loginListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			userId = userTextField.getText();
			if (userId.isBlank()) {
				message.setText("ID�� �Է����ּ��� !");
				message.setFont(new Font("BusanFont_Provisional", Font.PLAIN, 15));
				message.setForeground(new Color(255, 0, 0));
				return;
			}
			
			if (2 > userId.length() || userId.length() > 6) {
				message.setText("2 ~ 6���� ID�� �������ּ��� !");
				message.setFont(new Font("BusanFont_Provisional", Font.PLAIN, 15));
				message.setForeground(new Color(255, 0, 0));
				return;
			}

			setVisible(false);
			Map map = new Map(userId);

//			new Client(userInput); => ���߿� Client�� �÷��̾� �̸����� �ֱ�
		}

	};

	private ActionListener resetListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			userTextField.setText(null);
		}
	};

	public static void main(String[] args) {
		new Login();
	}

}
