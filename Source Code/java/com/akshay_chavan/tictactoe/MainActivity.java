package com.akshay_chavan.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

import static com.akshay_chavan.tictactoe.R.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public Button button1, button2, button3, button4, button5, button6, button7, button8, button9;
    public String turn = "Player1";     // variable to check turn of which player
    public Dialog dialog;
    public String player1Name,player2Name;
    String gameMode="";
    int angles[] = {90,180,270,360};
    boolean centerFlag = false;
    int playerPrevMove,compPrevMove, moveNo=1, filledBoxes = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        // select game mode ie. vs Player or vs Computer
        chooseGameMode();

        // set buttons by finding resp. ids
        button1 = findViewById(id.button1);
        button2 = findViewById(id.button2);
        button3 = findViewById(id.button3);
        button4 = findViewById(id.button4);
        button5 = findViewById(id.button5);
        button6 = findViewById(id.button6);
        button7 = findViewById(id.button7);
        button8 = findViewById(id.button8);
        button9 = findViewById(id.button9);

        // set click listener on each button
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);

        int angle = new Random().nextInt(4);
        rotate(angles[angle]);              // rotate box random times (to make pattern unpredictable)

    }

    public void  computerPlay() {
        if(centerFlag  )  // if player goes middle box
        {
            if(playWinningMove())
            {

            }
            if(moveNo == 2)
            {
                button3.setText("O");
            }
            else if(moveNo == 3)
            {
                button4.setText("O");
            }
            else if(moveNo == 4)
            {
                if(button2.getText().length()==0)
                    button2.setText("O");
                else if(button8.getText().length()==0)
                    button8.setText("O");
            }
            else if(moveNo ==5 )
            {
                if(button1.getText().length()==0)
                    button1.setText("O");
                else if(button7.getText().length()==0)
                    button7.setText("O");
                else if(button2.getText().length()==0)
                    button2.setText("O");
                else if(button8.getText().length()==0)
                    button8.setText("O");
            }
        } else {                // if player goes other box except middle
            if (moveNo ==2 )
            {
                if (playerPrevMove!=6 && playerPrevMove!=3 && playerPrevMove!=4)
                    button3.setText("O");
                else if(playerPrevMove!=8 && playerPrevMove!=7)
                    button7.setText("O");
            }
            else if(moveNo ==3 )
            {
                if(playerPrevMove!=6 && playerPrevMove!=8)
                {
                    if(button3.getText().equals("O") )
                        button6.setText("O");
                    else if(button7.getText().equals("O"))
                        button8.setText("O");
                }
                else {
                    if (button4.getText().length() > 0)
                        button3.setText("O");
                    else if(button2.getText().equals("X") && button6.getText().equals("X"))
                        button7.setText("O");
                    else if(button6.getText().equals("X") && button8.getText().equals("X"))
                        button1.setText("O");
                    else if(button1.getText().length()==0)
                        button1.setText("O");
                    else if(button7.getText().length()==0)
                        button7.setText("O");
//                    else if (button6.getText().length() > 0)
//                        button7.setText("O");
//                    else
//                        button1.setText("O");
                }
//                else if(playerPrevMove==8) {
//                    button1.setText("O");
//                }
//                else if(playerPrevMove==6)
//                    button7.setText("O");
            }
            else if( moveNo==4 )
            {
                if(playWinningMove())
                {

                }
                else if(playerPrevMove==2 || playerPrevMove==4 || playerPrevMove==6 || playerPrevMove==8)
                    button5.setText("O");
                else if(playerPrevMove ==5 )
                {

//                    if(button7.getText().length()==0)
//                        button2.setText("O");
//                    else if(button3.getText().length()==0 || button3.getText().equals("X"))
//                        button4.setText("O");
//                    else if(button6.getText().length()==0 && button3.getText().equals("O"))
//                        button6.setText("O");
//                    else if(button8.getText().length()==0)
//                        button8.setText("O");
                }
            }
        }
        moveNo+=1;

        turn = "Player2";
        if (isWon())        // check if computer won or not
        {
            wait_time();
            showWinnerDialog(player1Name+" won!");
            return;
        }
        filledBoxes+=1;
    }   // end computerPlay

    public void wait_time()
    {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Log.i("Exception occurred",""+e);
        }
    }

    public void rotate(float deg) {      // rotate puzzle right

        LinearLayout yourLayout  = (LinearLayout) findViewById(id.mainContainer);
        yourLayout.setRotation(deg);

        button1.setRotation(deg);
        button2.setRotation(deg);
        button3.setRotation(deg);
        button4.setRotation(deg);
        button5.setRotation(deg);
        button6.setRotation(deg);
        button7.setRotation(deg);
        button8.setRotation(deg);
        button9.setRotation(deg);
    }

    public boolean isWon() {
        // row check
        if( button1.getText().length()>0 && button1.getText().equals(button2.getText()) && button1.getText().equals(button3.getText())) {
            button1.setBackgroundColor(Color.parseColor("#4DFF2B"));
            button2.setBackgroundColor(Color.parseColor("#4DFF2B"));
            button3.setBackgroundColor(Color.parseColor("#4DFF2B"));
            return true;
        }
        if( button4.getText().length()>0 && button4.getText().equals(button5.getText()) && button4.getText().equals(button6.getText())) {
            button4.setBackgroundColor(Color.parseColor("#4DFF2B"));
            button5.setBackgroundColor(Color.parseColor("#4DFF2B"));
            button6.setBackgroundColor(Color.parseColor("#4DFF2B"));
            return true;
        }
        if( button7.getText().length()>0 && button7.getText().equals(button8.getText()) && button7.getText().equals(button9.getText())) {
            button7.setBackgroundColor(Color.parseColor("#4DFF2B"));
            button8.setBackgroundColor(Color.parseColor("#4DFF2B"));
            button9.setBackgroundColor(Color.parseColor("#4DFF2B"));
            return true;
        }
        // column check
        if( button1.getText().length()>0 && button1.getText().equals(button4.getText()) && button1.getText().equals(button7.getText())) {
            button1.setBackgroundColor(Color.parseColor("#4DFF2B"));
            button4.setBackgroundColor(Color.parseColor("#4DFF2B"));
            button7.setBackgroundColor(Color.parseColor("#4DFF2B"));
            return true;
        }
        if( button2.getText().length()>0 && button2.getText().equals(button5.getText()) && button2.getText().equals(button8.getText())) {
            button2.setBackgroundColor(Color.parseColor("#4DFF2B"));
            button5.setBackgroundColor(Color.parseColor("#4DFF2B"));
            button8.setBackgroundColor(Color.parseColor("#4DFF2B"));
            return true;
        }
        if( button3.getText().length()>0 && button3.getText().equals(button6.getText()) && button3.getText().equals(button9.getText())) {
            button3.setBackgroundColor(Color.parseColor("#4DFF2B"));
            button6.setBackgroundColor(Color.parseColor("#4DFF2B"));
            button9.setBackgroundColor(Color.parseColor("#4DFF2B"));
            return true;
        }
        // diagonal check
        if( button1.getText().length()>0 && button1.getText().equals(button5.getText()) && button1.getText().equals(button9.getText())) {
            button1.setBackgroundColor(Color.parseColor("#4DFF2B"));
            button5.setBackgroundColor(Color.parseColor("#4DFF2B"));
            button9.setBackgroundColor(Color.parseColor("#4DFF2B"));
            return true;
        }
        if( button3.getText().length()>0 && button3.getText().equals(button5.getText()) && button3.getText().equals(button7.getText())) {
            button3.setBackgroundColor(Color.parseColor("#4DFF2B"));
            button5.setBackgroundColor(Color.parseColor("#4DFF2B"));
            button7.setBackgroundColor(Color.parseColor("#4DFF2B"));
            return true;
        }
        return false;       // if not won return false
    }

    @SuppressLint("ResourceAsColor")
    boolean playWinningMove() {
        // Horizontal
        if(button1.getText().equals("O") && button3.getText().equals("O") && button2.getText().length()==0) {
            button2.setText("O");
        }
        else if(button5.getText().equals("O") && button6.getText().equals("O") && button4.getText().length()==0) {
            button4.setText("O");
            }
        else if(button7.getText().equals("O") && button9.getText().equals("O") && button8.getText().length()==0)
        {
            button8.setText("O");
        }
        // Vertical
        else if(button1.getText().equals("O") && button7.getText().equals("O") && button4.getText().length()==0) {
            button4.setText("O");
        }
        else if(button2.getText().equals("O") && button8.getText().equals("O") && button5.getText().length()==0) {
            button5.setText("O");
        }
        else if(button3.getText().equals("O") && button9.getText().equals("O") && button6.getText().length()==0) {
            button6.setText("O");
        }

        // Diagonal
        else if(button1.getText().equals("O") && button9.getText().equals("O") && button5.getText().length()==0) {
            button5.setText("O");
        }
        else if(button3.getText().equals("O") && button7.getText().equals("O") && button5.getText().length()==0) {
            button5.setText("O");
        }

        return true;
    }

    public void chooseCorner() {            // computer's first move
        wait_time();
        button9.setText("O");
        moveNo+=1;
        filledBoxes+=1;
    }

    @Override
    public void onClick(View v) {
            if(turn.equalsIgnoreCase("Player1"))
            {
                    switch (v.getId()) {
                        case id.button1:
                            if(button1.getText().length()==0) {
                                button1.setText("O");
                            }
                            else
                                return;
                            break;
                        case id.button2:
                            if(button2.getText().length()==0)
                                button2.setText("O");
                            else
                                return;

                            break;
                        case id.button3:
                            if(button3.getText().length()==0)
                                button3.setText("O");
                            else
                                return;
                            break;
                        case id.button4:
                            if(button4.getText().length()==0)
                                button4.setText("O");
                            else
                                return;
                            break;
                        case id.button5:
                            if(button5.getText().length()==0)
                                button5.setText("O");
                            else
                                return;
                            break;
                        case id.button6:
                            if(button6.getText().length()==0)
                                button6.setText("O");
                            else
                                return;
                            break;
                        case id.button7:
                            if(button7.getText().length()==0)
                                button7.setText("O");
                            else
                                return;
                            break;
                        case id.button8:
                            if(button8.getText().length()==0)
                                button8.setText("O");
                            else
                                return;
                            break;
                        case id.button9:
                            if(button9.getText().length()==0)
                                button9.setText("O");
                            else
                                return;
                            break;
                        default:
                            Toast.makeText(this,"Error while getting IDs",Toast.LENGTH_LONG).show();
                    }
                if (isWon())        // check if player 1 won or not
                {
                    wait_time();
                    showWinnerDialog(player1Name+" won!");
                    return;
                }
                turn = "Player2";
            }
            else if(turn.equalsIgnoreCase("Player2"))
            {
                switch (v.getId()) {
                    case id.button1:
                        if(button1.getText().length()==0)
                        {
                            button1.setText("X");
                            playerPrevMove = 1;
                        }
                        else
                            return;
                        break;
                    case id.button2:
                        if(button2.getText().length()==0)
                        {
                            button2.setText("X");
                            playerPrevMove = 2;
                        }
                        else
                            return;
                        break;
                    case id.button3:
                        if(button3.getText().length()==0)
                        {
                            button3.setText("X");
                            playerPrevMove = 3;
                        }
                        else
                            return;
                        break;
                    case id.button4:
                        if(button4.getText().length()==0)
                        {
                            button4.setText("X");
                            playerPrevMove = 4;
                        }
                        else
                            return;
                        break;
                    case id.button5:
                        if(button5.getText().length()==0)
                        {
                            button5.setText("X");
                            centerFlag = true;
                            playerPrevMove = 5;
                        }
                        else
                            return;
                        break;
                    case id.button6:
                        if(button6.getText().length()==0)
                        {
                            button6.setText("X");
                            playerPrevMove = 6;
                        }
                        else
                            return;
                        break;
                    case id.button7:
                        if(button7.getText().length()==0)
                        {
                            button7.setText("X");
                            playerPrevMove = 7;
                        }
                        else
                            return;
                        break;
                    case id.button8:
                        if(button8.getText().length()==0)
                        {
                            button8.setText("X");
                            playerPrevMove = 8;
                        }
                        else
                            return;
                        break;
                    case id.button9:
                        if(button9.getText().length()==0)
                        {
                            button9.setText("X");
                            playerPrevMove = 9;
                        }
                        else
                            return;
                        break;
                    default:
                        Toast.makeText(this,"Error while getting IDs",Toast.LENGTH_LONG).show();
                }
                if (isWon())        // check if player 2 won or not
                {
                    wait_time();
                    showWinnerDialog(player2Name+ "won!");
                    return;
                }
                turn = "Player1";
                if(gameMode.equalsIgnoreCase("vscomputer"))
                    computerPlay();

            }
            filledBoxes+=1;
            if(filledBoxes==9)
            {
                showWinnerDialog("Game is a draw!");
            }


    }




    public void showWinnerDialog(String result) {

        Log.i("***Test:", "Inside showDialog");
        dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(layout.my_dialog);
        TextView winner = dialog.findViewById(id.dialogTxt);
        winner.setText(result);
        dialog.show();

        // When closed button clicked
        Button closeBtn = (Button) dialog.findViewById(id.closeBtn);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });

        // When New Game Button clicked
        Button newGameBtn = (Button) dialog.findViewById(id.newGameBtn);
        newGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                startNewGame();
            }
        });
    }

    // Method to get players name
    public void getPlayersName() {
        Log.i("***Test:", "Inside get playersName");
        dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(layout.p_names_input);
        TextView winner = dialog.findViewById(id.dialogTxt);
        dialog.show();

        // on submit
        Button submitBtn = (Button) dialog.findViewById(id.submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText p1 = dialog.findViewById(id.player1Name);
                EditText p2 = dialog.findViewById(id.player2Name);
                TextView players = findViewById(id.playersMarker);
                player1Name = p1.getText().toString();
                player2Name = p2.getText().toString();
                if(player1Name.length()==0 || player2Name.length()==0)
                {
                    Toast.makeText(MainActivity.this,"Please enter player name!", Toast.LENGTH_LONG).show();
                }
                else {
                    //                Toast.makeText(MainActivity.this,"Player 1: "+player1Name+" Player 2: "+player2Name, Toast.LENGTH_LONG).show();
                    players.setText(player1Name+": O\n"+player2Name+": X");
                    dialog.dismiss();

                }
            }
        });
    }

    // Method to start app again after button click of the dialog box
    public  void startNewGame() {
        Intent intent= new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    public void chooseGameMode() {
        dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(layout.game_mode);
        dialog.show();

        Button player = dialog.findViewById(id.vsplayer);
        Button computer = dialog.findViewById(id.vscomputer);

        // when vs player game mode gets selected
        player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameMode = "vsplayer";
                dialog.dismiss();
                getPlayersName();
            }
        });

        // when vs computer game mode gets selected
        computer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameMode = "vscomputer";
                player1Name = "Computer";
                player2Name = "Player";
                TextView players = findViewById(id.playersMarker);
                players.setText(player1Name+": O\n"+player2Name+": X");
                dialog.dismiss();
                chooseCorner();             // computer will choose one of the corner
                turn = "Player2";
            }
        });
    }

}
