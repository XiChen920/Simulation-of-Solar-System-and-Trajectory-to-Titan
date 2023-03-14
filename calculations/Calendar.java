package phase3.calculations;

/**
 * A class which is used to express time as a function of the stepsizes adding up since 1 april 2020
 *
 */
public class Calendar {	
					
	private int[] leapYear = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	private int[] normalYear = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	private int yearsPassed;
	private int weeksPassed;
	private int daysPassed;
	private int hoursPassed;
	private int minutesPassed;
	private double secondsPassed;
	
	private int year;
	private int month;
	private int day;
	
	private boolean isLeapYear;
	private int deltaDays;
	private double deltaSeconds;
	
	private boolean pause;
	
	
	public Calendar() {
		isLeapYear = false;
		yearsPassed = 0;
		weeksPassed = 0;
		daysPassed = 0;
		hoursPassed = 0;
		secondsPassed = 0;
		
		deltaDays = 0;
		deltaSeconds = 0;
		
		year = 2020;
		day = 1;
		month = 4;
		
		pause = false;
	}
	
	public void reset() {
		isLeapYear = false;
		yearsPassed = 0;
		weeksPassed = 0;
		daysPassed = 0;
		hoursPassed = 0;
		secondsPassed = 0;
		
		deltaDays = 0;
		deltaSeconds = 0;
		
		year = 2020;
		day = 1;
		month = 4;
	}
	
	public void update() {
		//checks if the current year is a leap year having the modulo of the year be equal to zero
		if(!pause) {
			if(year%4 == 0)
				isLeapYear = true;
			else
				isLeapYear = false;
			
			//the time passage since t0
			secondsPassed += Constants.getStepSize();
			minutesPassed = (int) Math.floor(secondsPassed/60);
			hoursPassed = (int) Math.floor(secondsPassed/3600);
			daysPassed = (int) Math.floor(hoursPassed/24);
			weeksPassed = (int) Math.floor(daysPassed/7);
			
			//counts the passage of time for each day
			deltaSeconds += Constants.getStepSize();
			deltaDays = (int) Math.floor(deltaSeconds/86400);
			
			//resets the seconds passed after a day has passed
			if(deltaDays>0) {
				day += deltaDays;
				deltaSeconds = 0;
			}
			
			
			if(isLeapYear) {
				yearsPassed = (int) Math.floor(daysPassed/366);
				
				if(day > leapYear[month-1]) {
					day = 1;
					if(month<12) {
						month++;
					} else {
						month = 1;
						year++;
					}	
				}	
			} else {
				yearsPassed = (int) Math.floor(daysPassed/365);	
				
				if(day > normalYear[month-1]) {
					day = 1;
					if(month<12) {
						month++;
					} else {
						month = 1;
						year++;
					}
				}
			}
		}
		
	}
	
	
	
	
	public void addSeconds(double seconds) {
		this.secondsPassed += seconds;
		update();
	}
	
	public void addMulSeconds(double scalar, double seconds) {
		this.secondsPassed += (scalar * seconds);
		update();
	}
	
	public int getYearsPassed() {
		return yearsPassed;
	}
	
	public int getDaysPassed() {
		return daysPassed;
	}
	
	public int getHoursPassed() {
		return hoursPassed;
	}
	
	public double getSecondsPassed() {
		return secondsPassed;
	}
	
	public int getYear() {
		return year;
	}
	
	public int getMonth() {
		return month;
	}
	
	public int getday() {
		return day;
	}
	
	public String getDate() {
		return "Stardate: "+day+"/"+month+"/"+year;
	}
	
	public String getPassedTime() {
		return "Time passed: "+hoursPassed+" hour(s), "+daysPassed+" day(s), "+weeksPassed+" week(s), "+yearsPassed+" year(s)";
	}
	
	public String getPassedTimeExact() {
		return "Time passed: "+Math.floor(secondsPassed)+" seconds,  "+minutesPassed+" minutes, "+hoursPassed+" hour(s), "+daysPassed+" day(s), "+weeksPassed+" week(s), "+yearsPassed+" year(s)";
	}
	
	public void setDate(int day, int month, int year) {
		this.year = year;
		this.month = month;
		this.day = day;
		
	}
	
	public void isPaused(boolean pause) {
		this.pause = pause;
	}
}
