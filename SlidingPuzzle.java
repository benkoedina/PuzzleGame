/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sliding.puzzle;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author Dell
 */
public class SlidingPuzzle {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        String s = new String();
        AStarAlgorithm a1 = new AStarAlgorithm();

        showMenu();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String selectedOption = br.readLine();
        System.out.println("You selected: " + selectedOption);

        while (!selectedOption.equals("-cancel")) {

            if (selectedOption.equals("-input")) {

                System.out.println("Input from file or console?");
                BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
                String fileOrConsole = file.readLine();
                if (fileOrConsole.equals("file")) {

                    System.out.println("Give me the file name");
                    BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                    String filename = br1.readLine();
                  
                    a1.readStateFromFile(filename);
                    a1.display();
                } else {
                    System.out.println("Give me the input:");
                    a1.getStartPosition();
                    a1.display();
                }
            }

            if (selectedOption.equals("-solseq")) {

                a1.restoreValues();
                s = a1.Sequence();
                System.out.println(s);
            }

            if (selectedOption.equals("-pcost")) {

                a1.restoreValues();
                s = a1.Cost();
                System.out.println(s);

            }

            if (selectedOption.equals("-nvisited")) {

                a1.restoreValues();
                s = a1.Visited();
                System.out.println(s);

            }

            showMenu();
            selectedOption = br.readLine();
            System.out.println("You selected: " + selectedOption);
        }

    }

    public static void showMenu() {
        System.out.println("Select an option: ");
        System.out.println("-input");
        System.out.println("–solseq ");
        System.out.println("–pcost ");
        System.out.println("-nvisited");
        System.out.println("-cancel");

    }

}
