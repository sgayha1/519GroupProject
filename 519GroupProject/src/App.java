import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

//Makes a file directory that can read edit and write to files and folders.
public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        String[] inputArray;
        File file = new File(".");
        File[] files = file.listFiles();
        boolean login = false;
        boolean admin = false;

        while (!login){
            System.out.print("Username:");
            String usernameInput = scanner.nextLine();
            System.out.print("Password:");
            String passwordInput = scanner.nextLine();
            if (usernameInput.equals("admin") && passwordInput.equals("password")){
                login = true;
                admin = true;
                System.out.println("Welcome you are currently viewing this in admin mode");
            }
            else {
                login = true;
                admin = false;
                System.out.println("Welcome you are currently viewing this in guest mode");
            }
        }

        for (File f : files) {
            System.out.println(f.getName());
        }
        while (!input.equals("exit")) {
            input = scanner.nextLine();
            inputArray = input.split(" ");
            if (inputArray[0].equals("cd")) {
                file = new File(inputArray[1]);
                files = file.listFiles();
                for (File f : files) {
                    System.out.println(f.getName());
                }
            } else if (inputArray[0].equals("ls")) {
                for (File f : files) {
                    System.out.println(f.getName());
                }
            }else if (inputArray[0].equals("exit")) {
                System.exit(0);
            } else if (inputArray[0].equals("cat") || inputArray[0].equals("read")) {
                if(admin){
                    File f = new File(inputArray[1]);
                    try{
                        Scanner fileScanner = new Scanner(f); 
                        while (fileScanner.hasNextLine()) {
                            System.out.println(fileScanner.nextLine());
                        }
                    }
                    catch (Exception e){
                        System.out.println("File not found");
                    }
                }
                else{
                    System.out.println("You do not have permission to view this file");
                }
            } else if (inputArray[0].equals("touch")) {
                if(admin){
                    File f = new File(inputArray[1]);
                    f.createNewFile();
                }
                else{
                    System.out.println("You do not have permission to create this file");
                }
            } else if (inputArray[0].equals("rm")) {
                if (admin){
                    File f = new File(inputArray[1]);
                    f.delete();
                }
                else{
                    System.out.println("You do not have permission to delete this file");
                }
            } else if(inputArray[0].equals("createfile")){
                if(admin){
                    File f = new File(inputArray[1]);
                    f.createNewFile();
                }
                else{
                    System.out.println("You do not have permission to create this file");
                }
            }else if(inputArray[0].equals("createfolder")){
                if(admin){
                    File f = new File(inputArray[1]);
                    f.mkdir();
                }
                else{
                    System.out.println("You do not have permission to create this folder");
                }
            }else if(inputArray[0].equals("deletefolder")){
                if(admin){
                    File f = new File(inputArray[1]);
                    f.delete();
                }
                else{
                    System.out.println("You do not have permission to delete this folder");
                }
            }else if(inputArray[0].equals("deletefile")){
                if(admin){
                    File f = new File(inputArray[1]);
                    f.delete();
                }
                else{
                    System.out.println("You do not have permission to delete this file");
                }
            }else if(inputArray[0].equals("rename")){
                if(admin){
                    File f = new File(inputArray[1]);
                    f.renameTo(new File(inputArray[2]));
                }
                else{
                    System.out.println("You do not have permission to rename this file");
                }
            }else if(inputArray[0].equals("copy")){
                if(admin){
                    try{
                        File f = new File(inputArray[1]);
                        File f2 = new File(inputArray[2]);
                        f.renameTo(f2);
                    }
                    catch(Exception e){
                        System.out.println("File not found");
                    }
                }
                else{
                    System.out.println("You do not have permission to copy this file");
                }
            }else if(inputArray[0].equals("move")){
                if(admin){
                    try{
                        File f = new File(inputArray[1]);
                        File f2 = new File(inputArray[2]);
                        f.renameTo(f2);
                    }
                    catch(Exception e){
                        System.out.println("File not found");
                    }
                }
                else{
                    System.out.println("You do not have permission to move this file");
                }
            }else if(inputArray[0].equals("append")){
                if(admin){
                    File f = new File(inputArray[1]);
                    f.renameTo(new File(inputArray[2]));
                }
                else{
                    System.out.println("You do not have permission to append this file");
                }
            }else if(inputArray[0].equals("write")){
                if(admin){
                    try{
                        File f = new File(inputArray[1]);
                        Scanner fileScanner = new Scanner(f); 
                        while (fileScanner.hasNextLine()) {
                            System.out.println(fileScanner.nextLine());
                        }
                    }
                    catch (Exception e){
                        System.out.println("File not found");
                    }
                }
                else{
                    System.out.println("You do not have permission to write to this filae");
                }
            }
            //Creates a folder in a specific directory 
            else if (inputArray[0].equals("createfolderindirectory")){
                if(admin){
                    System.out.println("Enter the path to create a directory: \nExample:./directory/NAME");
                    Scanner sc = new Scanner(System.in);
                    String path = sc.next();
                    //Creating a File object
                    File f = new File(path);
                    //Creating the directory
                    boolean bool = f.mkdir();
                    if(bool){
                        System.out.println("Directory created successfully");
                    }else{
                        System.out.println("Sorry couldn’t create specified directory");
                    }
                }
                else{
                    System.out.println("You do not have permission to create this file");
                }
            }
            else if (inputArray[0].equals("createfileindirectory")){
                if(admin){
                    System.out.println("Enter the path to create a file: \nExample:./directory/file.txt");
                    Scanner sc = new Scanner(System.in);
                    String path = sc.next();
                    //Creating a File object
                    File f = new File(path);
                    //Creating the file
                    boolean bool = f.createNewFile();
                    if(bool){
                        System.out.println("File created successfully");
                    }else{
                        System.out.println("Sorry couldn’t create specified file");
                    }
                }
                else{
                    System.out.println("You do not have permission to create this file");
                }
            }
            //Deletes a folder in a specific directory 
            else if (inputArray[0].equals("deletefolderindirectory")){
                if(admin){
                    System.out.println("Enter the path of the folder: \nExample:./directory/NAME");
                    Scanner sc = new Scanner(System.in);
                    String path = sc.next();
                    //Creating a File object
                    File f = new File(path);
                    //deleting the directory
                    boolean bool = f.delete();
                    if(bool){
                        System.out.println("Directory deleted successfully");
                    }else{
                        System.out.println("Sorry couldn’t delete specified directory");
                    }
                }
                else{
                    System.out.println("You do not have permission to create this file");
                }
            }
            else if (inputArray[0].equals("deletefileindirectory")){
                if(admin){
                    System.out.println("Enter the path to delete a file: \nExample:./directory/file.txt");
                    Scanner sc = new Scanner(System.in);
                    String path = sc.next();
                    //Creating a File object
                    File f = new File(path);
                    //deleting the file
                    boolean bool = f.createNewFile();
                    if(bool){
                        System.out.println("File deleted successfully");
                    }else{
                        System.out.println("Sorry couldn’t create specified file");
                    }
                }
                else{
                    System.out.println("You do not have permission to create this file");
                }
            }
            else{
                System.out.println("Invalid command");
            }
        }
    }
}
