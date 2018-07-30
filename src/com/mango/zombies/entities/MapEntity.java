package com.mango.zombies.entities;

public class MapEntity
{
	private String MapName;
	private String Description;
	private String DeleteKey;
	private boolean Enabled;
	private String Uuid;
	
	public MapEntity(String name)
	{
		MapName = name;
	}
	
	public void setName(String name)
	{
		this.MapName = name;
	}
	
	public String getName()
	{
		return MapName;
	}
	
	public void setDescription(String description)
	{
		this.Description = description;
	}
	
	public String getDescription()
	{
		return Description;
	}
	
	public void setDeleteKey(String deleteKey)
	{
		this.DeleteKey = deleteKey;
	}
	
	public String getDeleteKey()
	{
		return DeleteKey;
	}
	
	public void setEnabled(boolean enabled)
	{
		this.Enabled = enabled;
	}
	
	public boolean isEnabled()
	{
		return this.Enabled;
	}
	
	public void setUuid(String uuid)
	{
		this.Uuid = uuid;
	}
	
	public String getUuid()
	{
		return Uuid;
	}
}
