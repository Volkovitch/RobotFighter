package Default;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Player 
{
	// déclaration des attributs
	private float x = 500, y = 100;
	private int direction = 2;
	private boolean onStair = false;
	private boolean moving = false;
	private Animation[] animations = new Animation[8];
	private Map map;
	
	//Constructeur
	public Player(Map map)
	{
		this.map = map;
	}
		
			//////////////////////////////
			///Méthode d'initialisation///
			//////////////////////////////
	
	public void init() throws SlickException
	{
		SpriteSheet spriteSheet = new SpriteSheet("/src/main/resources/caracteres/character.png", 64, 64);
	    this.animations[0] = loadAnimation(spriteSheet, 0, 1, 0);
	    this.animations[1] = loadAnimation(spriteSheet, 0, 1, 1);
	    this.animations[2] = loadAnimation(spriteSheet, 0, 1, 2);
	    this.animations[3] = loadAnimation(spriteSheet, 0, 1, 3);
	    this.animations[4] = loadAnimation(spriteSheet, 1, 9, 0);
	    this.animations[5] = loadAnimation(spriteSheet, 1, 9, 1);
	    this.animations[6] = loadAnimation(spriteSheet, 1, 9, 2);
	    this.animations[7] = loadAnimation(spriteSheet, 1, 9, 3);
	}
	
	//Chargement de l'animation du personnage
	private Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) 
	{
		Animation animation = new Animation();
		for (int x = startX; x < endX; x++)
		{
			animation.addFrame(spriteSheet.getSprite(x, y), 50);
		}
		return animation;
	}
	
			/////////////////////////
			///Méthode d'affichage///
			/////////////////////////
	
	//Affichage du personnage et de son ombre
	public void render (Graphics g)
	{
		g.setColor(new Color(0, 0, 0, .5f));
		g.fillOval((int) x -16, (int) y - 8, 32, 16);
		g.drawAnimation(animations[direction + (moving ? 4:0)], (int) x - 32, (int) y - 60);
	}
	
			//////////////////////////////
			///Méthodes calcul position///
			//////////////////////////////
	
	public void update (int delta)
	{
		//Gestion du mouvement en fonction de la collision
		if(this.moving)
		{
			float futurX = getFuturX(delta);
			float futurY = getFuturY(delta);
			boolean collision = this.map.isCollision(futurX, futurY);
			if (collision)
			{
				this.moving = false;
				
			}
			else
			{
				this.x = futurX;
				this.y = futurY;
			}			
		}
	}
	
	//Méthode calcul Futur X
	private float getFuturX(int delta)
	{
		float futurX = this.x;
		switch (this.direction)
		{
		case 1: 
			futurX = this.x - .2f * delta; 
			break;
		case 3:
			futurX = this.x + .2f * delta;
			break;
		}
		return futurX;
	}
	
	//Méthode calcul Futur Y
	private float getFuturY(int delta)
	{
		float futurY = this.y;
		switch (this.direction)
		{
		case 0:
			futurY = this.y - .2f * delta;
			break;
		case 2:
			futurY = this.y + .2f * delta;
			break;
		case 1:
			if (this.onStair) {
				futurY = this.y + .2f * delta;
			}
			break;
		case 3:
			if (this.onStair) {
				futurY = this.y - .2f * delta;
			}
			break;
		}
		return futurY;
	}
	
	//Getters Setters
	  public float getX() { return x; }
	  public void setX(float x) { this.x = x; }
	  public float getY() { return y; }
	  public void setY(float y) { this.y = y; }
	  public int getDirection() { return direction; }
	  public void setDirection(int direction) { this.direction = direction; }
	  public boolean isMoving() { return moving; }
	  public void setMoving(boolean moving) { this.moving = moving; }
	  public boolean isOnStair() { return onStair; }
	  public void setOnStair(boolean onStair) { this.onStair = onStair; }
}
