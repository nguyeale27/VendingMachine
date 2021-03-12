
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
    public VendingMachine(){
        inventory = new VendingSnack[10][10];
        record = new File("Transactions.txt");
        i = new File("input.json");
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
    vm.inventory[0][0] = new VendingSnack("Snickers", 10, 1.35);
    vm.calculatePayment(0,0);
}


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
private void recordTransaction(int row, int column, double price, double payment, double change){
    try {
        char[] c = new char[100000];
        fw = new FileWriter(record);
        fr = new FileReader(i);
        String name = inventory[row][column].getName();
        String s = String.format("Transaction: %s purchased. Payment: $%.2f. Total Change: $%.2f.", name, payment, change);
        fw.write(s);
        fw.close();
        fr.read(c);
        StringBuffer sb = new StringBuffer();
        sb.append(c);
        System.out.println(sb.toString());
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}
}