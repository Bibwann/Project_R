package fr.sae.game;

class Sort {
	protected String nom;
    protected int puissance;
    protected int cout;
	protected int cible;
	
    // Constructeur
    public Sort(String nom, int puissance, int portee, int cout) {
        this.nom = nom;
        this.puissance = puissance;
        this.cout = cout;
    }

    // Méthode pour lancer le sort
    
    public void lancer(int mana, int cible) {	
    	
        System.out.println("Le sort " + nom + " est lancé !");
    }
    
    public void lancer(int cible) {
    	int mana=0;
    	
        System.out.println("Le sort " + nom + " est lancé !");
    }
}