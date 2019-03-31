package com.gainwise.linker;

/**
 * Basic interface to be implemented in the calling code as to provide a way for callbacks on link
 * clicks.
 */
public interface LinkerListener {

    /**
     * This method provides access to the clicked word when implemented.
     * @param charSequenceClicked The word clicked in the text.
     */
    void onLinkClick(String charSequenceClicked);

}
