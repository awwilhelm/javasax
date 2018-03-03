/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saxparser;

import java.io.File;
import java.util.ArrayList;
import java.util.Stack;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author LeBorg
 */






public class NodeBuilder {
    public static Nodes load(File xmlCourseFile) throws Exception {
        Course course = new Course();
        Nodes nodes = new Nodes();
        Stack xmlStack = new Stack();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            
            DefaultHandler handler = new DefaultHandler() {
                Student student = null;
                String tempString = "";
                Node node = null;
                boolean pawprintFlag = false;
                boolean firstNameFlag = false;
                boolean lastNameFlag = false;
                boolean gradeFlag = false;
                
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    xmlStack.push(qName);
                    node = new Node();
                    if (qName.equalsIgnoreCase("student")) {
                        student = new Student();
                        String idString = attributes.getValue("id");
                        if (idString != null) {
                            int id = 0;
                            try {
                                id = Integer.parseInt(idString);
                            } catch (NumberFormatException e) {
                                throw new SAXException("student id in xml could not be converted to an int");
                            }
                            student.setId(id);
                        }
                        
                    }
                    if (qName.equalsIgnoreCase("pawprint")) {
                        pawprintFlag = true;
                    }
                    if (qName.equalsIgnoreCase("firstname")) {
                        firstNameFlag = true;
                    }
                    if (qName.equalsIgnoreCase("lastname")) {
                        lastNameFlag = true;
                    }
                    if (qName.equalsIgnoreCase("grade")) {
                        gradeFlag = true;
                    }
                }
                
                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    node.setIndentationCount(xmlStack.size());
                    xmlStack.pop();
                    node.setContent(tempString);
                    if(!tempString.replaceAll("\\s+","").equals("")){
                    nodes.addNode(node);
                    }
                    System.out.println(tempString);
                    if (qName.equalsIgnoreCase("student")) {
                        course.addStudent(student);
                        student = null;
                    }
                    if (qName.equalsIgnoreCase("pawprint")) {
                        pawprintFlag = false;
                    }
                    if (qName.equalsIgnoreCase("firstname")) {
                        firstNameFlag = false;
                    }
                    if (qName.equalsIgnoreCase("lastname")) {
                        lastNameFlag = false;
                    }
                    if (qName.equalsIgnoreCase("grade")) {
                        gradeFlag = false;
                    }
                }
                
                @Override
                public void characters(char ch[], int start, int length) throws SAXException {
                    
                    tempString = new String(ch, start, length);
                    //System.out.println(new String(ch, start, length) + "  " + xmlStack.peek());
                    if (pawprintFlag) {
                        if (student != null) {
                            student.setPawprint(new String(ch, start, length));
                        }
                    }
                    if (firstNameFlag) {
                        if (student != null) {
                            student.setFirstName(new String(ch, start, length));
                        }
                    }
                    if (lastNameFlag) {
                        if (student != null) {
                            student.setLastName(new String(ch, start, length));
                        }
                    }
                    if (gradeFlag) {
                        if (student != null) {
                            String gradeString = new String(ch, start, length);
                            double grade = 0.0;
                            try {
                                grade = Double.parseDouble(gradeString);
                            } catch (NumberFormatException e) {
                                throw new SAXException("grade in xml could not be converted to a double");
                            }
                            
                            student.setGrade(grade);
                        }
                    }                    
                }
            };
            
            saxParser.parse(xmlCourseFile.getAbsoluteFile(), handler);
            
        } catch (Exception e) {
            throw e;
        }
      //printNodes(nodes);
      return nodes; 
    }

    static void printNodes(Nodes nodes)
    {
        for(int i = 0; i < nodes.getNodes().size(); i++) {
            //System.out.println(nodes.getNodes().get(i).getContent() + "  " + nodes.getNodes().get(i).getIndentationCount());
            System.out.println(tabs(nodes.getNodes().get(i).getIndentationCount()) + nodes.getNodes().get(i).getContent());
        }
    }
    
    static String tabs(int count) {
        String start="";
        for(int i=0; i< count; i++) {
            start += "\t";
        }
        return start;
    }
}
