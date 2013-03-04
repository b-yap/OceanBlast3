/**
* MenuScene_base.java
* Mar 3, 2013
* 7:20:58 AM
* 
* @author B. Carla Yap
* @email bcarlayap@ymail.com
**/


package school.project.oceanblast3.scenes;

import org.andengine.audio.music.Music;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.ButtonSprite.OnClickListener;

import android.widget.Toast;

import school.project.oceanblast3.ConstantsList;
import school.project.oceanblast3.managers.ResourcesManager;
import school.project.oceanblast3.managers.SceneManager;
import school.project.oceanblast3.ConstantsList.SceneType;

public class MenuScene extends BaseScene {

	/******************* VARIABLES *******************/
	//sprites
	private Sprite oBackground;
	private Sprite playButton;
	private Sprite quitButton;
	private Sprite helpButton;
	private ButtonSprite soundButton;
	private Sprite onButton;
	private Sprite offButton;

	
	@Override
	public void createScene() {
		createBackground();
		createMenuButtons();
	}

	@Override
	public void onBackKeyPressed() {
		System.exit(0);
		
	}

	@Override
	public SceneType getSceneType() {
		// TODO Auto-generated method stub
		return ConstantsList.SceneType.MENU;
	}

	@Override
	public void disposeScene() {
		// TODO Auto-generated method stub
		
	}
	
	
	private void createBackground(){
		 oBackground = new Sprite(0,0,resourcesManager.menu_bgroundRegion,resourcesManager.vboManager);
		this.attachChild(oBackground); 
	
	}
	
	private void createMenuButtons(){
		
		/* LISTENERS */
		
		OnClickListener musicListener = new OnClickListener() {    	
        	Music music = ResourcesManager.getInstance().menu_music;
        			
			//toggle Music
			public void onClick(ButtonSprite pButtonSprite, float pTouchAreaLocalX,
					float pTouchAreaLocalY) {
				if(music.isPlaying()){	
					onButton.setVisible(false);
					offButton.setVisible(true);
					music.pause();
				}
				else{	
					music.play();
					onButton.setVisible(true);
					offButton.setVisible(false);
				}	
			}
		}; 
		
		 OnClickListener playListener = new OnClickListener(){
			
			 public void onClick(ButtonSprite pButtonSprite, float pTouchAreaLocalX,
					float pTouchAreaLocalY) {
				SceneManager.getInstance().loadGameScene(); 
			 }
		};	
		
		
		//menu buttons
		//playButton = new ButtonSprite((ConstantsList.CAMERA_WIDTH - resourcesManager.menu_btnPlayRegion.getWidth())/2,(ConstantsList.CAMERA_HEIGHT - resourcesManager.menu_btnPlayRegion.getHeight())/2,resourcesManager.menu_btnPlayRegion, resourcesManager.menu_btnPlayPushedRegion,
		//resourcesManager.vboManager, playListener);		
		
		playButton = new ButtonSprite(300,250,resourcesManager.menu_btnPlayRegion, resourcesManager.menu_btnPlayPushedRegion,
				resourcesManager.vboManager, playListener);		
		this.registerTouchArea(playButton);
		this.setTouchAreaBindingOnActionDownEnabled(true);
		this.attachChild(playButton);
		
		quitButton = new ButtonSprite(530,275,resourcesManager.menu_btnQuitRegion,resourcesManager.menu_btnQuitPushedRegion,
				resourcesManager.vboManager, playListener);		
		this.registerTouchArea(quitButton);
		this.setTouchAreaBindingOnActionDownEnabled(true);
		this.attachChild(quitButton);
		
		helpButton = new ButtonSprite(150,290,resourcesManager.menu_btnHelpRegion,resourcesManager.menu_btnHelpPushedRegion,
				resourcesManager.vboManager, playListener);		
		this.registerTouchArea(helpButton);
		this.setTouchAreaBindingOnActionDownEnabled(true);
		this.attachChild(helpButton);
		
		//music
		soundButton = new ButtonSprite(600,380,resourcesManager.menu_btnSoundRegion,resourcesManager.vboManager, musicListener);		
		this.registerTouchArea(soundButton);
		this.attachChild(soundButton);
		this.setTouchAreaBindingOnActionDownEnabled(true);
		
		onButton = new ButtonSprite(600,400,resourcesManager.menu_btnOnRegion,resourcesManager.vboManager);	
		offButton= new ButtonSprite (600,400,resourcesManager.menu_btnOffRegion, resourcesManager.vboManager);
		this.attachChild(onButton);
		this.attachChild(offButton);
		onButton.setVisible(true);
		offButton.setVisible(false);
	

	}

}
