import java.util.ArrayList;

/*
 * @author tgriswol, afisher
 */
public class NpcUniverse implements Universe {
    private NpcWorld[][] worlds;

    public NpcUniverse(){
        worlds = new NpcWorld[Const.UNIVERSE_ROWS][Const.UNIVERSE_COLS];
        for (int row = 0; row < worlds.length; row++) {
            for (int col = 0; col < worlds[0].length; col++) {
                worlds[row][col] = new NpcWorld();
            }
        }
    }

    public World getWorld(int row, int col){
        return worlds[row][col];
    }

    public void step(){

    }

    private void migrateWorlds(){
        for (int row = 0; row < worlds.length; row++) {
            for (int col = 0; col < worlds[0].length; col++) {
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

    private ArrayList<World> getAdjacentWorlds(int row, int col){
        return null;
    }
}
