package AI;

import model.Cell;
import model.Node;

import java.util.*;

public class AStar {

    public void search(Node start) {

        Queue<Node> frontier = new LinkedList<Node>();
        Hashtable<String, Boolean> explored = new Hashtable<>();
        frontier.add(start);
        Hashtable<String, Integer> distance = new Hashtable<>();

        while (!frontier.isEmpty()) {
            Node current = frontier.poll();
            explored.put(current.hash(), true);
            if (current.isGoal()) {
                printResult(current, 0);
                return;
            }
            ArrayList<Node> children = current.successor();
            distance.clear();
            for (Node child :
                    children) {
                if (!explored.contains(child.hash())) {
                    int heuristicDistance = child.heuristic(Cell.getGoal());
                    int childDistance = child.pathCost();
                    int totalDistance = childDistance + heuristicDistance;
                    if (distance.size() > 0) {
                        if(distance.get("child") != null)
                            if (totalDistance < distance.get("child")) {
                                distance.put("child", totalDistance);
                                frontier.add(child);
                            }
                    }else {
                        distance.put("child", totalDistance);
                        frontier.add(child);
                    }
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
