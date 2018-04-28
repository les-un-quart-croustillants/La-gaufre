package Vue;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import Modele.Plateau;
import Vue.Cadre.Cadre;
import Vue.Cadre.EnteteCadre;
import Vue.Cadre.PlateauCadre;

public class PanePrincipal extends GridPane {

	Cadre gameView;
	Cadre enteteView;
	Cadre infoView;
	
	public Plateau plateau;

	private class TestView extends Cadre { // exemple
		String s;

		TestView(int w, int h, String s) {
			super(w, h);
			this.s = s;
		}

		@Override
		public void Draw() {
			gc.clearRect(0, 0, this.getWidth(), this.getHeight());
			gc.setStroke(Color.RED);
			gc.strokeRect(0, 0, this.getWidth(), this.getHeight());
			gc.fillText(s, 10, 20);
		}
	}

	public PanePrincipal(){
		super();
		
		gameView = new PlateauCadre(800,600, new Plateau());
		enteteView = new EnteteCadre(100,50);
		infoView = new TestView(100,50,"On en a gros!");

		//Mise en place des Pane à la bonne position
		GridPane.setConstraints(enteteView,0,0);
		GridPane.setConstraints(infoView,0,1);
		GridPane.setConstraints(gameView,0,2);
		this.getChildren().addAll(enteteView,infoView,gameView);
		
		//Hauteur prise par les pane en %
		RowConstraints row1 = new RowConstraints();
	    row1.setPercentHeight(10);
	    RowConstraints row2 = new RowConstraints();
	    row2.setPercentHeight(10);
	    RowConstraints row3 = new RowConstraints();
	    row3.setPercentHeight(80);
		this.getRowConstraints().addAll(row1,row2,row3);
		
		//Largeur prise par les pane en %
		ColumnConstraints column1 = new ColumnConstraints();
	    column1.setPercentWidth(100);
		this.getColumnConstraints().addAll(column1);
		
		this.setBackground(new Background(new BackgroundFill(Donnees.COULEUR_BG, CornerRadii.EMPTY, Insets.EMPTY)));
	}

	//Cette fonction destiné à la mise à jour est appelée une fois par frame
	void Update() {
		gameView.update();
		enteteView.update();
	}
	
	//Cette fonction destiné au dessin est appelée une fois par frame
	void Draw() {
		gameView.Draw();
		enteteView.Draw();
		infoView.Draw();
	}

}
