import java.util.ArrayList;

/*
 * @author tgriswol, afisher
 */
public class NpcUniverse implements Universe {
    private NpcWorld[][] worlds;

    public NpcUniverse(){

    }

    public World getWorld(int row, int col){
        return null;
    }

    public void step(){
        for(int row = 0; row < Const.UNIVERSE_ROWS; row++){
            for(int col = 0; col < Const.UNIVERSE_COLS; col++){
                worlds[row][col].step();
            }
        }
    }

    private void migrateWorlds(){
        
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
