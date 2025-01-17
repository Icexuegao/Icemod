package ice.world.blocks.crafting;

import ice.world.meta.stat.IceStat;
import mindustry.content.StatusEffects;
import mindustry.entities.Units;
import mindustry.graphics.Drawf;
import mindustry.type.StatusEffect;
import mindustry.world.blocks.production.GenericCrafter;

/**
 * 状态工厂类,一段时间给予周围己方单位状态
 */
public class EffectGenericCrafter extends GenericCrafter {
    /**
     * 每隔一段时间给予一次状态
     */
    public float effectTime;
    /**
     * 给予的状态
     */
    public StatusEffect statusEffect = StatusEffects.wet;
    /**
     * 状态时间 ,秒为单位
     */
    public float statusTime = 15;
    /**
     * 范围
     */
    public float radius = 40;

    public EffectGenericCrafter(String name) {
        super(name);
        buildType = EffectGenericCrafterBuild::new;
        effectTime = 60;
    }

    @Override
    public void setStats() {
        stats.add(IceStat.状态重载时间, effectTime / 60 + " seconds");
        stats.add(IceStat.状态效果, statusEffect.localizedName);
        stats.add(IceStat.状态持续时间, statusTime + " seconds");
        stats.add(IceStat.范围, "[" + radius / 8 + "] tile");
        super.setStats();
    }

    @Override
    public void drawOverlay(float x, float y, int rotation) {
        Drawf.circles(x, y, radius);
        super.drawOverlay(x, y, rotation);
    }

    public class EffectGenericCrafterBuild extends GenericCrafterBuild {
        @Override
        public void craft() {
            super.craft();
            Units.nearby(team, x, y, radius, (e)->e.apply(statusEffect, statusTime * 60));
            heal(maxHealth * 0.05f);
        }
    }
}
