public class GenericUtils {
    public static <T> T first(T[] values) {
        return values[0];
    }

    public static <N extends Number> double average(N[] values) {
        double sum = 0;
        for (N value : values) {
            sum += value.doubleValue();
        }
        return sum / values.length;
    }

    public static void main(String[] args) {
        Box<String> box = new Box<>("Java");
        Pair<String, Integer> pair = new Pair<>("Age", 19);

        Integer[] integers = {10, 20, 30};
        Double[] doubles = {1.5, 2.5, 3.5};
        Long[] longs = {100L, 200L, 300L};

        System.out.println(box.get());
        System.out.println(pair.getFirst() + " " + pair.getSecond());
        System.out.println(first(integers));
        System.out.println(average(integers));
        System.out.println(average(doubles));
        System.out.println(average(longs));
    }
}
