package xyz.nasaknights.tunable;

import java.util.function.Consumer;

public class NKSmartString extends NKTunableString {

    private Consumer<String> consumer;

    public NKSmartString(String name, Consumer<String> valueConsumer, String defaultValue) {
        super(name, defaultValue);
        this.consumer = valueConsumer;
    }
    public NKSmartString(String name, Consumer<String> valueConsumer) {
        this(name, valueConsumer, kDefaultValue);
    }

    @Override
    protected void updateValue(String newValue) {
        if (!m_value.equals(newValue)) {
            m_value = newValue;
            if (this.consumer != null) this.consumer.accept(newValue);
        }
    }
    
}
