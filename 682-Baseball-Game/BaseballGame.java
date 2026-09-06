import java.util.Stack;

class BaseballGame {
    public int calPoints(String[] operations) {
        int n = operations.length;
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            if (operations[i].equals("C")) {
                st.pop();
            } else if (operations[i].equals("D")) {
                st.push(st.peek() * 2);
            } else if (operations[i].equals("+")) {
                int first = st.pop();
                int second = st.peek();

                st.push(first);
                st.push(first + second);
            } else {
                st.push(Integer.parseInt(operations[i]));
            }
        }

        int ans = 0;

        while (!st.isEmpty()) {
            ans += st.pop();
        }

        return ans;
    }
}