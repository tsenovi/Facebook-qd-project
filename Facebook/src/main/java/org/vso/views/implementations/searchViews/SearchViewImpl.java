package org.vso.views.implementations.searchViews;
import org.vso.views.contracts.SearchView;

import java.util.Scanner;

public class SearchViewImpl implements SearchView {
    private final Scanner scanner;

    public SearchViewImpl() {
        this.scanner = new Scanner(System.in);
    }

    public void show(String text) {
        System.out.println(text);
    }

    public void showTextWithoutNextLine(String text){
        System.out.print(text);
    }

    public String getUserTextInput() {
        return scanner.nextLine();
    }

    public Integer getUserDecimalInput() {
        return Integer.parseInt(getUserTextInput());
    }

}
