package Default;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Map 
{
	//d�claration des attributs
	private TiledMap tiledMap;
	
	//constructeur
	
			//////////////////////////////
			///M�thode d'initialisation///
			//////////////////////////////

	//m�thode d'initialisation de la map
	public void init() throws SlickException
	{
		this.tiledMap = new TiledMap("/src/main/resources/map/map1.tmx");
		
	}
	
				//////////////////////////
				///M�thodes d'affichage///
				//////////////////////////
	
	//m�thode d'affichage arriere plan
	public void renderBackground()
	{
		this.tiledMap.render(0, 0, 0);
		this.tiledMap.render(0, 0, 1);
		this.tiledMap.render(0, 0, 2);
		this.tiledMap.render(0, 0, 3);
	}
	
	//m�thode d'affichage Personnage et Avant plan
	public void renderForeground()
	{
		/*this.tiledMap.render(0, 0, 1);
		this.tiledMap.render(0, 0, 2);*/
	}
	
				//////////////////////////
				///M�thode de collision///
				//////////////////////////
	
	//M�thode de gestion de la collision
	public boolean isCollision(float x, float y)
	{
		int tileWidth = this.tiledMap.getTileWidth();
		int tileHeight = this.tiledMap.getTileHeight();
		int collisionLayer = this.tiledMap.getLayerIndex("collision");
		
		Image tile = this.tiledMap.getTileImage((int) x / tileWidth, (int) y / tileHeight, collisionLayer);
		
		boolean collision = tile != null;
		if(collision)
		{
			Color color = tile.getColor((int) x % tileWidth, (int) y % tileHeight);
			collision = color.getAlpha() > 0;
		}
		return collision;
	}
		
				///////////////////////////
				///M�thodes des triggers///
				///////////////////////////
	
	//liste des m�thodes utile � la t�l�portation, changement de map, etc...
	public void changeMap(String file) throws SlickException 
	{
		this.tiledMap = new TiledMap(file);
	}
	
	public int getObjectCount()
	{
		return this.tiledMap.getObjectCount(0);
	}
	
	public String getObjectType(int objectID)
	{
		return this.tiledMap.getObjectType(0, objectID);
	}
	
	public float getObjectX(int objectID) 
	{
		return this.tiledMap.getObjectX(0, objectID);
	}
	
	public float getObjectY(int objectID) 
	{
		return this.tiledMap.getObjectY(0, objectID);
	}
	
	public float getObjectWidth(int objectID) 
	{
		return this.tiledMap.getObjectWidth(0, objectID);
	}
	
	public float getObjectHeight(int objectID) 
	{
		return this.tiledMap.getObjectHeight(0, objectID);
	}
	
	public String getObjectProperty(int objectID, String propertyName, String def) 
	{
		return this.tiledMap.getObjectProperty(0, objectID, propertyName, def);
	}
}
