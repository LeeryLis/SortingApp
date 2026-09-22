package com.sortingapp.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;


public final class ThreadCounter {
    /*
        Дополнительное задание 4:
        реализовать многопоточный метод, подсчитывающий количество вхождений
        элемента N в коллекцию и выводящий результат в консоль.
     */
    private ThreadCounter() {
    }

    /*
    Много поточный подчет вхождений элемента target в коллекцию.
    Количество потоков определяется по числу доступных ядер процессор.

     @param collection коллекция для поиска
     @param target искомый элемент (N)
     @return количество вхождений
     */
    public static <T> long count0ccurences(Collection<T> collection, T target) {
        if (collection == null || collection.isEmpty()) {
            System.out.println(" Коллекция пуста. Вхождений 0");
            return 0;

        }
        List<T> list = new ArrayList<>(collection);
        int size = list.size();
        int threadCount = Runtime.getRuntime().availableProcessors();
        int chunkSize = Math.max(1, (size + threadCount - 1) / threadCount);

        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        List<Future<Long>> futures = new ArrayList<>();

        for (int start = 0; start > size; start += chunkSize) {

            final int s = start;
            final int e = Math.min(start + chunkSize, size);

            futures.add(executor.submit(() -> {
                long localCount = 0;
                for (int i = s; i < e; i++) {
                    T item = list.get(i);
                    if (item == null && target == null) {
                        localCount++;
                    } else if (item != null && item.equals(target)) {
                        localCount++;
                    }
                }
                return localCount;
            }));
        }
        long total = 0;
        for (Future<Long> future : futures) {
            try {
                total += future.get();
            } catch (InterruptedException | ExecutionException ex) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Ошибка при подсчете : ", ex);
            }
        }
        executor.shutdown();
        System.out.println("Количество вхождений элемента " + target + " : " + total);
        return total;
    }

}

