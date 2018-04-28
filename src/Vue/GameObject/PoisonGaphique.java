package Vue.GameObject;

import Vue.Donnees;
import javafx.scene.canvas.GraphicsContext;

public class PoisonGaphique extends GameObject{

	PlateauGraphique pg;
	long delay = 300;
	boolean visible=true;
	float poisonEchelle = 0.5f;
	
	private long time;

	
	public PoisonGaphique(PlateauGraphique pg) {
		this.pg = pg;
		time = System.currentTimeMillis();
	}

	@Override
	public void draw(GraphicsContext gc) {
		if(visible) {
			gc.drawImage(Donnees.IMG_POISON, position.x + pg.tailleCase()*poisonEchelle/2,  position.y + pg.tailleCase()*poisonEchelle/2, pg.tailleCase()*poisonEchelle, pg.tailleCase()*poisonEchelle);
		}
	}
	
	@Override
	public void update() {
		position.x = pg.position().x;
		position.y = pg.position().y;
		if(time+delay<System.currentTimeMillis()) {
			visible=!visible;
			time=System.currentTimeMillis();
		}
	}
	
}
