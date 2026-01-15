package xyz.glarmer.configuration;

public class Option<T> {
    T value;
    T defaultValue;
    String description;
    String name;

    public Option(String name, String description, T defaultValue) {
        this.name = name;
        this.defaultValue = defaultValue;
        this.description = description;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(T defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
