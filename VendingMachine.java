
import java.io.File;
import java.io.FileReader;
//import java.util.Enumeration;
import java.util.Scanner;
import java.util.logging.FileHandler;
class VendingMachine{
    //Enum e = {'A' = 1, 'B', 'C','D'}; //rows would be easier to get with enum statement
    Scanner s = new Scanner(System.in);
    VendingSnack[][] inventory; //keeps track of items in VendingMachine
    public VendingMachine(){
        inventory = new VendingSnack[10][10];
        //File f = new File("Text.txt");
        //FileReader fr = new FileReader("input.json");
        //fr.close();
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
    double d = payment - price;
    System.out.printf("Thank you. Your change is: %.2f", d);
    hasPaid = true;
}
else{
    System.out.println("Payment is less than price.");
}
}
}

}