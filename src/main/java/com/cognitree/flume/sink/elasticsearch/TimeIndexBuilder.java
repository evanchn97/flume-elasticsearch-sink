/*
 * Copyright 2017 Cognitree Technologies
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.cognitree.flume.sink.elasticsearch;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;

import static com.cognitree.flume.sink.elasticsearch.Constants.*;

/**
 * Created by Evan Li
 */
public class TimeIndexBuilder implements IndexBuilder {

    private static final Logger logger = LoggerFactory.getLogger(TimeIndexBuilder.class);

    private String index;

    private String type;

    @Override
    public String getIndex(Event event) {
        String index;
        String timeStamp = new SimpleDateFormat("-yyyy-MM-dd").format(new java.util.Date());
        if (this.index != null) {
            index = this.index + timeStamp;
        } else {
            index = DEFAULT_ES_INDEX;
        }
        return index;
    }

    @Override
    public String getType(Event event) {
        String type;
        if (this.type != null) {
            type = this.type;
        } else {
            type = DEFAULT_ES_TYPE;
        }
        return type;
    }

    @Override
    public String getId(Event event) {
        return null;
    }

    @Override
    public void configure(Context context) {
        this.index = Util.getContextValue(context, ES_INDEX);
        this.type = Util.getContextValue(context, ES_TYPE);
        logger.info("Simple Index builder, name [{}] type [{}] ",
                new Object[]{this.index, this.type});

    }
}
