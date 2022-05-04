import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

//Makes a file directory that can read edit and write to files and folders.
public class App {
    public static void commands() {
		System.out.println("\nThe avaliable commands are (anything labeled G is avaliable to a guest user): \n\t- list (G) \n\t- cd (G) \n\t- read (G) \n\t- createfile (G) \n\t- createfolder (G) \n\t- delete \n\t- createfolderindirectory (G) \n\t- createfileindirectory (G)");
		System.out.println("\t- rename \n\t- copy \n\t- append \n\t- chmod \n\t- write \n\t- help (G) \n\t- exit (G) \n\n");
	}
	
    public static void main(String[] args) throws Exception {
        Scanner fScanner, fScanner2, scanner = new Scanner(System.in);
        String command = "",user, pass, path;
        String[] arr;
        File file = new File("."), f1, f2;
        File[] files = file.listFiles();
        boolean login = false, admin = false, cond;
        PrintWriter writer;

        //Login process
        while (!login){
        	//Get user name and password
            System.out.print("Username:");
            user = scanner.nextLine();
            System.out.print("Password:");
            pass = scanner.nextLine();
            
            //Admin user mode
            if (user.equals("admin") && pass.equals("password")){
                login = true;
                admin = true;
                System.out.println("Welcome user admin! You are currently viewing this in admin mode.");                
            }
            
            //Guesr user modee
            else {
                login = true;
                admin = false;
                System.out.println("Welcome user " + user + "! You are currently viewing this in guest mode.");
            }
            
            commands();
        }

        //List directory
        System.out.println("Now listing directory: ");
        System.out.println("Name\tType");
        for (File f : files) {
            System.out.print(f.getName());
            if(!f.isFile())
            	System.out.println("  (Directory)");
            if(f.isFile())
            	System.out.println("  (File)");
        }
        
        //Will continue until the user enters "exit"   
        while (!command.equals("exit")) {
        	System.out.print("~ ");
        	command = scanner.nextLine();
        	arr = command.split(" ");
            
            //Exit application (G)
            if (arr[0].equals("exit")) {
            	System.out.println("Goodbye!");
            	System.exit(0);
            } 
        	
            //(G)
            else if(arr[0].equals("help")) {
            	commands();
            }
            
            //To list all files and directories
            else if (arr[0].equals("list")) {
            	System.out.println("Name\tType");
                for (File f : files) {
                    System.out.print(f.getName());
                    if(!f.isFile())
                    	System.out.println("  (Directory)");
                    if(f.isFile())
                    	System.out.println("  (File)");
                }
            }
            
        	//To traverse through the file system (G)
            else if (arr[0].equals("cd")) {
                file = new File(arr[1]);
                files = file.listFiles();
                for (File f : files) {
                    System.out.println(f.getName());
                }
            } 
            
            //Reading a file (G)
            else if (arr[0].equals("read")) {             
                    f1 = new File(arr[1]);
                    try{
                        fScanner = new Scanner(f1); 
                        while (fScanner.hasNextLine()) {
                            System.out.println(fScanner.nextLine());
                        }
                    }
                    catch (Exception e){
                        System.out.println("File not found");
                    }             
            } 
           
            //Functions to create and delete files and folders (G)
            else if(arr[0].equals("createfile")){
            	    File f = new File(arr[1]);
                    f.createNewFile();          
            }
            
            else if(arr[0].equals("createfolder")){          	
            		f1 = new File(arr[1]);
                    f1.mkdir();    
            }
            
            else if(arr[0].equals("delete")){
            	if(admin){    
            		f1 = new File(arr[1]);
            		f1.delete();
            	}
            	
                else{
                    System.out.println("Permission denied: Need to be an admin to delete!");
                }
            }
                   
            
            //Creates in a specific directory (G)
            else if (arr[0].equals("createfolderindirectory")){
                    System.out.println("Enter the path to create a directory: \nExample:./directory/NAME");                    
                    path = scanner.next();
                    
                    f1 = new File(path); //Creating a File object
                    cond = f1.mkdir(); //Creating the directory
                    
                    if(cond){
                        System.out.println("Directory created successfully");
                    }
                    else{
                        System.out.println("Sorry couldn’t create specified directory");
                    }              
            }
            
            else if (arr[0].equals("createfileindirectory")){
                    System.out.println("Enter the path to create a file: \nExample:./directory/file.txt");
                    path = scanner.next();
                    
                    f1 = new File(path);
                    cond = f1.createNewFile();
                    
                    if(cond){
                        System.out.println("File created successfully");
                    }
                   
                    else{
                        System.out.println("Sorry couldn’t create specified file");
                    }
            }
            
            //Deletes in a specific directory 
            else if (arr[0].equals("deleteindirectory")){
                if(admin){
                    System.out.println("Enter the path of the folder: \nExample:./directory/NAME");
                    path = scanner.next();
                   
                    f1 = new File(path);                    
                    cond = f1.delete(); //deleting the directory
                   
                    if(cond){
                        System.out.println("Directory deleted successfully");
                    }
                    
                    else{
                        System.out.println("Sorry couldn’t delete specified directory");
                    }
                }
                
                else
                    System.out.println("Permission denied: Need to be an admin to delete!");               
            }
                                  
            //Renames a file to another name
            else if(arr[0].equals("rename")){   
            	if(admin) {
                    f1 = new File(arr[1]);
                    f1.renameTo(new File(arr[2]));
            	}
            	else
                    System.out.println("Permission denied: Need to be an admin to rename!");
	
            }
            
            //Does not work!
            //Copies from one file to another
            else if(arr[0].equals("copy")){
            	if(admin) {
					f1 = new File(arr[1]);
					f2 = new File(arr[2]);
					try{
					    fScanner = new Scanner(f1); 
					    writer = new PrintWriter(f2);
					    while (fScanner.hasNextLine()) {
					        writer.println(fScanner.nextLine());
					    }
					    writer.close();
					}
				 catch (Exception e){
				     System.out.println("File not found");
				 }
            }
            	else
                    System.out.println("Permission denied: Need to be an admin to copy!");
            }
           
            //Will write to a file without rewriting 
            else if(arr[0].equals("append")){ 
            	if(admin) {
            		f1 = new File(arr[1]);
                    try{
                        writer = new PrintWriter(f1);
                        writer.println(arr[2]);
                        writer.close();
                    }
                    catch (Exception e){
                        System.out.println("File not found");
                    }
            	}
            	else 
                    System.out.println("Permission denied: Need to be an admin to append!");
            }
               
            else if(arr[0].equals("chmod")){    
            	f1 = new File(arr[2]);
            	
            	if(admin) {
	            	if(arr[1].equals("777")){
	            		f1.canRead();
	            		f1.canExecute();
	            		f1.canWrite();
	            		System.out.println(arr[2] + " now has read, write, and execute privleges.");
	            	}
	            	
	            	else if(arr[1].equals("+x")){
	            		f1.canExecute();
	            		System.out.println(arr[2] + " now has execute privleges.");
	            	}
	            	
	            	else
	            		System.out.println("Invalid! Has to be either chmod 777 'filename' or chmod +x 'filename'");
            	}
            	
            	else
                    System.out.println("Permission denied: Need to be an admin to use chmod!");
            }
            
            //Writes a file
            else if (arr[0].equals("write")){
            	if(admin) {
            		f1 = new File(arr[1]);
                    try{
                        writer = new PrintWriter(f1);
                        for(int i = 2; i < arr.length; i++){
                            writer.print(arr[i] + " ");
                        }
                        writer.println();
                        writer.close();
                    }
                    catch (Exception e){
                        System.out.println("File not found");
                    }
            	}
            	else
            		System.out.println("Permission denied: Need to be an admin to write!");
            }

            else
                System.out.println("Error! Command is invalid, try again!");           
        }
    }
}
