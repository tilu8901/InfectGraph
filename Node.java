import java.util.*;

public class Node {
	
	private LinkedList<Node> childrens = new LinkedList<Node>();
	LinkedList<Node> priority = new LinkedList<Node>();
	private int id;
	private boolean infected;
	private boolean counted;
	private int depth;
	public Node(){infected=false;counted=false;}
	

	public void setInfected(boolean a){
		infected =a;
	}
	public void setID(int id){
		this.id=id;
	}
	public void setDepth(int depth){
		this.depth=depth;
	}
	public int getDepth(){
		return depth;
	}
	public int getID(){
		return id;
	}
	public boolean getCounted(){
		return counted;
	}
	public void setCounted(boolean a){
		counted=a;
	}
	public LinkedList<Node> getList(){
		return childrens;
	}
	public LinkedList<Node> getPriority(){
		return priority;
	}
	public boolean isInfected(){
		if(infected==true){return true;}
		return false;
	}
	public void addChildren(Node children){
		childrens.add(children);
	}
	public void addPriority(Node children){
		priority.add(children);
	}
	public void infectNeighbour(int i){
				for(int k=0;k<childrens.size();k++){
					if(childrens.get(k).getID()==i){
						childrens.get(k).setInfected(true);
					}
				}
		}

	public Node findUninfected(){
		if(priority.size()!=0){
			Node node=priority.getFirst();
			priority.removeFirst();
			return node;
		}
		return null;
}
	public Node findChildren(int i){
		for(int k=0;k<childrens.size();k++){
			if(childrens.get(k).getID()==i){
				return childrens.get(k);
			}
		}
		return null;
	}

}
