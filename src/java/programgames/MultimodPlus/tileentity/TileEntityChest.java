package programgames.MultimodPlus.tileentity;

import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;

public class TileEntityChest extends TileEntity implements IInventory{

	
	
    private byte direction;
    private String customName;

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.direction = compound.getByte("Direction");
        if(compound.hasKey("CustomName", Constants.NBT.TAG_STRING))
        {
            this.customName = compound.getString("CustomName");
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
  


	 
}
