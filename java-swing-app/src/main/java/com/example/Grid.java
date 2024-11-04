package com.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Grid extends JPanel implements ActionListener {
    private int rows;
    private int cols;
    private int cellSize;
    private int[][] grid;  // 2D array representing the grid state

    public Grid(int rows, int cols, int cellSize, boolean randomStart, boolean customStart) {
        this.rows = rows;
        this.cols = cols;
        this.cellSize = cellSize;
        this.grid = new int[rows][cols];  // Initialize the grid with default state 0 (empty)
        
        setPreferredSize(new Dimension(cols * cellSize, rows * cellSize));
        if(customStart){
            //ADD YOUR INTIALIZATION HERE
        }else if(randomStart){
            //initialize grid to random values:
            for(int i = 0; i < rows;i++){
                for(int j = 0; j < cols;j++){
                    this.grid[i][j] = (int)(Math.random()*2);
                }
            }
        } else {
            //create a glider for testing
            this.grid[2][0] = 1;
            this.grid[3][1] = 1;
            this.grid[1][2] = 1;
            this.grid[2][2] = 1;
            this.grid[3][2] = 1;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Loop through each cell and paint based on alive or dead
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                
                if (grid[row][col] == 0) {//dead
                    g.setColor(Color.BLACK);
                } else if (grid[row][col] == 1) {
                    // Wall (brown)
                    g.setColor(Color.WHITE); // alive
                } else {
                    throw new Error("Not a valid grid value");
                }
                

                // Draw the cell
                g.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);

                // Draw grid lines (optional)
                g.setColor(Color.BLACK);
                g.drawRect(col * cellSize, row * cellSize, cellSize, cellSize);
            }
        }
    }

    //ALL YOUR CODE GOES HERE
    public void nextGeneration() {
        //1 Create a new temporary new array to store the values of the next generation
        int[][] nextGen = new int[rows][cols];


        //2 Visit every cell in the new temporary grid. Check the number of neighboring cells, and based on the rules determine whether the cell will be alive or dead.
        //Watch out for edge cases!
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                int liveNeighbord = countLiveNeighbors(i, j);

                if (board[i][j] == 1){
                    if(liveNeighbors < 2 || liveNeighbors > 3){
                        nextGen[i][j] = 0;
                    } else {
                        nextGen[i][j] = 1;
                    }
                } else {
                    if(liveneighbors == 3) {
                        nextGen[i][j] = 1;
                    }
                }
            }
            board = nextGen;
        }
        //3 Copy the values of your temporary grid to the real grid
        private int countLiveNeighbors(int x, int y){
            int liveNeighbors = 0;

            for (int i = -1; i <= 1; i++){
                for (int j = -1; j <=1; j++) {
                    if (i == 0 && j == 0) continue;
                    
                    int nx = x+i;
                    int ny = x+j;

                    if (nx >= 0 && nc < rows && ny >= 0 && ny < cols) {
                        liveNeighbors += board[nx][ny];
                    }
                    }
                }
            }
            return liveNeighbors;
        }

        //don't mess with this part
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //don't put code here
    }
}