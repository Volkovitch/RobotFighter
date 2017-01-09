package Default;

import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

//KeyListener est une interface qui permet de recevoir et de traiter les events clavier
public class PlayerController implements KeyListener
{
	
	private Player player;
	
	//Constructeur
	public PlayerController(Player player)
	{
		this.player = player;
	}

	
			/////////////////////////////////
			///Méthodes de gestion touches///
			/////////////////////////////////
	
	
	@Override
	public void inputEnded() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputStarted()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isAcceptingInput() 
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void setInput(Input input) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(int key, char c) 
	{
		switch(key)	
		{
			case Input.KEY_Z:
				this.player.setDirection(0);
				this.player.setMoving(true);
				break;
			case Input.KEY_Q:
				this.player.setDirection(1);
				this.player.setMoving(true);
				break;
			case Input.KEY_S:
				this.player.setDirection(2);
				this.player.setMoving(true);
				break;
			case Input.KEY_D:
				this.player.setDirection(3);
				this.player.setMoving(true);
				break;
		}
	}

	@Override
	public void keyReleased(int key, char c) 
	{
		this.player.setMoving(false);
	}
	
	//SettersGetters
	public boolean keyJumpPressed(){ return true; }
	
}
