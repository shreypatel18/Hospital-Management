import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Patient extends Managment {
    Boolean oldOrNew;//assigned by receptionist
    int idNo;//assigned by receptionist
    String name;//assigned by receptionist
    int age;//assigned by receptionist
    String oldDoctor;//assigned by receptionist
    String oldPrescriptions;//assigned by receptionist
    String prvDieseas;//assigned by receptionist
    String newAnalysis;//assigned by doctor
    String newPrescriptions;//assigned by doctor
    ArrayList medicinesIssuedbyPharmacy;
    PharmacyBill bill1;//assigned by pharmacist copy maintained in Accounts and management
    HospitalBill bill2;//assigned by Accounts copy maintained by Managment

    int createId() {
        Random x1 = new Random();
        idNo = x1.nextInt();
        return idNo;

    }

    Boolean oldMedicalHistory() {
        Scanner s3 = new Scanner(System.in);
        System.out.println("do you have any old medical history enter yes or no");
        try {
            String x1 = s3.nextLine();
            if (x1.equalsIgnoreCase("YES")) {
                return true;
            } else if (x1.equalsIgnoreCase("No")) {
                return false;
            }


        }catch (Exception e){
            System.out.println("Please enter valid data format Yes or No");
            oldMedicalHistory();
        }
        return oldMedicalHistory();
    }
}



