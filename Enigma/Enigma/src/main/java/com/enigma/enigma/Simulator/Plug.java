package com.enigma.enigma.Simulator;

public class Plug {

    boolean docked;
    String dockedLetter = "";

    public Plug(boolean docked) {
        this.docked = docked;
    }

    public void setDocked() {
        this.docked = true;
    }

    public void setUnDocked() {
        this.docked = false;
    }

    public boolean isDocked() {
        return docked;
    }

    public void setDockedLetter(String letter) {
        dockedLetter = letter;
    }

    public String getDockedLetter() {
        return dockedLetter;
    }
}
