import java.util.Scanner;

//Receptionist will take information about patient and will help to book slot and will give info about doctors
class Receptionist extends Managment {
    Managment man;

    void recieveManagementObj(Managment m) {
        man = m;

    }

    void oldOrNew() {
        int givenId = 0;
        System.out.println("Welcome to life care , May i know is this the first time you are visiting? " +
                "enter yes or no");
        Scanner s1 = new Scanner(System.in);
        String ans = s1.nextLine();
        if (ans.equalsIgnoreCase("yes")) {
            Patient p1 = new Patient();
            int createdId = p1.createId();
            p1.idNo = createdId;
            man.patients.put(createdId, p1);
            System.out.println(p1.idNo + "this is your id no");
            conversationPatient(p1);

        } else if (ans.equalsIgnoreCase("no")) {
            System.out.println("please enter your idNo");
            try {
                givenId = s1.nextInt();
            } catch (Exception e) {
                System.out.println("Please enter valid data ");
                oldOrNew();
            }
            try {
                Patient p = checkId(givenId);
                if (p == null) {
                    checkId(givenId);
                } else {
                    conversationPatient(p);
                }
            } catch (Exception e) {
                checkId(givenId);
            }
        } else {
            System.out.println("please enter valid choice yes or no");
            oldOrNew();
        }
    }

    Patient checkId(int x) { // either entered id or created id no will be taken as parameter
        if (man.patients.containsKey(x)) {
            return (Patient) man.patients.get(x);
        } else {
            Scanner s2 = new Scanner(System.in);
            System.out.println("id not founded to create new one enter new ,to go homepage enter home to enter other idno enter id, enter back to return home");
            String ans = s2.nextLine();
            if (ans.equalsIgnoreCase("new")) {
                Patient g = new Patient();
                int id = g.createId();
                g.idNo = id;
                man.patients.put(id, g);
                return g;
            } else if (ans.equalsIgnoreCase("home")) {
                //redirect to homepage*
                Main.start();
                return null;
            } else if (ans.equalsIgnoreCase("id")) {
                System.out.println("enter idno");
                int id = s2.nextInt();
                checkId(id);
                return  null;
            } else if (ans.equalsIgnoreCase("back")) {
                Main.start();
                return  null;
            } else {
                System.out.println("Please enter valid data new,home or id");
                checkId(x);
                return null;
            }

        }
    }


    void conversationPatient(Patient x) { //reception has identified patient now problems will be solved
        System.out.println("For new case enter new ,if old press anything and enter");
        try {
            Scanner s3 = new Scanner(System.in);
            String ans = s3.nextLine();
            if (ans.equalsIgnoreCase("new")) {
                System.out.println("enter your name");
                x.name = s3.nextLine();
                System.out.println("enter your age");
                x.age = s3.nextInt();
            }
            boolean x2 = x.oldMedicalHistory();
            if (x2) {
                Scanner s12 = new Scanner(System.in);
                System.out.println("May i know your previous Doctor");
                x.oldDoctor = s12.nextLine();
                System.out.println("what was your old diseases");
                Scanner s31 = new Scanner(System.in);
                x.prvDieseas = s31.nextLine();
                System.out.println("what medicines did you had, or currently having");
                Scanner med = new Scanner(System.in);
                x.oldPrescriptions = med.nextLine();
                System.out.println( );
                showInfoAboutDoctors();
                System.out.println( );
                showAppointments(x);
            }else{
                showInfoAboutDoctors();

                showAppointments(x);
            }

           } catch (Exception e) {
            System.out.println("please enter valid data type");
            conversationPatient(x);
        }
    }
    void showAppointments(Patient x) {
            System.out.println("which doctor would you like to consult enter D1,D2 or D3" +
                    " enter d1 for "+man.D1.name+" enter d2 for "+man.D2.name+" enter d3 for "+man.D3.name);
       try {
           Scanner s5 = new Scanner(System.in);
           String chose = s5.nextLine();
           if (chose.equalsIgnoreCase("D1")) {
               int[] a = man.checkAvailableSlotsD1();
               System.out.println("Doctor d1 can meet at slot" + " ");
               for (int m : a) {
                   System.out.print(m + " ");
               }
               System.out.println("which appointment would you like to book");
               bookAppointment(x, a, man.D1, man.D1.slots);
           } else if (chose.equalsIgnoreCase("d2")) {

               int[] b = man.checkAvailableSlotsD2();
               System.out.println("Doctor " + man.D2.name + " can meet at slot" + " ");
               for (int m : b) {
                   System.out.print(m + " ");
               }
               System.out.println("which appointment would you like to book");
               bookAppointment(x, b, man.D2, man.D2.slots);
           } else if (chose.equalsIgnoreCase("d3")) {

               int[] c = man.checkAvailableSlotsD3();
               System.out.println("Doctor " + man.D3.name + " can meet at slot" + " ");
               for (int m : c) {
                   System.out.print(m + " ");
               }
               System.out.println("which appointment would you like to book");
               bookAppointment(x, c, man.D3, man.D3.slots);
           } else {
               System.out.println("please enter valid choice d1 d2 or d3");
               showAppointments(x);
           }
       }catch (Exception e){
           System.out.println("please enter valid data type");
           showAppointments(x);
       }

    }


    void bookAppointment(Patient x, int[] availableSlots, Doctor D, Boolean[] totalSlots){
        Scanner s6 = new Scanner(System.in);
        int chosen = s6.nextInt();
        for(int i=0; i<availableSlots.length;i++){
            int a = availableSlots[i];
            if(a==chosen) {
                totalSlots[a - 1] = true;
                updateBookedslots(totalSlots, D);
                System.out.println("your appointment with doctor" + " " + D.name + " " + "is fixed for slot " + a);
                break;
            }
        }
      D.patientsHandled.put(x.idNo, x);
    }
  void showInfoAboutDoctors(){

      System.out.println(man.D1.name + " is known for " + man.D1.specialisation + " doctors experienced " + man.D1.experiance+" Years");
      System.out.println(man.D2.name + " is known for " + man.D2.specialisation + " doctors experienced " + man.D2.experiance+ " Years");
      System.out.println(man.D3.name + " is known for " + man.D3.specialisation + " doctors experienced " + man.D3.experiance+ " Years");
  }
}


