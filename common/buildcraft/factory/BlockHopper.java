package buildcraft.factory;

import java.util.ArrayList;

import buildcraft.BuildCraftCore;
import buildcraft.mod_BuildCraftFactory;
import buildcraft.core.BlockBuildCraft;
import buildcraft.core.CoreProxy;
import buildcraft.core.GuiIds;
import buildcraft.core.IItemPipe;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public class BlockHopper extends BlockBuildCraft {

	public BlockHopper(int blockId) {
		super(blockId, Material.iron);
		setHardness(5F);
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileHopper();
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public int getRenderType() {
		return BuildCraftCore.blockByEntityModel;
	}

	@Override
	public int getBlockTextureFromSide(int par1) {
		return 1;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) {
		super.onBlockActivated(world, x, y, z, entityplayer, par6, par7, par8, par9);

		// Drop through if the player is sneaking
		if (entityplayer.isSneaking())
			return false;

		if (entityplayer.getCurrentEquippedItem() != null) {
			if (entityplayer.getCurrentEquippedItem().getItem() instanceof IItemPipe) {
				return false;
			}
		}

		if (!CoreProxy.isClient(world))
			entityplayer.openGui(mod_BuildCraftFactory.instance, GuiIds.HOPPER, world, x, y, z);

		return true;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void addCreativeItems(ArrayList itemList) {
		itemList.add(new ItemStack(this));
	}

}
