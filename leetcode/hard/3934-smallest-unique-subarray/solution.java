
import java.util.*;

class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        int[] sa = buildSuffixArray(nums);
        int[] lcp = buildLCP(nums, sa);

        int ans = n + 1;

        for (int r = 0; r < n; r++) {
            int maxLCP = lcp[r];

            if (r + 1 < n) {
                maxLCP = Math.max(maxLCP, lcp[r + 1]);
            }

            int start = sa[r];
            int len = maxLCP + 1;

            if (len <= n - start) {
                ans = Math.min(ans, len);
            }
        }

        return ans;
    }

    private int[] buildSuffixArray(int[] nums) {
        int n = nums.length;
        int N = n + 1;

        int[] p = new int[N];
        int[] c = new int[N];

        // Add sentinel 0 at the end.
        int[] a = new int[N];

        for (int i = 0; i < n; i++) {
            a[i] = nums[i];
        }

        int max = Math.max(N, 100002);
        int[] cnt = new int[max];

        for (int x : a) {
            cnt[x]++;
        }

        for (int i = 1; i < max; i++) {
            cnt[i] += cnt[i - 1];
        }

        for (int i = N - 1; i >= 0; i--) {
            p[--cnt[a[i]]] = i;
        }

        int classes = 1;
        c[p[0]] = 0;

        for (int i = 1; i < N; i++) {
            if (a[p[i]] != a[p[i - 1]]) {
                classes++;
            }
            c[p[i]] = classes - 1;
        }

        int[] pn = new int[N];
        int[] cn = new int[N];

        for (int len = 1; len < N; len *= 2) {

            for (int i = 0; i < N; i++) {
                pn[i] = p[i] - len;
                if (pn[i] < 0) {
                    pn[i] += N;
                }
            }

            Arrays.fill(cnt, 0, classes, 0);

            for (int x : pn) {
                cnt[c[x]]++;
            }

            for (int i = 1; i < classes; i++) {
                cnt[i] += cnt[i - 1];
            }

            for (int i = N - 1; i >= 0; i--) {
                int x = pn[i];
                p[--cnt[c[x]]] = x;
            }

            cn[p[0]] = 0;
            int newClasses = 1;

            for (int i = 1; i < N; i++) {
                int cur = p[i];
                int prev = p[i - 1];

                int mid1 = (cur + len) % N;
                int mid2 = (prev + len) % N;

                if (c[cur] != c[prev] ||
                    c[mid1] != c[mid2]) {
                    newClasses++;
                }

                cn[cur] = newClasses - 1;
            }

            int[] temp = c;
            c = cn;
            cn = temp;

            classes = newClasses;

            if (classes == N) {
                break;
            }

            if (len > N / 2) {
                break;
            }
        }

        // Remove the sentinel suffix.
        int[] sa = new int[n];
        int idx = 0;

        for (int x : p) {
            if (x != n) {
                sa[idx++] = x;
            }
        }

        return sa;
    }

    private int[] buildLCP(int[] nums, int[] sa) {
        int n = nums.length;

        int[] rank = new int[n];
        int[] lcp = new int[n];

        for (int i = 0; i < n; i++) {
            rank[sa[i]] = i;
        }

        int h = 0;

        for (int i = 0; i < n; i++) {
            int r = rank[i];

            if (r == 0) {
                continue;
            }

            int j = sa[r - 1];

            while (i + h < n &&
                   j + h < n &&
                   nums[i + h] == nums[j + h]) {
                h++;
            }

            lcp[r] = h;

            if (h > 0) {
                h--;
            }
        }

        return lcp;
    }
}

