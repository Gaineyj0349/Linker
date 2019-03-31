package com.gainwise.linker;

import android.support.annotation.NonNull;

/**
 * Objects of this class will hold locations of a target string within a larger string.
 * Simple POJO class that supplements a member of the LinkProfile class.
 */
public class SpanLocation {
    
    /**
     * The beginning index of the string's location.
     */
    private int startIndex;

    /**
     * The ending index of the target string's location.
     */
    private int endIndex;


    /**
     * Constructor must be be given null or negative values.
     * @param startIndex The beginning index of the string's location.
     * @param endIndex The ending index of the target string's location.
     */
    public SpanLocation(@NonNull int startIndex, @NonNull int endIndex) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    /**
     * Basic getter for starting index.
     * @return starting index of the target string's location.
     */
    public int getstartIndex() {
        return startIndex;
    }

    /**
     * Basic setter for starting index.
     * @param startIndex The starting index of the target string's location
     */
    public void setstartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    /**
     * Basic getter for ending index.
     * @return ending index of the target string's location.
     */
    public int getEndIndex() {
        return endIndex;
    }

    /**
     * Basic setter for ending index.
     * @param endIndex The starting index of the target string's location
     */
    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }
}
