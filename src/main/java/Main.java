import java.sql.SQLException;
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
                        System.out.println("Clients");
                        break;
                    case 3:
                        System.out.println("Orders");
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
