package main;

public class Tile {
	
	
	private static TileInfo start = new TileInfo ("출발점", 0, 484, 516, 0);
	private static TileInfo Taipei = new TileInfo ("타이페이", 1, 400, 516, 2);
	private static TileInfo Hongkong = new TileInfo ("홍콩", 2, 330, 516, 4);
	private static TileInfo goldkey_1 = new TileInfo ("황금열쇠", 3, 260, 516, 0);
	private static TileInfo Manila = new TileInfo ("마닐라", 4, 190, 516, 6);
	private static TileInfo Singapore = new TileInfo ("싱가포르", 5, 120, 516, 8);
	private static TileInfo Island = new TileInfo ("무인도", 6, 30, 516, 0);
	private static TileInfo Tokyo = new TileInfo ("도쿄", 7, 30, 400, 10);
	private static TileInfo Copenhagen = new TileInfo ("코펜하겐", 8, 30, 330, 12);
	private static TileInfo goldkey_2 = new TileInfo ("황금열쇠", 9, 30, 260, 0);
	private static TileInfo Zurich = new TileInfo ("취리히", 10, 30, 190, 14);
	private static TileInfo Prague = new TileInfo ("프라하", 11, 30, 120, 16);
	private static TileInfo Reward = new TileInfo ("사회복지센터", 12, 30, 30, 0);
	private static TileInfo Berlin = new TileInfo ("베를린", 13, 120, 30, 18);
	private static TileInfo Lisbon = new TileInfo ("리스본", 14, 190, 30, 20);
	private static TileInfo goldkey_3 = new TileInfo ("황금열쇠", 15, 260, 30, 0);
	private static TileInfo Madrid = new TileInfo ("마드리드", 16, 330, 30, 22);
	private static TileInfo Paris = new TileInfo ("파리", 17, 400, 30, 24);
	private static TileInfo Fine = new TileInfo ("병원", 18, 500, 30, 0);
	private static TileInfo Rome = new TileInfo ("로마", 19, 512, 120, 26);
	private static TileInfo London = new TileInfo ("런던", 20, 512, 190, 28);
	private static TileInfo Newyork = new TileInfo ("뉴욕", 21, 512, 260, 28);
	private static TileInfo Sinchon = new TileInfo ("신촌", 22, 512, 330, 29);
	private static TileInfo Seoul = new TileInfo ("서울", 23, 500, 390, 30);
	
	
	/*
	 특수지역
	 
	 출발점 = 0
	 무인도 = 6
	 상금 = 12
	 벌금 = 18
	 
	 */
	
	
	static TileInfo[] tileList = {start, Taipei, Hongkong, goldkey_1, Manila, Singapore, 
								Island, Tokyo, Copenhagen, goldkey_2, Zurich, Prague, 
								Reward, Berlin, Lisbon, goldkey_3, Madrid, Paris, 
								Fine, Rome, London, Newyork, Sinchon, Seoul};

	
	public static void main(String[] args) {

	}

}