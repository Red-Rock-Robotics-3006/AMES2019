package frc.robot;
public class Shooter extends Mechanism
{
    private double maxPower;
    private int canID1, canID2;

    public Shooter(int canID1, int canID2, double maxPower)
    {
        super(canID1, canID2);
        this.canID1 = canID1;
        this.canID2 = canID2;
        this.maxPower = maxPower;
    }
    public void Fire()
    {
        super.setVictorSpecific(canID1, maxPower);
        super.setVictorSpecific(canID2, maxPower * -1);
    }
}