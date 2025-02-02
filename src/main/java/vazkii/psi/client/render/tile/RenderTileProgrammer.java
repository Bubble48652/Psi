/*
 * This class is distributed as part of the Psi Mod.
 * Get the Source Code in github:
 * https://github.com/Vazkii/Psi
 *
 * Psi is Open Source and distributed under the
 * Psi License: https://psi.vazkii.net/license.php
 */
package vazkii.psi.client.render.tile;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFaceBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3f;

import vazkii.psi.api.spell.SpellGrid;
import vazkii.psi.client.core.handler.ClientTickHandler;
import vazkii.psi.client.gui.GuiProgrammer;
import vazkii.psi.common.Psi;
import vazkii.psi.common.block.tile.TileProgrammer;

public class RenderTileProgrammer extends TileEntityRenderer<TileProgrammer> {

	public RenderTileProgrammer(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn);
	}

	@Override
	public void render(TileProgrammer te, float partialticks, MatrixStack ms, IRenderTypeBuffer buffers, int worldLight, int overlay) {
		if (te.isEnabled()) {
			ms.push();
			int light = Psi.magical ? worldLight : 0xF000F0;

			ms.translate(0, 1.62F, 0);
			ms.rotate(Vector3f.ZP.rotationDegrees(180F));
			ms.rotate(Vector3f.YP.rotationDegrees(-90F));

			float rot = 90F;
			BlockState state = te.getBlockState();

			Direction facing = state.get(HorizontalFaceBlock.HORIZONTAL_FACING);
			switch (facing) {
			case SOUTH:
				rot = -90F;
				break;
			case EAST:
				rot = 180F;
				break;
			case WEST:
				rot = 0F;
				break;
			default:
				break;
			}

			ms.translate(0.5F, 0F, 0.5F);
			ms.rotate(Vector3f.YP.rotationDegrees(rot));
			ms.translate(-0.5F, 0F, -0.5F);

			float f = 1F / 300F;
			ms.scale(f, f, -f);

			if (Psi.magical) {
				ms.rotate(Vector3f.XP.rotationDegrees(90F));
				ms.translate(70F, -220F, -100F + Math.sin(ClientTickHandler.total / 50) * 10);
				ms.rotate(Vector3f.XP.rotationDegrees(-16F + (float) Math.cos(ClientTickHandler.total / 100) * 10F));
			} else {
				ms.translate(151 - SpellGrid.GRID_SIZE * 9, 162 - SpellGrid.GRID_SIZE * 18, -200F);
			}

			te.spell.draw(ms, buffers, light);

			ms.push();
			ms.translate((SpellGrid.GRID_SIZE - 9) * 9, (SpellGrid.GRID_SIZE - 9) * 18, -0.01F);
			IVertexBuilder buffer = buffers.getBuffer(GuiProgrammer.LAYER);
			float size = 94 + SpellGrid.GRID_SIZE * 18;
			float x = 74 - SpellGrid.GRID_SIZE * 9, y = 155 - SpellGrid.GRID_SIZE * 18;
			float width = 12 + SpellGrid.GRID_SIZE * 18;
			float height = 22 + SpellGrid.GRID_SIZE * 18;
			float u = 0, v = 0;
			float rescale = 1 / size;
			float a = Psi.magical ? 1F : 0.5F;
			Matrix4f mat = ms.getLast().getMatrix();
			buffer.pos(mat, x, y + height, 0).color(1, 1, 1, a).tex(u * rescale, (v + height) * rescale).lightmap(light).endVertex();
			buffer.pos(mat, x + width, y + height, 0).color(1, 1, 1, a).tex((u + width) * rescale, (v + height) * rescale).lightmap(light).endVertex();
			buffer.pos(mat, x + width, y, 0).color(1, 1, 1, a).tex((u + width) * rescale, v * rescale).lightmap(light).endVertex();
			buffer.pos(mat, x, y, 0).color(1, 1, 1, a).tex(u * rescale, v * rescale).lightmap(light).endVertex();
			ms.pop();

			int color = Psi.magical ? 0 : 0xFFFFFF;
			Minecraft mc = Minecraft.getInstance();
			mc.fontRenderer.renderString(I18n.format("psimisc.name"), 20, SpellGrid.GRID_SIZE * 18, color, false, ms.getLast().getMatrix(), buffers, false, 0, 0xF000F0);
			mc.fontRenderer.renderString(te.spell.name, 58, SpellGrid.GRID_SIZE * 18, color, false, ms.getLast().getMatrix(), buffers, false, 0, 0xF000F0);

			ms.pop();
		}
	}
}
