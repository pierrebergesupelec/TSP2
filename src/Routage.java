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
	
	//Determine si 2 noeuds sont voisins : cette définition prend en compte le sens
	public boolean sontVoisins(int i,int j) {
		int n = tailleRoute();
		return getPreviousIndex(i, n) == j ;
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
    
    //Effectue une mutation aléatoire et modifie la distance : convient seulement aux graphes symétriques 
    public void twoPointsMove() {
    	int n = tailleRoute();
    	int randIndex1 = 0;
    	int randIndex2 = 0;
    	 // On choisit deux positions différentes du parcours au hasard et on les échange
    	while (randIndex1 == randIndex2){
    		 randIndex1 = (int) (tailleRoute() * Math.random());
    	     randIndex2 = (int) (tailleRoute() * Math.random());
    	}
       swap(randIndex1,randIndex2);
        //Repertorie les arêtes à rajouter et à supprimer pendant la mutation puis modifie distance
        if (Math.abs(randIndex1 - randIndex2) == 1) {
        	int indexMin = Math.min(randIndex1,randIndex2);
        	int indexMax = Math.max(randIndex1,randIndex2);
        	double plus1 = g.longueurEntre(route.get(indexMin),route.get(getPreviousIndex(indexMin,n)));
            double plus2 = g.longueurEntre(route.get(indexMax),route.get(getNextIndex(indexMax,n)));
            double moins1 = g.longueurEntre(route.get(indexMin),route.get(getNextIndex(indexMax,n)));
            double moins2 = g.longueurEntre(route.get(indexMax),route.get(getPreviousIndex(indexMin,n)));
            distance += (plus1 + plus2) - (moins1 + moins2);
        } else {
        	if (Math.abs(randIndex1 - randIndex2) == (n - 1)) {
        		int indexMin = Math.min(randIndex1,randIndex2);
            	int indexMax = Math.max(randIndex1,randIndex2);
            	double plus1 = g.longueurEntre(route.get(indexMin),route.get(getNextIndex(indexMin,n)));
                double plus2 = g.longueurEntre(route.get(indexMax),route.get(getPreviousIndex(indexMax,n)));
                double moins1 = g.longueurEntre(route.get(indexMin),route.get(getPreviousIndex(indexMax,n)));
                double moins2 = g.longueurEntre(route.get(indexMax),route.get(getNextIndex(indexMin,n)));
                distance += (plus1 + plus2) - (moins1 + moins2);
        	} else {
        
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
        }
    }
    
    //Effectue la mutation élémentaire aléatoire twoOptMove et modifie la distance (cf quantum_annealing_TSP)
    public void twoOptMove() {
    	int n = tailleRoute();
    	int randIndex1 = 0;
    	int randIndex2 = 0;
    	 // On choisit deux positions différentes du parcours au hasard et on les échange. Notons que le cas c2=c1' ne change pas la route et que le cas c1=c1' n'a aucun sens.
    	while (randIndex1 == randIndex2 || getPreviousIndex(randIndex1,n)==randIndex2){
    		 randIndex1 = (int) (tailleRoute() * Math.random());
    	     randIndex2 = (int) (tailleRoute() * Math.random());
    	}
    	int c2 = randIndex1; 
        //Ici, d1 correspond au c1' de l'article quantum_annealing_TSP
    	int d1 = randIndex2;
    	//On effectue la mutation en faisant apparaître les arêtes c1->d1 et c2->d2. A noter la modification de sens entre c2 et d1
    	swap(c2,d1);
    	int pre = d1;
    	int post = c2;
    	while ( !sontVoisins(pre,post) && !sontVoisins(getNextIndex(pre,n),post) ) {
    		pre = getNextIndex(pre,n);
    		post = getPreviousIndex(post,n);
    		swap(pre,post);
    	}
    	distance = newDistance();
    }
    
    //Calcule la distance d'une route
    public double newDistance(){
    	double dist = 0.;
    	int n = tailleRoute();
    	for(int i=0; i<n;i++){
    		//Rajoute la distance i -> getNextIndex(i)
    		dist += g.longueurEntre(route.get(i),route.get(getNextIndex(i,n))) ;
    	}
    	return dist;
    }
    
    
    
	
}
