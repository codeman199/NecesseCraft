package necessecraft;

import necesse.engine.modifiers.ModifierValue;
import necesse.entity.mobs.buffs.BuffModifiers;
import necesse.inventory.item.Item;
import necesse.inventory.item.matItem.MatItem;
import necesse.inventory.item.placeableItem.consumableItem.food.FoodConsumableItem;
import necesse.inventory.item.trinketItem.SimpleTrinketItem;
import necesse.inventory.recipe.Tech;
import necesse.level.maps.levelData.settlementData.settler.Settler;
import necessecraft.items.*;
import necesse.engine.modLoader.annotations.ModEntry;
import necesse.engine.registries.*;
import necesse.inventory.recipe.Ingredient;
import necesse.inventory.recipe.Recipe;
import necesse.inventory.recipe.Recipes;

@ModEntry
public class NecesseCraft {
    public static Tech CRAFTING_TABLE;

    public void init() {
        System.out.println("Now loading NecesseCraft");

        // Register tech
        CRAFTING_TABLE = RecipeTechRegistry.registerTech("craftingtable");

        // Register out objects
        ObjectRegistry.registerObject("craftingtable", new CraftingTable(), 2, true);

        // Register Items
        ItemRegistry.registerItem("woodenplank", new MatItem(64, Item.Rarity.COMMON), 0.25f, true);
        ItemRegistry.registerItem("gunpowder", new MatItem(64, Item.Rarity.COMMON), 5, true);
        ItemRegistry.registerItem("goldenapple", new FoodConsumableItem(64, Item.Rarity.RARE, Settler.FOOD_GOURMET, 40, 480, new ModifierValue[]{new ModifierValue(BuffModifiers.REGEN, 3f), new ModifierValue(BuffModifiers.INCOMING_DAMAGE_MOD, -0.1f)}), 100, true);

        // Register Buffs
        BuffRegistry.registerBuff("totemofundyingbuff",new TotemOfUndyingBuff());

        // Register Trinkets
        ItemRegistry.registerItem("totemofundying", new SimpleTrinketItem(Item.Rarity.RARE, "totemofundyingbuff", 700), 500.0F, true);
    }

    public void postInit() {

        // Wooden Plank
        Recipes.registerModRecipe(new Recipe(
                "woodenplank",
                4,
                RecipeTechRegistry.NONE,
                new Ingredient[]{
                        new Ingredient("anylog", 1)
                }
        ).showAfter("craftingtable"));

        Recipes.registerModRecipe(new Recipe(
                "woodenplank",
                4,
                CRAFTING_TABLE,
                new Ingredient[]{
                        new Ingredient("anylog", 1)
                }
        ).showAfter("woodboat"));

        // Crafting Table
        Recipes.registerModRecipe(new Recipe(
                "craftingtable",
                1,
                RecipeTechRegistry.NONE,
                new Ingredient[]{
                        new Ingredient("woodenplank", 4)
                }
        ).showAfter("woodenplank"));

        Recipes.registerModRecipe(new Recipe(
                "craftingtable",
                1,
                CRAFTING_TABLE,
                new Ingredient[]{
                        new Ingredient("woodenplank", 4)
                }
        ).showAfter("woodenplank"));

        // Golden Apple
        Recipes.registerModRecipe(new Recipe(
                "goldenapple",
                1,
                CRAFTING_TABLE,
                new Ingredient[]{
                        new Ingredient("apple", 1),
                        new Ingredient("goldbar",8)
                }
        ).showAfter("craftingtable"));

        // TNT
        Recipes.registerModRecipe(new Recipe(
                "tnt",
                1,
                CRAFTING_TABLE,
                new Ingredient[]{
                        new Ingredient("gunpowder", 5),
                        new Ingredient("sandtile",4)
                }
        ).showAfter("goldenapple"));
    }
}
