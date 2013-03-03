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

	
	@Override
	public void createScene() {
		engine.registerUpdateHandler(new FPSLogger());
		final float centerX = (ConstantsList.CAMERA_WIDTH - resourcesManager.pause_btnPausedRegion.getWidth()) / 2;
		final float centerY = (ConstantsList.CAMERA_HEIGHT - resourcesManager.pause_btnPausedRegion.getHeight()) / 2;
		
		final Sprite pausedSprite = createPauseSprite(centerX, centerY,resourcesManager.pause_btnPausedRegion);
		this.registerTouchArea(pausedSprite);
		this.setTouchAreaBindingOnActionDownEnabled(true);
		this.attachChild(pausedSprite);
		/* Makes the paused Game look through. */
		this.setBackgroundEnabled(false);
		
		
	}

	@Override
	public void onBackKeyPressed() {
		// TODO Auto-generated method stub
		
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
	
	 private Sprite createPauseSprite(float centerX, float centerY, TextureRegion button) {
	    	
	    	final Sprite pauseButton = new Sprite(centerX, centerY,	button,
					activity.getVertexBufferObjectManager()) {

					@Override
					public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
							final float pTouchAreaLocalX, final float pTouchAreaLocalY) {				
							// Toggle pause or not				
							switch(pSceneTouchEvent.getAction()) {
							case TouchEvent.ACTION_DOWN:
								Log.d("action down on pause", " ");
								// play the game again. call the SceneManager
								SceneManager.getInstance().setCurrentScene(SceneType.GAME);
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
