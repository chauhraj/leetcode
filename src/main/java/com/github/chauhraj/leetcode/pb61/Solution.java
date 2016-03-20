package com.github.chauhraj.leetcode.pb61;

public class Solution {

    private final static int N4 = 11;
    private final static int S2 = 10;
    private final static int S1 = 9;
    private final static int E = 8;
    private final static int NO  = 7;
    private final static int YES = 6;
    private final static int D2 = 5;
    private final static int D1 = 4;
    private final static int N3 = 3;
    private final static int N2 = 2;
    private final static int N1 = 1;
    private final static int START = 0;

    private static interface State {
        public State nextState(char c);
        public State nextState();
    }

    private State[] states = new State[] {new start(),
            new n1(),
            new n2(),
            new n3(),
            new d1(),
            new d2(),
            new yes(),
            new no(),
            new e(),
            new s1(),
            new s2(),
            new n4()
    };

    private class start implements State {
        public State nextState() {
            return states[NO];
        }

        public State nextState(char c) {
            if(c == '+' || c == '-') {
                return states[S1];
            } else if(Character.isDigit(c)) {
                return states[N1];
            } else if(c == '.') {
                return states[D1];
            } else {
                return states[NO];
            }
        }
    }
    private class n1 implements State {
        public State nextState() {
            return states[YES];
        }

        public State nextState(char c) {
            if(c == '+' || c == '-') {
                return states[NO];
            } else if(Character.isDigit(c)) {
                return states[N1];
            } else if(c == '.') {
                return states[D2];
            } else if(c == 'e') {
              return states[E];
            } else {
                return states[NO];
            }
        }
    }
    private class n2 implements State {
        public State nextState() {
            return states[YES];
        }

        public State nextState(char c) {
            if(Character.isDigit(c)) {
                return this;
            } else if(c == 'e') {
                return states[E];
            } else {
                return states[NO];
            }
        }
    }
    private class n3 implements State {
        public State nextState() {
            return states[YES];
        }

        public State nextState(char c) {
            if(Character.isDigit(c)) {
                return this;
            } else {
                return states[NO];
            }
        }
    }
    private class n4 implements State {
        public State nextState() {
            return states[YES];
        }

        public State nextState(char c) {
            if(Character.isDigit(c)) {
                return states[N1];
            } else {
                return states[NO];
            }
        }
    }
    private class d1 implements State {
        public State nextState() {
            return states[NO];
        }

        public State nextState(char c) {
            if(Character.isDigit(c)) {
                return states[N2];
            } else {
                return states[NO];
            }
        }
    }
    private class d2 implements State {
        public State nextState() {
            return states[YES];
        }

        public State nextState(char c) {
            if(Character.isDigit(c)) {
                return states[N2];
            } else if(c == 'e') {
                return states[E];
            } else {
                return states[NO];
            }
        }
    }

    private class e implements State {
        public State nextState() {
            return states[NO];
        }

        public State nextState(char c) {
            if(c == '+' || c == '-') {
                return states[S2];
            } else if(Character.isDigit(c)) {
                return states[N3];
            } else {
                return states[NO];
            }
        }
    }

    private class s1 implements State {
        public State nextState() {
            return states[NO];
        }

        public State nextState(char c) {
            if(Character.isDigit(c)) {
                return states[N1];
            } else if(c == '.') {
                return states[D1];
            } else {
                return states[NO];
            }
        }
    }

    private class s2 implements State {
        public State nextState() {
            return states[NO];
        }

        public State nextState(char c) {
            if(Character.isDigit(c)) {
                return states[N4];
            } else {
                return states[NO];
            }
        }
    }

    private class no implements State {

        public State nextState() {
            return states[NO];
        }

        public State nextState(char c) {
            return states[NO];
        }
    }

    private class yes implements State {

        public State nextState() {
            return states[YES];
        }

        public State nextState(char c) {
            return states[YES];
        }
    }

    private State currentState = states[0];

    public boolean isNumber (String s) {
        char[] chars = s.toCharArray();
        int startIndex = 0;
        int endIndex = s.length() - 1;
        while(startIndex < chars.length) {
            if(chars[startIndex] == ' ') {
                startIndex++;
                continue;
            }
            break;
        }
        while(endIndex > 0) {
            if(chars[endIndex] == ' ') {
                endIndex--;
                continue;
            }
            break;
        }
        for(int i = startIndex; i <= endIndex; i++) {
            State nextState = currentState.nextState(chars[i]);
            if(nextState == states[NO]) {
                //System.out.println("Inside for:" + nextStateIndex + ", prevState:" + currentStateIndex);
                return false;
            }
            currentState = nextState;
        }
        //System.out.println("Inside for:" + currentStateIndex);
        return currentState.nextState() == states[YES];
    }
}
