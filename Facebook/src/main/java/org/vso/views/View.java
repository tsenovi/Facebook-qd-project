package org.vso.views;

import java.util.Scanner;

public class View {
    private final Scanner scanner;

    public View() {
        this.scanner = new Scanner(System.in);
    }

    public void show(String text) {
        System.out.println(text);
    }

    public void showTextWithoutNextLine(String text){
        System.out.print(text);
    }

    public void showNumber(int number){
        System.out.print(number);
    }

    public String getUserTextInput() {
        return scanner.nextLine();
    }

    public Integer getUserDecimalInput() {
        return Integer.parseInt(getUserTextInput());
    }

}
