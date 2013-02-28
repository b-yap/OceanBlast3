/**
* enemyTimeHandler.java
* Feb 24, 2013
* 2:48:46 PM
* 
* @author B. Carla Yap
* @email bcarlayap@ymail.com
**/


package school.project.oceanblast3;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;

public class GameTimerHandler extends TimerHandler {
	 
	 private boolean mPause = false;
	  
	 public GameTimerHandler(float pTimerSeconds, boolean pAutoReset,
	   ITimerCallback pTimerCallback) {
	  super(pTimerSeconds, pAutoReset, pTimerCallback);
	 }
	  
	 public void pause() {
	  this.mPause = true;
	 }
	  
	 public void resume() {
	  this.mPause = false;
	 }
	 
	 @Override
	 public void onUpdate(float pSecondsElapsed) {
	  if(!this.mPause) {
	   super.onUpdate(pSecondsElapsed);
	  }
	 }
	}