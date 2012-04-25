package org.nicholas.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Graph
{
    private final int MAX_VERTS = 5;
    private Vertex[] vertexList;
    private int adjMat[][];
    private int nVerts;
    private List<Edge> edges = new ArrayList<Edge>();
    
    private List<List<Integer>> connectedComponents = new ArrayList<List<Integer>>();;

    public boolean isEdge(int start, int end)
    {
        return adjMat[start][end] == 1;
    }
    
    public Graph()
    {
        vertexList = new Vertex[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        
        for (int i = 0; i < MAX_VERTS; i++)
        {
            for (int j = 0; j < MAX_VERTS; j++)
            {
                adjMat[i][j] = 0;
            }
        }
    }
    
    public void addVertex(char lab)
    {
        Vertex vertex = new Vertex(lab);
        addVertex(vertex);
    }
    
    public void addVertex(Vertex vertex)
    {
        vertexList[nVerts] = vertex;
        
        //����һ����ͨ����
        List<Integer> connectedComponent = new ArrayList<Integer>();
        connectedComponent.add(nVerts);
        
        //��Ӵ���ͨ����
        connectedComponents.add(connectedComponent);
        
        nVerts++;
        
    }
    
    public void addEdage(int start, int end, int weight)
    {
        
        Edge edge = new Edge(start, end, weight);
        addEdage(edge);
    }
    
    public void addEdage(Edge edge)
    {
        edges.add(edge);
        
        adjMat[edge.start][edge.end] = 1;
                
        //����������ڵ㲻��ͬһ����ͨ�����У�����������������ͨ�����ϲ�
        if(!isInSameConnectedComponents(edge.start, edge.end))
        {
            List<Integer> connectedComponentOfStart = findConnectedComponent(edge.start);
            List<Integer> connectedComponentOfEnd = findConnectedComponent(edge.end);
            
            List<Integer> meragedConnectedComponents = new ArrayList<Integer>();
            meragedConnectedComponents.addAll(connectedComponentOfStart);
            meragedConnectedComponents.addAll(connectedComponentOfEnd);
            
            connectedComponents.remove(connectedComponentOfStart);
            connectedComponents.remove(connectedComponentOfEnd);
            
            connectedComponents.add(meragedConnectedComponents);
        }
        
    }
    
    
    

    public void display(int i)
    {
        System.out.println(vertexList[i].label);
    }
    
    /**
     * �����������<br>
     * Rule 1: if possible, visit an adjacent mark it, and push it into the
     * stack -- for pop the visited vertex and find the poped vertex<br>
     * Rule 2: if can't follow Rule 1, then if possible, pop a vertex off the
     * stack<br>
     * Rule 3: if can not follow Rule 1 and Rule 2, you're done<br>
     */
    public void dfs()
    {
        Stack<Integer> stack = new Stack<Integer>();
        display(0);
        vertexList[0].wasVisited = true;
        stack.push(0);
        while (!stack.isEmpty())
        {
            int v = getAdjUnvisted(stack.peek());
            if (v == -1)
            {
                stack.pop();
            }
            else
            {
                display(v);
                vertexList[v].wasVisited = true;
                stack.push(v);
            }
            
        }
    }
    
    /**
     * �����������<br>
     * Rule 1: Visit the unvisited vertex(if there is one) that's adjacent to
     * the current vertex, mark it, and insert it into the queue. Rule 2: if can
     * not carry out Rule 1, because there are no more unvisited vertices,
     * remove a vertex from the queue(if possible), and make it as the current
     * vertex. Rule 3: if can not carry Rule 1 and Rule 2 because the queue is
     * empty, you're done
     * 
     */
    public void bfs()
    {
        Queue<Integer> queue = new LinkedList<Integer>();
        display(0);
        vertexList[0].wasVisited = true;
        queue.add(0);
        
        int current;
        int adj;
        while (!queue.isEmpty())
        {
            current = queue.remove();
            while ((adj = getAdjUnvisted(current)) != -1)
            {
                vertexList[adj].wasVisited = true;
                display(adj);
                queue.add(adj);
            }
        }
    }
    
    /**
     * ��Ȩ�ص���С������
     */
    public void mst()
    {
        Stack<Integer> stack = new Stack<Integer>();
        vertexList[0].wasVisited = true;
        stack.push(0);
        while (!stack.isEmpty())
        {
            int current = stack.peek();
            
            int v = getAdjUnvisted(current);
            if (v == -1)
            {
                stack.pop();
            }
            else
            {
                System.out.print(vertexList[current].label);
                System.out.print(vertexList[v].label);
                System.out.print(" ");
                vertexList[v].wasVisited = true;
                stack.push(v);
            }
            
        }
    }
    
    /**
     * kruskal ��С�������㷨
     */
    public Graph kruskal()
    {
        Graph mst = new Graph();
        
        for(Vertex v : this.vertexList)
        {
            mst.addVertex(v);
        };
        
        int nVertex = mst.vertexList.length;
        
        while (mst.edges.size() < nVertex - 1 && !this.edges.isEmpty())
        {
            //�Ա߽�����������
            Collections.sort(this.edges);
            
            // ��ȡȨ����С�ı�
            Edge lowestWeightEdge = this.edges.remove(0);
            
            // ��������ߵ������ڵ��ڲ�ͬ����ͨ�����У�������߼ӵ���С�������У�
            if (!mst.isInSameConnectedComponents(lowestWeightEdge.start, lowestWeightEdge.end))
            {
                mst.addEdage(lowestWeightEdge);
            }
            // ���򣬺��Դ˱�            
        }
        return mst;
        
    }
    
    
    /**
     * topo����<br>
     * ��֧�ֶԷ�ѭ����ͼ����topo����<br>
     * Rule 1 : Find a vertex that has no successors Rule 2 : Delete this vertex
     * from the graph, and insert its label at the beginning of a list.
     */
    public void topo()
    {
        char[] sortedArray = new char[MAX_VERTS];
        int orig_nVerts = nVerts;
        
        while (nVerts > 0)
        {
            int currentVertex = noSuccessors();
            if (currentVertex == -1)
            {
                System.out.println("Error : Graph has cycles");
                return;
            }
            sortedArray[nVerts - 1] = vertexList[currentVertex].label;
            deleteVertex(currentVertex);
        }
        
        System.out.println("Topologically sorted order : ");
        for (int i = 0; i < orig_nVerts; i++)
        {
            System.out.print(sortedArray[i]);
            System.out.println();
        }
        
    }
    
    /**
     * ��һ������ͼ�Ĵ��ݱհ�
     * 
     * @return
     */
    public int[][] warshall()
    {
        return null;
    }
    
    /**
     * ��ȡһ��ͼ��������ͨ����
     */
    public void connectedComponents()
    {
        
    }
    
    private int getAdjUnvisted(Integer v)
    {
        for (int j = 0; j < MAX_VERTS; j++)
        {
            if (adjMat[v][j] == 1 && vertexList[j].wasVisited == false)
            {
                return j;
            }
        }
        return -1;
    }
    
    private int noSuccessors()
    {
        boolean isEdge;
        for (int row = 0; row < nVerts; row++)
        {
            isEdge = false;
            for (int col = 0; col < nVerts; col++)
            {
                if (adjMat[row][col] > 0)
                {
                    isEdge = true;
                    break;
                }
            }
            if (!isEdge)
            {
                return row;
            }
        }
        return -1;
        
    }
    
    private void deleteVertex(int delVert)
    {
        // if (delVert != nVerts - 1)
        // {
        // delete from vertexList
        for (int j = delVert; j < nVerts - 1; j++)
        {
            vertexList[j] = vertexList[j + 1];
        }
        
        // delete from adjMat
        for (int row = delVert; row < nVerts - 1; row++)
        {
            moveRowUp(row, nVerts);
        }
        
        for (int col = delVert; col < nVerts - 1; col++)
        {
            moveColLeft(col, nVerts - 1);
        }
        nVerts--;
        // }
    }
    
    private void moveRowUp(int row, int length)
    {
        for (int col = 0; col < length; col++)
        {
            adjMat[row][col] = adjMat[row + 1][col];
        }
    }
    
    private void moveColLeft(int col, int length)
    {
        for (int row = 0; row < length; row++)
        {
            adjMat[row][col] = adjMat[row][col + 1];
        }
    }
    
    /**
     * ��ѯ�ڵ� v���ڵ���ͨ����
     * @param v �ڵ���vertexList�е�index
     * @return �ڵ� v���ڵ���ͨ����
     */
    private List<Integer> findConnectedComponent(int v)
    {
        for(List<Integer> connectedComponent : connectedComponents)
        {
            if(connectedComponent.contains(v))
            {
                return connectedComponent;
            }
        }
        
        System.err.println("Can not find connected components for vertex : " + v);
        return null;
    }
    
    /**
     * �ж�ָ���������ڵ��Ƿ���ͬһ����ͨ������
     * @param v1 �ڵ�1��vertexList�е�index
     * @param v2 �ڵ�2��vertexList�е�index
     * @return
     */
    private boolean isInSameConnectedComponents(int v1, int v2)
    {
        for (List<Integer> connectedComponent : connectedComponents)
        {
            if (connectedComponent.contains(v1) && connectedComponent.contains(v2))
            {
                return true;
            }
        }
        return false;
    }
    
    private String getLabel(int i)
    {
        String s = "N";
        Vertex vertex = vertexList[i];
        if (null != vertex)
        {
            s = vertex.label + "";
        }
        return s;
    }
    
    
    public void displayAdjMatrix()
    {
        
        // ��ӡ��ͷ
        System.out.print("  ");
        for (int k = 0; k < MAX_VERTS; k++)
        {
            System.out.print(getLabel(k) + "  ");
        }
        System.out.println();
        
        for (int i = 0; i < adjMat.length; i++)
        {
            int row[] = adjMat[i];
            
            // ��ӡ��ͷ
            System.out.print(getLabel(i) + "[");
            for (int j = 0; j < row.length; j++)
            {
                System.out.print(adjMat[i][j]);
                System.out.print(", ");
            }
            System.out.println("]");
        }
        System.out.println();
    }
    
    
    public static void main(String[] args)
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
        
        // graph.dfs();
        // graph.bfs();
        // graph.mst();
//        graph.displayAdjMatrix();
        // graph.deleteVertex(1);
        // graph.displayAdjMatrix();
//        graph.topo();
        Graph mst = graph.kruskal();
        mst.displayAdjMatrix();
        
    }
}
