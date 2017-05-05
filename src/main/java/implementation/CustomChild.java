package implementation;

import definition.ConfigField;
import definition.Multiplicity;

public class CustomChild {

    private Integer childName;

    @ConfigField(displayName = "Custom child Annotation int", description = "Custom Child attribute with Annotation", required = false,
            multiplicity = Multiplicity.ZERO_TO_MANY,expressionEnabled = true)
    private int childInt;
}
