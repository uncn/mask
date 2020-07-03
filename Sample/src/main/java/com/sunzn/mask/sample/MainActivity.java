package com.sunzn.mask.sample;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import com.sunzn.mask.library.MaskDisplayHelper;
import com.sunzn.mask.library.MaskLayoutHelper;
import com.sunzn.mask.library.MaskLinearLayout;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MaskLinearLayout mTestLayout;
    private SeekBar mAlphaSeekBar;
    private SeekBar mElevationSeekBar;
    private SeekBar mRadiusSeekBar;
    private AppCompatTextView mAlphaTv;
    private AppCompatTextView mElevationTv;
    private AppCompatTextView mRadiusTv;
    private RadioGroup mHideRadiusGroup;

    private float mShadowAlpha = 0.25f;
    private int mShadowElevationDp = 14;
    private int mRadiusDp = 15;
    private int mRadius;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTestLayout = findViewById(R.id.layout_for_test);
        mAlphaSeekBar = findViewById(R.id.test_seekbar_alpha);
        mElevationSeekBar = findViewById(R.id.test_seekbar_elevation);
        mRadiusSeekBar = findViewById(R.id.test_seekbar_radius);
        mAlphaTv = findViewById(R.id.alpha_tv);
        mElevationTv = findViewById(R.id.elevation_tv);
        mRadiusTv = findViewById(R.id.radius_tv);
        mHideRadiusGroup = findViewById(R.id.hide_radius_group);
        mRadius = MaskDisplayHelper.dp2px(this, 1);

        findViewById(R.id.shadow_color_red).setOnClickListener(this);
        findViewById(R.id.shadow_color_blue).setOnClickListener(this);

        findViewById(R.id.radius_15dp).setOnClickListener(this);
        findViewById(R.id.radius_half_width).setOnClickListener(this);
        findViewById(R.id.radius_half_height).setOnClickListener(this);

        initLayout();
    }

    private void initLayout() {
        mAlphaSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mShadowAlpha = progress * 1f / 100;
                mAlphaTv.setText(String.format("alpha: %s", mShadowAlpha));
                mTestLayout.setRadiusAndShadow(mRadius, MaskDisplayHelper.dp2px(MainActivity.this, mShadowElevationDp), mShadowAlpha);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mElevationSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mShadowElevationDp = progress;
                mElevationTv.setText(String.format(Locale.getDefault(), "elevation: %ddp", progress));
                mTestLayout.setRadiusAndShadow(mRadius, MaskDisplayHelper.dp2px(MainActivity.this, mShadowElevationDp), mShadowAlpha);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mRadiusSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mRadiusDp = progress;
                mRadius = MaskDisplayHelper.dp2px(MainActivity.this, progress);
                mRadiusTv.setText(String.format(Locale.getDefault(), "radius: %ddp", progress));
                mTestLayout.setRadius(mRadius);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mAlphaSeekBar.setProgress((int) (mShadowAlpha * 100));
        mElevationSeekBar.setProgress(mShadowElevationDp);
        mRadiusSeekBar.setProgress(mRadiusDp);

        mHideRadiusGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.hide_radius_none:
                        mTestLayout.setRadius(mRadius, MaskLayoutHelper.HIDE_RADIUS_SIDE_NONE);
                        break;
                    case R.id.hide_radius_left:
                        mTestLayout.setRadius(mRadius, MaskLayoutHelper.HIDE_RADIUS_SIDE_LEFT);
                        break;
                    case R.id.hide_radius_top:
                        mTestLayout.setRadius(mRadius, MaskLayoutHelper.HIDE_RADIUS_SIDE_TOP);
                        break;
                    case R.id.hide_radius_bottom:
                        mTestLayout.setRadius(mRadius, MaskLayoutHelper.HIDE_RADIUS_SIDE_BOTTOM);
                        break;
                    case R.id.hide_radius_right:
                        mTestLayout.setRadius(mRadius, MaskLayoutHelper.HIDE_RADIUS_SIDE_RIGHT);
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shadow_color_red:
                mTestLayout.setShadowColor(0xffff0000);
                break;
            case R.id.shadow_color_blue:
                mTestLayout.setShadowColor(0xff0000ff);
                break;
            case R.id.radius_15dp:
                mRadius = MaskDisplayHelper.dp2px(this, 15);
                mTestLayout.setRadius(mRadius);
                break;
            case R.id.radius_half_width:
                mRadius = MaskLayoutHelper.RADIUS_OF_HALF_VIEW_WIDTH;
                mTestLayout.setRadius(mRadius);
                break;
            case R.id.radius_half_height:
                mRadius = MaskLayoutHelper.RADIUS_OF_HALF_VIEW_HEIGHT;
                mTestLayout.setRadius(mRadius);
                break;
        }
    }

}