package programgames.MultimodPlus.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;

public class TileEntityChest extends TileEntity implements IInventory{

	
	
    private byte direction;
    private String customName;
    private ItemStack[] contents = new ItemStack[27];
    
    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.direction = compound.getByte("Direction");
        if(compound.hasKey("CustomName", Constants.NBT.TAG_STRING))
        {
            this.customName = compound.getString("CustomName");
        }
        NBTTagList nbttaglist = compound.getTagList("Items", Constants.NBT.TAG_COMPOUND); // on obtient la liste de tags nommée Items
        this.contents = new ItemStack[this.getSizeInventory()]; // on réinitialise le tableau
        for(int i = 0; i < nbttaglist.tagCount(); ++i) // i varie de 0 à la taille la liste
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i); // on lit le tag nbt
            int j = nbttagcompound1.getByte("Slot") & 255; // on lit à quel slot se trouve l'item stack

            if(j >= 0 && j < this.contents.length)
            {
                this.contents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1); // on lit l'item stack qui se trouve dans le tag
            }
        }

       

    }

    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
    	
        super.writeToNBT(compound);
        compound.setByte("Direction", this.direction);
        if(this.hasCustomInventoryName())
        {
            compound.setString("CustomName", this.customName);
        }
        NBTTagList nbttaglist = new NBTTagList(); // on créé une nouvelle liste de tags
        for(int i = 0; i < this.contents.length; ++i) // i varie de 0 à la taille de notre tableau
        {
            if(this.contents[ i] != null) // si l'item stack à l'emplacement i du tableau n'est pas null
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound(); // on créé un tag nbt
                nbttagcompound1.setByte("Slot", (byte)i); // on enregistre son emplacement dans le tableau
                this.contents[ i].writeToNBT(nbttagcompound1); // on écrit l'item dans le tag
                nbttaglist.appendTag(nbttagcompound1); // on ajoute le tab à la liste
            }
        }
        compound.setTag("Items", nbttaglist); // on enregistre la liste dans le tag nbt
    
    }
    
    @Override
    public String getInventoryName()
    {
        return this.hasCustomInventoryName() ? this.customName : "tile.cupboard";
    }
    @Override
    public boolean hasCustomInventoryName()
    {
        return this.customName != null && !this.customName.isEmpty();
    }

    public void setCustomName(String customName)
    {
        this.customName = customName;
    }
    public byte getDirection()
    {
        return direction;
    }

    public void setDirection(byte direction)
    {
        this.direction = direction;
        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
    }
    public Packet getDescriptionPacket()
    {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        this.writeToNBT(nbttagcompound);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 0, nbttagcompound);
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
        this.readFromNBT(pkt.func_148857_g());
        this.worldObj.markBlockRangeForRenderUpdate(this.xCoord, this.yCoord, this.zCoord, this.xCoord, this.yCoord, this.zCoord);
    }
    @Override
    public int getSizeInventory()
    {
        return this.contents.length;
    }
    @Override
    public ItemStack getStackInSlot(int slotIndex)
    {
        return this.contents[slotIndex];
    }
    @Override
    public ItemStack decrStackSize(int slotIndex, int amount)
    {
        if(this.contents[slotIndex] != null) // si le contenu dans l'emplacement n'est pas null
        {
            ItemStack itemstack;

            if(this.contents[slotIndex].stackSize <= amount) // si la quantité est inférieur où égale à ce qu'on souhaite retirer
            {
                itemstack = this.contents[slotIndex]; // la variable itemstack prends la valeur du contenu
                this.contents[slotIndex] = null; // on retire ce qui est dans la variable contents
                this.markDirty(); // met à jour le tile entity
                return itemstack; // renvoie itemstack
            }
            else // sinon
            {
                itemstack = this.contents[slotIndex].splitStack(amount); // la fonction splitStack(quantité) retire dans this.contents[slotIndex] le contenu et le met dans itemstack

                if(this.contents[slotIndex].stackSize == 0) // au cas où la quantité passe à 0 (ce qui ne devrait pas arriver en temps normal)
                {
                    this.contents[slotIndex] = null; // on met sur null, ça évite de se retrouver avec des itemstack bugué qui contiennent 0
                }
                this.markDirty(); // met à jour le tile entity
                return itemstack; // renvoie itemstack
            }
        }
        else // sinon si le contenu dans cette emplacement est null
        {
            return null; // renvoie null, puisqu'il n'y a rien dans cette emplacement
        }
        
    }
    @Override
    public ItemStack getStackInSlotOnClosing(int slotIndex)
    {
        if(this.contents[slotIndex] != null)
        {
            ItemStack itemstack = this.contents[slotIndex];
            this.contents[slotIndex] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }
    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack stack)
    {
        this.contents[slotIndex] = stack; // met l'item stack dans le tableau

        if(stack != null && stack.stackSize > this.getInventoryStackLimit()) // si la taille de l'item stack dépasse la limite maximum de l'inventaire
        {
            stack.stackSize = this.getInventoryStackLimit(); // on le remet sur la limite
        }

        this.markDirty(); // met à jour le tile entity
    }
    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }
    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }
    @Override
    public boolean isItemValidForSlot(int slotIndex, ItemStack stack)
    {
        return true;
    }
    @Override
    public void openInventory()
    {

    }

    @Override
    public void closeInventory()
    {

    }

  


	 
}
