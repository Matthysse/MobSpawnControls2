package com.mcf.davidee.msc.packet.settings;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.mcf.davidee.msc.network.MSCPacketHandler;
import com.mcf.davidee.msc.packet.MSCPacket;

import cpw.mods.fml.common.network.Player;

public class SettingsPacket extends MSCPacket {

	public boolean readOnly;
	public boolean masterEnabled;
	public int[] caps;
	public int creatureFreq;

	@Override
	public MSCPacket readData(Object... data) {
		readOnly = (Boolean) data[0];
		masterEnabled = (Boolean) data[1];
		caps = (int[]) data[2];
		creatureFreq = (Integer) data[3];
		return this;
	}

	@Override
	public byte[] generatePacket() {
		ByteArrayDataOutput dat = ByteStreams.newDataOutput();
		dat.writeBoolean(readOnly);
		dat.writeBoolean(masterEnabled);
		for (int i = 0; i < caps.length; ++i)
			dat.writeInt(caps[i]);
		dat.writeInt(creatureFreq);
		return dat.toByteArray();
	}

	@Override
	public MSCPacket readPacket(ByteArrayDataInput dat) {
		readOnly = dat.readBoolean();
		masterEnabled = dat.readBoolean();
		caps = new int[4];
		for (int i = 0; i < caps.length; ++i)
			caps[i] = dat.readInt();
		creatureFreq = dat.readInt();
		return this;
	}

	@Override
	public void execute(MSCPacketHandler handler, Player player) {
		handler.handleSettings(this, player);
	}

}
