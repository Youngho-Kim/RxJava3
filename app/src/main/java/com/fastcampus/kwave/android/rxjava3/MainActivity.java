package com.fastcampus.kwave.android.rxjava3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

public class MainActivity extends AppCompatActivity {

    private Button btnPublish;
    private Button btnBehavior;
    private Button btnReplay;
    private Button btnAsync;
    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btnPublish = (Button) findViewById(R.id.btnPublish);
        btnBehavior = (Button) findViewById(R.id.btnBehavior);
        btnReplay = (Button) findViewById(R.id.btnReplay);
        btnAsync = (Button) findViewById(R.id.btnAsync);
        btnStart = (Button) findViewById(R.id.btnStart);
    }

    PublishSubject<String> publishSubject = PublishSubject.create();
    public void doPublish(View view) {
//        // 발행
//        publishSubject.onNext("A");
//        publishSubject.onNext("B");
//        publishSubject.onNext("C");
//
//        // 구독
//        // publishSubject는 구독 이후의 항목들에 대해서만 출력된다.
//
//        //        publishSubject.subscribe(new Observer<String>() {
//        //                                     @Override
//        //                                     public void onSubscribe(Disposable d) {
//        //
//        //                                     }
//        //
//        //                                     @Override
//        //                                     public void onNext(String value) {
//        //
//        //                                     }
//        //
//        //                                     @Override
//        //                                     public void onError(Throwable e) {
//        //
//        //                                     }
//        //
//        //                                     @Override
//        //                                     public void onComplete() {
//        //
//        //                                     }
//        //                                 }
//        //        );
//        //    ==>
//                publishSubject.subscribe(
//                item -> Log.i("publish", "item"+item)
//        );
//        publishSubject.onNext("D");
//        publishSubject.onNext("E");
//        publishSubject.onNext("F");
//        publishSubject.onNext("G");

        new Thread(){
            @Override
            public void run() {
                // 발행
                for (int i = 0; i < 100; i++) {
                    publishSubject.onNext("A"+i);
                    Log.i("Publish","A"+i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

    }


    public void getPublish(View view) {
        publishSubject.observeOn(AndroidSchedulers.mainThread());
        publishSubject.subscribe(
                item -> Log.i("publish", "item"+item)
        );
    }


    public void doStart(View view) {
    }

    public void doAsync(View view) {
    }

    public void doReplay(View view) {
    }

    public void doBehavior(View view) {
    }



}
