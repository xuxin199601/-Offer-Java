package xuxin.demo;

public class Test06_Power {

    public int minNumberInRotateArray(int [] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int left = 0;
        int right = array.length - 1;
        int mid = 0;

        while (array[left] >= array[right]) {
            if (right - left <= 1) {
                mid = right;
                break;
            }
            mid = (left + right) / 2;
            if (array[left] == array[mid] && array[mid] == array[right]) {
                if (array[left+1] != array[right-1]) {
                    mid = array[left+1] < array[right-1] ? left+1:right-1;
                    break;
                } else {
                    left ++;
                    right --;
                }
            } else {
                if (array[left] <= array[mid]) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
        }
        return array[mid];
    }
}
