package com.company;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.Scanner;

public class Library {
private final Path available = Paths.get("available.txt");
private final Path loaned = Paths.get("loaned.txt");

    public Library(){
        List<String> available = readAvailable();
        List<String> loaned = readLoaned();
        welcomeScreen();
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
}


//         for (String bokInfo : available){
//            System.out.println(bokInfo);
//        }
// För att loopa igenom alla objekt i available

//     System.out.println(PATH_NAME.toAbsolutePath()); byt ut PATH_NAME mot tex available eller loaned för att se exakt vart programmet letar efter filen