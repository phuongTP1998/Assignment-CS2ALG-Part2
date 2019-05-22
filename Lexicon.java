/**
 * StudentID: 20119571
 * Name: Trong Phuong Nguyen
 * Subject: CS2ALG
 */

import java.util.ArrayList;

public class Lexicon implements Comparable<Lexicon>{
    private String word;
    private int count = 0;
    private AVLTree<String> samePattern = new AVLTree<>();
    private String listNeighbors = "";

    public Lexicon(String word, int count, AVLTree<String> samePattern){
        this.word = word;
        this.count = count;
        this.samePattern = samePattern;
    }

    public Lexicon(String word){
        this.word = word;
    }

    public Lexicon(String word, int count){
        this.word = word;
        this.count = count;
    }

    private void listString(AVLNode<String> localRoot) {
        if (localRoot != null) {
            listString(localRoot.getLeftChild());
            listString(localRoot.getRightChild());
            listNeighbors += localRoot.getData() + ", ";
        }
    }

    private void listWord(){
        listString(samePattern.getRoot());
    }

    public String toString(){
        listWord();
        return word + " " + count + " [" + listNeighbors + "]";
    }

    public boolean equals(Lexicon otherLexicon){
        return this.word.equalsIgnoreCase(otherLexicon.getWord());
    }

    public void addSamePattern(String temp){
        samePattern.insert(temp);
    }

    public int compareTo(Lexicon other){
        return word.compareTo(other.getWord());
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public AVLTree<String> getSamePattern() {
        return samePattern;
    }
}
