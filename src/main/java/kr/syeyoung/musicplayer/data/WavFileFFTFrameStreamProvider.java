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

public class WavFileFFTFrameStreamProvider implements FFTProvider {
    public WavFileFFTFrameStreamProvider(File f) throws UnsupportedAudioFileException, IOException {
        result = new QuiFFT(f)
//                .windowSize(2048)
                .dBScale(false)
                .windowFunction(WindowFunction.RECTANGULAR)
//                .normalized(true)
                .fftStream();
        System.out.println(result.fftParameters.isNormalized);
    }

    @Getter
    private FFTStream result;

    private FFTFrame preparedFftFrame;

    @Override
    public boolean prepareFrame() {
        if (!result.hasNext()) {
            return false;
        }
        preparedFftFrame = result.next();
        return true;
    }

    public FFTFrame getFrame() {
        return preparedFftFrame;
    }
}
