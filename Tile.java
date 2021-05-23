package login;


public class Tile {
	
	
	private static TileInfo start = new TileInfo ("출발점", 0, 484, 516, 2);
	private static TileInfo Taipei = new TileInfo ("타이페이", 1, 400, 516, 4);
	private static TileInfo Hongkong = new TileInfo ("홍콩", 2, 330, 516, 4);
	private static TileInfo Bucheon = new TileInfo ("부천", 3, 260, 516, 5);
	private static TileInfo Manila = new TileInfo ("마닐라", 4, 190, 516, 6);
	private static TileInfo Singapore = new TileInfo ("싱가포르", 5, 120, 516, 8);
	private static TileInfo Island = new TileInfo ("무인도", 6, 14, 516, 0);
	private static TileInfo Tokyo = new TileInfo ("도쿄", 7, 14, 400, 10);
	private static TileInfo Copenhagen = new TileInfo ("코펜하겐", 8, 14, 330, 12);
	private static TileInfo Incheon = new TileInfo ("인천", 9, 14, 260, 15);
	private static TileInfo Zurich = new TileInfo ("취리히", 10, 14, 190, 14);
	private static TileInfo Prague = new TileInfo ("프라하", 11, 14, 120, 16);
	private static TileInfo Reward = new TileInfo ("사회복지센터", 12, 21, 14, 0);
	private static TileInfo Berlin = new TileInfo ("베를린", 13, 120, 14, 18);
	private static TileInfo Lisbon = new TileInfo ("리스본", 14, 190, 14, 20);
	private static TileInfo Busan = new TileInfo ("부산", 15, 260, 14, 21);
	private static TileInfo Madrid = new TileInfo ("마드리드", 16, 330, 14, 22);
	private static TileInfo Paris = new TileInfo ("파리", 17, 400, 14, 24);
	private static TileInfo Fine = new TileInfo ("병원", 18, 512, 27, 0);
	private static TileInfo Rome = new TileInfo ("로마", 19, 512, 120, 26);
	private static TileInfo London = new TileInfo ("런던", 20, 512, 190, 28);
	private static TileInfo Newyork = new TileInfo ("뉴욕", 21, 512, 260, 28);
	private static TileInfo Sinchon = new TileInfo ("신촌", 22, 512, 330, 29);
	private static TileInfo Seoul = new TileInfo ("서울", 23, 512, 400, 30);
	
	
	/*
	 특수지역
	 
	 출발점 = 0
	 무인도 = 6
	 상금 = 12
	 벌금 = 18
	 
	 */
	

	
	static TileInfo[] tileList = {start, Taipei, Hongkong, Bucheon, Manila, Singapore, 
								Island, Tokyo, Copenhagen, Incheon, Zurich, Prague, 
								Reward, Berlin, Lisbon, Busan, Madrid, Paris, 
								Fine, Rome, London, Newyork, Sinchon, Seoul};

	
	public static void main(String[] args) {
//		Tile tile = new Tile();
//		System.out.println(tileList[0].x);
//		System.out.println(tileList[0].y);
	}

}

//private static Point start = new Point(484, 484);
//private static Point Taipei  = new Point(400, 516);
//private static Point Hongkong = new Point(330, 516);
//private static Point Bucheon = new Point(260, 516);
//private static Point Manila = new Point(190, 516);
//private static Point Singapore = new Point(120, 516);
//private static Point Island = new Point(20, 484);
//private static Point Tokyo = new Point(14, 400);
//private static Point Copenhagen = new Point(14, 330);
//private static Point Incheon = new Point(14, 260);
//private static Point Zurich = new Point(14, 190);
//private static Point Prague = new Point(14, 120);
//private static Point Reward = new Point(21, 21);
//private static Point Berlin = new Point(120, 14);
//private static Point Lisbon = new Point(190, 14);
//private static Point Busan = new Point(260, 14);
//private static Point Madrid = new Point(330, 14);
//private static Point Paris = new Point(400, 14);
//private static Point Fine = new Point(484, 27);
//private static Point Rome = new Point(512, 120);
//private static Point London = new Point(512, 190);
//private static Point Newyork = new Point(512, 260);
//private static Point Sinchon = new Point(512, 330);
//private static Point Seoul = new Point(512, 400);


//static Point[] map = {start, Taipei, Hongkong, Bucheon, Manila, Singapore, 
//					Island, Tokyo, Copenhagen, Incheon, Zurich, Prague, 
//					Reward, Berlin, Lisbon, Busan, Madrid, Paris, 
//					Fine, Rome, London, Newyork, Sinchon, Seoul};

