package work;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

public class bigNumberMultiply {

	/*
	 * 模拟小数乘法
	 */

	public static Integer[] simulateMultiply(int[] num1, int[] num2) {

		ArrayList<Integer> result = new ArrayList<Integer>();		//返回最终计算结果

		for (int j = num2.length - 1; j >= 0; j--) {

			int carry = 0; // 记录进位
			ArrayList<Integer> singleCal = new ArrayList<Integer>();

			for (int i = num1.length - 1; i >= 0; i--) { // 单次乘
				int sum_1 = num1[i] * num2[j] + carry;
				singleCal.add(sum_1 % 10);
				carry = sum_1 / 10;
			}
			if (carry != 0)
				singleCal.add(carry);

			ArrayList<Integer> middle = new ArrayList<Integer>(); // 记录单次计算后的累加值
			int offset = num2.length - 1 - j;
			int count = 0;
			int cnt_p = 0, cnt_q = 0;
			int carryResult = 0;
			// 单次计算结果累加
			while (cnt_p < singleCal.size() || cnt_q < result.size()) {
				int val_p = 0, val_q = 0;
				if (cnt_p < singleCal.size() && count >= offset) {		//获取单次乘的结果
					val_p = singleCal.get(cnt_p++);
				}

				if (cnt_q < result.size()) {			//获取累加的结果
					val_q = result.get(cnt_q++);
				}

				int sum_2 = val_p + val_q + carryResult;
				middle.add(sum_2 % 10);
				carryResult = sum_2 / 10;

				count++;
			}

			if (carryResult != 0)
				middle.add(carryResult);
			result.clear();					//累加后的值更新result
			result = middle;
		}
		Collections.reverse(result);
		return result.toArray(new Integer[result.size()]);
	}

	
	/*
	 * 对模拟小数乘法的改进，最后累加阶段记录进位
	 */
	
	public static int[] simulateMultiply2(int[] num1, int[] num2) {
		
		
		int[] result = new int[num1.length+num2.length];
		for(int i=0; i<num1.length; i++) {
			for(int j=0; j<num2.length; j++) {
				result[i+j+1] += num1[i]*num2[j];
			}
		}
		
		for(int i=result.length-1; i>0; i--) {
			if(result[i]>10) {
				result[i-1] += result[i]/10;
				result[i] %= 10;
			}
		}
		
		return result;
	}
	
	
	
	/*
	 * 
	 * java的大数乘法
	 */
	
	public static BigInteger multiply(String num1, String num2) {
		
		BigInteger bi1 = new BigInteger(num1);
		BigInteger bi2 = new BigInteger(num2);
		BigInteger result = bi1.multiply(bi2);
		return result;
		
	}
	
	
	
	
	public static void main(String[] args) {
	 
		
		String str1="17394569031739456903173945690375834659017359347892";
		String str2="122369878345683065782346598346";
		char[] t1=str1.toCharArray();
		char[] t2=str2.toCharArray();
		int[] num1=new int[t1.length];
		int[] num2=new int[t2.length];
		for(int i=0; i<t1.length; i++) num1[i] = t1[i]-'0';
		for(int i=0; i<t2.length; i++) num2[i] = t2[i]-'0';
		
	/*	Integer[] t = simulateMultiply(num1, num2);
		for (int i = 0; i < t.length; i++) {
			if (i % 3 == 0 && i != 0)
				System.out.print(",");
			System.out.print(t[i]);
		}*/
		/*
		int[] result = simulateMultiply2(num1, num2);
		int i=0;
		while(result[i++]==0);
		for(i=i-1; i<result.length; i++) {
			if(i%3==0) System.out.print(",");
			System.out.print(result[i]);
		}*/
		
		
		BigInteger bResult = multiply(str1, str2);
		System.out.println(bResult);
			
		

	}

}
