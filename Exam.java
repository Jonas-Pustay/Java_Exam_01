import java.io.*;
import java.nio.*;
import java.util.*;
import java.time.*;

import java.time.format.DateTimeFormatter;

public class Exam
    {
        public static ArrayList<Task> listTask = new ArrayList<Task>();
        public static ArrayList<Task> listTaskFile = new ArrayList<Task>();
        public static Scanner sc = new Scanner(System.in);
        
        public static void main(String[] args)
        {
            boolean running = true;

            while (running)
            {
                afficherMenu();
                int number = 0;

                try { number = sc.nextInt(); }
                catch (Exception e) { }

                switch(number)
                {
                    case 1:
                        addTask();
                        break;
                    case 2:
                        removeTask();
                    break;
                    case 3:
                        showFinishTask();
                    break;
                    case 4:
                        saveTaskFile();
                    break;
                    case 5:
                        loadTaskFile();
                    break;
                    case 6:
                        loadTaskByDate();
                    break;
                    case 7:
                        running = false;
                        break;
                    default:
                        System.out.println("Valeur incorrect");
                        break;
                }
            }


        }

        private static void afficherMenu() {
            System.out.println("\nBienvenue dans le gestionnaire de tâche :");
            System.out.println("");
            System.out.println("1 = Ajouter une tâche");
            System.out.println("2 = Supprimer une tâche");
            System.out.println("3 = Afficher la liste des tâches finis");
            System.out.println("4 = Enregistrer la liste des tâches dans un fichier");
            System.out.println("5 = Charger la liste des tâches depuis un fichier");
            System.out.println("6 = Afficher la liste des tâches dont la date est comprise entre 2 date saisies par l'utilisateur");
            System.out.println("7 = Quitter");
            System.out.println("");
            System.out.println("Entrer un chiffre entre 1 et 7");
        }

        private static void addTask() {
            sc = new Scanner(System.in);
            System.out.println("Pour la création de la tâche merci de rentrer les paramétrès de la tâche, exemple : FR48-Création ...-true-dd/MM/yyyy");
            String infoTask = sc.nextLine();
            String[] infos = infoTask.split("-");

            if (infos.length != 4) { System.out.println("Valeur invalid"); addTask(); }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

            listTask.add(new Task(infos[0],infos[1],Boolean.parseBoolean(infos[2]),LocalDate.parse(infos[3], formatter)));
            
            System.out.println("tâche ajouter avec success");
        }
        
        private static void removeTask() {
            
            sc = new Scanner(System.in);
            System.out.println("Pour la suppresion de la tâche merci de rentrer le numéro de la tâche, exemple : FR48");
            String infoTask = sc.nextLine();
            
            int ElementToRemove = 0;
            int i = 0;
            
            for (Task Element : listTask) 
            { 
                System.out.println(Element.getNum() + " - " + infoTask);
                
                
                if (Element.getNum().equals(infoTask))
                    {
                        System.out.println("yep");
                        ElementToRemove = i;
                    }
                
                i++;
            }
            
            listTask.remove(ElementToRemove);
            System.out.println("tâche supprimer avec success");
        }
        
        private static void showFinishTask() {
            
            int i = 1;
            for (Task Element : listTask) 
            { 
                if (Element.getStatus() == true)
                {
                    System.out.println("tâche numéro " + i + " : " + Element.getNum() + " - " + Element.getDescription() + " - " + Element.getStatus() + " - " + Element.getlimitDate());
                }

                i++;
            }
        }
        
        private static void saveTaskFile() {
            String content = "";
            
            for (Task Element : listTask) 
            {
                content += "\n" + Element.getNum() + "-" + Element.getDescription() + "-" + Element.getStatus() + "-" + Element.getlimitDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));;
            }
            
            writeToFile("C:\\Users\\jonas\\Documents\\Visual Studio\\demo\\Task.txt", content, false);
        }
        
        private static void loadTaskFile() {
            List<String> Lines = readLines(new File("Task.txt"));
            
            for (String Element : Lines) 
            {
                
                if (Element.isEmpty() == false)
                {
                    String[] infos = Element.split("-");
                    
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
                    
                    listTaskFile.add(new Task(infos[0],infos[1],Boolean.parseBoolean(infos[2]),LocalDate.parse(infos[3], formatter)));
                    System.out.println("tâche charger : " + infos[0] + " - " + infos[1] + " - " + Boolean.parseBoolean(infos[2]) + " - " + LocalDate.parse(infos[3], formatter));
                }
            }
        }
        
        private static void loadTaskByDate() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
            
            sc = new Scanner(System.in);
            System.out.println("Veuillez rentrer la date de début, exemple : 16/11/2002");
            LocalDate DateStart = LocalDate.parse(sc.nextLine(), formatter);
            
            sc = new Scanner(System.in);
            System.out.println("Veuillez rentrer la date de fin, exemple : 16/11/2002");
            LocalDate DateEnd = LocalDate.parse(sc.nextLine(), formatter);
            
            for (Task Element : listTask) 
            { 
                if (Element.getlimitDate().isBefore(DateStart) || Element.getlimitDate().isAfter(DateEnd))
                {
                    System.out.println("tâche : " + Element.getNum() + " - " + Element.getDescription() + " - " + Element.getStatus() + " - " + Element.getlimitDate());
                }
            }
        }

        private static List<String> readLines(File file) {

            List<String> results = new ArrayList<String>();

            try
            {
                if (!file.exists()) {
                    return new ArrayList<String>();
                }
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line = reader.readLine();
                while (line != null) {
                    results.add(line);
                    line = reader.readLine();
                }
            }
            catch (Exception test)
        {
            System.out.println("error"); 
        }

            return results;
        }

        private static void writeToFile(String path, String content, boolean append) {
            try (FileWriter fileWriter = new FileWriter(path, append))
            {
                fileWriter.write(content);
            } catch (IOException e)
        {
            // exception handling ...
        }
        }
    }