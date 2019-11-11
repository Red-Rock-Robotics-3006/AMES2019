/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Main;
import frc.robot.Controller;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 * 
 * 
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  double maxSpeed;
  double maxPower;

  TalonSRX frontLeft;
  TalonSRX frontRight;
  VictorSPX backLeft;
  VictorSPX backRight;

 //Solenoid solenoid = new Solenoid(0);

  List<BaseMotorController> rightMotorControllers = new ArrayList<BaseMotorController>();
  List<BaseMotorController> leftMotorControllers = new ArrayList<BaseMotorController>();
  
  Controller driver;
  DriveTrain driveTrain;
  Shooter shooter;

  @Override
  public void robotInit() {
    /*
    Shooter Right: Victor ID: 4
    Shooter Left: Victor ID: 5

    PDP (Power Distribution Panel): ID: 20

    Front Left Motor: Talon ID: 0
    Front Right Motor: Talon ID: 2
    Back Left Motor: Victor ID: 1
    Back Right Motor: Victor ID: 3
    */
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    driver = new Controller(0);
    maxSpeed = 0.4;
    maxPower = 1;
    frontLeft = new TalonSRX(0);
    frontRight = new TalonSRX(2);
    backLeft = new VictorSPX(1);
    backRight = new VictorSPX(3);
    leftMotorControllers.add(frontLeft);
    rightMotorControllers.add(frontRight);
    leftMotorControllers.add(backLeft);
    rightMotorControllers.add(backRight);
   
    driveTrain = new DriveTrain(leftMotorControllers, rightMotorControllers, maxSpeed);
    driver.setDriveTrain(driveTrain);
    driver.setMaxDriveSpeed(maxSpeed);

    shooter = new Shooter(4, 5, maxPower);
    driver.setShooter(shooter);
  
    //Singleton should not allow manual object initalization. Must call init function
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {

    driver.tankDrive();
    driver.Fire(maxPower);
  //  solenoid.set(true);
   // driver.enableSolenoid(solenoid);

  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
