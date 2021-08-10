package me.maximumpower55.mecha.data;

import static me.maximumpower55.mecha.api.utils.ResourceUtils.prefixPath;

import me.maximumpower55.mecha.MechaMod;
import net.devtech.arrp.api.RRPCallback;
import net.devtech.arrp.api.RRPPreGenEntrypoint;
import net.devtech.arrp.api.RuntimeResourcePack;
import net.devtech.arrp.json.blockstate.JState;
import net.devtech.arrp.json.loot.JLootTable;
import net.devtech.arrp.json.models.JModel;
import net.minecraft.resources.ResourceLocation;

public final class MechaResourceGen implements RRPPreGenEntrypoint {
    private static final RuntimeResourcePack pack = RuntimeResourcePack.create(MechaMod.id("pack").toString());

    private static final ResourceLocation[] CABLES = {MechaMod.id("basic_cable_block")};
    private static final ResourceLocation[] ORES = {MechaMod.id("tin")};
    private static final ResourceLocation[] ITEMS = {MechaMod.id("raw_tin"), MechaMod.id("tin_ingot")};

    private static final String BLOCK = new ResourceLocation("minecraft", "block").toString();
    private static final String ITEM = new ResourceLocation("minecraft", "item").toString();

    @Override
    public void pregen() {
        genLootTables();
        genBlockModels();
        genBlockStates();
        genItemModels();

        RRPCallback.AFTER_VANILLA.register(e -> e.add(pack));
    }

    private void genBlockStates() {
        for (ResourceLocation id : ORES) {
            id = new ResourceLocation(id.getNamespace(), id.getPath() + "_ore");
            pack.addBlockState(JState.state().add(JState.variant(JState.model(prefixPath(id, "block")))), id);
        }

        for (ResourceLocation id : CABLES) {
            pack.addBlockState(JState.state().add(JState.variant(JState.model(prefixPath(id, "block")))), id);
        }
    }

    private void genBlockItemModel(ResourceLocation id) {
        pack.addModel(JModel.model().parent(prefixPath(id, "block").toString()), prefixPath(id, "item"));
    }

    private void genBlockModels() {
        for (ResourceLocation id : ORES) {
            id = new ResourceLocation(id.getNamespace(), id.getPath() + "_ore");
            pack.addModel(JModel.model().parent("block/cube_all").textures(JModel.textures().var("all", prefixPath(id, "block").toString())), prefixPath(id, "block"));
            genBlockItemModel(id);
        }
    }

    private void genItemModels() {
        for (ResourceLocation id : ITEMS) {
            pack.addModel(JModel.model().parent("item/generated").textures(JModel.textures().layer0(prefixPath(id, "item").toString())), prefixPath(id, "item"));
        }
    }

    private void genLootTables() {
        for (ResourceLocation id : ORES) {
            pack.addLootTable(prefixPath(new ResourceLocation(id.getNamespace(), id.getPath() + "_ore"), "blocks"), JLootTable.loot(BLOCK).pool(JLootTable.pool().rolls(1).entry(JLootTable.entry().type(ITEM).name(new ResourceLocation(id.getNamespace(), "raw_" + id.getPath()).toString())).condition(JLootTable.predicate("minecraft:survives_explosion"))));
        }
    }
}
