package frc.robot;
//import edu.wpi.first.wpilibj.Victor;
import java.util.ArrayList;
import java.util.List;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class DriveTrain
{
    //creates static variables
   
    private double maxSpeed;
    private List<BaseMotorController> rightMotorControllers;
    private List<BaseMotorController> leftMotorControllers;

    public DriveTrain(List<BaseMotorController> leftMotorControllers, List<BaseMotorController> rightMotorControllers, double maxSpeed)
    {
        //constructor for drivetrain
        this.leftMotorControllers = leftMotorControllers;
        this.rightMotorControllers = rightMotorControllers;

        this.maxSpeed = maxSpeed; //max speed for any motor at any time
    }
   
    public void drive(double leftMotorMove, double rightMotorMove)
    {
        for(BaseMotorController l: this.leftMotorControllers)
        {
            l.set(ControlMode.PercentOutput, leftMotorMove * maxSpeed);
        }

        for(BaseMotorController r: this.rightMotorControllers)
        {
            r.set(ControlMode.PercentOutput, rightMotorMove * maxSpeed);
        }
       
    }

    public void turn(double leftTurn, double rightTurn)
    {
        for(BaseMotorController l: this.leftMotorControllers)
        {
            l.set(ControlMode.PercentOutput, leftTurn  * maxSpeed);
        }
        for(BaseMotorController r: this.rightMotorControllers)
        {
            r.set(ControlMode.PercentOutput, rightTurn * maxSpeed);
        }
    }

    public void boost(double leftMotorMove, double rightMotorMove)
    {
        if(Math.signum(leftMotorMove) * -1 == Math.signum(rightMotorMove)) // round two numbers to sigs, if motor powers are the same
        {
            leftMotorMove *= maxSpeed; //multiplies the motor power times the power of the maximum speed
            rightMotorMove *= maxSpeed;
        }

        for(BaseMotorController l: this.leftMotorControllers)
        {
            l.set(ControlMode.PercentOutput, leftMotorMove * -1);
        }

        for(BaseMotorController r: this.rightMotorControllers)
        {
            r.set(ControlMode.PercentOutput, rightMotorMove);
        }       
    }

    public void reverse(double leftMotorMove, double rightMotorMove)
    {
        for(BaseMotorController l: this.leftMotorControllers)
        {
            l.set(ControlMode.PercentOutput, leftMotorMove * maxSpeed);
        }

        for(BaseMotorController r: this.rightMotorControllers)
        {
            r.set(ControlMode.PercentOutput, rightMotorMove * -1 * maxSpeed);
        }
    }

    public void setMaxSpeed(double maxSpeed)
    {
        this.maxSpeed = maxSpeed; //sets the static variable to the parameter
    }
}
