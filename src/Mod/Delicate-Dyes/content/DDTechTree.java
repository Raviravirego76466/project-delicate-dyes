package Mod.DelicateDyes.content;

import arc.struct.*;
import mindustry.Vars;
import mindustry.content.*;
import mindustry.ctype.*;
import mindustry.game.Objectives.*;
import mindustry.type.*;

import static mindustry.content.Blocks.*;
import static mindustry.content.TechTree.*;
import static Mod.Delicate-Dyes.content.DDBlocks;

public class EUTechTree {
    public static TechNode context = null;

    public static void load() {
        addToNode(sporePress, () -> node(blueExtractor));
}}
