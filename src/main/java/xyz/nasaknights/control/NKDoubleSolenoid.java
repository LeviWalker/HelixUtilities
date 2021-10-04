package xyz.nasaknights.control;

import edu.wpi.first.wpilibj.DoubleSolenoid;

// TODO Add invertability
public class NKDoubleSolenoid extends DoubleSolenoid {

    public NKDoubleSolenoid(int forwardChannel, int reverseChannel) {
        this(0, forwardChannel, reverseChannel);
    }

    public NKDoubleSolenoid(int moduleNumber, int forwardChannel, int reverseChannel) {
        super(moduleNumber, forwardChannel, reverseChannel);
    }

    @Override
    public void set(Value value) {
        if (value != super.get()) super.set(value);
    }

}