/**
 * 
 */
package org.pjay.iot.pi4j;

import java.util.Scanner;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

/**
 * @author Vijay Konduru
 *
 */
public class Pi4JLedOnOffDemo {
	
	public static void main(String[] args) {
		// create gpio controller
		final GpioController gpio = GpioFactory.getInstance();
		// provision gpio pin #01 as an output pin and turn on
		final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "LED", PinState.LOW);
        // set shutdown state for this pin
        pin.setShutdownOptions(true, PinState.LOW);
		
		Scanner scanner = new Scanner(System.in);
		int noOfIterations = 6;
		String input = "";
		while(noOfIterations > 0){
			System.out.println(" :: Please enter ON/OFF to swith on/off led accordingly :: ");
			input = scanner.nextLine();
			if("ON".equalsIgnoreCase(input)){
				//System.out.println(" :: LED Is ON :: ");
				pin.high();
			}else if("OFF".equalsIgnoreCase(input)){
				//System.out.println(" :: LED Is OFF :: ");
				pin.low();
			}else{
				System.out.println("Please enter a valid value from ON/OFF");
			}
			
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			noOfIterations--;
		}
		gpio.shutdown();
		scanner.close();
	}

}
