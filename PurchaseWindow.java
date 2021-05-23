package login;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

class PurchaseWindow extends JFrame implements ActionListener {
	private JRadioButton r1, r2;
	private JLabel text, label;
	private JPanel topPanel, sizePanel;
	private ButtonGroup size;
	private TileInfo tileInfo;
	private Map map;
//	private static Map map = new Map();
//	private static Tile tile = new Tile();

//	static int buy;

	private Player player;

	public PurchaseWindow(int playerIdx, TileInfo tile, Map root) {
		
//			try {
//	            Thread.sleep(1000);
//	        } catch (InterruptedException e) {
//	            e.printStackTrace();
//	        }
		
		this.map = root;
		this.player = map.getPlayerByIndex(playerIdx);
		this.tileInfo = tile;
		setSize(200, 200);
		setTitle("����â");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		topPanel = new JPanel();
		label = new JLabel(tileInfo.name +"��(��) �����Ͻðڽ��ϱ�?");
		topPanel.add(label);
		sizePanel = new JPanel();

		r1 = new JRadioButton("��");
		r2 = new JRadioButton("�ƴϿ�");

		size = new ButtonGroup();
		size.add(r1);
		size.add(r2);

		r1.addActionListener(this);
		r2.addActionListener(this);

		sizePanel.add(r1);
		sizePanel.add(r2);

		this.add(topPanel, BorderLayout.CENTER);
		this.add(sizePanel, BorderLayout.SOUTH);
		pack();
		setVisible(true);
		
		
	}

	@Override

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == r1) {


			JOptionPane.showMessageDialog(null, tileInfo.name + "��(��) �����Ͽ����ϴ�");
			tileInfo.buy(player);
//				try {
//	                Thread.sleep(500);
//	            } catch (InterruptedException e1) {
//	                // TODO Auto-generated catch block
//	                e1.printStackTrace();
//	            }
			
			
			
			JOptionPane.showMessageDialog(null, tileInfo.name + "��(��) "+player.getName()+"�� ���Դϴ�.");
		} else if (e.getSource() == r2) {
			JOptionPane.showMessageDialog(null, "����Ͽ����ϴ�");
		}
		setVisible(false);
		dispose();
	}	


}
