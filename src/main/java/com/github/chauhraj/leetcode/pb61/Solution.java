package com.github.chauhraj.leetcode.pb61;

public class Solution {

    private final static int NO  = 7;
    private final static int YES = 6;
    private final static int SPACE = 5;
    private final static int SIGN = 4;
    private final static int DOT = 3;
    private final static int E = 2;
    private final static int NUM = 1;
    private final static int START = 0;

    private boolean seenNumbers;

    private static interface State {
        public State nextState(State current, char c);
        public State nextState();
    }

    private State[] states = new State[] {new start(),
            new num(),
            new e(),
            new dot(),
            new sign(),
            new spc(),
            new yes(),
            new no()
    };

    private class start implements State {

        private java.util.Set<State> banned = new java.util.HashSet<> ();

        public State nextState() {
            return states[NO];
        }

        public State nextState(State current, char c) {
            if(c == ' ') {
                return states[SPACE];
            } else if (c == '+' || c == '-') {
                return states[SIGN];
            } else if(c >= '0' && c <= '9') {
                return states[NUM];
            } else if(c == '.') {
                return states[DOT];
            } else if(c == 'e') {
                return states[NO];
            }  else {
                return states[NO];
            }
        }
    }

    private class spc implements State {

        private java.util.Set<State> allowed = new java.util.HashSet<> ();
        private java.util.Set<State> banned = new java.util.HashSet<> ();

        public State nextState() {
            if(seenNumbers || allowed.contains(states[NUM])) {
                return states[YES];
            } else {
                return states[NO];
            }
        }

        public State nextState(State prevState, char c) {
            if(c == ' ') {
                return states[SPACE];
            } else if (c == '+' || c == '-') {
                if(banned.contains(states[NUM])) {
                    return states[NO];
                } else {
                    return states[SIGN];
                }
            } else if(c >= '0' && c <= '9') {
                if(seenNumbers || banned.contains(states[NUM])) {
                    return states[NO];
                } else {
                    allowed.add(states[NUM]);
                    banned.add(states[NUM]);
                    return states[NUM];
                }
            } else if(c == '.') {
                if(banned.contains(states[NUM])) {
                    return states[NO];
                } else {
                    return states[DOT];
                }
            } else {
                return states[NO];
            }
        }
    }

    private class num implements State {

        private java.util.Set<State> banned = new java.util.HashSet<> ();

        public State nextState() {
            return states[YES];
        }

        public State nextState(State current, char c) {
            seenNumbers = true;
            if(c == ' ') {
                if(banned.contains(states[SPACE])) {
                    return states[NO];
                } else {
                    spc s = (spc) states[SPACE];
                    banned.add(states[SPACE]);
                    return states[SPACE];
                }
            } else if (c == '+' || c == '-') {
                return states[NO];
            } else if(c >= '0' && c <= '9') {
                return this;
            } else if(c == '.') {
                if(banned.contains(states[DOT])) {
                    return states[NO];
                } else {
                    dot dt = (dot) states[DOT];
                    banned.add(dt);
                    return dt;
                }
            } else if(c == 'e') {
                if(banned.contains(states[E])) {
                    return states[NO];
                } else {
                    banned.add(states[E]);
                    return states[E];
                }
            }  else {
                return states[NO];
            }
        }
    }

    private class dot implements State {

        private java.util.Set<State> banned = new java.util.HashSet<> ();

        public State nextState() {
            if(banned.contains(this))
                return states[NO];
            else if(seenNumbers)
                return states[YES];
            else
                return states[NO];
        }

        public State nextState(State current, char c) {
            banned.add(this);
            if(c == ' ') {
                if (seenNumbers) {
                    return states[SPACE];
                } else {
                    return states[NO];
                }
            } else if (c == '+' || c == '-') {
                return states[NO];
            } else if(c >= '0' && c <= '9') {
                return states[NUM];
            } else if(c == '.') {
                return states[NO];
            } else if(c == 'e') {
                if(seenNumbers) {
                    return states[E];
                } else {
                    return states[NO];
                }
            }  else {
                return states[NO];
            }
        }

    }

    private class e implements State {

        private java.util.Set<State> banned = new java.util.HashSet<> ();

        public State nextState() {
            return states[NO];
        }

        public State nextState(State current, char c) {
            if(c == ' ') {
                return states[NO];
            } else if (c == '+' || c == '-') {
                banned.add(states[SIGN]);
                return states[SIGN];
            } else if(c >= '0' && c <= '9') {
                return states[NUM];
            } else if(c == '.') {
                return states[NO];
            } else if(c == 'e') {
                return states[NO];
            }  else {
                return states[NO];
            }
        }
    }

    private class sign implements State {

        private java.util.Set<State> banned = new java.util.HashSet<> ();

        public State nextState() {
            return states[NO];
        }

        public State nextState(State current, char c) {
            if(c == ' ') {
                return states[NO];
            } else if (c == '+' || c == '-') {
                return states[NO];
            } else if(c >= '0' && c <= '9') {
                return states[NUM];
            } else if(c == '.') {
                return states[NO];
            } else if(c == 'e') {
                return states[NO];
            }  else {
                return states[NO];
            }
        }
    }

    private class no implements State {

        public State nextState() {
            return states[NO];
        }

        public State nextState(State current, char c) {
            return states[NO];
        }
    }

    private class yes implements State {

        public State nextState() {
            return states[NO];
        }

        public State nextState(State current, char c) {
            return states[YES];
        }
    }

    private int currentStateIndex = 0;
    private State currentState = states[currentStateIndex];

    public boolean isNumber (String s) {

        for(char c: s.toCharArray()) {
            State nextState = currentState.nextState(currentState, c);
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
