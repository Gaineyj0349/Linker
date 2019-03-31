package com.gainwise.linker;

import android.support.annotation.NonNull;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

/**
 * This class is part of the interface that allows for calling code to implement a callback feature
 * for links clicked. Its important to note that the clicked links in the textview do not provide the
 * text that was clicked within itself. Instead in the update method in the Linker class, the custom
 * LinkerClickableSpans are created and fed a String (here called charSequence), and a listener that holds
 * actions to perform. So the custom clickable span is attached to the span, and the custom clickable
 * span holds a string. Upon click it triggers the listeners onLinkClick method which in turn has
 * access to the charSequence - this abrstraction ultimately provides it to the calling code in this way.
 */
class LinkerClickableSpan extends ClickableSpan {

    private LinkerListener listener;
    private String charSequence;
    private boolean underLineMode;
    private int linkColor;

    /**
     * One constructor that brings information in about the clicked link.
     * @param charSequence The target word that is to be clicked.
     * @param listener The listener that holds the action to perform. This is where the onLinkClick
     *                 gets fed the character sequence.
     * @param underLineMode Whether the target word should be underlined.
     */
    protected LinkerClickableSpan(String charSequence, LinkerListener listener, boolean underLineMode,
                int linkColor) {
        this.charSequence = charSequence;
        this.listener = listener;
        this.underLineMode = underLineMode;
        this.linkColor = linkColor;
    }

    @Override
    public void onClick(@NonNull View view) {
        if(null!= listener){
            listener.onLinkClick(charSequence);
        }
    }

    @Override
    public void updateDrawState(@NonNull TextPaint ds) {
        super.updateDrawState(ds);

        ds.setUnderlineText(underLineMode);
        ds.setColor(linkColor);
    }
}
