/*class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] cnt = new int[n + 1];
        for (int[] item : items) {
            cnt[item[0]]++;
        }
        long[] mult = new long[n + 1];
        for (int f = 1; f <= n; f++) {
            for (int m = f; m <= n; m += f) {
                mult[f] += cnt[m];
            }
        }
        List<long[]> bonus = new ArrayList<>();
        long cheapest = Long.MAX_VALUE;
        for (int[] item : items) {
            int factor = item[0];
            long price = item[1];
            cheapest = Math.min(cheapest, price);
            long gain = mult[factor] - 1;
            if (gain > 0) {
                bonus.add(new long[] {price, gain});
            }
        }
        bonus.sort((a, b) -> Long.compare(a[0], b[0]));
        long ans = 0;
        long rem = budget;
        for (long[] cur : bonus) {
            long price = cur[0];
            long gain = cur[1];
            if (rem < price) break;
            long take = Math.min(gain, rem / place);
            ans += 2L * take;
            rem -= take * price;
        }
        ans += rem / cheapest;
        return (int) ans;
    }
}*/
import java.util.Arrays;
class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int minPrice = Integer.MAX_VALUE;
        int[] freq = new int[n + 1];
        for (int i = 0; i < n; i++) {
            freq[items[i][0]]++;
            if (items[i][1] < minPrice) {
                minPrice = items[i][1];
            }
        }
        int[] multiplesCount = new int[n + 1];
        for (int f = 1; f <= n; f++) {
            if (freq[f] > 0) {
                int sum = 0;
                for (int m = f; m <= n; m += f) {
                    sum += freq[m];
                }
                multiplesCount[f] = sum;
            }
        }
        long[] sample2 = new long[n];
        int sample2Count = 0;
        for (int i = 0; i < n; i++) {
            int f = items[i][0];
            int p = items[i][1];
            int d = multiplesCount[f] - 1;   
            if (d > 0 && p < 2L * minPrice) {
                sample2[sample2Count++] = ((long) p << 32) | d;
            }
        }
        Arrays.sort(sample2, 0, sample2Count);
        long ans = 0;
        long remBudget = budget;
        for (int i = 0; i < sample2Count; i++) {
            long p = sample2[i] >>> 32;
            long d = sample2[i] & 0xFFFFFFFFL;   
            if (remBudget >= p) {
                long take = Math.min(d, remBudget / p);
                ans += 2L * take;
                remBudget -= take * p;
            }
        }
        ans += remBudget / minPrice;  
        return (int) ans;
    }
}