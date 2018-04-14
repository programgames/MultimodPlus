package programgames.multimodplus.tileentity;

import cofh.api.energy.IEnergyTransport;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityTransporter extends TileEntity implements IEnergyTransport {


  private byte direction;
  private String customName;
  protected int energy;
  protected int capacity;
  protected int maxReceive;
  protected int maxExtract;
  
  public TileEntityTransporter(int capacity) {

   
  }
  
  @Override
  public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
    // TODO Stub de la m�thode g�n�r� automatiquement
    return 20;
  }

  @Override
  public int getMaxEnergyStored(ForgeDirection from) {
    // TODO Stub de la m�thode g�n�r� automatiquement
    return 0;
  }

  @Override
  public boolean canConnectEnergy(ForgeDirection from) {
    // TODO Stub de la m�thode g�n�r� automatiquement
    return true;
  }

  @Override
  public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
    // TODO Stub de la m�thode g�n�r� automatiquement
    return 50;
  }

  @Override
  public int getEnergyStored(ForgeDirection from) {
    // TODO Stub de la m�thode g�n�r� automatiquement
    return 800;
  }

  @Override
  public InterfaceType getTransportState(ForgeDirection from) {
    // TODO Stub de la m�thode g�n�r� automatiquement
    return null;
  }

  @Override
  public boolean setTransportState(InterfaceType state, ForgeDirection from) {
    // TODO Stub de la m�thode g�n�r� automatiquement
    return false;
  }
 
  public void setCustomName(String customName) {
    this.customName = customName;
  }
  
  public byte getDirection() {
    return direction;
  }
  
  public void setDirection(byte direction) {
    this.direction = direction;
    this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
  }
  

}
