import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
//Doctor can see slots,can enter analysis,and can enter charges for consulting
class Doctor extends Managment {
    Managment man1;
    private String password;
    String name;
    String specialisation;
    int experiance;
    Boolean[] slots = new Boolean[5];
    HashMap patientsHandled = new HashMap();
    void receiveManagement(Managment m){
        man1 = m;
    }
    void recievePassword(String pw){
        password = pw;
    }

    void checkPassword(Managment m, Doctor d) {
       try {
           System.out.println("Please enter the password");
           String x; //x = password
           Scanner s17 = new Scanner(System.in);
           x = s17.nextLine();
           if (x.equals(password)) {
               System.out.println("Correct Password");
               System.out.println("what would you like to do, press a to view booked slots, b to view patientsinfo,c to register diagnosis" +
                       ", d to register charges for consustations to return to homescreen press back");
               s17 = new Scanner(System.in);
               String ans = s17.nextLine();
               //to view booked slots
               if (ans.equalsIgnoreCase("a")) {
                   displaybookedslots();
               }// to view history of handled patient
               else if (ans.equalsIgnoreCase("b")) {
                   patientsinfo();
               }// to prescribe diagnosis to a patinet
               else if (ans.equalsIgnoreCase("c")) {
                   System.out.println("please enter patients id");
                   Scanner r = new Scanner(System.in);
                   int id = r.nextInt();
                   diagnosis(id);
               }// to add consulting charges
               else if (ans.equalsIgnoreCase("d")) {

                   consultingCharges();
               } else if (ans.equalsIgnoreCase("back")) {
                   Main.start();
               } else {
                   System.out.println("please enter a valid choice a,b,c,d ");
                   checkPassword(m, d);
               }

           } else {
               System.out.println("incorrect Password please enter again");
               System.out.println("to create new one enter new to try some other password enter pass to return to home screen press home");
               Scanner s1 = new Scanner(System.in);
               String ans = s1.nextLine();
               if(ans.equalsIgnoreCase("new")){
                   createDoctorsPassword(d);
               } else if (ans.equalsIgnoreCase("pass")) {
                   checkPassword(m, d);
               } else if (ans.equalsIgnoreCase("home")) {
                   Main.start();
               } else {
                   System.out.println("please enter valid choice new or pass");
                   checkPassword(m, d);
               }
               Main.loginAsDoctor(m);
           }
       }catch (Exception e){
           System.out.println("please enter valid data type");
           checkPassword(m, d);
       }
    }

   //stoes the patients handled by doctor in map as patient id as key


    void displaybookedslots() {
        System.out.println("you have to meet patient at slots");
        for (int i = 0; i < slots.length; i++) {
            boolean h = slots[i];
            int y = i + 1;
            if (h == true) {
                System.out.println(" "+y + " ");
            }
        }
        Main.loginAsDoctor(man1);
    }

    //methods used by doctor to store diagnos and medicine given to patient by himself
    void diagnosis(int patientsId) {
        try {
            if (patientsHandled.containsKey(patientsId)) {// doctor will give diagnosis here
                Patient p1 = (Patient) patientsHandled.get(patientsId);
                patients.putIfAbsent(p1.idNo, p1);
                Scanner s8 = new Scanner(System.in);

                System.out.println("add diagnonis for " + p1.name);
                p1.newAnalysis = s8.nextLine();
                System.out.println("add prescriptions");
                p1.newPrescriptions = s8.nextLine();
                patientsHandled.put(patientsId, p1);
                System.out.println("Generate hospital bill for "+p1.name);
                consultingCharges();
            } else {
                System.out.println("id doesnt exist please enter new id");
                Scanner b = new Scanner(System.in);
                int p = b.nextInt();
                diagnosis(p);
            }
        }catch (Exception e){
            System.out.println("please enter valid data type");
            diagnosis(patientsId);
        }
    }

    void consultingCharges(){//doctor will specify charges through it
        try {
            Patient pa1;
            Scanner s11 = new Scanner(System.in);
            System.out.println("please enter idno of patient");
            int patientsId = s11.nextInt();
            HashMap billtwo = new HashMap();//key stores description as string and value stores cost
            if(patientsHandled.containsKey(patientsId)) {
                 pa1 = (Patient) patientsHandled.get(patientsId);
                System.out.println(pa1.name);
                System.out.println("please enter no of rows  for billing note- each row will consist" +
                        "of two columns one for description and other for charges");
                int rows = s11.nextInt();


                System.out.println("please enter the charges to consult patient " + pa1.name + " with id no " + pa1.idNo);
                for (int i = 1; i <= rows; i++) {
                    System.out.println("enter description");
                    Scanner a = new Scanner(System.in);
                    String temp1 = a.nextLine();
                    System.out.println("enter charges");
                    Scanner b = new Scanner(System.in);
                    int temp2 = b.nextInt();
                    billtwo.put(temp1, temp2);
                }
                pa1.bill2 = new HospitalBill();
                pa1.bill2.createBillNo();
                pa1.bill2.Billdetails = billtwo;
                System.out.println("Data entered successfully");
                Main.loginAsDoctor(man1);
            }else{
                System.out.println("id doesnot found , try again");
                consultingCharges();
            }
        }catch (Exception e){
            System.out.println("Something occured wrong , please check patientsid no and enter a valid Integer id value");
            consultingCharges();
        }
    }

    void patientsinfo() {
        Patient p = null;
        System.out.println("Please enter the idno of patient");
        Scanner s18 = new Scanner(System.in);

        try {
            int idno = s18.nextInt();
            if(patientsHandled.containsKey(idno)) {
                p = (Patient) patientsHandled.get(idno);

                System.out.println(p.name);
                System.out.println(p.age);
                System.out.println(p.idNo);
                System.out.println(p.oldDoctor);
                System.out.println(p.oldPrescriptions);
                System.out.println(p.newAnalysis);
                System.out.println(p.newPrescriptions);
                System.out.println(p.bill1);
                System.out.println(p.bill2);
                Main.loginAsDoctor(man1);
            }else{
                System.out.println("such a patient does not exist please check id");
                patientsinfo();
            }
        } catch (Exception e) {
            System.out.println("such Patient do not exist, insert correct idno of integer value");
            patientsinfo();
        }

    }
}


