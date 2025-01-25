package net.nikdo53.moresnifferflowers.components;

import net.minecraft.core.BlockPos;

import java.util.Comparator;

public class EntityDistanceComparator implements Comparator<BlockPos> {
    private final int x, y, z;

    public EntityDistanceComparator(BlockPos p) {
        x = p.getX();
        y = p.getY();
        z = p.getZ();
    }

    private int distSq(BlockPos p) {
        int dx = (p.getX() - x);
        int dy = (p.getY() - y);
        int dz = (p.getZ() - z);
        return dx * dx + dy * dy + dz * dz;
    }

    @Override
    public int compare(BlockPos o1, BlockPos o2) {
        return distSq(o1) - distSq(o2);
    }
}

