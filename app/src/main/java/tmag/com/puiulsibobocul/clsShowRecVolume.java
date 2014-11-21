package tmag.com.puiulsibobocul;

import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Environment;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created by Sam on 29/06/2014.
 */
public class clsShowRecVolume
{
    public static final int SAMPLE_RATE = 16000;

    public AudioRecord mRecorder;
    private File mRecording;
    public File mRecording_wav;
    private short[] mBuffer;


    Context context=null;
    boolean mIsRecording=false;
    View v = null;
    ProgressBar mProgressBar=null;


    public clsShowRecVolume(Context context, boolean mIsRecording, View v) {
        this.context = context;
        this.mIsRecording = mIsRecording;
        this.v = v;
        if(v instanceof ProgressBar)
        {
            mProgressBar=(ProgressBar) v;
        }
        else
            return;
    }

    public File getmRecording() {
        return mRecording_wav;
    }

    public void setmRecording_wav(File mRecording) {
        this.mRecording_wav = mRecording;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public boolean ismIsRecording() {
        return mIsRecording;
    }

    public void setmIsRecording(boolean mIsRecording) {
        this.mIsRecording = mIsRecording;
    }

    public View getV() {
        return v;
    }

    public void setV(View v) {
        this.v = v;
    }

public void start_recording()
{
    try {
      initRecorder();
        mRecorder.startRecording();
        mRecording = getFile("raw");

        startBufferedWrite(mRecording);
    } catch (IllegalStateException e) {
        e.printStackTrace();
    }
}

    public void stop_recording()
    {
        mRecorder.stop();
        File waveFile = getFile("wav");
        try {
            rawToWave(mRecording, waveFile);
        } catch (IOException e) {
            Log.i("RECORD", e.getMessage());
        }
        Log.i("Recorded to " + waveFile.getName(), "");

    }


    public void destroy()
    {
     mRecorder.release();
    }


    public void initRecorder() {
        int bufferSize = AudioRecord.getMinBufferSize(SAMPLE_RATE, AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_PCM_16BIT);
        mBuffer = new short[bufferSize];
        mRecorder = new AudioRecord(MediaRecorder.AudioSource.MIC, SAMPLE_RATE, AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_PCM_16BIT, bufferSize);
    }

    private void startBufferedWrite(final File file) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                DataOutputStream output = null;
                try {
                    output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
                    while (mIsRecording) {
                        double sum = 0;
                        int readSize = mRecorder.read(mBuffer, 0, mBuffer.length);
                        for (int i = 0; i < readSize; i++) {
                            output.writeShort(mBuffer[i]);
                            sum += mBuffer[i] * mBuffer[i];
                        }
                        if (readSize > 0) {
                            final double amplitude = sum / readSize;
                            mProgressBar.setProgress((int) Math.sqrt(amplitude));
                        }
                    }
                } catch (IOException e) {
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                } finally {
                    mProgressBar.setProgress(0);
                    if (output != null) {
                        try {
                            output.flush();
                        } catch (IOException e) {
                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT)
                                    .show();
                        } finally {
                            try {
                                output.close();
                            } catch (IOException e) {
                                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT)
                                        .show();
                            }
                        }
                    }
                }
            }
        }).start();
    }

    private void rawToWave(final File rawFile, final File waveFile) throws IOException {

        byte[] rawData = new byte[(int) rawFile.length()];
        DataInputStream input = null;
        try {
            input = new DataInputStream(new FileInputStream(rawFile));
            input.read(rawData);
        } finally {
            if (input != null) {
                input.close();
            }
        }

        DataOutputStream output = null;
        try {
            output = new DataOutputStream(new FileOutputStream(waveFile));
            // WAVE header
            // see http://ccrma.stanford.edu/courses/422/projects/WaveFormat/
            writeString(output, "RIFF"); // chunk id
            writeInt(output, 36 + rawData.length); // chunk size
            writeString(output, "WAVE"); // format
            writeString(output, "fmt "); // subchunk 1 id
            writeInt(output, 16); // subchunk 1 size
            writeShort(output, (short) 1); // audio format (1 = PCM)
            writeShort(output, (short) 1); // number of channels
            writeInt(output, SAMPLE_RATE); // sample rate
            writeInt(output, SAMPLE_RATE * 2); // byte rate
            writeShort(output, (short) 2); // block align
            writeShort(output, (short) 16); // bits per sample
            writeString(output, "data"); // subchunk 2 id
            writeInt(output, rawData.length); // subchunk 2 size
            // Audio data (conversion big endian -> little endian)
            short[] shorts = new short[rawData.length / 2];
            ByteBuffer.wrap(rawData).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().get(shorts);
            ByteBuffer bytes = ByteBuffer.allocate(shorts.length * 2);
            for (short s : shorts) {
                bytes.putShort(s);
            }
            output.write(bytes.array());
            mRecording_wav=new File(String.valueOf(waveFile));
        } finally {
            if (output != null) {
                output.close();
            }
        }
    }

    public File getFile(final String suffix) {
        Time time = new Time();
        time.setToNow();

       return new File(Environment.getExternalStorageDirectory(), time.format("%Y%m%d%H%M%S") + "." + suffix);
    }

    private void writeInt(final DataOutputStream output, final int value) throws IOException {
        output.write(value >> 0);
        output.write(value >> 8);
        output.write(value >> 16);
        output.write(value >> 24);
    }

    private void writeShort(final DataOutputStream output, final short value) throws IOException {
        output.write(value >> 0);
        output.write(value >> 8);
    }

    private void writeString(final DataOutputStream output, final String value) throws IOException {
        for (int i = 0; i < value.length(); i++) {
            output.write(value.charAt(i));
        }
    }

}
