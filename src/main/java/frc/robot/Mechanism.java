package frc.robot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import java.util.List;
import java.util.ArrayList;

public class Mechanism
{
    private static List<Integer> used_can = new ArrayList<Integer>();
    private List<VictorSPX> mech_victor = new ArrayList<VictorSPX>();

    public Mechanism(int... canID)
    {
        for(int i : canID)
        {
            if(used_can.contains(i))
            {
                System.out.println("CAN:" + i + " is being used. Please choose a different can.");
            }
            else
            {
                used_can.add(i);
                mech_victor.add(new VictorSPX(i));
            }
        }
    }
    public void setVictorAll(double motorVal)
    {
        for(VictorSPX i : mech_victor)
        {
            i.set(ControlMode.PercentOutput, motorVal);
        }
    }
    public void setVictorSpecific(int canID, double motorVal)
    {
        mech_victor.get(canID).set(ControlMode.PercentOutput, motorVal);
    }
    
    public void stop()
    {
        for(VictorSPX i : mech_victor)
        {
            i.set(ControlMode.PercentOutput, 0);
        }
    }
}