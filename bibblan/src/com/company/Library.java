package com.company;

public class Library {
    public Library(){
        welcomeScreen();
    }

    private void welcomeScreen(){
        System.out.println("Hej!");
        System.out.println("Vad vill du göra?");
        System.out.println("1. Låna en bok");
        System.out.println("2. Lämna tillbaka en bok");
        System.out.println("3. Se en lista över böckerna");
        System.out.println("4. Söka efter en bok");
    }


}
