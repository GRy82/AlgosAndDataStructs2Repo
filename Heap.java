public class Heap {
    private int capacity = 15;
    private int[] array = new int[capacity];
    private int count = 0;


    public void insert(int insertion){
        if(isFull()) throw new IllegalStateException();
        int index = count;
        array[count++] = insertion;
        bubble(index, insertion); 
    }

    public boolean isFull(){
        return count == capacity;
    }
    
    private void bubble(int index, int value){
        int parentIndex = index % 2 == 0 ? (index - 2) / 2 : (index - 1) / 2;
        if(index == 0 || value < array[parentIndex])
            return;
        
        swap(index, parentIndex, value);
        bubble(parentIndex, value);
            
    }
        
    private void swap(int index, int parentIndex, int value){
        int temp = array[parentIndex];
        array[parentIndex] = value;
        array[index] = temp;
    }
}
