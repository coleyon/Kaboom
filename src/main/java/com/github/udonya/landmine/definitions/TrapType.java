package com.github.udonya.landmine.definitions;

import org.bukkit.Material;

public enum TrapType {
    Wood,
    Stone,
    Iron,
    Gold;
    public Material getMaterial(){
        switch (this) {
        case Wood:
            return Material.WOOD_PLATE;
        case Iron:
            return Material.IRON_PLATE;
        case Stone:
            return Material.STONE_PLATE;
        case Gold:
            return Material.GOLD_PLATE;
        default:
            return null;
        }
    }
    public static TrapType getLandMineType(Material mat){
        switch (mat) {
        case WOOD_PLATE:
            return TrapType.Wood;
        case IRON_PLATE:
            return TrapType.Iron;
        case STONE_PLATE:
            return TrapType.Stone;
        case GOLD_PLATE:
            return TrapType.Gold;
        default:
            return null;
        }
    }
}
