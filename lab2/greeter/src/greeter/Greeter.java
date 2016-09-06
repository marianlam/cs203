/*
 * A class for producing simple greetings
 */
package greeter;

/**
 *
 * @author Marian Lam
 */
public class Greeter {
    
    /**
     * Constructs a Greeter object that can greet a person or entity.
     * @param aName the name of the person or entity who should be addressed 
     * in the greetings.
     */
    public Greeter(String aName) {
        name = aName;
    }
    
    /**
     * Greet with a "Hello" message.
     * @return a message containing "Hello" and the name of the greeted
     * person or entity
     */
    public String sayHello() {
        return "Hello, " + name + "!";
    }

    /**
     * Sets a new name for a Greeter object
     * @param newName the new name being set for the person or entity who
     * should be addressed in the greetings
     */
    public void setName(String newName) {
        name = newName;
    }
    
    /**
     * Gets the name from this Greeter object
     * @return name the private instance field of this greeter object
     */
    public String getName() {
        return name;
    }
    
    /**
     * swaps the names of this greeter with another
     * @param other the other Greeter object
     */
    public void swapNames(Greeter other) {
        String OGName = name;
        name = other.getName();
        other.setName(OGName);
    }
    
    private String name;
    
}
