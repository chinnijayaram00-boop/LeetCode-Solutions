
import java.util.*;

class Solution {
    public ArrayList<Integer> pattern(int n) {
        ArrayList<Integer> ans = new ArrayList<>();

        if (n <= 0) {
            ans.add(n);
            return ans;
        }

        subtract(n, n, ans);
        return ans;
    }

    void subtract(int current, int original, ArrayList<Integer> ans) {
        ans.add(current);

        if (current <= 0) {
            add(current + 5, original, ans);
            return;
        }

        subtract(current - 5, original, ans);
    }

    void add(int current, int original, ArrayList<Integer> ans) {
        ans.add(current);

        if (current == original) {
            return;
        }

        add(current + 5, original, ans);
    }
}
