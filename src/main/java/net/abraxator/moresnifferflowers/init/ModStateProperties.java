package net.abraxator.moresnifferflowers.init;

import net.minecraft.state.property.*;
import net.minecraft.util.DyeColor;
import net.minecraft.block.BlockState;

public class ModStateProperties {
    public static final IntProperty AGE_3 = IntProperty.of("age", 0, 3);
    public static final IntProperty AGE_8 = IntProperty.of("age", 0, 8);
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final BooleanProperty FLIPPED = BooleanProperty.of("flipped");
    public static final EnumProperty<DyeColor> COLOR = EnumProperty.of("color", DyeColor.class);
}
