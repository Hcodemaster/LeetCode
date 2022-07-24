import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class Solution {
    class  LinkNode
    {
        int val;
        LinkNode next;
        public LinkNode(int val)
        {
            this.val = val;
            next = null;
        }
    } 
    public boolean sequenceReconstruction(int[] nums, int[][] sequences) {
        int N = nums.length;
        LinkNode[] graph = new LinkNode[N+1];
        for(int i=0;i<N;i++)
        {
            graph[i+1] = new LinkNode(i+1);
        }
        for(int i=0;i<sequences.length;i++)
        {
            for(int j=0;j<sequences[i].length;j++)
            {
                if(j+1<sequences[i].length)
                {
                    LinkNode node = new LinkNode(sequences[i][j+1]);
                    node.next = graph[sequences[i][j]].next;
                    graph[sequences[i][j]].next = node;
                }
            }
        }
        //进入拓扑排序过程
        int[] visit = new int[N+1];//创建标识数组，标识每个排序出的节点
        int sum = 0;//已经排序的节点数目
        int[] in_degree = new int[N+1];//统计每个节点的入度
        for(int i=1;i<=N;i++)
        {
            if(visit[i] == 1)
                continue;
            LinkNode p = graph[i];
            while(p.next != null)
            {
                in_degree[p.next.val] ++;
                p = p.next;
            }
        }
        while(true)
        {
            int cnt = 0;//统计入度为0的节点的个数
            int candicate = 0;//候选节点
            for(int i =1 ;i<=N;i++)
            {
                if(visit[i] == 1)
                    continue;
                if(in_degree[i] == 0)
                {
                    cnt++;
                    candicate = i;
                }
            }
            if(cnt > 1)
                return false;
            else  //选出该候选节点，同时标记
            {
                System.out.println(candicate);
                visit[candicate] = 1;
                //更新所有节点的入度
                LinkNode tep = graph[candicate];
                while(tep.next != null)
                {
                    in_degree[tep.next.val] --;
                    tep = tep.next;
                }
                sum ++;
            }
            if(sum == N)
                break;
        }
        return true;
    }
    /*第二种拓扑排序的解法
     * 存储节点之间的关系时不用链表
     * 直接使用Set集合存储
     * 通过队列保存每一个入度为0的节点
     */
    public boolean sequenceReconstruction2(int[] nums, int[][] sequences) {
        int N = nums.length;
        Set<Integer>[] garph = new Set[N+1];
        for(int i=1;i<=N;i++)
        {
            garph[i] = new HashSet<>();
        }
        int[] in_degree = new int[N+1];
        for(int[] sequence : sequences)//根据sequences构造图并计算所有节点的入度
        {
            for(int i=0;i<sequence.length-1;i++)
            {
                garph[sequence[i]].add(sequence[i+1]);
                in_degree[sequence[i+1]] ++ ;
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i=1;i<=N;i++)
        {
            if(in_degree[i] == 0)
                queue.add(i);
        }
        while(queue.size()>0)
        {
            if(queue.size()>1)
                return false;
            int candicate = queue.poll();//得到拓扑排序的节点
            for(Integer val : garph[candicate])//更新剩余节点的入度值
            {
                in_degree[val] --;
                if(in_degree[val] == 0)
                    queue.offer(val);
            }
        }
        return true;
    }
}