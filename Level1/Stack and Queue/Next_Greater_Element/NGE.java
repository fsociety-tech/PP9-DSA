import java.util.Arrays;
import java.util.LinkedList;
import java.util.ArrayList;

public class NGE {

    public static int[] NGEonRight(int arr[]) {
        int n = arr.length;
        int ans[] = new int[n];
        Arrays.fill(ans, n - 1);
        LinkedList<Integer> st = new LinkedList<>();
        st.addFirst(-1);
        for (int i = 0; i < n; i++) {
            while (st.getFirst() != -1 && arr[st.getFirst()] < arr[i])
                ans[st.removeFirst()] = i;

            st.addFirst(i);
        }

        return ans;
    }

    public static int[] NSEonRight(int arr[]) {
        int n = arr.length;
        int ans[] = new int[n];
        Arrays.fill(ans, n - 1);
        LinkedList<Integer> st = new LinkedList<>();
        st.addFirst(-1);
        for (int i = 0; i < n; i++) {
            while (st.getFirst() != -1 && arr[st.getFirst()] > arr[i])
                ans[st.removeFirst()] = i;

            st.addFirst(i);
        }

        return ans;
    }

    public static int[] NGEonLeft(int arr[]) {
        int n = arr.length;
        int ans[] = new int[n];
        Arrays.fill(ans, -1);
        LinkedList<Integer> st = new LinkedList<>();
        st.addFirst(-1);
        for (int i = n - 1; i >= 0; i--) {
            while (st.getFirst() != -1 && arr[st.getFirst()] < arr[i])
                ans[st.removeFirst()] = i;

            st.addFirst(i);
        }

        return ans;
    }

    public static int[] NSEonLeft(int arr[]) {
        int n = arr.length;
        int ans[] = new int[n];
        Arrays.fill(ans, -1);
        LinkedList<Integer> st = new LinkedList<>();
        st.addFirst(-1);
        for (int i = n - 1; i >= 0; i--) {
            while (st.getFirst() != -1 && arr[st.getFirst()] > arr[i])
                ans[st.removeFirst()] = i;

            st.addFirst(i);
        }

        return ans;
    }

    // 503. Next Greater Element II
    public static int[] nextGreaterElements(int arr[]) {
        int n = arr.length;
        int ans[] = new int[n];
        Arrays.fill(ans, -1);
        LinkedList<Integer> st = new LinkedList<>();
        st.addFirst(-1);
        for (int i = 0; i < n * 2; i++) {

            while (st.getFirst() != -1 && arr[st.getFirst()] < arr[i % n])
                ans[st.removeFirst()] = arr[i % n];

            if (i < n)
                st.addFirst(i);
        }

        return ans;
    }

    // https://practice.geeksforgeeks.org/problems/stock-span-problem-1587115621/1#
    public static int[] calculateSpan(int[] arr, int n) {
        int[] ans = new int[n];
        LinkedList<Integer> st = new LinkedList<>();
        st.addFirst(-1);

        for (int i = 0; i < n; i++) {
            while (st.getFirst() != -1 && arr[st.getFirst()] <= arr[i])
                st.removeFirst();

            ans[i] = i - st.getFirst();
            st.addFirst(i);
        }

        return ans;
    }

    // leetcode 901

    class StockSpanner {
        int day = 0;
        LinkedList<int[]> st = new LinkedList<>();

        public StockSpanner() {
            // {index, value}
            st.addFirst(new int[] { -1, -1 });
        }

        public int next(int price) {
            while (st.getFirst()[0] != -1 && st.getFirst()[1] <= price)
                st.removeFirst();

            int span = day - st.getFirst()[0];
            st.addFirst(new int[] { day++, price });

            return span;
        }
    }

    // leetcode 20
    public boolean isValid(String str) {
        LinkedList<Character> st = new LinkedList<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '(' || ch == '[' || ch == '{')
                st.addFirst(ch);
            else {
                if (st.size() == 0)
                    return false;
                else if (st.getFirst() == '(' && ch != ')')
                    return false;
                else if (st.getFirst() == '[' && ch != ']')
                    return false;
                else if (st.getFirst() == '{' && ch != '}')
                    return false;
                else
                    st.removeFirst();
            }
        }

        return st.size() == 0;
    }

    // leetcode 739. Daily Temperatures
    public int[] dailyTemperatures(int[] arr) {
        int n = arr.length;
        LinkedList<Integer> st = new LinkedList<>();
        st.addFirst(-1);
        int ans[] = new int[n];
        for (int i = n - 1; i >= 0; i--) {

            while (st.getFirst() != -1 && arr[st.getFirst()] <= arr[i]) {
                st.removeFirst();
            }

            if (st.getFirst() != -1)
                ans[i] = st.getFirst() - i;

            st.addFirst(i);
        }

        return ans;
    }

    // Leetcode 735. Asteroid Collision
    public int[] asteroidCollision(int[] arr) {
        int n = arr.length;
        LinkedList<Integer> st = new LinkedList<>();
        for (int i = 0; i < n; i++) {

            if (arr[i] > 0) {
                st.addFirst(i);
                continue;
            }

            while (st.size() != 0 && arr[st.getFirst()] > 0 && arr[st.getFirst()] < -arr[i])
                st.removeFirst();

            if (st.size() != 0 && arr[st.getFirst()] == -arr[i])
                st.removeFirst();
            else if (st.size() == 0 || arr[st.getFirst()] < 0)
                st.addFirst(i);
        }

        int ans[] = new int[st.size()];
        int idx = ans.length - 1;

        while (st.size() != 0) {
            ans[idx--] = arr[st.removeFirst()];
        }

        return ans;
    }

    // Leetcode 946. Validate Stack Sequences
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        LinkedList<Integer> st = new LinkedList<>();
        int idx = 0;
        for (int i = 0; i < pushed.length; i++) {
            st.addFirst(i);

            while (st.size() != 0 && pushed[st.getFirst()] == popped[idx]) {
                idx++;
                st.removeFirst();
            }

        }

        return st.size() == 0;
    }

    // leetcode 856
    public int scoreOfParentheses(String s) {
        LinkedList<Integer> st = new LinkedList<>();
        st.addFirst(0);

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(')
                st.addFirst(0);
            else {
                int a = st.removeFirst();
                int b = st.removeFirst();

                int val = b + Math.max(2 * a, 1);
                st.addFirst(val);
            }
        }

        return st.removeFirst();
    }

    // Ye revise karni hai class Missed

    // 7n
    public int largestRectangleArea_01(int[] heights) {
        int[] nsol = NSEonLeft(heights); // 3n
        int[] nsor = NSEonRight(heights); // 3n

        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) { // n
            maxArea = Math.max(maxArea, heights[i] * (nsor[i] - nsol[i] - 1));
        }

        return maxArea;
    }

    // 84
    // 2n
    public int largestRectangleArea(int[] heights) {
        int n = heights.length, maxArea = 0;
        LinkedList<Integer> st = new LinkedList<>();
        st.addFirst(-1);

        for (int i = 0; i < n; i++) {
            while (st.getFirst() != -1 && heights[st.getFirst()] >= heights[i]) {
                int h = heights[st.removeFirst()];
                int w = i - st.getFirst() - 1;
                maxArea = Math.max(maxArea, h * w);
            }
            st.addFirst(i);
        }

        while (st.getFirst() != -1) {
            int h = heights[st.removeFirst()];
            int w = n - st.getFirst() - 1;
            maxArea = Math.max(maxArea, h * w);
        }

        return maxArea;
    }

    // 85
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int n = matrix.length, m = matrix[0].length;
        int[] height = new int[m];
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++)
                height[j] = matrix[i][j] == '0' ? 0 : height[j] + 1;
            maxArea = Math.max(maxArea, largestRectangleArea(height));
        }

        return maxArea;
    }

    // 32
    public int longestValidParentheses(String s) {
        int n = s.length(), maxLen = 0;
        LinkedList<Integer> st = new LinkedList<>();
        st.addFirst(-1);

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == ')' && st.getFirst() != -1 && s.charAt(st.getFirst()) == '(') {
                st.removeFirst();
                maxLen = Math.max(maxLen, i - st.getFirst());
            } else
                st.addFirst(i);
        }

        return maxLen;
    }

    // 07/10/2021

    // leetcode 402. Remove K Digits
    public static String removeKdigits(String num, int k) {

        ArrayList<Character> st = new ArrayList<>();

        for (int i = 0; i < num.length(); i++) {
            char ch = num.charAt(i);
            while (st.size() != 0 && st.get(st.size() - 1) > ch && k > 0) {
                k--;
                st.remove(st.size() - 1);
            }

            st.add(ch);
        }

        while (k-- > 0)
            st.remove(st.size() - 1);

        StringBuilder sb = new StringBuilder();
        boolean flag = false;

        for (char ch : st) {
            if (ch == '0' && !flag)
                continue;

            flag = true;
            sb.append(ch);
        }

        return sb.length() != 0 ? sb.toString() : "0";

    }

    // leetcode 316. Remove Duplicate Letters
    public String removeDuplicateLetters(String num) {
        ArrayList<Character> st = new ArrayList<>();

        boolean visited[] = new boolean[26];
        int freq[] = new int[26];

        for (int i = 0; i < num.length(); i++)
            freq[num.charAt(i) - 'a']++;

        for (int i = 0; i < num.length(); i++) {

            char ch = num.charAt(i);

            freq[ch - 'a']--;
            if (visited[ch - 'a'])
                continue;
            while (st.size() != 0 && st.get(st.size() - 1) > ch && freq[st.get(st.size() - 1) - 'a'] > 0) {
                visited[st.get(st.size() - 1) - 'a'] = false;
                st.remove(st.size() - 1);
            }

            visited[ch - 'a'] = true;
            st.add(ch);
        }

        StringBuilder sb = new StringBuilder();

        for (char ch : st) {
            sb.append(ch);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        int arr[] = { 2, 1, 3, 1, 2, 4, 5, 9, 6 };
        int ans[] = NSEonRight(arr);
        for (int e : ans)
            System.out.print(e + " ");
    }

}