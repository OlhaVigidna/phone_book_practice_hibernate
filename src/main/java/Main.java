import models.Email;
import models.PhoneNumber;
import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Email.class)
                .addAnnotatedClass(PhoneNumber.class)
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

//        addUser(session);
//        System.out.println(findAllUsers(session));
//        User user = findById(3, session);
//        System.out.println(user.getEmails() + "  " + user.getUserPhone());
//        updateNameWhereId(2, "kokos", session);
//        System.out.println(findById(2, session));
//        System.out.println(findByName(session, "kokos"));
//        delete(1, session);

        addUser(session);


        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

    public static void addUser(Session session){
        Scanner scannerForString = new Scanner(System.in);
        Scanner scannerForInt = new Scanner(System.in);
        User currentUser = createUser();
        System.out.println("enter number of users phone numbers");
        int countOfPhones = scannerForInt.nextInt();
        for (int i = 0; i < countOfPhones; i++) {
             PhoneNumber currentPhoneNumber = createPhone();
             currentPhoneNumber.setPhoneUser(currentUser);
             session.save(currentPhoneNumber);
        }
        System.out.println("enter emails number");
        int countOfEmails = scannerForInt.nextInt();
        for (int i = 0; i < countOfEmails; i++) {
            Email currentEmail = createEmail();
            currentEmail.setUserEmail(currentUser);
            session.save(currentEmail);
        }
//        session.save(user);

    }

    public static Email createEmail(){
        Scanner scannerForString = new Scanner(System.in);
        Scanner scannerForInt = new Scanner(System.in);
        System.out.println("enter email domain");
        String domain = scannerForString.nextLine();
        System.out.println("enter email name");
        String emailName = scannerForString.nextLine();
        Email email = new Email(domain, emailName);
        return email;
    }

    public static PhoneNumber createPhone(){
        Scanner scannerForString = new Scanner(System.in);
        Scanner scannerForInt = new Scanner(System.in);
            System.out.println("enter phone code");
            String code = scannerForString.nextLine();
            System.out.println("enter phone number");
            int number = scannerForInt.nextInt();
            PhoneNumber phoneNumber = new PhoneNumber(code, number);
            return phoneNumber;
    }

    public static  User createUser(){
        Scanner scannerForString = new Scanner(System.in);
        System.out.println("enter user name");
        String userName = scannerForString.nextLine();
        System.out.println("enter user surname");
        String userSurname = scannerForString.nextLine();
        User user = new User(userName, userSurname);
        return user;
    }

    public static  List<User> findAllUsers(Session session){
        Query<User> select_e_from_e_e = session.createQuery("SELECT u FROM User u", User.class);
        List<User> resultList = select_e_from_e_e.getResultList();
        return resultList;
    }

    public static User findById(int id, Session session){
        User user = session.find(User.class, id);
        return user;
    }

    public static User findByName(Session session, String name){
        Query<User> query = session.createQuery("SELECT u FROM User u WHERE u.name=:x", User.class);
        query.setParameter("x", name);
        User user = query.getSingleResult();
        return user;
    }

    public static List<User> findByNameList (Session session, String name){
        Query<User> query = session.createQuery("SELECT u FROM User u WHERE u.name = :x", User.class);
        query.setParameter("x", name);
        List<User> resultList = query.getResultList();
        return resultList;
    }

    public static void updateNameWhereId(int id, String newName, Session session){
        User user = session.find(User.class, id);
        user.setName(newName);
        session.update(user);
    }

    public static void delete(int id, Session session){
        User user = session.find(User.class, id);
        session.delete(user);
    }
}
