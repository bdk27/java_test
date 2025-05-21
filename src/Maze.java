public class Maze {
    static int[][] maze = {
            {2, 2, 2, 2, 2, 2, 2},
            {0, 0, 0, 0, 0, 0, 2},
            {2, 0, 2, 0, 2, 0, 2},
            {2, 0, 0, 2, 0, 2, 2},
            {2, 2, 0, 2, 0, 2, 2},
            {2, 0, 0, 0, 0, 0, 2},
            {2, 2, 2, 2, 2, 0, 2}
    };

    static boolean[][] visited = new boolean[maze.length][maze[0].length];

    public static void main(String[] args) {
        if (findPath(1, 0)) {
            System.out.println("成功找到一條路!");
        } else {
            System.out.println("無法到達終點");
        }
        printMaze();
    }

    public static boolean findPath(int x, int y) {
        if (x < 0 || x >= maze.length ||
                y < 0 || y >= maze[0].length ||
                maze[x][y] == 2 || visited[x][y]) {
            return false;
        }
        if (x == 6 && y == 5) {
            maze[x][y] = 3;
            return true;
        }

        visited[x][y] = true;

        if(findPath(x + 1, y) || findPath(x, y + 1) ||
            findPath(x - 1, y) || findPath(x, y - 1)) {
            maze[x][y] = 3;
            return true;
        }

        return false;
    }

    public static void printMaze() {
        for (int[] row : maze) {
            for (int cell : row) {
                if (cell == 2) {
                    System.out.print("[]");
                } else if (cell == 3) {
                    System.out.print("<>");
                } else {
                    System.out.print("[]");
                }
            }
            System.out.println();
        }
    }
}
