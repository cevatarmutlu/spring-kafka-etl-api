package com.hepsiburada.streamreader.model;

import com.hepsiburada.streamreader.model.submodel.EventContext;
import com.hepsiburada.streamreader.model.submodel.EventProperties;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    private String event;
    private String messageid;
    private String userid;
    private EventProperties properties;
    private EventContext context;
    private Long timestamp;
}


