package com.problem3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Node {
    int value;
    Node leftNode, rightNode;

    public Node(int value) {
        this.value = value;
        this.leftNode = null;
        this.rightNode = null;
    }
}

class BST {
    Node root;

    public BST() {
        this.root = null;
    }

    public void add(int value) {
        root = addRecursive(root, value);
    }

    private Node addRecursive(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }
        if (value < current.value) {
            current.leftNode = addRecursive(current.leftNode, value);
        } else if (value > current.value) {
            current.rightNode = addRecursive(current.rightNode, value);
        }
        return current;
    }
}

public class FindPathBetweenTwoNodes {

    public static void main(String[] args) {
        BST bst = new BST();
        bst.add(8);
        bst.add(5);
        bst.add(10);
        bst.add(9);
        bst.add(11);
        bst.add(4);
        bst.add(6);

        int node1 = 4, node2 = 9;
        List<Integer> path = findPathBetweenNodes(bst.root, node1, node2);

        System.out.println("Path between " + node1 + " and " + node2 + ": " + path);
        System.out.println("Steps: " + (path.size() - 1));
    }

    public static List<Integer> findPathBetweenNodes(Node root, int node1, int node2) {
        List<Integer> path1 = new ArrayList<>();
        List<Integer> path2 = new ArrayList<>();

        findPathToNode(root, node1, path1);
        findPathToNode(root, node2, path2);

        // Find the common ancestor
        int i = 0;
        while (i < path1.size() && i < path2.size() && path1.get(i).equals(path2.get(i))) {
            i++;
        }

        // Combine paths: path1 to common ancestor + reverse of path2 from common ancestor
        List<Integer> fullPath = new ArrayList<>(path1.subList(0, i));
        List<Integer> path2Suffix = path2.subList(i, path2.size());
        Collections.reverse(path2Suffix);
        fullPath.addAll(path2Suffix);

        return fullPath;
    }

    private static boolean findPathToNode(Node current, int target, List<Integer> path) {
        if (current == null) {
            return false;
        }

        path.add(current.value);

        if (current.value == target) {
            return true;
        }

        if (target < current.value && findPathToNode(current.leftNode, target, path)) {
            return true;
        }
        if (target > current.value && findPathToNode(current.rightNode, target, path)) {
            return true;
        }

        path.remove(path.size() - 1); // Backtrack if not found
        return false;
    }
}
