# sort
[六大排序算法](http://note.youdao.com/s/Wfkl4fj8)
### 排序算法
+ #### 冒泡排序
> 双重循环，只要当前值比选定的值就交换
> 第一重循环遍历数组（0到len-1），第二重循环将数组中每个元素都与第一重循环中的值相比，如小，则交换顺序，直到最后一个元素，然后进行下轮循环。
> 时间复杂度 O(n^2)，空间复杂度O(1)
``` 
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
 ```
+ #### 选择排序
> 双重循环，每次都找出一个最大或最小的数的下标，然后交换
> 第一重循环遍历数组，第二重循环将选出数组中最小元素的下标，最后才交换。
```
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
```
>  时间复杂度 O(n^2)，空间复杂度O(1)；
>  **冒泡排序每次只要满足条件就交换，选择排序只是先找出最大或最小的元素的下标，最后才交换。所以冒泡排序最大可能交换次数为n^2，而选择排序交换的次数为n。**
+ #### 插入排序 
> 假设该数组已经有序，从当前需要排序的前一个元素开始一一比较，只要大于当前元素就后移，直到找到插入的位置。
> 可以从数组中第二个元素开始插入，之后每次都假设数组有序。
```
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
```
>  时间复杂度 O(n^2)，空间复杂度O(1)；

+ #### 希尔排序
> 本质是分组插入排序，通过设置增量来进行分组，然后对分组进行插入排序。先增量设置为len/2，len/4，直到增量为1。
> 在实际代码中，可以不考虑分组情况，直接从gap到len进行处理，只是每次跳动gap。
```
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
```

+ #### 归并排序
> 先拆分再合并
> 使用分而治之的思想，将数组分成两部分，直到一侧只有一个元素，此时该小数组必然有序，然后将两有序数组合并成一个大的有序数组，然后继续合并。
> 采用二分的思想，时间复杂度O(nlogn)，空间复杂度O(n)，需要临时空间存放处理后的数据。 
```
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
```

+ #### 快速排序
> 采用分治的思想，通过选取一个基准，比基准大的都放到基准的右边，比基准小的放到左边，然后对左右两边分别进行选基，移动递归操作。这种做法需要额外的数组空间
> 如果不能引入额外的空间，我们可以采用左右交替扫描的方法，先从后往前扫，直到找到第一个比基准小的值，移动到前面，然后从前往后扫描。
```
    //法1：需要额外空间 
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
  
    //法2：不需要额外空间
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

