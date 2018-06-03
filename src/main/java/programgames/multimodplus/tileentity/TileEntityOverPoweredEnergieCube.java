package programgames.multimodplus.tileentity;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyStorage;
import io.netty.util.concurrent.EventExecutorGroup;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityOverPoweredEnergieCube extends TileEntity
        implements IEnergyStorage, IEnergyHandler {

  private byte direction;
  private String customName;
  protected int energy;
  private int capacity;
  private int maxReceive;
  private int maxExtract;

  public TileEntityOverPoweredEnergieCube(int capacity) {

    this(capacity, capacity, capacity);
  }


  /**
   * Create a tile.
   * @param capacity the capacity.
   * @param maxReceive the max input.
   * @param maxExtract the pax output.
   */
  public TileEntityOverPoweredEnergieCube(int capacity, int maxReceive, int maxExtract) {

    this.capacity = capacity;
    this.maxReceive = maxReceive;
    this.maxExtract = maxExtract;
  }




  /**
   * Set the capacity.
   * @param capacity  capacity.
   */
  public void setCapacity(int capacity) {

    this.capacity = capacity;

    if (energy > capacity) {
      energy = capacity;
    }
  }

  /**
   * Set the max output.
   * @param maxTransfer output.
   */
  public void setMaxTransfer(int maxTransfer) {

    setMaxReceive(maxTransfer);
    setMaxExtract(maxTransfer);
  }

  /**
   * set the max input.
   * @param maxReceive the input.
   */
  public void setMaxReceive(int maxReceive) {

    this.maxReceive = maxReceive;
  }

  public void setMaxExtract(int maxExtract) {

    this.maxExtract = maxExtract;
  }

  public int getMaxReceive() {

    return maxReceive;
  }

  public int getMaxExtract() {

    return maxExtract;
  }

  /**
   * This function is included to allow for server -&gt; client sync. Do not call this externally
   * to the containing Tile Entity, as not all IEnergyHandlers
   * are guaranteed to have it.
   *
   * @param energy the energy.
   */
  public void setEnergyStored(int energy) {

    this.energy = energy;

    if (this.energy > capacity) {
      this.energy = capacity;
    } else if (this.energy < 0) {
      this.energy = 0;
    }
  }

  /**
   * This function is included to allow the containing tile to directly and efficiently modify the
   * energy contained in the EnergyStorage. Do not rely on this
   * externally, as not all IEnergyHandlers are guaranteed to have it.
   *
   * @param energy the energy.
   */
  public void modifyEnergyStored(int energy) {

    this.energy += energy;

    if (this.energy > capacity) {
      this.energy = capacity;
    } else if (this.energy < 0) {
      this.energy = 0;
    }
  }

  /* IEnergyStorage */
  @Override
  public int receiveEnergy(int maxReceive, boolean simulate) {

    int energyReceived = Math.min(capacity - energy, Math.min(this.maxReceive, maxReceive));

    if (!simulate) {
      energy += energyReceived;
    }
    return energyReceived;
  }

  public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
    return capacity += 10;
  }

  @Override
  public int extractEnergy(int maxExtract, boolean simulate) {

    int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));

    if (!simulate) {
      energy -= energyExtracted;
    }
    return energyExtracted;
  }

  /**
   * Remove energy from an IEnergyProvider, internal distribution is left entirely to
   * the IEnergyProvider.
   *
   * @param from
   *            Orientation the energy is extracted from.
   * @param maxExtract
   *            Maximum amount of energy to extract.
   * @param simulate
   *            If TRUE, the extraction will only be simulated.
   * @return Amount of energy that was (or would have been, if simulated) extracted.
   */
  public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
    return capacity -= 10;
  }

  @Override
  public int getEnergyStored() {

    return energy;
  }

  /**
   * Returns the amount of energy currently stored.
   */
  public int getEnergyStored(ForgeDirection from) {
    return capacity;
  }

  /**
   * Returns the maximum amount of energy that can be stored.
   */
  public int getMaxEnergyStored(ForgeDirection from) {
    return 800;
  }

  @Override
  public int getMaxEnergyStored() {

    return capacity;
  }

  public void setCustomName(String customName) {
    this.customName = customName;
  }

  public byte getDirection() {
    return direction;
  }


  /**
   * Sets the direction.
   *
   * @param direction the new direction
   */
  public void setDirection(byte direction) {
    this.direction = direction;
    this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
  }

  /**
   * get the packet.
   * @return Packet
   */
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
  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
    this.readFromNBT(pkt.func_148857_g());
    this.worldObj.markBlockRangeForRenderUpdate(this.xCoord,
        this.yCoord, this.zCoord, this.xCoord, this.yCoord, this.zCoord);
  }


  @Override
  public boolean canConnectEnergy(ForgeDirection from) {
    // TODO Stub de la m�thode g�n�r� automatiquement
    return true;
  }
  
  @Override
  public void updateEntity() {
    
    energy = extractEnergy(10000,false);
    
    
  }
  
  


}
