/*
 * This class is distributed as part of the Psi Mod.
 * Get the Source Code in github:
 * https://github.com/Vazkii/Psi
 *
 * Psi is Open Source and distributed under the
 * Psi License: https://psi.vazkii.net/license.php
 */
package vazkii.psi.client.gui.button;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;

import vazkii.psi.api.spell.SpellGrid;
import vazkii.psi.client.gui.GuiProgrammer;

public class GuiButtonPage extends Button {

	public final boolean right;
	final GuiProgrammer gui;

	public GuiButtonPage(int x, int y, boolean right, GuiProgrammer gui) {
		super(x, y, 18, 10, TextComponent.EMPTY, button -> {});
		this.gui = gui;
		this.right = right;
	}

	public GuiButtonPage(int x, int y, boolean right, GuiProgrammer gui, Button.OnPress pressable) {
		super(x, y, 18, 10, TextComponent.EMPTY, pressable);
		this.gui = gui;
		this.right = right;
	}

	@Override
	public void renderButton(PoseStack ms, int par2, int par3, float pTicks) {
		if (active) {
			boolean hover = par2 >= x && par3 >= y && par2 < x + width && par3 < y + height;

			RenderSystem.setShaderTexture(0, GuiProgrammer.texture);
			RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
			blit(ms, x, y, hover ? 54 + SpellGrid.GRID_SIZE * 18 : 36 + SpellGrid.GRID_SIZE * 18, right ? SpellGrid.GRID_SIZE * 18 - 17 : SpellGrid.GRID_SIZE * 18 - 7, width, height, 94 + SpellGrid.GRID_SIZE * 18, 94 + SpellGrid.GRID_SIZE * 18);

			if (hover) {
				gui.tooltip.add(new TranslatableComponent(right ? "psimisc.next_page" : "psimisc.prev_page"));
			}
		}
	}

	public boolean isRight() {
		return right;
	}
}
