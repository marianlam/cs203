/*
 * simple program that prints out a greeting along with the user's name.
 */
package mygreeter;

/**
 *
 * @author malam
 */
public class mygreeter {

    // declare private String variable name
    private String name;
    
    // constructor that initializes instance variable name
    public mygreeter(String name) {
        this.name = name;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        mygreeter newGreeter = new mygreeter("Marian");
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
