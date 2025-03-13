package com.github.qy;


import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.KV;
import io.etcd.jetcd.kv.GetResponse;
import java.util.concurrent.CompletableFuture;
public class App {
    public static void main(String[] args) {
        try {
            Client client = Client.builder().endpoints("http://localhost:2379").build();
            KV kvClient = client.getKVClient();
            ByteSequence key = ByteSequence.from("test_key".getBytes());
            ByteSequence value = ByteSequence.from("test_value".getBytes());
            kvClient.put(key, value).get();
            CompletableFuture<GetResponse> getFuture = kvClient.get(key);
            GetResponse response = getFuture.get();
            System.out.println("resp:"+response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
