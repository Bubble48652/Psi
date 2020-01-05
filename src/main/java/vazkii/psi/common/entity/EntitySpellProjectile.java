/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Psi Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Psi
 *
 * Psi is Open Source and distributed under the
 * Psi License: http://psi.vazkii.us/license.php
 *
 * File Created @ [20/01/2016, 23:10:47 (GMT)]
 */
package vazkii.psi.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import vazkii.psi.api.cad.ICADColorizer;
import vazkii.psi.api.internal.Vector3;
import vazkii.psi.api.spell.ISpellAcceptor;
import vazkii.psi.api.spell.Spell;
import vazkii.psi.api.spell.SpellContext;
import vazkii.psi.api.internal.PsiRenderHelper;
import vazkii.psi.common.Psi;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

public class EntitySpellProjectile extends ThrowableEntity {

	private static final String TAG_COLORIZER = "colorizer";
	private static final String TAG_BULLET = "bullet";
	private static final String TAG_TIME_ALIVE = "timeAlive";

	private static final String TAG_LAST_MOTION_X = "lastMotionX";
	private static final String TAG_LAST_MOTION_Y = "lastMotionY";
	private static final String TAG_LAST_MOTION_Z = "lastMotionZ";

	private static final DataParameter<ItemStack> COLORIZER_DATA = EntityDataManager.createKey(EntitySpellProjectile.class, DataSerializers.ITEMSTACK);
	private static final DataParameter<ItemStack> BULLET_DATA = EntityDataManager.createKey(EntitySpellProjectile.class, DataSerializers.ITEMSTACK);
	private static final DataParameter<Optional<UUID>> CASTER_UUID = EntityDataManager.createKey(EntitySpellProjectile.class, DataSerializers.OPTIONAL_UNIQUE_ID);

	public SpellContext context;
	public int timeAlive;

	public EntitySpellProjectile(World worldIn) {
		super(worldIn);
		setSize(0F, 0F);
	}

	public EntitySpellProjectile(World worldIn, LivingEntity throwerIn) {
		super(worldIn, throwerIn);
		
		shoot(throwerIn, throwerIn.rotationPitch, throwerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
		double speed = 1.5;
		setMotion(getMotion().mul(speed, speed, speed));
	}

	public EntitySpellProjectile setInfo(PlayerEntity player, ItemStack colorizer, ItemStack bullet) {
		dataManager.set(COLORIZER_DATA, colorizer);
		dataManager.set(BULLET_DATA, bullet);
		dataManager.set(CASTER_UUID, Optional.of(player.getUniqueID()));
		return this;
	}

	@Override
	protected void registerData() {
		dataManager.register(COLORIZER_DATA, new ItemStack(Blocks.STONE));
		dataManager.register(BULLET_DATA, new ItemStack(Blocks.STONE));
		dataManager.register(CASTER_UUID, Optional.empty());
	}

	@Override
	public void writeAdditional(CompoundNBT tagCompound) {
		super.writeAdditional(tagCompound);

		CompoundNBT colorizerCmp = new CompoundNBT();
		ItemStack colorizer = dataManager.get(COLORIZER_DATA);
		if(!colorizer.isEmpty())
			colorizerCmp = colorizer.write(colorizerCmp);
		tagCompound.put(TAG_COLORIZER, colorizerCmp);

		CompoundNBT bulletCmp = new CompoundNBT();
		ItemStack bullet = dataManager.get(BULLET_DATA);
		if(!bullet.isEmpty())
			bulletCmp = bullet.write(bulletCmp);
		tagCompound.put(TAG_BULLET, bulletCmp);

		tagCompound.putInt(TAG_TIME_ALIVE, timeAlive);

		tagCompound.putDouble(TAG_LAST_MOTION_X, getMotion().getX());
		tagCompound.putDouble(TAG_LAST_MOTION_Y, getMotion().getY());
		tagCompound.putDouble(TAG_LAST_MOTION_Z, getMotion().getZ());
	}

	@Override
	public void readAdditional(CompoundNBT tagCompound) {
		super.readAdditional(tagCompound);

		CompoundNBT colorizerCmp = tagCompound.getCompound(TAG_COLORIZER);
		ItemStack colorizer = ItemStack.read(colorizerCmp);
		dataManager.set(COLORIZER_DATA, colorizer);

		CompoundNBT bulletCmp = tagCompound.getCompound(TAG_BULLET);
		ItemStack bullet = ItemStack.read(bulletCmp);
		dataManager.set(BULLET_DATA, bullet);

		LivingEntity thrower = getThrower();
		if(thrower instanceof PlayerEntity)
			dataManager.set(CASTER_UUID, Optional.of(thrower.getUniqueID()));

		timeAlive = tagCompound.getInt(TAG_TIME_ALIVE);

		double lastMotionX = tagCompound.getDouble(TAG_LAST_MOTION_X);
		double lastMotionY = tagCompound.getDouble(TAG_LAST_MOTION_Y);
		double lastMotionZ = tagCompound.getDouble(TAG_LAST_MOTION_Z);
		setMotion(lastMotionX, lastMotionY, lastMotionZ);
	}

	@Override
	public void tick() {
		super.tick();
		
		int timeAlive = ticksExisted;
		if(timeAlive > getLiveTime())
			remove();

		int colorVal = ICADColorizer.DEFAULT_SPELL_COLOR;
		ItemStack colorizer = dataManager.get(COLORIZER_DATA);
		if(!colorizer.isEmpty() && colorizer.getItem() instanceof ICADColorizer)
			colorVal = Psi.proxy.getColorForColorizer(colorizer);

		float r = PsiRenderHelper.r(colorVal) / 255F;
		float g = PsiRenderHelper.g(colorVal) / 255F;
		float b = PsiRenderHelper.b(colorVal) / 255F;

		double x = posX;
		double y = posY;
		double z = posZ;
		
		Vector3 lookOrig = new Vector3(getMotion()).normalize();
		for(int i = 0; i < getParticleCount(); i++) {
			Vector3 look = lookOrig.copy();
			double spread = 0.6;
			double dist = 0.15;
			if(this instanceof EntitySpellGrenade) {
				look.y += 1;
				dist = 0.05;
			}

			look.x += (Math.random() - 0.5) * spread;
			look.y += (Math.random() - 0.5) * spread;
			look.z += (Math.random() - 0.5) * spread;

			look.normalize().multiply(dist);

			Psi.proxy.sparkleFX(x, y, z, r, g, b, (float) look.x, (float) look.y, (float) look.z, 1.2F, 12);
		}
	}

	public int getLiveTime() {
		return 600;
	}

	public int getParticleCount() {
		return 5;
	}

	@Override
	protected void onImpact(@Nonnull RayTraceResult pos) {
		if(pos instanceof EntityRayTraceResult && ((EntityRayTraceResult) pos).getEntity() instanceof LivingEntity) {
			cast((SpellContext context) -> {
				if (context != null) {
					context.attackedEntity = (LivingEntity) ((EntityRayTraceResult) pos).getEntity();
				}
			});
		} else cast();
	}

	public void cast() {
		cast(null);
	}

	public void cast(Consumer<SpellContext> callback) {
		Entity thrower = getThrower();
		boolean canCast = false;

		if(thrower instanceof PlayerEntity) {
			ItemStack spellContainer = dataManager.get(BULLET_DATA);
			if (!spellContainer.isEmpty() && ISpellAcceptor.isContainer(spellContainer)) {
				Spell spell = ISpellAcceptor.acceptor(spellContainer).getSpell();
				if(spell != null) {
					canCast = true;
					if(context == null)
						context = new SpellContext().setPlayer((PlayerEntity) thrower).setFocalPoint(this).setSpell(spell);
					context.setFocalPoint(this);
				}
			}
		}

		if(callback != null)
			callback.accept(context);

		if(canCast && context != null)
			context.cspell.safeExecute(context);

		remove();
	}

	@Override
	public LivingEntity getThrower() {
		LivingEntity superThrower = super.getThrower();
		if(superThrower != null)
			return superThrower;

		return dataManager.get(CASTER_UUID)
				.map(u -> getEntityWorld().getPlayerByUuid(u))
				.orElse(null);
	}

	@Override
	protected float getGravityVelocity() {
		return 0F;
	}

	@Override
	public boolean doesEntityNotTriggerPressurePlate() {
		return true;
	}

}
