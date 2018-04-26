package Vue;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

public class Cadre extends Pane {
	private Canvas canvas;
	public GraphicsContext gc;

	Cadre() {
		super();
		canvas = new Canvas();
		this.getChildren().add(canvas);
		canvas.widthProperty().bind(this.widthProperty());
        canvas.heightProperty().bind(this.heightProperty());
        gc = canvas.getGraphicsContext2D();
	}
	Cadre(int wpref,int hpref) {
		this();
		this.setPrefSize(wpref, hpref);
	}
	void Draw() {
		gc.clearRect(0, 0, this.getWidth(), this.getHeight());
		gc.strokeRect(0, 0, this.getWidth(), this.getHeight());
	}
}
