package club.tranch.mycustoms;

import android.animation.Animator;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.*;
import android.support.design.widget.*;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.*;
import android.view.animation.*;
import android.widget.*;


public class LoginRegister extends FrameLayout {
    private Context mContext;
    private FloatingActionButton fab;
    private Button login_btn, register_btn, register_close;
    private EditText login_edit_first, login_edit_second, register_edit_first, register_edit_second, register_edit_thrid;
    private TextView login_title, register_title;
    private CardView loginCard, registerCard;

    private float radius;
    private int cx, cy;
    private int width, height;
    private Animator start, close;
    Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if(msg.what==1){
                start.start();
                registerCard.setVisibility(View.VISIBLE);
            }
            return true;
        }
    });

    public LoginRegister(Context context) {
        this(context, null);
    }

    public LoginRegister(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        findViews();
        //获取registerCard的宽高，直接getWidth是0
        registerCard.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
                , View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        height = registerCard.getMeasuredHeight();
        width = registerCard.getMeasuredWidth();
        //揭露动画 圆 的半径
        radius = (float) Math.sqrt(width * width + height * height)+100;
        fab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                fabHint(fab,500);
                fabShow(register_close,500);
                fab.setVisibility(View.GONE);
                cx = loginCard.getWidth();
                cy = fab.getHeight() / 2;
                //揭露动画
                start = ViewAnimationUtils.createCircularReveal(registerCard, cx, cy, 0, radius);
                start.setDuration(500);
                //等待fab消失动画结束再执行揭露动画
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(1);
                    }
                },500);
                cleanLogin();
            }
        });
        register_close.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                close = ViewAnimationUtils.createCircularReveal(registerCard, cx, cy, radius+100, 0);
                close.setDuration(500);
                close.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        fabHint(register_close,500);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        registerCard.setVisibility(View.GONE);
                        fabShow(fab,500);
                        fab.setVisibility(View.VISIBLE);
                        cleanRegister();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }
                });
                close.start();
            }
        });

    }
/**/
    private void cleanLogin(){
        login_edit_first.setText("");
        login_edit_second.setText("");
    }

    private void cleanRegister(){
        register_edit_first.setText("");
        register_edit_second.setText("");
        register_edit_thrid.setText("");
    }

    private void fabHint(View fab,long duration) {
        AnimationSet animationSet = new AnimationSet(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);//从不透明到完全透明
        alphaAnimation.setDuration(duration);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1, 0.1f, 1,
                0.1f, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(duration);
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(duration);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(scaleAnimation);
        fab.startAnimation(animationSet);
    }

    private void fabShow(View fab,long duration) {
        AnimationSet animationSet = new AnimationSet(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(duration);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.1f, 1f, 0.1f,
                1f, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(duration);
        RotateAnimation rotateAnimation = new RotateAnimation(360, 0,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(duration);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(scaleAnimation);
        fab.startAnimation(animationSet);
    }

    private void findViews() {
        LayoutInflater.from(mContext).inflate(R.layout.merge, this);
        loginCard = (CardView) findViewById(R.id.login_card);
        registerCard = (CardView) findViewById(R.id.register_card);
        fab = (FloatingActionButton) findViewById(R.id.loginFab);
        login_btn = (Button) findViewById(R.id.login_btn);
        login_title = (TextView) findViewById(R.id.login_title);
        login_edit_first = (EditText) findViewById(R.id.login_first_text);
        login_edit_second = (EditText) findViewById(R.id.login_second_text);
        register_btn = (Button) findViewById(R.id.register_btn);
        register_close = (Button) findViewById(R.id.register_close);
        register_title = (TextView) findViewById(R.id.register_title);
        register_edit_first = (EditText) findViewById(R.id.register_first_text);
        register_edit_second = (EditText) findViewById(R.id.register_second_text);
        register_edit_thrid = (EditText) findViewById(R.id.register_third_text);

    }

    //region Setter
    public void setLoginTitle(String text) {
        this.login_title.setText(text);
    }

    public void setLoginFirstText(String text) {
        this.login_edit_first.setHint(text);
    }

    public void setLoginSecondText(String text) {
        this.login_edit_second.setText(text);
    }

    public void setLoginBtnText(String text) {
        this.login_btn.setText(text);
    }

    public void setRegisterTitle(String text) {
        this.register_title.setText(text);
    }

    public void setRegisterFirstText(String text) {
        this.register_edit_first.setText(text);
    }

    public void setRegisterSecondText(String text) {
        this.register_edit_second.setText(text);
    }

    public void setRegisterThridText(String text) {
        this.register_edit_thrid.setText(text);
    }

    public void setRegisterBtnText(String text) {
        this.register_btn.setText(text);
    }
    //endregion

    //region Getter
    public String getLoginTitle() {
        return login_title.getText().toString();
    }

    public String getLoginFirstText() {
        return login_edit_first.getText().toString();
    }

    public String getLoginSecondText() {
        return login_edit_second.getText().toString();
    }

    public String getLoginBtnText() {
        return login_btn.getText().toString();
    }

    public String getRegisterTtitle() {
        return register_title.getText().toString();
    }

    public String getRegisterFirstText() {
        return register_edit_first.getText().toString();
    }

    public String getRegisterSecondText() {
        return register_edit_second.getText().toString();
    }

    public String getRegisterThridText() {
        return register_edit_thrid.getText().toString();
    }

    public String getRegisterBtnText() {
        return register_btn.getText().toString();
    }
    //endregion
}
