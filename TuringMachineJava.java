/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.machine.java;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Nadod
 */
public class TuringMachineJava {

    /**
     * @param args the command line arguments
     */
    public static TuringMachine SettinUpTM()
	{
		TuringMachine newTM = new TuringMachine();
                Scanner scanner = new Scanner(System.in); 
                
                System.out.println("Enter number of states(states will be represented qs):");
                int no_of_states = scanner.nextInt();
               for (int i=0;i<no_of_states;i++){
               newTM.addState("q"+i);
               }
               
               System.out.println("Enter start state : ");
               Scanner scanner1 = new Scanner(System.in);
               String initial_state = scanner1.nextLine();
	       newTM.setStartState(initial_state);
               
                
               System.out.println("Enter number of alphabets : "); 
               int no_of_alphabets = scanner.nextInt();
                char alpha;
               for (int i=1;i<=no_of_alphabets;i++){
               System.out.println("Enter alphabet_"+i+" : "); 
               alpha = scanner1.nextLine().charAt(0);
               newTM.add_aplhabets(alpha);
               }
               
               String current_state,next_state;
               char current_symbol,new_symbol,Direction;
               String line;
               String[] lineVector;
               Scanner scanner2 = new Scanner(System.in); 
               System.out.println("Enter transition table seperated by a comma: "); 
               for(int i=0;i<(no_of_states*no_of_alphabets);i++){
                   
                line = scanner2.nextLine(); //read tranitions
               lineVector = line.split(","); //separate all values by comma
                //parsing the values to Integer
                 current_state=lineVector[0];
                 current_symbol=lineVector[1].charAt(0);
                 next_state=lineVector[2];
                 new_symbol=lineVector[3].charAt(0);
                 Direction=lineVector[4].charAt(0);;
                 newTM.addTransition(current_state, current_symbol,next_state,new_symbol,Direction);
               }
               
		//newTM.addTransition("q1", '1', "q3", 'x', true);
		
		return newTM;		
	}

    
    public static void main(String[] args) {
        // TODO code application logic here
       
        Scanner scanner = new Scanner(System.in); 
                
                 TuringMachine TM1 = SettinUpTM();
                 String initial_Configuaration;
                 System.out.println("Enter initial Configuaration : "); 
		initial_Configuaration= scanner.nextLine();
		boolean done = TM1.Run(initial_Configuaration);
		if (done==true)
		{
			System.out.println("The input was accepted.");
		}
		else
		{
			System.out.println("The input was rejected.");
		}
    }
    
}
