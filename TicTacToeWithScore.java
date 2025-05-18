import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeWithScore {
    private JFrame frame;
    private JButton[][] buttons;
    private JLabel scoreLabel;
    private int xWins = 0, oWins = 0;
    private char currentPlayer = 'X';

    public TicTacToeWithScore() {
        frame = new JFrame("Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 450);
        frame.setLayout(new BorderLayout());

        scoreLabel = new JLabel("Score - X: 0 | O: 0", SwingConstants.CENTER);
        frame.add(scoreLabel, BorderLayout.NORTH);

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));
        buttons = new JButton[3][3];
        initializeButtons(boardPanel);
        frame.add(boardPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void initializeButtons(JPanel panel) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton(" ");
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 40));
                buttons[row][col].setFocusPainted(false);
                panel.add(buttons[row][col]);

                final int r = row, c = col;
                buttons[row][col].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (buttons[r][c].getText().equals(" ")) {
                            buttons[r][c].setText(String.valueOf(currentPlayer));
                            if (checkWin()) {
                                if (currentPlayer == 'X') xWins++;
                                else oWins++;
                                JOptionPane.showMessageDialog(frame, "Player " + currentPlayer + " wins!");
                                updateScore();
                                resetBoard();
                            } else {
                                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                            }
                        }
                    }
                });
            }
        }
    }

    private void updateScore() {
        scoreLabel.setText("Score - X: " + xWins + " | O: " + oWins);
    }

    private boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if ((buttons[i][0].getText().equals(String.valueOf(currentPlayer)) &&
                 buttons[i][1].getText().equals(String.valueOf(currentPlayer)) &&
                 buttons[i][2].getText().equals(String.valueOf(currentPlayer))) ||
                (buttons[0][i].getText().equals(String.valueOf(currentPlayer)) &&
                 buttons[1][i].getText().equals(String.valueOf(currentPlayer)) &&
                 buttons[2][i].getText().equals(String.valueOf(currentPlayer)))) {
                return true;
            }
        }
        return (buttons[0][0].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][2].getText().equals(String.valueOf(currentPlayer))) ||
               (buttons[0][2].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][0].getText().equals(String.valueOf(currentPlayer)));
    }

    private void resetBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText(" ");
            }
        }
        currentPlayer = 'X';
    }

    public static void main(String[] args) {
        new TicTacToeWithScore();
    }
}