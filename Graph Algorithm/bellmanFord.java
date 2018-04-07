//Cuong Nguyen
import java.util.*;

public class longpath{
	public static final int oo = Integer.MAX_VALUE ;
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		
		int numCase = input.nextInt();
		Edge[] edgeList;
		Edge[] negEdgeList;
		int v,e;
		for(int k=0;k< numCase;k++){
			v = input.nextInt();
			e = input.nextInt();
			
			edgeList = new Edge[e];
			negEdgeList = new Edge[e];
			for(int i=0;i<e;i++){
				int a = input.nextInt(),
					b = input.nextInt(),
					c = input.nextInt();
				edgeList[i] = new Edge(a,b,c);
				negEdgeList[i] = new Edge(a,b,(c*-1));
			}
			// System.out.println("edgeList " +Arrays.toString(edgeList));
			Graph map = new Graph(v,e, edgeList);
			Graph negMap = new Graph(v,e, negEdgeList);
			int[] dist = bellmanFord(map,0);
			int[] negDist = bellmanFord(negMap,0);
			// System.out.println(Arrays.toString(dist));
			System.out.println(dist[v-1]+" "+(negDist[v-1]*-1));
		}
	}
	
	//return distance list
	static int[] bellmanFord(Graph map, int src){
		int dist[] = new int[map.numVertext];
		Arrays.fill(dist, oo );
		dist[src] = 0;
		for(int i =0;i< map.numVertext-1;i++){
			for(int j=0;j < map.numEdge;j++){
				int u = map.edgeList[j].src;
				int v = map.edgeList[j].dest;
				int weight = map.edgeList[j].weight;
				
				if(dist[u] != oo && dist[u]+weight <dist[v])
					dist[v] = dist[u]+weight;
			}
		}		
		
		
/* 		//check negative weight cycle
			for(int j=0;j < map.numEdge;j++){
				int u = map.edgeList[j].src;
				int v = map.edgeList[j].dest;
				int weight = map.edgeList[j].weight;
				
				if(dist[u] != oo && dist[u]+weight <dist[v])
					System.out.println("Neg Cycle");
			}
		} */
		return dist;
	}
}

class Graph{
	int numVertext;
	int numEdge;
	
	Edge[] edgeList;
	Graph(int v, int e, Edge[] list){
		numVertext = v;
		numEdge = e;
		
		edgeList = new Edge[v];
		
		//get infomation of edge before continue
		edgeList = list;
	}
}
class Edge{
	int src, dest,weight;
	
	//constructor
	Edge(){
		src = dest = weight = 0;
	}	
	
	Edge(int s, int d, int w){
		src = s;
		dest = d;
		weight = w;
	}
	 
	public String toString(){
		String temp = Integer.toString(src);
		temp.concat("-");
		temp.concat(Integer.toString(dest));
		return temp;
	}
}