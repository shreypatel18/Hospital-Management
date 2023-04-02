
import java.util.*;

//Management has association with doctors management has doctors
//keeps all data of doctors,patients handled by each doctors every patient and accounts
class Managment {
    //take info from receptionist
    //give info to doctor


    ArrayList Doctors = new ArrayList();//stores doctors data
    HashMap patients = new HashMap(); // stores patient data id as key


    Doctor D1;
    Doctor D2;
    Doctor D3;

    Pharmacist p;

    HashMap bill1 = new HashMap(); //stores billno as key and bill object as value
    HashMap bill2 = new HashMap(); //stores billno as key and bill object


    void setPharmacist(Pharmacist p) {
        this.p = p;
    }

    void setDoctors(Doctor d1, Doctor d2, Doctor d3) {
        D1 = d1;
        D2 = d2;
        D3 = d3;
        Doctors.add(d1);
        Doctors.add(d2);
        Doctors.add(d3);

    }

    void setdoctorinfo() {
        D1.name = "v.j patil";
        D1.specialisation = "general";
        D1.experiance = 10;
        D2.specialisation = "assistand";
        D2.name = "m.b shah";
        D2.experiance = 3;
        D3.name = "vikrant y";
        D3.specialisation = "technical";
        D3.experiance = 1;
    }

    void createDoctorsPassword(Doctor d) {
        System.out.println("please enter new password");
        Scanner s = new Scanner(System.in);
        String pw = s.nextLine();
        d.recievePassword(pw);
        System.out.println("password change successfully");
    }

    void setInitialslotsfalse(Doctor d) {
        for (int i = 0; i < d.slots.length; i++) {
            d.slots[i] = false;
        }
    }

    int[] checkAvailableSlotsD1() {

        for (int i = 0; i < D1.slots.length; i++) {
            D1.slots[i] = false;
        }
        int[] availableSlots = new int[5];
        int h = 0;
        for (int i = 0; i < D1.slots.length; i++) {
            Boolean b = D1.slots[i];

            if (b == false) {
                int y = i + 1;
                availableSlots[h] = y;
                h++;
            }
        }
        return availableSlots;
    }

    int[] checkAvailableSlotsD2() {

        int[] availableSlots = new int[5];
        int h = 0;
        for (int i = 0; i < D2.slots.length; i++) {
            Boolean b = D2.slots[i];

            if (b == false) {
                int y = i + 1;


                availableSlots[h] = y;
                h++;
            }
        }
        return availableSlots;
    }

    int[] checkAvailableSlotsD3() {

        int h = 0;
        int[] availableSlots = new int[5];

        for (int i = 0; i < D3.slots.length; i++) {
            Boolean b = D3.slots[i];

            if (b == false) {
                int y = i + 1;


                availableSlots[h] = y;
                h++;
            }
        }
        return availableSlots;
    }

    void updateBookedslots(Boolean[] bookedslots, Doctor d) {
        d.slots = bookedslots;
    }

    void collectBillsfromAccounts(HospitalBill H, PharmacyBill P) {
        bill1.put(P.billNo, P);
        bill2.put(H.Bill2no, H.Billdetails);

    }
}

    class HospitalBill {
        int Bill2no;
        HashMap Billdetails = new HashMap(); //constains bill no as key and a hashmap of cost and descrip as value
        void createBillNo() {
            Random r = new Random();
            Bill2no = r.nextInt();
            System.out.println("your billno "+Bill2no);
        }
}






