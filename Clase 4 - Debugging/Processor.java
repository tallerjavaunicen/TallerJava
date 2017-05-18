package unicen.tallerjava;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by acorbellini on 02/03/17.
 */
public class Processor<R>
        implements Runnable {
	
	public class ParObject{
		Object a;
		Object b;
	}
	
    public static class Pair<A, B> {
        A first;
        B second;

        public Pair(A first, B second) {
            this.first = first;
            this.second = second;
        }

        public A getFirst() {
            return first;
        }

        public B getSecond() {
            return second;
        }
    }

    public interface Task<R> {
        public R process();
    }

    public interface Callback<R> {
        public void onResult(R res);
    }

    AtomicBoolean stopped = new AtomicBoolean(false);

    @Override
    public void run() {
        while (!stopped.get() || !tasks.isEmpty()) {
            Pair<Task<R>, Callback<R>> task = null;
            try {
                task = tasks.take();
                if(task.getFirst()!=null) {
                    R res = task.getFirst().process();
                    task.getSecond().onResult(res);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        tasks.add(new Pair<Task<R>, Callback<R>>(null, null));
        stopped.set(true);
    }

    LinkedBlockingDeque<Pair<Task<R>, Callback<R>>> tasks = new LinkedBlockingDeque<>();

    public void addTask(Task<R> task, Callback<R> res) {
        tasks.add(new Pair<>(task, res));
    }

    public static void main(String[] args) {
        Processor<String> procesador = new Processor<>();

        Thread threadProcesador = new Thread(procesador);
        threadProcesador.start();

        List<String> mensajes = Arrays.asList("JAVA", "PROGRAMACIÓN", "AVANZADA");
        for (final String msg :
                mensajes) {
            procesador.addTask(() -> {
                return msg.toLowerCase();
            }, (res) -> {
                System.out.println(res);
            });
        }
        procesador.stop();
    }
}
