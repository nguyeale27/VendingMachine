
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;


class VendingMachine{
     //rows would be easier to get with enum statement
     HashMap<Character,Integer> hm = new HashMap<Character, Integer>();
    Scanner s = new Scanner(System.in);
    VendingSnack[][] inventory; //keeps track of items in VendingMachine
    File file, input;
    FileWriter fw;
    FileReader fr;
    
    public VendingMachine(){
        inventory = new VendingSnack[10][10];
        file = new File("Transactions.txt");
        input = new File("input.json");
        
        
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
    System.out.printf("Thank you. Your change is: %.2f", change);
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
        fw = new FileWriter(file);
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