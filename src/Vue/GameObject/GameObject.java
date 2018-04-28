package Vue.GameObject;

import com.sun.javafx.geom.Vec2d;

import javafx.scene.canvas.GraphicsContext;

public class GameObject {
	protected Vec2d position;
	private boolean estDetruit=false;
	OnDestroyHandler odh;
	
	public GameObject(int x,int y) {
		position = new Vec2d(x,y);
	}
	
	public Vec2d position() {
		return position;
	}
	
	public boolean estDetruit() {
		return estDetruit;
	}
	
	public void update() {
		
	}
	public void draw(GraphicsContext gc) {
		
	}
	
	public void detruire() {
		estDetruit = true;
	}
	
	public void setOnDestroyHandler(OnDestroyHandler odh) {
		this.odh = odh;
	}
	
	public void onDestroy() {
		odh.handle();
	}
}
