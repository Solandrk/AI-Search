package AI;

import model.Node;

import java.util.*;

public class BDS {

    private Queue<Node> frontier = new LinkedList<Node>();
    private Hashtable<String, Boolean> inFrontier = new Hashtable<>();
    private Hashtable<String, Boolean> explored = new Hashtable<>();
    private boolean solving = true;

    public void reverseSearch(Node start) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                BFSSearch(start);
            }
        }).start();
    }

    public void normalSearch(Node start) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                BFSSearch(start);
            }
        }).start();
    }

    public void search(Node startNode) {
        normalSearch(startNode);
        reverseSearch(startNode.reverseNode());
    }

    private void BFSSearch(Node startNode) {

        if (startNode.isGoal()) {
            printResult(startNode, 0);
            return;
        }
        frontier.add(startNode);
        inFrontier.put(startNode.hash(), true);
        while (!(frontier.isEmpty()) && solving) {
            Node temp = frontier.poll();
            inFrontier.remove(temp.hash());
            if (explored.contains(temp.hash())) {
                endSolving(temp);
                return;
            }else
              explored.put(temp.hash(), true);
            ArrayList<Node> children = temp.successor();
            for (Node child : children) {
                if (!(inFrontier.containsKey(child.hash())) && !(explored.containsKey(child.hash()))) {
                    System.out.println(child); // Print Child Position
                    if (child.isGoal()) {
                        endSolving(child);
                        return;
                    }
                    frontier.add(child);
                    inFrontier.put(child.hash(), true);
                }

            }

        }

        System.out.println("no solution");

    }

    private void endSolving(Node node) {
        printResult(node,0);
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
