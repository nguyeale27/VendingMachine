
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.FileHandler;

class VendingMachine{
     //rows would be easier to get with enum statement
     HashMap<Character,Integer> hm = new HashMap<Character, Integer>();
    Scanner s = new Scanner(System.in);
    VendingSnack[][] inventory; //keeps track of items in VendingMachine
    File file, input;
    FileWriter fw;
    public VendingMachine(){
        inventory = new VendingSnack[10][10];
        file = new File("Text.txt");
        input = new File("input.json");
        try {
            fw = new FileWriter(file);
            fw.write("Testing");
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
public static void main (String[] args){
    VendingMachine vm = new VendingMachine();
    System.out.println(vm.file.canWrite());
    //vm.inventory[0][0] = new VendingSnack("Snickers", 10, 1.35);
    //vm.calculatePayment(0,0);
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