package example;

import arc.*;
import arc.util.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.gen.*;
import mindustry.mod.*;


public class ExamplePlugin extends Plugin{

    //called when game initializes

    //register commands that run on the server
    @Override
    public void registerServerCommands(CommandHandler handler){
        handler.register("reactors", "List all thorium reactors in the map.", args -> {
            for(int x = 0; x < Vars.world.width(); x++){
                for(int y = 0; y < Vars.world.height(); y++){
                    //loop through and log all found reactors
                    //make sure to only log reactor centers
                    if(Vars.world.tile(x, y).block() == Blocks.thoriumReactor && Vars.world.tile(x, y).isCenter()){
                        Log.info("Reactor at @, @", x, y);
                    }
                }
            }
        });
    }

    //register commands that player can invoke in-game
    @Override
    public void registerClientCommands(CommandHandler handler){

        handler.<Player>register("discord", "Get discord invite link.", (args, player) -> {
            player.sendMessage("[#FFD700]Discord Invite: https://discord.gg/tXcRKQhksk");
        });


        //register a message command which can be used to send other players messages
        handler.<Player>register("msg", "<player> <text...>", "Message another player.", (args, player) -> {
            //find player by name
            Player other = Groups.player.find(p -> p.name.equalsIgnoreCase(args[0]));

            //give error message with scarlet-colored text if player isn't found
            if(other == null){
                player.sendMessage("[scarlet]No player by that name found!");
                return;
            }

            //send the other player a message, using [lightgray] for gray text color and [] to reset color
            other.sendMessage("[lightgray](message) " + player.name + ":[] " + args[1]);
        });
    }

}
