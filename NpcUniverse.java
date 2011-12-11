import java.util.ArrayList;

/*
 * @author tgriswol, afisher
 */
public class NpcUniverse implements Universe {
    private NpcWorld[][] worlds;

    public NpcUniverse(){
        worlds = new NpcWorld[Const.UNIVERSE_ROWS][Const.UNIVERSE_COLS];
        for (int row = 0; row < Const.UNIVERSE_ROWS; row++) {
            for (int col = 0; col < Const.UNIVERSE_COLS; col++) {
                worlds[row][col] = new NpcWorld();
            }
        }
    }

    public World getWorld(int row, int col){
        return worlds[row][col];
    }

    public void step(){
        for(int row = 0; row < Const.UNIVERSE_ROWS; row++){
            for(int col = 0; col < Const.UNIVERSE_COLS; col++){
                worlds[row][col].step();
            }
        }
        if(Settings.migrationEnabled)
            migrateWorlds();
    }

    private void migrateWorlds(){
        for (int row = 0; row < Const.UNIVERSE_ROWS; row++) {
            for (int col = 0; col < Const.UNIVERSE_COLS; col++) {
                NpcWorld world = worlds[row][col];
                ArrayList<NpcIndividual> migrationPool = world.getMigrationPool();
                ArrayList<NpcWorld> adjacentWorlds = getAdjacentWorlds(row, col);

                for (NpcIndividual ind : migrationPool) {
                    int index = Util.random.nextInt(adjacentWorlds.size());
                    world.removeIndividual(ind);
                    adjacentWorlds.get(index).addIndividual(ind);
                }
            }
        }
    }

    private ArrayList<NpcWorld> getAdjacentWorlds(int row, int col){
        ArrayList<NpcWorld> adjacentWorlds = new ArrayList<NpcWorld>();
        if(row >= 0 && row < Const.UNIVERSE_ROWS && col >= 0 && col < Const.UNIVERSE_COLS){
            if(row < Const.UNIVERSE_ROWS-1) adjacentWorlds.add(worlds[row+1][col]); 
            if(row > 0)      adjacentWorlds.add(worlds[row-1][col]); 
            if(col < Const.UNIVERSE_COLS-1) adjacentWorlds.add(worlds[row][col+1]);
            if(col > 0)      adjacentWorlds.add(worlds[row][col-1]);
        }
        return adjacentWorlds;
    }
}
