import java.util.HashMap;
import java.util.Map;;

class Solution {
    /*
     * 该题为典型的模拟题，难度不大，只需要读懂题目意思，用程序模拟
     * 得到正确答案即可
     * 由于数据规模达到了20000，暴力模拟会导致超时
     * 需要借助哈希表对其进行优化
     */
    public int arrayNesting(int[] nums) {
        int res = 1;
        int N = nums.length;
        Map<Integer,Integer> tep = new HashMap<Integer,Integer>();
        for(int i=0;i<N;i++)
        {
            if(tep.get(nums[i]) != null)
                continue;
            int candicate = nums[i], sum=0;
            while(tep.get(candicate) == null)
            {
                sum ++;
                tep.put(candicate, 1);
                candicate = nums[candicate];
            }
            res = Math.max(res, sum);
            if(res == N) //剪枝
                break;
        }
        return res;
    }

    /*
     * 方法二
     * 不借助哈希表，直接在原数组上标记
     */
    public int arrayNesting2(int[] nums) {
        int res = 1;
        int N = nums.length;
        for(int i=0;i<N;i++)
        {
            if(nums[i] == N)
                continue;
            int candicate = nums[i], sum=0, pre;
            while(nums[candicate] != N)  /*这一行代码是最绕的，candicate代表每个节点值的在数组中的下标*/
            {
                sum ++;
                pre = candicate ;
                candicate = nums[candicate];
                nums[pre] = N;

            }
            res = Math.max(res, sum);
            if(res == N) //剪枝
                break;
        }
        return res;
    }
}