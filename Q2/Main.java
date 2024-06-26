import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static int numofcust = 355 ;
    private static TransactionDetails tdetails = new TransactionDetails();
    public static void main(String[] args) {
       


      
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        System.out.println("Who are you?");
        System.out.println("(1) Manager of Restaurant ");
        System.out.println("(2) Customer ");
        int biod;
        biod = sc.nextInt();
        int choice ;
        if(biod == 1 )
        {
            while (!exit) {
               
                System.out.println("1. Display past transactions :");
                System.out.println("2. Exit");
                System.out.print("Enter your choice: ");
    
                 choice = sc.nextInt();
    
                
                switch (choice) {
                   
                    case 1:
                      tdetails.DisplayTransaction();
                      break;
                    case 2:
                        exit = true;
                        System.out.println("Exiting.................");
                        break;
                    
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            }
        }
        else{
            while (!exit) {
                System.out.println("1. Display Menu:");
                System.out.println("2. Place an Order ");
                System.out.println("3. Display past transactions :");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
    
                 choice = sc.nextInt();
    
                
                switch (choice) {
                    case 1:
                        DisplayItems();
                        break;
                    case 2:
                      placeOrder();
                        break;
                    case 3:
                      tdetails.DisplayTransaction();
                      break;
                    case 4:
                        exit = true;
                        System.out.println("Exiting.................");
                        break;
                    
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            }
        }
        
        sc.close();
        
    }

    
    public static void  DisplayItems()
    {
        try (BufferedReader br = new BufferedReader(new FileReader("menu.txt"))) {
            String line;
           

            int i = 0 ;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" - ");
                System.out.println( "(" +  ++i + ")  " + parts[0]+ "\n" + "Description:" +  parts[1] + "\n" +  "Price :" + parts[2]  + "\n");
            }
        } catch (IOException e) {
           e.printStackTrace();
        }
    }
    public static  ArrayList<MenuItem> ItemList()
    {
    
            ArrayList <MenuItem> list = new ArrayList<>();

            try (BufferedReader br = new BufferedReader(new FileReader("menu.txt"))) {
                String line;
               
    
                int i = 0 ;
                while ((line = br.readLine()) != null) {

                    String[] parts = line.split(" - ");
                   RestaurantMenuItem item = new RestaurantMenuItem(parts[0], parts[1], Double.parseDouble(parts[2]));

                   list.add(item);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return list ;
            
        
    }


    public static  void placeOrder()
    {
       RestaurantOrder order = new RestaurantOrder();
       ArrayList <MenuItem> list = ItemList();
       
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Item no. that you want to order: ");
        int num  =sc.nextInt();
        while(num<1 || num>18)
        {
            System.out.println("Please enter a valid number");
             num  =sc.nextInt();

        }
        order.Additem(list.get(num -1));
        int flag =0 ;
        int ans = 0 ;
        
        while(flag == 0 )
        {
            System.out.println("(1) Add item");
            System.out.println("(2) Remove  item");
            System.err.println("(3) Display your  Order");
            System.err.println("(4) Place Order");
            System.out.println("(5) Exit..");
            ans = sc.nextInt();
            if(ans == 1)
            {
                System.out.println("Enter the Item no. that you want to order: ");
                 num  =sc.nextInt();
                while(num<1 || num>18)
                {
                    System.out.println("Please enter a valid number");
                    num  =sc.nextInt();
        
                }
                order.Additem(list.get(num -1));
               

            }
            
            else if (ans == 2)
            {
                int ritem ;
                System.out.println("Enter the item no. you want to remove ");
                ritem = sc.nextInt();
                order.removeItem( order.getitem(ritem ));
                
                
                
            }
            
            else if(ans == 3)
            {
                PrintOrder(order);
              //  System.out.println("Thank you for Coming :) ");
               
            }
            else if(ans == 4)
            {
                PrintOrder(order);
                System.out.println("Thank you for Coming :) ");
                 flag =1 ;
            }
            else
            {
                System.out.println("Thank you for Coming :) ");
                flag = 1 ;
            }
            

        }
       if(ans == 4)
       {
        order.setid(numofcust++);
        tdetails.AddTransaction(order);
       // tdetails.SetItem(numofcust++);
       }
       
       
             

    }
    public static void PrintOrder(RestaurantOrder order)
    {
        System.out.println("Your Order id  is :" + order.getId());
        for(MenuItem item : order.giveitems())
        {
         System.out.println("Item name :" + item.getName());
         System.out.println("Description:" + item.getDescription());
         System.out.println("Price:" + item.getPrice() + "\n");

        }
        System.out.println("Total Cost : " + order.calculateprice());
        System.out.println("Your food will be delivering within few minutes :)");
   
    } 



  
}

