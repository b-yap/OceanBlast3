/**
* ResourceManager.java
* Feb 24, 2013
* 5:49:46 PM
* 
* @author B. Carla Yap
* @email bcarlayap@ymail.com
**/


package school.project.oceanblast3.managers;

import java.io.IOException;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.BaseGameActivity;

public class ResourcesManager {
	 
	 /* MAIN ACTIVITY VARIABLES */
	 private static final ResourcesManager INSTANCE = new ResourcesManager();
	 public Engine engine;
	 public BaseGameActivity activity;
	 public Camera mCamera;
	 public VertexBufferObjectManager vboManager;
	
	
	 /* SPLASH SCENE RESOURCES*/

		public ITextureRegion splashRegion;
		private BitmapTextureAtlas splashAtlas; 
	
	/* MENUSCENE RESOURCES */
		public ITextureRegion menu_bgroundRegion;
		public ITextureRegion play_region;
		private BitmapTextureAtlas menuAtlas;
		 
	 /* LOGIC AREA */
	 public void loadMenuResources(){
		loadMenuGraphics();	
		 loadMenuAudio();
	 }
	 
	 public void loadGameResources(){
		 
		 loadGameGraphics();
		 loadGameAudio();
	 }
	 
	 
	 private void loadMenuGraphics(){
		 BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu/");
			//background
			menuAtlas = new BitmapTextureAtlas(activity.getTextureManager(),1500,1500);
				menu_bgroundRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuAtlas, activity, "background.png",0,0);
				play_region =  BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuAtlas, activity, "background.png",0,481);
			menuAtlas.load();		

		 
	 }
	 private void loadMenuAudio(){
		 
	 }
	 
	 private void loadGameGraphics(){
		 
		 
	 }
	 
	 private void loadGameAudio(){
		 
	 }
	 
	 public void loadSplashScene(){
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
	        splashAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 640, 300, TextureOptions.DEFAULT);
	        splashRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(splashAtlas, activity, "splash.png", 0, 0);
	        splashAtlas.load();

	 }
	 
	 public void unloadSplashScene(){
		 splashAtlas.unload();
		 splashRegion =null;
	 }
	 
	 public static void prepareManager(Engine engine, BaseGameActivity activity, Camera camera,
			 VertexBufferObjectManager vbom){
		getInstance().engine = engine;
		getInstance().activity = activity;
		getInstance().mCamera = camera;
		getInstance().vboManager =vbom;
	 }
	 
	 public static ResourcesManager getInstance(){
		 return INSTANCE;
	 }

}
