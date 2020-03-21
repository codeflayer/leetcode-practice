package javaoffer;

import org.junit.Test;

/**
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 * 示例:
 * 现有矩阵 matrix 如下：
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 * 给定 target = 20，返回 false。
 *
 * 限制：
 * 0 <= n <= 1000
 * 0 <= m <= 1000
 *
 * 思路：和240题一样，从右上角开始找
 */
public class Easy04 {
	public boolean findNumberIn2DArray(int[][] matrix, int target) {
		if (matrix.length==0)return false;
		int i = 0, j = matrix[0].length-1;
		while (i >= 0 && i < matrix.length && j >= 0 && j < matrix[0].length) {
			if (matrix[i][j] == target) return true;
			else if (matrix[i][j] < target) i++;
			else if (matrix[i][j] > target) j--;
		}
		return false;
	}

	@Test
	public void test1() {
		int[][] res = new int[][]{
				{1, 4, 7, 11, 15},
				{2, 5, 8, 12, 19},
				{3, 6, 9, 16, 12},
				{10, 13, 14, 17, 24},
				{18, 21, 23, 26, 30}};
		System.out.println(findNumberIn2DArray(res, 5));
	}
}