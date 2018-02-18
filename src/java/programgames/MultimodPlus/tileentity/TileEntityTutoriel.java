package programgames.MultimodPlus.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityTutoriel extends TileEntity {

	
	private int number;
	
	 @Override
	   public void readFromNBT(NBTTagCompound compound)
	   {
	       super.readFromNBT(compound);
	       this.number = compound.getInteger("Number"); // pour lire sa valeur depuis  la sauvegarde du monde lorsqu'on charge le chunk qui contient l'entité de bloc
	   }
	   

	   @Override
	   public void writeToNBT(NBTTagCompound compound)
	   {
	       super.writeToNBT(compound);
	       compound.setInteger("Number", this.number); // pour enregistrer sa valeur dans la sauvegarde du monde lorsqu'on décharge le chunk qui contient l'entité de bloc
	   }
	   /**
	   public Packet getDescriptionPacket()
	   {
	   NBTTagCompound nbttagcompound = new NBTTagCompound();
	   this.writeToNBT(nbttagcompound);
	   return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 0, nbttagcompound);
	   }

	   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
	   {
	   this.readFromNBT(pkt.func_148857_g());
	   }**/
	   public void increase() // une fonction pour augmenter sa valeur
	   {
	       this.number++;
	   }

	   public void decrease() // une fonction pour diminuer sa valeur
	   {
	       this.number--;
	   }

	   public int getNumber() // et une fonction pour obtenir sa valeur (on appelle ça un getter)
	   {
	       return number;
	   }
	   
}
