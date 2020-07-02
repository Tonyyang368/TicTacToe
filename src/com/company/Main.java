// This program creates a tic-tac-toe game. Users can play between one another until
//someone wins. Future plans: adjust code to be a connect-4 game because connect-4 is more fun

package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // booleans to determine when to end the game (win) and who's turn it is (turn)
        boolean win = false;
        boolean turn = true;
        char[][] board = makeBoard(); //make a board and display to user
        while (!win) { //continue playing while no one has won
            turn = !turn; //next player's turn
            int[] Input = grabInput(board); //get input coordinates
            int x = Input[0]*2 - 2; //adjust coordinates because actual board has delimiters '-', '+' and '|' between squares
            int y = Input[1]*2 - 2;

            if (turn){ //check who's turn it is
                board[x][y] = 'X';
            }
            else {
                board[x][y] = 'O';
            }

            win = checkWin(board); //check to see if there's a winner
        }
        printBoard(board); //display winning board and inform user
        System.out.print("Congrats nerd, you just won\n");

        }
        public static char[][] makeBoard(){
            char[][] board = new char[5][6]; //preallocate array
            for (int i = 0; i < 5; i++){ //vertical entries separated by '-' and '+'
                if (i % 2 == 1) {
                    for (int p = 0; p < 5; p +=2){
                        board[i][p] = '-';
                        board[i][p+1] = '+';
                    }
                }
                else{ //horizontal entries separated by '|'
                    for (int k = 0; k < 5; k+=2) {
                        board[i][k] = ' ';
                        board[i][k+1] = '|';
                    }
                }
            }
            return board;
        }

        public static boolean checkWin(char[][] board){
            int xCount = 0;
            int yCount = 0;
            //row check
            for (char[] row : board) {
                if ((xCount == 3)||(yCount == 3)) return true;
                xCount = yCount = 0;
                for (char c : row) {
                    if (c == 'X'){
                        xCount++;
                    }
                    if (c == 'O') {
                        yCount++;
                    }
                }
            }
            //column check
            for (int i = 0; i < board[0].length; i++) {
                xCount = yCount = 0;
                for (int k = 0; k < board.length;k++) {
                    if (board[k][i] == 'X'){
                        xCount++;
                    }
                    if (board[k][i] == 'O') {
                        yCount++;
                    }
                }
                if ((xCount == 3)||(yCount == 3)) return true;
            }

            //the dreaded diagonal check (to the right)
            for (int i = 0; i < (board[0].length - 5); i+=2) {
                for (int k = 0; k < board.length; k+=5){
                    if ((board[i+k][k] == 'X')&&(board[i+k+2][k+2] == 'X')&&(board[i+k+4][k+4] == 'X')) return true;
                    if ((board[i+k][k] == 'O')&&(board[i+k+2][k+2] == 'O')&&(board[i+k+4][k+4] == 'O')) return true;
                }

            }
            //diagonal check(to the left)
            for (int i = 4; i > 3; i-=2) {
                for (int k = 0; k < board.length; k+=5){
                    if ((board[i+k][k] == 'X')&&(board[i+k-2][k+2] == 'X')&&(board[i+k-4][k+4] == 'X')) return true;
                    if ((board[i+k][k] == 'O')&&(board[i+k-2][k+2] == 'O')&&(board[i+k-4][k+4] == 'O')) return true;
                }

            }
            return false;
        }

        public static int[] grabInput(char[][] board){
            Scanner scan = new Scanner(System.in);
            while (true) { //ask user for input until a usable input is printed
                printBoard(board);
                System.out.print("Enter the coordinates, you reckless child:\n");
                int x = scan.nextInt();
                int y = scan.nextInt();
                //check if input is a valid one
                if (!(y < 4)||!(y > 0)||!(x < 4)||!(x > 0)||!(board[x*2 - 2][y*2 - 2]==' ')) {
                    System.out.print("Your numbers are weak, try again\n");
                }
                else {
                    return new int[] {x,y};
                }
            }

        }

        public static void printBoard(char[][] board){
            for (char[] row : board) { //printing that saucy board back to the user
                for(char c : row) {
                    System.out.print(c);
                }
                System.out.println();
            }
        }
    }


