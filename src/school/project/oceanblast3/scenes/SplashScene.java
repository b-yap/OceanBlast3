/**
* SplashScene.java
* Feb 27, 2013
* 9:01:45 PM
* 
* @author B. Carla Yap
* @email bcarlayap@ymail.com
**/


package school.project.oceanblast3.scenes;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;

import school.project.oceanblast3.ConstantsList;
import school.project.oceanblast3.interfaces.ISceneCreator;
import school.project.oceanblast3.managers.ResourcesManager;
import school.project.oceanblast3.managers.SceneManager;

public class SplashScene implements ISceneCreator{

	private Scene mScene = new Scene();
	private Sprite splash;
	private ResourcesManager resourcesManager = ResourcesManager.getInstance();
	
	@Override
	public void loadResources() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createScene(SceneManager sceneManager) {
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
		    mScene.attachChild(splash);
		
		
	}

	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return mScene;
	}

}
