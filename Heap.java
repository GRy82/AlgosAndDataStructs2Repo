public class Heap {
    private int capacity = 15;
    private int[] array = new int[capacity];
    private int count = 0;


    public void insert(int insertion){
        if(isFull()) throw new IllegalStateException();
        int index = count;
        array[count++] = insertion;
        bubbleUp(index, insertion); 
    }

    public boolean isFull(){
        return count == capacity;
    }

    public boolean isEmpty(){
        return count == 0;
    }
    
    private void bubbleUp(int index, int value){
        int parentIndex = index % 2 == 0 ? (index - 2) / 2 : (index - 1) / 2;
        if(index == 0 || value < array[parentIndex])
            return;
        
        swap(index, parentIndex);
        bubbleUp(parentIndex, value);      
    }
        
    private void swap(int index1, int index2){
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }


    public int moshRemove(){
        if (count == 0) throw new IllegalStateException();
        
        var root = array[0];
        array[0] = array[--count];
        bubbleDown();

        return root;
    }
    
    private void bubbleDown(){
        var index = 0;
        while(index <= count && !isValidParent(index)){
            int largerChildIndex = largerChildIndex(index);
            
            swap(index, largerChildIndex);
            index = largerChildIndex;
        }
    }

    private int largerChildIndex(int index){
        if(!hasLeftChild(index))
            return index;

        if(!hasRightChild(index))
            return leftChild(index);

        return (leftChild(index) > rightChild(index)) ? 
            leftChildIndex(index) : rightChildIndex(index);
    }

    private boolean isValidParent(int index){
        if(!hasLeftChild(index))
            return true;

        var isValid = array[index] >= leftChild(index);

        if(hasRightChild(index))
            isValid &= array[index] >= rightChild(index);

        return isValid;
    }

    private int leftChild(int index){
        return array[leftChildIndex(index)];
    }

    private boolean hasLeftChild(int index){
        return leftChild(index) <= count;
    }

    private int rightChild(int index){
        return array[rightChildIndex(index)];
    }

    private boolean hasRightChild(int index){
        return rightChild(index) <= count;
    }

    private int leftChildIndex(int index){
        return index * 2 + 1;
    }

    private int rightChildIndex(int index){
        return index * 2 + 2;
    }
}
