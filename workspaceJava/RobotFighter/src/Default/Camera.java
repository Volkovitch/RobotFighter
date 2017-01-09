package Default;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Camera 
{
	private Player player;
	private float xCamera, yCamera;
	
	//Constructeur
	public Camera(Player player)
	{
		this.player = player;
		this.xCamera = player.getX();
		this.yCamera = player.getY();
	}
	
	//Méthode de cadrage de la camera
	public void place(GameContainer container, Graphics g)
	{
		g.translate(container.getWidth() / 2 - (int) this.xCamera, container.getHeight() / 2 - (int) this.yCamera);
	}
	
	//Méthode Update pour la "traque" de la position du joueur
	public void update (GameContainer container)
	{
		//Traque en X
		int w = container.getWidth() / 4;
		if (this.player.getX() > this.xCamera + w) 
		{
			this.xCamera = this.player.getX() - w;
		} 
		else if (this.player.getX() < this.xCamera - w) 
		{
			this.xCamera = this.player.getX() + w;
		}
		
		//Traque en Y
		int h = container.getHeight() / 4;
		if (this.player.getY() > this.yCamera + h) 
		{
			this.yCamera = this.player.getY() - h;
		}
		else if (this.player.getY() < this.yCamera - h) 
		{
			this.yCamera = this.player.getY() + h;
		}
	}
}
