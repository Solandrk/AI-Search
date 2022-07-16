package AI;

import model.Node;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    public void search(Node startNode) {

        Queue<Node> frontier = new LinkedList<Node>();
        Hashtable<String, Boolean> inFrontier = new Hashtable<>();
        Hashtable<String, Boolean> explored = new Hashtable<>();
        if (startNode.isGoal()) {
            printResult(startNode, 0);
            return;
        }
        frontier.add(startNode);
        inFrontier.put(startNode.hash(), true);
        while (!frontier.isEmpty()) {
            Node temp = frontier.poll();
            inFrontier.remove(temp.hash());
            explored.put(temp.hash(), true);
            ArrayList<Node> children = temp.successor();
            for (Node child : children) {
                if (!(inFrontier.containsKey(child.hash())) && !(explored.containsKey(child.hash()))) {
                    //System.out.println(child); // Print Child Position
                    if (child.isGoal()) {
                        /**In faghat baraye didan ravesh hal masealast . mitoni
                         packesh koni*/
                        printResult(child, 0);
                        return;}
                    frontier.add(child);
                    inFrontier.put(child.hash(), true);
                }
            }
        }

        System.out.println("no solution");

    }

    public void printResult(Node node, int depthCounter) {
        if (node.parent == null) {
            System.out.println("problem solved at a depth of  : " + depthCounter);
            return;
        }
        System.out.println(node.toString());
        node.drawState();
        printResult(node.parent, depthCounter + 1);
    }

}
