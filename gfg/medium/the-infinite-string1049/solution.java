
class Solution {

    public char posInfinite(String s, int pos) {

        long n = s.length();

        java.util.ArrayList<Long> len = new java.util.ArrayList<>();

        len.add(n);

        long i = 1;

        while (len.get(len.size() - 1) < pos) {
            long L = len.get(len.size() - 1);
            len.add(2 * L + i);
            i++;
        }

        for (int j = len.size() - 1; j >= 1; j--) {

            long L = len.get(j - 1);

            if (pos <= L) {
                continue;
            }
            else if (pos <= L + j) {
                return '$';
            }
            else {
                pos = (int)(2 * L + j - pos + 1);
            }
        }

        return s.charAt(pos - 1);
    }
}

