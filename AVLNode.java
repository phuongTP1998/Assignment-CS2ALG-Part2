public class AVLNode<T extends Comparable<T>> {
    private T data;
    private AVLNode<T> leftChild;
    private AVLNode<T> rightChild;
    private int height;

    public AVLNode(T data){
        this.data = data;
        leftChild = null;
        rightChild = null;
        height = 0;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public AVLNode<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(AVLNode<T> leftChild) {
        this.leftChild = leftChild;
    }

    public AVLNode<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(AVLNode<T> rightChild) {
        this.rightChild = rightChild;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
