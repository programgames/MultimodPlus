package programgames.multimodplus.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import programgames.multimodplus.Main;
import programgames.multimodplus.container.ContainerCupboard;
import programgames.multimodplus.tileentity.TileEntityChest;
// TODO: Auto-generated Javadoc

/**
 * The Class GuiCupboard draw the gui of the chest.
 */
public class GuiCupboard extends GuiContainer {


  /** The Constant textures. */
  private static final ResourceLocation textures =
      new ResourceLocation(Main.MODID, "textures/gui/container/cupboard.png");
  
  /** The tile tuto. */
  private TileEntityChest tileTuto;
  
  /** The player inv. */
  private IInventory playerInv;

  /**
   * Instancie un nouveau gui cupboard.
   *
   * @param tile the tile
   * @param inventory the inventory
   */
  public GuiCupboard(TileEntityChest tile, InventoryPlayer inventory) {
    super(new ContainerCupboard(tile, inventory));
    this.tileTuto = tile;
    this.playerInv = inventory;
    this.allowUserInput = false;
    this.ySize = 170;
  }


  /* (non-Javadoc)
   * @see net.minecraft.client.gui.inventory.GuiContainer#drawGuiContainerForegroundLayer(int, int)
   */
  protected void drawGuiContainerForegroundLayer(int x, int y) {
    String tileName = this.tileTuto.hasCustomInventoryName()
        ? this.tileTuto.getInventoryName() : I18n.format(this.tileTuto.getInventoryName());
        
    this.fontRendererObj.drawString(tileName,
        (this.xSize - this.fontRendererObj.getStringWidth(tileName)) / 2, 6, 0);
    
    String invName = this.playerInv.hasCustomInventoryName()
        ? this.playerInv.getInventoryName() : I18n.format(this.playerInv.getInventoryName());
        
    this.fontRendererObj.drawString(invName,
        (this.xSize - this.fontRendererObj.getStringWidth(invName)) / 2, this.ySize - 96, 0);
  }
  
  /* (non-Javadoc)
   * @see net.minecraft.client.gui.inventory.GuiContainer
   * #drawGuiContainerBackgroundLayer(float, int, int)
   */
  @Override
  protected void drawGuiContainerBackgroundLayer(float partialRenderTick, int x, int y) {
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F); // on colorise
    this.mc.getTextureManager().bindTexture(textures); // affiche la texture
    int k = (this.width - this.xSize) / 2; // on calcul la coordonnée x du point en haut à gauche
    int l = (this.height - this.ySize) / 2; // on calcul la coordonnée y du point en haut à gauche
    this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    // on desine la texture, la fonction à pour argument point x de départ, point y
    //de départ, vecteur u, vecteur v, largeur, hauteur)
  }
}
