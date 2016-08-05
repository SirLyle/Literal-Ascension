package com.jamieswhiteshirt.literalascension.common.init

import com.jamieswhiteshirt.literalascension.common.CreativeTab
import com.jamieswhiteshirt.literalascension.common.block.BlockChuteBase
import com.jamieswhiteshirt.literalascension.common.block.BlockChuteExit
import com.jamieswhiteshirt.literalascension.common.block.BlockChute
import com.jamieswhiteshirt.literalascension.common.block.BlockStepladder
import net.minecraft.block.material.Material
import net.minecraft.item.ItemBlock
import net.minecraftforge.fml.common.registry.GameRegistry

object LABlocks {
    val OAK_STEPLADDER: BlockStepladder = BlockStepladder({ LAItems.OAK_STEPLADDER }).setUnlocalizedName("literalascension:stepladderOak") as BlockStepladder
    val SPRUCE_STEPLADDER: BlockStepladder = BlockStepladder({ LAItems.SPRUCE_STEPLADDER }).setUnlocalizedName("literalascension:stepladderSpruce") as BlockStepladder
    val BIRCH_STEPLADDER: BlockStepladder = BlockStepladder({ LAItems.BIRCH_STEPLADDER }).setUnlocalizedName("literalascension:stepladderBirch") as BlockStepladder
    val JUNGLE_STEPLADDER: BlockStepladder = BlockStepladder({ LAItems.JUNGLE_STEPLADDER }).setUnlocalizedName("literalascension:stepladderJungle") as BlockStepladder
    val ACACIA_STEPLADDER: BlockStepladder = BlockStepladder({ LAItems.ACACIA_STEPLADDER }).setUnlocalizedName("literalascension:stepladderAcacia") as BlockStepladder
    val DARK_OAK_STEPLADDER: BlockStepladder = BlockStepladder({ LAItems.DARK_OAK_STEPLADDER }).setUnlocalizedName("literalascension:stepladderDarkOak") as BlockStepladder
    val IRON_STEPLADDER: BlockStepladder = BlockStepladder({ LAItems.IRON_STEPLADDER }).setUnlocalizedName("literalascension:stepladderIron") as BlockStepladder
    val OAK_CHUTE: BlockChute = BlockChute(Material.WOOD).setUnlocalizedName("literalascension:chuteOak") as BlockChute
    val OAK_CHUTE_EXIT: BlockChuteExit = BlockChuteExit(Material.WOOD).setUnlocalizedName("literalascension:chuteExitOak") as BlockChuteExit
    val SPRUCE_CHUTE: BlockChute = BlockChute(Material.WOOD).setUnlocalizedName("literalascension:chuteSpruce") as BlockChute
    val SPRUCE_CHUTE_EXIT: BlockChuteExit = BlockChuteExit(Material.WOOD).setUnlocalizedName("literalascension:chuteExitSpruce") as BlockChuteExit
    val BIRCH_CHUTE: BlockChute = BlockChute(Material.WOOD).setUnlocalizedName("literalascension:chuteBirch") as BlockChute
    val BIRCH_CHUTE_EXIT: BlockChuteExit = BlockChuteExit(Material.WOOD).setUnlocalizedName("literalascension:chuteExitBirch") as BlockChuteExit
    val JUNGLE_CHUTE: BlockChute = BlockChute(Material.WOOD).setUnlocalizedName("literalascension:chuteJungle") as BlockChute
    val JUNGLE_CHUTE_EXIT: BlockChuteExit = BlockChuteExit(Material.WOOD).setUnlocalizedName("literalascension:chuteExitJungle") as BlockChuteExit
    val ACACIA_CHUTE: BlockChute = BlockChute(Material.WOOD).setUnlocalizedName("literalascension:chuteAcacia") as BlockChute
    val ACACIA_CHUTE_EXIT: BlockChuteExit = BlockChuteExit(Material.WOOD).setUnlocalizedName("literalascension:chuteExitAcacia") as BlockChuteExit
    val DARK_OAK_CHUTE: BlockChute = BlockChute(Material.WOOD).setUnlocalizedName("literalascension:chuteDarkOak") as BlockChute
    val DARK_OAK_CHUTE_EXIT: BlockChuteExit = BlockChuteExit(Material.WOOD).setUnlocalizedName("literalascension:chuteExitDarkOak") as BlockChuteExit
    val STONE_CHUTE: BlockChute = BlockChute(Material.ROCK).setUnlocalizedName("literalascension:chuteStone") as BlockChute
    val STONE_CHUTE_EXIT: BlockChuteExit = BlockChuteExit(Material.ROCK).setUnlocalizedName("literalascension:chuteExitStone") as BlockChuteExit
    val GRANITE_CHUTE: BlockChute = BlockChute(Material.ROCK).setUnlocalizedName("literalascension:chuteGranite") as BlockChute
    val GRANITE_CHUTE_EXIT: BlockChuteExit = BlockChuteExit(Material.ROCK).setUnlocalizedName("literalascension:chuteExitGranite") as BlockChuteExit
    val DIORITE_CHUTE: BlockChute = BlockChute(Material.ROCK).setUnlocalizedName("literalascension:chuteDiorite") as BlockChute
    val DIORITE_CHUTE_EXIT: BlockChuteExit = BlockChuteExit(Material.ROCK).setUnlocalizedName("literalascension:chuteExitDiorite") as BlockChuteExit
    val ANDESITE_CHUTE: BlockChute = BlockChute(Material.ROCK).setUnlocalizedName("literalascension:chuteAndesite") as BlockChute
    val ANDESITE_CHUTE_EXIT: BlockChuteExit = BlockChuteExit(Material.ROCK).setUnlocalizedName("literalascension:chuteExitAndesite") as BlockChuteExit

    fun register() {
        registerStepladder(OAK_STEPLADDER, "oak")
        registerStepladder(SPRUCE_STEPLADDER, "spruce")
        registerStepladder(BIRCH_STEPLADDER, "birch")
        registerStepladder(JUNGLE_STEPLADDER, "jungle")
        registerStepladder(ACACIA_STEPLADDER, "acacia")
        registerStepladder(DARK_OAK_STEPLADDER, "dark_oak")
        registerStepladder(IRON_STEPLADDER, "iron")

        registerChute(OAK_CHUTE, OAK_CHUTE_EXIT, "oak")
        registerChute(SPRUCE_CHUTE, SPRUCE_CHUTE_EXIT, "spruce")
        registerChute(BIRCH_CHUTE, BIRCH_CHUTE_EXIT, "birch")
        registerChute(JUNGLE_CHUTE, JUNGLE_CHUTE_EXIT, "jungle")
        registerChute(ACACIA_CHUTE, ACACIA_CHUTE_EXIT, "acacia")
        registerChute(DARK_OAK_CHUTE, DARK_OAK_CHUTE_EXIT, "dark_oak")
        registerChute(STONE_CHUTE, STONE_CHUTE_EXIT, "stone")
        registerChute(GRANITE_CHUTE, GRANITE_CHUTE_EXIT, "granite")
        registerChute(DIORITE_CHUTE, DIORITE_CHUTE_EXIT, "diorite")
        registerChute(ANDESITE_CHUTE, ANDESITE_CHUTE_EXIT, "andesite")
    }

    private fun registerStepladder(stepladder: BlockStepladder, type: String) {
        GameRegistry.register(stepladder.setRegistryName("literalascension", "${type}_stepladder"))
    }

    private fun registerChute(chute: BlockChuteBase, chuteExit: BlockChuteBase, type: String) {
        chute.setCreativeTab(CreativeTab)
        chuteExit.setCreativeTab(CreativeTab)

        GameRegistry.register(chute.setRegistryName("literalascension", "${type}_chute"))
        GameRegistry.register(ItemBlock(chute).setRegistryName("literalascension", "${type}_chute"))
        GameRegistry.register(chuteExit.setRegistryName("literalascension", "${type}_chute_exit"))
        GameRegistry.register(ItemBlock(chuteExit).setRegistryName("literalascension", "${type}_chute_exit"))
    }
}
