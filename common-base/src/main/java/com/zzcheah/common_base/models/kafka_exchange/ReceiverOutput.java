package com.zzcheah.common_base.models.kafka_exchange;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReceiverOutput {

    String key;
    String client;
    String ediType;

}
