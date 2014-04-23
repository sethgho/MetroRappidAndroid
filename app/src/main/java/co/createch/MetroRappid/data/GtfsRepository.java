package co.createch.MetroRappid.data;

/**
 * Created by Seth Gholson on 4/22/14.
 */
public class GtfsRepository {

    private GtfsDatabase mDatabase;

    public GtfsRepository(GtfsDatabase database)
    {
        this.mDatabase = database;
    }

    public Object getSomething()
    {
        return mDatabase.query("SELECT * FROM table.table");
    }

}
