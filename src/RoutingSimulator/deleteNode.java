package RoutingSimulator;

public class deleteNode {

	int[][]  newCost;
	
	public int[][] delete(int[][] cost, int nodeNumber){
		
		int size = cost.length;
		
		newCost = new int[size][size];
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				if(i==nodeNumber-1||j==nodeNumber-1){
					cost[i][j]=-1;
				}
			}
		}
		newCost = cost;
		return newCost;
	}
	
}
