package programgames.multimodplus.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class Key extends Item{

	public Key(String unlocalizedName) {
		super();
		this.setHasSubtypes(true);//not stack item with same metadata
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(CreativeTabs.tabMaterials);
	}

	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		if(!world.isRemote)
		{
			player.addChatMessage(new ChatComponentText("serveur : side " + side));
			player.addChatMessage(new ChatComponentText("serveur : metadata " + world.getBlockMetadata(x, y, z)));
		}
		if(world.getBlock(x, y, z).rotateBlock(world, x, y, z, ForgeDirection.getOrientation(side)))
		{
			return true;
		}
		return false;
	}

}
