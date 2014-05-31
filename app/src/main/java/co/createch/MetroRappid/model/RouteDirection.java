package co.createch.MetroRappid.model;

/**
 * Created by Seth Gholson on 5/2/14.
 */
public enum RouteDirection {
    South(0),
    North(1);

    private int key;
    private RouteDirection(int key) {
        this.key = key;
    }

    public static RouteDirection findByKey(int key) {
        for(RouteDirection d : values()) {
            if(d.key == key) {
                return d;
            }
        }
        return null;
    }

    public int getKey()
    {
        return key;
    }
}
