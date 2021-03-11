class VendingSnack{
    private String name;
    private int amount;
    private double price;
    public VendingSnack(String n, int a, double p){
        name = n;
        amount = a;
        price = p;
    }
    public String getName(){
        return name;
    }
    public int getAmount(){
        return amount;
    }
    public double getPrice(){
        return price;
    }
}