package Mod.DelicateDyes.content;

import arc.Core;
import arc.Events;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.graphics.g2d.TextureRegion;
import arc.math.Angles;
import arc.math.Interp;
import arc.math.Mathf;
import arc.math.Rand;
import arc.math.geom.Geometry;
import arc.math.geom.Position;
import arc.math.geom.Rect;
import arc.math.geom.Vec2;
import arc.scene.style.TextureRegionDrawable;
import arc.scene.ui.Image;
import arc.scene.ui.layout.Stack;
import arc.scene.ui.layout.Table;
import arc.struct.ObjectMap;
import arc.struct.Seq;
import arc.util.*;
import mindustry.Vars;
import mindustry.content.*;
import mindustry.entities.Effect;
import mindustry.entities.Mover;
import mindustry.entities.Units;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.*;
import mindustry.entities.units.BuildPlan;
import mindustry.game.EventType;
import mindustry.game.Team;
import mindustry.gen.*;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.graphics.Trail;
import mindustry.type.*;
import mindustry.ui.Styles;
import mindustry.world.Block;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.distribution.DirectionLiquidBridge;
import mindustry.world.blocks.distribution.DuctBridge;
import mindustry.world.blocks.distribution.MassDriver;
import mindustry.world.blocks.heat.*;
import mindustry.world.blocks.liquid.ArmoredConduit;
import mindustry.world.blocks.liquid.LiquidBlock;
import mindustry.world.blocks.power.ConsumeGenerator;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.units.Reconstructor;
import mindustry.world.blocks.units.UnitAssemblerModule;
import mindustry.world.blocks.units.UnitFactory;
import mindustry.world.consumers.ConsumeLiquid;
import mindustry.world.consumers.ConsumeLiquidFlammable;
import mindustry.world.draw.*;
import mindustry.world.meta.*;

import static arc.graphics.g2d.Draw.*;
import static arc.math.Angles.randLenVectors;
import static mindustry.Vars.*;
import static mindustry.content.Fx.*;
import static mindustry.type.ItemStack.*;
public class DDBlocks {
    public static Block 
            blueExtractor = new AttributeCrafter("blueExtractor"){{
            requirements(Category.production, with(Items.lead, 60, Items.sporePod, 60));
            outputItem = new ItemStack(DDItems.blueberries, 1);
            craftTime = 30;
            size = 2;
            hasPower = true;
            attribute = stone;
            baseEfficiency = 0.8f;
            maxBoost = 1.5f;
            //minEfficiency = 0.001f;
            alwaysUnlocked = true;

            drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawRegion("-rot", -4){{
                        x = -2.8f;
                        y = 2.8f;
                    }},
                    new DrawRegion("-rot", 4){{
                        x = 2.8f;
                        y = 2.8f;
                    }},
                    new DrawRegion("-rot", -4){{
                        x = 2.8f;
                        y = -2.8f;
                    }},
                    new DrawRegion("-rot", 4){{
                        x = -2.8f;
                        y = -2.8f;
                    }},
                    new DrawDefault()
            );

            craftEffect = Fx.smokeCloud;
            updateEffect = new Effect(20, e -> {
                color(Pal.gray, Color.lightGray, e.fin());
                randLenVectors(e.id, 6, 3f + e.fin() * 6f, (x, y) -> Fill.square(e.x + x, e.y + y, e.fout() * 2f, 45));
            });

            consumePower(1f);
        }};
   BurplePress = new GenericCrafter("burple-press"){{
            requirements(Category.crafting, with(Items.sporePod, 60, DDItems.blueberries, 120));
            liquidCapacity = 60f;
            craftTime = 30f;
            outputItem = new ItemStack(DDItems.burple, 1);
            size = 3;
            scaledHealth = 60;
            hasLiquids = true;
            hasPower = true;
            craftEffect = new Effect(23, e -> {
                float scl = Math.max(e.rotation, 1);
                color(Tmp.c1.set(Pal.gray).mul(1.1f), Items.sporePod.color, e.fin());
                randLenVectors(e.id, 8, size * 8f + 4 * e.finpow() * scl, (x, y) -> Fill.circle(e.x + x, e.y + y, e.fout() * 3.5f * scl + 0.3f));
            }).layer(Layer.debris);
            updateEffect = sporeSlowed;
            drawer = new DrawMulti(new DrawDefault(), new DrawFrames(), new DrawLiquidRegion());

            consumeItem(Items.sporePod, 60, DDItems.blueberries, 120);
            consumePower(1.5f);
        }};
}
