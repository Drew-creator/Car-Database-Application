/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectstartup;

/**
 *
 * 
 */
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Main {

    private static HashMap<String, Car> inventory;
    private static int numberOfCars = 0;

    public static void main(String[] args) {
       //initialize the inventory
       inventory = new HashMap<String, Car>();
       
       
       //Present the customer with a choice of vehicles
		String[]  choices = {"Add Car", "Remove Car", "Update Car", "List Cars", "Clear Cars", "Find Car" , "Exit"};

                //keep the menu up unless exiting
                while(true){ 
                    int response = JOptionPane.showOptionDialog(
                                    null                       					// center over parent
                                    , "Select Operation to perform"                                 // message
                                    , "Database Operations"                         		// title in titlebar
                                    , JOptionPane.YES_NO_OPTION  					// Option type
                                    , JOptionPane.PLAIN_MESSAGE  					// messageType
                                    , null                       					// icon
                                    , choices                    					// Options
                                    , "Add Car"    							// initial value
                    );
                    switch (response){

                        case 0:
                            addCar();
                            break;
                        case 1:
                            removeCar();
                            break;                      
                        case 2:
                            updateCar();
                            break;
                        case 3:
                            listCars();
                            break;
                        case 4:
                            clearCars();
                            break;
                        case 5: 
                            findCar();
                            break;
                        default:
                               exitApplication();

                    }
                }
                
    }
    
    
    private static void addCar(){
        
        //ask user for input
        String carInfo = JOptionPane.showInputDialog("ENTER THE CAR INFORMATION SEPERATED "
                + "BY SPACES\n\n" + "VIN CAR_MAKE CAR_MODEL CAR_YEAR");
        
        //add code to read the data entered and use split to break it into variables
        try{
            String carVin = carInfo.split(" ")[0];
            String carMake = carInfo.split(" ")[1];
            String carModel = carInfo.split(" ")[2];
            String carYear = carInfo.split(" ")[3];

            int year = Integer.parseInt(carYear);

            //add code to save the data entered by the user to the hashmap as a VIN and a CAR object
            Car car = new Car(carVin, carMake, carModel, year);       
            inventory.put(carVin, car);      
            
            JOptionPane.showMessageDialog(null, "SUCCESS! NEW CAR ADDED");
            numberOfCars++;

        }
        //catches exception and displays an error message to the user
        catch(Exception e) {
            
            //this if-statement prevents error message from popping up if "cancel" is pressed
            if(carInfo == null) {
                
            }
            else {
                JOptionPane.showMessageDialog(null, "INCORRECT INPUT!");
            }
            
        }
        
    }
    
    private static void removeCar(){
        //add code to ask user for data entry and remove a car from the hashmap
        //Example using the validator class to check data for issues
        String vin = JOptionPane.showInputDialog("ENTER THE VIN OF THE CAR TO REMOVE");
        
        //check the hashmap for car then remove
        if(inventory.containsKey(vin)){
            
            inventory.remove(vin);
            
            JOptionPane.showMessageDialog(null,"CAR WITH VIN: " + vin + " REMOVED!");
        }
        //this else-if statement prevents error message from displaying to user when "cancel" button is pressed
        else if(vin == null) {
            
        }
        else{
            JOptionPane.showMessageDialog(null, "CAR WITH VIN: " + vin + " WAS NOT FOUND IN INVENTORY!");
        }      
        
        numberOfCars--;
    }
    
    private static void updateCar(){
        
        String updateVIN;
        
        updateVIN = JOptionPane.showInputDialog("ENTER THE VIN OF THE CAR "
                    + "TO UPDATE\n\n VIN");
        
        //add code to ask user for data entry and update the car in the hashmap
        if(inventory.containsKey(updateVIN)) {
            
            String carInfo = JOptionPane.showInputDialog("ENTER THE CAR INFORMATION SEPERATED "
                    + "BY SPACES\n\n" + "CAR_MAKE CAR_MODEL CAR_YEAR");
            
            try {

                String carMake = carInfo.split(" ")[0];
                String carModel = carInfo.split(" ")[1];
                String carYear = carInfo.split(" ")[2];

                int year = Integer.parseInt(carYear);

                //add code to save the data entered by the user to the hashmap as a VIN and a CAR object
                Car car = new Car(updateVIN, carMake, carModel, year); //creates new car instance       
                inventory.put(updateVIN, car); //replaces that current car instance associated with the VIN

                JOptionPane.showMessageDialog(null,"CAR WITH VIN: " + updateVIN + " UPDATED!");
                
            }
            catch(Exception e) {
                if(carInfo == null) {
                
                }
                else {
                    JOptionPane.showMessageDialog(null, "INCORRECT INPUT!");
                }
            }
            
        }
        //this else-if statement prevents error message from displaying to user when "cancel" button is pressed
        else if(updateVIN == null) {
            
        }
        else {
            JOptionPane.showMessageDialog(null, "CAR WITH VIN: " + updateVIN + " WAS NOT FOUND IN INVENTORY!");
        }
    }
    
    private static void listCars(){
       
        String summary = "MAXCAR Listing\n\n";
    
        for( Map.Entry<String, Car> carSummary : inventory.entrySet()){
        
            summary += carSummary.getValue().getSummary() + "\n";
        }
        
        JOptionPane.showMessageDialog(null,new JTextArea(summary));
    
    }
    
    private static void clearCars(){
        //add code to clear the hashmap
        inventory.clear(); 
        JOptionPane.showMessageDialog(null, numberOfCars + " cars cleared from Database!");
        
        numberOfCars = 0;
    }
    
    private static void findCar() {
        String findVIN;
        findVIN = JOptionPane.showInputDialog("ENTER THE VIN OF THE CAR TO FIND\n\n VIN");
        
        String result = "Not Found!";
        
        if(inventory.containsKey(findVIN)) {
            
            for(Map.Entry<String,Car> carEntry: inventory.entrySet()) {
                
                if(carEntry.getKey().equalsIgnoreCase(findVIN)) {
                    result = "CAR VIN: " + findVIN + " was found!\n\n" 
                            + carEntry.getValue().getSummary() + "\n";
                }
            }
            
            JOptionPane.showMessageDialog(null,new JTextArea(result));
        }
        //this else-if statement prevents error message from displaying to user when "cancel" button is pressed
        else if(findVIN == null) {
            
        }
        else {
            JOptionPane.showMessageDialog(null, "CAR WITH VIN: " + findVIN + " WAS NOT FOUND IN INVENTORY!");
        }
    }
    
    private static void exitApplication(){
        JOptionPane.showMessageDialog(null, "Thank you for using the MaxCar Application. " + inventory.size() + " cars saved!...EXITING...");
        System.exit(0);
    }
    
}
