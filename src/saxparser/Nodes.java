/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saxparser;

import java.util.ArrayList;

/**
 *
 * @author LeBorg
 */
public class Nodes {
    private ArrayList<Node> nodes;
    
    public Nodes() {
        nodes = new ArrayList<>();
    }
    
    public ArrayList<Node> getNodes() {
        return nodes;
    }
    
    public void addNode(Node node) {
        nodes.add(node);
    }
}
