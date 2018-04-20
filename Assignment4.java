// SIMULATING PRINTER USAGE
// Use to determine, in one hour, what the average wait time is 
// for a student to wait for their print job to be finished

import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

public class Assignment4 {

	public static void main(String[] args) {
		simulate(3600, 5);	
	} // main()

	
	// Simulate for given time (seconds) for a printer that prints at speed (ppm)
	public static void simulate(int seconds, int ppm) {
		
		// CREATE A NEW PRINTER AT SPEED PPM
		Printer labPrinter = new Printer(ppm);

		// CREATE A NEW PRINTERQUEUE (A QUEUE OF TASKS)
		Queue<Task> printerQueue = new LinkedList<Task>();
		
		// CREATE AN ARRAYLIST TO RECORD WAIT TIMES
		// NOTE: although this particular ppm will result in wait times that are only integers,
		// 		 if the ppm changes then the wait times may not be integers anymore.
		ArrayList<Double> waitTimes = new ArrayList<Double>();
		
		// Variables for recording data
		int jobNumber = 0;				// print job #. Also keeps track of total print tasks completed
		//int totalPages = 0;				// total # of pages printed so far
		
		// Convert ppm to pages per second, to make sure all units are in seconds
		double pps = (double)ppm/60;
		
		// FOR EACH TICK (SECOND):
		for (int time = 1; time < seconds; time++) {
			// EVENT: IF THERE IS A NEW TASK (see function newPrintTask()):
			if (newPrintTask()) {
				// CREATE A NEW TASK AND ADD IT TO THE PRINTERQUEUE
				Task newIndividualTask = new Task(time);
				printerQueue.add(newIndividualTask);				
			}
			
			// IF PRINTER IS NOT BUSY AND THE PRINTERQUEUE IS NOT EMPTY:
			if (!labPrinter.busy() && !printerQueue.isEmpty()) {
				// REMOVE NEXT TASK FROM PRINTERQUEUE
				Task printTask = printerQueue.remove();
				
				// Print the task timestamp and number of pages
				System.out.println(printTask);
				
				// Increment the index for the print job #
				jobNumber++;
				
				// Keep track of the total number of pages printed so far
				//totalPages += printTask.getPages();
				//System.out.println("Total number of pages printed so far: " + totalPages);
				
				// RECORD WAIT TIME FOR THIS TASK
				// Total wait time = wait time in queue + service time
				double serviceTime = printTask.getPages()/pps;	
				double queueWaitTime = printTask.waitTime(time);
				//System.out.println("Service time for print job #" + jobNumber + ": " + serviceTime + " seconds");
				//System.out.println("Wait time for this task (minus service): " + queueWaitTime);
				
				double individualWait = queueWaitTime + serviceTime;
				
				// Only add the task's wait time to the ArrayList if it is COMPLETED by the time the simulation ends
				double timeCompleted = time + individualWait;
				if (timeCompleted <= seconds){
					waitTimes.add(individualWait);
					System.out.println("Total wait time for print job #" + jobNumber + ": " + individualWait + " seconds \n");
				}
				else {
					System.out.println("Print job #" + jobNumber + " was not completed. \n");
				}
				
				// START THE NEXT TASK ON THE PRINTER
				labPrinter.startNext(printTask);
			}
			
			// Printer prints for one second
			labPrinter.tick();
		}

		// Recording measurements of pertinent information for this simulation
		System.out.println("END RESULTS: ");
		
		// Compute total wait time (wait time in queue + service time, for all print tasks)
		double totalWait = 0;
		
		for (int i=0; i < waitTimes.size(); i++) {
			totalWait += waitTimes.get(i);
		}
		System.out.println("Total wait time: " + totalWait + " seconds");
		
		// Number of print tasks completed
		int totalServed = Printer.getTasksCompleted();
		System.out.println("Number of print tasks completed: " + totalServed + " out of " + jobNumber + " tasks");
		
		// COMPUTE AVERAGE WAIT TIME AND PRINT RESULTS
		double avgWait = totalWait/(double)totalServed;
		System.out.println("Average wait time: " + avgWait + " seconds");

	} // simulate()
 
	
	public static boolean newPrintTask() {
		return (180 == ((int) (1 + 180 * Math.random())));
	} // newPrintTask()

} // Assignment4
