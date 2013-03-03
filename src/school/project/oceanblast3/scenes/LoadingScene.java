/**
* LoadingScene.java
* Mar 3, 2013
* 10:39:07 AM
* 
* @author B. Carla Yap
* @email bcarlayap@ymail.com
**/


package school.project.oceanblast3.scenes;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;


import school.project.oceanblast3.ConstantsList.SceneType;

public class LoadingScene extends BaseScene {

	@Override
	public void createScene() {
		createBackground();
		this.attachChild(new Text(400, 240, resourcesManager.font, "Loading...", vboManager));

	}

	@Override
	public void onBackKeyPressed() {
		return ;
		
	}

	@Override
	public SceneType getSceneType() {
		// TODO Auto-generated method stub
		return SceneType.LOADING;
	}

	@Override
	public void disposeScene() {
		// TODO Auto-generated method stub
		
	}
	
	private void createBackground(){
	    this.setBackground(new Background(100,100,100,100));
	    setBackgroundEnabled(true);

	}

}
