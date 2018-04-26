package Vue;


import java.awt.Point;

import Controleur.Utilisateur.CoordonneesSouris;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class PanePrincipal extends BorderPane{

	Cadre gameView;
	Cadre rightView; //exemple
	Cadre bottomView; //exemple
		
	private class RightView extends Cadre{//exemple
		Point coord;
		
		RightView(int w,int h){
			super(w,h);
			coord = new Point(0,0);
			this.setOnMouseMoved(new CoordonneesSouris(coord));
		}
		void Draw() {
			gc.clearRect(0, 0, this.getWidth(), this.getHeight());
			gc.setStroke(Color.RED);
			gc.strokeRect(0, 0, this.getWidth(), this.getHeight());
			gc.fillText(coord.toString(), 10, 20);
		}
	}
	
	public PanePrincipal(){
		super();
		
		gameView = new VuePlateau(800,600);
		rightView = new RightView(200,50);
		bottomView = new Cadre(100,50);

		this.setCenter(gameView);
		this.setBottom(bottomView);
		this.setRight(rightView);
		
	}
	
	void Draw() {
		gameView.Draw();
		bottomView.Draw();
		rightView.Draw();
	}
	
	
}
