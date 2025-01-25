package net.nikdo53.moresnifferflowers.init;

import io.github.fabricators_of_create.porting_lib.util.LazyRegistrar;
import io.github.fabricators_of_create.porting_lib.util.RegistryObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.nikdo53.moresnifferflowers.MoreSnifferFlowers;
import net.nikdo53.moresnifferflowers.blockentities.*;

public class ModBlockEntities {
    public static LazyRegistrar<BlockEntityType<?>> BLOCK_ENTITIES = LazyRegistrar.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, MoreSnifferFlowers.MOD_ID);

    public static final RegistryObject<BlockEntityType<XbushBlockEntity>> XBUSH = BLOCK_ENTITIES.register("xbush", () -> BlockEntityType.Builder.of(XbushBlockEntity::new, ModBlocks.AMBUSH_TOP.get(), ModBlocks.GARBUSH_TOP.get()).build(null));
    public static final RegistryObject<BlockEntityType<GiantCropBlockEntity>> GIANT_CROP = BLOCK_ENTITIES.register("giant_crop", () -> BlockEntityType.Builder.of(GiantCropBlockEntity::new, ModBlocks.GIANT_CARROT.get(), ModBlocks.GIANT_POTATO.get(), ModBlocks.GIANT_NETHERWART.get(), ModBlocks.GIANT_BEETROOT.get(), ModBlocks.GIANT_WHEAT.get()).build(null));
    public static final RegistryObject<BlockEntityType<CropressorBlockEntity>> CROPRESSOR = BLOCK_ENTITIES.register("cropressor", () -> BlockEntityType.Builder.of(CropressorBlockEntity::new, ModBlocks.CROPRESSOR_OUT.get()).build(null));
    public static final RegistryObject<BlockEntityType<RebrewingStandBlockEntity>> REBREWING_STAND = BLOCK_ENTITIES.register("rebrewing_stand", () -> BlockEntityType.Builder.of(RebrewingStandBlockEntity::new, ModBlocks.REBREWING_STAND_TOP.get()).build(null));
    public static final RegistryObject<BlockEntityType<DyespriaPlantBlockEntity>> DYESPRIA_PLANT = BLOCK_ENTITIES.register("dyespria_plant", () -> BlockEntityType.Builder.of(DyespriaPlantBlockEntity::new, ModBlocks.DYESPRIA_PLANT.get()).build(null));
    public static final RegistryObject<BlockEntityType<BoblingSackBlockEntity>> BOBLING_SACK = BLOCK_ENTITIES.register("bobling_sack", () -> BlockEntityType.Builder.of(BoblingSackBlockEntity::new, ModBlocks.BOBLING_SACK.get()).build(null));
    public static final RegistryObject<BlockEntityType<CorruptedSludgeBlockEntity>> CORRUPTED_SLUDGE = BLOCK_ENTITIES.register("corrupted_sludge", () -> BlockEntityType.Builder.of(CorruptedSludgeBlockEntity::new, ModBlocks.CORRUPTED_SLUDGE.get()).build(null));
    public static final RegistryObject<BlockEntityType<BondripiaBlockEntity>> BONDRIPIA = BLOCK_ENTITIES.register("bondripia", () -> BlockEntityType.Builder.of(BondripiaBlockEntity::new, ModBlocks.BONDRIPIA.get(), ModBlocks.ACIDRIPIA.get()).build(null));
    
    public static final RegistryObject<BlockEntityType<ModSignBlockEntity>> MOD_SIGN = BLOCK_ENTITIES.register("mod_sign", () -> BlockEntityType.Builder.of(ModSignBlockEntity::new, ModBlocks.CORRUPTED_SIGN.get(), ModBlocks.CORRUPTED_WALL_SIGN.get()).build(null));
    public static final RegistryObject<BlockEntityType<VivicusSignBlockEntity>> VIVICUS_SIGN = BLOCK_ENTITIES.register("vivicus_sign", () -> BlockEntityType.Builder.of(VivicusSignBlockEntity::new, ModBlocks.VIVICUS_SIGN.get(), ModBlocks.VIVICUS_WALL_SIGN.get()).build(null));
    public static final RegistryObject<BlockEntityType<ModHangingSignBlockEntity>> MOD_HANGING_SIGN = BLOCK_ENTITIES.register("mod_hanging_sign", () -> BlockEntityType.Builder.of(ModHangingSignBlockEntity::new, ModBlocks.CORRUPTED_HANGING_SIGN.get(), ModBlocks.CORRUPTED_WALL_HANGING_SIGN.get()).build(null));
    public static final RegistryObject<BlockEntityType<VivicusHangingSignBlockEntity>> VIVICUS_HANGING_SIGN = BLOCK_ENTITIES.register("vivicus_hanging_sign", () -> BlockEntityType.Builder.of(VivicusHangingSignBlockEntity::new, ModBlocks.VIVICUS_HANGING_SIGN.get(), ModBlocks.VIVICUS_WALL_HANGING_SIGN.get()).build(null));
}
