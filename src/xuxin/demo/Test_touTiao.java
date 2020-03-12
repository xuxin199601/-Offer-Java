package xuxin.demo;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Test_touTiao {

    // 定义每个传输任务的数据结构
    public static class Task {
        // 任务的到达时间
        int arriveTime;
        // 任务中需要上传的任务数量
        int fileNum;

        public Task(int arriveTime, int fileNum) {
            this.arriveTime = arriveTime;
            this.fileNum = fileNum;
        }
    }

    public static void main(String[] args) {

        // 输入
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Task> tasks = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int arriveTime = sc.nextInt();
            int fileNum = sc.nextInt();
            tasks.add(new Task(arriveTime, fileNum));
        }

        // 根据任务到达的时间顺序进行排序
        tasks.sort(new Comparator<Task>() {
            @Override
            public int compare(Task task1, Task task2) {
                return task1.arriveTime > task2.arriveTime ? 1 : -1;
            }
        });

        int time1 = tasks.get(0).arriveTime;
        // 下次任务到达的时间
        int time2;
        // 剩余需要上传的任务数量
        long restNum = tasks.get(0).fileNum;
        // 积累的最大文件数量
        long maxRestNum = tasks.get(0).fileNum;

        for (int i = 1; i < n; i++) {
            // 此次任务的到达时间
            time2 = tasks.get(i).arriveTime;

            // 与上次任务的相差时间，即为此段时间内可以上传的文件数
            int diffTime = time2 - time1;
            // 上次任务在此段时间内是否可以传输完成
            if (diffTime > restNum) {
                // 在相隔时间内完成，此次剩余文件数 = 此次任务新增的任务数
                restNum = tasks.get(i).fileNum;
            } else {
                // 在相隔时间内未完成，此次剩余文件数 = 上次剩余文件数 - 完成的文件数 + 此次任务新增的任务数
                restNum = restNum - diffTime + tasks.get(i).fileNum;
            }

            // 积累的最大文件数量,即每次剩余任务数量中的最大值
            if (maxRestNum < restNum) {
                maxRestNum = restNum;
            }

            // 重置，便于下次迭代有相同的计算逻辑
            time1 = time2;
        }

        // 最终的完成时间 = 最后一次任务到达时间 + 此前剩余的文件数需要的时间
        long finishTime = tasks.get(n-1).arriveTime + restNum;

        System.out.println(finishTime + " " + maxRestNum);
    }
}
