import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

class LargestSeriesProductCalculator {
    private final String inputNumber;

    LargestSeriesProductCalculator(String inputNumber) {
        validateDigits(inputNumber);
        this.inputNumber = inputNumber;
    }

    long calculateLargestProductForSeriesLength(int numberOfDigits) {
        validateLength(numberOfDigits);

        return IntStream.rangeClosed(0, inputNumber.length() - numberOfDigits)
                .mapToObj(getSubstring(numberOfDigits))
                .map(calculateProduct())
                .max(Long::compareTo).orElse(0L);
    }

    private IntFunction<String> getSubstring(int numberOfDigits) {
        return i -> inputNumber.substring(i, i + numberOfDigits);
    }

    private static Function<String, Long> calculateProduct() {
        return s -> s.chars()
                .mapToLong(c -> Character.digit(c, 10))
                .reduce(1, (acc, element) -> acc * element);
    }

    private static void validateDigits(String inputNumber) {
        if (inputNumber.chars().anyMatch(c -> !Character.isDigit(c)))
            throw new IllegalArgumentException("String to search may only contain digits.");
    }

    private void validateLength(int numberOfDigits) {
        if (numberOfDigits > inputNumber.length())
            throw new IllegalArgumentException("Series length must be less than or equal to the length of the string to search.");
        if (numberOfDigits < 0) throw new IllegalArgumentException("Series length must be non-negative.");
    }
}
