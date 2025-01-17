package ice.world.blocks.liquid;

import arc.Core;
import arc.func.Func;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.scene.ui.layout.Table;
import arc.struct.OrderedMap;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.Vars;
import mindustry.game.Team;
import mindustry.gen.Building;
import mindustry.graphics.Pal;
import mindustry.type.Liquid;
import mindustry.ui.Bar;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.liquid.LiquidBlock;
import mindustry.world.meta.BlockGroup;
import mindustry.world.meta.Env;

@SuppressWarnings("unchecked")
public class MultipleLiquidBlock extends Block {

    public TextureRegion topRegion;
    public TextureRegion bottomRegion;

    public MultipleLiquidBlock(String name) {
        super(name);
        update = true;
        solid = true;
        hasLiquids = true;
        group = BlockGroup.liquids;
        outputsLiquid = true;
        envEnabled |= Env.space | Env.underwater;
        buildType = MultipleBlockBuild::new;
    }

    @Override
    public void load() {
        topRegion = Core.atlas.find(name + "-top");
        bottomRegion = Core.atlas.find(name + "-bottom");
        super.load();
    }

    @Override
    protected TextureRegion[] icons() {
        return new TextureRegion[]{bottomRegion, topRegion};
    }

    public class MultipleBlockBuild extends Building {
        Liquid liquid;
        OrderedMap<String, Func<Building, Bar>> icebarMap = new OrderedMap<>();

        public MultipleBlockBuild() {
        }

        public Iterable<Func<Building, Bar>> listBars() {
            return icebarMap.values();
        }

        public void addLiquidBar(Liquid liq) {
            iceAddBar("liquid-" + liq.name, entity -> !liq.unlockedNow() ? null : new Bar(() -> liq.localizedName, liq::barColor, () -> entity.liquids.get(liq) / liquidCapacity));
        }

        public <T extends Building> void iceAddBar(String name, Func<T, Bar> sup) {
            icebarMap.put(name, (Func<Building, Bar>) sup);
        }

        @Override
        public Building init(Tile tile, Team team, boolean shouldAdd, int rotation) {
            iceAddBar("health", entity -> new Bar("stat.health", Pal.health, entity::healthf).blink(Color.white));
            return super.init(tile, team, shouldAdd, rotation);
        }

        @Override
        public void displayBars(Table table) {
            for (Func<Building, Bar> bar : listBars()) {
                var result = bar.get(this);
                if (result == null) continue;
                table.add(result).growX();
                table.row();
            }
        }

        @Override
        public boolean acceptLiquid(Building source, Liquid liquid) {
            this.liquid = liquid;
            return true;
        }

        @Override
        public void write(Writes write) {
            super.write(write);
            if (liquid != null) {
                write.i(liquid.id);
            } else {
                write.i(-1);
            }

        }

        @Override
        public void read(Reads read, byte revision) {
            super.read(read, revision);
            int i = read.i();
            if (i != -1) {
                liquid = Vars.content.liquid(i);
            } else {
                liquid = null;
            }
        }

        @Override
        public void transferLiquid(Building next, float amount, Liquid liquid) {
            float flow = Math.min(next.block.liquidCapacity - next.liquids.get(liquid), amount);
            if (next.acceptLiquid(this, liquid)&&liquids.get(liquid)>flow) {
                next.handleLiquid(this, liquid, flow);
                this.liquids.remove(liquid, flow);
            }else {
                next.handleLiquid(this, liquid, liquids.get(liquid));
                this.liquids.remove(liquid, liquids.get(liquid));
            }
        }

        public Func<Building, Bar> b1;

        @Override
        public void drawSelect() {
            icebarMap.forEach(t -> {
                if (t.key.equals("health")) {
                    b1 = t.value;
                }
            });
            icebarMap.clear();
            icebarMap.put("health", b1);
            liquids.each((l, m) -> {
                if (icebarMap.get("liquid-" + l.name) == null) addLiquidBar(l);
            });
            super.drawSelect();
        }

        @Override
        public void draw() {
            float rotation = rotate ? rotdeg() : 0;
            Draw.rect(bottomRegion, x, y, rotation);
            if (liquid != null && liquids.get(liquid) > 1f) {
                LiquidBlock.drawTiledFrames(size, x, y, 0, 0, 0, 0, liquid, liquids.get(liquid) / liquidCapacity);
            }
            Draw.rect(topRegion, x, y, rotation);
        }
    }
}
