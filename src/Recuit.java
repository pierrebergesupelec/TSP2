

// Cette classe dÃ©finit le problÃ¨me du recuit. Il se charge d'effectuer les mutations Ã©lÃ©mentaires, de calculer l'Ã©nergie et de diminuer T...

public class Recuit {
    
	static double temperature = 10000;
    static double refroidissement = 0.0001;
    
	
    public static void resetRecuit()
    {
    	temperature = 10000;
    }
    // ProbabilitÃ© d'accepter une solution pire que l'actuelle
    public static double probaAcceptation(double energieCourante, double energieNouvelle, double temperature) 
    {
        // Si la nouvelle solution est meilleure, alors on accepte !
        if (energieNouvelle < energieCourante) {
            return 1.0;
        }
        // si elle est pire, on dÃ©finit une proba pour accepter Ã©ventuellement cette solution...
        return Math.exp((energieCourante - energieNouvelle) / temperature);
    }
    
    // Remonte la tempÃ©rature, remet tout en ordre !

    // Cette mÃ©thode est la plus importante : elle implÃ©mente l'algorithme du recuit simulÃ©
    public static double solution(Graphe g)
    {
    	// On dÃ©finit une route alÃ©atoire en premier lieu
    	Routage solutionCourante = new Routage(g);
    	// On affiche la route et son énergie
    	System.out.println("Tour initial: " + solutionCourante.toString());
    	System.out.println("distance solution initiale: " + solutionCourante.getDistance());
    	// et on dit que pour l'instant, c'est la meilleure route !
    	Routage meilleureRoute = new Routage(g,solutionCourante.getRoute(),solutionCourante.getDistance());
    	// On crÃ©e une nouvelle route conÃ§ue Ã  partir de l'ancienne
    	Routage nvelleSolution = new Routage(g,solutionCourante.getRoute(),solutionCourante.getDistance());
    	// On rÃ©pÃ¨te tant que la tempÃ©rature est assez haute(j'ai mis 9950 pour faire des tests, à modifier biensur)
    	while (temperature > 1) {
    	// Sur cette nouvelle route, on effectue une mutation Ã©lÃ©mentaire 
    		
    	nvelleSolution.twoOptMove();

    	// On rÃ©cupÃ¨re l'Ã©nergie (distance de parcours) des deux routes
    	double energieCourante = solutionCourante.getDistance();
    	double energieVoisine = nvelleSolution.getDistance();

    	// On dÃ©cide si on accepte cette nouvelle route comme vu prÃ©cÃ©demment
    	if (probaAcceptation(energieCourante, energieVoisine, temperature) >= Math.random()) {
    		solutionCourante = new Routage(g,nvelleSolution.getRoute(),nvelleSolution.getDistance());
    	}
  
    	// et si cette solution est meilleure que toutes les prÃ©cÃ©dentes, alors on l'enregistre comme Ã©tant la meilleure pour l'instant
    	if (solutionCourante.getDistance() < meilleureRoute.getDistance()) {
    	meilleureRoute = new Routage(g,solutionCourante.getRoute(),solutionCourante.getDistance());
    	}
    	
    	// puis on refroidit le systÃ¨me
    	temperature *= 1-refroidissement;
    	}
    	// Lorsque l'Ã©nergie cinÃ©tique n'est plus suffisante, on s'arrÃªte et on affiche la solution trouvÃ©e
    	System.out.println("distance solution trouvÃ©e: " + meilleureRoute.getDistance());
    	return meilleureRoute.getDistance();
    }
}
