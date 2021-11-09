import java.rmi.registry.LocateRegistry; 
import java.rmi.registry.Registry;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;  

public class Client {  
   private Client() {}  
   public static void main(String[] args) {  
      try {  
         Scanner input = new Scanner(System.in);
         // Getting the registry 
         Registry registry = LocateRegistry.getRegistry(null); 
    
         // Looking up the registry for the remote object 
         Hello stub = (Hello) registry.lookup("Hello"); 
         int choice;
         String userInput;
         do {
            choice = 0;

            System.out.println("Choose 1. Current Date | 2. Quadratic Solvers | " +
                              "3. String Reverser | 4. Decimal Base Converter | " + 
                              "5. Equation Bracket Checker | 9. To Exit");
            
            System.out.print("Choice:");
            choice = input.nextInt();
            if (choice == 9) {
               break;
            }
            switch (choice) {
               // check and respond to user for the first option
               case 1:
                  System.out.println(stub.printDate());
                  break;

               // respond for second option and run the quadratic solver 
               case 2: 
                  System.out.println("Hello! Welcome to Quadratic Equation Solver!" +
                  "Please enter a, b, and c, coefficients separated by a comma(','):");
                  System.out.print("Enter coefficients: " );
                  // get user input
                  userInput = input.next();

                  // breaks users input into data
                  int[] coefficients = parseInput(userInput);
                  System.out.println(stub.calculateRoots(coefficients));
                  break;
               
               // respond for third option and run the reverse string service 
               case 3:
                  System.out.print("Enter string: ");
                  userInput = input.next();
                  System.out.print("The resulting reversed string is \" ");
                  System.out.print(stub.flipString(userInput) + "\"\n");
                  break;
               
               // respond for fourth option and run the binary conversion server
               case 4:
                  System.out.print("Enter a number: ");
                  int num = input.nextInt();
                  System.out.println("The converted decimal number is " + stub.decimalBaseConverter(num) + " in binary");
                  break;

               // respond for fifth service to check bracket balance 
               case 5:
                  System.out.print("Enter an equation to be checked: ");
                  userInput = input.next();
                  System.out.println(stub.checkEquation(userInput));
                  break;
               
            }
         } while(choice != 9);
         
         // System.out.println("Remote method invoked"); 
      } catch (Exception e) {
         System.err.println("Client exception: " + e.toString()); 
         e.printStackTrace(); 
      } 
   } 

   // method for parsing a comma delimited string for further use
   public static int[] parseInput(String userInput) {
      String[] values = userInput.split(",");
      int[] result = new int[3];

      for (int i = 0; i < values.length; i++) {
          result[i] = Integer.parseInt(values[i]);
      }

      return result;
  }
}