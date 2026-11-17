
class Solution {
    public boolean findPattern(String s) {
        int i = 0;
        int n = s.length();

        while (i < n) {
            int x = 0;
            int y = 0;

            // Count consecutive x's
            while (i < n && s.charAt(i) == 'x') {
                x++;
                i++;
            }

            // Count consecutive y's
            while (i < n && s.charAt(i) == 'y') {
                y++;
                i++;
            }

            if (x == 0 || x != y) {
                return false;
            }
        }

        return true;
    }
}
