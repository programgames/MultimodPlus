package programgames.multimodplus.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import programgames.multimodplus.tileentity.TileEntityChest;

// TODO: Auto-generated Javadoc
/**
 * Interaction between user and chest.Synchronyze where element are set.
 * @author JULIEN
 *
 */
public class ContainerCupboard extends Container {


  /** The tile tuto. */
  private final TileEntityChest tileTuto;
  /**
 * Create a container.
 * @param tile entity of the chest.
 * @param inventory Enventory of the player.
 */
  
  public ContainerCupboard(TileEntityChest tile, InventoryPlayer inventory) {
    this.tileTuto = tile;
    tile.openInventory();
    for (int i = 0; i < 3; ++i) {
      for (int j = 0; j < 9; ++j) {
        this.addSlotToContainer(new Slot(tile, j + i * 9, 8 + j * 18, 18 + i * 18));
      }
    }
    this.bindPlayerInventory(inventory);
  }

  /* (non-Javadoc)
   * @see net.minecraft.inventory.Container#canInteractWith
   * (net.minecraft.entity.player.EntityPlayer)
   */
  @Override
  public boolean canInteractWith(EntityPlayer player) {
    return this.tileTuto.isUseableByPlayer(player);

  }
  
  /**
   * Bind player inventory.
   *
   * @param inventory the inventory
   */
  private void bindPlayerInventory(InventoryPlayer inventory) {
    int i;
    for (i = 0; i < 3; ++i) {
      for (int j = 0; j < 9; ++j) {
        this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 86 + i * 18));
      }
    }

    for (i = 0; i < 9; ++i) {
      this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 144));
    }
  }
  
  /**
   * Shift-click function.
   *
   * @param player the player
   * @param slotIndex the slot index
   * @return the item stack
   */
  
  public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
    ItemStack itemstack = null;
    Slot slot = (Slot)this.inventorySlots.get(slotIndex);

    if (slot != null && slot.getHasStack()) {
      ItemStack itemstack1 = slot.getStack();
      itemstack = itemstack1.copy();

      if (slotIndex < this.tileTuto.getSizeInventory()) {
        if (!this.mergeItemStack(itemstack1, this.tileTuto.getSizeInventory(),
            this.inventorySlots.size(), true)) {
          return null;
        }
      
      
      } else if (!this.mergeItemStack(itemstack1, 0, this.tileTuto.getSizeInventory(), false)) {
        return null;
      }

      if (itemstack1.stackSize == 0) {
        slot.putStack((ItemStack)null);
      
      } else {
        slot.onSlotChanged();
      }
    }
    return itemstack;
  }
  
  /* (non-Javadoc)
   * @see net.minecraft.inventory.Container#onContainerClosed
   * (net.minecraft.entity.player.EntityPlayer)
   */
  public void onContainerClosed(EntityPlayer player) {
    super.onContainerClosed(player);
    this.tileTuto.closeInventory();
  }
  
  /**
   * Gets the tile tuto.
   *
   * @return the tile tuto
   */
  public TileEntityChest getTileTuto() {
    return tileTuto;
  }
}
