/**
* GameScene_base.java
* Mar 3, 2013
* 7:16:19 AM
* 
* @author B. Carla Yap
* @email bcarlayap@ymail.com
**/


package school.project.oceanblast3.scenes;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.scene.background.AutoParallaxBackground;
import org.andengine.entity.scene.background.ParallaxBackground.ParallaxEntity;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.ButtonSprite.OnClickListener;
import org.andengine.entity.text.Text;
import org.andengine.opengl.font.Font;

import android.opengl.GLES20;
import android.util.Log;

import school.project.oceanblast3.ConstantsList;
import school.project.oceanblast3.ConstantsList.SceneType;
import school.project.oceanblast3.managers.SceneManager;
import school.project.oceanblast3.objects.Enemy;

public class GameScene extends BaseScene {

	private int mScore=0;
	
	
	private Sprite userPlayer;
	private ButtonSprite pauseButton;
	private Enemy goldfish;
	private Text scoreText;
	private Font mFont;
	private PhysicsHandler physicsHandler;
	private BaseScene mScene;
			
	
	@Override
	public void createScene() {
		this.mScene = this;	
		
		createBackground();
		createGameObjects();
		createButtons();
		resourcesManager.game_analogControl.setPlayerPhysicsHandler(userPlayer, physicsHandler);
		resourcesManager.game_analogControl.setAnalogControl();
		
			this.registerUpdateHandler(new IUpdateHandler() {
				public void reset() { }

				public void onUpdate(final float pSecondsElapsed) {
					if(userPlayer.collidesWith(goldfish)) {
						Log.d("gameOver!", " ");
					}else{}
					if(!camera.isRectangularShapeVisible(userPlayer)) {
						//nothing
					}
				}
			});


	}

	@Override
	public void onBackKeyPressed() {
		SceneManager.getInstance().loadMenuScene();
		
	}

	@Override
	public SceneType getSceneType() {
		// TODO Auto-generated method stub
		return ConstantsList.SceneType.GAME;
	}

	@Override
	public void disposeScene() {
		
	}
	
	
	private void createBackground(){
	
		//background
		final AutoParallaxBackground autoParallaxBackground = new AutoParallaxBackground(0, 0, 0, 5);
		autoParallaxBackground.attachParallaxEntity(new ParallaxEntity(0.0f, new Sprite(0, ConstantsList.CAMERA_HEIGHT - 
				 resourcesManager.game_parallaxLayerBackRegion.getHeight(),resourcesManager.game_parallaxLayerBackRegion,
				 vboManager)));
		 
		 autoParallaxBackground.attachParallaxEntity(new ParallaxEntity(-10.0f, new Sprite(0, ConstantsList.CAMERA_HEIGHT - 
				 resourcesManager.game_parallaxLayerFrontRegion.getHeight(), resourcesManager.game_parallaxLayerFrontRegion,
				 vboManager)));
		 this.setBackground(autoParallaxBackground);
			 
	}
	

	private void createGameObjects(){	
		
		final int centerX= (int) (ConstantsList.CAMERA_WIDTH - resourcesManager.game_playerRegion.getHeight())/2;	
		final int centerY=(int) (ConstantsList.CAMERA_HEIGHT - resourcesManager.game_playerRegion.getHeight())/2;	
	
		//player
		 final Sprite userPlayer = new Sprite(centerX, centerY, resourcesManager.game_playerRegion,vboManager);
		 this.physicsHandler = new PhysicsHandler(userPlayer);
		 userPlayer.registerUpdateHandler(physicsHandler);
		 this.attachChild(userPlayer);
		 this.userPlayer = userPlayer;
		
		//enemy
	    goldfish = new Enemy(650,50,resourcesManager.game_enemyGoldfishRegion,vboManager,-100);
		goldfish.animation(200);
		final PhysicsHandler physicsHand = new PhysicsHandler(goldfish);
		goldfish.registerUpdateHandler(physicsHand);
		this.attachChild(goldfish);
					
		this.scoreText = new Text(5, 5, resourcesManager.font, "Score: 0", "Score: XXXX".length(), vboManager);
		this.scoreText.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		this.scoreText.setAlpha(0.5f);
		this.attachChild(this.scoreText);
			
	}
	
	private void createButtons(){
		 //listener for the pause button
		 OnClickListener pauseListener = new OnClickListener(){
			
				public void onClick(ButtonSprite pButtonSprite, float pTouchAreaLocalX,
						float pTouchAreaLocalY) {
					// call the setCurrentScene for pause button
					SceneManager.getInstance().setCurrentScene(SceneType.PAUSE);
				}
			};	
			
		 //pause button
		 pauseButton = new ButtonSprite(740,10,resourcesManager.game_btnPauseRegion,vboManager,pauseListener);
		 this.registerTouchArea(pauseButton);
		 this.setTouchAreaBindingOnActionDownEnabled(true);
		 this.attachChild(pauseButton);
		 
		pelletShooting();	
	}
	
	private void pelletShooting(){
		// shoot pellet listener
	
		
		OnClickListener shootListener = new OnClickListener() {
			
			public void onClick(ButtonSprite pButtonSprite, float pTouchAreaLocalX,
					float pTouchAreaLocalY) {
				//the pellet
				final Sprite pellet = new Sprite(userPlayer.getX()+100, userPlayer.getY()+50,resourcesManager.game_pelletRegion,vboManager);
			
				PhysicsHandler pelletHandler = new PhysicsHandler(pellet);
				pelletHandler.setVelocityX(100);
				pellet.registerUpdateHandler(pelletHandler);
				mScene.attachChild(pellet);
				
				mScene.registerUpdateHandler(new IUpdateHandler() {
					public void reset() { 
											}

					public void onUpdate(final float pSecondsElapsed) {							
						if(pellet.collidesWith(goldfish)){
							mScore++;
							
							scoreText.setText("Score: " + mScore);
							activity.runOnUpdateThread(new Runnable(){

								@Override
								public void run() {
									mScene.detachChild(pellet);
									pellet.setVisible(false);
									mScene.detachChild(goldfish);
									goldfish = null;
									Log.d("hit!", " ");
									scoreText.setIgnoreUpdate(true);
																				
									
								}
								
							});	 
							
							//pellet.dispose();
//								//pellet.detachSelf();
//								//pellet = null;
//								mScene.detachChild(pellet);
//								pellet.setIgnoreUpdate(true);
//								pellet.clearEntityModifiers();
//								pellet.clearUpdateHandlers();				
						}
						if(pellet.getX()>700){
							activity.runOnUpdateThread(new Runnable(){

								@Override
								public void run() {
									mScene.detachChild(pellet);
									
								}
								
							});	 
						}
					}
									
				});					
			}
		};
		
		//the shoot button
		final Sprite fire = new ButtonSprite(700, 420, resourcesManager.game_btnShootRegion, 
				resourcesManager.game_btnShootPushedRegion, vboManager,shootListener);
		mScene.registerTouchArea(fire);
		mScene.attachChild(fire);
		mScene.setTouchAreaBindingOnActionDownEnabled(true);
	}
	

}
