

// Cette classe définit le problème du recuit. Il se charge d'effectuer les mutations élémentaires, de calculer l'énergie et de diminuer T...

public class Recuit {
    
	static double temperature = 10000;
    static double refroidissement = 0.0001;
    
	
    public static void resetRecuit()
    {
    	temperature = 10000;
    }
    // Probabilité d'accepter une solution pire que l'actuelle
    public static double probaAcceptation(double energieCourante, double energieNouvelle, double temperature) 
    {
        // Si la nouvelle solution est meilleure, alors on accepte !
        if (energieNouvelle < energieCourante) {
            return 1.0;
        }
        // si elle est pire, on définit une proba pour accepter éventuellement cette solution...
        return Math.exp((energieCourante - energieNouvelle) / temperature);
    }
    
    // Remonte la température, remet tout en ordre !

    // Cette méthode est la plus importante : elle implémente l'algorithme du recuit simulé
    public static double solution(Graphe g)
    {
    	// On définit une route aléatoire en premier lieu
    	Routage solutionCourante = new Routage(g);
    	// On affiche la route et son �nergie
    	System.out.println("Tour initial: " + solutionCourante.toString());
    	System.out.println("distance solution initiale: " + solutionCourante.getDistance());
    	// et on dit que pour l'instant, c'est la meilleure route !
    	Routage meilleureRoute = new Routage(g,solutionCourante.getRoute(),solutionCourante.getDistance());
    	// On crée une nouvelle route conçue à partir de l'ancienne
    	Routage nvelleSolution = new Routage(g,solutionCourante.getRoute(),solutionCourante.getDistance());
    	// On répète tant que la température est assez haute(j'ai mis 9950 pour faire des tests, � modifier biensur)
    	while (temperature > 9950) {
    	// Sur cette nouvelle route, on effectue une mutation élémentaire (2optMove)
    		//GROS PROBLEME ICI : j'ajoute 2 prints pour obtenir � chaque it�ration les distances interm�diaires.
    		//Les distances atteignent des valeurs gigantesques. Je suppose qu'elles s'additionnent... Code � rectifier !!!
    	System.out.println("Oldsol: " + nvelleSolution.getDistance());
    	nvelleSolution.twoPointsMove();
    	System.out.println("Newsol: " + nvelleSolution.getDistance());

    	// On récupère l'énergie (distance de parcours) des deux routes
    	double energieCourante = solutionCourante.getDistance();
    	double energieVoisine = nvelleSolution.getDistance();

    	// On décide si on accepte cette nouvelle route comme vu précédemment
    	if (probaAcceptation(energieCourante, energieVoisine, temperature) >= Math.random()) {
    		solutionCourante = new Routage(g,nvelleSolution.getRoute(),nvelleSolution.getDistance());
    	}
  
    	// et si cette solution est meilleure que toutes les précédentes, alors on l'enregistre comme étant la meilleure pour l'instant
    	if (solutionCourante.getDistance() < meilleureRoute.getDistance()) {
    	meilleureRoute = new Routage(g,solutionCourante.getRoute(),solutionCourante.getDistance());
    	}
    	
    	// puis on refroidit le système
    	temperature *= 1-refroidissement;
    	}
    	// Lorsque l'énergie cinétique n'est plus suffisante, on s'arrête et on affiche la solution trouvée
    	System.out.println("distance solution trouvée: " + meilleureRoute.getDistance());
    	System.out.println("Tour: " + meilleureRoute);
    	return meilleureRoute.getDistance();
    }
}
     