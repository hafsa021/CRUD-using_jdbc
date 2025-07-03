import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class CRUD {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/elevate_labs";
            String userid = "root";
            String pwd = "";
            Connection con = DriverManager.getConnection(url, userid, pwd);
            Statement stmt = con.createStatement();
            Scanner sc = new Scanner(System.in);
            String name = "";
            int roll;
            int total_mark;
            int option;
            do {
                System.out.println("****************************** CRUD MENU ******************************");
                System.out.println("1. Insert data into the table ");
                System.out.println("2. Read/View data from the table ");
                System.out.println("3. Update the student data");
                System.out.println("4. Delete desird student data ");
                System.out.println("5. Exit from the menu");
                System.out.println("Enter any choice from 1 to 5");
                option = sc.nextInt();
                switch (option) {
                    case 1:
                        System.out.println("Enter Roll :");
                        roll = sc.nextInt();
                        System.out.println("Enter Name :");
                        name = sc.next();
                        System.out.println("Enter total mark :");
                        total_mark = sc.nextInt();
                        stmt.executeUpdate(
                                "INSERT INTO student VALUES(" + roll + ", '" + name + "', " + total_mark + ")");
                        System.out.println("Data inserted successfully..");
                        break;

                    case 2:
                        System.out.println("*************** All Student Data ***************");
                        ResultSet rs = stmt.executeQuery("SELECT * FROM student");
                        System.out.println(String.format("%-10s | %-10s | %-10s", "roll", "name", "total mark"));
                        System.out.println("------------------------------------------");
                        while (rs.next()) {
                            System.out.println(String.format("%-10d | %-10s | %-10d",
                                    rs.getInt("roll"), rs.getString("name"), rs.getInt("total_mark")));
                        }
                        break;

                    case 3:
                        System.out.println("Enter roll to update:");
                        roll = sc.nextInt();
                        System.out.println("Enter name for roll " + roll + ":");
                        name = sc.next();
                        System.out.println("Enter total mark for roll " + roll + ":");
                        total_mark = sc.nextInt();
                        stmt.executeUpdate(
                                "UPDATE student SET name='" + name + "', total_mark=" + total_mark + " WHERE roll="
                                        + roll);
                        System.out.println("Name and Age of roll : " + roll + "updated successfully..");

                        break;

                    case 4:
                        System.out.println("Enter roll to delete:");
                        roll = sc.nextInt();
                        stmt.executeUpdate("DELETE FROM student WHERE roll=" + roll);
                        System.out.println("the data of roll : " + roll + " is deleted successfully..");
                        break;

                    case 5:
                        System.out.println("Exiting from the menu..");
                        break;

                    default:
                        System.out.println("Invalid input. please choose from 1 to 5");

                }
            } while (option != 5);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
