package com.company;

public class Track {
    private Bound pair;
    private int section;

    public Track(Bound pair, int section) {
        this.pair = pair;
        this.section = section;
    }

    public Bound getPair() {
        return pair;
    }

    public void setPair(Bound pair) {
        this.pair = pair;
    }

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
    }

}
