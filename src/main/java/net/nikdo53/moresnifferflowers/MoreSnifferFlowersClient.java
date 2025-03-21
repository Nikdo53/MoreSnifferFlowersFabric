package net.nikdo53.moresnifferflowers;

import io.github.fabricators_of_create.porting_lib.event.common.AddPackFindersEvent;
import io.github.fabricators_of_create.porting_lib.resources.PathPackResources;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.nikdo53.moresnifferflowers.client.ClientEvents;
import net.nikdo53.moresnifferflowers.client.ModColorHandler;
import net.nikdo53.moresnifferflowers.client.model.ModModelLayerLocations;
import net.nikdo53.moresnifferflowers.client.model.block.BondripiaModel;
import net.nikdo53.moresnifferflowers.client.model.block.CropressorModel;
import net.nikdo53.moresnifferflowers.client.model.block.GiantCropModels;
import net.nikdo53.moresnifferflowers.client.model.entity.BoblingModel;
import net.nikdo53.moresnifferflowers.client.model.entity.CorruptedProjectileModel;
import net.nikdo53.moresnifferflowers.client.model.entity.DragonflyModel;
import net.nikdo53.moresnifferflowers.client.particle.*;
import net.nikdo53.moresnifferflowers.client.renderer.block.*;
import net.nikdo53.moresnifferflowers.client.renderer.entity.BoblingRenderer;
import net.nikdo53.moresnifferflowers.client.renderer.entity.CorruptedProjectileRenderer;
import net.nikdo53.moresnifferflowers.client.renderer.entity.DragonflyRenderer;
import net.nikdo53.moresnifferflowers.client.renderer.entity.ModBoatRenderer;
import net.nikdo53.moresnifferflowers.init.*;

public class MoreSnifferFlowersClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModColorHandler.onRegisterBlockColorHandlers();
        ModColorHandler.onRegisterItemColorHandlers();
        ModItemProperties.register();
        ModMenuTypes.renderScreens();
        onRegisterEntityRenderers();
        onRegisterBlockEntityRenderer();
        onRegisterParticles();
        onEntityRenderersRegisterLayerDefinitions();
        registerWoodTypes(ModWoodTypes.CORRUPTED);
        registerWoodTypes(ModWoodTypes.VIVICUS);
        registerRenderTypes();
        ClientEvents.init();
        AddPackFindersEvent.EVENT.register(MoreSnifferFlowersClient::addPackFinders);

    }

    private static void registerRenderTypes() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DYESPRIA_PLANT.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DYESCRAPIA_PLANT.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GLOOMBERRY_VINE.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DAWNBERRY_VINE.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.REBREWING_STAND_BOTTOM.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.REBREWING_STAND_TOP.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CROPRESSOR_CENTER.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GIANT_BEETROOT.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GIANT_CARROT.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GIANT_NETHERWART.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GIANT_POTATO.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GIANT_WHEAT.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.AMBER_BLOCK.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GARNET_BLOCK.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BONMEELIA.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BONWILTIA.get(), RenderType.cutout());

    }

    public static void registerWoodTypes(WoodType woodType) {
        Sheets.SIGN_MATERIALS.put(woodType, Sheets.createSignMaterial(woodType));
        Sheets.HANGING_SIGN_MATERIALS.put(woodType, Sheets.createHangingSignMaterial(woodType));
    }


    public static void onEntityRenderersRegisterLayerDefinitions() {
        //ENTITY
        EntityModelLayerRegistry.registerModelLayer(ModModelLayerLocations.BOBLING, BoblingModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayerLocations.DRAGONFLY, DragonflyModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayerLocations.CORRUPTED_PROJECTILE, CorruptedProjectileModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayerLocations.CORRUPTED_BOAT_LAYER, BoatModel::createBodyModel);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayerLocations.CORRUPTED_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayerLocations.VIVICUS_BOAT_LAYER, BoatModel::createBodyModel);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayerLocations.VIVICUS_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);

        //BLOCK
        EntityModelLayerRegistry.registerModelLayer(ModModelLayerLocations.GIANT_CARROT, GiantCropModels::createGiantCarrotLayer);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayerLocations.GIANT_POTATO, GiantCropModels::createGiantPotatoLayer);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayerLocations.GIANT_NETHERWART, GiantCropModels::createNetherwartLayer);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayerLocations.GIANT_BEETROOT, GiantCropModels::createBeetrootLayer);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayerLocations.GIANT_WHEAT, GiantCropModels::createWheatLayer);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayerLocations.CROPRESSOR, CropressorModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayerLocations.BONDRIPIA, BondripiaModel::createBodyLayer);
    }

    public static void onRegisterEntityRenderers() {
        EntityRendererRegistry.register(ModEntityTypes.BOBLING.get(), BoblingRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.DRAGONFLY.get(), DragonflyRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.CORRUPTED_SLIME_BALL.get(), CorruptedProjectileRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.MOD_CORRUPTED_BOAT.get(), pContext -> new ModBoatRenderer(pContext, false));
        EntityRendererRegistry.register(ModEntityTypes.MOD_CORRUPTED_CHEST_BOAT.get(), pContext -> new ModBoatRenderer(pContext, true));
        EntityRendererRegistry.register(ModEntityTypes.MOD_VIVICUS_BOAT.get(), pContext -> new ModBoatRenderer(pContext, false));
        EntityRendererRegistry.register(ModEntityTypes.MOD_VIVICUS_CHEST_BOAT.get(), pContext -> new ModBoatRenderer(pContext, true));
        EntityRendererRegistry.register(ModEntityTypes.JAR_OF_ACID.get(), ThrownItemRenderer::new);
    }

    public static void onRegisterBlockEntityRenderer() {
        BlockEntityRenderers.register(ModBlockEntities.XBUSH.get(), AmbushBlockEntityRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.GIANT_CROP.get(), GiantCropBlockEntityRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.CROPRESSOR.get(), CropressorBlockEntityRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.DYESPRIA_PLANT.get(), DyespriaPlantBlockEntityRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.MOD_SIGN.get(), SignRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.VIVICUS_SIGN.get(), VivicusSignRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.MOD_HANGING_SIGN.get(), HangingSignRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.VIVICUS_HANGING_SIGN.get(), VivicusHangingSignRenderer::new);
        BlockEntityRenderers.register(ModBlockEntities.BONDRIPIA.get(), BondripiaBlockEntityRenderer::new);


    }

    public static void onRegisterParticles() {
        ParticleFactoryRegistry.getInstance().register(ModParticles.FLY.get(), FlyParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.CARROT.get(), CarrotParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.AMBUSH.get(), AmbushParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.GARBUSH.get(), AmbushParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.GIANT_CROP.get(), GiantCropParticle.Provider::new);

        ParticleFactoryRegistry.getInstance().register(ModParticles.BONDRIPIA_DRIP.get(), BondripiaParticle.BondripiaDripProvider::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.BONDRIPIA_FALL.get(), BondripiaParticle.BondripiaFallProvider::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.BONDRIPIA_LAND.get(), BondripiaParticle.BondripiaLandProvider::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.ACIDRIPIA_DRIP.get(), BondripiaParticle.AcidripiaDripProvider::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.ACIDRIPIA_FALL.get(), BondripiaParticle.AcidripiaFallProvider::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.ACIDRIPIA_LAND.get(), BondripiaParticle.AcidripiaLandProvider::new);

    }

    public static void addPackFinders(AddPackFindersEvent event) {
        ModContainer mf = FabricLoader.getInstance().getModContainer(MoreSnifferFlowers.MOD_ID).get();
        if(event.getPackType() == PackType.CLIENT_RESOURCES) {
            event.addRepositorySource(pOnLoad -> {
                Pack rtx = Pack.readMetaAndCreate(
                        MoreSnifferFlowers.loc("more_sniffer_flowers_rtx").toString(),
                        Component.literal("RTX More Sniffer Flowers"),
                        false,
                        pId -> new PathPackResources(pId, true, mf.findPath("resourcepacks/more_sniffer_flowers_rtx").get()),
                        PackType.CLIENT_RESOURCES,
                        Pack.Position.TOP,
                        PackSource.BUILT_IN);
                if(rtx != null) {
                    pOnLoad.accept(rtx);
                }
            });

            event.addRepositorySource(pOnLoad -> {
                Pack customStyleGUI = Pack.readMetaAndCreate(
                        MoreSnifferFlowers.loc("more_sniffer_flowers_boring").toString(),
                        Component.literal("Boring More Sniffer Flowers"),
                        false,
                        pId -> new PathPackResources(pId, true, mf.findPath("resourcepacks/more_sniffer_flowers_boring").get()),
                        PackType.CLIENT_RESOURCES,
                        Pack.Position.TOP,
                        PackSource.BUILT_IN);
                if(customStyleGUI != null) {
                    pOnLoad.accept(customStyleGUI);
                }
            });
        }
    }

}
