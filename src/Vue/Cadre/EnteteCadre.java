package Vue.Cadre;

import Vue.Donnees;
import Vue.ImageBouton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;


public class EnteteCadre extends Cadre {
	public Label label;
	
	StackPane sp;
	HBox hbox;
	Button b1,b2,b3,b4;
	
	private ImageBouton creerBoutonUndo() {
		ImageBouton b = new ImageBouton(Donnees.LOGO_FLECHE_GAUCHE);
		b.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Undo");
			}
		});
		return b;
	}
	
	private ImageBouton creerBoutonRedo() {
		ImageBouton b = new ImageBouton(Donnees.LOGO_FLECHE_DROITE);
		b.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Redo");
			}
		});
		return b;
	}
	
	private ImageBouton creerBoutonSauvegarder() {
		ImageBouton b = new ImageBouton(Donnees.LOGO_SAUVEGARDE);
		b.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Sauvegarder");
			}
		});
		return b;
	}
	
	private ImageBouton creerBoutonRetour() {
		ImageBouton b = new ImageBouton(Donnees.LOGO_FERMER);
		b.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Retour");
			}
		});
		return b;
	}
	
	private HBox creerBarreBoutons() {
		HBox hbox;
		hbox = new HBox();
		hbox.prefHeightProperty().bind(this.heightProperty());
		hbox.prefWidthProperty().bind(this.widthProperty());
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(20);
		
		double echelle = 0.6;
		
		sp = new StackPane();
		sp.prefHeightProperty().bind(this.heightProperty());
		b1 = creerBoutonUndo();
		b1.prefHeightProperty().bind(hbox.heightProperty().multiply(echelle));
		b1.prefWidthProperty().bind(hbox.heightProperty().multiply(echelle));
		b2 = creerBoutonRedo();
		b2.prefHeightProperty().bind(hbox.heightProperty().multiply(echelle));
		b2.prefWidthProperty().bind(hbox.heightProperty().multiply(echelle));
		b3 = creerBoutonSauvegarder();
		b3.prefHeightProperty().bind(hbox.heightProperty().multiply(echelle));
		b3.prefWidthProperty().bind(hbox.heightProperty().multiply(echelle));
		b4 = creerBoutonRetour();
		b4.prefHeightProperty().bind(hbox.heightProperty().multiply(echelle));
		b4.prefWidthProperty().bind(hbox.heightProperty().multiply(echelle));
		sp.getChildren().addAll(b4);
		sp.setAlignment(Pos.CENTER_RIGHT);
		sp.prefWidthProperty().bind(this.widthProperty().subtract(this.heightProperty().multiply(3)));
		hbox.getChildren().addAll(b1,b2,b3,sp);
		return hbox;
	}
	
	private void Initialisation() {	
		label = new Label("Joueur 1");
		label.setFont(Donnees.FONT_TEXT);
		label.setTextFill(Donnees.COULEUR_TEXT);
		
		
		this.getChildren().add(label);
		this.getChildren().add(creerBarreBoutons());
		
	}
	
	public EnteteCadre(){
		super();
		Initialisation();
	}
	
	@Override
	public void update() {
		label.setScaleX(this.getHeight()*0.02);
		label.setScaleY(this.getHeight()*0.02);
		label.setLayoutY(this.getHeight()/2-label.boundsInLocalProperty().getValue().getHeight()/2);
		label.setLayoutX(this.getWidth()/2-label.boundsInLocalProperty().getValue().getWidth()/2);
	}
	
	@Override
	public void Draw() {
		gc.clearRect(0, 0, this.getWidth(), this.getHeight());
	}
	
	public EnteteCadre(int wpref,int hpref){
		super(wpref,hpref);
		Initialisation();
	}
}
