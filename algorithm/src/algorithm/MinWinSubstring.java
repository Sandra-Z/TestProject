package algorithm;

/**
 * 给定一个字符串S和T，在S中找到一个最小的子串包含T中的所有字符，时间复杂度为O(n)。
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 * 举例:
 * S = "ADOBECODEBANC"
 * T = "ABC"
 * 最小子串是"BANC".
 * @author zz
 *
 */

public class MinWinSubstring {
	public String minWindow(String T, String S) {
		int[]srcHash = new int[128];
		for (int i=0; i<T.length(); i++) {
			srcHash[T.charAt(i)]++;
		}
		int[]destHash = new int[128];
		int i = 0, start = 0, found = 0;
		int begin = -1, end = S.length(), minLength = S.length();
		
		for (start = 0, i = 0; i < S.length(); i++) {
			destHash[S.charAt(i)]++;
			if (destHash[S.charAt(i)] <= srcHash[S.charAt(i)]) found++;
			if (found == T.length()) {
				while (start < i && destHash[S.charAt(start)] > srcHash[S.charAt(start)]) {
					destHash[S.charAt(start)]--;
					start++;
				}
				if (i - start < minLength) {
					minLength = i - start;
					begin = start;
					end = i;
				}
				destHash[S.charAt(start)]--;
				start++;
				found--;
			}
		}
		return begin == -1 ? "" : S.substring(begin, end + 1);
	}
	
	public static void main(String[] args) {
		MinWinSubstring solution = new MinWinSubstring();
		String T = "ABC";
		String S = "ADOBECODEBANC";
		System.out.println(solution.minWindow(T, S));
	}
	
}
