package org.vso.views.contracts;

public interface SearchView {

    void show(String text);

    void showTextWithoutNextLine(String text);

    public String getUserTextInput();

    Integer getUserDecimalInput();
}
