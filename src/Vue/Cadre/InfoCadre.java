package Vue.Cadre;

import Vue.Donnees;
import javafx.scene.control.Label;

public class InfoCadre extends Cadre{
	
	public Label label;
	
	private void Initialisation() {	
		label = new Label("Cliquez sur une case");
		label.setFont(Donnees.FONT_TEXT);
		label.setTextFill(Donnees.COULEUR_TEXT);
		
		this.getChildren().add(label);		
	}
	
	public InfoCadre(){
		super();
		Initialisation();
	}
	public InfoCadre(int wpref,int hpref){
		super(wpref,hpref);
		Initialisation();
	}
	
	@Override
	public void update() {
		label.setScaleX(this.getHeight()*0.01);
		label.setScaleY(this.getHeight()*0.01);
		label.setLayoutY(this.getHeight()/2-label.boundsInLocalProperty().getValue().getHeight()/2);
		label.setLayoutX(this.getWidth()/2-label.boundsInLocalProperty().getValue().getWidth()/2);
	}
}
