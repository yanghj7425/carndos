package com.carndos.modules.commons.util.video;

import com.alibaba.fastjson.JSONArray;

public interface VideoConsumer {
    Object consumer(JSONArray jsonArray);
}
