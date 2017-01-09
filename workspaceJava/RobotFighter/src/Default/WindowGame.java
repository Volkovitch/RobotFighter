package Default;

//import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
//import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
//import org.newdawn.slick.Image;
//import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
//import org.newdawn.slick.SpriteSheet;

public class WindowGame extends BasicGame
{
	//Déclaration des attributs
	private GameContainer container;
	private Map map = new Map();
	private Player player = new Player(map);
	private Camera camera = new Camera(player);
	private TriggerController triggers = new TriggerController(map, player);
	float g = 1;
	private PhysicsGame physics = new PhysicsGame(map, player);
	
	//Main
	public static void main(String[] args) throws SlickException 
	{
    	AppGameContainer app = new AppGameContainer(new WindowGame(), 1400, 700, false);
        app.setTargetFrameRate(60);
        app.start();
    }
	//Constructeur
	public WindowGame() 
	{
		super("RobotFighter");
	}

	
			///////////////////////////////
			///Méthodes d'initialisation///
			///////////////////////////////
	
	@Override
	public void init(GameContainer container) throws SlickException 
	{
		//Initialisation de container map musique et player
		this.container = container;
		this.map.init();
		this.player.init();
		Music background = new Music("/src/main/resources/sound/lost-in-the-meadows.ogg");
		background.loop();
		//Initialisation  du Playercontroller "controller"
		PlayerController controller = new PlayerController(this.player);
		container.getInput().addKeyListener(controller);
		
	}

			/////////////////////////
			///Méthode d'affichage///
			/////////////////////////
	
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException 
	{
		//Appel des méthodes d'affichage
		this.camera.place(container, g);
		this.map.renderBackground();
		this.player.render(g);
		this.map.renderForeground();
		
	}

	
			/////////////////////////////
			///Méthodes de mise à jour///
			/////////////////////////////
	
	@Override
	public void update(GameContainer container, int delta) throws SlickException 
	{
		this.triggers.update();
		this.player.update(delta);
		this.camera.update(container);
		this.physics.update(delta);
	}
}
/*
import org.newdawn.slick.tiled.TiledMap;

public class WindowGame extends BasicGame {
  

	private GameContainer container;
	private TiledMap map;
	private float x = 500, y = 350;
	private int direction = 0;
	private boolean movingUP = false;
	private boolean movingLEFT = false;
	private boolean movingDOWN = false;
	private boolean movingRIGHT = false;
	private Animation[] animations = new Animation[8];
	private boolean keyRight = false;
	private boolean keyLeft = false;
	private boolean keyUp = false;
	private float xCamera = x, yCamera = y;
	private boolean isJumping = false;
	 private float i;
	 private float j;
	 private float k;
	 private boolean gravity = true;
	 private float animationX = x, animationY = y;
	 
	 private int  elapsedTime = 0;
	 private int  elapsedTime2 = 0;
	 private int  elapsedTime3 = 0;
	//private Image GunShot;
	//private Boolean Shoting = false;

	//private Boolean ShotingCamera = false;
	//private Boolean RandomCamera = true;
	//private Boolean RandomCamera2 = false;
	

	public WindowGame() {
        super("RobotFighter");
    }
	
	public static void main(String[] args) throws SlickException {
    	AppGameContainer app = new AppGameContainer(new WindowGame(), 1920, 1080, false);
        app.setTargetFrameRate(60);
        app.start();
    }
	
	@Override
    public void init(GameContainer container) throws SlickException {
		//this.GunShot = new Image("E:/Developpement/workspaceJava/RobotFighter/src/main/resources/effect/lrflash0001gauchedroite.png");
        this.container = container;
        this.map = new TiledMap("D:/DevPerso/Developpement/workspaceJava/RobotFighter/src/main/resources/map/map1.tmx");

        SpriteSheet spriteSheet = new SpriteSheet("D:/DevPerso/Developpement/workspaceJava/RobotFighter/src/main/resources/caracteres/character.png", 64, 64);
        this.animations[0] = loadAnimation(spriteSheet, 0, 1, 0);
        this.animations[1] = loadAnimation(spriteSheet, 0, 1, 1);
        this.animations[2] = loadAnimation(spriteSheet, 0, 1, 2);
        this.animations[3] = loadAnimation(spriteSheet, 0, 1, 3);
        this.animations[4] = loadAnimation(spriteSheet, 1, 9, 0);
        this.animations[5] = loadAnimation(spriteSheet, 1, 9, 1);
        this.animations[6] = loadAnimation(spriteSheet, 1, 9, 2);
        this.animations[7] = loadAnimation(spriteSheet, 1, 9, 3); 
        
	
	}
	
	 @Override
	    public void render(GameContainer container, Graphics g) throws SlickException {
		 g.translate(container.getWidth() / 2 - (int) this.xCamera, 
		            container.getHeight() / 2 - (int) this.yCamera);
	    	this.map.render(0, 0, 0);
	    	this.map.render(0, 0, 1);
	    	this.map.render(0, 0, 3);
	    	this.map.render(0, 0, 4);
	    	
	    	g.setColor(new Color(0, 0, 0, .5f));
	    	g.fillOval(x - 16, y - 8, 32, 16);
	    	g.drawAnimation(animations[direction + (movingUP || movingDOWN || movingLEFT || movingRIGHT ? 4 : 0)], x-32, y-60);
	    	
	    	if(Shoting){
	    	g.drawImage(GunShot, animationX, animationY-35);
	    	}
	    	//this.map.render(0, 0, 5);
	    	  //GunShot.drawCentered(animationX + container.getWidth() * 1 / 4, animationY + container.getHeight() / 2);
	    	  
	    	}
	
	 @Override
	    public void update(GameContainer container, int delta) throws SlickException {
		 float futurX = this.x;
	        float futurY = this.y;
		 if(this.gravity == true){
			 
			 
		        futurY = this.y + .1f * delta;
			 Image tile = this.map.getTileImage(
		                (int) futurX / this.map.getTileWidth(), 
		                (int) futurY / this.map.getTileHeight(), 
		                this.map.getLayerIndex("collision"));
		        boolean collision = tile != null;
		        if (collision) {
		            this.gravity = false;
		        } else {
		            this.gravity = true;
		            
		            //this.y += 1;
		          
			    	    	
			    	     this.y += (1 * k) ;
			    	   
			    	     k = (float) (k + 0.1); //0.040817
			    	    
			    	     
			    	    
			    	     this.isJumping = false;
			    	     futurY = this.y;
			    	     futurX = this.x;
			    	     //animationY = y;
			    	     //animationX=x;
			    	     if(Shoting != true){
				    	     animationY = y;
				    	     }
			    	     gravity = true;
			    	    
			    	   
		            //animationY =y;
		        }
		 
		 }
		 if ((this.movingLEFT || this.movingRIGHT)) {
		        
		  
		        switch (this.direction) {
		         
		        case 1: futurX = this.x - .1f * delta; break;
		        case 3: futurX = this.x + .1f * delta; break;
		        }
		        
		       
		        Image tile = this.map.getTileImage(
		                (int) futurX / this.map.getTileWidth(), 
		                (int) futurY / this.map.getTileHeight(), 
		                this.map.getLayerIndex("collisionX"));
		        boolean collision = tile != null;
		        if (collision) {
		            Color color = tile.getColor(
		                    (int) futurX % this.map.getTileWidth(), 
		                    (int) futurY % this.map.getTileHeight());
		            collision = color.getAlpha() > 0;
		        }
		        if (collision) {
		            this.movingRIGHT = false;
		            this.movingLEFT = false;
		        } else {
		            this.x = futurX;
		            if(Shoting != true){
		            animationX=x;
		            }
		            this.gravity = true;
		        }
		    }
			 
		      
		    
		 
		 int w = container.getWidth() / 4;
		    if (this.x > this.xCamera + w) this.xCamera = this.x - w;
		    if (this.x < this.xCamera - w) this.xCamera = this.x + w;
		    int h = container.getHeight() / 4;
		    if (this.y > this.yCamera + h) this.yCamera = this.y - h;
		    if (this.y < this.yCamera - h) this.yCamera = this.y + h;
	    	 if (this.movingUP || this.movingDOWN || this.movingLEFT || this.movingRIGHT) {
	    	        switch (this.direction) {
	    	            case 0: this.y -= .1f * delta; break;
	    	            case 1: this.x -= .1f * delta; break;
	    	            case 2: this.y += .1f * delta; break;
	    	            case 3: this.x += .1f * delta; break;
	    	        }
	    	    }
	    	 
	    	 if (this.isJumping == true)
	    	  {
	    	   elapsedTime += delta;
	    	   if(elapsedTime >= 30)
	    	   {
	    	      elapsedTime = 0;
	    	    if (i > 0)
	    	    {
	    	    	
	    	     this.y -= (3 * j)*4 ;
	    	    if(j < 0){
	    	    j=0;	
	    	    }else{
	    	    
	    	     j = (float) (j - 0.04); //0.040817
	    	    }
	    	     i--;
	    	    }

	    	    if(i == 0)
	    	    {
	    	     this.isJumping = false;
	    	     futurY = this.y;
	    	     futurX = this.x;
	    	     //animationY = y;
	    	     //animationX=x;
	    	     if(Shoting != true){
		    	     animationY = y;
		    	     }
	    	     gravity = true;
	    	    }
	    	   }
	    	   
	    	  }
	    	 
	    	 if(this.Shoting == true){
	    		 elapsedTime2 += delta;
		    	   if(elapsedTime2 >= 10)
		    	   {
		    	      elapsedTime2 = 0;
		    	    if (i > 0)
		    	    {
		    	    	
		    	     this.animationX += 10;//(3 * j)*4 ;
		    	     //animationX = y;
		    	     //j = (float) (j - 0.040817);
		    	     if(ShotingCamera){
		    	    	 if(RandomCamera){
		    	    		 xCamera += -5;
		    	    		 //yCamera += Math.random() * ( -2 - -5 );
		    	    		 RandomCamera = false;
		    	    		 RandomCamera2 = true;
		    	    	 }
		    	    	 else if(RandomCamera2){
		    	    		 xCamera += 5;
		    	    		 //yCamera += Math.random() * ( 5 - 0 );
		    	    		 RandomCamera2 =false;
		    	    		 RandomCamera = true;
		    	    	 }
		    	     }
		    	     i--;
		    	    }

		    	    if(i == 0)
		    	    {
		    	     this.Shoting = false;
		    	     animationX=x;
		    	     animationY=y;
		    	    }
		    	   }
	    	 }
	    	 
	    	
	 }
    
    public void keyPressed(int key, char c) {
    	switch(key)
    	  {
    	   case Input.KEY_Q:
    	    this.direction = 1;
    	    this.movingLEFT = true;
    	    keyLeft = true;
    	    break;
    	    
    	    
    	    
    	   case Input.KEY_D:
      	     this.direction = 3;
      	     this.movingRIGHT = true;
      	     keyRight = true;
      	     break;
      	     
    	   case Input.KEY_Z:
    		    if (this.isJumping == false)
    		    {
    		        isJumping = true;
    		        this.i = 50;
    		        this.j = (float) (1);
    		        this.elapsedTime = 0;
    		     keyUp = true;
    		     break;
    		    }
    		    else
    		     break;
    		    
    	   case Input.KEY_E:
    		  //this.Shoting =true;
    		   //this.ShotingCamera = true;
    		   this.i = 50;
		        this.j = (float) (1);
		        this.elapsedTime2 = 0;
    		   break;
    	  }
        
    }

    

	private Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
        Animation animation = new Animation();
        for (int x = startX; x < endX; x++) {
            animation.addFrame(spriteSheet.getSprite(x, y), 100);
        }
        return animation;
    }

    @Override
    public void keyReleased(int key, char c) {
        if (Input.KEY_ESCAPE == key) {
            container.exit();
        }
        switch (key) {
        case Input.KEY_UP:   this.movingUP = false; break;
        
        case Input.KEY_Q: 
        	keyLeft = false;
        	if(keyRight == true){
        		this.movingRIGHT = true;
        		this.movingLEFT = false; 
        		keyRight = true;
        		}; 
        		
        		this.movingLEFT = false; 
        		
        		break;
        		
        case Input.KEY_DOWN: this.movingDOWN = false; break;
        case Input.KEY_D:  
        	keyRight = false;
        	if(keyLeft == true){
        		this.movingLEFT = true;
        		this.movingRIGHT = false;
        		};
        		this.movingRIGHT = false;
        		break;
        		
        case Input.KEY_E:
 		   //this.Shoting =false;
        	//dthis.ShotingCamera = false;
 		   break;
 	  
        
    }
        
}
}*/