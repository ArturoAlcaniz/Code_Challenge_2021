package challenge_nine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapSprite {

	private String[][] mapWithSprites;
	private List<Integer[]> spritesInMap = new ArrayList<>();
	private List<Integer[][]> typeSprites;
	private HashMap<String, List<String>> collisions = new HashMap<>();
	
	public void initializeMap(int y) {
		this.mapWithSprites = new String[1][y];
	}
	
	public void addSprite(Integer[] sprite) {
		this.spritesInMap.add(sprite);
	}
	
	public void setTypeSprites(List<Integer[][]> typeSprites) {
		this.typeSprites = typeSprites;
	}
	
	public void makeCollisions() {
		int num = 0;
		int size = this.mapWithSprites[0].length-1;
		while(num<=size) {
			for(int s=this.spritesInMap.size(); --s >= 0;) {
				makeCollision(this.spritesInMap.get(s), num, String.valueOf(s));
			}
			this.mapWithSprites[0] = new String[this.mapWithSprites[0].length];
			num++;
		}
	}
	
	public void makeCollision(Integer[] sprite, int iM, String s) {
		Integer[][] definitionSprite = typeSprites.get(sprite[0]);
		int xStart = sprite[1];
		int yStart = sprite[2];
		int posxDefinitionSprite = iM-xStart;
		try {
			if(definitionSprite[posxDefinitionSprite][0] == null)
				return;
		} catch (Exception e) {
			return;
		}
		
		for(int j=0; j<definitionSprite[posxDefinitionSprite].length; j++) {
			
			if(definitionSprite[posxDefinitionSprite][j] == 0)
				continue;

			int posyArray = yStart+j;
			
			if(this.mapWithSprites[0][posyArray] == null) {
				this.mapWithSprites[0][posyArray] = s;
			} else {
				this.mapWithSprites[0][posyArray] = new StringBuilder(this.mapWithSprites[0][posyArray]).append(",").append(s).toString();
				String[] arraySplit = this.mapWithSprites[0][posyArray].split(",");
				for(int as=0; as<arraySplit.length-1; as++) {
					List<String> collisionWithSaved = this.collisions.get(arraySplit[as]);
					try {
						if(!collisionWithSaved.contains(s))
							collisionWithSaved.add(s);
					} catch (Exception e) {
						List<String> collisionWith = new ArrayList<>();
						collisionWith.add(s);
						collisions.put(arraySplit[as], collisionWith);
					}
				}
			}
		}
	}
	
	public Integer obtainCollisions() {
		int collisionsCount = 0;
		int size = this.spritesInMap.size();
		for(int i=0; i<size;i++) {
			for(int j=i; j<size; j++) {
				
				if(i == j) {
					continue;
				}
				String sprite1 = String.valueOf(i);
				String sprite2 = String.valueOf(j);
				
				if(checkCollisionsKeys(sprite1, sprite2)) {
					collisionsCount++;
				} 
			}
		}
		return collisionsCount;
	}
	
	private boolean checkCollisionsKeys(String sprite1, String sprite2) {
		if(this.collisions.containsKey(sprite1) && !this.collisions.get(sprite1).isEmpty() && this.collisions.get(sprite1).contains(sprite2))
			return true;
		
		return this.collisions.containsKey(sprite2) && !this.collisions.get(sprite2).isEmpty() && this.collisions.get(sprite2).contains(sprite1);
	}

}