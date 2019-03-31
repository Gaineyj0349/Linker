package com.gainwise.linker;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This class provides access to customizing target links within a given textview.
 */
public class Linker {

    private String textViewText;
    private TextView textView;
    private ArrayList<LinkProfile> profiles;
    private LinkerListener listener;

    /**
     * A single constructor that takes the target textview for linking in as an argument.
     * @param textViewIn the textview that will hold the links.
     */
    public Linker(TextView textViewIn) {
           textView = textViewIn;
           textViewText = textView.getText().toString().toLowerCase().trim();
           profiles = new ArrayList<LinkProfile>();

    }

    /**
     * This will set every link color to the argument provided.
     * @param color The color to repaint all the links to.
     */
    public void setAllLinkColors(int color){
        for(LinkProfile profile : profiles){
            profile.setLinkColor(color);
        }
    }

    /**
     * This will set the link color to the character sequence provided via arguments.
     * @param color The color to repaint all the links to.
     * @param charSequenceIn The character sequence to customize.
     */
    public void setLinkColorForCharSequence(String charSequenceIn, int color){
        for(LinkProfile profile : profiles){
            if(profile.getCharSequence().toLowerCase().equals(charSequenceIn.toLowerCase())){
                Log.i("JOSHY", "met");
                profile.setLinkColor(color);
            }
        }
    }

    /**
     * This will set the link underline mode to the character sequence provided via arguments.
     * @param whether the setting whether to underline or not.
     * @param charSequenceIn The character sequence to customize.
     */
    public void setUnderlineModeForCharSequence(String charSequenceIn, boolean whether){
        for(LinkProfile profile : profiles){
            if(profile.getCharSequence().toLowerCase().equals(charSequenceIn.toLowerCase())){
                profile.setUnderlineMode(whether);
            }
        }
    }


    /**
     * This will set every link underlined or not depending on the to the argument provided.
     * @param whether the setting whether to underline or not.
     */
    public void setAllLinkUnderline(boolean whether){
        for(LinkProfile profile : profiles){
            profile.setUnderlineMode(whether);
        }
    }





    /**
     * Simple getter for the list of LinkProfiles.
     * @return list of profiles
     */
    public ArrayList<LinkProfile> getProfiles() {
        return profiles;
    }

    /**
     * Clears the current list of profiles only.
     */
    public void clearLinksList(){
        profiles.clear();
    }

    /**
     * Overloaded method that takes in an array of Strings as targets to link in the textview's text.
     * @param list The array of target character sequences to link within the textview.
     */
    public void addStrings(String...list){
        for(String charSeq : list){
            if(okToAdd(charSeq)){
                profiles.add(new LinkProfile(charSeq.trim()));
            }
        }
        Log.i("LinkerLib", "addList finished");
    }

    /**
     * This method is used to check whether the list of LinkProfiles already contains a profile with
     * a given character sequence.
     * @param charSequence the target character sequence to highlight in the textview's text.
     * @return true if the list does not already contain the target character sequence.
     */
    private boolean okToAdd(String charSequence){
        boolean flag = true;
        for(LinkProfile profile : profiles){
            if(profile.getCharSequence().toLowerCase().trim().equals(charSequence.toLowerCase().trim())) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    /**
     * Overloaded method that takes in a List of Strings as targets to link in the textview's text.
     * @param list The list of target character sequences to link within the textview.
     */
    public void addStrings(List<String> list){
        for(String charSeq : list){
            if(okToAdd(charSeq)){
                profiles.add(new LinkProfile(charSeq.trim()));
            }
        }
        Log.i("LinkerLib", "addList finished");
    }

    /**
     * Overloaded method that takes in an array of LinkProfiles as targets to link in the textview's text.
     * @param list The array of LinkProfiles to link within the textview.
     */
    public void addProfiles(LinkProfile...list){
        for(LinkProfile profile : list){
            if(okToAdd(profile.getCharSequence())){
                profiles.add(profile);
            }
        }
        Log.i("LinkerLib", "addList finished");
    }

    /**
     * Overloaded method that takes in an List of LinkProfiles as targets to link in the textview's text.
     * @param list The list of LinkProfiles to link within the textview.
     */
    public void addProfiles(List<LinkProfile> list){
        for(LinkProfile profile : list){
            if(okToAdd(profile.getCharSequence())){
                profiles.add(profile);
            }
        }
        Log.i("LinkerLib", "addList finished");
    }



    /**
     * This method sets up the listener for the clicked links. If there was already a listener attached
     * it will be set to null and then reset to the new listener argument.
     * @param listener The listener that holds the desired action to perform upon click of target links.
     */
    public void setListener(LinkerListener listener) {
        if(null != this.listener){
            this.listener = null;
        }
        this.listener = listener;
    }


    /**
     * This method is the last to be called as it will find and create the links, and format the textview,
     * based off of the setup of LinkProfiles provided within the Linker.
     */
    public void update(){
        SpannableString spannableString = new SpannableString(textView.getText().toString().trim());

        Collections.sort(profiles, new Comparator<LinkProfile>() {
            @Override
            public int compare(LinkProfile linkProfile, LinkProfile t1) {
                return t1.compareTo(linkProfile);
            }
        });
        ArrayList<SpanLocation> accumulatedSpans = new ArrayList<>();
        for (int i = 0; i< profiles.size(); i++) {
            Utils.setSpanLocations(accumulatedSpans, profiles.get(i).getLocations(),
                    profiles.get(i).getCharSequence().toLowerCase(), textViewText);

            String link = profiles.get(i).getCharSequence();

            ArrayList<SpanLocation> indexList = profiles.get(i).getLocations();
            Log.i("LinkerLib", "indexList size: " + indexList.size());

            for (int j = 0; j<indexList.size(); j++) {
                LinkerClickableSpan customSpanner = new LinkerClickableSpan(link, listener,
                        profiles.get(i).isUnderlineMode(), profiles.get(i).getLinkColor());
                spannableString.setSpan(
                        customSpanner, indexList.get(j).getstartIndex(),
                        indexList.get(j).getEndIndex(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                );
            }
        }

        textView.setHighlightColor(Color.TRANSPARENT); // prevent TextView change background when highlight
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setText(spannableString, TextView.BufferType.SPANNABLE);
    }




}
