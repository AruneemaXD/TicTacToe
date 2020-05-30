package com.example.admin.TicTacToe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
/*
 Created by Aruneema on 07/07/19.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

/*
    DESCRIPTION OF VARIABLES
    TAG ---------> Used for log debugging
    PLAYER_X ----> Variable to identify player turn
    TURN_COUNT --> Variable to keep count of number of turns
    b00 - b22 ---> Button for each 3x3 box
    tvInfo ------> Information regarding game status
    boardStatus -> 2-d array to identify board status
        -1 means, No one has played on that box yet
        0 means, Computer's has played on that box
        1 means, Player X has played on that box
    */
    /*
    DESCRIPTION OF METHODS
    onClick --------> Called whenever any box or reset button is clicked.
        This method places either X or 0 according to player turn
        and updates other information
    checkWinner ----> This method checks the board status and identifies the winner
        It checks for all the 3 rows, 3 columns and 2 diagonals
    resetBoard -----> This method is called whenever reset button is pressed.
        updates everything to initial values of game
    enableAllBoxes -> This method can either enable or disable all boxes
    setInfo --------> This method updates the string in TextView
    result ---------> This method works whenever a player wins or game draws
    initializeBoardStatus -> Method which set the value of integer array to -1
    */

    private final static String TAG = MainActivity.class.getSimpleName();

    boolean PLAYER_X = true;

    int TURN_COUNT = 0;
    Button b00;
    Button b01;
    Button b02;
    Button b10;
    Button b11;
    Button b12;
    Button b20;
    Button b21;
    Button b22;
    Button bReset;
    TextView tvInfo;

    int[][] boardStatus = new int[3][3];

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b00 = (Button) findViewById(R.id.b00);
        b01 = (Button) findViewById(R.id.b01);
        b02 = (Button) findViewById(R.id.b02);
        b10 = (Button) findViewById(R.id.b10);
        b11 = (Button) findViewById(R.id.b11);
        b12 = (Button) findViewById(R.id.b12);
        b20 = (Button) findViewById(R.id.b20);
        b21 = (Button) findViewById(R.id.b21);
        b22 = (Button) findViewById(R.id.b22);
        bReset = (Button) findViewById(R.id.bReset);
        tvInfo = (TextView) findViewById(R.id.tvInfo);

        bReset.setOnClickListener(this);
        b00.setOnClickListener(this);
        b01.setOnClickListener(this);
        b02.setOnClickListener(this);
        b10.setOnClickListener(this);
        b11.setOnClickListener(this);
        b12.setOnClickListener(this);
        b20.setOnClickListener(this);
        b21.setOnClickListener(this);
        b22.setOnClickListener(this);
        initializeBoardStatus();
    }

    @Override

    public void onClick(View view) {
        Log.d(TAG, "Inside onClick");

        boolean resetButtonPressed = false;

        switch (view.getId()) {

            case R.id.b00:

                if (PLAYER_X) {

                    b00.setText("X");

                    boardStatus[0][0] = 1;
                }

                b00.setEnabled(false);

                break;

            case R.id.b01:

                if (PLAYER_X) {

                    b01.setText("X");

                    boardStatus[0][1] = 1;
                }

                b01.setEnabled(false);

                break;

            case R.id.b02:

                if (PLAYER_X) {

                    b02.setText("X");

                    boardStatus[0][2] = 1;
                }

                b02.setEnabled(false);

                break;

            case R.id.b10:

                if (PLAYER_X) {

                    b10.setText("X");

                    boardStatus[1][0] = 1;
                }

                b10.setEnabled(false);

                break;

            case R.id.b11:

                if (PLAYER_X) {

                    b11.setText("X");

                    boardStatus[1][1] = 1;
                }


                b11.setEnabled(false);

                break;

            case R.id.b12:

                if (PLAYER_X) {

                    b12.setText("X");

                    boardStatus[1][2] = 1;
                }


                b12.setEnabled(false);

                break;

            case R.id.b20:

                if (PLAYER_X) {

                    b20.setText("X");

                    boardStatus[2][0] = 1;
                }


                b20.setEnabled(false);

                break;

            case R.id.b21:

                if (PLAYER_X) {

                    b21.setText("X");

                    boardStatus[2][1] = 1;
                }


                b21.setEnabled(false);

                break;

            case R.id.b22:

                if (PLAYER_X) {

                    b22.setText("X");

                    boardStatus[2][2] = 1;
                }


                b22.setEnabled(false);

                break;

            case R.id.bReset:
                resetButtonPressed =
                        true;

                break;

            default:

                break;
        }

        if (resetButtonPressed) {
            resetBoard();
        } else {

            TURN_COUNT++;
            checkWinner();

            compMove();

            TURN_COUNT++;
            checkWinner();
        }


        if (TURN_COUNT == 10) {
            result(
                    "Game Draw");
        }
        checkWinner();
    }


    private void checkWinner() {
        Log.d(TAG, "Inside checkWinner");

//Horizontal --- rows

        for (int i = 0; i < 3; i++) {

            if (boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][0] == boardStatus[i][2]) {

                if (boardStatus[i][0] == 1) {
                    result("Player X winner\n" + (i + 1) + " row");


                } else if (boardStatus[i][0] == 0) {
                    result("Computer winner\n" + (i + 1) + " row");


                }
            }
        }

//Vertical --- columns

        for (int i = 0; i < 3; i++) {

            if (boardStatus[0][i] == boardStatus[1][i] && boardStatus[0][i] == boardStatus[2][i]) {

                if (boardStatus[0][i] == 1) {
                    result(
                            "Player X winner\n" + (i + 1) + " column");


                } else if (boardStatus[0][i] == 0) {
                    result(
                            "Computer winner\n" + (i + 1) + " column");


                }
            }
        }

//First diagonal

        if (boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0] == boardStatus[2][2]) {

            if (boardStatus[0][0] == 1) {
                result(
                        "Player X winner\nFirst Diagonal");
            } else if (boardStatus[0][0] == 0) {
                result(
                        "Computer winner\nFirst Diagonal");
            }
        }

//Second diagonal

        if (boardStatus[0][2] == boardStatus[1][1] && boardStatus[0][2] == boardStatus[2][0]) {

            if (boardStatus[0][2] == 1) {
                result(
                        "Player X winner\nSecond Diagonal");
            } else if (boardStatus[0][2] == 0) {
                result(
                        "Computer winner\nSecond Diagonal");
            }
        }
    }

    private void enableAllBoxes(boolean value) {
        Log.
                d(TAG, "Inside enableAllBoxes");

        b00.setEnabled(value);

        b01.setEnabled(value);

        b02.setEnabled(value);

        b10.setEnabled(value);

        b11.setEnabled(value);

        b12.setEnabled(value);

        b20.setEnabled(value);

        b21.setEnabled(value);

        b22.setEnabled(value);
    }

    private void result(String winner) {
        Log.
                d(TAG, "Inside result");
        setInfo(winner);
        enableAllBoxes(false);
    }

    private void resetBoard() {
        Log.
                d(TAG, "Inside resetBoard");

        b00.setText("");

        b01.setText("");

        b02.setText("");

        b10.setText("");

        b11.setText("");

        b12.setText("");

        b20.setText("");

        b21.setText("");

        b22.setText("");
        enableAllBoxes(
                true);

        PLAYER_X = true;

        TURN_COUNT = 0;
        initializeBoardStatus();
        setInfo(
                "Start Again!!!");
        Toast.makeText(this, "Board Reset", Toast.LENGTH_SHORT).show();
    }

    private void setInfo(String text) {

        tvInfo.setText(text);
    }

    private void initializeBoardStatus() {

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                boardStatus[i][j] = -1;
            }
        }

    }

    private boolean isWinning(int player, int[][] inputboard) {
        for (int r = 0; r <= 2; r++) {
            if (inputboard[r][0] == player && inputboard[r][1] == player && inputboard[r][2] == player) {
                return true;
            }
        }

        for (int c = 0; c <= 2; c++) {
            if (inputboard[0][c] == player && inputboard[1][c] == player && inputboard[2][c] == player) {
                return true;
            }
        }
        if (inputboard[0][0] == player && inputboard[1][1] == player && inputboard[2][2] == player) {
            return true;
        }
        return inputboard[0][2] == player && inputboard[1][1] == player && inputboard[2][0] == player;
    }

    private void setComp(int[][] inputboard) {
        if (inputboard[0][0] == 0) {
            b00.setText("O");
            b00.setEnabled(false);
        }
        if (inputboard[0][1] == 0) {
            b01.setText("O");
            b01.setEnabled(false);
        }
        if (inputboard[0][2] == 0) {
            b02.setText("O");
            b02.setEnabled(false);
        }
        if (inputboard[1][0] == 0) {
            b10.setText("O");
            b10.setEnabled(false);
        }
        if (inputboard[1][1] == 0) {
            b11.setText("O");
            b11.setEnabled(false);
        }
        if (inputboard[1][2] == 0) {
            b12.setText("O");
            b12.setEnabled(false);
        }
        if (inputboard[2][0] == 0) {
            b20.setText("O");
            b20.setEnabled(false);
        }
        if (inputboard[2][1] == 0) {
            b21.setText("O");
            b21.setEnabled(false);
        }
        if (inputboard[2][2] == 0) {
            b22.setText("O");
            b22.setEnabled(false);
        }

    }

    private void compMove() {
        //Choose winning move if available
        for (int row = 0; row <= 2; row++) {
            for (int column = 0; column <= 2; column++) {
                if (boardStatus[row][column] == -1) {
                    boardStatus[row][column] = 0;
                    if (isWinning(0, boardStatus)) {
                        boardStatus[row][column] = 0;
                        setComp(boardStatus);
                        return;

                    } else {
                        boardStatus[row][column] = -1;
                    }
                }
            }

        }

        //Choose blocking move if available
        for (int row = 0; row <= 2; row++) {
            for (int column = 0; column <= 2; column++) {
                if (boardStatus[row][column] == -1) {
                    boardStatus[row][column] = 1;
                    if (isWinning(1, boardStatus)) {
                        boardStatus[row][column] = 0;
                        setComp(boardStatus);
                        return;
                    } else {
                        boardStatus[row][column] = -1;
                    }
                }
            }
        }
        //Choose if opp diagonal taken
        if (boardStatus[0][0] == 1 && boardStatus[2][2]==1 ) {
            boardStatus[0][1] = 0;
            setComp(boardStatus);
            return;
        }

        //Choose if opp diagonal taken
        if (boardStatus[0][2] == 1 && boardStatus[2][0]==1 ) {
            boardStatus[2][1] = 0;
            setComp(boardStatus);
            return;
        }

        //Choose center if available
        if (boardStatus[1][1] == -1) {
            boardStatus[1][1] = 0;
            setComp(boardStatus);
            return;
        }

        //Choose a corner if available
        if (boardStatus[0][0] == -1) {
            boardStatus[0][0] = 0;
            setComp(boardStatus);
            return;
        }

        if (boardStatus[0][2] == -1) {
            boardStatus[0][2] = 0;
            setComp(boardStatus);
            return;
        }

        if (boardStatus[2][0] == -1) {
            boardStatus[2][0] = 0;
            setComp(boardStatus);
            return;
        }

        if (boardStatus[2][2] == -1) {
            boardStatus[2][2] = 0;
            setComp(boardStatus);
            return;
        }

        //Choose a random move
        for (int r = 0; r <= 2; r++) {
            for (int column = 0; column <= 2; column++) {
                if (boardStatus[r][column] == -1) {
                    boardStatus[r][column] = 0;
                    setComp(boardStatus);
                    return;
                }
            }
        }

    }


}

