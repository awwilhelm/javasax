/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saxparser;

/**
 *
 * @author LeBorg
 */
class Node {
    private String content = "";
    private int indentationCount = 0;
    
    public Node() {
        
    }
    
    public Node(String content, int count) {
        this.content = content;
        this.indentationCount = count;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setIndentationCount(int count) {
        this.indentationCount = count;
    }
    
    public int getIndentationCount() {
        return indentationCount;
    }
}