package xyz.nasaknights.util.tunable;

import java.util.function.Consumer;

public class NKSmartBoolean extends NKTunableBoolean {

    private Consumer<Boolean> consumer;

    public NKSmartBoolean(String name, Consumer<Boolean> valueConsumer, boolean defaultValue) {
        super(name, defaultValue);
        this.consumer = valueConsumer;
    }

    public NKSmartBoolean(String name, Consumer<Boolean> valueConsumer) {
        this(name, valueConsumer, kDefaultValue);
    }

    @Override
    protected void updateValue(boolean newValue) {
        if (m_value != newValue) {
            m_value = newValue;
            if (this.consumer != null) consumer.accept(newValue);
        }
    }
    
}
