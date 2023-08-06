package programmers;
import java.util.LinkedList;
import java.util.Queue;

class Position {
    int x;
    int y;
    int answer;
    Position(int x, int y, int answer) {
        this.x = x;
        this.y = y;
        this.answer = answer;
    }
}

public class bfs_ricochet_robot {
    public static void main(String[] args) {
        String[] boards = {"...D..R", ".D.G...", "....D.D", "D....D.", "..D...."};
        int answer = solution(boards);
        System.out.println(answer);
    }

    static int solution(String[] boards) {
        Position start = null;
        int i;
        int j;
        for (i = 0; i < boards.length; i++) {
            for (j = 0; j < boards[0].length(); j++) {
                if (boards[i].charAt(j) == 'R') {
                    start = new Position(i, j, 0);
                }
            }
        }

        return bfs(boards, start);
    }
    static int bfs(String[] boards, Position start) {
        Queue<Position> queue = new LinkedList<>();
        queue.add(start);
        boolean[][] visited = new boolean[boards.length][boards[0].length()];
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        visited[start.x][start.y] = true;
        while (!queue.isEmpty()) {
            Position now = queue.poll();
            for (int i = 0; i < 4; i++) {
                Position next = move(now, dx[i], dy[i], boards);
                System.out.println(next.x);
                System.out.println(next.y);
                if (boards[next.x].charAt(next.y) == 'G') {
                    return next.answer;
                } else if (!visited[next.x][next.y]){
                    visited[next.x][next.y] = true;
                    queue.add(next);
                }
            }
        }

        return -1;
    }

    static Position move (Position now, int dx, int dy, String[] boards) {
        int x = now.x;
        int y = now.y;
        while ( 0 <= x + dx  && x + dx < boards.length && 0 <= y + dy && y + dy < boards[0].length() &&  boards[x + dx].charAt(y + dy) != 'D') {
            x += dx;
            y += dy;
        }
        return new Position(x, y, now.answer + 1);
    }
}
