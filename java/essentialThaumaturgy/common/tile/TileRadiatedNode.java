package essentialThaumaturgy.common.tile;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.util.EnumHelper;
import essentialThaumaturgy.common.utils.ThaumcraftHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.nodes.NodeModifier;
import thaumcraft.api.nodes.NodeType;
import thaumcraft.api.visnet.TileVisNode;

public class TileRadiatedNode extends TileVisNode implements IAspectContainer{

    public TileRadiatedNode()
    {
        auraBase = (new AspectList()).add(Aspect.AIR, 20).add(Aspect.FIRE, 20).add(Aspect.EARTH, 20).add(Aspect.WATER, 20).add(Aspect.ORDER, 20).add(Aspect.ENTROPY, 20);
        visBase = new AspectList();
        vis = new AspectList();
        nodeType = NodeType.NORMAL;
        nodeModifier = NodeModifier.values()[3];
        id = "blank";
        setupNode();
    }
    
    @Override
    public void updateEntity()
    {
    	try
    	{
    		if(this.vis.aspects.isEmpty())
    		{
    			this.vis.add(visBase);
    		}
    		else
    			this.vis.merge(visBase);
    		Class TileNodeStabilizer = Class.forName("thaumcraft.common.tiles.TileNodeStabilizer");
    		Class TileNodeConverter = Class.forName("thaumcraft.common.tiles.TileNodeConverter");
    		TileEntity te = worldObj.getTileEntity(xCoord, yCoord - 1, zCoord);
    		if(te.getClass().isAssignableFrom(TileNodeStabilizer))
    		{
    			Field count = TileNodeStabilizer.getField("count");
    			int c = count.getInt(te);
    			if(c < 37)
    			{
    				count.set(te, c+=1);
    			}
    		}
    		te = worldObj.getTileEntity(xCoord, yCoord + 1, zCoord);
    		if(te.getClass().isAssignableFrom(TileNodeConverter))
    		{
    			Field count = TileNodeConverter.getField("count");
    			int c = count.getInt(te);
    			if(c < 50)
    			{
    				count.set(te, c+=1);
    			}else
    			{
    				count.set(te, 51);
    			}
    		}
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		super.updateEntity();
    		return;
    	}
    	super.updateEntity();
    }
    
    @Override
    public boolean canUpdate()
    {
        return true;
    }
    
    public void setupNode()
    {
    	try
    	{
        visBase = new AspectList();
        Class researchManager = Class.forName("thaumcraft.common.lib.research.ResearchManager");
        Method reduceToPrimals = researchManager.getMethod("reduceToPrimals", AspectList.class,boolean.class);
        AspectList temp = (AspectList) reduceToPrimals.invoke(null, getAuraBase(),true);
        Aspect arr$[] = temp.getAspects();
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            Aspect aspect = arr$[i$];
            int amt = temp.getAmount(aspect);
            if(getNodeModifier() == NodeModifier.BRIGHT)
                amt = (int)((float)amt * 1.2F);
            if(getNodeModifier() == NodeModifier.PALE)
                amt = (int)((float)amt * 0.8F);
            if(getNodeModifier() == NodeModifier.FADING)
                amt = (int)((float)amt * 0.5F);
            if(getNodeType() == NodeType.UNSTABLE)
                amt += worldObj.rand.nextInt(5) - 2;
            if(amt >= 1)
                visBase.merge(aspect, amt);
        }

        this.parentChanged();
    	}catch(Exception e)
    	{
    		return;
    	}
    }
    
	@Override
    public void readFromNBT(NBTTagCompound i)
    {
		super.readFromNBT(i);
    }
	
	@Override
    public void writeToNBT(NBTTagCompound i)
    {
		super.writeToNBT(i);
    }
    
	@Override
    public void readCustomNBT(NBTTagCompound nbttagcompound)
    {
        id = nbttagcompound.getString("nodeId");
        setNodeType(NodeType.values()[nbttagcompound.getByte("type")]);
        byte mod = nbttagcompound.getByte("modifier");
        if(mod >= 0)
            setNodeModifier(NodeModifier.values()[mod]);
        else
            setNodeModifier(null);
        visBase.aspects.clear();
        NBTTagList tlist = nbttagcompound.getTagList("AEB", 10);
        for(int j = 0; j < tlist.tagCount(); j++)
        {
            NBTTagCompound rs = tlist.getCompoundTagAt(j);
            if(rs.hasKey("key"))
                visBase.add(Aspect.getAspect(rs.getString("key")), rs.getInteger("amount"));
        }

        getAuraBase().readFromNBT(nbttagcompound);
    }

	@Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setString("nodeId", id);
        nbttagcompound.setByte("type", (byte)getNodeType().ordinal());
        nbttagcompound.setByte("modifier", getNodeModifier() != null ? (byte)getNodeModifier().ordinal() : -1);
        NBTTagList tlist = new NBTTagList();
        nbttagcompound.setTag("AEB", tlist);
        Aspect arr$[] = visBase.getAspects();
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            Aspect aspect = arr$[i$];
            if(aspect != null)
            {
                NBTTagCompound f = new NBTTagCompound();
                f.setString("key", aspect.getTag());
                f.setInteger("amount", visBase.getAmount(aspect));
                tlist.appendTag(f);
            }
        }

        getAuraBase().writeToNBT(nbttagcompound);
    }

    public NodeType getNodeType()
    {
        return nodeType;
    }

    public void setNodeType(NodeType nodeType)
    {
        this.nodeType = nodeType;
    }
    
    public void setNodeModifier(NodeModifier nodeModifier)
    {
        this.nodeModifier = nodeModifier;
    }

    public NodeModifier getNodeModifier()
    {
        return nodeModifier;
    }
	
    public int consumeVis(Aspect aspect, int amount)
    {
        int drain = Math.min(vis.getAmount(aspect), amount);
        if(drain > 0)
            vis.reduce(aspect, drain);
        return drain;
    }
    
    public AspectList getAuraBase()
    {
        return auraBase;
    }
    
	@Override
	public AspectList getAspects() {
		// TODO Auto-generated method stub
		return visBase;
	}

	@Override
	public void setAspects(AspectList aspects) {
		auraBase = aspects;
	}

	@Override
	public boolean doesContainerAccept(Aspect tag) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int addToContainer(Aspect tag, int amount) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean takeFromContainer(Aspect tag, int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean takeFromContainer(AspectList ot) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doesContainerContainAmount(Aspect tag, int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doesContainerContain(AspectList ot) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int containerContains(Aspect tag) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRange() {
		// TODO Auto-generated method stub
		return 8;
	}

    public byte getAttunement()
    {
        return -1;
    }
	
	@Override
	public boolean isSource() {
		// TODO Auto-generated method stub
		return true;
	}

    private AspectList auraBase;
    AspectList visBase;
    AspectList vis;
    private NodeType nodeType;
    private NodeModifier nodeModifier;
    String id;
}
