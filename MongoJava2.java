package javamongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Scanner;

public class MongoJava2 {
    private static Scanner sc2;

    public static void main(String args[]) {
        MongoCollection<Document> coll = null;
        try {
            // To connect to MongoDB server
            MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");

            // Now connect to your database
            MongoDatabase db = mongoClient.getDatabase("diw");

            // Selecting the Collection
            coll = db.getCollection("Employee");
            System.out.println("Connected to database successfully");

            sc2 = new Scanner(System.in);
            int choice;
            do {
                System.out.println("Enter your choice of operation \n1. Display All \n2. Insert Document \n3. Delete Document \n4. Update \n5. Conditional Display \n6.Exit \n");
                choice = sc2.nextInt();
                switch (choice) {
                    case 1:
                        displayAll(coll);
                        break;
                    case 2:
                        insertDoc(coll);
                        break;
                    case 3:
                        deleteDoc(coll);
                        break;
                    case 4:
                        updateDoc(coll);
                        break;
                    case 5:
                        conditionalDisplay(coll);
                        break;
                    case 6:
                        System.out.println("Exiting Program...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println(choice + " is not a valid Menu Option! Please Select Another.");
                }
            } while (choice != 6);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void insertDoc(MongoCollection<Document> coll) {
        System.out.println("Inserting document");
        Document document = new Document();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Student rollno");
        int sroll = sc.nextInt();
        System.out.println("Enter Student Name");
        String sname = sc.next();
        System.out.println("Enter Student Class");
        String sclass = sc.next();
        System.out.println("Enter Student Marks");
        int smarks = sc.nextInt();
        System.out.println("Enter Student Technical Interest");
        String sti = sc.next();

        document.put("stu_rollno", sroll);
        document.put("stu_name", sname);
        document.put("class", sclass);
        document.put("marks", smarks);
        document.put("technical_interest", sti);

        coll.insertOne(document);
        System.out.println("Document inserted successfully");
    }

    public static void deleteDoc(MongoCollection<Document> coll) {
        System.out.println("Deleting document");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Student rollno");
        int sroll = sc.nextInt();

        Document document = new Document("stu_rollno", sroll);
        coll.deleteOne(document);
        System.out.println("Document deleted successfully");
    }

    public static void updateDoc(MongoCollection<Document> coll) {
        System.out.println("Updating document");
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Student rollno");
        int sroll = sc.nextInt();
        Document searchQuery = new Document("stu_rollno", sroll);

        System.out.println("Enter New marks");
        int smarks = sc.nextInt();
        Document newDocument = new Document("$set", new Document("marks", smarks));

        coll.updateOne(searchQuery, newDocument);
        System.out.println("Document updated successfully");
    }

    public static void displayAll(MongoCollection<Document> coll) {
        System.out.println("Displaying all documents in collection");
        try (MongoCursor<Document> cursor = coll.find().iterator()) {
            while (cursor.hasNext()) {
                System.out.println(cursor.next());
            }
        }
    }

    public static void conditionalDisplay(MongoCollection<Document> coll) {
        System.out.println("Enter Minimum marks");
        Scanner sc = new Scanner(System.in);
        int smarks = sc.nextInt();

        try (MongoCursor<Document> cursor = coll.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                int marks = doc.getInteger("marks");
                if (marks > smarks) {
                    System.out.println(doc);
                }
            }
        }
    }
}
