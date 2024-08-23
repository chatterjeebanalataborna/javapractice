public class soduku {

    public boolean isSafe(char[][] board, int row, int col, int number) {
        // Column check
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == (char) (number + '0')) {
                return false;
            }
        }
        
        // Row check
        for (int j = 0; j < board.length; j++) {
            if (board[row][j] == (char) (number + '0')) {
                return false;
            }
        }
        
        // 3x3 Subgrid check
        int sr = 3 * (row / 3);
        int sc = 3 * (col / 3);
        for (int i = sr; i < sr + 3; i++) {
            for (int j = sc; j < sc + 3; j++) {
                if (board[i][j] == (char) (number + '0')) {
                    return false;
                }
            }
        }
        
        return true;
    }

    public boolean helper(char[][] board, int row, int col) {
        if (row == board.length) {
            return true;
        }
        
        int nrow = 0;
        int ncol = 0;
        
        if (col == board.length - 1) {
            nrow = row + 1;
            ncol = 0;
        } else {
            nrow = row;
            ncol = col + 1;
        }
        
        if (board[row][col] != '.') {
            if (helper(board, nrow, ncol)) {
                return true;
            }
        } else {
            for (int i = 1; i <= 9; i++) {
                if (isSafe(board, row, col, i)) {
                    board[row][col] = (char) (i + '0');
                    if (helper(board, nrow, ncol)) {
                        return true;
                    } else {
                        board[row][col] = '.';
                    }
                }
            }
        }
        
        return false;
    }

    public void solveSudoku(char[][] board) {
        helper(board, 0, 0);
    }

    public static void main(String[] args) {
        char[][] board = {
            {'.', '.', '8', '.', '.', '.', '.', '.', '.'},
            {'4', '9', '.', '1', '5', '7', '.', '.', '2'},
            {'.', '.', '3', '.', '.', '4', '1', '9', '.'},
            {'1', '8', '5', '.', '6', '.', '.', '2', '.'},
            {'.', '.', '.', '.', '2', '.', '.', '6', '.'},
            {'9', '6', '.', '4', '.', '5', '3', '.', '.'},
            {'.', '3', '.', '.', '7', '2', '.', '.', '4'},
            {'.', '4', '9', '.', '3', '.', '.', '5', '7'},
            {'8', '2', '7', '.', '.', '9', '.', '1', '3'}
        };

        soduku soduku = new soduku();
        soduku.solveSudoku(board);

        // Print the solved board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
