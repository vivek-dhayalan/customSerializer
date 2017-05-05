package implementation;

import definition.ConfigField;
import definition.Multiplicity;


public class CustomAnnotation {

    //String attribute with Annotation
    @ConfigField(displayName = "AnnotationForString", description = "config field anotation Test", required = true,
            multiplicity = Multiplicity.ZERO_TO_MANY, expressionEnabled = false)
    private String testField = "something";

    //primitive attribute with Annotation
    @ConfigField(displayName = "Long-field-test", description = "config field long", required = true,
            multiplicity = Multiplicity.ONE_TO_MANY,expressionEnabled = true)
    private long longField;

    //String attribute without Annotation
    private String stringNoAnnotation;

    //no Annotation for primitive attribute
    private long longFieldNoAnnotation;

    //Primitive Wrapper with Annotation
    @ConfigField(displayName = "AnnotationForPrimitiveWrapper", description = "config field with primitive Wrapper", required =
            true,
            multiplicity = Multiplicity.ONE_TO_MANY,expressionEnabled = true)
    private Integer intField;

    //PrimitveWrapper without Annotation
    private Integer primitiveWrapperNoAnnotation;

    //POJO with Annotation
    @ConfigField(displayName = "pojo-annotation", description = "config field POJO", required = true,
            multiplicity = Multiplicity.SINGLE,expressionEnabled = false)
    private CustomChild customChild;

    //POJO with out Annotation
    private CustomChild customChildNoAnnotation;

}
