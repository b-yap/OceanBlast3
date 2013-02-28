/**
 * ISceneCreator.java
 * Jan 11, 2013
 * 3:04:41 PM
 *
 * @author B. Carla Yap 
 * email: bcarlayap@ymail.com
 *
 */

package school.project.oceanblast3.interfaces;

import org.andengine.entity.scene.Scene;

import school.project.oceanblast3.managers.SceneManager;

public interface ISceneCreator {

	public void loadResources();
	public void createScene(SceneManager sceneManager);
	public Scene getScene();

}

