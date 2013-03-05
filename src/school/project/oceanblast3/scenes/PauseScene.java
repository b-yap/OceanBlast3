/**
* PauseScene_base.java
* Mar 3, 2013
* 12:40:57 PM
* 
* @author B. Carla Yap
* @email bcarlayap@ymail.com
**/


package school.project.oceanblast3.scenes;

import org.andengine.entity.scene.CameraScene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.util.FPSLogger;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.TextureRegion;

import android.util.Log;

import school.project.oceanblast3.ConstantsList;
import school.project.oceanblast3.ConstantsList.SceneType;
import school.project.oceanblast3.managers.SceneManager;

public class PauseScene extends BaseScene {

	private int buttonChoice;
	@Override
	public void createScene() {
		engine.registerUpdateHandler(new FPSLogger());
		
		final float centerX = (ConstantsList.CAMERA_WIDTH - resourcesManager.pause_btnHolderRegion.getWidth()) / 2;
		final float centerY = (ConstantsList.CAMERA_HEIGHT - resourcesManager.pause_btnHolderRegion.getHeight()) / 2;
		
		
		final Sprite messageBox = createPauseSprite(centerX, centerY,resourcesManager.pause_btnHolderRegion,0);
		this.attachChild(messageBox);
		
		final Sprite resumeButton = createPauseSprite((messageBox.getWidth() - resourcesManager.pause_btnResumeRegion.getWidth())/2,
				(messageBox.getWidth()-resourcesManager.pause_btnResumeRegion.getHeight())/2-70,resourcesManager.pause_btnResumeRegion, 1);
		this.registerTouchArea(resumeButton);
		this.setTouchAreaBindingOnActionDownEnabled(true);
		messageBox.attachChild(resumeButton);
		
		final Sprite menuButton = createPauseSprite((messageBox.getWidth() - resourcesManager.pause_btnResumeRegion.getWidth())/2,
				(messageBox.getWidth()-resourcesManager.pause_btnResumeRegion.getHeight())/2-10,resourcesManager.pause_btnMenuRegion, 2);
		this.registerTouchArea(menuButton);
		this.setTouchAreaBindingOnActionDownEnabled(true);
		messageBox.attachChild(menuButton);
		/* Makes the paused Game look through. */
		this.setBackgroundEnabled(false);
		
		
	}

	@Override
	public void onBackKeyPressed() {
		SceneManager.getInstance().loadMenuScene();
		
	}

	@Override
	public SceneType getSceneType() {
		// TODO Auto-generated method stub
		return SceneType.PAUSE;
	}

	@Override
	public void disposeScene() {
		// TODO Auto-generated method stub
		
	}
	
	 private Sprite createPauseSprite(float centerX, float centerY, TextureRegion button, int choice) {
	    	
		 buttonChoice =choice;
		 
	    	final Sprite pauseButton = new Sprite(centerX, centerY,	button,
					activity.getVertexBufferObjectManager()) {
	    
		    	
					@Override
					public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
							final float pTouchAreaLocalX, final float pTouchAreaLocalY) {				
							// Toggle pause or not				
							switch(pSceneTouchEvent.getAction()) {
							case TouchEvent.ACTION_DOWN:
								Log.d("action down on pause", " ");
								
								if(buttonChoice==1)
								{	
									System.err.println("HELLO");
									SceneManager.getInstance().setCurrentScene(SceneType.GAME);
								
								}else if(buttonChoice ==2){
									System.err.println("BUTTON2");
									SceneManager.getInstance().loadMenuScene();
								}
								break;
							case TouchEvent.ACTION_MOVE:
								break;
							case TouchEvent.ACTION_UP:
								break;
							}
					return true;
					}
			};
			
		return pauseButton;
	    }

	
}
