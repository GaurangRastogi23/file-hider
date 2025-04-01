package Views;

import Model.User;
import Service.GenerateOTP;
import Service.SendOTPService;
import Service.userService;
import dao.UserDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

public class Welcome {
    public  void welcomeScreen(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to the application.");
        System.out.println("Press 1 to login.");
        System.out.println("Press 2 to signup.");
        System.out.println("Press 0 to exist.");
        int choice =0;
        try{
            choice = Integer.parseInt(br.readLine());
        }catch(IOException e){
            e.printStackTrace();
        }
        switch (choice){
            case 1 -> login();
            case 2 -> signup();
            case 0 -> System.exit(0);
            default -> System.out.println("Invalid choice! Please try again.");
        }

    }

    private void signup() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name : ");
        String name = sc.nextLine();
        System.out.println("Enter your email : ");
        String email = sc.nextLine();

        String genOTP = GenerateOTP.getOTP();
        SendOTPService.sendOTP(email,genOTP);
        System.out.println("Enter the OTP: ");
        String otp = sc.nextLine();
        if(otp.equals(genOTP)){
            User user = new User(name , email);
            int response = userService.saveUser(user);
            switch (response) {
                case 0 -> System.out.println("User registered");
                case 1 -> System.out.println("User already exists");
            }

        } else {
            System.out.println("Wrong otp.");
        }
    }

    private void login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your email : ");
        String email = sc.nextLine();
        try{
            if(UserDAO.isExists(email)){
                String genOTP = GenerateOTP.getOTP();
                SendOTPService.sendOTP(email,genOTP);
                System.out.println("Enter the OTP: ");
                String otp = sc.nextLine();
                if(otp.equals(genOTP)){
                    new UserView(email).home();

                } else {
                    System.out.println("Wrong otp.");
                }
            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
}
