/*
 * Contains the main() method for testing the program
 */
package greeter;

/**
 *
 * @author Marian Lam
 */
public class GreeterApp {
        
    /**
     * Where execution starts
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Greeter g1 = new Greeter("Marian");
        Greeter g2 = new Greeter("Michelle");
        g1.setName("Morrie");
        System.out.println("g1: " + g1.sayHello());
        System.out.println("g2: " + g2.sayHello() + "\n");
        
        // testing swapNames(Greeter) method in Greeter class
        g1.swapNames(g2);
        System.out.println("g1: " + g1.sayHello());
        System.out.println("g2: " + g2.sayHello());
    }
    
    
}
