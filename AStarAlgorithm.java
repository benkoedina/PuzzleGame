/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sliding.puzzle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class AStarAlgorithm {

    private int[][] startState = new int[4][3];
    private int[][] finalState = new int[4][3];

     private int row = 0;
    private int column = 0;
    private int row1 = -1;
    private int column1 = -1;
    
    private int[][] visitedNode = new int[4][3];

    private int l = 0;
    private int parent[][] = new int[4][3];

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    private int temp = 0;
    private int depth = 0;
    private int ok = 0;
    private int pathcost = 0;
    private int visited = 1;
    
    List<int[][]> list1 = new ArrayList();
    List<int[][]> list2 = new ArrayList();

 
    public void restoreValues()
    {
        pathcost=0;
        visited = 1;
        visitedNode = new int[4][3];
        temp =0;
        depth = 0;
        ok = 0;
        parent = new int [4][3];
        l=0;
        row = 0;
        column = 0;
        row1 = -1;
        column1 = -1;
        list1 = new ArrayList();
        list2 = new ArrayList();    
       
        
    }

 
    public int[][] getStartState() {
        return startState;
    }

    public  int getInvCount(int[][] arr) 
{ 
    int inv_count = 0; 
    for (int i = 0; i < 3 - 1; i++) 
        for (int j = i + 1; j < 3; j++) 
          
            // Value 0 is used for empty space 
            if (arr[j][i] > 0 && 
                            arr[j][i] > arr[i][j]) 
                inv_count++; 
    return inv_count; 
} 
    public boolean isSolvable(int[][] puzzle) 
{ 

    int invCount = getInvCount(puzzle); 
    return (invCount % 2 == 0); 
} 
  
    public void readStateFromFile(String filename) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(filename));
        } catch (FileNotFoundException ex) {
            System.out.println("File exception!");
            System.exit(404);
        }
        int row = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] items = line.split(" ");
            for (int i = 0; i < items.length; i++) {
                int number = Integer.parseInt(items[i]);
                startState[row][i] = number;
            }

            row++;

        }
        startState[3][1] = depth;

        setFinalPosition();

    }

    public void getStartPosition() throws IOException {

        System.out.println("\n Enter the start position: ");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                startState[i][j] = Integer.parseInt(br.readLine());
            }

        }
        startState[3][1] = depth;

        setFinalPosition();

    }

    public void setFinalPosition() {
        int nr = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 2 && j == 2) {
                    finalState[i][j] = 0;
                } else {
                    finalState[i][j] = nr;
                    nr++;
                }
            }
        }
    }

    //call from the main
    public void display() {

        System.out.println("\n The start position is");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + startState[i][j]);
            }
            System.out.println();

        }

        System.out.println("\n ***");

    }

    public void displayCurrentState(int a[][]) {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + a[i][j]);
            }
            System.out.println("");
        }
        System.out.println("\n ***");
    }

    public int minimum() {

        int min = list1.get(0)[3][0];
        int nr = 0;
        for (int i = 1; i < list1.size(); i++) {
            if (list1.get(i)[3][0] < min) {
                min = list1.get(i)[3][0];
                nr = i;
            }
        }

        return nr;
    }

    public String Visited() {

        list1.add(startState);
        while (true) {
            if (list1.isEmpty() == false) {

                visitedNode = list1.remove(minimum());

            } else {
                return "Execution failed";
            }
            ok = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (visitedNode[i][j] != finalState[i][j]) {
                        ok = 1;
                    }
                }
            }
            if (ok != 1) {
                System.out.println("Number of Visited Nodes: " + visited);
                return "Execution succeed";
            } else {

                expand(visitedNode, visitedNode[3][1] + 1);
                parent(visitedNode);
            }
        }
    }

    public String Cost() {

        list1.add(startState);
        while (true) {
            if (list1.isEmpty() == false) {

                visitedNode = list1.remove(minimum());

            } else {
                return "Exectuion Failed";
            }
            ok = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (visitedNode[i][j] != finalState[i][j]) {
                        ok = 1;
                    }
                }
            }
            if (ok != 1) {

                System.out.println("The path cost is: " + visitedNode[3][1]);
                return "Exectuion Succeed";
            } else {

                expand(visitedNode, visitedNode[3][1] + 1);
                parent(visitedNode);
            }
        }
    }

    public int[][] parent(int[][] visitedNode) {
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                parent[i][j] = visitedNode[i][j];
            }
        }

        return parent;
    }

    public String Sequence() {

        list1.add(startState);
        while (true) {
            if (list1.isEmpty() == false) {

                visitedNode = list1.remove(minimum());
                displayCurrentState(visitedNode);
            } else {
                return "Exectuion Failed";
            }
            ok = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (visitedNode[i][j] != finalState[i][j]) {
                        ok = 1;
                    }
                }
            }
            if (ok != 1) {
                return "Exectuion Succeed";
            } else {

                expand(visitedNode, visitedNode[3][1] + 1);
                parent(visitedNode);
            }
        }
    }

    public void expand(int k[][], int depth) {

        getOk(k, depth);
        row1 = row;
        column1 = column;
    }

    public void getOk(int[][] table, int depth) {

        
        findspace(table);
        if ((row - 1 >= 0 && row1 != row - 1) || (row - 1 >= 0 && row1 == -1)) {
            //top
            int n[][] = arrayExec(table);
            change(n, row - 1, column, depth);
        }
        if ((column + 1 <= 2 && column1 != column + 1) || (column + 1 <= 2 && column1 == -1)) {
            //right
            int n[][] = arrayExec(table);
            change(n, row, column + 1, depth);
        }
        if ((row + 1 <= 2 && row1 != row + 1) || (row + 1) <= 2 && row1 == -1) {

            //bottom
            int n[][] = arrayExec(table);
            change(n, row + 1, column, depth);
        }

        if ((column - 1 >= 0 && column1 != column - 1) || (column - 1) >= 0 && column1 == -1) {
            //left
            int n[][] = arrayExec(table);
            change(n, row, column - 1, depth);
        }
    }

    public int[][] arrayExec(int[][] table) {
        int n[][] = new int[4][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                n[i][j] = table[i][j];
            }
        }
        return n;
    }

    public void change(int[][] puzzleList, int j, int k, int depth) {

        int count = 0;
        int cost = 0;
        int temp = puzzleList[j][k];
        int s = 1;

        puzzleList[j][k] = puzzleList[row][column];
        puzzleList[row][column] = temp;
        puzzleList[3][1] = depth;
        for (int i = 0; i < 3; i++) {
            for (int p = 0; p < 3; p++) {
                if (puzzleList[i][p] != finalState[i][p]) {
                    count++;
                }
            }
        }

        visited++;
        cost = puzzleList[3][1] + count;
        puzzleList[3][0] = cost;
        list1.add(puzzleList);

    }

    public void findspace(int orgnode[][]) {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (orgnode[i][j] == 0) {
                    row = i;
                    column = j;
                    break;
                }
            }
        }
    }
}
