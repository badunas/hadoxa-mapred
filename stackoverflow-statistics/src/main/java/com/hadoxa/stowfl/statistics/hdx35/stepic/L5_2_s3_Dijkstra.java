package com.hadoxa.stowfl.statistics.hdx35.stepic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by badun on 6/21/16.
 */
public class L5_2_s3_Dijkstra {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int result = execute(reader);
        System.out.println(result);
    }

    public static int execute(BufferedReader reader) throws IOException {
        boolean firstLine = true;
        Context context = new Context();

        String line;
        while((line = reader.readLine()) != null) {
            String[] split = line.split(" ");
            if (firstLine) {
                context.nodeCount = Integer.parseInt(split[0]);
                context.edgeCount = Integer.parseInt(split[1]);
                firstLine = false;
            } else {
                if (split.length > 2) {
                    context.addEdge(split[0], split[1], Integer.parseInt(split[2]));
                } else {
                    context.addFromNodeLabel(split[0]);
                    context.addToNodeLabel(split[1]);
                }
            }
        }

        return findShortestPath(context);
    }

    static int findShortestPath(Context context) {
        context.getFromNode().minPath = 0;

        PriorityQueue<Node> restNodes = new PriorityQueue<>(context.nodeCount);
        for (Node node : context.graph.nodes()) {
            restNodes.add(node);
        }

        Node toNode = context.getToNode();
        if (context.getFromNode().equals(toNode)) {
            return 0;
        }
        while (!restNodes.isEmpty()) {
            Node nearestNode = restNodes.poll();
            if (nearestNode.minPath < 0) {
                break;
            }
            for (Edge edge : nearestNode.neighborhoods) {
                Node nextNode = edge.toNode;
                int newPath = nearestNode.minPath + edge.weight;
                if (nextNode.minPath < 0) {
                    nextNode.minPath = newPath;
                } else if (nextNode.minPath > newPath) {
                    nextNode.minPath = newPath;
                }
            }
            restNodes = refreshQueue(restNodes, context);
        }
        return toNode.minPath;
    }

    static PriorityQueue<Node> refreshQueue(PriorityQueue<Node> restNodes, Context context) {
        PriorityQueue<Node> newRestNodes = new PriorityQueue<>(context.nodeCount);
        for (Node restNode : restNodes) {
            newRestNodes.add(restNode);
        }
        return newRestNodes;
    }

    static class Context {
        int nodeCount;
        int edgeCount;
        String fromNodeLabel;
        String toNodeLabel;

        Graph graph = new Graph();

        void addEdge(String fromNodeLabel, String toNodeLabel, int weight) {
            graph.addEdge(fromNodeLabel, toNodeLabel, weight);
        }

        Node getFromNode() {
            return graph.nodesMap.get(fromNodeLabel);
        }

        Node getToNode() {
            return graph.nodesMap.get(toNodeLabel);
        }

        void addFromNodeLabel(String s) {
            fromNodeLabel = s;
            graph.addNode(s);
        }

        void addToNodeLabel(String s) {
            toNodeLabel = s;
            graph.addNode(s);
        }
    }

    static class Graph {
        Map<String, Node> nodesMap = new HashMap<>();

        void addEdge(String fromNodeLabel, String toNodeLabel, int weight) {
            Node fromNode = nodesMap.get(fromNodeLabel);
            Node toNode = nodesMap.get(toNodeLabel);
            if (fromNode == null) {
                fromNode = addNode(fromNodeLabel);
            }
            if (toNode == null) {
                toNode = addNode(toNodeLabel);
            }
            fromNode.addNeighborhood(new Edge(fromNode, toNode, weight));
        }

        Node addNode(String nodeLabel) {
            Node node = new Node(nodeLabel);
            if (nodesMap.get(nodeLabel) == null) {
                nodesMap.put(nodeLabel, node);
            }
            return node;
        }

        Collection<Node> nodes() {
            return nodesMap.values();
        }
    }

    static class Node implements Comparable<Node> {
        String label;
        List<Edge> neighborhoods = new ArrayList<>();

        int minPath = -1;

        Node(String label) {
            this.label = label;
        }

        void addNeighborhood(Edge edge) {
            if (neighborhoods.contains(edge)) {
                return;
            }
            neighborhoods.add(edge);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            return label.equals(node.label);

        }

        @Override
        public int hashCode() {
            return label.hashCode();
        }

        @Override
        public int compareTo(Node o) {
            if (o.minPath == minPath) {
                return 0;
            }
            if (minPath < 0) {
                return 1;
            }
            if (o.minPath < 0) {
                return -1;
            }
            return minPath - o.minPath;
        }

        @Override
        public String toString() {
            return "label=" + label + ", minPath=" + minPath;
        }
    }

    static class Edge {
        Node fromNode;
        Node toNode;
        int weight;

        Edge(Node fromNode, Node toNode, int weight) {
            this.fromNode = fromNode;
            this.toNode = toNode;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Edge edge = (Edge) o;

            if (!fromNode.equals(edge.fromNode)) return false;
            return toNode.equals(edge.toNode);
        }

        @Override
        public int hashCode() {
            int result = fromNode.hashCode();
            result = 31 * result + toNode.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return "fromNode=" + fromNode.label +
                    ", toNode=" + toNode.label +
                    ", weight=" + weight;
        }
    }
}
