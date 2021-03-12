import javax.lang.model.util.ElementScanner14;

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
        
        swap(index, parentIndex, value);
        bubbleUp(parentIndex, value);      
    }

    private void bubbleDown(int parentIndex){
        int childIndex = getGreaterChildIndex(parentIndex);
        if(childIndex == -1){
            array[parentIndex] = 0;
            shiftLeft(parentIndex);
            return;
        }
        
        swap(childIndex, parentIndex, Integer.MIN_VALUE);
        bubbleDown(childIndex);
    }

    private void shiftLeft(int emptyIndex){
        int currentIndex = emptyIndex;
        while(array[currentIndex + 1] != 0){
            array[currentIndex] = array[currentIndex + 1];
            currentIndex++;
        }
        array[currentIndex] = 0;
    }

    private int getGreaterChildIndex(int parentIndex){
        int leftChildIndex = parentIndex * 2 + 1;
        int rightChildIndex = parentIndex * 2 + 2;
        if(leftChildIndex > count || rightChildIndex > count)
            return leftChildIndex > count && rightChildIndex > count ? -1 : Math.min(leftChildIndex, rightChildIndex);
        else
            return array[rightChildIndex] >= array[leftChildIndex] ? rightChildIndex : leftChildIndex;
    }
        
    private void swap(int index1, int index2, int value){
        int temp = array[index1];
        array[index1] = value;
        array[index2] = temp;
    }

    public void removeRoot(){
        array[0] = Integer.MIN_VALUE;
        bubbleDown(0);

        count--;
    }

    //------------Mosh Remove Solution----------
    //------------------------------------------
}
