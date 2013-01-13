package base;

import battlecode.common.Clock;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

public abstract class BaseRobot {
	
	public RobotController rc;
	public int id;
	
	// Round variables
	int currentRound;
	MapLocation currentLocation;
	
	// Default constructor
	public BaseRobot(RobotController myRC) {
		rc = myRC;
		id = rc.getRobot().getID();
		NavSystem.init(this);
		BroadcastSystem.init(this);
		EncampmentJobSystem.init(this);
	}
	
	// Actions for a specific robot
	abstract public void run();
	
	public void loop() {
		while (true) {
			try {
				updateRoundVariables();
				run();
			} catch (Exception e) {
				// Deal with exception
			}
			rc.yield();
		}
	}
	
	/**
	 * Updates round variables that we shouldn't call over and over again
	 */
	public void updateRoundVariables() {
		currentRound = Clock.getRoundNum();
		currentLocation = rc.getLocation();
	}
}