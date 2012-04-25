package org.nicholas.graph;

public class Edge implements Comparable<Edge>
{
    public Edge(int start, int end, int weight)
    {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
    public int start;
    public int end;
    public int weight;
    
    @Override
    public int compareTo(Edge o)
    {
        return this.weight - o.weight;
    }
}
