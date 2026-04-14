import java.util.ArrayList;
import java.util.List;

public class Sudoku {

    int[][] board = new int[9][9];

    public static void main(String[] args) {
        Sudoku s = new Sudoku();
        s.fillBoard();
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) System.out.println("-------------------------");
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0) System.out.print("| ");
                System.out.print(s.board[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("-------------------------");
    }

    public boolean fillBoard() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    List<Integer> nums = getNum();
                    for (int num : nums) {
                        if (isValid(row, col, num)) {
                            board[row][col] = num;
                            if (fillBoard()) return true;
                            board[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private List<Integer> getNum() {
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= 9; i++) nums.add(i);
        for (int i = nums.size() - 1; i > 0; i--) {
            int j = (int)(Math.random() * (i + 1));
            int temp = nums.get(i);
            nums.set(i, nums.get(j));
            nums.set(j, temp);
        }
        return nums;
    }

    public boolean isValid(int row, int col, int num) {
        return checkRow(row, num) && checkCol(col, num) && checkBox(row, col, num);
    }

    public boolean checkRow(int row, int num) {
        for (int col = 0; col < 9; col++) {
            if (board[row][col] == num) return false;
        }
        return true;
    }

    public boolean checkCol(int col, int num) {
        for (int row = 0; row < 9; row++) {
            if (board[row][col] == num) return false;
        }
        return true;
    }

    public boolean checkBox(int row, int col, int num) {
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[startRow + i][startCol + j] == num) return false;
            }
        }
        return true;
    }
}