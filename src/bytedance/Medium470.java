package bytedance;

import org.junit.Test;

/**
 * 已有方法 rand7 可生成 1 到 7 范围内的均匀随机整数，试写一个方法 rand10 生成 1 到 10 范围内的均匀随机整数。
 * <p>
 * 不要使用系统的 Math.random() 方法。
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1
 * 输出: [7]
 * 示例 2:
 * <p>
 * 输入: 2
 * 输出: [8,4]
 * 示例 3:
 * <p>
 * 输入: 3
 * 输出: [8,1,10]
 * <p>
 * 提示:
 * <p>
 * rand7 已定义。
 * 传入参数: n 表示 rand10 的调用次数。
 * 进阶:
 * <p>
 * rand7()调用次数的 期望值 是多少 ?
 * 你能否尽量少调用 rand7() ?
 * <p>
 * 思路：拒绝采样
 * 现在我们有rand7()相当于一个七面的色子，我想要rand10()相当于10面的色子，因为10>7，所以无法把7映射到10，只能执行两遍rand7()，
 * 那么我们就有了7*7=49种组合，就可以想办法映射到1-10中去了。
 * 因为这49种组合的概率是相同的，所以为了映射到10个数中去，我们取其中40个结果，即1到40。
 * 所以，可以定义：（1,1）=1.（1,2）=2，（1,3）=3，以此类推直到（6,5）=10。
 * 可能会问，（2,3）=10了，为啥不到这停止呢？
 * 因为这样做对于49种可能来说，相当于拒绝了39种，而取到（6,5）=10，只拒绝了9种，显然后者执行更少的rand7就可以得到结果，效率更高
 * <p>
 * 两次结果对应序号的公式为：ind=(r1-1)*7+r2（画一个二维矩阵很容易总结）,r1r2为两次执行rand7的结果
 * 判断，如果ind>40，则重新掷骰子。
 * 直到满足结果，返回.
 */
public class Medium470 {
    public int rand10() {
        int row, col, idx;
        do {
            row = rand7();
            col = rand7();
            idx = col + (row - 1) * 7;
        } while (idx > 40);
        return 1 + (idx - 1) % 10;
    }
    //*********************************************方法2****************************************

    /**
     * 该题的核心就是多次掷筛子，找到多次的结果与序号的映射关系，拒绝掉一些无法计算的结果
     * 下面代码，更加清晰
     * ind=(r1-1)*7+r2是两次结果对1-49的映射
     * 而现在ind=(r1-1)*7+(r2-1)是两次结果对0-48的映射，好处就是反算映射时不用复杂的加1减1了
     */
    public int rand10II() {
        int result = 40;
        while (result >= 40) {
            result = 7 * (rand7() - 1) + (rand7() - 1);
        }
        return result % 10 + 1;//result映射范围是（0，9），所以最后再加1就是（1,10）
    }

    private int rand7() {//已有方法
        return (int) (Math.random() * 7 + 1);
    }

    @Test
    public void test1() {
        System.out.println(rand10());
    }
}