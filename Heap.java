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


    public void moshRemove(){
        if (count == 0) throw new IllegalStateException();

        array[0] = array[--count];

        var index = 0;
        while(index <= count && !isValidParent(index)){
            var largerChildIndex = (leftChild(index) > rightChild(index)) ? 
                leftChildIndex(index) : rightChildIndex(index);
            
            swap(index, largerChildIndex);
            index = largerChildIndex;
        }
    }

    private boolean isValidParent(int index){
        return array[index] >= leftChild(index) && array[index] >= rightChild(index);
    }

    private int leftChild(int index){
        return array[leftChildIndex(index)];
    }

    private int rightChild(int index){
        return array[rightChildIndex(index)];
    }

    private int leftChildIndex(int index){
        return index * 2 + 1;
    }

    private int rightChildIndex(int index){
        return index * 2 + 2;
    }
}
