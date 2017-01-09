package Default;

import org.newdawn.slick.SlickException;

public class PhysicsGame 
{
	private Player player;
	private PlayerController playerController;
	private Map map;
	private boolean jump = false;
	private float g = 0;
	private int elapsedTimeGravity = 0;
	private int elapsedTimeJump = 0;

	//Constructeur
	public PhysicsGame(Map map,Player player)
	{
		this.player = player;
		this.map = map;
	}
	

	/////////////////////////
	///Méthodes de gravité///
	/////////////////////////
	
	public void update(int delta) throws SlickException
	{
		this.gravity(delta);
		this.jump(delta);
	}
	
	public void gravity(int delta) throws SlickException
	{
		float posY = this.player.getY();
		float posX = this.player.getX();
		boolean collision = this.map.isCollision(posX, posY);
		elapsedTimeGravity += delta;
  	   	if(elapsedTimeGravity >= 5)
  	   	{
  	   	elapsedTimeGravity = 0;
			if(!collision || !jump)
			{
				posY = posY + 1 * g;
				g = (float) (g + 0.1);
				this.player.setY(posY);
			}
			if(collision)
			{
				g = 0;
			}
  	  	}
	}
	
	//////////////////////
	///Méthodes de saut///
	//////////////////////
	
	public void jump(int delta) throws SlickException
	{
		/*float posY = this.player.getY();
		float posX = this.player.getX();
		boolean collision = this.map.isCollision(posX, posY);
		boolean isJumping = this.playerController.keyJumpPressed();
		if(collision && isJumping)
		{
			elapsedTimeJump += delta;
			if(elapsedTimeJump >= 30)
			{
				elapsedTimeJump = 0;
				this.jump = true;
			}
			else
			{
				this.jump = false;
			}
		}*/
	}	
}
