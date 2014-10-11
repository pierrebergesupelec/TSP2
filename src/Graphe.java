
public class Graphe {

	// Un graphe est d�fini par une matrice carr�e repr�sentant les distances entre chaque noeud
	double[][] longueursAretes;
	
	//Constructeur
	public Graphe(double[][] tab) {
		longueursAretes = tab;
	}
	
	//Fournit la longueur entre 2 noeuds
	public double longueurEntre(int index1, int index2) {
		return longueursAretes[index1][index2];
	}
	
	//Renvoie le nombre de noeuds du graphe
	public int nombreDeNoeuds() {
		return longueursAretes.length;
	}
	
	

	
	
	
	
	

}
