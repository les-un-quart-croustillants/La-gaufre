package Vue;


import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class PanePrincipal extends BorderPane{

	Cadre gameView;
	Cadre rightView; //exemple
	Cadre bottomView; //exemple
		
	private class TestView extends Cadre{//exemple
		String s;
		TestView(int w,int h,String s){
			super(w,h);
			this.s = s;
		}
		void Draw() {
			gc.clearRect(0, 0, this.getWidth(), this.getHeight());
			gc.setStroke(Color.RED);
			gc.strokeRect(0, 0, this.getWidth(), this.getHeight());
			gc.fillText(s, 10, 20);
		}
	}
	
	public PanePrincipal(){
		super();
		
		gameView = new VuePlateau(800,600);
		rightView = new TestView(200,50,"coucou");
		bottomView = new TestView(100,50,"On en a gros!");

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
