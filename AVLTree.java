/**
 * StudentID: 20119571
 * Name: Trong Phuong Nguyen
 * Subject: CS2ALG
 */

import java.io.PrintWriter;

public class AVLTree<T extends Comparable<T>> {
    private AVLNode<T> root;

    public AVLTree(){
        root = null;
    }

    public T contains(T searchElement){
        boolean found = false;
        AVLNode<T> current = root;
        T temp = null;

        while (current != null && !found){
            if (current.getData().compareTo(searchElement) > 0){
                current = current.getLeftChild();
            } else if (current.getData().compareTo(searchElement) < 0){
                current = current.getRightChild();
            } else {
                temp = current.getData();
                found = true;
            }
        }
        return temp;
    }

    private int height(AVLNode<T> localRoot){
        if(localRoot == null) {
            return -1;
        }
        else {
            return localRoot.getHeight();
        }
    }

    public AVLNode<T> getRoot(){
        return root;
    }

    public void displayElements(PrintWriter p){
        displaySubtreeInOrder(root, p);
    }

    public String displayElements(){
        return displaySubtreeInOrder(root);
    }

    private String displaySubtreeInOrder(AVLNode<T> localRoot){
        String temp = "";
        if (localRoot != null)
        {
            displaySubtreeInOrder(localRoot.getLeftChild());
            temp += localRoot.getData();
            displaySubtreeInOrder(localRoot.getRightChild());
        }
        return temp;
    }

    private void displaySubtreeInOrder(AVLNode<T> localRoot, PrintWriter p){
        if (localRoot != null)
        {
            displaySubtreeInOrder(localRoot.getLeftChild(), p);
            p.println(localRoot.getData().toString());
            displaySubtreeInOrder(localRoot.getRightChild(), p);
        }
    }

    private void setHeight(AVLNode<T> localRoot){
        if (height(localRoot.getLeftChild()) > height(localRoot.getRightChild())){
            localRoot.setHeight(height(localRoot.getLeftChild()) + 1);
        } else {
            localRoot.setHeight(height(localRoot.getRightChild()) + 1);
        }
    }

    public void insert(T data){
        insertElement(data);
    }

    public boolean insertElement(T data){
        root = insertElement(root, data);
        return true;
    }

    private AVLNode<T> leftRotation(AVLNode<T> node){
        AVLNode<T> g = node;
        AVLNode<T> p = g.getRightChild();
        AVLNode<T> lcp = p.getLeftChild();

        p.setLeftChild(g);
        g.setRightChild(lcp);

        setHeight(g);
        return p;
    }

    private AVLNode<T> rightRotation(AVLNode<T> node){
        AVLNode<T> g = node;
        AVLNode<T> p = g.getLeftChild();
        AVLNode<T> rcp = p.getRightChild();
        p.setRightChild(g);
        g.setLeftChild(rcp);

        setHeight(g);
        return p;
    }

    private AVLNode<T> rightLeftRotation(AVLNode<T> node){
        AVLNode<T> g = node;
        AVLNode<T> p = g.getRightChild();
        g.setRightChild(rightRotation(p));
        return leftRotation(g);
    }

    private AVLNode<T> leftRightRotation(AVLNode<T> node){
        AVLNode<T> g = node;
        AVLNode<T> p = g.getLeftChild();
        g.setLeftChild(leftRotation(p));
        return rightRotation(g);
    }

    private int getHeightDifference(AVLNode<T> node){
        AVLNode leftChild = node.getLeftChild();
        AVLNode rightChild = node.getRightChild();
        int leftHeight = leftChild == null ? -1 : leftChild.getHeight();
        int rightHeight = rightChild == null ? -1 : rightChild.getHeight();

        return leftHeight - rightHeight;
    }

    private AVLNode<T> rebalance(AVLNode<T> rootNode){
        int diff = getHeightDifference(rootNode);

        if(diff < -1){
            if(getHeightDifference(rootNode.getRightChild()) < 0){
                rootNode = leftRotation(rootNode);
            } else {
                rootNode = rightLeftRotation(rootNode);
            }
        } else if(diff > 1){
            if(getHeightDifference(rootNode.getLeftChild()) > 0){
                rootNode = rightRotation(rootNode);
            } else {
                rootNode = leftRightRotation(rootNode);
            }
        }

        return rootNode;
    }

    public AVLNode search(T key) {
        if(root == null) {
            return null;
        }

        AVLNode<T> p = root;
        while(true){
            if(p == null && key.compareTo(p.getData()) == 0){
                break;
            } else if(key.compareTo(p.getData()) > 0){
                p = p.getLeftChild();
            } else {
                p = p.getRightChild();
            }
        }
        return p;
    }

    private AVLNode<T> insertElement(AVLNode<T> localRoot, T data){
        if(localRoot == null){
            localRoot = new AVLNode<T>(data);
        } else if (data.compareTo(localRoot.getData()) < 0){
            AVLNode<T> leftChild = localRoot.getLeftChild();
            AVLNode<T> subtree = insertElement(leftChild, data);
            localRoot.setLeftChild(subtree);
            localRoot = rebalance(localRoot);
        } else {
            AVLNode<T> rightChild = localRoot.getRightChild();
            AVLNode<T> subtree = insertElement(rightChild, data);
            localRoot.setRightChild(subtree);
            localRoot = rebalance(localRoot);
        }

        setHeight(localRoot);
        return localRoot;
    }
}
