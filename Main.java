import java.util.*;

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {

        return this.username;
    }


    public boolean authenticates(String password) {
        return this.password.equals(password);
    }
}

class LoginPage {
    private HashMap<String, User> users;

    public LoginPage() {
        users = new HashMap<>();
        /*
         * Adding a default username-password for store for admin use.
         */
        users.put("admin", new User("admin", "password"));
    }

    public void Register() {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter Username: ");
        String username = s.next();
        /*
         * Checking for duplicate username, to avoid discrepencies on a later stage.
         */
        if (users.containsKey(username)) {
            System.out.println("The username is already taken. Please try with a different username.");
            return;
        }
        System.out.print("Enter the Password: ");
        String password = s.next();
        /*
         * Creating a seperate object for each new user.
         * This is created to add the details to the HashMap(data bank).
         */
        User newuser = new User(username, password);
        users.put(username, newuser);
        System.out.println("Registeration successful !! ");
    }

    public boolean Login() {
        Scanner s = new Scanner(System.in);
        /*
         * Getting details from the user for Login procedure.
         */
        System.out.print("Enter the Username: ");
        String username = s.next();
        System.out.print("Enter the Password: ");
        String password = s.next();
        User user = users.get(username);
        boolean f = false;
        /*
         * Checking whether username exists in the HashMap.
         * If exists, the password is retrieved from the data structure and checked.
         * This is carried out using authenticates() function.
         */
        if (user != null && user.authenticates(password)) {
            System.out.println("Login successful, Welcome " + user.getUsername() + " to the Online Store");
            f = true;
        } else {
            System.out.println("Login failed, Invalid credentials.");
        }
        return f;
    }
}

class Books {
    private String title;
    private String author;
    private double price;
    private int availability;

    /*
     * Define get Methods for all data-types.
     * Define setting availability function for book.
     */
    public Books(String title, String author, double price, int availability) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.availability = availability;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }
}

class OnlineBookStore {
    private HashMap<Integer, Books> Catalog;

    public OnlineBookStore() {
        Catalog = new HashMap<>();
        /*
         * Initializing with some books in book bank.
         * We can add new Books while managing BookStore.
         */
        Catalog.put(1, new Books("Harry Potter", "J K Rowling", 899, 2));
        Catalog.put(2, new Books("Software Eng.", "Sandy", 654, 4));
        Catalog.put(3, new Books("Intro to Python", "A J R Uttam", 1399, 7));
    }

    public void displayCatalog() {
        /*
         * Printing all the elements of the Catalog to the user.
         * The user can choose what book to buy from the catalog.
         */
        System.out.println("------ Book Catalog ------");
        Set<Integer> keys = Catalog.keySet();
        Iterator<Integer> iterator = keys.iterator();
        while (iterator.hasNext()) {
            int key = iterator.next();
            Books b = Catalog.get(key);
            System.out.println("Book key: " + key + " Book name: " + b.getTitle() + " Author name: " + b.getAuthor()
                    + " Price: " + b.getPrice() + " Availability: " + b.getAvailability());
        }
    }

    public Books getBook(int bookId) {
        return Catalog.get(bookId);
    }
}

class ShoppingCart {
    private HashMap<Books, Integer> cart;

    /*
     * Initializing a empty HashMap for storing shopping cart details.
     */
    public ShoppingCart() {
        cart = new HashMap<>();
    }

    /*
     * Separate methods(functions) for adding to cart and viewing cart.
     * Adding to cart is carried out with object Books as key and integer as value.
     * ViewCart function is printing all keys along with their values.
     */
    public void addtoCart(Books book) {
        int quantity = cart.getOrDefault(book, 0);
        if (book.getAvailability() > quantity) {
            cart.put(book, quantity + 1);
            System.out.println(book.getTitle() + " added to the Shopping cart");
        } else {
            System.out.println(book.getTitle() + " is currently out of stock");
        }
    }

    public void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("Shopping cart is empty.");
        } else {
            System.out.println("--- Shopping Cart ---");
            for (Map.Entry<Books, Integer> entry : cart.entrySet()) {
                Books book = entry.getKey();
                int quantity = entry.getValue();
                System.out.println("Title: " + book.getTitle() + ", Author: " + book.getAuthor() + ", Quantity: "
                        + quantity + ", Price per item: Rs." + book.getPrice() + ", Subtotal: Rs."
                        + (book.getPrice() * quantity));
            }
        }
    }

    public void checkOut() {
        if (cart.isEmpty()) {
            System.out.println("Shopping cart is empty.");
        } else {
            System.out.println("--- CheckOut ---");
            double totalBill = 0;
            for (Map.Entry<Books, Integer> entry : cart.entrySet()) {
                Books book = entry.getKey();
                int quantity = entry.getValue();
                double eachprice = book.getPrice() * quantity;
                totalBill += eachprice;
                System.out.println("Title: " + book.getTitle() + ", Author: " + book.getAuthor() + ", Quantity: "
                        + quantity + ", Price per item: Rs." + book.getPrice() + ", Subtotal: $"
                        + (book.getPrice() * quantity));
            }
            System.out.println("--- --- ---");
            System.out.println("The total amount to be paid is Rs." + totalBill);
        }
        cart.clear();
    }
}

class WishList {
    private HashMap<Books, Integer> wish;

    /*
     * Initializing a empty HashMap for storing Wish list details.
     */
    public WishList() {
        wish = new HashMap<>();
    }

    /*
     * Separate methods(functions) for adding to wishlist and viewing it.
     * Adding to wishlist is carried out with object Books as key and integer as
     * value.
     * ViewWishlist function is printing all keys along with their values.
     */
    public void addtoWish(Books book) {
        int quantity = wish.getOrDefault(book, 0);
        if (book.getAvailability() > quantity) {
            wish.put(book, quantity + 1);
            System.out.println(book.getTitle() + " added to the WishList");
        } else {
            System.out.println(book.getTitle() + " is currently out of stock");
        }
    }

    public void viewWishlist() {
        if (wish.isEmpty()) {
            System.out.println("Wish List is empty.");
        } else {
            System.out.println("--- Wish List ---");
            for (Map.Entry<Books, Integer> entry : wish.entrySet()) {
                Books book = entry.getKey();
                int quantity = entry.getValue();
                System.out.println("Title: " + book.getTitle() + ", Author: " + book.getAuthor() + ", Quantity: "
                        + quantity + ", Price per item: $" + book.getPrice() + ", Subtotal: $"
                        + (book.getPrice() * quantity));
            }
        }
    }
}

class Session implements Runnable {
    public void run() {
        boolean f = false;
        LoginPage newlogin = new LoginPage();
        Scanner s = new Scanner(System.in);
        while (true && !f) {
            System.out.println("Welcome to Online Book Store");
            System.out.println("\n--- Login Page ---");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = s.nextInt();
            s.nextLine();
            switch (choice) {
                case 1:
                    f = newlogin.Login();
                    break;
                case 2:
                    newlogin.Register();
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            /*
             * Once Logged-in as old user/Registered as new user, The Online book store's
             * function begins.
             * Options will be provided to the user, user can select the required option as
             * per their need.
             */
            OnlineBookStore bookStore = new OnlineBookStore();
            ShoppingCart newcart = new ShoppingCart();
            WishList wishinglist = new WishList();
            System.out.println();
            System.out.println("-------------------");
            System.out.println("Welcome, What are you looking for ?");
            while (true) {
                System.out.println("\n--- Main Menu ---");
                System.out.println("1. View Book Catalog");
                System.out.println("2. Add Book to Cart");
                System.out.println("3. View Cart");
                System.out.println("4. Add Book to WishList");
                System.out.println("5. View WishList");
                System.out.println("6. Checkout");
                System.out.println("7. Exit");
                System.out.print("Enter your choice: ");
                choice = s.nextInt();
                s.nextLine();

                switch (choice) {
                    case 1:
                        bookStore.displayCatalog();
                        break;
                    case 2:
                        System.out.print("Enter the Book ID: ");
                        int bookId = s.nextInt();
                        Books book = bookStore.getBook(bookId);
                        if (book != null) {
                            newcart.addtoCart(book);
                        } else {
                            System.out.println("Invalid Book ID. Please try again.");
                        }
                        break;
                    case 3:
                        newcart.viewCart();
                        break;
                    case 4:
                        System.out.print("Enter the Book ID: ");
                        int bookwish = s.nextInt();
                        Books book2 = bookStore.getBook(bookwish);
                        if (book2 != null) {
                            wishinglist.addtoWish(book2);
                        } else {
                            System.out.println("Invalid Book ID. Please try again.");
                        }
                        break;
                    case 5:
                        wishinglist.viewWishlist();
                        break;
                    case 6:
                        System.out.println("Do you want to give feedback/Review for our system(Y/N)?");
                        char response = s.nextLine().charAt(0);
                        if (response == 'Y') {
                            System.out.print("Give Your Rating (1-10): ");
                            int rating = s.nextInt();
                            System.out.println("Thanks for your feedback");
                        }
                        newcart.checkOut();
                        break;
                    case 7:
                        System.out.println("Thank you for shopping with us. Goodbye!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        /*
         * Each thread will run the session class.
         * This is done to achieve concurrency.
         */
        int n = 2;
        for (int i = 0; i < n; i++) {
            Session newSession = new Session();
            Thread thread = new Thread(newSession);
            thread.start();
            /*
             * Here, thread.join() method to used to differntiate each customer output from
             * each.
             * thread.join() makes each customer start after the previous customer leaves.
             * To get concurrency, Comment the try-catch block.
             * By doing so, all customers can access the Online-Book store concurrently.
             */
            try {
                thread.join();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
