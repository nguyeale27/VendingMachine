
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;



class VendingMachine{
     //rows would be easier to get with enum statement
     HashMap<Character,Integer> hm = new HashMap<Character, Integer>(); //used to convert row letters to numbers
    Scanner s = new Scanner(System.in); //used for input
    VendingSnack[][] inventory; //keeps track of items in VendingMachine
    File record, i;
    FileWriter fw;
    FileReader fr;
    StringBuffer sb;

    /**
     * 
     */
    public VendingMachine(){
        record = new File("Transactions.txt");
        i = new File("input.json");
        readInput();
        setRowsAndColumns();
        //setMachine();
        boolean done = false;
        while (done == false){
            System.out.println("Enter Combination to select a snack. Enter 0 to exit.");
            String select = s.next();
            if(select.length() == 2){
                
            }
            else if(select.equals("0")){
                done = true;
            }
            else{
                System.out.println("Invalid combination.");
            }
        }
    }
public static void main (String[] args){
    VendingMachine vm = new VendingMachine();
    //vm.inventory[0][0] = new VendingSnack("Snickers", 10, 1.35);
    //vm.calculatePayment(0,0);
}
 /**
     * readInput Takes input file and sends it into a StringBuffer for creating VendingSnacks.
     */
private void readInput(){
    try{
    char[] c = new char[100000];
    fr = new FileReader(i);
    fr.read(c);
    fr.close();
    sb = new StringBuffer();
    sb.append(c);
    for(int x = 0; x <sb.length(); x++){
        Character ch = sb.charAt(x);
    if(ch.equals('"') == true){
        sb.setCharAt(x, ' ');
    }
    }
        }
catch (IOException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
        }
}


 /**
     * setMachine creates VendingSnack objects from the given input
     */
private void setMachine(){
//Read StringBuffer, s.indexOf();get sb.charAt, cut from start to where charAt is
    //sb.substring(start)
    String s = sb.toString();
    //s.indexOf(str)
    String name = "name";
    String amount = "amount";
    boolean b = false;
    sb.indexOf(name);
    while(b == false){
        if(s.contains("name") == true){
            //System.out.println(sb.substring(sb.indexOf(name),sb.indexOf(amount)));
            s = sb.substring(sb.indexOf(amount));
            sb.replace(sb.indexOf(name), sb.indexOf(amount), "");
            System.out.println(s);
        }
        else if (s.contains("amount") == true){
            b = true;
        }
        else if (s.contains("price") == true){
            b = true;
        }
        else{
            b = true;
        }
    }
}

private void setRowsAndColumns(){
    String s = sb.toString();

    if (s.contains("rows") == true && s.contains("columns") == true){
        String r = s.substring(s.indexOf("rows"), s.indexOf("columns")); // copy this to setMachine
        r = r.replaceAll("rows", "");
        r = r.replace(':', ' '); r = r.replace(',', ' '); r = r.trim();

        String c = s.substring(s.indexOf("columns"), s.indexOf("}"));
        c = c.replaceAll("columns", "");
        c = c.replace(':', ' '); c = c.replace(',', ' '); c = c.trim();

        inventory = new VendingSnack[Integer.parseInt(r)][Integer.parseInt(c)];
        setRowLetters(Integer.parseInt(r));
    }
}
private void setRowLetters(int numRows){
    char c = 'A'; 
    for(int i = 0; i<numRows;i++){
        hm.put(c, i+1);
        c += 1;
    }
}
 /**
     * calculatePayment calculates the transaction for the selected snack.
     * 
     * @param row the selected row of the vending machine
     * @param column the selected column of the vending machine
     */
public void calculatePayment(int row, int column){
double price = inventory[row][column].getPrice();

System.out.println("Selected " + inventory[row][column].getName() + ".");
System.out.println("Total price is: " + price + ".");

boolean hasPaid = false;
while(hasPaid == false){

    System.out.println("Please enter payment amount.");
double payment = s.nextDouble();
if(payment >= price){
    double change = payment - price;
    System.out.printf("Thank you. Your change is: %.2f\n", change);
    hasPaid = true;
    recordTransaction(row, column, price, payment, change);
}
else{
    System.out.println("Payment is less than price.");
}
}

}
 /**
     * recordTransaction records the current transaction into the txt file
     * 
     * @param row the selected row of the vending machine
     * @param column the selected column of the vending machine
     */
private void recordTransaction(int row, int column, double price, double payment, double change){
    try {
      
        fw = new FileWriter(record);
      
        String name = inventory[row][column].getName();
        String s = String.format("Transaction: %s purchased. Payment: $%.2f. Total Change: $%.2f.", name, payment, change);
        fw.write(s);
        fw.close();
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}
}