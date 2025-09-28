public class InputParameter {
    InputParamType type;
    Object value;

    public InputParameter(InputParamType type, Object value) {
        this.type = type; // type of the parameter
        this.value = value; // value of the parameter
    }

    @Override
    public String toString() {
        return String.format("InputParameter{type=%s, value=%s}", type, value);
    }
}