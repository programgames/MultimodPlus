package programgames.MultimodPlus.client;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import programgames.MultimodPlus.Main;
import programgames.MultimodPlus.tileentity.TileEntityVoid;

public class TileEntityTutorielSpecialRenderer extends TileEntitySpecialRenderer {

	
	  public TileEntityTutorielSpecialRenderer()
	    {
	        this.func_147497_a(TileEntityRendererDispatcher.instance);
	    }
	 public static multi model = new multi();
	    public static ResourceLocation texture = new ResourceLocation(Main.MODID, "textures/models/blocks/multi.png");
    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialRenderTick) // la fonction qui �tait la de base
    {
        this.renderTileEntityTutorielAt((TileEntityVoid)tile, x, y, z, partialRenderTick); // j'appelle ma fonction renderTileEntityTutorielAt en castant TileEntityTutoriel � l'argument tile
    }

    private void renderTileEntityTutorielAt(TileEntityVoid tile, double x, double y, double z, float partialRenderTick) // ma propre fonction
    {
    	
    	GL11.glPushMatrix();
    	      GL11.glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
    	      GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
    	      GL11.glRotatef((90F * tile.getDirection()) + 180F, 0.0F, 1.0F, 0.0F);
    	       this.bindTexture(texture);
    	       model.renderAll();
    	        GL11.glPopMatrix();
    }
}
