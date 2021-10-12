
class Reverse {

    public static void main(String argv[]) {
        String input = argv[0];
        String output = reverseString(input);
        System.out.println(output);
    }

    private static String reverseString(String input) {
        StringBuilder sb = new StringBuilder(input);
        sb.reverse();
        return sb.toString();
    }

}