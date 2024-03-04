package ku.cs.exercises;

public class ColoredMap {
    static final int NONE = 0;
    static final int RED = 1;
    static final int YELLOW = 2;
    static final int GREEN = 3;
    static final int BLUE = 4;

    String getColorString(int color) {
        if (color == RED) return "RED";
        if (color == YELLOW) return "YELLOW";
        if (color == GREEN) return "GREEN";
        if (color == BLUE) return "BLUE";
        return null;
    }

    int map[][];

    void createMap() {
        map = new int[7][];
        map[0] = new int[] { 1, 4, 2, 5 };
        map[1] = new int[] { 0, 4, 6, 5 };
        map[2] = new int[] { 0, 4, 3, 6, 5 };
        map[3] = new int[] { 2, 4, 6 };
        map[4] = new int[] { 0, 1, 6, 3, 2 };
        map[5] = new int[] { 2, 6, 1, 0 };
        map[6] = new int[] { 2, 3, 4, 1, 5 };
    }

    // 7 countries
    int mapColors[] = { NONE, NONE, NONE, NONE, NONE, NONE, NONE };

    boolean explore(int country, int color) {
        if (country >= map.length) return true;
        if (okToColor(country, color)) {

            System.out.printf("explore(%d,%s) ok\n", country, getColorString(color).toLowerCase());

            mapColors[country] = color;
            for (int i = RED; i <= BLUE; i++)
                if (explore(country + 1, i)) return true;
        }
        
        else System.out.printf("explore(%d,%s) no\n", country, getColorString(color).toLowerCase());

        return false;
    }

    boolean okToColor(int country, int color) {
        for (int i = 0; i < map[country].length; i++) {
            int ithAdjCountry = map[country][i];
            if (mapColors[ithAdjCountry] == color)
                return false;
        }
        return true;
    }

    void printMap() {
        for (int i = 0; i < mapColors.length; i++) {
            System.out.print("map[" + i + "] is ");
            System.out.println(getColorString(mapColors[i]).toLowerCase());
        }
    }

    public static void main(String[] args) {
        ColoredMap map = new ColoredMap();
        map.createMap();
        boolean result = map.explore(0, RED);
        System.out.println(result);
        map.printMap();
    }

}
