package tmag.com.puiulsibobocul;

import android.annotation.TargetApi;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class FullScreenDialogBoxFragment extends DialogFragment {



    public static FullScreenDialogBoxFragment newInstance(int title) {
        FullScreenDialogBoxFragment frag = new FullScreenDialogBoxFragment();
        Bundle args = new Bundle();
        args.putInt("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



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
        setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_Holo_Panel);
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_full_screen_dlgbox, container, false);
        v.setBackground(new ColorDrawable(00000000));


        ImageButton imgbtnRec=(ImageButton)v.findViewById(R.id.imgbtnRec);
        ImageButton imgbtnPlay=(ImageButton)v.findViewById(R.id.imgbtnPlay);

        imgbtnRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnRecord_click(v);
            }
        });

        imgbtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPlay_click(v);
            }
        });

        return v;
    }


    @Override
    public void onDestroy() {
        //showRecVolume.destroy();
        super.onDestroy();
    }


    public void btnRecord_click(View v) {

//        if (!showRecVolume.ismIsRecording()) {
//            btnRecStop.setImageResource(R.drawable.icon_stop);
//            showRecVolume.setmIsRecording(true);
//            showRecVolume.start_recording();
//        } else {
//            btnRecStop.setImageResource(R.drawable.icon_rec);
//            showRecVolume.setmIsRecording(false);
//            showRecVolume.stop_recording();
//        }
        destroyItem();
    }

    public void btnPlay_click(View v) {
        destroyItem();
//        try {
//            MediaPlayer player = new MediaPlayer();
//            //Toast.makeText(getApplicationContext(), file_name, Toast.LENGTH_LONG).show();
//            File recFile= new File(showRecVolume.getmRecording().toString());
//
//            player.setDataSource( String.valueOf(recFile));//aRec.getFilePath());
//            player.prepare();
//            player.start();
//
//        } catch (IllegalArgumentException e) {
//            // TODO Auto-generated catch block
////            if(player.isPlaying()) {
////                player.stop();
////                player.release();
////            }
//            e.printStackTrace();
//        } catch (SecurityException e) {
//            // TODO Auto-generated catch block
////            if(player.isPlaying()) {
////                player.stop();
////                player.release();
////            }
//
////            e.printStackTrace();
//        } catch (IllegalStateException e) {
//            // TODO Auto-generated catch block
////            if(player.isPlaying()) {
////                player.stop();
////                player.release();
////            }
//
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
////            if(player.isPlaying()) {
////                player.stop();
////                player.release();
////            }
//            e.printStackTrace();
//        }

    }


    public void btnSave_click(View v) {
    //check if user has made a recording
//    if(showRecVolume.mRecording_wav !=null)
//        {
//            File sourceFile = new File(showRecVolume.getmRecording().toString());
//            File rootPath = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).toString()+ File.separator + "puiulsibobocul");
//            if(!rootPath.exists()) {
//                rootPath.mkdir();
//            }
//
//            String saveREC2Filename = "puiulsibobocul_rec_page_"+ bookCurrPage +1+".wav";
//            File recordingFile = new File(rootPath, saveREC2Filename);
//            Uri destUri = Uri.fromFile(recordingFile);
//
//            //Uri destUri = MainActivity.genUsage.save_to_file(saveREC2Filename, Environment.DIRECTORY_MUSIC, "myvoice", "wav");
//            File destFile = new File(destUri.getPath());
//            sourceFile.renameTo(destFile);
//            Toast.makeText(getActivity(),"Recording Saved",Toast.LENGTH_SHORT).show();
//        }
//        else
//        {
//            destroyItem();
//        }

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
