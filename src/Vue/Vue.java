package Vue;

import Modele.Modele;

public abstract class Vue extends Cadre {
	Modele model;
	
	Vue() {
		super();
	}
	
	Vue(int width, int height) {
		super(width, height);
	}
	
	public abstract void maj();
}
