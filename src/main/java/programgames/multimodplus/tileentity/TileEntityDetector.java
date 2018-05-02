package programgames.multimodplus.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.Sys;
import programgames.multimodplus.block.BlockDetector;

public class TileEntityDetector extends TileEntity {

  private int facing = 2;
  public boolean isConnected = false;

  @Override
  public void updateEntity() {
    notifyConnection();
  }

  public int getFacing() {
    return facing;
  }

  public void setFacing(int dir) {
    facing = dir;
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
    return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 0, nbttagcompound);

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
  }

  private void notifyConnection() {
    if ((getWorldObj().getBlock(this.xCoord,this.yCoord + 1,this.zCoord) != null
            && !(getWorldObj().getBlock(xCoord, yCoord + 1, zCoord) instanceof BlockAir))) {
      isConnected = true;
      getWorldObj().notifyBlockOfNeighborChange(xCoord, yCoord - 1, zCoord,getBlockType());
    } else {
      isConnected = false;
      getWorldObj().notifyBlockOfNeighborChange(xCoord, yCoord - 1, zCoord,getBlockType());

    }
  }

  @Override
  public void onChunkUnload() {
    super.onChunkUnload();
    this.invalidate();
  }
}
