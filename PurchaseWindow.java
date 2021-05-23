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
		setTitle("구매창");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		topPanel = new JPanel();
		label = new JLabel(tileInfo.name +"을(를) 구매하시겠습니까?");
		topPanel.add(label);
		sizePanel = new JPanel();

		r1 = new JRadioButton("예");
		r2 = new JRadioButton("아니오");

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


			JOptionPane.showMessageDialog(null, tileInfo.name + "을(를) 구매하였습니다");
			tileInfo.buy(player);
//				try {
//	                Thread.sleep(500);
//	            } catch (InterruptedException e1) {
//	                // TODO Auto-generated catch block
//	                e1.printStackTrace();
//	            }
			
			
			
			JOptionPane.showMessageDialog(null, tileInfo.name + "은(는) "+player.getName()+"의 땅입니다.");
		} else if (e.getSource() == r2) {
			JOptionPane.showMessageDialog(null, "취소하였습니다");
		}
		setVisible(false);
		dispose();
	}	


}
