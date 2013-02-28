/**
* LevelChooserScene.java
* Feb 24, 2013
* 2:10:45 PM
* 
* @author B. Carla Yap
* @email bcarlayap@ymail.com
**/


package school.project.oceanblast3.scenes;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.ui.activity.BaseGameActivity;

import school.project.oceanblast3.interfaces.ISceneCreator;
import school.project.oceanblast3.managers.SceneManager;

public class LevelChooserScene implements ISceneCreator {

	//for resources
	private BitmapTextureAtlas bgroundTextureAtlas;
    private ITextureRegion bgroundTextureRegion;
   
    private Sprite bground;
    private Scene mScene=new Scene();
    
	private BaseGameActivity mActivity;
	
	
	public LevelChooserScene(BaseGameActivity activity){
		mActivity = activity;
	}
	
	
	@Override
	public void loadResources() {
		// TODO Auto-generated method stub
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        bgroundTextureAtlas = new BitmapTextureAtlas(mActivity.getTextureManager(), 640, 300, TextureOptions.DEFAULT);
        bgroundTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bgroundTextureAtlas, mActivity, "splash.png", 0, 0);
        bgroundTextureAtlas.load();

	}
	
	@Override
	public void createScene(SceneManager sceneManager) {
		// TODO Auto-generated method stub
		bground = new Sprite(0, 0, bgroundTextureRegion, mActivity.getVertexBufferObjectManager());
		mScene.attachChild(bground);
	}

	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return this.mScene;
	}

 
}
