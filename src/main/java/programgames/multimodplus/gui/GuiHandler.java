package programgames.multimodplus.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import programgames.multimodplus.container.ContainerCupboard;
import programgames.multimodplus.tileentity.TileEntityChest;

// TODO: Auto-generated Javadoc
/**
 * The Class GuiHandler is the handler of all gui of this mod.
 */
public class GuiHandler implements IGuiHandler {

  /* (non-Javadoc)
   * @see cpw.mods.fml.common.network.IGuiHandler
   * #getServerGuiElement(int, net.minecraft.entity.player.EntityPlayer,
   * net.minecraft.world.World, int, int, int)
   */
  @Override
  public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
    TileEntity tile = world.getTileEntity(x, y, z);
    if (tile instanceof TileEntityChest) {
      return new ContainerCupboard((TileEntityChest)tile, player.inventory);
    }
    return null;
  }



  /* (non-Javadoc)
   * @see cpw.mods.fml.common.network.IGuiHandler#
   * getClientGuiElement(int, net.minecraft.entity.player.EntityPlayer,
   * net.minecraft.world.World, int, int, int)
   */
  @Override
  public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
    TileEntity tile = world.getTileEntity(x, y, z);
    if (tile instanceof TileEntityChest) {
      return new GuiCupboard((TileEntityChest)tile, player.inventory);
    }
    return null;

  }

}
