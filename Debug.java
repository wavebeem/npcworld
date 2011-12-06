public class Debug {
    private static final boolean DEBUG = true;

    public static void echo(Object... args) {
        if (! DEBUG)
            return;

        if (args.length > 0) {
            for (int i=0; i < (args.length - 1); i++) {
                System.out.print(args[i] + " ");
            }

            System.out.print(args[args.length - 1]);
        }

        System.out.println();
    }
}
