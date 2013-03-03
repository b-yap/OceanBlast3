/**
 * SceneManager.java
 * Jan 11, 2013
 * 2:04:14 PM
 *
 * @author B. Carla Yap 
 * email: bcarlayap@ymail.com
 *
 */

package school.project.oceanblast3.managers;

import java.util.ArrayList;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.ui.IGameInterface.OnCreateSceneCallback;
import org.andengine.ui.activity.BaseGameActivity;

import android.content.res.Resources;
import android.util.Log;

import school.project.oceanblast3.scenes.BaseScene;
import school.project.oceanblast3.managers.ResourcesManager;
import school.project.oceanblast3.ConstantsList;
import school.project.oceanblast3.ConstantsList.SceneType;
import school.project.oceanblast3.interfaces.ILoaderObserver;
import school.project.oceanblast3.interfaces.IObserver;
import school.project.oceanblast3.scenes.GameScene;
import school.project.oceanblast3.scenes.LoadingScene;
import school.project.oceanblast3.scenes.MenuScene;
import school.project.oceanblast3.scenes.PauseScene;
import school.project.oceanblast3.scenes.ScoreObserver;
import school.project.oceanblast3.scenes.SplashScene;

public class SceneManager implements ILoaderObserver {

	private static final SceneManager INSTANCE = new SceneManager();	
	private Engine engine =ResourcesManager.getInstance().engine;


	private BaseGameActivity mainActivity=ResourcesManager.getInstance().activity;

	private Camera mCamera= ResourcesManager.getInstance().camera;
	private ArrayList<IObserver> observers= new ArrayList<IObserver>();
	private IObserver scoreObserver; 
	

	//scenes
	private BaseScene splashScene;
	private BaseScene menuScene;
	private BaseScene gameScene;
	private BaseScene pauseScene;
	private BaseScene loadingScene;
	private BaseScene currentScene;
	
	

//	public SceneManager() {
//		menuScreen = new MenuScene();
//		gameScreen = new GameScene();
//		pauseScreen = new PauseScene();	
//		this.scoreObserver = new ScoreObserver(mainActivity);
//		registerObserver(this.scoreObserver);		
//	}

	public BaseScene getCurrentScene() {
		return currentScene;
	}	

	//Method allows you to set the currently active scene
	public void setCurrentScene(ConstantsList.SceneType scene) {
	
		switch (scene)
		{
		case SPLASH:{
			engine.setScene(splashScene);
			 Log.d("set Splash", " ");
			 currentScene = splashScene;
			break;
			}
		case MENU:
			{
			engine.setScene(menuScene);			
			Log.d("set Menu", " ");
			currentScene = menuScene;
			break;
			}
		case GAME:{
			notifyObservers();
			gameScene.setIgnoreUpdate(false);
			gameScene.clearChildScene();
			gameScene.setChildScene(ResourcesManager.getInstance().game_analogControl.getAnalogControl());		
			engine.setScene(gameScene);	
			Log.d("set game", " ");
			currentScene = gameScene; 
			break;
			}
		case SCORE:{
			Log.d("Scoring", " ");
			break;
		}
		case PAUSE:{	
			gameScene.setIgnoreUpdate(true);
			gameScene.setChildScene(pauseScene,false, true,true);
			Log.d("paused", "done");
			break;
		}
		case LOADING:{
			engine.setScene(loadingScene);
			Log.d("Scoring", " ");
			break;
		}
	}
	}
	
	public void registerObserver(IObserver observer) {
        observers.add(observer);
        Log.d(observer.getObserverName()+ " successfully registered"," ");	
	}

	public void removeObserver(IObserver observer) {
			observers.remove(observer);
		
	}

	public void notifyObservers() {
		for (IObserver ob : observers) {
             Log.d("Notifying Observers on change in Score", " ");
             ob.update("updated");
		}	
	}
	
	public static SceneManager getInstance(){
		return INSTANCE;
	}
	
	
	
	public void createSplashScene(OnCreateSceneCallback pOnCreateSceneCallback){
		splashScene = new SplashScene();
		currentScene =splashScene;
		pOnCreateSceneCallback.onCreateSceneFinished(splashScene);		
	}
	
	private void disposeSplashScene(){
		ResourcesManager.getInstance().unloadSplashScene();
		splashScene.disposeScene();
		splashScene = null;
	}
	
	public void createMenuScene(){
		ResourcesManager.getInstance().loadMenuResources();
		
		menuScene = new MenuScene();
		loadingScene = new LoadingScene();
		setCurrentScene(ConstantsList.SceneType.MENU);
		disposeSplashScene();
		
	}
	
	
	/********************* WHILE LOADING *************************************/
	
	public void loadGameScene()
	{	
	    setCurrentScene(SceneType.LOADING);
	    ResourcesManager.getInstance().unloadMenuScene();
	    engine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() 
	    {
	        public void onTimePassed(final TimerHandler pTimerHandler) 
	        {
	            engine.unregisterUpdateHandler(pTimerHandler);
	            ResourcesManager.getInstance().loadGameResources();
	            ResourcesManager.getInstance().loadPauseResources();
	            gameScene = new GameScene();
	            pauseScene = new PauseScene();
	            setCurrentScene(SceneType.GAME);
	        }
	    }));
	}
	
	public void loadMenuScene()
	{
	    setCurrentScene(SceneType.LOADING);
	    gameScene.disposeScene();
	    ResourcesManager.getInstance().unloadGameScene();
	    engine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() 
	    {
	        public void onTimePassed(final TimerHandler pTimerHandler) 
	        {
	            engine.unregisterUpdateHandler(pTimerHandler);
	            ResourcesManager.getInstance().loadMenuScene();
	            setCurrentScene(SceneType.MENU);
	        }
	    }));
	}
	

}



