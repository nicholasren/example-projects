package org.nicholas.graph;

public class Vertex
{
    public char label;
    public boolean wasVisited;
    
    public Vertex(char lab)
    {
        this.label = lab;
        this.wasVisited = false;
    }
    
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("(");
        builder.append(label);
        builder.append(", ");
        builder.append(wasVisited);
        return builder.toString();
    }
}
