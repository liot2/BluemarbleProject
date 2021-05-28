package main;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.security.sasl.RealmChoiceCallback;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class Login extends JFrame {

	private BufferedImage img = null;

	private Choice numPlayer;
	private JButton startbtn;
	
	private JLabel text;
	
	private static Clip clip;

	
	private int choiceNum = 0;
	
	
	// 배경 사운드
	
		public static void playSound(String pathName, boolean isLoop) {
			try {
				clip = AudioSystem.getClip();
				File audioFile = new File(pathName);
				AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
				clip.open(audioStream);
				clip.start();
				if(isLoop)
					clip.loop(clip.LOOP_CONTINUOUSLY);
			} catch(LineUnavailableException | UnsupportedAudioFileException | IOException e) {
				e.printStackTrace();
			}
		}
	

	public Login() {

		setTitle("재미난 부루마블");
		setSize(465, 600);
		
		
		playSound("sound/Sneaky.wav", true);
		

		// 레이아웃 설정
		setLayout(null);
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 465, 600);
		layeredPane.setLayout(null);
		
		

		// 배경
		try {
			img = ImageIO.read(new File("img/login_1.png"));
		} catch (IOException e) {
			System.out.println("이미지 불러오기 실패");
			System.exit(0);
		}

		MainPanel panel = new MainPanel();
		panel.setBounds(0, 0, 465, 600);
		
		
		text = new JLabel("함께 플레이 할 인원을 선택해주세요");
		text.setBounds(120, 450, 300, 30);
		text.setFont(new Font("BusanFont_Provisional", Font.PLAIN, 15));
		layeredPane.add(text);
		
		
		
		numPlayer = new Choice();
		
		numPlayer.add("2명");
		numPlayer.add("3명");
		numPlayer.add("4명");
		
		numPlayer.setBounds(120, 405, 150, 50);
		layeredPane.add(numPlayer);
		
		
		
		// 시작 버튼
		startbtn = new JButton("START");
		startbtn.setBounds(250, 400, 100, 35);
		startbtn.addActionListener(loginListener);

		startbtn.setFocusPainted(false); // 포커스 없애기
		startbtn.setContentAreaFilled(false); // 배경색상 투명하게
		startbtn.setFont(new Font("BusanFont_Provisional", Font.PLAIN, 15));
		layeredPane.add(startbtn);
		

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
			
			choiceNum = (numPlayer.getSelectedIndex())+2;
			int result = JOptionPane.showConfirmDialog(null, "선택한 인원이 "+ choiceNum + "명이 맞습니까?", "확인창", JOptionPane.YES_NO_OPTION);
			
			if (result == JOptionPane.YES_OPTION) {
				setVisible(false);
				System.out.println(choiceNum);
				new Map(choiceNum);
			
			} else if (result == JOptionPane.NO_OPTION) {
				JOptionPane.showMessageDialog(null, "다시 선택해주세요!");
			}
		}

	};
	

	public static void main(String[] args) {
		Login log = new Login();
		
		
	}

}
