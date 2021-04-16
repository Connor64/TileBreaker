package Objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

public class TileManager {
	
	private HashMap<TileType, Integer> typeTable = new HashMap<TileType, Integer>() {{
		put(TileType.BOMB, 1);
		put(TileType.EMPTY, 1);
		put(TileType.NORMAL, 20);
		put(TileType.INDESTRUCTIBLE, 7);
	}};
	private Random rand = new Random();
	private ArrayList<Integer> tileChances;
	
	private static TileManager instance;
	public static TileManager Instance() {
		if (instance == null) {
			instance = new TileManager();
		}
		return instance;
	}
	
	public TileManager() {
		tileChances = new ArrayList<Integer>();
		
		int val = 0;
		for (Entry<TileType, Integer> entry : typeTable.entrySet()) {
			for (int i = 0; i < entry.getValue().intValue(); i++) {
				System.out.println(val);
				tileChances.add(val);
			}
			val++;
		}
	}
	
	public TileType randomType() {
		TileType[] types = new TileType[typeTable.size()];

		types = typeTable.keySet().toArray(types);
		
		return types[tileChances.get(rand.nextInt(tileChances.size()))];
		
	}
}