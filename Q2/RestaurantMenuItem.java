package Q2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// MenuItem interface
interface MenuItem {
    String getName();
    String getDescription();
    double getPrice();
}

// RestaurantMenuItem class implementing MenuItem interface
public class RestaurantMenuItem implements MenuItem {
    private String name;
    private String description;
    private double price;

    public RestaurantMenuItem(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    
    public String getName() {
        return name;
    }

  
    public String getDescription() {
        return description;
    }

       public double getPrice() {
        return price;
    }
}

interface Order{

void  Additem(MenuItem item);
void removeItem(MenuItem item);
double calculateprice();

}
class RestaurantOrder implements Order{

   private  List <MenuItem> items;
   private int orderid ;
   RestaurantOrder()
   {
    this.items = new ArrayList<>();
   }
   public void Additem(MenuItem item)
   {
    items.add(item);
   }
   public void removeItem(MenuItem item)
   {
    items.remove(item);
   }
   public double calculateprice()
   {
    double amt =0;
 for(MenuItem item : items)
 {
    amt += item.getPrice();
 }
 return amt ;
   }

   public List <MenuItem> giveitems() 
   {
    return items ;
   }
   public void setid(int a){
    orderid = a ;
    
   }
   public int getId()
   {
    return orderid;
   }
   public MenuItem getitem(int i) {
        return items.get(i-1);
   }

}

interface Transaction
{
    public void AddTransaction(RestaurantOrder order);
   // public void SetItem(int a );
    public void DisplayTransaction();

}
class TransactionDetails implements Transaction
{
 ArrayList <RestaurantOrder> orderlist = new  ArrayList<>();
//  ArrayList <Integer> l = new  ArrayList<>();

 public void AddTransaction(RestaurantOrder order)
 {
    orderlist.add(order);
 }
// public void SetItem(int a)
// {
//     l.add(a);
// }
public void DisplayTransaction() {
    if (orderlist.isEmpty()) {
        System.out.println("No transactions to display.");
        return;
    }

    System.out.println("Transaction Details:");
    for (int i = 0; i < orderlist.size(); i++) {
        RestaurantOrder order = orderlist.get(i);
       // int itemNumber = l.get(i);
        System.out.println("Transaction id :" + order.getId());
        System.out.println("Ordered Items:");

       
        for (MenuItem item : order.giveitems()) {
            System.out.println("Item: " + item.getName());
            System.out.println("Description: " + item.getDescription());
            System.out.println("Price: $" + item.getPrice());
            System.out.println();
        }
        System.out.println(); 
    }
}


    
}
