package programgames.multimodplus.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import programgames.multimodplus.container.ContainerCupboard;
import programgames.multimodplus.tileentity.TileEntityChest;

public class GuiHandlerChest implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tile = world.getTileEntity(x, y, z);
        if(tile instanceof TileEntityChest)
        {
            return new ContainerCupboard((TileEntityChest)tile, player.inventory);
        }
        return null;
	}
	


	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tile = world.getTileEntity(x, y, z);
        if(tile instanceof TileEntityChest)
        {
            return new GuiCupboard((TileEntityChest)tile, player.inventory);
        }
        return null;
	
	}

}
