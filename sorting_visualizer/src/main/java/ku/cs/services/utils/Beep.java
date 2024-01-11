package ku.cs.services.utils;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class Beep {
    public static float SAMPLE_RATE = 8000f;
    private static long prev = System.currentTimeMillis();
    private static final int SOUND_DELAY = 10;

    public static void tone(int hz, int msecs) throws LineUnavailableException {
        tone(hz, msecs, 1.0);
    }

    public static void tone(int hz, int msecs, double vol) {
        long start = System.currentTimeMillis();
        if (start - prev < SOUND_DELAY) return;
        if (msecs < SOUND_DELAY) msecs = SOUND_DELAY;
        prev = System.currentTimeMillis();
        final int finalMsecs = msecs;
        new Thread(() -> {
            byte[] buf = new byte[1];
            AudioFormat af =
                    new AudioFormat(
                            SAMPLE_RATE, // sampleRate
                            8,           // sampleSizeInBits
                            1,           // channels
                            true,        // signed
                            false);      // bigEndian
            SourceDataLine sdl = null;
            try {
                sdl = AudioSystem.getSourceDataLine(af);
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            }
            try {
                sdl.open(af);
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            }
            sdl.start();
            for (int i=0; i < finalMsecs*8; i++) {
                double angle = i / (SAMPLE_RATE / hz) * 2.0 * Math.PI;
                buf[0] = (byte)(Math.signum(Math.cos(angle)) * 127.0 * vol);
                sdl.write(buf,0,1);
            }
            sdl.drain();
            sdl.stop();
            sdl.close();
        }).start();
    }
}
