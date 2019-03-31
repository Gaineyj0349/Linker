package com.gainwise.linker;

import android.graphics.Color;
import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * This class is used to describe details of a target character sequence.
 */
public class LinkProfile implements Comparable<LinkProfile> {

    private int charSequenceLength;
    private String charSequence;
    private ArrayList<SpanLocation> locations;
    private int linkColor = Color.BLUE;
    private boolean underlineMode = true;


    /**
     * Single constructor to initialize the object with the target sequence and locations of 
     * appearances within the given textview.
     * @param charSequence the target character sequence to make a link within the textview
     */
    protected LinkProfile(String charSequence) {
        this.charSequence = charSequence;
        locations = new ArrayList<SpanLocation>();
    }

    public LinkProfile(String charSequence, int linkColor, boolean underlineMode) {
        this.charSequence = charSequence;
        this.linkColor = linkColor;
        this.underlineMode = underlineMode;
        locations = new ArrayList<SpanLocation>();
        this.charSequenceLength = charSequence.trim().length();
    }

    /**
     * Simple getter for sorting in the update method of Linker class.
     * @return the length of the target character sequence.
     */
    protected int getCharSequenceLength() {
        return charSequenceLength;
    }

    /**
     * Simple getter for linkColor in this profile.
     * @return the link color in int format.
     */
    public int getLinkColor() {
        return linkColor;
    }

    /**
     * Simple setter for link color
     * @param linkColor The desired color for the link in this profile.
     */
    public void setLinkColor(int linkColor) {
        this.linkColor = linkColor;
    }

    /**
     * Simple getter for if this link is to be underlined in the textview.
     * @return true if it is set to be underlined, false otherwise.
     */
    public boolean isUnderlineMode() {
        return underlineMode;
    }

    /**
     * Simple setter for underline mode of this profile's link.
     * @param underlineMode true to underline, false to not underline.
     */
    public void setUnderlineMode(boolean underlineMode) {
        this.underlineMode = underlineMode;
    }

    /**
     * Simple getter for the character sequence.
     * @return The character sequence attached to this profile.
     */
    protected String getCharSequence() {
        return charSequence;
    }

    /**
     * Simple getter for the target character sequence's Span Locations.
     * @return the arraylist of SpanLocations for this current profile.
     */
    protected ArrayList<SpanLocation> getLocations() {
        return locations;
    }

    @Override
    public int compareTo(@NonNull LinkProfile linkProfile) {
        return (this.getCharSequenceLength() < linkProfile.getCharSequenceLength() ? -1 :
                (this.getCharSequenceLength() == linkProfile.getCharSequenceLength() ? 0 : 1));
    }
}
