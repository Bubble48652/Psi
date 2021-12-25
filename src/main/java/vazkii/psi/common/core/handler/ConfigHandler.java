/*
 * This class is distributed as part of the Psi Mod.
 * Get the Source Code in github:
 * https://github.com/Vazkii/Psi
 *
 * Psi is Open Source and distributed under the
 * Psi License: https://psi.vazkii.net/license.php
 */
package vazkii.psi.common.core.handler;

import net.minecraftforge.common.ForgeConfigSpec;

import org.apache.commons.lang3.tuple.Pair;

public class ConfigHandler {

	public static class Client {

		public final ForgeConfigSpec.BooleanValue useShaders;
		public final ForgeConfigSpec.BooleanValue psiBarOnRight;
		public final ForgeConfigSpec.BooleanValue contextSensitiveBar;
		public final ForgeConfigSpec.BooleanValue pauseGameInProgrammer;
		public final ForgeConfigSpec.IntValue maxPsiBarScale;
		public final ForgeConfigSpec.BooleanValue changeGridCoordinatesToLetterNumber;

		public Client(ForgeConfigSpec.Builder builder) {
			useShaders = builder.comment("Controls whether Psi's shaders are used. If you're using the GLSL Shaders mod and are having graphical troubles with Psi stuff, you may want to turn this off.")
					.define("client.useShaders", true);

			psiBarOnRight = builder.comment("Controls whether the Psi Bar should be rendered on the right of the screen or not.")
					.define("client.psiBarOnRight", true);

			contextSensitiveBar = builder.comment("Controls whether the Psi Bar should be hidden if it's full and the player is holding an item that uses Psi.")
					.define("client.contextSensitiveBar", true);

			maxPsiBarScale = builder.comment("The maximum scale your Psi bar can be. This prevents it from being too large on a bigger GUI scale. This is maximum amount of \\\"on screen pixels\\\" each actual pixel can take.")
					.defineInRange("client.maxPsiBarScale", 3, 1, 5);

			pauseGameInProgrammer = builder.comment("Controls whether the Spell Programmer screen will pause the game in singleplayer.")
					.define("client.pauseGameInProgrammer", true);

			changeGridCoordinatesToLetterNumber = builder.comment("Controls whether or not the Programmer will display the coordinates as a pair of two numbers or as a letter and a number")
					.define("client.changeGridCoordinatesToLetterNumber", false);
		}
	}

	public static final Client CLIENT;
	public static final ForgeConfigSpec CLIENT_SPEC;

	static {
		final Pair<Client, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Client::new);
		CLIENT_SPEC = specPair.getRight();
		CLIENT = specPair.getLeft();
	}

	public static class Common {

		public final ForgeConfigSpec.BooleanValue magiPsiClientSide;
		public final ForgeConfigSpec.IntValue spellCacheSize;
		public final ForgeConfigSpec.IntValue cadHarvestLevel;
		public final ForgeConfigSpec.IntValue maxPsiEnergy;
		public final ForgeConfigSpec.IntValue regenRate;
		public final ForgeConfigSpec.IntValue deductPsiTicks;
		public final ForgeConfigSpec.DoubleValue reducePsiPercentWhenDamaged;
		public final ForgeConfigSpec.IntValue LeggingsTicks;
		public final ForgeConfigSpec.IntValue HarvestLevel;
		public final ForgeConfigSpec.IntValue SwordAttackDamage;
		public final ForgeConfigSpec.DoubleValue SwordAttackSpeed;
		public final ForgeConfigSpec.IntValue PieceTrickResistanceCostMultiple;
		public final ForgeConfigSpec.IntValue PieceTrickExplodeCostMultiple;
		public final ForgeConfigSpec.IntValue GirdSize;

		public final ForgeConfigSpec.IntValue IronEfficiency;
		public final ForgeConfigSpec.IntValue IronPotency;
		public final ForgeConfigSpec.IntValue GoldEfficiency;
		public final ForgeConfigSpec.IntValue GoldPotency;
		public final ForgeConfigSpec.IntValue PsimetalEfficiency;
		public final ForgeConfigSpec.IntValue PsimetalPotency;
		public final ForgeConfigSpec.IntValue EbonyPsimetalEfficiency;
		public final ForgeConfigSpec.IntValue EbonyPsimetalPotency;
		public final ForgeConfigSpec.IntValue IvoryPsimetalEfficiency;
		public final ForgeConfigSpec.IntValue IvoryPsimetalPotency;

		public final ForgeConfigSpec.IntValue BasicComplexity;
		public final ForgeConfigSpec.IntValue BasicProjection;
		public final ForgeConfigSpec.IntValue OverclockedComplexity;
		public final ForgeConfigSpec.IntValue OverclockedProjection;
		public final ForgeConfigSpec.IntValue ConductiveComplexity;
		public final ForgeConfigSpec.IntValue ConductiveProjection;
		public final ForgeConfigSpec.IntValue HyperclockedComplexity;
		public final ForgeConfigSpec.IntValue HyperclockedProjection;
		public final ForgeConfigSpec.IntValue RadiativeComplexity;
		public final ForgeConfigSpec.IntValue RadiativeProjection;

		public final ForgeConfigSpec.IntValue BasicBandwidth;
		public final ForgeConfigSpec.IntValue BasicSockets;
		public final ForgeConfigSpec.IntValue BasicSavedVectors;
		public final ForgeConfigSpec.IntValue SignalingBandwidth;
		public final ForgeConfigSpec.IntValue SignalingSockets;
		public final ForgeConfigSpec.IntValue SignalingSavedVectors;
		public final ForgeConfigSpec.IntValue LargeBandwidth;
		public final ForgeConfigSpec.IntValue LargeSockets;
		public final ForgeConfigSpec.IntValue LargeSavedVectors;
		public final ForgeConfigSpec.IntValue TransmissiveBandwidth;
		public final ForgeConfigSpec.IntValue TransmissiveSockets;
		public final ForgeConfigSpec.IntValue TransmissiveSavedVectors;
		public final ForgeConfigSpec.IntValue HugeBandwidth;
		public final ForgeConfigSpec.IntValue HugeSockets;
		public final ForgeConfigSpec.IntValue HugeSavedVectors;

		public final ForgeConfigSpec.IntValue BasicOverflow;
		public final ForgeConfigSpec.IntValue ExtendedOverflow;
		public final ForgeConfigSpec.IntValue UltradenseOverflow;

		public Common(ForgeConfigSpec.Builder builder) {

			magiPsiClientSide = builder.comment("Set this to true to disable all server side features from Magical Psi, to allow you to use it purely as a client side mod")
					.define("common.magiPsiClientSide", false);

			spellCacheSize = builder.comment("How many compiled spells should be kept in a cache. Probably best not to mess with it if you don't know what you're doing.")
					.defineInRange("common.spellCacheSize", 200, 0, Integer.MAX_VALUE);

			cadHarvestLevel = builder.comment("The harvest level of a CAD for the purposes of block breaking spells. Defaults to 3 (diamond level)")
					.defineInRange("common.cadHarvestLevel", 3, 0, 255);

			maxPsiEnergy = builder.comment("The maximum energy")
					.defineInRange("common.maxPsiEnergy", 10000, 0, Integer.MAX_VALUE);

			regenRate = builder.comment("The energy regen rate (per tick)")
					.defineInRange("common.regenRate", 50, 0, Integer.MAX_VALUE);

			deductPsiTicks = builder.comment("The ticks of deducting psi when you are attacked")
					.defineInRange("common.deductPsiTicks", 20, 0, Integer.MAX_VALUE);

			LeggingsTicks = builder.comment("Every ? ticks the leggings execute once")
					.defineInRange("common.LeggingsTicks", 20, 0, Integer.MAX_VALUE);

			HarvestLevel = builder.comment("The harvestLevel of axe,pickaxe and shovel")
					.defineInRange("common.HarvestLevel", 3, 0, Integer.MAX_VALUE);

			SwordAttackDamage = builder.comment("The basic attack damage of sword (? + 3)")
					.defineInRange("common.SwordAttackDamage", 4, 0, Integer.MAX_VALUE);

			SwordAttackSpeed = builder.comment("The attack speed of sword (4 - ?)")
					.defineInRange("common.SwordAttackSpeed", 2.4, -1000.0, Float.MAX_VALUE);

			PieceTrickResistanceCostMultiple = builder.comment("The multiple of piece trick resistance cost, default: power*power*power*time*5")
					.defineInRange("common.PieceTrickResistanceCostMultiple", 1, 0, Integer.MAX_VALUE);

			PieceTrickExplodeCostMultiple = builder.comment("The multiple of piece trick explode cost, default: power*210")
					.defineInRange("common.PieceTrickExplodeCostMultiple", 1, 0, Integer.MAX_VALUE);

			reducePsiPercentWhenDamaged = builder.comment("reduce Psi percent per damage when damaged")
					.defineInRange("common.reducePsiPercentWhenDamaged", 0.00, 0, 1);

			GirdSize = builder.comment("The grid size of programmer, available values are 9,11,13,15,17")
					.defineInRange("common.GirdSize", 17, 9, 17);


			IronEfficiency = builder.defineInRange("assemblyStats.IronEfficiency", 70, 0, Integer.MAX_VALUE);
			IronPotency = builder.defineInRange("assemblyStats.IronPotency", 100, 0, Integer.MAX_VALUE);

			GoldEfficiency = builder.defineInRange("assemblyStats.GoldEfficiency", 75, 0, Integer.MAX_VALUE);
			GoldPotency = builder.defineInRange("assemblyStats.GoldPotency", 175, 0, Integer.MAX_VALUE);

			PsimetalEfficiency = builder.defineInRange("assemblyStats.PsimetalEfficiency", 85, 0, Integer.MAX_VALUE);
			PsimetalPotency = builder.defineInRange("assemblyStats.PsimetalPotency", 250, 0, Integer.MAX_VALUE);

			EbonyPsimetalEfficiency = builder.defineInRange("assemblyStats.EbonyPsimetalEfficiency", 90, 0, Integer.MAX_VALUE);
			EbonyPsimetalPotency = builder.defineInRange("assemblyStats.EbonyPsimetalPotency", 350, 0, Integer.MAX_VALUE);

			IvoryPsimetalEfficiency = builder.defineInRange("assemblyStats.IvoryPsimetalEfficiency", 95, 0, Integer.MAX_VALUE);
			IvoryPsimetalPotency = builder.defineInRange("assemblyStats.IvoryPsimetalPotency", 320, 0, Integer.MAX_VALUE);



			BasicComplexity = builder.defineInRange("coreStats.BasicComplexity", 14, 0, Integer.MAX_VALUE);
			BasicProjection = builder.defineInRange("coreStats.BasicProjection", 1, 0, Integer.MAX_VALUE);

			OverclockedComplexity = builder.defineInRange("coreStats.OverclockedComplexity", 24, 0, Integer.MAX_VALUE);
			OverclockedProjection = builder.defineInRange("coreStats.OverclockedProjection", 3, 0, Integer.MAX_VALUE);

			ConductiveComplexity = builder.defineInRange("coreStats.ConductiveComplexity", 20, 0, Integer.MAX_VALUE);
			ConductiveProjection = builder.defineInRange("coreStats.ConductiveProjection", 4, 0, Integer.MAX_VALUE);

			HyperclockedComplexity = builder.defineInRange("coreStats.HyperclockedComplexity", 36, 0, Integer.MAX_VALUE);
			HyperclockedProjection = builder.defineInRange("coreStats.HyperclockedProjection", 6, 0, Integer.MAX_VALUE);

			RadiativeComplexity = builder.defineInRange("coreStats.IvoryPsimetalEfficiency", 30, 0, Integer.MAX_VALUE);
			RadiativeProjection = builder.defineInRange("coreStats.IvoryPsimetalPotency", 7, 0, Integer.MAX_VALUE);



			BasicBandwidth = builder.defineInRange("SocketStats.BasicBandwidth", 5, 0, Integer.MAX_VALUE);
			BasicSockets = builder.defineInRange("SocketStats.BasicSockets", 4, 0, Integer.MAX_VALUE);
			BasicSavedVectors = builder.defineInRange("SocketStats.BasicSavedVectors", 7, 0, Integer.MAX_VALUE);

			SignalingBandwidth = builder.defineInRange("SocketStats.SignalingBandwidth", 7, 0, Integer.MAX_VALUE);
			SignalingSockets = builder.defineInRange("SocketStats.SignalingSockets", 6, 0, Integer.MAX_VALUE);
			SignalingSavedVectors = builder.defineInRange("SocketStats.SignalingSavedVectors", 14, 0, Integer.MAX_VALUE);

			LargeBandwidth = builder.defineInRange("SocketStats.LargeBandwidth", 6, 0, Integer.MAX_VALUE);
			LargeSockets = builder.defineInRange("SocketStats.LargeSockets", 8, 0, Integer.MAX_VALUE);
			LargeSavedVectors = builder.defineInRange("SocketStats.LargeSavedVectors", 14, 0, Integer.MAX_VALUE);

			TransmissiveBandwidth = builder.defineInRange("SocketStats.TransmissiveBandwidth", 9, 0, Integer.MAX_VALUE);
			TransmissiveSockets = builder.defineInRange("SocketStats.TransmissiveSockets", 10, 0, Integer.MAX_VALUE);
			TransmissiveSavedVectors = builder.defineInRange("SocketStats.TransmissiveSavedVectors", 18, 0, Integer.MAX_VALUE);

			HugeBandwidth = builder.defineInRange("SocketStats.HugeBandwidth", 8, 0, Integer.MAX_VALUE);
			HugeSockets = builder.defineInRange("SocketStats.HugeSockets", 12, 0, Integer.MAX_VALUE);
			HugeSavedVectors = builder.defineInRange("SocketStats.HugeSavedVectors", 21, 0, Integer.MAX_VALUE);

			BasicOverflow = builder.defineInRange("BatteryStats.BasicOverflow", 100, 0, Integer.MAX_VALUE);

			ExtendedOverflow = builder.defineInRange("BatteryStats.ExtendedOverflow", 200, 0, Integer.MAX_VALUE);

			UltradenseOverflow = builder.defineInRange("BatteryStats.UltradenseOverflow", 400, 0, Integer.MAX_VALUE);

		}
	}

	public static final Common COMMON;
	public static final ForgeConfigSpec COMMON_SPEC;

	static {
		final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
		COMMON_SPEC = specPair.getRight();
		COMMON = specPair.getLeft();
	}
}
