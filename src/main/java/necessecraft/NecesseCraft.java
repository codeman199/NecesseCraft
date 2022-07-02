package necessecraft;

import necessecraft.items.*;
import necesse.engine.commands.CommandsManager;
import necesse.engine.modLoader.annotations.ModEntry;
import necesse.engine.registries.*;
import necesse.gfx.gameTexture.GameTexture;
import necesse.inventory.recipe.Ingredient;
import necesse.inventory.recipe.Recipe;
import necesse.inventory.recipe.Recipes;
import necesse.level.maps.biomes.Biome;

@ModEntry
public class NecesseCraft {

    public void init() {
        System.out.println("Now loading NecesseCraft");

        // Register out objects
        ObjectRegistry.registerObject("craftingtable", new CraftingTable(), 2, true);


        ItemRegistry.registerItem("woodenplank", new WoodenPlank(), 10, true);
    }

    public void postInit() {

    }

}
