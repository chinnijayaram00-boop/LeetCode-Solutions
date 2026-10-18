class Solution {
    public int[] lastVisitedCell(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int r = 0;
        int c = 0;
        visited[r][c] = true;
        while (true) {
            char dir = grid[r][c];
            grid[r][c] = '#';
            int nr = r, nc = c;
            switch (dir) {
                case 'L':
                    nc--;
                    break;
                case 'R':
                    nc++;
                    break;
                case 'U':
                    nr--;
                    break;
                case 'D':
                    nr++;
                    break;
            }
            if (nr < 0 || nr >= m || nc < 0 || nc >= n || grid[nr][nc] == '#') {
                return new int[]{r, c};
            }
            r = nr;
            c = nc;
            visited[r][c] = true;
        }
    }
}