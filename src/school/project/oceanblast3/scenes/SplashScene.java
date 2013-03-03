/**
* SplashScene_base.java
* Mar 3, 2013
* 7:38:04 AM
* 
* @author B. Carla Yap
* @email bcarlayap@ymail.com
**/


package school.project.oceanblast3.scenes;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;

import school.project.oceanblast3.ConstantsList;
import school.project.oceanblast3.ConstantsList.SceneType;
import school.project.oceanblast3.managers.ResourcesManager;

public class SplashScene extends BaseScene{

	private Sprite splash;
	
	
	@Override
	public void createScene() {
		 splash = new Sprite(0, 0, resourcesManager.splashRegion, resourcesManager.vboManager)
		    {
		    @Override
		    protected void preDraw(GLState pGLState, Camera pCamera)
		    {
		                super.preDraw(pGLState, pCamera);
		                pGLState.enableDither();
		            }
		    };
		   
		    splash.setPosition((ConstantsList.CAMERA_WIDTH - splash.getWidth()) * 0.5f, (ConstantsList.CAMERA_HEIGHT - splash.getHeight()) * 0.5f);
		    this.attachChild(splash);
		
		
	}

	@Override
	public void onBackKeyPressed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SceneType getSceneType() {
		return ConstantsList.SceneType.SPLASH;
	}

	@Override
	public void disposeScene() {
		splash.detachSelf();
		splash.dispose();
		this.detachSelf();
		this.dispose();
		
		
	}

}
