package Vue;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import Modele.Plateau;
import Vue.Cadre.Cadre;
import Vue.Cadre.EnteteCadre;
import Vue.Cadre.InfoCadre;
import Vue.Cadre.PlateauCadre;

public class PanePrincipal extends GridPane {

	public PlateauCadre gameView;
	public EnteteCadre enteteView;
	public Cadre infoView;
	
	public Plateau plateau;	
	public Moteur moteur;

	public PanePrincipal(){
		super();
		
		plateau = new Plateau(5,8);
		
		gameView = new PlateauCadre(800,600, this);
		enteteView = new EnteteCadre(100,50, this);
		infoView = new InfoCadre(100,50);
		
		moteur=new Moteur(this);


		//Mise en place des Pane � la bonne position
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

	//Cette fonction destin� � la mise � jour est appel�e une fois par frame
	void Update() {
		moteur.update();
		gameView.update();
		enteteView.update();
		infoView.update();
	}
	
	//Cette fonction destin� au dessin est appel�e une fois par frame
	void Draw() {
		gameView.Draw();
		enteteView.Draw();
		infoView.Draw();
	}

}
