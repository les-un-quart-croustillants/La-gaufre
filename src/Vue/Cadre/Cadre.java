package Vue.Cadre;

import java.util.ArrayList;
import java.util.Iterator;

import Vue.GameObject.GameObject;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

public class Cadre extends Pane {
	private Canvas canvas;
	public GraphicsContext gc;
	ArrayList<GameObject> gameObjects;
	
	public Cadre() {
		super();
		canvas = new Canvas();
		this.getChildren().add(canvas);
		canvas.widthProperty().bind(this.widthProperty());
        canvas.heightProperty().bind(this.heightProperty());
        gc = canvas.getGraphicsContext2D();
        gameObjects = new ArrayList<GameObject>();
	}
	
	public Cadre(int wpref,int hpref) {
		this();
		this.setPrefSize(wpref, hpref);
	}
	
	public void update() {
		Iterator<GameObject> it = gameObjects.iterator();
		while(it.hasNext()) {
			GameObject go = it.next();
			if(go.estDetruit()) {
				it.remove();
				go.onDestroy();
			}
			else {
				go.update();
			}
		}
	}
	
	public void Draw() {
		gc.clearRect(0, 0, this.getWidth(), this.getHeight());
		//gc.strokeRect(0, 0, this.getWidth(), this.getHeight());
		Iterator<GameObject> it = gameObjects.iterator();
		while(it.hasNext()) {
			GameObject go = it.next();
			go.draw(gc);
		}
	}
}
