package org.ezengine.AL;

import static org.lwjgl.openal.AL10.*;

import java.net.URL;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.openal.AL;
import org.lwjgl.util.WaveData;

public class Audio {

	private static List<Integer> sources = new ArrayList<Integer>();

	public static void create() {
		try {
			AL.create();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int loadAudio(String wavFile) {
		WaveData w = WaveData.create(wavFile);
		int buffer = alGenBuffers();
		alBufferData(buffer, w.format, w.data, w.samplerate);
		w.dispose();
		int audioID = alGenSources();
		alSourcei(audioID, AL_BUFFER, buffer);
		sources.add(audioID);
		return audioID;
	}

	public static int loadAudio(URL wavFile) {
		WaveData w = WaveData.create(wavFile);
		int buffer = alGenBuffers();
		alBufferData(buffer, w.format, w.data, w.samplerate);
		w.dispose();
		int audioID = alGenSources();
		alSourcei(audioID, AL_BUFFER, buffer);
		sources.add(audioID);
		return audioID;
	}

	public static void setPitch(int source, float pitch) {
		alSourcef(source, AL_PITCH, pitch);
	}

	public static void setGain(int source, float gain) {
		alSourcef(source, AL_GAIN, gain);
	}

	public static void setLooping(int source, boolean loop) {
		if (loop) alSourcei(source, AL_LOOPING, AL_TRUE);
		else alSourcei(source, AL_LOOPING, AL_FALSE);
	}

	public static void setSourcePosVel(int source, FloatBuffer position, FloatBuffer velocity) {
		alSource(source, AL_POSITION, position);
		alSource(source, AL_VELOCITY, velocity);
	}

	public static void setListenerPosVelOri(FloatBuffer position, FloatBuffer velocity, FloatBuffer orientation) {
		alListener(AL_POSITION, position);
		alListener(AL_VELOCITY, velocity);
		alListener(AL_ORIENTATION, orientation);
	}

	public static void finish(int source) {
		while (alGetSourcei(source, AL_SOURCE_STATE) == AL_PLAYING) {
			try {
				Thread.sleep(1);
			} catch (Exception e) {}
		}
	}

	public static void playSound(int source) {
		alListener3f(AL_POSITION, 0, 0, 0);
		alSource3f(source, AL_POSITION, 0, 0, 0);
		alSourcePlay(source);
	}

	public static void stopSound(int source) {
		alSourceStop(source);
	}

	public static void deleteSound(int source) {
		alDeleteSources(source);
		sources.remove((Object) source);
	}

	public static void destroy() {
		for (int i : sources) {
			alDeleteSources(i);
		}
		AL.destroy();
	}

}
