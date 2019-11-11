package frc.robot;
import edu.wpi.first.wpilibj.*;
import frc.robot.DriveTrain.*;
//import frc.team3006.Mechanisms.*;

public class Controller {
    private Joystick joystick = new Joystick(0);

    final private int buttonA = 1;
    final private int buttonY = 4;
    final private int buttonX = 3;
    final private int buttonB = 2;
    final private int buttonLeftBumper = 5;
    final private int buttonRightBumper = 6; 
    final private int leftXAxis = 0;
    final private int leftYAxis = 1; 
    final private int rightXAxis = 4;
    final private int rightYAxis = 5; 
    final private int leftTrigger = 2; 
    final private int rightTrigger = 3;
    final private double triggerDeadZone = 0.05;

    private DriveTrain driveTrain;
	private int driveTrainLeft1 = 1;
	private int driveTrainLeft2 = 2;
	private int driveTrainRight1 = 3;
	private int driveTrainRight2 = 4;


	private Shooter shooter;
	private double maxPower;
    

    public Controller(int port) {

		joystick = new Joystick(port);
		
		
		
	/*	if(!Controller.isDriver && isDriver) {
			Controller.isDriver = isDriver;
		} else if (Controller.isDriver) {
			isDriver = false;
			System.out.println("Driver controller already created. This controller is connected to port: " + 
				port + " and will not have driver control");
		}*/

		
	}
	
	public void tankDrive() {
		if(driveTrain != null) {
	
			double leftMotorMove = joystick.getRawAxis(leftYAxis);
			double rightMotorMove = joystick.getRawAxis(rightYAxis);
			if(joystick.getRawAxis(rightTrigger) > triggerDeadZone) {
				driveTrain.boost(leftMotorMove, rightMotorMove);
			} else {
				/*if(leftMotorMove < 0 && rightMotorMove > 0) {
					driveTrain.drive(leftMotorMove, rightMotorMove);
				} else if (leftMotorMove > 0 && rightMotorMove < 0) {
					driveTrain.reverse(leftMotorMove, rightMotorMove);
				}*/
				driveTrain.drive(leftMotorMove, rightMotorMove);
			}
		} else {
			System.out.println("Wrong controller. This is not the driver controller");
		}
	}

	public void arcadeDrive() {
		if(driveTrain != null) {
			double forwardVal = joystick.getRawAxis(rightTrigger);
			double backwardsVal = joystick.getRawAxis(leftTrigger);
			System.out.println("Fordward " + forwardVal);
			System.out.println("Backward " + backwardsVal);

			double turn = joystick.getRawAxis(leftXAxis);
			System.out.println(turn);

			double forwardLeft = forwardVal;
			double forwardRight = forwardVal;
			double backwardsLeft = backwardsVal;
			double backwardsRight = backwardsVal;

			
			if(turn > 0) {
				forwardRight *= (1-turn);
				backwardsLeft *= (1-turn);	
			} else if (turn < 0) {
				forwardLeft *= (1-Math.abs(turn));
				backwardsRight *= (1-Math.abs(turn));
			}

			if (forwardVal > triggerDeadZone) {
				backwardsVal = 0;
				driveTrain.drive(forwardLeft, forwardRight);
			} else if(backwardsVal > triggerDeadZone) {
				forwardVal = 0;
				driveTrain.reverse(backwardsLeft, backwardsRight);
			}
		}
	

		
/*
			if(backwardsVal == 0 && forwardVal == 0) {
				if(turn > 0) {
					forwardRight *= (1-turn)/2;
					backwardsLeft *= (1-turn);	
				} else if (turn < 0) {
					forwardLeft *= (1-Math.abs(turn));
					backwardsRight *= (1-Math.abs(turn));
				}
			}*/
		}
	
	public void arcadeDrive1()
	{
		if(driveTrain != null)
		{
			double allMotorMove = joystick.getRawAxis(leftYAxis);
			double allMotorTurn = joystick.getRawAxis(rightXAxis);
			if(Math.abs(joystick.getRawAxis(leftYAxis)) > 0.3)
			{
				driveTrain.drive(allMotorMove, -allMotorMove);
				
			}
		
			/*else if(joystick.getRawAxis(rightXAxis) > 0.05)
			{
				driveTrain.turn(allMotorTurn, -allMotorTurn);
			}*/
		}
	}
	

	public void setMaxDriveSpeed(double val) {
		driveTrain.setMaxSpeed(val);
	}

	public void setDriveTrain(DriveTrain driveTrain)
	{
		this.driveTrain = driveTrain;
	}

	public void Fire(double maxPower)
	{
		if(joystick.getRawButton(buttonA))
		{
			this.shooter.Fire();
		}
		else
		{
			this.shooter.stop();
		}
	}
	

	public void setShooter(Shooter shooter)
	{
		this.shooter = shooter;
	}
	/*
	public void enableSolenoid(Solenoid solenoid) {
		if(joystick.getRawButton(buttonX)) {
			solenoid.set(true);
		} else if (joystick.getRawButton(buttonY)) {
			solenoid.set(false);
		}
	}*/
}
    


