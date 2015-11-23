package RoutingSimulator;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

public class Dijkstra {

    public String[] interfaceR;//store each interface
    public String[] shortPath;//store the shortest path
	
	public int[] dijkstra(int[][] weight, int start){//use cost matrix and start point find the shortest rout
		
		int l = weight.length;//The number of router
		int[] Path = new int[l];//store the cost of each road
		int[] visit = new int[l];//store whether this point is or not visited
		interfaceR = new String[l];
		shortPath = new String[l];
		
		for(int i=0;i<l;i++){
			shortPath[i]=new String(start+"--->"+i);
		}
		
		Path[start] = 0;
		visit[start] = 1;
		
		for(int i = 1;i<l;i++){
			
			int k=-1;
			int dismin=99999;
			for(int x=0;x<l;x++){
				if(visit[x]==0&&(0 < weight[start][x]&& weight[start][x]< dismin)){
					dismin=weight[start][x];
					k=x;
				}
			}
			Path[k]=dismin;
			visit[l]=1;
		
		//calculate the distance between start to unvisited point depend on k.
        for(int y=0;y<l;y++){
        	
        	if(visit[y]==0&&weight[start][y]>0){//for those weight bigger than 0
        		if(weight[start][k]+weight[k][y]<weight[start][y]&&weight[start][k]+weight[k][y]>weight[start][k]){
        			weight[start][y]=weight[start][k]+weight[k][y];
        			shortPath[y]=shortPath[k]+"--->"+y;
        		}
        	}
        	
        	if(visit[y]==0&&weight[start][y]<0&&weight[start][k]+weight[k][y]>weight[start][k]){
        		weight[start][y]=weight[start][k]+weight[k][y];
        		shortPath[y]=shortPath[k]+"--->"+y;
        	}
        	
        }//for
			

		}
		
		for(int i=0;i<l;i++){
			if(i==start){
			interfaceR[i]=String.valueOf(i);
			}
			else{
				interfaceR[i]=shortPath[i].split("--->")[1];
					
			}
		}
		return Path;
	}


    public Map shortPath(int[][] weight, int start, int destination){//use cost matrix and start point find the shortest rout
		
		int l = weight.length;//The number of router
		int l1 = l;
		boolean f = false;//judge if there is a node have been deleted
		int[] Path = new int[l];//store the cost of each road
		int[] visit = new int[l];//store whether this point is or not visited
		Map result = new HashMap();//store the return result
		interfaceR = new String[l];
		shortPath = new String[l];
		
		for(int i=0;i<l;i++){
			shortPath[i]=new String((start+1)+"--->"+(i+1));
		}
		
		//if there is a node deleted, so the l should minus 1
		for(int i=0;i<l;i++){			
			 if(weight[i][i]==-1){
				 f=true;
				if(start==i||destination==i){
					
				    return null;
				   
				}
			}
		}
		if(f){
			l=l-1;
		}
		
		Path[start] = 0;
		visit[start] = 1;
		
		for(int i = 1;i<l;i++){
			
			int k=-1;
			int dismin=99999;
			for(int x=0;x<l1;x++){
				if(visit[x]==0&&(0 < weight[start][x]&& weight[start][x]< dismin)){
					dismin=weight[start][x];
					k=x;
				}
			}
			Path[k]=dismin;
			visit[k]=1;
		
		//calculate the distance between start to unvisited point depend on k.
        for(int y=0;y<l1;y++){
        	
        	if(visit[y]==0&&weight[start][y]>0){//for those weight bigger than 0
        		if(weight[start][k]+weight[k][y]<weight[start][y]&&weight[start][k]+weight[k][y]>weight[start][k]){
        			weight[start][y]=weight[start][k]+weight[k][y];
        			shortPath[y]=shortPath[k]+"--->"+(y+1);
        		}
        	}
        	
        	if(visit[y]==0&&weight[start][y]<0&&weight[start][k]+weight[k][y]>weight[start][k]){
        		weight[start][y]=weight[start][k]+weight[k][y];
        		shortPath[y]=shortPath[k]+"--->"+(y+1);
        	}
        	
        }//for
			

		}
		

		result.put(1, shortPath[destination]);
		result.put(2, String.valueOf(weight[start][destination]));
		
		return result;
	}

	
	
	public String[] getInterface(int[][] weight,int start){
		
		int[][] cost = weight;
		int l = cost.length;//The number of router
		int l1 = l;
		int[] Path = new int[l];//store the cost of each road
		int[] visit = new int[l];//store whether this point is or not visited
		interfaceR = new String[l];
		shortPath = new String[l];
		boolean f = false;//judge if there is a node have been deleted
		//if there is a node deleted, so the l should minus 1
		for(int i=0;i<l;i++){			
			 if(cost[i][i]==-1){
				 f=true;
				if(start==i){
					JOptionPane.showMessageDialog(null,"The router has been deleted, please change a router", "System Information", JOptionPane.ERROR_MESSAGE);
				    
				   
				}
			}
		}
		if(f){
			l=l-1;
		}
		
		for(int i=0;i<l1;i++){
			shortPath[i]=new String((start+1)+"--->"+(i+1));
		}
		
		Path[start] = 0;
		visit[start] = 1;
		
		for(int i = 1;i<l;i++){
			
			int k=-1;
			int dismin=99999;
			for(int x=0;x<l1;x++){
				if(visit[x]==0&&(0 < cost[start][x]&& cost[start][x]< dismin)){
					dismin=cost[start][x];
					k=x;
				}
			}
			
			Path[k]=dismin;
			visit[k]=1;
			
		//calculate the distance between start to unvisited point depend on k.
        for(int y=0;y<l1;y++){
        	
        	if(visit[y]==0&&cost[start][y]>0){//for those weight bigger than 0
        		if(cost[start][k]+cost[k][y]<cost[start][y]&&cost[start][k]+cost[k][y]>cost[start][k]){//for those connected with start
        			cost[start][y]=cost[start][k]+cost[k][y];
        			shortPath[y]=shortPath[k]+"--->"+(y+1);
        		}
        		
        
        	}
        	
        	if(visit[y]==0&&cost[start][y]<0&&cost[start][k]+cost[k][y]>cost[start][k]){//for those not connected with start
        		cost[start][y]=cost[start][k]+cost[k][y];
        		shortPath[y]=shortPath[k]+"--->"+(y+1);
        	}
        	
        }//for
			

		}
		
		for(int i=0;i<l1;i++){
			if(i==start){
			interfaceR[i]="-";
			}
			else{
				if(cost[i][i]!=-1)
				interfaceR[i]=shortPath[i].split("--->")[1];
				else{
					interfaceR[i]="deleted";
				}
					
			}
		}
		
	
		return interfaceR;
	}

}
