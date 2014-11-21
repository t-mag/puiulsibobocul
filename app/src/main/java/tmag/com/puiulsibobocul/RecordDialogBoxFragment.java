package tmag.com.puiulsibobocul;

import android.annotation.TargetApi;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.List;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class RecordDialogBoxFragment extends DialogFragment {


    clsShowRecVolume showRecVolume =null;

    private ProgressBar mProgressBar;
    ImageButton btnRecStop=null;
    ImageButton btnPlay=null;
    ImageButton btnSave=null;
    int bookCurrPage=0; // gets the current page from the 'title_activity'

    public View v = null;

    public static RecordDialogBoxFragment newInstance(int title) {
        RecordDialogBoxFragment frag = new RecordDialogBoxFragment();
        Bundle args = new Bundle();
        args.putInt("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bookCurrPage = getArguments().getInt("currPage");


//        // Pick a style based on the num.
//        int style = DialogFragment.STYLE_NORMAL, theme = 0;
//        switch ((mNum-1)%6) {
//            case 1: style = DialogFragment.STYLE_NO_TITLE; break;
//            case 2: style = DialogFragment.STYLE_NO_FRAME; break;
//            case 3: style = DialogFragment.STYLE_NO_INPUT; break;
//            case 4: style = DialogFragment.STYLE_NORMAL; break;
//            case 5: style = DialogFragment.STYLE_NORMAL; break;
//            case 6: style = DialogFragment.STYLE_NO_TITLE; break;
//            case 7: style = DialogFragment.STYLE_NO_FRAME; break;
//            case 8: style = DialogFragment.STYLE_NORMAL; break;
//        }
//        switch ((mNum-1)%6) {
//            case 4: theme = android.R.style.Theme_Holo; break;
//            case 5: theme = android.R.style.Theme_Holo_Light_Dialog; break;
//            case 6: theme = android.R.style.Theme_Holo_Light; break;
//            case 7: theme = android.R.style.Theme_Holo_Light_Panel; break;
//            case 8: theme = android.R.style.Theme_Holo_Light; break;
//        }
      //  setStyle(style, theme);
        setStyle(DialogFragment.STYLE_NO_FRAME,android.R.style.Theme_Holo_Dialog);
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_record_dlgbox, container, false);

        mProgressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        btnRecStop=(ImageButton)v.findViewById(R.id.btnRecStop);
        btnPlay=(ImageButton)v.findViewById(R.id.btnPlay);
        btnSave=(ImageButton)v.findViewById(R.id.btnSave);

        showRecVolume=new clsShowRecVolume(v.getContext(),false,mProgressBar);

        showRecVolume.initRecorder();
        showRecVolume.setmIsRecording(false);

        btnRecStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnRecStop_click(v);
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPlay_click(v);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSave_click(v);
                destroyItem();



            }
        });


        return v;
    }


    @Override
    public void onDestroy() {
        showRecVolume.destroy();
        super.onDestroy();
    }


    public void btnRecStop_click(View v) {

        if (!showRecVolume.ismIsRecording()) {
            btnRecStop.setImageResource(R.drawable.icon_stop);
            showRecVolume.setmIsRecording(true);
            showRecVolume.start_recording();
        } else {
            btnRecStop.setImageResource(R.drawable.icon_rec);
            showRecVolume.setmIsRecording(false);
            showRecVolume.stop_recording();
        }

    }

    public void btnPlay_click(View v) {

        try {
            MediaPlayer player = new MediaPlayer();
            //Toast.makeText(getApplicationContext(), file_name, Toast.LENGTH_LONG).show();
            File recFile= new File(showRecVolume.getmRecording().toString());

            player.setDataSource( String.valueOf(recFile));//aRec.getFilePath());
            player.prepare();
            player.start();

        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
//            if(player.isPlaying()) {
//                player.stop();
//                player.release();
//            }
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
//            if(player.isPlaying()) {
//                player.stop();
//                player.release();
//            }

//            e.printStackTrace();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
//            if(player.isPlaying()) {
//                player.stop();
//                player.release();
//            }

            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
//            if(player.isPlaying()) {
//                player.stop();
//                player.release();
//            }
            e.printStackTrace();
        }

    }


    public void btnSave_click(View v) {
    //check if user has made a recording
    if(showRecVolume.mRecording_wav !=null)
        {
            File sourceFile = new File(showRecVolume.getmRecording().toString());
            File rootPath = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).toString()+ File.separator + "puiulsibobocul");
            if(!rootPath.exists()) {
                rootPath.mkdir();
            }

            String saveREC2Filename = "puiulsibobocul_rec_page_"+ bookCurrPage +1+".wav";
            File recordingFile = new File(rootPath, saveREC2Filename);
            Uri destUri = Uri.fromFile(recordingFile);

            //Uri destUri = MainActivity.genUsage.save_to_file(saveREC2Filename, Environment.DIRECTORY_MUSIC, "myvoice", "wav");
            File destFile = new File(destUri.getPath());
            sourceFile.renameTo(destFile);
            Toast.makeText(getActivity(),"Recording Saved",Toast.LENGTH_SHORT).show();
        }
        else
        {
            destroyItem();
        }

    }


   // @Override
    public void destroyItem() {
        // TODO Auto-generated method stub

        FragmentManager manager = ((Fragment) this).getFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        trans.remove((Fragment) this);
        trans.commit();

       super.onDestroy();
    }


}
