package programgames.multimodplus.common;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.BlockEvent;
import programgames.multimodplus.block.ChestBlock;

public class EventHandler {


  /**
   * evenement.
   * @param event evenement d'un bloc
   */
  @SubscribeEvent
  public void onBlockDestroyed(BlockEvent.BreakEvent event) {
    if (event.block instanceof ChestBlock) {
      event.getPlayer().addChatMessage(new ChatComponentText("You can't remove this block"));
    }
  }
}
