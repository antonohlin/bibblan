package com.company;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.Scanner;

public class Library {

    Scanner userInput = new Scanner(System.in);
private final Path available = Paths.get("available.txt");
private final Path loaned = Paths.get("loaned.txt");

    public Library(){
        readAvailable();
        readLoaned();
        welcomeScreen();
        userAction();
    }

    private List<String> readAvailable(){
        try { //Försök läsa in alla rader i textfilen available som en ArrayList
            return Files.readAllLines(available);
        } catch (IOException e) { //Om detta inte går, ge användaren ett felmeddelande/diagnos
            e.printStackTrace();
        }
        return new ArrayList<>(); //returnera en arraylist till available (rad 12)
    }
    private List<String> readLoaned(){
        try { //Försök läsa alla rader i textfilen loaned
            return Files.readAllLines(loaned);
        } catch (IOException e) { //Annars felmeddelande/diagnos
            e.printStackTrace();
        }
        return new ArrayList<>(); //returnera en arraylist till loaned (rad 13)
    }

    private void welcomeScreen(){
        System.out.println("Hej!");
        System.out.println("Vad vill du göra?");
        System.out.println("1. Låna en bok");
        System.out.println("2. Lämna tillbaka en bok");
        System.out.println("3. Se en lista över böckerna");
        System.out.println("4. Söka efter en bok");
    }

    private void userAction(){
        int userChoice = userInput.nextInt(); //Användaren får välja 1-4, annars felmeddelande och anropa metoden igen.
        switch (userChoice) {
            case 1: borrowBook();
                break;
            case 2: returnBook();
                break;
            case 3: seeAllBooks(readAvailable(), readLoaned()); //Ta med resultatet från RA() och RL()
                break;
            case 4: searchBook();
                break;
            default: System.out.println("Oj, något blev fel! Gör ett nytt val!");
            userAction();
        }
    }
    private void borrowBook(){
        System.out.println("Lånar bok");
    }
    private void returnBook(){
        System.out.println("Lämna bok");
    }
    private void seeAllBooks(List<String> available, List<String> loaned){
        System.out.println("Lista över böcker");
        for (String bookInfo : available){ //Läs igenom available.txt
            System.out.println(bookInfo);
        }
        for (String bookInfo : loaned){ //Läs igenom loaned.txt
            System.out.println(bookInfo);
        }
    }
    private void searchBook(){
    System.out.println("Sök bok");
    }
}


//         for (String bokInfo : available){
//            System.out.println(bokInfo);
//        }
// För att loopa igenom alla objekt i available

//     System.out.println(PATH_NAME.toAbsolutePath()); byt ut PATH_NAME mot tex available eller loaned för att se exakt vart programmet letar efter filen