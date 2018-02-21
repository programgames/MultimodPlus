package programgames.multimodplus.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

// TODO: Auto-generated Javadoc
/**
 * The Class TileEntityCounter count and print number clicked of a block.
 */
public class TileEntityCounter extends TileEntity {


  /** The number. */
  private int number;

  /* (non-Javadoc)
   * @see net.minecraft.tileentity.TileEntity#readFromNBT(net.minecraft.nbt.NBTTagCompound)
   */
  @Override
  public void readFromNBT(NBTTagCompound compound) {
    super.readFromNBT(compound);
    this.number = compound.getInteger("Number");
    // pour lire sa valeur depuis  la sauvegarde du monde lorsqu'on charge le chunk
    //qui contient l'entité de bloc
  }

  /* (non-Javadoc)
   * @see net.minecraft.tileentity.TileEntity#writeToNBT(net.minecraft.nbt.NBTTagCompound)
   */
  @Override
  public void writeToNBT(NBTTagCompound compound) {
    super.writeToNBT(compound);
    compound.setInteger("Number", this.number);
    // pour enregistrer sa valeur dans la sauvegarde du monde
    //lorsqu'on décharge le chunk qui contient l'entité de bloc
  }

  /**
   * Increase.
   */
  public void increase() { 
    // une fonction pour augmenter sa valeur
  
    this.number++;
  }

  /**
   * Decrease.
   */
  public void decrease() { 
    // une fonction pour diminuer sa valeur
  
    this.number--;
  }

  /**
   * Gets the number.
   *
   * @return the number
   */
  public int getNumber() { // et une fonction pour obtenir sa valeur (on appelle ça un getter)
  
    return number;
  }

}
