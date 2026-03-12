package com.example.blog;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import java.util.*;

public class App {

    static SessionFactory factory = new Configuration().configure().buildSessionFactory();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while(true) {

            System.out.println("\n--- BLOG SYSTEM ---");
            System.out.println("1 Add User");
            System.out.println("2 View Users");
            System.out.println("3 Update User");
            System.out.println("4 Delete User");

            System.out.println("5 Add Blog");
            System.out.println("6 View Blogs");

            System.out.println("7 Add Comment");
            System.out.println("8 View Comments");

            System.out.println("9 Exit");

            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice) {

                case 1:
                    addUser(sc);
                    break;

                case 2:
                    viewUsers();
                    break;

                case 3:
                    updateUser(sc);
                    break;

                case 4:
                    deleteUser(sc);
                    break;

                case 5:
                    addBlog(sc);
                    break;

                case 6:
                    viewBlogs();
                    break;

                case 7:
                    addComment(sc);
                    break;

                case 8:
                    viewComments();
                    break;

                case 9:
                    factory.close();
                    System.exit(0);
            }
        }
    }

    static void addUser(Scanner sc) {

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        User u = new User(name,email);
        session.save(u);

        tx.commit();
        session.close();

        System.out.println("User added");
    }

    static void viewUsers() {

        Session session = factory.openSession();

        List<User> users = session.createQuery("from User",User.class).list();

        for(User u : users) {
            System.out.println(u.getId()+" "+u.getName()+" "+u.getEmail());
        }

        session.close();
    }

    static void updateUser(Scanner sc) {

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter user id: ");
        int id = sc.nextInt();
        sc.nextLine();

        User u = session.get(User.class,id);

        System.out.print("Enter new name: ");
        String name = sc.nextLine();

        u.setName(name);

        session.update(u);

        tx.commit();
        session.close();

        System.out.println("User updated");
    }

    static void deleteUser(Scanner sc) {

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter user id to delete: ");
        int id = sc.nextInt();

        User u = session.get(User.class,id);
        session.delete(u);

        tx.commit();
        session.close();

        System.out.println("User deleted");
    }

    static void addBlog(Scanner sc) {

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter title: ");
        String title = sc.nextLine();

        System.out.print("Enter content: ");
        String content = sc.nextLine();

        System.out.print("Enter user id: ");
        int userId = sc.nextInt();

        Blog b = new Blog(title,content,userId);
        session.save(b);

        tx.commit();
        session.close();

        System.out.println("Blog added");
    }

    static void viewBlogs() {

        Session session = factory.openSession();

        List<Blog> blogs = session.createQuery("from Blog",Blog.class).list();

        for(Blog b : blogs) {
            System.out.println(b.getId()+" "+b.getTitle()+" "+b.getContent()+" user:"+b.getUser_id());
        }

        session.close();
    }

    static void addComment(Scanner sc) {

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter comment: ");
        String text = sc.nextLine();

        System.out.print("Enter blog id: ");
        int blogId = sc.nextInt();

        System.out.print("Enter user id: ");
        int userId = sc.nextInt();

        Comment c = new Comment(text,blogId,userId);
        session.save(c);

        tx.commit();
        session.close();

        System.out.println("Comment added");
    }

    static void viewComments() {

        Session session = factory.openSession();

        List<Comment> comments = session.createQuery("from Comment",Comment.class).list();

        for(Comment c : comments) {
            System.out.println(c.getId()+" "+c.getText()+" blog:"+c.getBlog_id()+" user:"+c.getUser_id());
        }

        session.close();
    }
}