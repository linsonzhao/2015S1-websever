package com;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Scheduler {
	private Timer masterTimer;
	private Timer timer;

	public void scheduleControl(){

		masterTimer = new Timer();
		masterTimer.schedule(new MasterTask(), new Date(), 1000*60*60);  //5 minutes cycle
	}
	
	public void cleanUp(){
		timer = new Timer();
		timer.schedule(new ScheduleTask(), new Date());
	}
	
	public void stopTimer(){
		timer.cancel();
		timer.purge();
		masterTimer.cancel();
		masterTimer.purge();
	}
	
	class MasterTask extends TimerTask{

		@Override
		public void run() {
				cleanUp();
		}
	}
}

