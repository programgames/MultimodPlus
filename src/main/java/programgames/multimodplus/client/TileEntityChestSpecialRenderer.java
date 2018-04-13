package programgames.multimodplus.client;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import programgames.multimodplus.Main;
import programgames.multimodplus.tileentity.TileEntityChest;

/**
 * Draw the render of the chest.
 * @author programgames
 *
 */
public class TileEntityChestSpecialRenderer extends TileEntitySpecialRenderer {

  public static ModelChest model = new ModelChest();
  public static ResourceLocation texture =
      new ResourceLocation(Main.MODID, "textures/models/blocks/multi.png");

  /**
   * Used to avoid crash.
   */
  public TileEntityChestSpecialRenderer() {
    this.func_147497_a(TileEntityRendererDispatcher.instance);
  }

  @Override
  public void renderTileEntityAt(TileEntity tile, double x, double y,
      double z, float partialRenderTick) {

    this.renderTileEntityTutorielAt((TileEntityChest)tile, x, y, z, partialRenderTick);
  }

  /**
   * Render tile entity tutoriel at.
   *
   * @param tile the tile
   * @param x the x
   * @param y the y
   * @param z the z
   * @param partialRenderTick the partial render tick
   */
  private void renderTileEntityTutorielAt(TileEntityChest tile, double x, double y, double z,
      float partialRenderTick) {
    GL11.glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
    GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
    GL11.glRotatef((90F * tile.getDirection()) + 180F, 0.0F, 1.0F, 0.0F);
    this.bindTexture(texture);
    float f1 = tile.prevLidAngle + (tile.lidAngle - tile.prevLidAngle) * partialRenderTick;
    f1 = 1.0F - f1; //f1 va aller de 1 à 0 quand elle va de 0 à 1 (inverse le sens de variation)
    f1 = 1.0F - f1 * f1 * f1;
    // à nouveau, sauf que là on retire f1 au cube. Du-coup le déplacement va être lent au début,
    //puis va accélérer (il suffit de comparer les variations de y=x et de y=x^3 entre 0 et 1 pour
    //constater)
    model.doorLeft.rotateAngleY = -(f1 * (float)Math.PI / 2.0F);
    model.leftHandle.rotateAngleY = -(f1 * (float)Math.PI / 2.0F);
    model.doorRight.rotateAngleY = (f1 * (float)Math.PI / 2.0F);
    model.rightHandle.rotateAngleY = (f1 * (float)Math.PI / 2.0F);
    model.renderAll();
    GL11.glPopMatrix();
  }

}
