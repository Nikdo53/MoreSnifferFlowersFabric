package net.nikdo53.moresnifferflowers.entities;

import net.nikdo53.moresnifferflowers.MoreSnifferFlowers;
import net.nikdo53.moresnifferflowers.entities.goals.BoblingAttackPlayerGoal;
import net.nikdo53.moresnifferflowers.entities.goals.BoblingAvoidPlayerGoal;
import net.nikdo53.moresnifferflowers.init.ModBlocks;
import net.nikdo53.moresnifferflowers.init.ModEntityTypes;
import net.nikdo53.moresnifferflowers.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.Mth;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.IntFunction;

public class BoblingEntity extends PathfinderMob {
    private static final EntityDataAccessor<Boolean> DATA_CURED = SynchedEntityData.defineId(BoblingEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_RUNNING = SynchedEntityData.defineId(BoblingEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Optional<BlockPos>> DATA_WANTED_POS = SynchedEntityData.defineId(BoblingEntity.class, EntityDataSerializers.OPTIONAL_BLOCK_POS);
    private static final EntityDataAccessor<Boolean> DATA_PLANTING = SynchedEntityData.defineId(BoblingEntity.class, EntityDataSerializers.BOOLEAN);

    private int idleAnimationTimeout = 0;
    public AnimationState idleAnimationState = new AnimationState();
    public AnimationState plantingAnimationState = new AnimationState();

    private boolean finalizePlanting = false;
    private int plantingProgress = 0;
    private static final int MAX_PLANTING_PROGRESS = 35;

    public BoblingEntity(EntityType<? extends BoblingEntity> pEntityType, Level pLevel, boolean type) {
        super(pEntityType, pLevel);
        setCured(type);
    }

    public BoblingEntity(EntityType<? extends BoblingEntity> pEntityType, Level pLevel) {
        this(pEntityType, pLevel, false);
    }

    public BoblingEntity(Level level, boolean type) {
        this(ModEntityTypes.BOBLING.get(), level, type);
    }

    public boolean isCured() {
        return this.entityData.get(DATA_CURED);
    }

    public void setCured(boolean type) {
        this.entityData.set(DATA_CURED, type);
    }

    public boolean isRunning() {
        return this.entityData.get(DATA_RUNNING);
    }

    public void setRunning(boolean running) {
        this.entityData.set(DATA_RUNNING, running);
    }

    @Nullable
    public BlockPos getWantedPos() {
        return this.entityData.get(DATA_WANTED_POS).orElse(null);
    }

    public void setWantedPos(Optional<BlockPos> wantedPos) {
        this.entityData.set(DATA_WANTED_POS, wantedPos);
    }

    public boolean isPlanting() {
        return this.entityData.get(DATA_PLANTING);
    }

    public void setPlanting(boolean plan) {
        this.entityData.set(DATA_PLANTING, plan);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("cured", this.isCured());
        pCompound.putBoolean("running", this.isRunning());
        pCompound.putBoolean("planting", this.isPlanting());
        if (getWantedPos() != null) {
            pCompound.put("wanted_pos", NbtUtils.writeBlockPos(getWantedPos()));
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setCured(pCompound.getBoolean("cured"));
        this.setRunning(pCompound.getBoolean("running"));
        this.setPlanting(pCompound.getBoolean("planting"));
        this.setWantedPos(Optional.of(NbtUtils.readBlockPos(pCompound.getCompound("wanted_pos"))));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_CURED, false);
        this.entityData.define(DATA_RUNNING, false);
        this.entityData.define(DATA_WANTED_POS, Optional.empty());
        this.entityData.define(DATA_PLANTING, false);
    }

    @Override
    protected void registerGoals() {
        /* else if (this.getBoblingType() == Type.CURED) {
            this.goalSelector.addGoal(3, new TemptGoal(this, 0.9F, itemStack ->
                    itemStack.is(ModItems.JAR_OF_BONMEEL), false));
        }*/

        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new BoblingAttackPlayerGoal(this, 1.5F, false));
        this.goalSelector.addGoal(2, new BoblingAvoidPlayerGoal<>(this, Player.class, 16.0F, 1.0F, 1.3F));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 0.8F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, false));
    }

    @Override
    protected void actuallyHurt(DamageSource pDamageSource, float pDamageAmount) {
        super.actuallyHurt(pDamageSource, pDamageAmount);
        if (this.isRunning() && pDamageSource.is(DamageTypes.PLAYER_ATTACK) && !isCured()) {
            var r = 2.5;
            var checkR = 1.5;
            Set<Vec3> set = new HashSet<>();

            for (double theta = 0; theta <= Mth.TWO_PI * 3; theta += Mth.TWO_PI / random.nextIntBetweenInclusive(2, 5)) {
                generateProjectile(set, r, theta + this.level().random.nextDouble(), checkR);
            }
        }

        if (!this.isRunning() && pDamageSource.is(DamageTypes.PLAYER_ATTACK)) {
            this.setRunning(true);
        }
    }

    @Override
    protected int calculateFallDamage(float pFallDistance, float pDamageMultiplier) {
        if (this.tickCount <= 60) return 0;
        return super.calculateFallDamage(pFallDistance, pDamageMultiplier);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.isPlanting()) {
            this.plantingProgress++;
            if (plantingProgress >= MAX_PLANTING_PROGRESS) {
                this.finalizePlanting = true;
                this.setPlanting(false);
            }
        }

        if(this.level().isClientSide) {
            this.setupAnimationStates();
        }
    }

    @Override
    public void aiStep() {
        for (WrappedGoal wrappedGoal : goalSelector.getAvailableGoals()) {
            if(wrappedGoal == null) {
                MoreSnifferFlowers.LOGGER.error("NULL");
            }
        }
        for (WrappedGoal wrappedGoal : targetSelector.getAvailableGoals()) {
            if(wrappedGoal == null) {
                MoreSnifferFlowers.LOGGER.error("NULL");
            }
        }

        super.aiStep();

        if (canPlant()) {
            this.plantingProgress = 0;
            this.setPlanting(true);
            this.removeFreeWill();
            this.setYRot(this.getDirection().toYRot());
        }

        if(this.finalizePlanting && this.isAlive()) {
            var blockPos = BlockPos.containing(this.position()).relative(this.getDirection());
            this.level().setBlockAndUpdate(blockPos, ModBlocks.CORRUPTED_SAPLING.get().defaultBlockState());
            this.discard();
        }
    }

    private boolean canPlant() {
        return
                this.isRunning() &&
                        this.isAlive() &&
                        !this.isPlanting() &&
                        this.getWantedPos() != null &&
                        this.position().closerThan(getWantedPos().getCenter(), 0.75F);
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 40;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }

        if(this.isPlanting()) {
            this.idleAnimationState.stop();
            if(!this.plantingAnimationState.isStarted()) {
                this.plantingAnimationState.start(this.tickCount);
            }
        } else {
            this.plantingAnimationState.stop();
        }
    }

    @Override
    protected InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pHand);

        if (itemStack.is(ModItems.VIVICUS_ANTIDOTE.get()) && this.isCured() == false) {
            this.setCured(true);

            particles(new DustParticleOptions(Vec3.fromRGB24(7118872).toVector3f(), 1));
            itemStack.shrink(1);

            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }

        return super.mobInteract(pPlayer, pHand);
    }

    private void particles(ParticleOptions particle) {
        for(int i = 0; i <= 30; i++) {
            double d0 = this.random.nextGaussian() * 0.02;
            double d1 = this.random.nextGaussian() * 0.02;
            double d2 = this.random.nextGaussian() * 0.02;
            this.level().addParticle(particle, this.getRandomX(1), this.getRandomY() + 0.5D, this.getRandomZ(1), d0, d1, d2);
        }
    }

    @Override
    public boolean removeWhenFarAway(double pDistanceToClosestPlayer) {
        return false;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25F)
                .add(Attributes.ATTACK_DAMAGE, 3.0);
    }


    private void generateProjectile(Set<Vec3> set, double r, double theta, double checkR) {
        var x = this.getX() + r * Mth.cos((float) theta);
        var yx = this.getY() + r * Mth.sin((float) theta);
        var yz = this.getY() + r * Mth.cos((float) theta);
        var z = this.getZ() + r * Mth.sin((float) theta);

        createAndAddProjectile(set, checkR, new Vec3(x, yo, z));
        createAndAddProjectile(set, checkR, new Vec3(x, yx, zo));
        createAndAddProjectile(set, checkR, new Vec3(xo, yz, z));
    }

    private void createAndAddProjectile(Set<Vec3> set, double checkR, Vec3 vec3) {
        AABB aabb = AABB.ofSize(vec3, checkR, checkR, checkR);
        if (set.stream().noneMatch(aabb::contains)) {
            CorruptedProjectile projectile = new CorruptedProjectile(this.level());
            projectile.setPos(vec3);
            Vec3 dir = new Vec3(projectile.getX() - this.getX(), projectile.getY() - this.getY(), projectile.getZ() - this.getZ()).normalize();
            projectile.setDeltaMovement(dir);
            this.level().addFreshEntity(projectile);
            set.add(vec3);
        }
    }

/*    public enum Type implements StringRepresentable {
        CORRUPTED(0, "corrupted"),
        CURED(1, "cured");

        public static final IntFunction<Type> BY_ID = ByIdMap.continuous(Type::id, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
        private final int id;
        private final String name;

        Type(int pId, String name) {
            this.id = pId;
            this.name = name;
        }

        public int id() {
            return this.id;
        }

        @Override
        public String getSerializedName() {
            return name;
        }
    }*/
}
