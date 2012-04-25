package org.nicholas.graph;

import static org.junit.Assert.*;

import org.junit.Test;

public class GraphTest
{
    @Test
    public void kruskal()
    {
        Graph graph = new Graph();
        
        graph.addVertex('A');
        graph.addVertex('B');
        graph.addVertex('C');
        graph.addVertex('D');
        graph.addVertex('E');
        
        graph.addEdage(0, 1, 5);
        graph.addEdage(0, 4, 3);
        graph.addEdage(1, 2, 2);
        graph.addEdage(2, 4, 1);
        graph.addEdage(3, 4, 2);
        graph.displayAdjMatrix();
        
        Graph mst = graph.kruskal();
        mst.displayAdjMatrix();
        
        //有条 A和B的边
        assertTrue(mst.isEdge(0, 4));
        
        
        assertTrue(mst.isEdge(1, 2));
        
        assertTrue(mst.isEdge(2, 1));
        assertTrue(mst.isEdge(2, 4));
        
        
        assertTrue(mst.isEdge(3, 4));
        
        assertTrue(mst.isEdge(4, 0));
        assertTrue(mst.isEdge(4, 2));
        assertTrue(mst.isEdge(4, 3));
    }
}
