package edu.isistan.mergesort;

public class MergeSortUtils {

    public static void mergeSort(int[] array) {
        mergeSort(array, 0, array.length-1);
    }

    public static void mergeSort(int[] array, int i, int max) {
        if (i == max)
            return;
        int mid = (max + i)/2;
        mergeSort(array, i, mid);
        mergeSort(array, mid + 1, max);
        
        merge(array, i, mid, max);
    }

    public static void merge(int[] array, int i, int mid, int max) {
        int[] tmp = new int [max - i + 1];
        int k = 0;
        int left = i; 
        int right = mid + 1;
        while((left <= mid) && (right <= max)) {
            if(array[left]<=array[right])
                tmp[k++] = array[left++];
            else
                tmp[k++] = array[right++];
        }
        while(left <= mid)  {
            tmp[k++] = array[left++];
        }
        while(right <= max) {
            tmp[k++] = array[right++];
        }
        for(int j = 0; j < tmp.length; j++)
            array[i+j] = tmp[j];
    }
}
