package Modele.Plateau;

import java.awt.Point;

public class Plateau {
    int [][] tab;
    int compteurCoups,
        hauteur,
        largeur;

    Plateau() {
        this(10,10, 0);
    }

    Plateau(int hauteur, int largeur, int compteurCoups) {
        this.compteurCoups = compteurCoups;
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.tab = new int[largeur][hauteur];
    }
    Plateau(int [][] tab){
        this.tab = tab;
    }

    /*
     * estMangeable : Si une case est mangeable
     *  @params coord : coordonnées de la case
     *  @return : booléen vrai si la case est mangeable, faux sinon
     */
    public boolean estMangeable(Point coord) { // TODO : à implémenter
        return true;
    }

    /* FIXME : possible redondance avec estMangeable
     * estMangee : Si une case a déjà été mangée ou non
     * @param coord : coordonnée de la case
     * @return : vrai si la case a été mangée faux sinon
     */
    public boolean estMangee(Point coord) { // TODO : à implémenter
        return true;
    }

    /*
     * manger : mange les cases en fonction de la position donnée
     * @param coord : coordonnées de la case
     */
    public void manger(Point coord) {
        if(estMangeable(coord)) {
            // mange toutes les cases en dessous et à droite de la position donnée
            for(int i = coord.getX(); i < largeur; i++) {
                for (int j = coord.getY(); j < hauteur; j++) {
                    if (!estMangee(new Point(i,j))) {
                        tab[i][j] = compteurCoups;
                    }
                }
            }
            compteurCoups++;
        }
    }
}
