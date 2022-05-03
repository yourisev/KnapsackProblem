package com.company;

import java.util.Comparator;

public class TrackComparator implements Comparator<Track>{

    public int compare(Track first,Track second){

        return Integer.compare(first.getSection(),second.getSection());
    }
}
