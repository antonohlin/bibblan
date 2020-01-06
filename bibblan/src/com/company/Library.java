package com.company;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.Scanner;

public class Library {

    Scanner userInput = new Scanner(System.in);
private final Path available = Paths.get("available.txt"); //dessa läses bara in en gång när programmet startas och ska inte förändras under programmets gång, där av final.
private final Path loaned = Paths.get("loaned.txt");

    public Library(){
        readAvailable(); //Läs in available.txt
        readLoaned(); //Läs in loaned.txt
        welcomeScreen(); //Prita välkommsttext för användaren
        userAction(); //Invänta användarens val
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
            case 1: borrowBook(readAvailable(), readLoaned());
                break;
            case 2: returnBook();
                break;
            case 3: seeAllBooks(readAvailable(), readLoaned()); //Skicka med våra listor över böcker
                break;
            case 4: searchBook(readAvailable(), readLoaned()); //Skicka med våra listor över böcker
                break;
            default: System.out.println("Oj, något blev fel! Gör ett nytt val!");
            userAction();
        }
    }
    private void borrowBook(List<String> available, List<String> loaned){

        System.out.println("Ange ID på den bok du vill låna: ");
        int chosenBookId = (userInput.nextInt() - 1); //Få en position av användaren och ta sedan bort 1 för att matcha det med platsen i arrayen (För att användaren ska slippa räkna med talet 0
        System.out.println("Vill du låna boken: " + available.get(chosenBookId) + "? Y/N");
        char borrow = (userInput.next().charAt(0)); //Låter användaren svara yes/y
        if (borrow == 'y' || borrow == 'Y'){
            System.out.println("lånad");
            System.out.println("Flyttad till loaned.txt");
            loaned.add(available.get(chosenBookId)); //Addera till loaned och ta sedan bort från available, OBS, funkar ej!
            available.remove(available.get(chosenBookId));
        } else if (borrow == 'n' || borrow == 'N'){ //Eller no/n
            System.out.println("Inte lånad");
        } else {
            System.out.println("felaktig inmatning");
            borrowBook(readAvailable(), readLoaned());
        }
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
    private String searchBook(List<String> available, List<String> loaned){
        boolean availableMatch = false; //Har någon match hittats i tillgängliga?
        boolean loanedMatch = false; //Har någon match hittats i utlånade?
        int hits = 0; //Räkna antalet matchande sökträffar
    System.out.println("Skriv in en del av författarens namn eller titeln på en bok:");
    String userSearch = userInput.next(); //Nästa fras blir sökfrasen
        for (String stringSearch : available) { //Loopa igenom available.txt efter sökfrasen
            if (stringSearch.toLowerCase().contains(userSearch.toLowerCase())) { //om matchning printa fras + träff
                System.out.println(stringSearch); //Printa den hittade titeln
                availableMatch = true; //You've got a match <3
                hits++; //+1 på totala träffarna
            }
        }
        if (availableMatch){ //Visas bara om vi fått en matchning
            System.out.println("Sökfrasen matchade " + hits + " av våra tillgängliga böcker.");
        }
        hits = 0;//Återställ träffar för att söka igenom loaned.txt

        for (String stringSearch : loaned) { //Loopa igenom loaned.txt efter sökfrasen
            if (stringSearch.toLowerCase().contains(userSearch.toLowerCase())) { //Om matchning printa fras + träff
                System.out.println(stringSearch);
                loanedMatch = true; //Match
                hits++; //träffar +1
            }
        }
        if (loanedMatch){
            System.out.println("Sökfrasen matchade " + hits + " av våra utlånade böcker.");
        }
        if (!availableMatch && !loanedMatch){ //Hit kommer vi bara om den varken träffar i available eller loaned
            System.out.println("Tyvärr din sökfras gav ingen träff");
        }
        return null; //Returnera inget då resultatet printas direkt.
    }
}


//         for (String bokInfo : available){
//            System.out.println(bokInfo);
//        }
// För att loopa igenom alla objekt i available

//     System.out.println(PATH_NAME.toAbsolutePath()); byt ut PATH_NAME mot tex available eller loaned för att se exakt vart programmet letar efter filen