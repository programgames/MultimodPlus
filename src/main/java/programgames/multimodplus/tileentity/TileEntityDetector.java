package programgames.multimodplus.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import programgames.multimodplus.block.BlockDetector;

public class TileEntityDetector extends TileEntity {

  private int facing = 2;
  private static int s = 3;

  @Override
  public void updateEntity() {

    if ((getWorldObj().getBlock(this.xCoord,this.yCoord + 1,this.zCoord) != null
            && !(getWorldObj().getBlock(xCoord, yCoord + 1, zCoord) instanceof BlockAir))) {
      ((BlockDetector)this.getWorldObj().getBlock(this.xCoord,this.yCoord,this.zCoord))
              .setBlockConnected(true);
      getWorldObj().notifyBlockChange(xCoord, yCoord, zCoord,this.getWorldObj()
              .getBlock(this.xCoord,this.yCoord - 1,this.zCoord));
    } else {
      ((BlockDetector)this.getWorldObj().getBlock(this.xCoord,this.yCoord,this.zCoord))
              .setBlockConnected(false);
      getWorldObj().notifyBlockChange(xCoord, yCoord, zCoord,this.getWorldObj()
                .getBlock(this.xCoord,this.yCoord - 1,this.zCoord));

    }
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
  }

  @Override
  public void writeToNBT(NBTTagCompound tag) {
    tag.setShort("Facing", (short) this.facing);

  }





}
