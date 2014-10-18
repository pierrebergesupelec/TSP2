
public class Main {
	
	public static void main(String[] args){
		
		// Solution tab = 8
		double[] tab0 = {0,2,7,3,2,1,1,4};
		double[] tab1 = {5,0,5,1,2,8,10,2};
		double[] tab2 = {3,1,0,3,3,4,5,6};
		double[] tab3 = {12,2,2,0,4,9,1,8};
		double[] tab4 = {1,14,2,4,0,7,6,5};
		double[] tab5 = {6,2,1,3,7,0,3,2};
		double[] tab6 = {5,2,4,3,7,5,0,1};
		double[] tab7 = {6,2,8,9,1,10,13,0};
		double[] ttab1 = {0,7,1,2,1,2};
		double[] ttab2 = {7,0,0.8,1,1,3};
		double[] ttab3 = {1,0.8,0,3.7,1,1};
		double[] ttab4 = {2,1,3.7,0,1,4};
		double[] ttab5 = {1,1,1,1,0,1};
		double[] ttab6 = {2,3,1,4,1,0};
		double[][] tab = {tab0,tab1,tab2,tab3,tab4,tab5,tab6,tab7};
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


