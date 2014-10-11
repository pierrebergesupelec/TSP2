import java.util.ArrayList;
import java.util.Collections;

public class Routage {
	
	Graphe g;
	ArrayList<Integer> route ; 
	double distance ;
	
	//Crée une route aléatoire
	public Routage(Graphe g) {
		this.g = g;
		routeInitiale();
		distance = newDistance();
	}
	
	//Route définie par une liste d'indices
	public Routage(Graphe g, ArrayList<Integer> liste, double distance){
		this.g=g;
		this.route=liste;
		this.distance=distance;
	}

	
	public void clear()
    {
    	route.clear();
    }
	
	public String toString() {
		int n = g.nombreDeNoeuds();
		String s = "";
		for (int index = 0; index < n; index++){
			s += route.get(index).intValue() + "->";
		}
		return s;
	}
	
	public ArrayList<Integer> getRoute(){
		return route;
	}
	
	public double getDistance(){
		return distance;
	}
	
	//Taille de la route
	public int tailleRoute() {
		return route.size();
	}
    
 // Renvoie une route initiale aléatoire
    public void routeInitiale() {
    	int n = g.nombreDeNoeuds();
    	ArrayList<Integer> liste = new ArrayList<Integer>();
        for (int index = 0; index < n; index++) {
          liste.add(new Integer(index));
        }
        // rÃ©organise alÃ©atoirement l'ordre de visite
        Collections.shuffle(liste);
        route = liste;
    }
    
    //Modifie la route de manière aléatoire
    public void routeAleatoire() {
    	Collections.shuffle(route);
    }
    
    //2 Fonctions pratiques qui rebouclent les indices
    public static int getNextIndex(int index, int indexMax){
    	if (index==indexMax-1) {
    		return 0;
    	} else {
    		return index+1;
    	}
    }
    
    public static int getPreviousIndex(int index, int indexMax){
    	if (index==0) {
    		return indexMax - 1;
    	} else {
    		return index-1;
    	}
    }
    
    //Echange 2 noeuds dans la route
    
    public void swap(int i, int j){
    	Collections.swap(route,i,j);
    }
    
    //Effectue une mutation et modifie la distance
    public void twoPointsMove() {
    	int n = tailleRoute();
    	 // On choisit deux positions du parcours au hasard.
        int randIndex1 = (int) (tailleRoute() * Math.random());
        int randIndex2 = (int) (tailleRoute() * Math.random());
        swap(randIndex1,randIndex2);
        //Repertorie les arêtes à rajouter et à supprimer pendant la mutation puis modifie distance
        double plus1 = g.longueurEntre(route.get(randIndex1),route.get(getPreviousIndex(randIndex1,n)));
        double plus2 = g.longueurEntre(route.get(randIndex1),route.get(getNextIndex(randIndex1,n)));
        double plus3 = g.longueurEntre(route.get(randIndex2),route.get(getPreviousIndex(randIndex2,n)));
        double plus4 = g.longueurEntre(route.get(randIndex2),route.get(getNextIndex(randIndex2,n)));
        double moins1 = g.longueurEntre(route.get(randIndex1),route.get(getPreviousIndex(randIndex2,n)));
        double moins2 = g.longueurEntre(route.get(randIndex1),route.get(getNextIndex(randIndex2,n)));
        double moins3 = g.longueurEntre(route.get(randIndex2),route.get(getPreviousIndex(randIndex1,n)));
        double moins4 = g.longueurEntre(route.get(randIndex2),route.get(getNextIndex(randIndex1,n)));
        distance += (plus1 + plus2 + plus3 + plus4) - (moins1 + moins2 + moins3 +moins4);
    }
    
    //Calcule la distance d'une route
    public double newDistance(){
    	double dist = 0.;
    	int n = tailleRoute();
    	for(int i=0; i<tailleRoute();i++){
    		//Rajoute la distance i -> getNextIndex(i)
    		dist += g.longueurEntre(route.get(i).intValue(),route.get(getNextIndex(i,n)).intValue()) ;
    	}
    	return dist;
    }
    
    
    
	
}
