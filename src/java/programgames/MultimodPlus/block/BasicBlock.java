package programgames.MultimodPlus.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import programgames.MultimodPlus.Main;
import programgames.MultimodPlus.tileentity.TileEntityTutoriel;
public class BasicBlock extends Block{

	protected BasicBlock(String unlocalizedName, Material material) {
		super(material);
		// TODO Auto-generated constructor stub
		this.setBlockName(unlocalizedName);
		this.setBlockTextureName(Main.MODID + ":" + unlocalizedName);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(2.0F);
		this.setResistance(6.0F);
		this.setLightLevel(1.0F);
		this.setHarvestLevel("pickaxe", 3);
		this.setStepSound(soundTypeMetal);
		this.isBlockContainer = true;
	}

	@Override
	public void registerBlockIcons(IIconRegister reg) { }

	@Override
	public IIcon getIcon(int side, int meta) {
		if (meta > 5)
			meta = 0;

		return BlockMaker.multitexture.getIcon(meta, 0);
	}
	@Override
	public int damageDropped(int meta) {
		return meta;
	}
	@SuppressWarnings("unchecked")
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, @SuppressWarnings("rawtypes") List list) {
		for (int i = 0; i < 6; i ++) {
			list.add(new ItemStack(item, 1, i));
		}
	}
	
 
	
	   @Override
	   public TileEntity createTileEntity(World world, int metadata)
	   {
	     
	       return new TileEntityTutoriel();
	   }

	   @Override
	   public boolean hasTileEntity(int metadata)
	   {
	       if(metadata >= 0 && metadata <= 2)
	           return true;
	       return false;
	   }
	   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	   {
	       /* 
	        * petit explication sur la condition world.isRemote
	        * world.isRemote = monde client, c'est celui qui va g�rer le rendu
	        * !world.isRemote = monde serveur, il va g�rer le reste, notamment la sauvegarde, les variables d'une entit� de bloc sont donc � manipuler c�t� serveur seulement, d'o� la condition juste en dessous
	        */
	       if(!world.isRemote)
	       {
	           TileEntity tile = world.getTileEntity(x, y, z); // on obtient l'instance du TileEntity
	           if(tile instanceof TileEntityTutoriel) // si le TileEntity est bien le n�tre (cette condition est importante pour �viter tout risque de corruption de monde, car il peut arriver qu'une mauvaise entit� de bloc soit sur les coordonn�es de votre bloc)
	           {
	               TileEntityTutoriel tileTuto = (TileEntityTutoriel)tile; // on cast pour avoir acc�s au m�thode qui se trouve dans TileEntityTutoriel
	               if(side == 0 || side == 1 || side == 2) // si le c�t� est 0, donc en dessous, on appelle la fonction decrease pour diminuer la valeur
	               {
	                   tileTuto.decrease();
	               }
	               else if(side == 3 || side == 4 || side == 5) // si le c�t� est 1, donc en dessous, on appelle la fonction increase pour augmenter la valeur
	               {
	                   tileTuto.increase();
	               }
	               player.addChatMessage(new ChatComponentTranslation("tile.tutoriel2.number", tileTuto.getNumber())); // et on affiche par un message tchat la valeur. ChatComponentTranslation permet de faire un String.format, dans mon fichier de lang je vais mettre %d qui sera remplac� par la valeur de tileTuto.getNumber(). (voir plus bas)
	               return true;
	           }
	       }
	       return false;
	   }
}
