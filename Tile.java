package main;

public class Tile {
	
	
	private static TileInfo start = new TileInfo ("�����", 0, 484, 516, 0);
	private static TileInfo Taipei = new TileInfo ("Ÿ������", 1, 400, 516, 2);
	private static TileInfo Hongkong = new TileInfo ("ȫ��", 2, 330, 516, 4);
	private static TileInfo goldkey_1 = new TileInfo ("Ȳ�ݿ���", 3, 260, 516, 0);
	private static TileInfo Manila = new TileInfo ("���Ҷ�", 4, 190, 516, 6);
	private static TileInfo Singapore = new TileInfo ("�̰�����", 5, 120, 516, 8);
	private static TileInfo Island = new TileInfo ("���ε�", 6, 30, 516, 0);
	private static TileInfo Tokyo = new TileInfo ("����", 7, 30, 400, 10);
	private static TileInfo Copenhagen = new TileInfo ("�����ϰ�", 8, 30, 330, 12);
	private static TileInfo goldkey_2 = new TileInfo ("Ȳ�ݿ���", 9, 30, 260, 0);
	private static TileInfo Zurich = new TileInfo ("�븮��", 10, 30, 190, 14);
	private static TileInfo Prague = new TileInfo ("������", 11, 30, 120, 16);
	private static TileInfo Reward = new TileInfo ("��ȸ��������", 12, 30, 30, 0);
	private static TileInfo Berlin = new TileInfo ("������", 13, 120, 30, 18);
	private static TileInfo Lisbon = new TileInfo ("������", 14, 190, 30, 20);
	private static TileInfo goldkey_3 = new TileInfo ("Ȳ�ݿ���", 15, 260, 30, 0);
	private static TileInfo Madrid = new TileInfo ("���帮��", 16, 330, 30, 22);
	private static TileInfo Paris = new TileInfo ("�ĸ�", 17, 400, 30, 24);
	private static TileInfo Fine = new TileInfo ("����", 18, 500, 30, 0);
	private static TileInfo Rome = new TileInfo ("�θ�", 19, 512, 120, 26);
	private static TileInfo London = new TileInfo ("����", 20, 512, 190, 28);
	private static TileInfo Newyork = new TileInfo ("����", 21, 512, 260, 28);
	private static TileInfo Sinchon = new TileInfo ("����", 22, 512, 330, 29);
	private static TileInfo Seoul = new TileInfo ("����", 23, 500, 390, 30);
	
	
	/*
	 Ư������
	 
	 ����� = 0
	 ���ε� = 6
	 ��� = 12
	 ���� = 18
	 
	 */
	
	
	static TileInfo[] tileList = {start, Taipei, Hongkong, goldkey_1, Manila, Singapore, 
								Island, Tokyo, Copenhagen, goldkey_2, Zurich, Prague, 
								Reward, Berlin, Lisbon, goldkey_3, Madrid, Paris, 
								Fine, Rome, London, Newyork, Sinchon, Seoul};

	
	public static void main(String[] args) {

	}

}