package Modele;

import java.util.List;

import Vue.Vue;

public abstract class Modele {
	List<Vue> observers;

	public void notifier() {
		for(Vue o : observers) {
			o.maj();
		}
	}

	public void ajouter_observateur(Vue o) {
		observers.add(o);
	}
}