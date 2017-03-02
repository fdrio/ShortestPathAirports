
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JFileChooser;


public class ShortestPath
{
	private static Vertex[] vertexArray;
	private static ArrayList<Vertex> tracePath = new ArrayList<>();
	private static ArrayList<Edge> edgesList = new ArrayList<>();
	static Scanner read, userInput;
	static JFileChooser choose;
	public static void main(String[] args) throws FileNotFoundException
	{
		userInput = new Scanner(System.in);


		createVertexArray();
		fillVertexArray();
		createEdgeList();
		System.out.println("");
		System.out.println("Enter the starting vertex name: ");

		String starting = userInput.next();
		System.out.println("");
		System.out.println("Enter the destination vertex name: ");
		String ending = userInput.next();
		ArrayList <Vertex> vertexList = new ArrayList<Vertex>(Arrays.asList(vertexArray));
		Vertex startingVertex = vertexList.get(Vertex.nameToIndex(starting, vertexArray));
		Vertex endingVertex = vertexList.get(Vertex.nameToIndex(ending, vertexArray));
		shortestPath(vertexList, edgesList, startingVertex, endingVertex);



	}
	public static void showPath(Vertex source, Vertex destination){

		Vertex lastVertex = vertexArray[destination.getIndex()];
		tracePath.add(lastVertex);
		while(lastVertex.getPreviousVertex() != source){// if it is the same then stop.
			lastVertex = vertexArray[lastVertex.getPreviousVertex().getIndex()];
			tracePath.add(lastVertex);
		}
		tracePath.add(vertexArray[source.getIndex()]);
		System.out.println("The shortest path from "+ source.getName()+" to "+ destination.getName());
		for(int i = tracePath.size()-1; i >= 0; i--){
			System.out.println(tracePath.get(i).getName());
		}
	}
	public static void createVertexArray()throws FileNotFoundException{
		JFileChooser choose = new JFileChooser();

		if(choose.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
			read = new Scanner(choose.getSelectedFile());
			if(read.hasNextInt()){
				vertexArray = new Vertex[read.nextInt()];

			}
			else throw new FileNotFoundException("There needs to be a size for the vertex list. ");
		}

	}

	public static void fillVertexArray(){
		System.out.println("Vertices:");
		for(int index = 0; index < vertexArray.length; index++){
			vertexArray[index] = new Vertex(read.next());
			vertexArray[index].setIndex(index);

		}
		for(int index = 0; index< vertexArray.length; index++){
			System.out.println(vertexArray[index].getName());
		}
		System.out.println("---------");
		System.out.println("Edges:");

	}
	public static void createEdgeList(){

		while(read.hasNext()){
			String starting = read.next();
			String ending = read.next();
			String weight = read.next();
			Vertex.nameToIndex(starting,vertexArray);
			System.out.println("---------");
			System.out.println(starting);
			System.out.println(ending);
			System.out.println(weight);

			edgesList.add(new Edge(vertexArray[Vertex.nameToIndex(starting, vertexArray)],vertexArray[Vertex.nameToIndex(ending, vertexArray)], Double.parseDouble(weight)));
		}

	}

	public static void shortestPath(ArrayList<Vertex> vertexList, ArrayList<Edge> edgeList, Vertex source, Vertex target)
	{	Vertex starting = null;
	Vertex tailTarget = null;
	source.setDistanceFromSource(0);
	int length = vertexList.size();
	for (int index = 0; index<length; index++){
		vertexList.get(index).setIndex(index);

	}
	System.out.println("-----");
	for (int numberOfVertexes = 0; numberOfVertexes < length-1; numberOfVertexes++)
	{


		for (Edge edge : edgeList)
		{
			//Not relaxed; therefore skipped.
			if(edge.getStartingVertex().getDistanceFromSource() == Double.POSITIVE_INFINITY) continue; // Not connected
			starting = edge.getStartingVertex();
			tailTarget = edge.getEndingVertex();
			//System.out.println("Distance from source: "+ starting.getDistanceFromSource());
			double newDistance = starting.getDistanceFromSource() + edge.getWeight();
			if(newDistance < tailTarget.getDistanceFromSource()){
				tailTarget.setDistanceFromSource(newDistance);//
				tailTarget.setPreviousVertex(starting);

			}
		}

	}

	if(target.getDistanceFromSource() == Double.MAX_VALUE)
	{
		System.out.println("There is no such path from source to the target vertex!");
	}

	else 
	{
		showPath(source, target);
		System.out.println("The Shortest Distance from "+source.getName()+" to " +target.getName()+" is: "+ target.getDistanceFromSource()+ " km");
		for(Vertex vertexes : vertexList){
			vertexes.setDistanceFromSource(Double.POSITIVE_INFINITY);
			tracePath.clear();
		}
	}

	}


}

class Vertex{
	private String name;
	private boolean wasVisited;
	private ArrayList<Edge> adjacentEdges;
	private double minimumDistance = Double.POSITIVE_INFINITY; // These should later be relaxed
	private Vertex previousVertex = null; // Will be changed as needed
	private int index;
	public Vertex(String name){
		this.name = name;
		this.adjacentEdges = new ArrayList <>();
	}
	public Vertex(){

		this.adjacentEdges = new ArrayList <>();
	}
	public void setName(String newName){this.name = newName;}

	public String getName(){return this.name;}

	public void setWasVisited(boolean truthValue){	this.wasVisited = truthValue;}

	public boolean getWasVisited(){	return this.wasVisited;}

	public void setDistanceFromSource(double distance){this.minimumDistance = distance;}

	public double getDistanceFromSource(){	return this.minimumDistance;}

	public void setPreviousVertex(Vertex previousVertex){	this.previousVertex = previousVertex;}
	public int getIndex(){
		return this.index;
	}
	public Vertex getPreviousVertex(){	return this.previousVertex;}
	public void setIndex(int index){
		this.index = index;
	}
	public static int nameToIndex(String name, Vertex[] vertexArray){
		ArrayList<Vertex> vertexList = new ArrayList<Vertex>(Arrays.asList(vertexArray));
		for(Vertex vertex: vertexArray){
			if(vertex.getName().toLowerCase().equals(name.toLowerCase())){
				return vertexList.indexOf(vertex);
			}
		}
		return -1;

	}
	public void addNewEdge(Edge theNewEdge){this.adjacentEdges.add(theNewEdge);}
	public ArrayList<Edge> adjacentEdges(){return this.adjacentEdges;}


}

class Edge {
	private Vertex startingVertex, endingVertex;
	private double weight;

	public Edge(Vertex startingVertex, Vertex endingVertex, double weight)
	{
		this.startingVertex  = startingVertex;
		this.endingVertex = endingVertex;
		this.weight = weight;
	}

	public void setStartingVertex(Vertex newStartingVertex){ this.startingVertex  = newStartingVertex;}

	public Vertex getStartingVertex(){ return this.startingVertex;	}

	public void setEndingVertex(Vertex newEndingVertex){ this.endingVertex = newEndingVertex;}

	public Vertex getEndingVertex(){ return this.endingVertex; }

	public void setWeight(double weight){	this.weight = weight;}

	public double getWeight(){	return this.weight;}

}

