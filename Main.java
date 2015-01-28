
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Main {
	static Scanner in;
	Node root = new Node();
	int numberOfNodes;
	LinkedList<Node> tree = new LinkedList<Node>();
	LinkedList<Node> newInfected=new LinkedList<Node>();
	int rootID;
	int count;
	int steps;
	LinkedList<Node> infected = new LinkedList<Node>();
	File file=new File("test.txt");
	public Main() throws FileNotFoundException{in=new Scanner(file);setUp();infect(root);printSteps();}
	
	
	
	public void setUp(){
		int id = 0;
		int x,y;
		numberOfNodes = in.nextInt();
		rootID = in.nextInt();
		for(int i = 0;i<numberOfNodes;i++){
			Node newNode = new Node();
			newNode.setID(++id);
			tree.add(newNode);
		}
		root = tree.get(rootID-1); 
		for(int j=0;j<numberOfNodes-1;j++){
			x = in.nextInt();
			y = in.nextInt();
			tree.get(x-1).addChildren(tree.get(y-1));
			tree.get(y-1).addChildren(tree.get(x-1));
		}
		root.setInfected(true);
		root.setDepth(1);
		setDepths(root);
		for(int l=0;l<tree.size();l++){
			System.out.println(tree.get(l).getDepth());
		}
		for(int k=0;k<tree.size();k++){
			findBestPath(tree.get(k));
		}
	}

	
	public void infect(Node node){
			int best=0;
			if(node.getPriority().size()!=0){
				best=node.getPriority().getFirst().getID();
				node.priority.removeFirst();
				node.infectNeighbour(best);
				newInfected.add(node);
				newInfected.add(node.findChildren(best));
				steps++;
			}
			
			
		while(!complete()){
			for(int i=0;i<tree.size();i++){
				if(tree.get(i).isInfected()){
					Node a=tree.get(i).findUninfected();
					if(a!=null){
						newInfected.add(a);
					}
				}
			}
			for(int q=0;q<newInfected.size();q++){
				if(!newInfected.get(q).isInfected()){
					newInfected.get(q).setInfected(true);
				}
				
			}
			steps++;
		}

	}
	
	public void findBestPath(Node node){
		LinkedList<Node> paths = new LinkedList<Node>();
		node.setCounted(true);
		for(int i=0;i<node.getList().size();i++){
			if(node.getDepth()<node.getList().get(i).getDepth()){
				node.getList().get(i).setCounted(true);
				paths.add(node.getList().get(i));
			}
		}
		int pathSize[]=new int[paths.size()];
		for(int j=0;j<paths.size();j++){
			count(paths.get(j));
			pathSize[j]=count;
			count=0;
		}
		int max=0;
		Node bestPath=new Node();
		while(!sorted(pathSize)){
			for(int z=0;z<pathSize.length-1;z++){
				if(pathSize[z]<pathSize[z+1]){
					int a=pathSize[z];
					int b=pathSize[z+1];
					pathSize[z]=b;
					pathSize[z+1]=a;
					Collections.swap(paths, z, z+1);
				}
			}
		}
		node.priority=paths;
		if(node.getPriority().size()!=0){
			//System.out.println(node.getID()+""+node.getPriority().get(0).getID());
		}
		//System.out.println("The path with most internal nodes is "+bestPath.getID()+"\n"+"With "+max+" nodes.");
		for(int z=0;z<tree.size();z++){
			tree.get(z).setCounted(false);
		}
	}
	
	public void count(Node node){
		for(int i=0;i<node.getList().size();i++){
			if(node.getList().get(i).getCounted()==false){
				node.getList().get(i).setCounted(true);
				count++;
				count(node.getList().get(i));
			}
		}
	}

	public boolean sorted(int a[]){
		for(int i=0;i<a.length-1;i++){
			if(a[i]<a[i+1]){
				return false;
			}
		}
		return true;
	}
	
	public void setDepths(Node node){
		for(int i=0;i<node.getList().size();i++){
			if(node.getList().get(i).getDepth()==0){
				node.getList().get(i).setDepth(node.getDepth()+1);
				setDepths(node.getList().get(i));
			}
		}
	}
	
	
	
	
	
	
	public void printSteps(){
		System.out.print("The lowest number of steps is : "+steps);
	}
	
	
	public boolean complete(){
		for(int i=0;i<tree.size();i++){
			if(!tree.get(i).isInfected()){
				return false;
			}
		}
		return true;
	}

	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Main a=new Main();
	}

}
