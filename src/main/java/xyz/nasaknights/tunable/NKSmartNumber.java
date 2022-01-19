package xyz.nasaknights.tunable;

import java.util.function.Consumer;

public class NKSmartNumber extends NKTunableNumber {

    private Consumer<Double> consumer;

    public NKSmartNumber(String name, Consumer<Double> valueConsumer, double defaultValue) {
        super(name, defaultValue); // need both a name and default return value
        // below how we are going to use new values once they come in
        this.consumer = valueConsumer;
    }

    public NKSmartNumber(String name, Consumer<Double> valueConsumer) {
        // run through the default constructor,
        // using kDefaultValue from base class
        this(name, valueConsumer, kDefaultValue); 
    }

    @Override
    protected final void updateValue(double newValue) {
        if (m_value != newValue) {
            m_value = newValue;
            if (this.consumer != null) this.consumer.accept(newValue);
        }
    }
}