package com.extremegame.weedmatch;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.heyzap.sdk.ads.BannerAdView;
import com.heyzap.sdk.ads.HeyzapAds;
import com.heyzap.sdk.ads.IncentivizedAd;
import com.heyzap.sdk.ads.InterstitialAd;
import com.heyzap.sdk.ads.VideoAd;

import com.extremegame.weedmatch.R;

public class MainActivity extends AppCompatActivity {
    private Context context = this;
    private Activity activity = this; // must be an Activity
    private boolean isGdprConsentGiven = true;
    private BannerAdView bannerAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Khi bạn đã thu thập được sự đồng ý của người dùng, bạn có thể chuyển nó vào SDK bằng cách sử dụng API sau:
        HeyzapAds.setGdprConsent(isGdprConsentGiven, context);
        // Your Publisher ID is: 028cb104713cd354464737c34c135cb4
      // HeyzapAds.start("269112502fa776506ed91c526e14f7ce", activity);
        HeyzapAds.start("1e58991c974a2dab27f0c817d3ae7eb8", activity);

        // As early as possible, and after showing each video ad, call fetch
        VideoAd.fetch();

        // As early as possible, and after showing a rewarded video, call fetch
        IncentivizedAd.fetch();

        bannerAdView = new BannerAdView(activity);
        LinearLayout rootView = findViewById(R.id.rootView);
        rootView.addView(bannerAdView);
        // Load the banner ad.
        bannerAdView.load();

        findViewById(R.id.btnTest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 *Màn hình đầu tiên cho phép bạn cách ly một mạng để kiểm tra sự tích hợp của nó với SDK Heyzap.
                 *  Khi bạn chọn một mạng, bạn sẽ thấy nếu:
                 * Mạng được cài đặt chính xác.
                 * Mạng có thông tin đăng nhập hợp lệ trên trang tổng quan của bạn.
                 *  Mạng được bật trên trang tổng quan của bạn.
                 *  Để sử dụng Bộ thử nghiệm dàn xếp, hãy gọi phương thức này sau khi bắt đầu SD
                 */

                HeyzapAds.startTestActivity(activity);
            }
        });

        findViewById(R.id.btnShowAdsInterstitial).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // InterstitialAds are automatically fetched from our server
                InterstitialAd.display(activity);
            }
        });

        findViewById(R.id.btnShowAdsVideo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (VideoAd.isAvailable()) {
                    VideoAd.display(activity);
                }
            }
        });
        findViewById(R.id.btnShowAdsRewarded).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (IncentivizedAd.isAvailable()) {
                    IncentivizedAd.display(activity);
                }
            }
        });


    }
}
