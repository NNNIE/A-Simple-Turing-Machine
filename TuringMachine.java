/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.machine.java;

import java.util.*;

public class TuringMachine {

    class Transition {

        String readState;
        char readSymbol;

        String writeState;
        char writeSymbol;
        char Action;
    }

    private Set<String> StateSpace;
    private Set<Transition> TransitionSpace;
    private ArrayList<Character> Symbol_alphabets = new ArrayList<Character>();
    private String StartState;
    private String Tape;
    private String CurrentState;
    private char CurrentSymbol;
    private int head;
    private char Actionnow;

    public TuringMachine() {
        StateSpace = new HashSet<String>();
        TransitionSpace = new HashSet<Transition>();
        StartState = new String("");
        Tape = new String("");
        CurrentState = new String("");
        CurrentSymbol = new Character(' ');
        head = 0;
    }

    public boolean Run(String input) {
        Tape = input;
        CurrentState = StartState;

        Transition CurrentTransition = null;

        //check that symbols are in alphabets
        for (int i = 0; i < input.length(); i++) {
            if (!Symbol_alphabets.contains(input.charAt(i))) {
                System.err.println("the symbol you entered" + input.charAt(i) + " is not in the alphabets");
            }
        }

        while (true) {

            System.out.println(Tape + " " + CurrentState + " " + " Head position : " + head);

            Iterator<Transition> TransitionsIterator = TransitionSpace.iterator();
            while (TransitionsIterator.hasNext()) {
                Transition nextTransition = TransitionsIterator.next();

                if (nextTransition.readState.equals(CurrentState) && nextTransition.readSymbol == Tape.charAt(head)) {
                    CurrentTransition = nextTransition;
                    CurrentState = CurrentTransition.writeState;

                    char[] tempTape = Tape.toCharArray();
                    tempTape[head] = CurrentTransition.writeSymbol;
                    Tape = new String(tempTape);

                    if (CurrentTransition.Action == 'R' || CurrentTransition.Action == 'r') {
                        head++;

                    } else if (CurrentTransition.Action == 'L' || CurrentTransition.Action == 'l') {
                        head--;
                        if (head < 0) {
                            System.err.println("cannot move left beacause reached start of tape");
                        }
                    } else if (CurrentTransition.Action == 'Y' || CurrentTransition.Action == 'y') {
                        return true;
                    } else if (CurrentTransition.Action == 'N' || CurrentTransition.Action == 'n') {
                        return false;
                    }
                    if (Tape.length() <= head) {
                        Tape = Tape.concat("#");
                    }
                }

            }

        }

    }

    // return myret;
    public void addState(String newState) {
        if (StateSpace.contains(newState)) {
            //do nothing
        } else {
            StateSpace.add(newState);
            //add new state to set of StateSpace
        }
    }

    public void setStartState(String newStartState) {
        if (StateSpace.contains(newStartState))//start state should be one of exsiting states
        {
            StartState = newStartState;
        } else {
            System.err.println("Error: start state should be one of exsiting states");
        }

    }

    public void addTransition(String rState, char rSymbol, String wState, char wSymbol, char Actionn) {
        if (!StateSpace.contains(rState) || !StateSpace.contains(wState)) {
            System.err.println("Error: the states you entered does not exit ");
        }

        Transition newTransition = new Transition();
        newTransition.readState = rState;
        newTransition.readSymbol = rSymbol;
        newTransition.writeState = wState;
        newTransition.writeSymbol = wSymbol;
        newTransition.Action = Actionn;
        TransitionSpace.add(newTransition);

    }

    public void add_aplhabets(char newalpha) {

        if (Symbol_alphabets.contains(newalpha)) {
            //do nothing
        } else {
            Symbol_alphabets.add(newalpha);
            //add new state to set of StateSpace
        }
    }
}
