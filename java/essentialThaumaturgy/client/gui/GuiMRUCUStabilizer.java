package essentialThaumaturgy.client.gui;

import DummyCore.Client.GuiCommon;
import DummyCore.Client.GuiElement;
import ec3.api.ITEHasMRU;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;

public class GuiMRUCUStabilizer extends GuiCommon{

	public GuiMRUCUStabilizer(Container c, TileEntity tile) {
		super(c,tile);
		try
		{
			this.elementList.add((GuiElement) Class.forName("ec3.client.gui.element.GuiMRUStorage").getConstructor(int.class,int.class,ITEHasMRU.class).newInstance(7, 4, (ITEHasMRU) tile));
			this.elementList.add((GuiElement) Class.forName("ec3.client.gui.element.GuiBoundGemState").getConstructor(int.class,int.class,TileEntity.class,int.class).newInstance(88, 4, tile,0));
			this.elementList.add((GuiElement) Class.forName("ec3.client.gui.element.GuiBalanceState").getConstructor(int.class,int.class,TileEntity.class).newInstance(46, 31, tile));
			this.elementList.add((GuiElement) Class.forName("ec3.client.gui.element.GuiMRUState").getConstructor(int.class,int.class,ITEHasMRU.class,int.class).newInstance(25, 58, (ITEHasMRU)tile,0));
		}catch(Exception e)
		{
			e.printStackTrace();
			return;
		}
	}
	
	

}
