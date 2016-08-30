/*
 * simple program that prints out a greeting along with the user's name.
 */
package greeter;

/**
 *
 * @author malam
 */
public class Greeter {

    // declare private String variable name
    private String name;
    
    // constructor that initializes instance variable name
    public Greeter(String name) {
        this.name = name;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Greeter newGreeter = new Greeter("Marian");
        sayHello(newGreeter.name);
    }
    
    /**
     * prints out a greeting
     * @param name 
     */
    public static void sayHello(String name) {
        System.out.println("Hello, " + name + "!");
    }
    
}
