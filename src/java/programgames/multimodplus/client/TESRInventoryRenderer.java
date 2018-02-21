package programgames.multimodplus.client;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import programgames.multimodplus.block.BlockMaker;

public class TESRInventoryRenderer implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
		// TODO Auto-generated method stub
		
		if(block == BlockMaker.multitexture && metadata == 0)
        {
			
            GL11.glPushMatrix();
        //    GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
            GL11.glRotated(180F, 1.0F, 0.0F, 0.0F);
            GL11.glTranslatef(0.0F, -1.0F, 0.0F);
            Minecraft.getMinecraft().getTextureManager().bindTexture(TileEntityChestSpecialRenderer.texture);
            TileEntityChestSpecialRenderer.model.renderAll();
            GL11.glPopMatrix();
        }

	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
			RenderBlocks renderer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getRenderId() {
		// TODO Auto-generated method stub
		return RenderInventoryMaker.tesrRenderId;
	}

}
