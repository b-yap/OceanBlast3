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

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.background.AutoParallaxBackground;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.util.debug.Debug;

import android.graphics.Color;
import android.graphics.Typeface;

import school.project.oceanblast3.objects.Enemy;
import school.project.oceanblast3.scenes.AnalogControls;

public class ResourcesManager {
	 
	 /******************** MAINACTIVITY VARIABLES ********************/
	
	private static final ResourcesManager INSTANCE = new ResourcesManager();
		public Engine engine;
		public BaseGameActivity activity;
		public Camera camera;
		public VertexBufferObjectManager vboManager;
	
	
	 /******************** SPLASH SCENE RESOURCES ********************/

		public ITextureRegion splashRegion;
		private BitmapTextureAtlas splashAtlas; 
	

	/******************** MENUSCENE RESOURCES ************************/
		 
		//background
		private BitmapTextureAtlas 	menu_bgroundAtlas;
		public TextureRegion 		menu_bgroundRegion;
		
		//menu buttons
		private BitmapTextureAtlas 	menu_btnAtlas;
		public TextureRegion 		menu_btnPlayRegion;
		public TextureRegion 		menu_btnPlayPushedRegion;
		public TextureRegion		menu_btnSoundRegion;
		public TextureRegion 		menu_btnOnRegion;
		public TextureRegion 		menu_btnOffRegion;
		
		//music
		public Music menu_music;
		
	/******************** GAMESCENE RESOURCES ************************/
		
		public AnalogControls game_analogControl;
		
		//parallax background
		private BitmapTextureAtlas game_autoParallaxAtlas;
		public ITextureRegion game_parallaxLayerBackRegion;
		public ITextureRegion game_parallaxLayerFrontRegion;

		//objects
		private BitmapTextureAtlas game_objectAtlas;
		public TiledTextureRegion game_playerRegion;
		public TextureRegion game_pelletRegion;
		private BuildableBitmapTextureAtlas game_enemyAtlas;
		public TiledTextureRegion game_enemyGoldfishRegion;
				
		//buttons
		private BuildableBitmapTextureAtlas game_btnAtlas;
		public ITextureRegion game_btnShootRegion;
		public ITextureRegion game_btnShootPushedRegion;
		
		private BitmapTextureAtlas game_pauseAtlas;
		public  TextureRegion game_btnPauseRegion;
		
							
	/******************** LOADINGSCENE RESOURCES **********************/
			
		public Font font;
		
	/******************** PAUSECENE RESOURCES **********************/
		
		private BitmapTextureAtlas pause_pausedAtlas;
		public TextureRegion pause_btnPausedRegion;
		
		
	/******************** LOGIC AREA **********************************/
		
	/* FONT AREA */ 
			
	private void loadFontResources(){
		FontFactory.setAssetBasePath("font/");
	    final ITexture mainFontTexture = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
	    font = FontFactory.createStrokeFromAsset(activity.getFontManager(), mainFontTexture, activity.getAssets(), "BASKVILL.TTF", 50, true, Color.WHITE, 2, Color.BLACK);
	    font.load();

	}
	
	/* PAUSESCENE AREA */ 
	
	public void loadPauseResources(){
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		pause_pausedAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 200, 50, TextureOptions.DEFAULT);
		pause_btnPausedRegion = BitmapTextureAtlasTextureRegionFactory		
			    .createFromAsset(pause_pausedAtlas, activity, "paused.png",0, 0);
		pause_pausedAtlas.load();
	
	}
	
	/* MENUSCENE AREA */ 
	
	public void loadMenuResources(){
		loadMenuGraphics();	
		loadMenuAudio();
		loadFontResources();
	 }
	  
	 private void loadMenuGraphics(){
			BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");	
			
			//background
			menu_bgroundAtlas 				= new BitmapTextureAtlas(activity.getTextureManager(),1000,1000);
				menu_bgroundRegion 			= BitmapTextureAtlasTextureRegionFactory.createFromAsset(menu_bgroundAtlas, activity, "background.png",0,0);
			menu_bgroundAtlas.load();		
			
			//menu buttons
			menu_btnAtlas					= new BitmapTextureAtlas(activity.getTextureManager(),400,435);
				menu_btnPlayRegion			= BitmapTextureAtlasTextureRegionFactory.createFromAsset(menu_btnAtlas, activity,"playBig.png",0,0);
				menu_btnPlayPushedRegion 	= BitmapTextureAtlasTextureRegionFactory.createFromAsset(menu_btnAtlas, activity,"playBigPushed.png",0,151);
				menu_btnOnRegion 			= BitmapTextureAtlasTextureRegionFactory.createFromAsset(menu_btnAtlas, activity,"on.png",0,303);
				menu_btnOffRegion 			= BitmapTextureAtlasTextureRegionFactory.createFromAsset(menu_btnAtlas, activity,"off.png",0,353);
				menu_btnSoundRegion			= BitmapTextureAtlasTextureRegionFactory.createFromAsset(menu_btnAtlas, activity,"soundToggles.png",0,405);
			menu_btnAtlas.load();
		
		
	 }
	 
	 private void loadMenuAudio(){
		//add music
			MusicFactory.setAssetBasePath("musix/");
			try	{
					this.menu_music 		= MusicFactory.createMusicFromAsset(activity.getMusicManager(),activity, "mainMenuMusic.mid");
					this.menu_music.setVolume(0.5f);
					this.menu_music.setLooping(true);
				}
			catch (IOException e)
				{
					e.printStackTrace();
				}
		
	 }
	 
	 public void unloadMenuScene(){
	     menu_bgroundAtlas.unload();
	     menu_btnAtlas.unload();
	 }
	     
	 public void loadMenuScene(){
		 menu_bgroundAtlas.load();
	     menu_btnAtlas.load();
	 }
	 
	
	 /* GAMESCENE AREA */
	 
	 public void loadGameResources(){
		 game_analogControl = new AnalogControls();
		 loadGameGraphics();
		 loadGameAudio();
	 }
	 
	 private void loadGameGraphics(){
			
		 BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
			  
		 	//parallax background
			game_autoParallaxAtlas 				= new BitmapTextureAtlas(activity.getTextureManager(), 1024, 1024);
				game_parallaxLayerFrontRegion 	= BitmapTextureAtlasTextureRegionFactory.createFromAsset(game_autoParallaxAtlas,
						activity, "parallax_background_layer_front.png", 0, 0);
				game_parallaxLayerBackRegion 	= BitmapTextureAtlasTextureRegionFactory.createFromAsset(game_autoParallaxAtlas,
						activity, "background.png", 0, 188);
			game_autoParallaxAtlas.load();	
		
			
			//player: submarine	
			game_objectAtlas 		= new BitmapTextureAtlas(activity.getTextureManager(),200,200);
				game_playerRegion 	= BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.game_objectAtlas,
						activity,"submarine.png",0,0,1,1);
				game_pelletRegion 	=BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.game_objectAtlas,
						activity,"pellet.png",0,118);
				game_objectAtlas.load();
			
			//analog controls
			game_analogControl.loadAnalogControlResources();	
				
			//buttons
			game_pauseAtlas = new BitmapTextureAtlas(activity.getTextureManager(),32,32);
				game_btnPauseRegion =BitmapTextureAtlasTextureRegionFactory.createFromAsset(game_pauseAtlas,
						activity, "pause.png", 0, 0);
			game_pauseAtlas.load();
			
			game_btnAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 512, 546);
			game_btnShootRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(game_btnAtlas, activity, "face_box_tiled.png");
			game_btnShootPushedRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(game_btnAtlas, activity, "face_circle_tiled.png");
					
			try {
				game_btnAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 0));
				game_btnAtlas.load();
			} catch (TextureAtlasBuilderException e) {
				Debug.e(e);
			}
					
			
			//animated sprite: enemy
			game_enemyAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 512, 256, TextureOptions.NEAREST);
				game_enemyGoldfishRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(game_enemyAtlas, 
									 activity, "goldfish_tiled.png",2,1);
					
			try {
					game_enemyAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 1));
					game_enemyAtlas.load();
				} catch (TextureAtlasBuilderException e) {
					Debug.e(e);
				}
	 
	 }
	 
	 private void loadGameAudio(){
		 
	 }
	 
	 public void unloadGameScene()
	 {
	     // TODO (Since we did not create any textures for game scene yet)
	 }
	 
	 
	 /* SPLASH SCENE AREA */
	 
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
		getInstance().camera = camera;
		getInstance().vboManager =vbom;
	 }
	 
	 public static ResourcesManager getInstance(){
		 return INSTANCE;
	 }

}
