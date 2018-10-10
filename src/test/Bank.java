package test;

import java.util.*;

class Account
{
    private double bal;  //The current balance
    private int accnum;  //The account number
    public Account(int a)
    {   
        bal=0.0;
        accnum=a;
    }
    public void deposit(double sum)
    {
    if (sum>0)
        bal+=sum;   
    else 
        System.err.println("Account.deposit(...): "+"cannot deposit negative amount.");   
    }
    public void withdraw(double sum)
    {
        if (sum>0)
            bal-=sum;   
        else
            System.err.println("Account.withdraw(...):"+"cannot withdraw negative amount.");   
    }
    public double getBalance()
    {
        return bal;
    }
    public double getAccountNumber()
    {
        return accnum;
    }
    public String toString()
    {
        return "Acc " + accnum +": " + "balance = " + bal;   
    }
    public final void print()
    {
        //Don't override this,
        //override the toString method
        System.out.println( toString() );   
    }
}

//* Code for Savings account */

class SavingsAccount extends Account{
    double interest;
    
    SavingsAccount(int a)
    {
       super(a);
       interest=0.1;
       
    }
    
    void setInterest(double interest)
    {
        this.interest=interest;
        addInterest();
    }
    
    void addInterest()
    {
        deposit(getBalance()*interest);
    }
    
    public void withdraw(double sum)
    {
        if( getBalance()-sum < 0)
        {
            System.out.print("Cannot be done Balance is insuffucient");
        }
        else    
         super.withdraw(sum);   
    }
    
    public void display()
    {
        super.print();
    }
}
/* Code For Current Account */
class CurrentAccount extends Account{
    double overDraft;
   
    CurrentAccount(int a){
        super(a);
        overDraft=5000;
    }
      
   public void display(){
        super.print();
    }
   public void withdraw(double sum){
        if( getBalance()-overDraft < sum){
            System.out.print("Cannot be done Balance is insuffucient");
        }
        else    
         super.withdraw(sum);
    }
}

public class Bank{

      int sac,cac,ch;
        String ch1,ch2;
        double amt;
        Scanner scan=new Scanner(System.in);
        public void update(Account b){
             do{
                    System.out.println("1.Deposite \t 2.Withdraw \t 3.Display Balance 4.Exit");
                    System.out.println("Enter your preference");
                    ch=scan.nextInt();
                    
                    switch(ch){
                        case 1:System.out.println("Enter Amount to Deposit");
                            amt=scan.nextDouble();
                           b.deposit(amt);
                            b.addInterest();
                            break;
                        case 2:System.out.println("Enter Amount to Withdraw");
                            amt=scan.nextDouble();
                            b.withdraw(amt);
                            break;
                        case 3:b.display();
                                break;
                        case 4:break;   
                        default: System.out.println("Enter correct choice");
                            break;
                    }   
                    System.out.println("Enter y to continue or any key to exit");
                    ch1 = scan.next();
                }while(ch1.equalsIgnoreCase("y"));
        }

    public static void main(String[] args){
        Bank b = new Bank();
            
        
           
        List<SavingsAccount> saccnt = new ArrayList<>();
        List<CurrentAccount> caccnt = new ArrayList<>();

        Scanner scan=new Scanner(System.in);
          
            
        do{
            System.out.println("1.Savings Account \t 2.Current Account");
            int accountchoice=scan.nextInt();

            if(accountchoice==1){
                System.out.println("Enter Savings Account Number");
                sac=scan.nextInt();
                    
                SavingsAccount s= new SavingsAccount(sac);
                saccnt.add(s);

                update(s);
               
                s.display();  
            }

            else if(accountchoice==2){
                System.out.println("Enter Current Account Number");
                cac=scan.nextInt();
                
                CurrentAccount c= new CurrentAccount(cac);
                caccnt.add(c);
                
                    
                update(c);
                c.display();
            }

            else{
                System.out.println("Eneter a correct choice ");
            }

            System.out.println("Enter y to continue or any key to exit");
            ch2 = scan.next();
            
        }while(ch2.equalsIgnoreCase("y"));

        for(SavingsAccount s:saccnt){
            s.print();
        }    

        for(CurrentAccount c:caccnt){
            c.print();
        }         

    }
}