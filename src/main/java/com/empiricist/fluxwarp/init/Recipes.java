package com.empiricist.fluxwarp.init;

import com.empiricist.fluxwarp.handler.ConfigurationHandler;
import com.empiricist.fluxwarp.utility.LogHelper;
import com.google.common.collect.Multimap;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

public class Recipes {

    //register recipes
    public static void init(){
        //to add vanilla shaped crafting recipe (can also use addShapedRecipe?)
        //GameRegistry.addRecipe(new ItemStack(ModItems.itemFan), "pp ", "spp", "ssp", 's', new ItemStack(Items.stick), 'p', new ItemStack(Items.paper));

        //to add vanilla shapeless crafting recipe
        GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.test), new ItemStack(Items.redstone), new ItemStack(Blocks.wool));

        //to add forge oredict shaped crafting recipe (can use oredict names or itemStacks)
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.fan), "pp ", "spp", "ssp", 's', "stickWood", 'p', new ItemStack(Items.paper)));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.staff), "fsf", " s ", "psp", 'f', new ItemStack(ModItems.fan), 's', "stickWood", 'p', new ItemStack(Items.paper)));

        if(ConfigurationHandler.vanillaRecipes){
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.dimensionAddress), " r ", "ibi", " g ", 'r', "dustRedstone", 'i', "ingotIron", 'b', new ItemStack(Items.glass_bottle), 'g', "ingotGold"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.warpcore), "gig", "pep", "iri", 'g', "ingotGold", 'i', "ingotIron", 'p', new ItemStack(Blocks.piston), 'e', new ItemStack(Items.ender_pearl), 'r', "dustRedstone"));
        }

    }

    public static void postInit(){
        if(ConfigurationHandler.thermalRecipes){

            String thermalID = "ThermalExpansion";
            String thermalI2 = "ThermalExpansion";

            if(!Loader.isModLoaded(thermalID)){
                LogHelper.warn(thermalID + " is not loaded!");
                List<ModContainer> mods = Loader.instance().getActiveModList();
                for( ModContainer mod : mods ){
                    LogHelper.info("ModID:" + mod.getModId());
                }
            }else{
                LogHelper.info("Dumping Registry to " + Minecraft.getMinecraft().mcDataDir);

                //GameData.dumpRegistry(Minecraft.getMinecraft().mcDataDir);
                Set example = GameData.getItemRegistry().getKeys();

                LogHelper.info("Found " + example.size() + " items");
                String name = "";
                for(Object o : example){
                    name = o.toString();
                    if(name.contains(thermalID)){
                        LogHelper.info(name);
                    }

                }

                example = Block.blockRegistry.getKeys();
                LogHelper.info("Found " + example.size() + " blocks");

                for(Object o : example){
                    name = o.toString();
                    if(name.contains("Thermal")){
                        LogHelper.info(name);
                    }

                }

            }

            if(exists(thermalID, "material") && exists(thermalID, "diagram") ){
                GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.dimensionAddress), new ItemStack(GameRegistry.findItem(thermalID, "material"), 1, 0), new ItemStack(GameRegistry.findItem(thermalID, "diagram"), 1, 1 ) ) );//servo and redprint
            }
            if(exists(thermalID, "frame") && exists(thermalID, "plate") && exists(thermalID, "material")){
                GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.warpcore), "sds", "ptp", "scs", 's', "ingotSilver", 'd', "dustEnderium", 'p', new ItemStack(GameRegistry.findBlock(thermalID, "plate"), 1, 2), 't', new ItemStack(GameRegistry.findBlock(thermalID, "frame"), 1, 11), 'c', new ItemStack(GameRegistry.findBlock(thermalID, "material"), 1, 1 )));//translocation plate, full tesseract frame, redstone reception coil
            }
        }
    }

    public static boolean exists(String modid, String name) {
        if ( GameRegistry.findItem(modid, name) != null) {
            LogHelper.info("Found item " + name + " for recipe");
            return true;
        }else if (GameRegistry.findBlock(modid, name) != null){
            LogHelper.info("Found block " + name + " for recipe");
            return true;
        }else{
            LogHelper.warn("Did not find " + name + " for recipe!");
            return false;
        }
    }
}
