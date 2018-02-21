package programgames.multimodplus.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import programgames.multimodplus.Main;
import programgames.multimodplus.tileentity.TileEntityCounter;

public class CounterBlock extends Block {

	public IIcon[] icons = new IIcon[6];

	public CounterBlock(String unlocalizedName, Material material) 
	{ 
		super(material);
		this.setBlockName(unlocalizedName);
		this.setBlockTextureName(Main.MODID + ":" + unlocalizedName);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(2.0F);
		this.setResistance(6.0F);
		this.setStepSound(soundTypeGravel);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister reg) {

		for (int i = 0; i < 6; i ++) {
			this.icons[i] = reg.registerIcon(this.textureName + i);
		}
	}

	@Override
	public IIcon getIcon(int side, int metadata) {

		if(metadata == 1)
		{
			if(side == 4)
				return this.icons[3];
			if(side == 3 )
				return this.icons[5];
			if(side == 5)
				return this.icons[2];
			if(side == 2)
				return this.icons[4];
			if(side == 1 || side == 0)
				return this.icons[side];
		}
		if(metadata == 2)
		{
			if(side == 4)
				return this.icons[5];
			if(side == 3 )
				return this.icons[2];
			if(side == 5)
				return this.icons[4];
			if(side == 2)
				return this.icons[3];
			if(side == 1 || side == 0)
				return this.icons[side];
		}
		if(metadata == 3)
		{
			if(side == 4)
				return this.icons[5];
			if(side == 3 )
				return this.icons[4];
			if(side == 5)
				return this.icons[3];
			if(side == 2)
				return this.icons[5];
			if(side == 1 || side == 0)
				return this.icons[side];
		}
		return this.icons[side];

	}
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack)
	{
		int direction = MathHelper.floor_double((double)(living.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
		world.setBlockMetadataWithNotify(x, y, z, direction, 2);
	}
	public boolean rotateBlock(World world, int x, int y, int z, ForgeDirection axis)
	{
		if((axis == ForgeDirection.UP || axis == ForgeDirection.DOWN) && !world.isRemote)
		{
			int direction = world.getBlockMetadata(x, y, z) + 1;
			if(direction > 3)
			{
				direction = 0;
			}
			world.setBlockMetadataWithNotify(x, y, z, direction, 3);
			return true;
		}
		return false;
	}
	 public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	   {
	       /* 
	        * petit explication sur la condition world.isRemote
	        * world.isRemote = monde client, c'est celui qui va gérer le rendu
	        * !world.isRemote = monde serveur, il va gérer le reste, notamment la sauvegarde, les variables d'une entité de bloc sont donc à manipuler côté serveur seulement, d'où la condition juste en dessous
	        */
	       if(!world.isRemote)
	       {
	           TileEntity tile = world.getTileEntity(x, y, z); // on obtient l'instance du TileEntity
	           if(tile instanceof TileEntityCounter) // si le TileEntity est bien le nôtre (cette condition est importante pour éviter tout risque de corruption de monde, car il peut arriver qu'une mauvaise entité de bloc soit sur les coordonnées de votre bloc)
	           {
	               TileEntityCounter tileTuto = (TileEntityCounter)tile; // on cast pour avoir accès au méthode qui se trouve dans TileEntityTutoriel
	               if(side == 0) // si le côté est 0, donc en dessous, on appelle la fonction decrease pour diminuer la valeur
	               {
	                   tileTuto.decrease();
	               }
	               else if(side == 1) // si le côté est 1, donc en dessous, on appelle la fonction increase pour augmenter la valeur
	               {
	                   tileTuto.increase();
	               }
	               player.addChatMessage(new ChatComponentTranslation("tile.tutoriel2.number", tileTuto.getNumber())); // et on affiche par un message tchat la valeur. ChatComponentTranslation permet de faire un String.format, dans mon fichier de lang je vais mettre %d qui sera remplacé par la valeur de tileTuto.getNumber(). (voir plus bas)
	               return true;
	           }
	       }
	       return false;
	   }
	


	public ForgeDirection[] getValidRotations(World world, int x, int y, int z)
	{
		return new ForgeDirection[] {ForgeDirection.UP, ForgeDirection.DOWN};
	}
	   @Override
	   public TileEntity createTileEntity(World world, int metadata)
	   {
	       return new TileEntityCounter();
	   }

	   @Override
	   public boolean hasTileEntity(int metadata)
	   {
	       return true;
	   }

	  
}
