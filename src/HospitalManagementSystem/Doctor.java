package HospitalManagementSystem;
import java.sql.*;
import java.util.Scanner;

public class Doctor {
    private Connection connection;
    private Scanner scanner = new Scanner(System.in);

    public Doctor(Connection connection){
        this.connection = connection;
    }

    public void addDoctor() {
        System.out.print("Enter Doctor ID: ");
        int id = scanner.nextInt();
        System.out.print("Enter Doctor Name: ");
        String name = scanner.next();
        System.out.print("Enter Doctor Specialization: ");
        String specialization = scanner.next();

        try{
            String query = "INSERT INTO doctors(id, name, specialization) VALUES(?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, specialization);
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows>0){
                System.out.println("Doctor Added Successfully!!");
            }else{
                System.out.println("Failed to add Doctor!!");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void viewDoctors(){
        String query = "select * from doctors";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Doctors: ");
            System.out.println("+------------+--------------------+------------------+");
            System.out.println("| Doctor Id  | Name               | Specialization   |");
            System.out.println("+------------+--------------------+------------------+");
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specialization = resultSet.getString("specialization");
                System.out.printf("| %-10s | %-18s | %-16s |\n", id, name, specialization);
                System.out.println("+------------+--------------------+------------------+");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean getDoctorById(int id){
        String query = "SELECT * FROM doctors WHERE id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }else{
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public void deleteDoctor() {
        System.out.print("Enter Doctor ID: ");
        int id = scanner.nextInt();

        try{
            String query = "DELETE FROM doctors WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows>0){
                System.out.println("Doctor Deleted Successfully!!");
            }else{
                System.out.println("Failed to Delete Doctor!!");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
