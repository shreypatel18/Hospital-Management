import java.util.*;

class Pharmacist extends Managment{
    Managment man1;//will create bills //will maintain stock
    void recieveManagement(Managment m1) {
        man1 = m1;
    }
    HashMap billsList = new HashMap();
    HashMap  ListOfMedicines = new HashMap();
     void addNewclassOfMedicine(String name, String use, int Quantity, double price){
      Medicines x = new Medicines();
      x.name = name;
      x.generalUse = use;
      x.quantity = Quantity;
      x.price = price;
      man1.p.ListOfMedicines.put(name, x);
     };
     void generateBill(){

         try{    System.out.println("Please enter patient id to generate bill");
             Scanner s = new Scanner(System.in);
             int Patientsid = s.nextInt();
             Patient p;
             if (man1.patients.containsKey(Patientsid)) {
                 p = (Patient) man1.patients.get(Patientsid);
                 PharmacyBill p1 = new PharmacyBill();
                 int billno = p1.createBillNo();

                 Scanner s10 = new Scanner(System.in);
                 System.out.println("How many types of medicines you want to add enter number");
                 int n = s10.nextInt();
                 for (int i = 1; i <= 10; i++) {
                     System.out.println("Please enter name of medicine");
                     Scanner s3 = new Scanner(System.in);
                     String n1 = s3.nextLine();
                     System.out.println("please enter quantity of medicine");
                     int u = s10.nextInt();

                     p1.addMedicinesAndQuantity(n1, u, p, man1);
                 }
                 p1.calc();
                 p.bill1 = p1; //storing bill for customer
                 billsList.put(billno, p1);

                 Main.loginAsPharmacist(man1.p);//storing bill with billno as key for pharmacist
             } else {
                 System.out.println("patient id do not exist retry");
                 generateBill();
             }}
         catch (Exception e){
             System.out.println("something went wrong redirecting to homepage");
             Main.start();
         }

     }

       void subtract(String medicineName, int quantityDelivered ,Managment m){

                // name based excessing to update stock
               Medicines x = (Medicines) m.p.ListOfMedicines.get(medicineName);
               String name = x.name;

               if(name.equals(medicineName)){
                   x.quantity-= quantityDelivered;
                   System.out.println(x.name +" "+"units delivered = "+quantityDelivered+" unit in stock now="+
                           x.quantity);
                   if(x.quantity<5){
                       System.out.println("Alert! less than 5 units are left");
                   }
                   Main.loginAsPharmacist(p);
               }else{
                   System.out.println("please enter valid name");
                   subtract(medicineName, quantityDelivered, m);
           }
       }
       void add (String medicineName, int quantityAdded, Managment m1) {
           System.out.println("Please enter new to enter the new kind of medicine or enter old to manage existing stock");
           Scanner s = new Scanner(System.in);
           String m = s.nextLine();
           if (m.equalsIgnoreCase("new")) {
               Medicines x = new Medicines();
               x.name = medicineName;
               man1.p.ListOfMedicines.put(medicineName, x);
           } else if (m.equalsIgnoreCase("old")) {
               Medicines x = (Medicines) man1.p.ListOfMedicines.get(medicineName);
               String name = x.name;
               if (name.equals(medicineName)) {
                   x.quantity += quantityAdded;
                   System.out.println(x.name + " " + "units addedtostock = " + quantityAdded+ " unit in stock now=" +
                           x.quantity);
                   if (x.quantity < 5) {
                       System.out.println("Alert! only 5 units are left");
                   }
               } else {
                   System.out.println("please enter valid name");
                   add(medicineName, quantityAdded, m1);
               }
           } else {
               System.out.println("please enter valid choice yes or no");
               add(medicineName, quantityAdded, m1);
           }
           Main.loginAsPharmacist(Main.p);
       }

}
   class Medicines extends Pharmacist {
    String name;
    int quantity;
    String generalUse;
    double price;
}
    class MedicinesGivenToPatients extends Medicines{
    //every patient would have its own list of this
        // class is made because price use  of medicine remains same but quantity is stockmedicine medicines class
        //will be different
}
    class PharmacyBill extends Pharmacist {
        ArrayList medicinesUsed = new ArrayList();
        int TotalPayment;
        int billNo;
        int createBillNo(){
            Random r = new Random();
            billNo = r.nextInt();
            System.out.println("your bill no is "+billNo);
            return billNo;

        }
        //it will generate bill of medicines for patient object and give to accounts and management
        void addMedicinesAndQuantity(String MedicineName, int Quantity, Patient p, Managment m2) {
         //PHARMACIST WILL USE THIS METHOD
     try {
         Medicines m = (Medicines) m2.p.ListOfMedicines.get(MedicineName);

         MedicinesGivenToPatients m1 = new MedicinesGivenToPatients();
         m1.price = m.price;
         m1.generalUse = m.generalUse;
         m1.quantity = Quantity;
         m1.name = m.name;
         medicinesUsed.add(m1);
         m.subtract(m.name, Quantity, m2); // updating stock
     }catch (Exception e){
         System.out.println("Medicine not found try some other");
         generateBill();
     }
        }//it will generate bill of medicines for patient object
        void calc(){

            int totalCost = 0;
            ArrayList x = new ArrayList();
            for(Object m: medicinesUsed){
                Medicines m1 = (Medicines)m;
               double ans =  m1.price*m1.quantity;
               x.add(ans);
            }
            for(Object m: x){
                int m1 = (int)m;
                totalCost += m1;
            }
            TotalPayment = totalCost;

        }

    }


