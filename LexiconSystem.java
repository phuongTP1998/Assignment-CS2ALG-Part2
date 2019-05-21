import java.io.IOException;
import java.io.PrintWriter;

public class LexiconSystem {
    private AVLTree<Lexicon> dataStructure;
    private Lexicon temp1;
    private String temp2;
    private Boolean isMatch = false;

    public LexiconSystem(){
        dataStructure = new AVLTree<Lexicon>();
    }

    public boolean insert(String word){
        Lexicon temp = new Lexicon(word);
        return dataStructure.insertElement(temp);
    }

    public void triggerFinding(){
        findingNeighbors(dataStructure.getRoot());
    }

    private void findingNeighbors(AVLNode<Lexicon> localRoot){
        if (localRoot != null) {
            findingNeighbors(localRoot.getLeftChild());
            findingNeighbors(localRoot.getRightChild());
            temp1 = localRoot.getData();
            findingString(dataStructure.getRoot());
        }
    }

    private void findingString(AVLNode<Lexicon> localRoot) {
        if (localRoot != null) {
            findingString(localRoot.getLeftChild());
            findingString(localRoot.getRightChild());
            temp2 = localRoot.getData().getWord();
            if (differBy1Character(temp1.getWord(), temp2)) {
                temp1.addSamePattern(temp2);
            }
        }
    }

     private void findingMatchingPattern(AVLNode<Lexicon> localRoot, PrintWriter p, String regex){
        if (localRoot != null) {
            findingMatchingPattern(localRoot.getLeftChild(), p, regex);
            findingMatchingPattern(localRoot.getRightChild(), p, regex);
            if (localRoot.getData().getWord().matches(regex)) {
                p.println(localRoot.getData().getWord());
                isMatch = true;
            }
        }
    }

    public void matchRegexWord(String regex, PrintWriter p){
        isMatch = false;
        findingMatchingPattern(dataStructure.getRoot(), p, regex);
        if(!isMatch){
            p.println("No words in the lexicon match the pattern");
        }
    }

    public boolean insert(Lexicon temp){
        return dataStructure.insertElement(temp);
    }

    public Lexicon contains(String word){
        return dataStructure.contains(new Lexicon(word));
    }

    public void displayElements(PrintWriter z) throws IOException {
        dataStructure.displayElements(z);
    }

    public static boolean differBy1Character(String word1, String word2){
        int differCount = 0;
        if(word1.length() == word2.length()){
            for(int i = 0; i < word1.length(); i++) {
                if(word1.charAt(i) != word2.charAt(i)){
                    if(differCount == 1){
                        return false;
                    }
                    differCount++;
                }
            }
        }
        if(differCount == 1){
            return true;
        }
        return false;
    }
}
