import java.util.*;
import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

interface Admin {
    public void admin_login();
}
class ConnectDB {
    Connection con;
    String url = "jdbc:mysql://localhost:3306/my_app";
    String db_username = "root";
    String db_password = "yusuf";
    public void connect() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, db_username, db_password);
        System.out.println("Yoohoo");
    }
    public void disconnect() throws Exception{
        con.close();
    }
}
class ConsoleClass extends ConnectDB{
    Console console = System.console();
    public void delay(int seconds) {
        long ms = seconds * 1000;
        try { 
            Thread.sleep(ms);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @SuppressWarnings("deprecation")
    public void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                // For Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // For other operating systems (Linux, macOS)
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            // Handle exceptions
            System.out.println("Error while clearing console: " + e.getMessage());
        }
    }
}
class StudentData extends ConsoleClass {
    public int s_id;
    public String s_name;
    public String s_dept;
    public String s_class;
    public int jpr;
    public int dcc;
    public int mic;
    public int sen;
    public void getStudentData(){
        boolean isValidInput = false;
        do{
            try{
                console.printf("\nEnter Student Name : "); s_name = console.readLine();
                console.printf("Enter Student Id : "); s_id = Integer.parseInt(console.readLine());
                console.printf("Enter Student Dept : "); s_dept = console.readLine();
                console.printf("Enter Student Class : "); s_class = console.readLine();
                console.printf("\nEnter GAD Marks : "); jpr = Integer.parseInt(console.readLine());
                console.printf("Enter JPR Marks : "); dcc = Integer.parseInt(console.readLine());
                console.printf("Enter DCC Marks : "); mic = Integer.parseInt(console.readLine());
                console.printf("Enter SEN Marks : "); sen = Integer.parseInt(console.readLine());
                isValidInput = true;
            }catch(NumberFormatException n){
                System.out.println("Only Integer Value allowed for ID & Marks !");
            }
        }while(!isValidInput);
    }
    public void header(){
        System.out.println();
        System.out.println("+"+"-".repeat(79)+"+");
        System.out.println("| ID \t| Name \t\t| Dept \t| Class \t| JPR \t| DCC \t| MIC \t| SEN \t|");;
        System.out.println("+"+"-".repeat(79)+"+");
    }
    public void footer(){
        System.out.println("+"+"-".repeat(79)+"+");
    }
    public void displayStudentData(int s_id, String s_name, String s_dept, String s_class, int jpr, int dcc, int mic, int sen){
        String tab = "\t";
        String column = "| ";
        String s_data = column+s_id+tab+column+s_name+tab+column+s_dept+tab+column+s_class+tab+tab+column+jpr+tab+column+dcc+tab+column+mic+tab+column+sen+tab+column;
        System.out.println(s_data);
    }
}
class DBEntry extends StudentData {
    PreparedStatement pstmt;
    ResultSet rs;
    int retStatus;
    int choice;
    int id;
    int newid;
    String newname;
    String newdept;
    String newclass;
    int newjpr;
    int newdcc;
    int newmic;
    int newsen;
}
class App extends DBEntry {
    public void menu(){
        System.out.println("\n ---------- Student Management System Console App ----------");
        System.out.println("\n ---------- MAIN MENU ----------");
        System.out.println("1. Add a New Student");
        System.out.println("2. Delete an Existing Student");
        System.out.println("3. Edit Student Data");
        System.out.println("4. Show all Student's Data");
        System.out.println("5. Exit");
        System.out.println("\nEnter your choice : ");
        try{
            int choice = Integer.parseInt(console.readLine());
            switch (choice) {
                case 1:
                    try {
                        insertData();
                    } catch (Exception e) {
                        System.out.println("\nUnable to enter data ! Maybe there's an existing Data with similar ID"); 
                        delay(2);
                        menu();
                    }   
                    break;
                case 2:
                    try {
                        deleteData();
                    } catch (Exception e){}   
                    break;
                case 3:
                    try {
                        updateById();
                    } catch (Exception e){}   
                    break;
                case 4:
                    try{
                        showData();
                    }catch (Exception e){}
                    break;
                case 5:
                    System.out.println("Bye Bye...");
                    delay(2);
                    try{disconnect();}catch(Exception e){}
                    System.exit(0);
                default:
                    break;
            }
        }catch(NumberFormatException n){
            System.out.println("\nChoice can only be a number !");
        }
    }
    public void updateById() throws Exception{
        System.out.println("\n ----------- UPDATE USING ID ----------- ");
        System.out.println("\n * Update Options :");
        System.out.println("1. ID");
        System.out.println("2. Name");
        System.out.println("3. Dept");
        System.out.println("4. Class");
        System.out.println("5. JPR Marks");
        System.out.println("6. DCC Marks");
        System.out.println("7. MIC Marks");
        System.out.println("8. SEN Marks");
        console.printf("\nEnter your choice : " + (choice = Integer.parseInt(console.readLine())));
        switch (choice) {
            case 1:
                try{updateID();}catch(Exception e){
                    System.out.println("Unable to Update Data !"); delay(3);menu();}
                break;
            case 2:
                try{updateName();}catch(Exception e){
                System.out.println("Unable to Update Data !"); delay(3);menu();}
                break;
            case 3:
                try{updateDept();}catch(Exception e){
                System.out.println("Unable to Update Data !"); delay(3);menu();}
                break;
            case 4:
                try{updateClass();}catch(Exception e){
                System.out.println("Unable to Update Data !"); delay(3);menu();}
                break;
            case 5:
                try{updateJPR();}catch(Exception e){
                System.out.println("Unable to Update Data !"); delay(3);menu();}
                break;
            case 6:
                try{updateDCC();}catch(Exception e){
                System.out.println("Unable to Update Data !"); delay(3);menu();}
                break;
            case 7:
                try{updateMIC();}catch(Exception e){
                System.out.println("Unable to Update Data !"); delay(3);menu();}
                break;
            case 8:
                try{updateSEN();}catch(Exception e){
                System.out.println("Unable to Update Data !"); delay(3);menu();}
                break;
            default:
                System.out.println("\nInvalid Choice.. Try Again"); delay(1);menu();
                break;
        }

    }
    public void insertData() throws Exception{
        getStudentData();
        connect();

        pstmt = con.prepareStatement("insert into students values(?,?,?,?,?,?,?,?)");
        pstmt.setInt(1, s_id);
        pstmt.setString(2, s_name);
        pstmt.setString(3, s_dept);
        pstmt.setString(4, s_class);
        pstmt.setInt(5, jpr);
        pstmt.setInt(6, dcc);
        pstmt.setInt(7, mic);
        pstmt.setInt(8, sen);
        retStatus = pstmt.executeUpdate();
        if (retStatus>0) {System.out.println("\nData entered successfully !");returnMenu();}
        else{System.out.println("\nError in inserting Data !");returnMenu();}
    }
    public void deleteData() throws Exception {
        System.out.println("\n ----------- Delete using ID ----------- ");
        console.printf("Enter ID : ");
        id = Integer.parseInt(console.readLine());
        pstmt = con.prepareStatement("delete from students where id = ?");
        pstmt.setInt(1, id);
        retStatus = pstmt.executeUpdate();
        if (retStatus>0) {System.out.println("\nStudent Deleted Successfully !");returnMenu();}
        else{System.out.println("\nError in Deleting Data !");returnMenu();}
    }

    public void updateID() throws Exception {
        console.printf("Enter Current ID : ");
        id = Integer.parseInt(console.readLine());
        console.printf("Enter New ID : ");
        newid = Integer.parseInt(console.readLine());
        pstmt = con.prepareStatement("update students set id = ? where id = ?");
        pstmt.setInt(1, newid);pstmt.setInt(2, id);
        retStatus = pstmt.executeUpdate();
        if (retStatus>0) {System.out.println("\nStudent Updated Successfully !");returnMenu();}
        else{System.out.println("\nError in Updating Data !");returnMenu();}
    }

    public void updateName() throws Exception {
        console.printf("Enter ID : ");
        id = Integer.parseInt(console.readLine());
        console.printf("Enter new Name : ");
        newname = console.readLine();
        pstmt = con.prepareStatement("update students set name = ? where id = ?");
        pstmt.setString(1, newname);pstmt.setInt(2, id);
        retStatus = pstmt.executeUpdate();
        if (retStatus>0) {System.out.println("\nStudent Updated Successfully !");returnMenu();}
        else{System.out.println("\nError in Updating Data !");returnMenu();}
    }
    
    public void updateDept() throws Exception {
        console.printf("Enter ID : ");
        id = Integer.parseInt(console.readLine());
        console.printf("Enter new Dept : ");
        newdept = console.readLine();
        pstmt = con.prepareStatement("update students set dept = ? where id = ?");
        pstmt.setString(1, newdept);pstmt.setInt(2, id);
        retStatus = pstmt.executeUpdate();
        if (retStatus>0) {System.out.println("\nStudent Updated Successfully !");returnMenu();}
        else{System.out.println("\nError in Updating Data !");returnMenu();}
    }
    
    public void updateClass() throws Exception {
        console.printf("Enter ID : ");
        id = Integer.parseInt(console.readLine());
        console.printf("Enter new Class : ");
        newclass = console.readLine();
        pstmt = con.prepareStatement("update students set class = ? where id = ?");
        pstmt.setString(1, newclass);pstmt.setInt(2, id);
        retStatus = pstmt.executeUpdate();
        if (retStatus>0) {System.out.println("\nStudent Updated Successfully !");returnMenu();}
        else{System.out.println("\nError in Updating Data !");returnMenu();}
    }
    
    public void updateJPR() throws Exception {
        console.printf("Enter ID : ");
        id = Integer.parseInt(console.readLine());
        console.printf("Enter updated JPR Marks : ");
        newjpr = Integer.parseInt(console.readLine());
        pstmt = con.prepareStatement("update students set jpr = ? where id = ?");
        pstmt.setInt(1, newjpr);pstmt.setInt(2, id);
        retStatus = pstmt.executeUpdate();
        if (retStatus>0) {System.out.println("\nStudent Updated Successfully !");returnMenu();}
        else{System.out.println("\nError in Updating Data !");returnMenu();}
    }
    
    public void updateDCC() throws Exception {
        console.printf("Enter ID : ");
        id = Integer.parseInt(console.readLine());
        console.printf("Enter updated DCC Marks : ");
        newdcc = Integer.parseInt(console.readLine());
        pstmt = con.prepareStatement("update students set dcc = ? where id = ?");
        pstmt.setInt(1, newdcc);pstmt.setInt(2, id);
        retStatus = pstmt.executeUpdate();
        if (retStatus>0) {System.out.println("\nStudent Updated Successfully !");returnMenu();}
        else{System.out.println("\nError in Updating Data !");returnMenu();}
    }

    public void updateMIC() throws Exception {
        console.printf("Enter ID : ");
        id = Integer.parseInt(console.readLine());
        console.printf("Enter updated MIC Marks : ");
        newmic = Integer.parseInt(console.readLine());
        pstmt = con.prepareStatement("update students set mic = ? where id = ?");
        pstmt.setInt(1, newmic);pstmt.setInt(2, id);
        retStatus = pstmt.executeUpdate();
        if (retStatus>0) {System.out.println("\nStudent Updated Successfully !");returnMenu();}
        else{System.out.println("\nError in Updating Data !");returnMenu();}
    }

    public void updateSEN() throws Exception {
        console.printf("Enter ID : ");
        id = Integer.parseInt(console.readLine());
        console.printf("Enter updated SEN Marks : ");
        newsen = Integer.parseInt(console.readLine());
        pstmt = con.prepareStatement("update students set sen = ? where id = ?");
        pstmt.setInt(1, newsen);pstmt.setInt(2, id);
        retStatus = pstmt.executeUpdate();
        if (retStatus>0) {System.out.println("\nStudent Updated Successfully !");returnMenu();}
        else{System.out.println("\nError in Updating Data !");returnMenu();}
    }
    
    public void showData() throws Exception {
        connect();
        pstmt = con.prepareStatement("select * from students");
        rs = pstmt.executeQuery();
        System.out.println("\nPlease wait.. we are fetching your data..");
        delay(3);
        header();
        while(rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String dept = rs.getString("dept");
            String sclass = rs.getString("class");
            int jpr = rs.getInt("jpr");
            int dcc = rs.getInt("jpr");
            int mic = rs.getInt("mic");
            int sen = rs.getInt("sen");
            displayStudentData(id, name, dept, sclass, jpr, dcc, mic, sen);
        }
        footer();
        delay(5);
        clearConsole();
        menu();
    }
    public void returnMenu(){
        delay(3);
        clearConsole();
        menu();
    }
}

class ConsoleApp extends App implements Admin {

    static Thread td = new Thread();

    public Scanner sc = new Scanner(System.in);
    String admin_name;
    String admin_pwd;

    @Override
    public void admin_login() {
        do {
            clearConsole();
            console.printf("Enter admin username : "); admin_name = console.readLine();
            console.printf("Enter admin password : "); admin_pwd = console.readLine();
            if (!(admin_name.equals("batman") && admin_pwd.equals("bruce"))) {
                System.out.println("\nInvalid Credentials! Try again..");
                delay(2);
            }else {
                System.out.println("\nHello Mr." + admin_name);
                returnMenu();
            }
        }while (!(admin_name.equals("batman") && admin_pwd.equals("bruce")));
    }

    public static void main(String[] args) throws Exception {
        
        ConnectDB cdb = new ConnectDB();
        try{
            cdb.connect();
        }catch(Exception e){System.out.println("Failed to connect to Database");}

        ConsoleApp c = new ConsoleApp();
        c.admin_login();
    }

}

