1) [Wonderful Paralellism & Concurrency Lectures](https://www.youtube.com/c/DefogTech)
2) Using volatile keyword 
```java
public class Test1 {
    public volatile static boolean shared = true; // Using volatile the updates will be push to shared cache
    /*
    * So each thread has : Local Cache >> Shared cache
    * when a shared value is changes by one thread, the other thread may keep reading the stale val
    * But using volatile flushed the updates to the shared cache
    * */
    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(Test1::run);
        t1.start();
        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(2500);
                shared = false;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t2.start();
    }

    private static void run() {
        try {
            int i = 0;
            while(Test1.shared) {
                System.out.println("Running..." + ++i);
                //Thread.sleep(500);
            }
            System.out.println("Came out...");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

```
2) Observables
```java
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;

import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.operators.observable.ObservableCreate;

public class Test {
    // Basic Observable implementation
    public static void main(String[] args) {
        System.out.println("Hiii..");
        Observable<Integer> source = new ObservableCreate<>(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) {
                try {
                    emitter.onNext(10);
                    emitter.onNext(11);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        });
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("On subscribe...");
            }

            @Override
            public void onNext(@NonNull Integer nextInt) {
                System.out.println("Next Int =" + nextInt);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("Completed....!");
            }
        };
        source.subscribe(observer);
    }
}
/*
Hiii..
On subscribe...
Next Int =10
Next Int =11
Completed....!
Disconnected from the target VM, address: '127.0.0.1:51375', transport: 'socket'

Process finished with exit code 0
*/

```
