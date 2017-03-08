package com.euky.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by euky on 2017/3/7.
 */

@RestController
public class TopicController {

    @Autowired
    private TopicService topicService;

    @RequestMapping(value = "/topics", method = RequestMethod.GET)
    public List<Topic> getTopics() {
        return topicService.getAllTopics();
    }

    @RequestMapping(value = "/topic/{id}", method = RequestMethod.GET)
    public Topic getTopic(@PathVariable String id) {
        return topicService.getTopic(id);
    }

    @RequestMapping(value = "/topics", method = RequestMethod.POST)
    public Topic addTopic(@RequestBody Topic topic) {
        topicService.addTopic(topic);
        return topic;
    }

    @RequestMapping(value = "/topic", method = RequestMethod.PUT)
    public Topic updateTopic(@RequestBody Topic topic) {
        topicService.updateTopic(topic);
        return topic;
    }

    @RequestMapping(value = "/topic/{id}", method = RequestMethod.DELETE)
    public void delTopic(@PathVariable String id) {
         topicService.delTopic(id);
    }

}
