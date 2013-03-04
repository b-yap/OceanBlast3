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
import org.andengine.entity.text.Text;
import org.andengine.entity.util.FPSLogger;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.TextureRegion;

import android.opengl.GLES20;
import android.util.Log;

import school.project.oceanblast3.ConstantsList;
import school.project.oceanblast3.ConstantsList.SceneType;
import school.project.oceanblast3.managers.SceneManager;

public class GameOverScene extends BaseScene {

	private int buttonChoice=1;
	private Text endGameText;
	
	@Override
	public void createScene() {
		engine.registerUpdateHandler(new FPSLogger());
		
		final float centerX = (ConstantsList.CAMERA_WIDTH - resourcesManager.pause_btnHolderRegion.getWidth()) / 2;
		final float centerY = (ConstantsList.CAMERA_HEIGHT - resourcesManager.pause_btnHolderRegion.getHeight()) / 2;
		
		
		final Sprite messageBox = createPauseSprite(centerX, centerY,resourcesManager.pause_btnHolderRegion,0);
		this.attachChild(messageBox);
		  
		endGameText = new Text((messageBox.getWidth() - resourcesManager.pause_btnResumeRegion.getWidth())/2,
				(messageBox.getWidth()-resourcesManager.pause_btnResumeRegion.getHeight())/2, 
				resourcesManager.font, "GAME OVER!!", "GAME OVER".length(), vboManager);
		
		this.endGameText.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		this.endGameText.setAlpha(0.5f);
		this.attachChild(this.endGameText);
		
		final Sprite menuButton = createPauseSprite((messageBox.getWidth() - resourcesManager.pause_btnResumeRegion.getWidth())/2,
				(messageBox.getWidth()-resourcesManager.pause_btnResumeRegion.getHeight())/2+50,resourcesManager.pause_btnMenuRegion, 2);
		this.registerTouchArea(menuButton);
		this.setTouchAreaBindingOnActionDownEnabled(true);
		messageBox.attachChild(menuButton);
			
		this.setBackgroundEnabled(false);
		
		
	}

	@Override
	public void onBackKeyPressed() {
		SceneManager.getInstance().loadMenuScene();
		
	}

	@Override
	public SceneType getSceneType() {
		// TODO Auto-generated method stub
		return SceneType.GAMEOVER;
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
									SceneManager.getInstance().loadMenuScene();
								
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
