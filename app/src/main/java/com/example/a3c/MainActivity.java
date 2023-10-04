package com.example.a3c;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileDescriptor;
import java.io.IOException;

public class MainActivity<intsetid> extends AppCompatActivity {
    //读写权限
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET
    };
    //请求状态码
    private static int REQUEST_PERMISSION_CODE = 1;
    //    private Button btn1;
//    private TextView text;
//    private TextView jump;
//    private TextView password;
//    private AssetManager assetManager;
//    private MediaPlayer player = null;
    private TextView Recode;
    //是否使用特殊的标题栏背景颜色，android5.0以上可以设置状态栏背景色，如果不使用则使用透明色值
    protected boolean useThemestatusBarColor = false;
    //是否使用状态栏文字和图标为暗色，如果状态栏采用了白色系，则需要使状态栏和图标为暗色，android6.0以上可以设置
    protected boolean useStatusBarColor = true;
    private MediaPlayer mediaPlayer;
    private AssetManager path;
    private ImageView img;
    private Boolean isPause=false;
    String str;
    FileDescriptor filedescriptor;
    private ObjectAnimator objAnim = null;
    private ImageView sport_start,sport_stop;
    private ImageView sport_anmi;
    private RotateAnimation rotateAnimation;
    private boolean isStopAnim = false;
    private float currentValue = 0f;
    private AlphaAnimation animation2;


    public static AppCompatActivity getIntance()
    {
        return new AppCompatActivity();
    }


//    public String getHTML(String url) {
//        try {
//            URL newUrl = new URL(url);
//            URLConnection connect = newUrl.openConnection();
//            DataInputStream dis = new DataInputStream(connect.getInputStream());
//            BufferedReader in = new BufferedReader(new InputStreamReader(dis, "UTF-8"));//目标页面编码为UTF-8
//            String html = "";
//            String readLine = null;
//            while ((readLine = in.readLine()) != null) {
//                html = html + readLine;
//            }
//            in.close();吧 
//            return html;
//        } catch (MalformedURLException me) {
//        } catch (IOException ioe) {
//        }
//        return null;
//    }


    @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_music);

        img=findViewById(R.id.image);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()&&!isPause){
                    mediaPlayer.pause();//暂停播放
                    isPause = true;
//                    ((Button)v).setText("继续");
//                    hint.setText("暂停播放音频...");
//                    button1.setEnabled(true);//“播放”按钮可用
                }else{
                    mediaPlayer.start();//继续播放
//                    ((Button)v).setText("暂停");
//                    hint.setText("正在播放音频...");
                    isPause = false;
//                    button1.setEnabled(false);//“播放”按钮不可用
                }
            }
        });


        View view = null;
        sport_stop= (ImageView) view.findViewById(R.id.sport_stop);
        sport_anmi= (ImageView) view.findViewById(R.id.sport_anmi);
        sport_start= (ImageView) view.findViewById(R.id.sport_start);
        animation2 = new AlphaAnimation(1.0f, 1.0f);
        animation2.setDuration(3000);
        animation2.setInterpolator(new LinearInterpolator());//不停顿
        animation2.setRepeatCount(-1);

        sport_start.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startAnimation();
                sport_stop.setVisibility(View.VISIBLE);
                sport_start.setVisibility(View.GONE);

            }
        });

        sport_stop.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                pauseAnimation();
                sport_stop.setVisibility(View.GONE);
                sport_start.setVisibility(View.VISIBLE);
            }
        });
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sport_fragment_layout,container,false);
    }


    /**
     * 开始动画
     * */

    public void startAnimation()
    {

        // 设置动画，从上次停止位置开始,这里是顺时针旋转360度
        objAnim = ObjectAnimator.ofFloat(sport_anmi, "Rotation",
                currentValue - 360, currentValue);
        // 设置持续时间
        objAnim.setDuration(3000);
        // 设置循环播放
        objAnim.setRepeatCount(-1);

        objAnim.setInterpolator(new LinearInterpolator());//不停顿

        // 设置动画监听
        objAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                // 监听动画执行的位置，以便下次开始时，从当前位置开始
                currentValue = (Float) animation.getAnimatedValue();
            }
        });
        objAnim.start();
        sport_anmi.startAnimation(animation2);
    }

    /**
     * 暂停动画
     * */
    public void pauseAnimation()
    {
        objAnim.cancel();
        sport_anmi.clearAnimation();// 清除此ImageView身上的动画
    }











    /***********************  动画  **********************/
        ImageView imageView = (ImageView) findViewById(R.id.imageview);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.circle);
        LinearInterpolator lin = new LinearInterpolator();//设置动画匀速运动
        animation.setInterpolator(lin);
        imageView.startAnimation(animation);
        /*********************  动画结束  ********************/
            mediaPlayer = new MediaPlayer();
            path = getAssets();
            mediaPlayer.reset();
                try {
                    AssetFileDescriptor fileDescriptor = path.openFd("123.mp3");
                    mediaPlayer.setDataSource(fileDescriptor);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (IOException e) {
                e.printStackTrace();
            }
    }

}


