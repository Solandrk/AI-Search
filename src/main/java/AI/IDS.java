package AI;

import model.Node;

import java.util.ArrayList;

/**
 <b> IDS ( Iterative Deeping Search ) </b><p>
 In computer science, iterative deepening search or more specifically iterative deepening depth-first search
 (IDS or IDDFS) is a state space/graph search strategy in which
 a depth-limited version of depth-first search is run repeatedly
 with increasing depth limits until the goal is found.
 IDDFS is optimal like breadth-first search, but uses much less memory; at each iteration,
 it visits the nodes in the search tree in the same order as depth-first search,
 but the cumulative order in which nodes are first visited is effectively breadth-first

 * */
public class IDS {

    /** <p>Depth condition : is reached to deepest surface or not</p*/
    private boolean bottomReached = false;

    /**
     * <p> Start founding solution of problem </p>
     * <p>
     *     In this function we set value of depth and then call next function
     * @param start Starting point
     * */

    public void search(Node start) {
        // Start by doing DFS with a depth of 1, keep doubling depth until we reach the "bottom" of the tree or find the node we're searching for
        int depth = 1;
        while (!bottomReached) {
            bottomReached = true; // One of the "end nodes" of the search with this depth has to still have children and set this to false again
            Node result = search(start, 0, depth);
            if (result != null) {
                // We've found the goal node while doing DFS with this max depth
                printResult(result,depth);
                return;
            }

            // We haven't found the goal node, but there are still deeper nodes to search through
            depth *= 2;
        }
        System.out.println("no solution");
    }

    public Node search(Node node, int currentDepth, int maxDepth) {
        if (node.isGoal()) {
            // We have found the goal node  we're searching for
            return node;
        }

        if (currentDepth == maxDepth) {
            // We have reached the end for this depth...
            if (node.successor().size() > 0) {
                //...but we have not yet reached the bottom of the tree
                bottomReached = false;
            }else bottomReached = true;
        }
        // Recurse with all children
        ArrayList<Node> children = node.successor();
        for (int i = 0; i < children.size(); i++) {
            Node result = search(children.get(i) , currentDepth + 1, maxDepth);
            if (result != null) {
                // We've found the goal node while going down that child
                return result;
            }
        }
        // We've gone through all children and not found the goal node
        return null;
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
