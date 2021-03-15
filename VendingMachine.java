
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;



class VendingMachine{

     HashMap<Character,Integer> hm = new HashMap<Character, Integer>(); //used to convert row letters to numbers
    Scanner s = new Scanner(System.in); //used for input
    VendingSnack[][] inventory; //keeps track of items in VendingMachine
    File record; //Used to record transactions
    File i;//Used to represent the input
    FileWriter fw;
    FileReader fr;
    StringBuffer sb;
    int totalRows, totalColumns;
    /**
     * Constructor for VendingMachine
     */
    public VendingMachine(){
        try{
        record = new File("Transactions.txt");
        i = new File("input.json");
        readInput();
        setRowsAndColumns();
        setMachine();
        fw = new FileWriter(record);
        boolean done = false;
        while (done == false){ //Allows the user to enter combinations until they are done
            System.out.println("Enter combination to select a snack. Example: A1 for the snack in the first row and first column. Enter 0 to exit.");
            String select = s.next();
            if(select.length() == 2){
                try{
                int r = hm.get(select.charAt(0));
                 int c = Character.getNumericValue(select.charAt(1)) - 1;
                 calculatePayment(r, c);
            }
            catch(NullPointerException e){
                System.out.printf("Row at %c does not exist. Try another combination.\n", select.charAt(0));
            }
        }
            else if(select.equals("0")){ //Exits the program
                done = true;
            }
            else{
                System.out.println("Invalid combination.");
            }
        }
        fw.close();
    }
    catch(IOException e){
        System.out.println("Error with Transaction file");
    }
    }
public static void main (String[] args){
    new VendingMachine();
}

 /**
     * readInput Takes input file and sends it into a StringBuffer for creating VendingSnacks.
     */
private void readInput(){
    try{
    char[] c = new char[10000];
    fr = new FileReader(i);
    fr.read(c);
    fr.close();
    sb = new StringBuffer();
    sb.append(c);
    sb.trimToSize();
    for(int x = 0; x <sb.length(); x++){ //Takes out all of the quotation marks to trim the input
        Character ch = sb.charAt(x);
    if(ch.equals('"') == true){
        sb.setCharAt(x, ' ');
    }
    }
        }
catch (IOException e) {
    System.out.println("Error with FileReader");
        }
}


 /**
     * setMachine creates VendingSnack objects from the given input
     */
private void setMachine(){
    boolean b = false;

    if(sb.toString().contains("items") == true){
        sb.delete(0, sb.indexOf("items"));
        int currentRow = 0; 
        int currentColumn = 0;

    while(b == false){
        if(sb.toString().contains("name") == true && sb.toString().contains("amount") == true 
        && sb.toString().contains("price") == true){
            String[] properties = new String[3];
            properties[0] = sb.substring(sb.indexOf("name"), sb.indexOf("amount")); //represents the name
            properties[1] = sb.substring(sb.indexOf("amount"), sb.indexOf("price")); //represents the amount
            properties[2] = sb.substring(sb.indexOf("price"), sb.indexOf("}")); //represents the price
            setSnack(properties,currentRow,currentColumn);

            sb.delete(sb.indexOf("name"), sb.indexOf("}")+1); //allows the program to move on to the next snack
            if(currentColumn >= totalColumns-1) //Program goes through columns first before moving on to the next row
            {
                currentColumn = 0;
                currentRow++;
            }
            else{
                currentColumn++;
            }
        }
        else{
            b = true;
        }
    }
}
}
/**
     * setRowsAndColumns Takes the specified number of rows and columns and creates a 2D array
     * carrying the VendingSnack objects with them.
     */
private void setRowsAndColumns(){
    String s = sb.toString();

    if (s.contains("rows") == true && s.contains("columns") == true){
        String r = s.substring(s.indexOf("rows"), s.indexOf("columns")); 
        r = r.replaceAll("rows", ""); r = r.replace(':', ' '); //Used to trim the string to get only the int value
        r = r.replace(',', ' '); r = r.trim();

        String c = s.substring(s.indexOf("columns"), s.indexOf("}"));
        c = c.replaceAll("columns", "");
        c = c.replace(':', ' '); c = c.replace(',', ' '); c = c.trim();

        totalRows = Integer.parseInt(r);
        totalColumns = Integer.parseInt(c);

        inventory = new VendingSnack[totalRows][totalColumns];
        setRowLetters(Integer.parseInt(r));
    }
}
/**
     * setMachine creates VendingSnack objects from the given input
     */
private void setRowLetters(int numRows){
    char c = 'A'; 
    for(int i = 0; i<numRows;i++){
        hm.put(c, i);
        c += 1;
    }
}
/**
     * calculatePayment calculates the transaction for the selected snack.
     * 
     * @param properties carries the name, amount, and price information needed to create the VendingSnack object.
     * @param r the selected row of the vending machine
     * @param c the selected column of the vending machine
     */
private void setSnack(String[] properties, int r, int c){
    String name = "";
    int amount = 0;
    double price = 0;
    for (String s : properties) {
        
        if(s.contains("name") == true){
            s = s.replaceAll("name", ""); //Trimming the string to get only the needed value for name
            s = s.replace(':', ' '); s = s.replace(',', ' ');
            s = s.replace('"', ' '); s = s.trim();

            name = s;
        }
        else if(s.contains("amount") == true){
            s = s.replaceAll("amount", "");
            s = s.replace(':', ' '); s = s.replace(',', ' ');
            s = s.replace('"', ' '); s = s.trim();

            amount = Integer.parseInt(s);
        }
        else if(s.contains("price") == true){
            s = s.replaceAll("price", "");
            s = s.replace(':', ' '); s = s.replace(',', ' ');
            s = s.replace('"', ' ');s = s.replace('$', ' '); s = s.trim();

            price = Double.parseDouble(s);
        }
    }
    inventory[r][c] = new VendingSnack(name, amount, price);
}
 /**
     * calculatePayment calculates the transaction for the selected snack.
     * 
     * @param row the selected row of the vending machine
     * @param column the selected column of the vending machine
     */
public void calculatePayment(int r, int c){
    try{
        
        if(inventory[r][c].getAmount() > 0){//Checks to make sure that there is still a snack left to purchase
            double price = inventory[r][c].getPrice();
   
            System.out.printf("Selected %s. \n", inventory[r][c].getName());
            System.out.printf("Total price is: $%.2f.\n", price);

            boolean hasPaid = false;
            while(hasPaid == false){

                System.out.println("Please enter payment amount.");
                double payment = s.nextDouble();
                if(payment >= price){ //If the entered payment covers the price
                    double change = payment - price;
                    System.out.printf("Thank you. Your change is: %.2f.\n", change);
                    hasPaid = true;
                    recordTransaction(r, c, price, payment, change);
                    inventory[r][c].subtractAmount();
                                    }
        else{
                System.out.println("Payment is less than price.");
            }
    }
}
else{ //if the snack has run out
    System.out.println("This snack has run out.");
}
}
catch(NullPointerException e){//If the combination does not have a snack
    System.out.println("No snack found. Try another combination.");
}
}
 /**
     * recordTransaction records the current transaction into the txt file
     * 
     * @param row the selected row of the vending machine
     * @param column the selected column of the vending machine
     */
private void recordTransaction(int r, int c, double price, double payment, double change){
    try {
        String name = inventory[r][c].getName();
        String s = String.format("Transaction: %s purchased. Payment: $%.2f. Total Change: $%.2f. \n", name, payment, change);
        fw.append(s);
    } catch (IOException e) {
        System.out.println("Error with FileWriter.");
    }
}
}