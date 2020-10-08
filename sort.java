package com.exam;

public class sort {
    //冒泡排序
    public static int[] bubbleSort(int[] nums){
        int len = nums.length;
        for(int i=0;i<len-1;i++){
            for(int j=i+1;j<len;j++){
                if(nums[j]<nums[i]){  //每次都与i 相比，满足条件就交换
                    int tem = nums[i];
                    nums[i]=nums[j];
                    nums[j]=tem;
                }
            }
        }
        return nums;
    }
    //插入排序
    public static int[] insertSort(int[] nums) {
        int len = nums.length;
        //在有序数组中进行插入
        for(int i=1;i<len;i++){
            int cur = nums[i];
            int index = i;
            for(int j=i-1;j>=0;j--) {
                if(cur<nums[j]){
                    nums[j+1]=nums[j];
                    index = j;
                }else{
                    break;
                }
            }
            nums[index] = cur;
        }

        return nums;
    }

    //选择排序
    public static int[] selectSort(int[] nums) {
//        每次都选择一个最大或者最小的进行排序
        int len = nums.length;
        for(int i=0;i<len-1;i++){
            int minIndex = i;
            for(int j=i+1;j<len;j++){
                if(nums[j]<nums[minIndex]){
                    minIndex = j;
                }
            }
            int tem = nums[i];
            nums[i] = nums[minIndex];
            nums[minIndex] = tem;
        }
        return nums;
    }

    //希尔排序
    public static int[] shellSort(int[] nums) {
        int len = nums.length;
        for(int gap = len/2;gap>0;gap = gap/2) {
            for (int i = gap; i < len; i++) {
                int index = i;
                int cur = nums[index];
                while (index-gap>=0 && cur < nums[index - gap]) {
                    nums[index] = nums[index - gap];
                    index = index - gap;
                }
                nums[index] = cur;
            }
        }
        return nums;
    }

    //归并排序
    public static int[] mergeSort(int[] nums) {
        mergeSort(nums,0,nums.length-1);
        return nums;
    }
    //将数组拆分成两部分
    public static void mergeSort(int[] nums,int start,int end){
        if(start>=end){
            return;
        }
        int mid = (end-start)/2 + start;
//        System.out.println("mid:"+mid);
        mergeSort(nums,start,mid);
        mergeSort(nums,mid+1,end);
        merge(nums,start,mid,end);
    }
    //合并两个有序数组
    public static void merge(int[] nums,int start,int mid,int end){
        int []temp = new int[end-start+1];//临时数组记录数据
        int i=start,j=mid+1;
        int k = 0; //记录temp复制到哪里了
        for(;k<temp.length && i<=mid && j<=end;k++){
            if(nums[i]<=nums[j]){
                temp[k]=nums[i];
                i++;
            }else{
                temp[k]=nums[j];
                j++;
            }
        }
        //将剩余的（比另一边多出来的个数）放到temp数组中
        for(;j>end && i<=mid && k<temp.length;k++){
            temp[k]=nums[i];
            i++;
        }
        for(;j<=end && i>mid && k<temp.length;k++){
            temp[k]=nums[j];
            j++;
        }
        //将temp复制到nums中
        k=0;i=start;
        for(;k<temp.length;k++){
            nums[i]=temp[k];
            i++;
        }
    }

    //快速排序
    public static int[] quickSort(int[] nums) {
        quickSort(nums,0,nums.length-1);
        return nums;
    }
    public static void quickSort(int[] nums,int start,int end){
        if(start>=end){
            return;
        }
       /* for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println(" "); */
        int p = (end-start)/2+start;
        int i = partition(nums,start,end,p);
//        int i = partition(nums,start,end);
        quickSort(nums, start, i-1);
        quickSort(nums,i+1,end);
    }
    //需要额外空间
    public static int partition(int [] nums,int start,int end,int p){
        int len = end-start+1;
        int [] tem = new int[len];  //需要额外的空间
        int i=0;
        for(int j=len-1,k=start;k<=end;k++){
            if(k==p){
                continue;
            }
            if(nums[k]>nums[p]){   //比基准值大的放大数组后面
                tem[j]=nums[k];
                j--;
            }else{
                tem[i]=nums[k];     //小的放到数组前面
                i++;
            }
        }
        tem[i] = nums[p];
        for(int j=0;j<len;j++){   //拷贝
            nums[start+j]=tem[j];
        }
        return i+start;   //返回基准所在的位置
    }
    //不用额外空间
    public static int partition(int []nums,int start,int end){
        int temp = nums[start];
        while(start<end){
            while(nums[end]>=temp && start<end){ //从后向前，比基准大跳过
                end--;
            }
            if(start<end){                     //比基准小赋值
                nums[start] = nums[end];
                start++;
            }
            while( nums[start]<=temp && start<end){//从前往后，比基准小跳过
                start++;
            }
            if(start<end){
                nums[end] = nums[start];
                end--;
            }
        }
        nums[start]=temp;
        return start;
    }
}
