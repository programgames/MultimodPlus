package programgames.multimodplus.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import programgames.multimodplus.Main;
import programgames.multimodplus.container.ContainerCupboard;
import programgames.multimodplus.tileentity.TileEntityChest;
public class GuiCupboard extends GuiContainer {

	
	private static final ResourceLocation textures = new ResourceLocation(Main.MODID, "textures/gui/container/cupboard.png");
	   private TileEntityChest tileTuto;
	   private IInventory playerInv;
	
	 public GuiCupboard(TileEntityChest tile, InventoryPlayer inventory)
	   {
	       super(new ContainerCupboard(tile, inventory));
	       this.tileTuto = tile;
	       this.playerInv = inventory;
	       this.allowUserInput = false;
	       this.ySize = 170;
	   }

	  
	   protected void drawGuiContainerForegroundLayer(int x, int y)
	   {
	       String tileName = this.tileTuto.hasCustomInventoryName() ? this.tileTuto.getInventoryName() : I18n.format(this.tileTuto.getInventoryName());
	       this.fontRendererObj.drawString(tileName, (this.xSize - this.fontRendererObj.getStringWidth(tileName)) / 2, 6, 0);
	       String invName = this.playerInv.hasCustomInventoryName() ? this.playerInv.getInventoryName() : I18n.format(this.playerInv.getInventoryName());
	       this.fontRendererObj.drawString(invName, (this.xSize - this.fontRendererObj.getStringWidth(invName)) / 2, this.ySize - 96, 0);
	   }
	   @Override
	   protected void drawGuiContainerBackgroundLayer(float partialRenderTick, int x, int y)
	   {
	       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F); // on colorise
	       this.mc.getTextureManager().bindTexture(textures); // affiche la texture
	       int k = (this.width - this.xSize) / 2; // on calcul la coordonnée x du point en haut à gauche
	       int l = (this.height - this.ySize) / 2; // on calcul la coordonnée y du point en haut à gauche
	       this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize); // on desine la texture, la fonction à pour argument point x de départ, point y de départ, vecteur u, vecteur v, largeur, hauteur)
	   }
}
