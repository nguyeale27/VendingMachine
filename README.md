# VendingMachine

Overview
VendingMachine is a program developed in Visual Studio Code that simulates a vending machine. It reads off of a JSON file as an input for the items that will be added to the machine as well as how many there are and
adds them as snacks the user can select. The user can also create snacks based on if the size of the vending machine allows it.
Repository URL: https://github.com/nguyeale27/VendingMachine

Approach
First, the program takes the JSON file and reads it using FileReader to find the number of rows and columns as well as the information for each snack and builds a 2D array, where
each element holds an object instance of VendingSnack to represent a snack. Then the user is given a prompt to choose whether they would like to add another snack, buy one, or exit.
The program will record transactions in a txt file called "Transactions" in the project folder. The transactions will not appear until the user exits the program.

User Interaction
When using the program, it is recommended to use Visual Studio Code to run the program and the JSON file should be named "input.json" and added to the VendingMachine folder.
After starting the program, the user can either add another snack, purchase an existing one, or exit the program in the prompt given in the terminal.
A user selects a particular item by giving a 2 character input, 
where the first character must be a letter that indicates the row and the second character is a number indicating the column.
If the snack has an amount value greater than 0, the user is asked to enter the payment. If it is less than the price, the program will not accept it and 
ask to reenter another payment. Otherwise, the payment goes through and the transaction is recorded. The program then goes back to the original menu.
If the user adds another snack, they are given prompts to enter the name, amount, and price of the snack. Once given, it is added to the next available slot, which is
displayed for the user.
