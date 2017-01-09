package Default;

import org.newdawn.slick.SlickException;

public class TriggerController 
{
	private Map map;
	private Player player;
	
	//Constructeur
	public TriggerController(Map map, Player player)
	{
		this.map = map;
		this.player = player;
	}
	
	//Méthode update générale, appelant les autres méthodes
	public void update() throws SlickException
	{
		this.player.setOnStair(false);
		for (int objectID = 0; objectID < this.map.getObjectCount(); objectID ++)
		{
			if(isInTrigger(objectID))
			{
				//Appel des méthodes en fonction du type de l'object (teleport/changemap/ etc
				if ("teleport".equals(this.map.getObjectType(objectID)))
				{
					this.teleport(objectID);
				} 
				else if ("stair".equals(this.map.getObjectType(objectID))) 
				{
				this.player.setOnStair(true);
				} 
				else if ("change-map".equals(this.map.getObjectType(objectID))) 
				{
				this.changeMap(objectID);
				}
			}
		}
	}
	
	//méthode gérant la téléportation
	private void teleport(int objectID)
	{
		//TP du personnage à la position x/y voulu grace au paramettre de l'objet tiled (dest-x dest-y)
		this.player.setX(Float.parseFloat(this.map.getObjectProperty(objectID, "dest-x", Float.toString(this.player.getX()))));
		this.player.setY(Float.parseFloat(this.map.getObjectProperty(objectID, "dest-y", Float.toString(this.player.getY()))));		
	}
	
	//calcul de la position du joueur en fonction de l'objet tiled
	private boolean isInTrigger(int id) 
	{
		return this.player.getX() > this.map.getObjectX(id)
				&& this.player.getX() < this.map.getObjectX(id) + this.map.getObjectWidth(id)
				&& this.player.getY() > this.map.getObjectY(id)
				&& this.player.getY() < this.map.getObjectY(id) + this.map.getObjectHeight(id);
	}
	
	private void changeMap(int objectID) throws SlickException 
	{
		this.teleport(objectID);
		String newMap = this.map.getObjectProperty(objectID, "dest-map", "undefined");
		if (!"undefined".equals(newMap)) 
		{
			this.map.changeMap("/src/main/resources/map/" + newMap);
		}
	}
}
