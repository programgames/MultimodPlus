package programgames.multimodplus.tileentity;

import ic2.api.tile.IWrenchable;
import net.minecraft.block.BlockAir;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityDetector extends TileEntity implements IWrenchable {

  private short facing = 2;
  public boolean isConnected = false;

  @Override
  public void updateEntity() {
    notifyConnection();
  }


  @Override
  public void readFromNBT(NBTTagCompound tag) {
    this.facing = tag.getShort("Facing");
    this.isConnected = tag.getBoolean("Connected");
    super.readFromNBT(tag);
  }

  @Override
  public void writeToNBT(NBTTagCompound tag) {
    tag.setShort("Facing", (short) this.facing);
    tag.setBoolean("Connected", isConnected);
    super.writeToNBT(tag);

  }

  @Override
  public Packet getDescriptionPacket() {
    NBTTagCompound nbttagcompound = new NBTTagCompound();
    this.writeToNBT(nbttagcompound);
    return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbttagcompound);

  }

  /* (non-Javadoc)
   * @see net.minecraft.tileentity.TileEntity
   * #onDataPacket(net.minecraft.network.NetworkManager,
   * net.minecraft.network.play.server.S35PacketUpdateTileEntity)
   */
  /**
   * * Lit les metadata cote serveur logique.
   *
   * @param net the net
   * @param pkt the pkt
   */
  @Override
  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
    this.readFromNBT(pkt.func_148857_g());
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

  }

  private void notifyConnection() {

    switch (facing)
    {
      case 2:
        if ((getWorldObj().getBlock(this.xCoord,this.yCoord,this.zCoord + 1) != null
                && !(getWorldObj().getBlock(xCoord, yCoord, zCoord + 1) instanceof BlockAir))) {
          isConnected = true;
          getWorldObj().notifyBlockOfNeighborChange(xCoord, yCoord, zCoord - 1 ,getBlockType());
        } else {
          isConnected = false;
          getWorldObj().notifyBlockOfNeighborChange(xCoord, yCoord, zCoord - 1,getBlockType());
        }
        break;
      case 3:
        if ((getWorldObj().getBlock(this.xCoord,this.yCoord,this.zCoord - 1) != null
                && !(getWorldObj().getBlock(xCoord, yCoord, zCoord - 1) instanceof BlockAir))) {
          isConnected = true;
          getWorldObj().notifyBlockOfNeighborChange(xCoord, yCoord, zCoord + 1 ,getBlockType());
        } else {
          isConnected = false;
          getWorldObj().notifyBlockOfNeighborChange(xCoord, yCoord, zCoord + 1,getBlockType());
        }
        break;
      case 4:
        if ((getWorldObj().getBlock(this.xCoord +1,this.yCoord,this.zCoord) != null
                && !(getWorldObj().getBlock(xCoord + 1, yCoord, zCoord ) instanceof BlockAir))) {
          isConnected = true;
          getWorldObj().notifyBlockOfNeighborChange(xCoord -1, yCoord, zCoord ,getBlockType());
        } else {
          isConnected = false;
          getWorldObj().notifyBlockOfNeighborChange(xCoord - 1, yCoord, zCoord,getBlockType());
        }
        break;
      case 5:
        if ((getWorldObj().getBlock(this.xCoord - 1,this.yCoord,this.zCoord) != null
                && !(getWorldObj().getBlock(xCoord - 1, yCoord, zCoord ) instanceof BlockAir))) {
          isConnected = true;
          getWorldObj().notifyBlockOfNeighborChange(xCoord + 1, yCoord, zCoord ,getBlockType());
        } else {
          isConnected = false;
          getWorldObj().notifyBlockOfNeighborChange(xCoord +1, yCoord, zCoord,getBlockType());
        }
        break;

    }


  }

  @Override
  public boolean wrenchCanSetFacing(EntityPlayer entityPlayer, int side)
  {
    return (side != 0 && side != 1);
  }

  @Override
  public short getFacing() {
    return this.facing;
  }


  @Override
  public void setFacing(short direction)
  {
    if(canSetFacing(direction))
    {
      facing = direction;
    }
    getWorldObj().markBlockForUpdate(xCoord,yCoord,zCoord);
    getWorldObj().notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, worldObj.getBlock(xCoord, yCoord, zCoord));
            this.markDirty();
  }

  /**
   * Whether or not this block's orientation can be changed to a specific direction. True by default.
   * @param facing - facing to check
   * @return if the block's orientation can be changed
   */
  public boolean canSetFacing(int facing)
  {
    return (facing != 0 && facing != 1);
  }

  @Override
  public boolean wrenchCanRemove(EntityPlayer entityPlayer)
  {
    return true;
  }

  @Override
  public float getWrenchDropRate()
  {
    return 1.0F;
  }

  @Override
  public ItemStack getWrenchDrop(EntityPlayer entityPlayer)
  {
    return new ItemStack(this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord), 1, this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord));  }

  @Override
  public void onChunkUnload() {
    super.onChunkUnload();
    this.invalidate();
  }

}
