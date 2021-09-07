import lombok.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Orders{

    private int id;
    private int id_car;
    private int id_client;
    private Date dateOfLoan;
    private Date dateOfReturn;


    public static List<Orders> showOrders() throws SQLException {
        List<Orders> ordersList = new ArrayList<>();
        Statement statement = JdbcConfig.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * from orders");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int id_car = resultSet.getInt("id_car");
            int id_client = resultSet.getInt("id_client");
            Date dateOfLoan = resultSet.getDate("dateOfLoan");
            Date dateOfReturn = resultSet.getDate("dateOfReturn");

            Orders orders = new Orders(id, id_car, id_client, dateOfLoan, dateOfReturn);
            ordersList.add(orders);
        }
        for (Orders orders : ordersList) {
            System.out.println(orders);
        }
        statement.close();
        resultSet.close();

        return ordersList;
    }
    public static void removeOrders() throws SQLException {
        Orders.showOrders();
        Scanner scanner = new Scanner(System.in);
        boolean notFoundID = true;
        Statement statement = JdbcConfig.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT id from orders");
        PreparedStatement preparedStatement;
        int id;
        System.out.println("\nType ID orders witch you want to remove");
        while (true) {
            try {
                id = scanner.nextInt();
                while (resultSet.next()) {
                    if (id == resultSet.getInt("id")) {
                        preparedStatement = JdbcConfig.getConnection().prepareStatement("DELETE from orders where id = " + id);
                        preparedStatement.execute();
                        System.out.println("Order with ID: " + id + " was removed");
                        notFoundID = false;
                        break;
                    }

                }
                if (notFoundID) {
                    System.out.println("ID does not exist");
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("\nIncorrect value - must be number");
                scanner.next();
            }
        }
        statement.close();
        resultSet.close();
    }
}
