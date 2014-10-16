import java.util.ArrayList;

public class Main {
	
	@SuppressWarnings("null")
	public static void main(String[] args){
		

		double[] tab1 = {0,2,3,1,1,6};
		double[] tab2 = {2,0,5.7,21,1,1};
		double[] tab3 = {3,5.7,0,1,10,1};
		double[] tab4 = {1,21,1,0,4.4,1.6};
		double[] tab5 = {1,1,10,4.4,0,7};
		double[] tab6 = {6,1,1,1.6,7,0};
		double[] ttab1 = {0,1,1,1,1,1};
		double[] ttab2 = {1,0,0.5,1,1,1};
		double[] ttab3 = {1,0.5,0,1,1,1};
		double[] ttab4 = {1,1,1,0,1,1};
		double[] ttab5 = {1,1,1,1,0,1};
		double[] ttab6 = {1,1,1,1,1,0};
		double[][] tab = {ttab1,ttab2,ttab3,ttab4,ttab5,ttab6};
		Graphe g = new Graphe(tab);
		/*Routage r = new Routage(g);
		System.out.println(r.toString());
		System.out.println(r.getDistance());
		r.twoPointsMove();
		System.out.println(r.toString());
		System.out.println(r.getDistance());*/
		Recuit.solution(g);
	
	}
}

