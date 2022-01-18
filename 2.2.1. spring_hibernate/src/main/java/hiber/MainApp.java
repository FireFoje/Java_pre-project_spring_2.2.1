package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        User user4 = new User("User4", "Lastname4", "user4@mail.ru");

        Car car1 = new Car("Lada", 32);
        Car car2 = new Car("T-34", 34);
        Car car3 = new Car("Bike", 1000);
        Car car4 = new Car("Honda Civic", 2008);

        userService.add(user1.setCar(car1).setUser(user1));
        userService.add(user2.setCar(car2).setUser(user2));
        userService.add(user3.setCar(car3).setUser(user3));
        userService.add(user4.setCar(car4).setUser(user4));

        System.out.println("========================Users from cars=============================");
        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println(userService.getUserFromCar("T-34", 34));
            System.out.println();
        }
        System.out.println("========================Users with cars=============================");

        for (User user : users) {
            System.out.println(user + " " + user.getCar());
        }

        context.close();
    }
}
