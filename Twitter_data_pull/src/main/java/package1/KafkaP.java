/**
 * 
 */
package package1;

//package com.kafkademo;

import java.util.Properties;
 
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
 
public class KafkaP {
 
	Properties props;
	Producer<String, String> producer ;
    //producer = new KafkaProducer(props);
	int i;
	
	KafkaP(){
			props = new Properties();
		    props.put("bootstrap.servers", "192.168.1.10:9092");
		    props.put("acks", "all");
		    props.put("retries", 0);
		    props.put("batch.size", 16384);
		    props.put("linger.ms", 1);
		    props.put("buffer.memory", 33554432);
		    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		    producer = null;
		    producer = new KafkaProducer(props);
		    i = 1;
	}
  
	public void Topic(String msg){
		 try {
			 	producer.send(new ProducerRecord<String, String>("KafkaTopic1", msg));
		        System.out.println("Sent : Tweet " + i++);
		      
		    } catch (Exception e) {
		      e.printStackTrace();
		 
		    } /*finally {
		      producer.close();
		    }*/
		 //producer.close();
	}
 
}
 