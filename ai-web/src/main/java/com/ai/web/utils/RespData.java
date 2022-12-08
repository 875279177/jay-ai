package com.ai.web.utils;

import lombok.Data;

@Data
public class RespData {
    private String code;
    private String msg;
    private Object data;

    public static RespData buildSuccess(Object data){
        RespData respData = new RespData();
        respData.setCode("000000");
        respData.setMsg("SUCCESS");
        respData.setData(data);
        return respData;
    }

    public static RespData buildError(String msg){
        RespData respData = new RespData();
        respData.setCode("999999");
        respData.setMsg(msg);
        return respData;
    }
}
