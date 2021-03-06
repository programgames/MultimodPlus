package programgames.multimodplus.client;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import programgames.multimodplus.block.BlockMaker;


/**
 * This class draw the icon of the chest model for the inventory.
 * @author programgames
 *
 */

public class TesrInventoryRenderer implements ISimpleBlockRenderingHandler {

  @Override
  public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
 

    if (block == BlockMaker.multitexture && metadata == 0) {

      GL11.glPushMatrix();
      //    GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
      GL11.glRotated(180F, 1.0F, 0.0F, 0.0F);
      GL11.glTranslatef(0.0F, -1.0F, 0.0F);
      
      Minecraft.getMinecraft()
      .getTextureManager().bindTexture(TileEntityChestSpecialRenderer.texture);
      
      TileEntityChestSpecialRenderer.model.renderAll();
      GL11.glPopMatrix();
    }

  }

  @Override
  public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
      RenderBlocks renderer) {
    
    return false;
  }

  @Override
  public boolean shouldRender3DInInventory(int modelId) {

    return true;
  }

  @Override
  public int getRenderId() {
    
    return RenderInventoryMaker.tesrRenderId;
  }

}
