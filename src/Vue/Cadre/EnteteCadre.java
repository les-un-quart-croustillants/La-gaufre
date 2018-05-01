package Vue.Cadre;

import Vue.ConfirmationPopup;
import Vue.Donnees;

import java.io.IOException;

import Vue.InterfaceGraphique;
import Vue.InterfaceGraphique.Appli_state;
import Vue.PaneMenu;
import Vue.PanePrincipal;
import Vue.GameObject.AnimationGraphique;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;


public class EnteteCadre extends Cadre {
	PanePrincipal panePrincipal;
	public Label label;
	
	StackPane sp;
	HBox hbox;
	Button b1,b2,b3,b4,b5;
	
	private Button creerBoutonUndo() {
		Button b = new Button();
		b.getStyleClass().add("undobtn");
		b.getStyleClass().add("gamebtn");
		b.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				panePrincipal.moteur.undo();
			}
		});
		return b;
	}
	
	private Button creerBoutonRedo() {
		Button b = new Button();
		b.getStyleClass().add("redobtn");
		b.getStyleClass().add("gamebtn");
		b.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				panePrincipal.moteur.redo();
			}
		});
		return b;
	}
	
	private Button creerBoutonSauvegarder() {
		Button b = new Button();
		b.getStyleClass().add("savebtn");
		b.getStyleClass().add("gamebtn");
		b.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				try {
					panePrincipal.plateau.saveGame();
					AnimationGraphique an = new AnimationGraphique(1000) {
						public String tmp = panePrincipal.infoView.label.getText();;
						@Override
						public void update() {
							super.update();
							panePrincipal.infoView.label.setText("Sauvegarde");
						}
						
						@Override
						public void onDestroy() {
							panePrincipal.infoView.label.setText(tmp);
						}
					};
					panePrincipal.gameView.gameObjects.add(an);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		return b;
	}
	
	private Button creerBoutonCharger() {
		Button b = new Button();
		b.getStyleClass().add("loadbtn");
		b.getStyleClass().add("gamebtn");
		b.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				try {
					panePrincipal.plateau.loadGame();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		return b;
	}
	
	private Button creerBoutonRetour() {
		Button b = new Button();
		b.getStyleClass().add("closebtn");
		b.getStyleClass().add("gamebtn");
		b.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				ConfirmationPopup c = new ConfirmationPopup("Retourner au menu?","Oui","Non",null,null, InterfaceGraphique.m.get_css());
				panePrincipal.getChildren().add(c);
				EventHandler<ActionEvent> non = new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						panePrincipal.getChildren().remove(c);
					} 
				};
				EventHandler<ActionEvent> oui = new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						InterfaceGraphique.etat=Appli_state.MENU;
						InterfaceGraphique.m = new PaneMenu(panePrincipal.getWidth(),panePrincipal.getHeight(), InterfaceGraphique.m.get_css());
						InterfaceGraphique.primaryStage.setScene(new Scene(InterfaceGraphique.m));
					}
				};
				c.setOuiAction(oui);
				c.setNonAction(non);
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
		b5 = creerBoutonCharger();
		b5.prefHeightProperty().bind(hbox.heightProperty().multiply(echelle));
		b5.prefWidthProperty().bind(hbox.heightProperty().multiply(echelle));
		b4 = creerBoutonRetour();
		b4.prefHeightProperty().bind(hbox.heightProperty().multiply(echelle));
		b4.prefWidthProperty().bind(hbox.heightProperty().multiply(echelle));
		sp.getChildren().addAll(b4);
		sp.setAlignment(Pos.CENTER_RIGHT);
		sp.prefWidthProperty().bind(this.widthProperty().subtract(this.heightProperty().multiply(3)));
		hbox.getChildren().addAll(b1,b2,b3,b5,sp);
		return hbox;
	}
	
	private void Initialisation(PanePrincipal pp, String css) {	
		this.panePrincipal = pp;
		label = new Label();
		label.setFont(Donnees.FONT_TEXT);
		label.setTextFill(Donnees.COULEUR_TEXT);
		this.getStylesheets().add(css);
		
		
		this.getChildren().add(label);
		this.getChildren().add(creerBarreBoutons());
		
	}
	
	public EnteteCadre(PanePrincipal pp, String css){
		super();
		Initialisation(pp, css);
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
	
	public EnteteCadre(int wpref,int hpref,PanePrincipal pp, String css){
		super(wpref,hpref);
		Initialisation(pp, css);
	}
}
