package definition;

public enum Multiplicity {
    SINGLE("1"),
    ZERO_TO_ONE("0..1"),
    ZERO_TO_MANY("0..*"),
    ONE_TO_MANY("1..*");

    private String value;

    public String getValue() {
        return value;
    }

    private Multiplicity(String value) {
        this.value = value;
    }
}