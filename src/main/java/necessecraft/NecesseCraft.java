package necessecraft;

import necesse.engine.modifiers.ModifierValue;
import necesse.entity.mobs.buffs.BuffModifiers;
import necesse.inventory.item.Item;
import necesse.inventory.item.placeableItem.consumableItem.food.FoodConsumableItem;
import necesse.inventory.recipe.Tech;
import necesse.level.maps.levelData.settlementData.settler.Settler;
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
    public static Tech CRAFTING_TABLE;

    public void init() {
        System.out.println("Now loading NecesseCraft");

        //Register tech
        CRAFTING_TABLE = RecipeTechRegistry.registerTech("craftingtable");

        // Register out objects
        ObjectRegistry.registerObject("craftingtable", new CraftingTable(), 2, true);


        ItemRegistry.registerItem("woodenplank", new WoodenPlank(), 1, true);
        ItemRegistry.registerItem("goldenapple", new FoodConsumableItem(64, Item.Rarity.RARE, Settler.FOOD_GOURMET, 40, 480, new ModifierValue[]{new ModifierValue(BuffModifiers.REGEN, 1f), new ModifierValue(BuffModifiers.INCOMING_DAMAGE_MOD, -0.5f)}), 100, true);
    }

    public void postInit() {
        Recipes.registerModRecipe(new Recipe(
                "woodenplank",
                4,
                RecipeTechRegistry.NONE,
                new Ingredient[]{
                        new Ingredient("anylog", 1)
                }
        ).showAfter("woodboat"));

        Recipes.registerModRecipe(new Recipe(
                "craftingtable",
                1,
                RecipeTechRegistry.NONE,
                new Ingredient[]{
                        new Ingredient("woodenplank", 4)
                }
        ).showAfter("woodenplank"));

        Recipes.registerModRecipe(new Recipe(
                "goldenapple",
                1,
                CRAFTING_TABLE,
                new Ingredient[]{
                        new Ingredient("apple", 1),
                        new Ingredient("goldbar",8)
                }
        ).showAfter("craftingtable"));
    }
}
