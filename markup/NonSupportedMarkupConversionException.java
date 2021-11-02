package markup;

public class NonSupportedMarkupConversionException extends RuntimeException {
    public NonSupportedMarkupConversionException(String errorMessage) {
        super(errorMessage);
    }
}
