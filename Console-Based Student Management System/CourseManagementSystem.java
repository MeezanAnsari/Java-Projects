import java.util.*;

interface CourseManagement {
    void displayCourses();
    void selectOption();
    void makePayment(String courseName, int amount) throws InputMismatchException;
    void buyCourse(String courseName, String shippingAddress, String recipientName, long phoneNumber) throws InputMismatchException;
}

public class CourseManagementSystem implements CourseManagement {

    static class CourseAmount {
        int ds = 10000;
        int cc = 6000;
        int wd = 2000;
        int ad = 8000;
        int ml = 12000;
        int iot = 7000;
    }

    static class DisplayingTheNameOfCourses {
        void course() {
            System.out.println();
            System.out.println("The Name of Our Team Providing the Courses is :");
            System.out.println("Algorithm Assassins");
            System.out.println();
            System.out.println("We have Number of courses Available :");
            System.out.println("1. Data Science");
            System.out.println("2. Cloud Computing");
            System.out.println("3. Web Development");
            System.out.println("4. Android Development");
            System.out.println("5. Machine Learning");
            System.out.println("6. Internet of Things (IOT)");
        }
    }

    static class SelectToViewThePriceOfCoursesOrNumberOFBuyers {
        void select() {
            System.out.println();
            System.out.println("Do you want to view the price of the course or number of buyers?");
            System.out.println("Please Enter 1 To see the price of the course and 2 to see the number of buyers");

            int choice;
            Scanner sc = new Scanner(System.in);
            System.out.println();
            System.out.println("Enter Your Choice:");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    displayCoursePrices();
                    break;
                case 2:
                    displayNumberOfBuyers();
                    break;
                default:
                    throw new InputMismatchException("Invalid Choice!");
            }
        }

        private static void displayCoursePrices() {
            System.out.println();
            System.out.println("The Price of the Courses are:");
            System.out.println("1. Data Science : Rs.10000/-");
            System.out.println("2. Cloud Computing : Rs.6000/-");
            System.out.println("3. Web Development : Rs.2000/-");
            System.out.println("4. Android Development : Rs.8000/-");
            System.out.println("5. Machine Learning : Rs.12000/-");
            System.out.println("6. Internet of Things (IOT) : Rs.7000/-");
            System.out.println("IF you want to buy then you can buy from here");
        }

        private static void displayNumberOfBuyers() {
            System.out.println();
            System.out.println("The Number of Buyers are:");
            System.out.println("1. Data Science : 1000 People have bought last month");
            System.out.println("2. Cloud Computing : 500 People have bought last month");
            System.out.println("3. Web Development : 200 People have bought last month");
            System.out.println("4. Android Development : 800 People have bought last month");
            System.out.println("5. Machine Learning : 1200 People have bought last month");
            System.out.println("6. Internet of Things (IOT) : 700 People have bought last month");
        }
    }

    static class Payment {
        Scanner scanner = new Scanner(System.in);

        public void makePayment(String courseName, int amount) throws InputMismatchException {
            System.out.println();
            System.out.println(" Initializing PayPal For Payment");
            processPayPalPayment(courseName, amount);
        }

        private void processPayPalPayment(String courseName, int amount) {
            CourseAmount o = new CourseAmount();
            System.out.println();
            System.out.println("Processing PayPal payment for " + courseName + " amount: Rs." + amount);
            System.out.println("Please enter your PayPal email:");
            String email = scanner.next();
        }
    }

    static class IfWantToBuy {
        Scanner scanner = new Scanner(System.in);

        public void buyCourse(String courseName, String shippingAddress, String recipientName, long phoneNumber) throws InputMismatchException {
            System.out.println();
            System.out.println("You are purchasing the physical course: " + courseName);
            System.out.println("Please enter the shipping address:");
            scanner.nextLine(); // consume newline
            shippingAddress = scanner.nextLine();
            System.out.println("Please enter the recipient's name:");
            recipientName = scanner.nextLine();
            System.out.println("Please enter your phone number:");
            phoneNumber = scanner.nextLong();

            // Display confirmation message
            System.out.println();
            System.out.println("Thank you, " + recipientName + "! Your physical course, " + courseName + ", will be shipped to the following address: ");
            System.out.println(shippingAddress);
            System.out.println("We will contact you at " + phoneNumber + " for further details.");
        }
    }

    public void displayCourses() {
        DisplayingTheNameOfCourses obj = new DisplayingTheNameOfCourses();
        obj.course();
    }

    public void selectOption() {
        SelectToViewThePriceOfCoursesOrNumberOFBuyers obj1 = new SelectToViewThePriceOfCoursesOrNumberOFBuyers();
        obj1.select();
    }

    public void makePayment(String courseName, int amount) throws InputMismatchException {
        Payment payment = new Payment();
        payment.makePayment(courseName, 0); // Pass 0 as placeholder
    }

    public void buyCourse(String courseName, String shippingAddress, String recipientName, long phoneNumber) throws InputMismatchException {
        IfWantToBuy obj2 = new IfWantToBuy();
        obj2.buyCourse(courseName, shippingAddress, recipientName, phoneNumber);
    }

    public static void main(String args[]) {
        CourseManagementSystem cms = new CourseManagementSystem();
        cms.displayCourses();
        cms.selectOption();

        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("Enter the course number you want to buy:");
        int courseChoice;
        try {
            courseChoice = sc.nextInt();

            String courseName = "";
            String shippingAddress = "";
            String recipientName = "";
            long phoneNumber = 0;

            switch (courseChoice) {
                case 1:
                    courseName = "Data Science";
                    break;
                case 2:
                    courseName = "Cloud Computing";
                    break;
                case 3:
                    courseName = "Web Development";
                    break;
                case 4:
                    courseName = "Android Development";
                    break;
                case 5:
                    courseName = "Machine Learning";
                    break;
                case 6:
                    courseName = "Internet of Things (IOT)";
                    break;
                default:
                    throw new InputMismatchException("Invalid course selection!");
            }

            cms.makePayment(courseName, 0);
            cms.buyCourse(courseName, shippingAddress, recipientName, phoneNumber);
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }
}