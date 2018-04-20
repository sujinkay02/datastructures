
public class Printer {
	
	private int pageRate;						// pages per minute (ppm)
	private Task currentTask;					// current task on printer
	private int timeRemaining;					// time remaining on current task
	private static int tasksCompleted = 0;		// # print tasks completed so far
	
	public Printer(int ppm) {
		pageRate = ppm;
		currentTask = null;
		timeRemaining = 0;
	} // Printer()

	public static int getTasksCompleted() {
		return tasksCompleted;
	}
	public void tick() {
		// Student is serviced for 1 second
		if (currentTask != null) {
			timeRemaining--;
			
			if (timeRemaining == 0) {
				currentTask = null;
				tasksCompleted++;
			}
		}
	} // tick()
	
	public boolean busy() {
		return currentTask != null;
	}
	
	public void startNext(Task newTask) {
		currentTask = newTask;
		timeRemaining = newTask.getPages() * 60 / pageRate;
	} // startNext()

	
} // class Printer