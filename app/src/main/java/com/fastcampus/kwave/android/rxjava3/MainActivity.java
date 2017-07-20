package com.fastcampus.kwave.android.rxjava3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;

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
/*
        // 발행
        publishSubject.onNext("A");
        publishSubject.onNext("B");
        publishSubject.onNext("C");

        // 구독
        // publishSubject는 구독 이후의 항목들에 대해서만 출력된다.

        //        publishSubject.subscribe(new Observer<String>() {
        //                                     @Override
        //                                     public void onSubscribe(Disposable d) {
        //
        //                                     }
        //
        //                                     @Override
        //                                     public void onNext(String value) {
        //
        //                                     }
        //
        //                                     @Override
        //                                     public void onError(Throwable e) {
        //
        //                                     }
        //
        //                                     @Override
        //                                     public void onComplete() {
        //
        //                                     }
        //                                 }
        //        );
        //    ==>
                publishSubject.subscribe(
                item -> Log.i("publish", "item"+item)
        );
        publishSubject.onNext("D");
        publishSubject.onNext("E");
        publishSubject.onNext("F");
        publishSubject.onNext("G");
*/
        new Thread(){
            @Override
            public void run() {
                // 발행
                for (int i = 0; i < 10; i++) {
                    behaviorSubject.onNext("A"+i);           // 옵저버블이 발행
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
        publishSubject.observeOn(AndroidSchedulers.mainThread());   // 옵저버가 메인쓰레드를 구독하기 시작
        publishSubject.subscribe(                                   // 옵저버가 내용을 출력
                item -> Log.i("publish", "item"+item)
        );
    }


    BehaviorSubject<String> behaviorSubject = BehaviorSubject.create();
    public void doBehavior(View view) {
        new Thread(){
            @Override
            public void run() {
                // 발행
                for (int i = 0; i < 10; i++) {
                    publishSubject.onNext("B"+i);           // 옵저버블이 발행
                    Log.i("behavior","B"+i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public void getBehavior(View view) {
        behaviorSubject.subscribe(                                   // 옵저버가 내용을 출력
                item -> Log.i("behavior", "item"+item)
        );
    }

    ReplaySubject<String> replaySubject = ReplaySubject.create();
    public void doReplay(View view) {
        new Thread(){
            @Override
            public void run() {
                // 발행
                for (int i = 0; i < 10; i++) {
                    replaySubject.onNext("C"+i);           // 옵저버블이 발행
                    Log.i("replay","C"+i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public void getReplay(View view) {
        replaySubject.subscribe(                                   // 옵저버가 내용을 출력
                item -> Log.i("replay", "item"+item)
        );
    }


    AsyncSubject<String> asyncSubject = AsyncSubject.create();
    public void doAsync(View view) {
        new Thread(){
            @Override
            public void run() {
                // 발행
                for (int i = 0; i < 10; i++) {
                    asyncSubject.onNext("C"+i);           // 옵저버블이 발행
                    Log.i("Async","C"+i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public void getAsync(View view) {
        asyncSubject.subscribe(                                   // 옵저버가 내용을 출력
                item -> Log.i("Async", "item"+item)
        );
    }


    public void doStart(View view) {

    }


}
