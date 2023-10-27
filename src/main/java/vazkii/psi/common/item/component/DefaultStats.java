/*
 * This class is distributed as part of the Psi Mod.
 * Get the Source Code in github:
 * https://github.com/Vazkii/Psi
 *
 * Psi is Open Source and distributed under the
 * Psi License: https://psi.vazkii.net/license.php
 */
package vazkii.psi.common.item.component;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import vazkii.psi.api.cad.CADStatEvent;
import vazkii.psi.api.cad.EnumCADComponent;
import vazkii.psi.api.cad.EnumCADStat;
import vazkii.psi.api.cad.ICAD;
import vazkii.psi.common.core.handler.ConfigHandler;
import vazkii.psi.common.item.base.ModItems;
import vazkii.psi.common.lib.LibMisc;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = LibMisc.MOD_ID)
public class DefaultStats {

	public static void registerStats() {
		registerAssemblyStats();
		registerCoreStats();
		registerSocketStats();
		registerBatteryStats();
	}

	public static void registerAssemblyStats() {
		//Iron
		ItemCADComponent.addStatToStack(ModItems.cadAssemblyIron, EnumCADStat.EFFICIENCY, ConfigHandler.COMMON.IronEfficiency.get());
		ItemCADComponent.addStatToStack(ModItems.cadAssemblyIron, EnumCADStat.POTENCY, ConfigHandler.COMMON.IronPotency.get());

		// Gold
		ItemCADComponent.addStatToStack(ModItems.cadAssemblyGold, EnumCADStat.EFFICIENCY, ConfigHandler.COMMON.GoldEfficiency.get());
		ItemCADComponent.addStatToStack(ModItems.cadAssemblyGold, EnumCADStat.POTENCY, ConfigHandler.COMMON.GoldPotency.get());

		// Psimetal
		ItemCADComponent.addStatToStack(ModItems.cadAssemblyPsimetal, EnumCADStat.EFFICIENCY, ConfigHandler.COMMON.PsimetalEfficiency.get());
		ItemCADComponent.addStatToStack(ModItems.cadAssemblyPsimetal, EnumCADStat.POTENCY, ConfigHandler.COMMON.PsimetalPotency.get());

		// Ebony Psimetal
		ItemCADComponent.addStatToStack(ModItems.cadAssemblyEbony, EnumCADStat.EFFICIENCY, ConfigHandler.COMMON.EbonyPsimetalEfficiency.get());
		ItemCADComponent.addStatToStack(ModItems.cadAssemblyEbony, EnumCADStat.POTENCY, ConfigHandler.COMMON.EbonyPsimetalPotency.get());

		// Ivory Psimetal
		ItemCADComponent.addStatToStack(ModItems.cadAssemblyIvory, EnumCADStat.EFFICIENCY, ConfigHandler.COMMON.IvoryPsimetalEfficiency.get());
		ItemCADComponent.addStatToStack(ModItems.cadAssemblyIvory, EnumCADStat.POTENCY, ConfigHandler.COMMON.IvoryPsimetalPotency.get());

		// Creative
		ItemCADComponent.addStatToStack(ModItems.cadAssemblyCreative, EnumCADStat.EFFICIENCY, -1);
		ItemCADComponent.addStatToStack(ModItems.cadAssemblyCreative, EnumCADStat.POTENCY, -1);
	}

	public static void registerCoreStats() {
		// Basic
		ItemCADComponent.addStatToStack(ModItems.cadCoreBasic, EnumCADStat.COMPLEXITY, ConfigHandler.COMMON.BasicComplexity.get());
		ItemCADComponent.addStatToStack(ModItems.cadCoreBasic, EnumCADStat.PROJECTION, ConfigHandler.COMMON.BasicProjection.get());

		// Overclocked
		ItemCADComponent.addStatToStack(ModItems.cadCoreOverclocked, EnumCADStat.COMPLEXITY, ConfigHandler.COMMON.OverclockedComplexity.get());
		ItemCADComponent.addStatToStack(ModItems.cadCoreOverclocked, EnumCADStat.PROJECTION, ConfigHandler.COMMON.OverclockedProjection.get());

		// Conductive
		ItemCADComponent.addStatToStack(ModItems.cadCoreConductive, EnumCADStat.COMPLEXITY, ConfigHandler.COMMON.ConductiveComplexity.get());
		ItemCADComponent.addStatToStack(ModItems.cadCoreConductive, EnumCADStat.PROJECTION, ConfigHandler.COMMON.ConductiveProjection.get());

		// Hyperclocked
		ItemCADComponent.addStatToStack(ModItems.cadCoreHyperClocked, EnumCADStat.COMPLEXITY, ConfigHandler.COMMON.HyperclockedComplexity.get());
		ItemCADComponent.addStatToStack(ModItems.cadCoreHyperClocked, EnumCADStat.PROJECTION, ConfigHandler.COMMON.HyperclockedProjection.get());

		// Radiative
		ItemCADComponent.addStatToStack(ModItems.cadCoreRadiative, EnumCADStat.COMPLEXITY, ConfigHandler.COMMON.RadiativeComplexity.get());
		ItemCADComponent.addStatToStack(ModItems.cadCoreRadiative, EnumCADStat.PROJECTION, ConfigHandler.COMMON.RadiativeProjection.get());
	}

	public static void registerSocketStats() {
		//Basic
		ItemCADComponent.addStatToStack(ModItems.cadSocketBasic, EnumCADStat.BANDWIDTH, ConfigHandler.COMMON.BasicBandwidth.get());
		ItemCADComponent.addStatToStack(ModItems.cadSocketBasic, EnumCADStat.SOCKETS, ConfigHandler.COMMON.BasicSockets.get());
		ItemCADComponent.addStatToStack(ModItems.cadSocketBasic, EnumCADStat.SAVED_VECTORS, ConfigHandler.COMMON.BasicSavedVectors.get());

		// Signaling
		ItemCADComponent.addStatToStack(ModItems.cadSocketSignaling, EnumCADStat.BANDWIDTH, ConfigHandler.COMMON.SignalingBandwidth.get());
		ItemCADComponent.addStatToStack(ModItems.cadSocketSignaling, EnumCADStat.SOCKETS, ConfigHandler.COMMON.SignalingSockets.get());
		ItemCADComponent.addStatToStack(ModItems.cadSocketSignaling, EnumCADStat.SAVED_VECTORS, ConfigHandler.COMMON.SignalingSavedVectors.get());

		// Large
		ItemCADComponent.addStatToStack(ModItems.cadSocketLarge, EnumCADStat.BANDWIDTH, ConfigHandler.COMMON.LargeBandwidth.get());
		ItemCADComponent.addStatToStack(ModItems.cadSocketLarge, EnumCADStat.SOCKETS, ConfigHandler.COMMON.LargeSockets.get());
		ItemCADComponent.addStatToStack(ModItems.cadSocketLarge, EnumCADStat.SAVED_VECTORS, ConfigHandler.COMMON.LargeSavedVectors.get());

		// Transmissive
		ItemCADComponent.addStatToStack(ModItems.cadSocketTransmissive, EnumCADStat.BANDWIDTH, ConfigHandler.COMMON.TransmissiveBandwidth.get());
		ItemCADComponent.addStatToStack(ModItems.cadSocketTransmissive, EnumCADStat.SOCKETS, ConfigHandler.COMMON.TransmissiveSockets.get());
		ItemCADComponent.addStatToStack(ModItems.cadSocketTransmissive, EnumCADStat.SAVED_VECTORS, ConfigHandler.COMMON.TransmissiveSavedVectors.get());

		// Huge
		ItemCADComponent.addStatToStack(ModItems.cadSocketHuge, EnumCADStat.BANDWIDTH, ConfigHandler.COMMON.HugeBandwidth.get());
		ItemCADComponent.addStatToStack(ModItems.cadSocketHuge, EnumCADStat.SOCKETS, ConfigHandler.COMMON.HugeSockets.get());
		ItemCADComponent.addStatToStack(ModItems.cadSocketHuge, EnumCADStat.SAVED_VECTORS, ConfigHandler.COMMON.HugeSavedVectors.get());
	}

	public static void registerBatteryStats() {
		// Basic
		ItemCADComponent.addStatToStack(ModItems.cadBatteryBasic, EnumCADStat.OVERFLOW, 100);

		// Extended
		ItemCADComponent.addStatToStack(ModItems.cadBatteryExtended, EnumCADStat.OVERFLOW, 200);

		// Ultradense
		ItemCADComponent.addStatToStack(ModItems.cadBatteryUltradense, EnumCADStat.OVERFLOW, 400);
	}

	@SubscribeEvent
	public static void modifyCreativeAssemblyStats(CADStatEvent event) {
		ItemStack cad = event.getCad();
		ICAD cadItem = (ICAD) cad.getItem();
		ItemStack assembly = cadItem.getComponentInSlot(cad, EnumCADComponent.ASSEMBLY);
		if (!assembly.isEmpty() && assembly.getItem() == ModItems.cadAssemblyCreative) {
			switch (event.getStat()) {
			case BANDWIDTH:
				event.setStatValue(9);
				break;
			case SOCKETS:
				event.setStatValue(12);
				break;
			default:
				event.setStatValue(-1);
				break;
			}
		}
	}
}
