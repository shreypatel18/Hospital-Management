
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;


public class Main{ //hospital has 3 doctors , 1 pharmacist and 1 management body
    static Managment m;
    static Pharmacist p;
    static Doctor d1;
    static Doctor d2;
    static Doctor d3;


    public static void main(String[] args) {


        //Management setup
        Managment m1 = new Managment();
        m=m1;
        m1.D1 = new Doctor();
        m1.D2 = new Doctor();
        m1.D3= new Doctor();
        d1 = m1.D1;
        d2 = m1.D2;
        d3 = m1.D3;
        d1.receiveManagement(m1);
        d2.receiveManagement(m1);
        d3.receiveManagement(m1);
        m1.setInitialslotsfalse(m1.D1);
        m1.setInitialslotsfalse(m1.D2);
        m1.setInitialslotsfalse(m1.D3);
        Pharmacist p1 = new Pharmacist();
        p = p1;
        m1.setPharmacist(p1);
        p1.recieveManagement(m1) ;
        m1.setdoctorinfo();


        start();

    }
    static void start(){//begining of program

           System.out.println("Want to login as Patient,Doctor or Pharmacist enter P for patient, D for" +
                   " Doctor,  Ph for Pharmacist");
           Scanner s7 = new Scanner(System.in);
           String chosen = s7.nextLine();

           if (chosen.equalsIgnoreCase("p")) {
               Main.loginAsPatient();
           } else if (chosen.equalsIgnoreCase("d")) {
               Main.loginAsDoctor(m);
           } else if (chosen.equalsIgnoreCase("ph")) {
               Main.loginAsPharmacist(p);
           }  else {
               System.out.println("make a valid choice");
               start();
           }

    }
    static void loginAsPatient(){
        Receptionist r = new Receptionist();
        r.recieveManagementObj(m);
        r.oldOrNew();
        start();
    }
    static void loginAsDoctor(Managment m1) {
        try {
            System.out.println("Who are you d1,d2 or d3 enter back for home screen");
            Scanner s7 = new Scanner(System.in);
            String ans = s7.nextLine();
            if (ans.equalsIgnoreCase("d1")) {
                m1.D1.checkPassword(m1, d1);
            } else if (ans.equalsIgnoreCase("d2")) {
                m1.D2.checkPassword(m1, d2);;
            } else if (ans.equalsIgnoreCase("d3")) {
                m1.D3.checkPassword(m1, d3);
            } else if (ans.equalsIgnoreCase("back")) {
                start();
            }
            else {
                System.out.println("Please enter valid choice d1,d2 or d3");
                loginAsDoctor(m1);
            }
        }catch (Exception e){
            System.out.println("something went wrong");
            loginAsDoctor(m1);
        }

    }

    static void loginAsPharmacist(Pharmacist p){//manages stock , displaysstock, generatesbillbill1
        System.out.println("what would you like to do to manage stock enter m, to generate bills press g, to display bill of patient enter d");
        System.out.println("to display stocks press dis, to add new class of medicines press n press back for homescreen");
        Scanner j = new Scanner(System.in);
        String ans = j.nextLine();

           if (ans.equalsIgnoreCase("m")) {
               System.out.println("to add enter add, to remove stock enter remove");
               Scanner h = new Scanner(System.in);
               String ans1 = h.nextLine();
               if(ans1.equalsIgnoreCase("add")){
                   System.out.println("enter name of medicine");
                   String temp1 = h.nextLine();
                   System.out.println("enter quantity to be added");
                   int temp2 = h.nextInt();
                   p.add(temp1, temp2, m);
                   loginAsPharmacist(p);
               } else if (ans1.equalsIgnoreCase("remove")) {
                   System.out.println("enter name of medicine");
                   String temp1 = h.nextLine();
                   System.out.println("enter quantity to be remove");
                   int temp2 = h.nextInt();
                   p.subtract(temp1, temp2, m);

               }else{
                   System.out.println("please enter valid choice add or remove");

               }
               loginAsPharmacist(p);
           } else if (ans.equalsIgnoreCase("g")) {
               p.generateBill();
               System.out.println("bill generated successfully");
              loginAsPharmacist(p);
           } else if (ans.equalsIgnoreCase("d")) {//print bill
               System.out.println("to enter Patientsid enter p to enter billno enter b");
               Scanner x = new Scanner(System.in);
               String ans2 = x.nextLine();
               if(ans2.equalsIgnoreCase("p")){
                   System.out.println("please enter patients id");
                   String id = x.nextLine();
                   if(p.patients.containsKey(id)){
                       Patient p1 =(Patient) p.patients.get(id);
                       for(Object x3:  p1.bill1.medicinesUsed){
                           String x1 = (String)x3;
                           System.out.println(x1);
                       }
                       System.out.println();
                       System.out.println(p1.bill1.TotalPayment+" pharmacy bill amount");
                   }else{
                       System.out.println("id donot exist please enter valid id");
                       loginAsPharmacist(p);
                   }
                   loginAsPharmacist(p);
               }
               if(ans2.equalsIgnoreCase("b")){
                   Scanner f = new Scanner(System.in);
                   System.out.println("please enter bill no");
                   String billno = f.nextLine();
                   if(p.billsList.containsKey(billno)){
                       PharmacyBill ph1 = (PharmacyBill) p.billsList.get(billno);
                       System.out.println(ph1.TotalPayment+" pharmacy bill amount");
                       for(Object r : ph1.medicinesUsed){
                           String x1 = (String)r;
                           System.out.println(x1);
                       }
                   }
               }
             loginAsPharmacist(p);
           } else if(ans.equalsIgnoreCase("dis")) {
               Collection s1  =(Collection) m.p.ListOfMedicines.keySet();
               Collection  s2  =(Collection) m.p.ListOfMedicines.values();
               Iterator x = s1.iterator();
               Iterator y = s2.iterator();
               while(x.hasNext()){
                   while(y.hasNext()){
                       Medicines m =(Medicines) y.next();
                       System.out.println((String) x.next() +" "+m.quantity);
                   }
               }
               loginAsPharmacist(p);

           } else if (ans.equalsIgnoreCase("n")) {
               Scanner s20 = new Scanner(System.in);
               System.out.println("enter name of medicine");
               String name = s20.nextLine();
               System.out.println("enter use of medicine");
               String use = s20.nextLine();
               System.out.println("enter price of medicine, please enter price in double format .00");
               double price = s20.nextDouble();
               System.out.println("enter quantity of medicine");
               int qty = s20.nextInt();
               m.p.addNewclassOfMedicine(name, use, qty, price);
               loginAsPharmacist(p);
           } else if (ans.equalsIgnoreCase("back")) {
               start();
           } else {
               System.out.println("please enter valid choice m,g,d,dis or n");
               loginAsPharmacist(m.p);
           }
    }

}



