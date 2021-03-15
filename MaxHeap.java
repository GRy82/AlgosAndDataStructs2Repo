public class MaxHeap {
    public static void heapify(int[] numbers){
        int lastParentIndex = numbers.length / 2 - 1;
        for (int i = lastParentIndex; i >= 0; i--)
            heapify(numbers, i);
    }

    private static void heapify(int[] numbers, int index){
        var largerIndex = index;

        var leftIndex = index * 2 + 1;
        if(leftIndex < numbers.length && numbers[leftIndex] > numbers[largerIndex])
            largerIndex = leftIndex;
        
        var rightIndex = index * 2 + 2;
        if(rightIndex < numbers.length && numbers[rightIndex] > numbers[largerIndex])
            largerIndex = rightIndex;

        if (largerIndex == index)
            return;

        swap(numbers, index, largerIndex);
        heapify(numbers, largerIndex);
    }

    private static void swap(int[] numbers, int firstIndex, int secondIndex){
        int temp = numbers[firstIndex];
        numbers[firstIndex] = numbers[secondIndex];
        numbers[secondIndex] = temp;
    }

}

