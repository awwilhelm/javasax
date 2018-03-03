/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saxparser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author LeBorg
 */
public class XmlNode {
    private String key;
    private String value = "";
    private Map<String, List<XmlNode>> properties;
    private Map<String, String> attributes;
    
    public XmlNode() {}
    
    public XmlNode(String key) {
        this.key = key;
        this.attributes = new HashMap<String, String>();
    }
    
    public XmlNode(String key, String value, Map<String, List<XmlNode>> properties) {
        this(key);
        this.value = value;
        this.properties = properties;
    }
    
    public String getKey() {
        return key;
    }
    
    public void setKey(String key) {
        this.key = key;
    }
    
    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    public void addProperty(String name, XmlNode node) {
        if(properties == null) {
            properties = new HashMap<String, List<XmlNode>>();
        }
        properties.putIfAbsent(name, new ArrayList<XmlNode>());
        properties.get(name).add(node);
    }
    
    public void deleteProperty(String name) {
        if(properties == null) {
            return;
        }
        properties.remove(name);
    }
    
    public boolean propertyExists(String name){
        if (properties == null){
            return false;
        }
        return this.properties.containsKey(name);
    }
    
    public List<XmlNode> getProperty(String name){
        return properties.get(name);
    }
    
    public Map<String, List<XmlNode>> getProperties(){
        return properties;
    }
    
    public void setAttributes(String key, String value){
        this.attributes.put(key, value);
    }
    
    public Map<String,String> getAttributes(){
        return attributes;
    }
    
    public int getAttributesLength(){
        return attributes.size();
    }
}
