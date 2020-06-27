import java.util.Random;

public enum Dir {
    U,D,L,R;
    private static Random r = new Random();

    public static Dir randomDir() {
        return values()[r.nextInt(values().length)];
    }
}
