import com.mysql.cj.protocol.Resultset;

import java.math.BigDecimal;
import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n*****CAR RENTAL******\n");
            System.out.println("Cars - type: 1");
            System.out.println("Clients - type: 2");
            System.out.println("Orders - type: 3");
            System.out.println("Exit - type: 4");

            try {
                switch (scanner.nextInt()) {
                    case 1:
                        while (true) {
                            System.out.println("Show cars - type: 1");
                            System.out.println("Add cars - type: 2");
                            System.out.println("Remove cars - type: 3");
                            System.out.println("Modify cars - type: 4");
                            System.out.println("Return - type: 5");
                            switch (scanner.nextInt()) {
                                case 1:
                                    Cars.showCars();
                                    continue;
                                case 2:
                                    Cars.addCars();
                                    continue;
                                case 3:
                                    Cars.removeCars();
                                    continue;
                                case 4:
                                    Cars.modifyCars();
                                    continue;
                                case 5:
                                    break;
                                default:
                                    System.out.println("Incorrect value!");
                                    continue;
                            }
                            break;
                        }
                        break;
                    case 2:
                        while (true) {
                            System.out.println("Show clients - type: 1");
                            System.out.println("Add clients - type: 2");
                            System.out.println("Remove clients - type: 3");
                            System.out.println("Modify clients - type: 4");
                            System.out.println("Return - type: 5");
                            switch (scanner.nextInt()) {
                                case 1:
                                    Clients.showClients();
                                    continue;
                                case 2:
                                    Clients.addClients();
                                    continue;
                                case 3:
                                    Clients.removeClients();
                                    continue;
                                case 4:
                                    Clients.modifyClients();
                                    continue;
                                case 5:
                                    break;
                                default:
                                    System.out.println("Incorrect value!");
                                    continue;
                            }
                            break;
                        }
                        break;
                    case 3:
                        while (true) {
                            System.out.println("Show orders - type: 1");
                            System.out.println("Add orders - type: 2");
                            System.out.println("Remove orders - type: 3");
                            System.out.println("Modify orders - type: 4");
                            System.out.println("Return - type: 5");
                            switch (scanner.nextInt()) {
                                case 1:
                                    continue;
                                case 2:
                                    continue;
                                case 3:
                                    continue;
                                case 4:
                                    continue;
                                case 5:
                                    break;
                                default:
                                    System.out.println("Incorrect value!");
                                    continue;
                            }
                            break;
                        }
                        break;
                    case 4:
                        System.exit(0);
                    default:
                        System.out.println("Incorrect value!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Incorrect value!");
                scanner.nextLine();
            }
        }


    }
}
