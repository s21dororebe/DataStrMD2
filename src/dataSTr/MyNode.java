package dataSTr;

public class MyNode<T> {
    private MyNode<T> leftChild;
    private MyNode<T> rightChild;
    private MyNode<T> parent;
    private T value;

    public MyNode(T myNode, MyNode<T> parent) {
        setValue(myNode);
        this.leftChild = null;
        this.rightChild = null;
        this.parent = parent;
    }

    public T getValue() {
        return this.value;
    }
    public MyNode<T> getLeftChild() {
        return this.leftChild;
    }
    public MyNode<T> getRightChild() {
        return this.rightChild;
    }
    public MyNode<T> getParent() {
        return parent;
    }

    public void setValue(T value) {
        if (value != null) {
            this.value = value;
        } else {
            this.value = null;
        }
    }
    public void setParent(MyNode<T> parent) {
        this.parent = parent;
    }
    public void setLeftChild(T value) {
        if (value != null) {
            this.leftChild = new MyNode<T>(value, this);
        } else {
            this.leftChild = null;
        }
    }
    public void setLeftChild(MyNode<T> node) {
        if (node != null) {
            this.leftChild = node;
            leftChild.setParent(this);
        } else {
            this.leftChild = null;
        }
    }
    public void setRightChild(T value) {
        if (value != null) {
            this.rightChild = new MyNode<T>(value, this);
        } else {
            this.rightChild = null;
        }
    }
    public void setRightChild(MyNode<T> node) {
        if (node != null) {
            this.rightChild = node;
            rightChild.setParent(this);
        } else {
            this.rightChild = null;
        }
    }

    public boolean hasChildren(MyNode<T> node) throws Exception {
        if (node != null) {
            return ((node.getLeftChild() != null) || (node.getRightChild() != null));
        }
        throw new Exception("Given node does not exist");
    }
    public boolean hasChildren() throws Exception {
        if (this.value != null) {
            return ((this.getLeftChild() != null) || (this.getRightChild() != null));
        }
        throw new Exception("Given node does not exist");
    }

    @Override
    public String toString() {
        return "" + this.getValue();
    }


}