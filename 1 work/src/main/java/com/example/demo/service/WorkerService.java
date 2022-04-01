package com.example.demo.service;

import com.example.demo.model.Worker;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class WorkerService {

    private List<Worker> workerList = new ArrayList<>(
            Arrays.asList(
                    new Worker(1,"Ivan","123456"),
                    new Worker(2,"Anna","234567"),
                    new Worker(3,"Mihael","345678")
            )
    );

    public List<Worker> getWorkers() {
        return workerList;
    }

    public Worker getWorkerById(int id) {
        return workerList.stream().filter(worker -> worker.getId() == id).findFirst().get();
    }

    public Worker createWorker(Worker newWorker) {
        Worker lastWorker = workerList.isEmpty() ? null : workerList.get(workerList.size()-1);
        newWorker.setId(lastWorker.getId()+1);
        workerList.add(newWorker);
        return newWorker;
    }
    public Worker updateWorkerById(int id, Worker worker) {
        workerList.set(id-1,worker);
        return workerList.get(id);
    }

    public void deleteWorkerById(int id) {
        workerList.remove(id-1);
    }
}
