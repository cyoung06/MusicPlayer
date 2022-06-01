package kr.syeyoung.musicplayer.data;

import lombok.Getter;
import org.quifft.QuiFFT;
import org.quifft.output.FFTFrame;
import org.quifft.output.FFTResult;
import org.quifft.output.FFTStream;
import org.quifft.params.WindowFunction;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class WavFileFFTFrameProvider implements FFTProvider {
    public WavFileFFTFrameProvider(File f) throws UnsupportedAudioFileException, IOException {
        result = new QuiFFT(f)
//                .windowSize(2048)
                .dBScale(false)
                .windowFunction(WindowFunction.RECTANGULAR)
//                .normalized(true)
                .fullFFT();
        System.out.println(result.fftParameters.isNormalized);
    }

    @Getter
    private FFTResult result;

    private FFTFrame preparedFftFrame;
    private int frame = 0;

    @Override
    public boolean prepareFrame() {
        if (frame >= result.fftFrames.length) {
            return false;
        }
        preparedFftFrame = result.fftFrames[frame++];
        return true;
    }

    public FFTFrame getFrame() {
        return preparedFftFrame;
    }
}
