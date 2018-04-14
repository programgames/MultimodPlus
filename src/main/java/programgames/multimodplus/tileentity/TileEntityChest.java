package programgames.multimodplus.tileentity;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.Constants;
import programgames.multimodplus.container.ContainerCupboard;

// TODO: Auto-generated Javadoc
/**
 * The Class TileEntityChest.
 */
public class TileEntityChest extends TileEntity implements IInventory {


  private byte direction;
  private ItemStack[] contents = new ItemStack[27];
  private String customName;

  public float lidAngle;
  public float prevLidAngle;
  public int numPlayersUsing;
  private int ticksSinceSync;

  /* (non-Javadoc)
   * @see net.minecraft.tileentity.TileEntity#readFromNBT(net.minecraft.nbt.NBTTagCompound)
   */
  @Override
  public void readFromNBT(NBTTagCompound compound) {
    super.readFromNBT(compound);
    this.direction = compound.getByte("Direction");
    if (compound.hasKey("CustomName", Constants.NBT.TAG_STRING)) {
      this.customName = compound.getString("CustomName");
    }
    NBTTagList nbttaglist = compound.getTagList("Items", Constants.NBT.TAG_COMPOUND);
    // on obtient la liste de tags nommée Items

    this.contents = new ItemStack[this.getSizeInventory()]; // on réinitialise le tableau

    for (int i = 0; i < nbttaglist.tagCount(); ++i) { // i varie de 0 à la taille la liste

      NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i); // on lit le tag nbt
      int j = nbttagcompound1.getByte("Slot") & 255; // on lit à quel slot se trouve l'item stack

      if (j >= 0 && j < this.contents.length) {
        this.contents[j] =
            ItemStack.loadItemStackFromNBT(nbttagcompound1);
        // on lit l'item stack qui se trouve dans le tag
      }
    }



  }

  /* (non-Javadoc)
   * @see net.minecraft.tileentity.TileEntity#writeToNBT(net.minecraft.nbt.NBTTagCompound)
   */
  @Override
  public void writeToNBT(NBTTagCompound compound) {

    super.writeToNBT(compound);
    compound.setByte("Direction", this.direction);
    if (this.hasCustomInventoryName()) {
      compound.setString("CustomName", this.customName);
    }
    NBTTagList nbttaglist = new NBTTagList(); // on créé une nouvelle liste de tags
    for (int i = 0; i < this.contents.length; ++i) { // i varie de 0 à la taille de notre tableau

      if (this.contents[ i] != null) {
        // si l'item stack à l'emplacement i du tableau n'est pas null

        NBTTagCompound nbttagcompound1 = new NBTTagCompound(); // on créé un tag nbt
        nbttagcompound1.setByte("Slot", (byte)i); // on enregistre son emplacement dans le tableau
        this.contents[ i].writeToNBT(nbttagcompound1); // on écrit l'item dans le tag
        nbttaglist.appendTag(nbttagcompound1); // on ajoute le tab à la liste
      }
    }
    compound.setTag("Items", nbttaglist); // on enregistre la liste dans le tag nbt

  }

  /* (non-Javadoc)
   * @see net.minecraft.inventory.IInventory#getInventoryName()
   */
  @Override
  public String getInventoryName() {
    return this.hasCustomInventoryName() ? this.customName : "tile.cupboard";
  }

  /* (non-Javadoc)
   * @see net.minecraft.inventory.IInventory#hasCustomInventoryName()
   */
  @Override
  public boolean hasCustomInventoryName() {
    return this.customName != null && !this.customName.isEmpty();
  }

  /**
   * Sets the custom name.
   *
   * @param customName the new custom name
   */
  public void setCustomName(String customName) {
    this.customName = customName;
  }

  /**
   * Gets the direction.
   *
   * @return the direction
   */
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

  /* (non-Javadoc)
   * @see net.minecraft.tileentity.TileEntity#getDescriptionPacket()
   */
  /**
   * Ecrit les metadata cote serveur logique.
   *
   * @return the description packet
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

  /* (non-Javadoc)
   * @see net.minecraft.inventory.IInventory#getSizeInventory()
   */
  @Override
  public int getSizeInventory() {
    return this.contents.length;
  }

  /* (non-Javadoc)
   * @see net.minecraft.inventory.IInventory#getStackInSlot(int)
   */
  @Override
  public ItemStack getStackInSlot(int slotIndex) {
    return this.contents[slotIndex];
  }

  /* (non-Javadoc)
   * @see net.minecraft.inventory.IInventory#decrStackSize(int, int)
   */
  @Override
  public ItemStack decrStackSize(int slotIndex, int amount) {
    if (this.contents[slotIndex] != null) { // si le contenu dans l'emplacement n'est pas null

      ItemStack itemstack;

      if (this.contents[slotIndex].stackSize <= amount) {
        // si la quantité est inférieur où égale à ce qu'on souhaite retirer

        itemstack = this.contents[slotIndex]; // la variable itemstack prends la valeur du contenu
        this.contents[slotIndex] = null; // on retire ce qui est dans la variable contents
        this.markDirty(); // met à jour le tile entity
        return itemstack;

        // renvoie itemstack

      } else { // sinon

        itemstack = this.contents[slotIndex].splitStack(amount);
        // la fonction splitStack(quantité) retire dans
        //this.contents[slotIndex] le contenu et le met dans itemstack

        if (this.contents[slotIndex].stackSize == 0) {
          // au cas où la quantité passe à 0 (ce qui ne devrait pas arriver en temps normal)

          this.contents[slotIndex] = null;
          // on met sur null, ça évite de se retrouver avec des itemstack bugué qui contiennent 0
        }
        this.markDirty(); // met à jour le tile entity
        return itemstack; // renvoie itemstack
      }

    } else { // sinon si le contenu dans cette emplacement est null

      return null; // renvoie null, puisqu'il n'y a rien dans cette emplacement
    }

  }

  /* (non-Javadoc)
   * @see net.minecraft.inventory.IInventory#getStackInSlotOnClosing(int)
   */
  @Override
  public ItemStack getStackInSlotOnClosing(int slotIndex) {
    if (this.contents[slotIndex] != null) {
      ItemStack itemstack = this.contents[slotIndex];
      this.contents[slotIndex] = null;
      return itemstack;

    } else {
      return null;
    }
  }

  /* (non-Javadoc)
   * @see net.minecraft.inventory.IInventory#setInventorySlotContents
   * (int, net.minecraft.item.ItemStack)
   */
  @Override
  public void setInventorySlotContents(int slotIndex, ItemStack stack) {
    this.contents[slotIndex] = stack; // met l'item stack dans le tableau

    if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
      // si la taille de l'item stack dépasse la limite maximum de l'inventaire

      stack.stackSize = this.getInventoryStackLimit(); // on le remet sur la limite
    }

    this.markDirty(); // met à jour le tile entity
  }

  /* (non-Javadoc)
   * @see net.minecraft.inventory.IInventory#getInventoryStackLimit()
   */
  @Override
  public int getInventoryStackLimit() {
    return 64;
  }

  /* (non-Javadoc)
   * @see net.minecraft.inventory.IInventory#isUseableByPlayer
   * (net.minecraft.entity.player.EntityPlayer)
   */
  @Override
  public boolean isUseableByPlayer(EntityPlayer player) {
    return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord)
        != this ? false : player.getDistanceSq((double)this.xCoord + 0.5D,
            (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
  }

  /* (non-Javadoc)
   * @see net.minecraft.inventory.IInventory#isItemValidForSlot(int, net.minecraft.item.ItemStack)
   */
  @Override
  public boolean isItemValidForSlot(int slotIndex, ItemStack stack) {
    return true;
  }

  /* (non-Javadoc)
   * @see net.minecraft.inventory.IInventory#openInventory()
   */
  @Override
  public void openInventory() {

    if (this.numPlayersUsing < 0) {
      // au cas où numPlayersUsing aurait prit une valeur inférieure
      //à 0 à cause d'un problème de synchro ou autre
      this.numPlayersUsing = 0; // on remet à 0
    }

    ++this.numPlayersUsing;
    // on augmente de 1 le nombre de joueurs ayant ouvert l'inventaire du bloc
    this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord,
        this.getBlockType(), 1, this.numPlayersUsing);
    // on ajoute un événement de bloc, il va servir pour la sync client / serveur
    this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord,
        this.getBlockType()); // on prévient le bloc d'un changement

    this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord - 1, this.zCoord,
        this.getBlockType()); // on prévient le bloc d'en dessous d'un changement
  }

  /* (non-Javadoc)
   * @see net.minecraft.inventory.IInventory#closeInventory()
   */
  @Override
  public void closeInventory() {


    --this.numPlayersUsing;
    this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord,
        this.getBlockType(), 1, this.numPlayersUsing);

    this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord,
        this.getBlockType());

    this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord - 1, this.zCoord,
        this.getBlockType());
  }

  @Override
  public void updateEntity() {
    ++this.ticksSinceSync;
    float f;

    if (!this.worldObj.isRemote && this.numPlayersUsing != 0
        && (this.ticksSinceSync + this.xCoord + this.yCoord + this.zCoord) % 200 == 0) {
      this.numPlayersUsing = 0;
      f = 5.0F;
      @SuppressWarnings("rawtypes")
      List list = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class,
          AxisAlignedBB.getBoundingBox((double)((float)this.xCoord - f),
              (double)((float)this.yCoord - f), (double)((float)this.zCoord - f),
              (double)((float)(this.xCoord + 1) + f),
              (double)((float)(this.yCoord + 1) + f),
              (double)((float)(this.zCoord + 1) + f)));
      
      @SuppressWarnings("rawtypes")
      Iterator iterator = list.iterator();

      while (iterator.hasNext()) {
        EntityPlayer entityplayer = (EntityPlayer)iterator.next();

        if (entityplayer.openContainer instanceof ContainerCupboard) {
          IInventory iinventory = ((ContainerCupboard)entityplayer.openContainer).getTileTuto()
              ;
          if (iinventory == this) {
            ++this.numPlayersUsing;
          }
        }
      }
    }

    this.prevLidAngle = this.lidAngle;
    f = 0.1F;

    if (this.numPlayersUsing > 0 && this.lidAngle == 0.0F) {
      this.worldObj.playSoundEffect(this.xCoord + 0.5D,
          this.yCoord + 0.5D, this.zCoord + 0.5D,
          "random.chestopen", 0.5F,
          this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
    }

    if (this.numPlayersUsing == 0 && this.lidAngle > 0.0F
        || this.numPlayersUsing > 0 && this.lidAngle < 1.0F) {
      float f1 = this.lidAngle;

      if (this.numPlayersUsing > 0) {
        this.lidAngle += f;
      
      } else {
        this.lidAngle -= f;
      }

      if (this.lidAngle > 1.0F) {
        this.lidAngle = 1.0F;
      }

      float f2 = 0.5F;

      if (this.lidAngle < f2 && f1 >= f2) {
        this.worldObj.playSoundEffect(this.xCoord + 0.5D,
            this.yCoord + 0.5D,
            this.zCoord + 0.5D, "random.chestclosed",
            0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
      }

      if (this.lidAngle < 0.0F) {
        this.lidAngle = 0.0F;
      }
    }
  }

  @Override
  public boolean receiveClientEvent(int id, int value) {
    if (id == 1) {
      this.numPlayersUsing = value;
      return true;
    }
    return super.receiveClientEvent(id, value);
  }
  
}
