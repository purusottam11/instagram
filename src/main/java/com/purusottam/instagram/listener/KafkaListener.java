package com.purusottam.instagram.listener;

import com.purusottam.instagram.beans.PostBean;
import com.purusottam.instagram.model.Post;
import com.purusottam.instagram.repository.PostRepository;
import com.purusottam.instagram.utils.CopyUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class KafkaListener implements AcknowledgingMessageListener<String, PostBean> {

    @Autowired
    private PostRepository postRepository;

    private final Logger logger = LoggerFactory.getLogger(KafkaListener.class);

//    @org.springframework.kafka.annotation.KafkaListener(topics = "post_topic", groupId = "PURUSOTTAM", containerFactory = "postBeamKafkaListenerContainerFactory")
//    public void upload(PostBean postBean, Acknowledgment acknowledgment) {
//        Post post = new Post();
//        CopyUtils.copySafe(postBean, post);
//        postRepository.save(post);
//        acknowledgment.acknowledge();
//    }

    @Override
    @org.springframework.kafka.annotation.KafkaListener(topics = "post_topic", groupId = "PURUSOTTAM", containerFactory = "postBeamKafkaListenerContainerFactory")
    public void onMessage(ConsumerRecord<String, PostBean> consumerRecord, Acknowledgment acknowledgment) {
        System.out.println("consumerRecord : "+consumerRecord);
        Post post = new Post();
        CopyUtils.copySafe(consumerRecord.value(), post);
        postRepository.save(post);
        acknowledgment.acknowledge();
    }
}
