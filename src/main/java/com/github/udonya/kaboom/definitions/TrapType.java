package com.github.udonya.kaboom.definitions;

import org.bukkit.Material;

public enum TrapType {
    Wood,
    Stone;
    public Material getMaterial(){
        switch (this) {
        case Wood:
            return Material.WOOD_PLATE;
        case Stone:
            return Material.STONE_PLATE;
        default:
            return null;
        }
    }
    public static TrapType getLandMineType(Material mat){
        switch (mat) {
        case WOOD_PLATE:
            return TrapType.Wood;
        case STONE_PLATE:
            return TrapType.Stone;
        default:
            return null;
        }
    }
}
