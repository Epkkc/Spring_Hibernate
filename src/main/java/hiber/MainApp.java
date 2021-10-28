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

      System.out.println("Saving users...");
      userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("Ford",1998)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("BMW",2000)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("Audi",2002)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("Lada",2004)));

      System.out.println("\nGetting users...");
      List<User> users = userService.listUsers();
      users.forEach(System.out::println);

      System.out.println("\nFinding user with car...");
      System.out.println(userService.getUserWithCar("Lada",2004));

      context.close();
   }
}
