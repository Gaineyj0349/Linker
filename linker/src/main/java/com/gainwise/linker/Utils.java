package com.gainwise.linker;

import android.util.Log;

import java.util.ArrayList;

/**
 * This basic package private Utility class will hold useful methods as the library grows.
 */
class Utils {

    /**
     * This method loops through the text and finds all locations of a target character sequence.
     * This method also will remove any of the overlapping spans, giving preference to the longer
     * target character sequence.
     * @param list The collection of SpanLocations to add the findings to.
     * @param charSequence The target character sequence to locate.
     * @param textViewText The text to search through.
     */
    protected static void setSpanLocations(ArrayList<SpanLocation> accumulatedSpans, ArrayList<SpanLocation> list,
                                                            String charSequence, String textViewText){

        Log.i("Linkeroo33", "word " + charSequence);
        int trackerIndex = -2;
        int lastIndex = 0;

        while (trackerIndex != -1 ) {
            lastIndex = trackerIndex;
            trackerIndex = textViewText.toLowerCase().indexOf(charSequence.toLowerCase(), trackerIndex);
            if (trackerIndex != -1 && lastIndex != trackerIndex) {
                SpanLocation span = new SpanLocation(trackerIndex,trackerIndex + charSequence.length());
                if(accumulatedSpans.size() == 0){
                    accumulatedSpans.add(span);
                    list.add(span);
                    //increase the trackerIndex so next indexOf call starts searching at the end of the
                    //last found word
                    trackerIndex +=  charSequence.length();
                    continue;
                }
                int initialSpanSize = accumulatedSpans.size();
                boolean goodToAdd = false;

                for(int j = 0; j< initialSpanSize; j++){
                    if((trackerIndex >= accumulatedSpans.get(j).getstartIndex()) &&
                            (trackerIndex <= accumulatedSpans.get(j).getEndIndex())){
                        //span already taken - flag and break
                        goodToAdd = false;
                        break;
                    }else{
                        goodToAdd = true;
                    }
                }
                if(goodToAdd){
                    list.add(span);
                    accumulatedSpans.add(span);
                }

                //increase the trackerIndex so next indexOf call starts searching at the end of the
                //last found word
                trackerIndex +=  charSequence.length();

            }else{
                break;
            }
        }
    }


}
