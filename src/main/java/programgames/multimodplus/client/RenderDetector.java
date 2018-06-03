package programgames.multimodplus.client;

import com.enderio.core.api.client.render.VertexTransform;
import com.enderio.core.client.render.BoundingBox;
import com.enderio.core.common.vecmath.Vector3d;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import programgames.multimodplus.block.BlockMaker;
import programgames.multimodplus.proxy.CommonProxy;
import programgames.multimodplus.tileentity.TileEntityDetector;

/**
  * Class RenderDetector ...
  *
  * @author Julien
  *  Created on 03/06/2018
 */
public class RenderDetector implements ISimpleBlockRenderingHandler {
  /**
   * Method the renderInventoryBlock ...
   *
   * @param block    of type Block
   * @param metadata of type int
   * @param modelId  of type int
   * @param renderer of type RenderBlocks
   */
  @Override
  public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
    Tessellator.instance.startDrawingQuads();
    renderWorldBlock(null, 0, 0, 0, null, 0, renderer);
    Tessellator.instance.draw();
  }

  /**
   * Method the renderWorldBlock ...
   *
   * @param world    of type IBlockAccess
   * @param x        of type int
   * @param y        of type int
   * @param z        of type int
   * @param block    of type Block
   * @param modelId  of type int
   * @param renderer of type RenderBlocks
   * @return boolean
   */
  @Override
  public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block,
                                  int modelId, RenderBlocks renderer) {
    TileEntity tile = world.getTileEntity(x, y, z);
    int rotation = 0;
    if (tile instanceof TileEntityDetector) {
      int facing = ((TileEntityDetector) tile).getFacing();
      switch (facing) {
        case 2:
          rotation = 0;
          break;
        case 3:
          rotation = 180;
          break;
        case 4:
          rotation = 90;
          break;
        case 5:
          rotation = 270;
          break;
        default:
          rotation = 0;
      }

    }
    Tessellator tes = Tessellator.instance;
    renderer.setRenderBounds(0F, 0F, 0F, 1F, 1F, 1F);
    renderer.renderStandardBlock(block, x, y, z);

    IIcon baseIcon = BlockMaker.detector.getIcon(0, 0);

    tes.addTranslation(x, y, z);

    VertexTransform rot = new VertexRotation(
            Math.toRadians(rotation),
            new Vector3d(0, 1, 0),
            new Vector3d(0.5, 0.5, 0.5)
    );

    VertexTransform trans = new VertexTranslation(0, 0.5 - 0.025, 0);
    VertexTransformComposite xform = new VertexTransformComposite(rot, trans);
    CubeRenderer.render(BoundingBox.UNIT_CUBE.scale(0.999, 0.06, 0.999), baseIcon, xform, true);

    tes.addTranslation(-x, -y, -z);
    return false;
  }

  /**
   * Method the shouldRender3DInInventory ...
   *
   * @param modelId of type int
   * @return boolean
   */
  @Override
  public boolean shouldRender3DInInventory(int modelId) {
    return false;
  }


  /**
   * Method getRenderId returns the renderId of this RenderDetector object.
   *
   * @return the renderId (type int) of this RenderDetector object.
   */
  @Override
  public int getRenderId() {
    return CommonProxy.renderDetectorId;
  }
}