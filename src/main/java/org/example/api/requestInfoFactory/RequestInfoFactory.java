package org.example.api.requestInfoFactory;

import com.google.gson.Gson;
import org.example.models.Macro;
import org.example.models.ReqInfo;

import java.util.List;

public class RequestInfoFactory{

    public static ReqInfo createReq(boolean operationResult){
        ReqInfo reqInfo = new ReqInfo();
        if(operationResult){
            reqInfo.setResponse("Macro Created.");
            reqInfo.setStatus(201);
        }else{
            reqInfo.setResponse("Macro not created. Collision detected.");
            reqInfo.setStatus(409);
        }
        return reqInfo;
    }

    public static ReqInfo retrieveReq(List<Macro> operationResult){
        ReqInfo reqInfo = new ReqInfo();
        reqInfo.setStatus(200);
        String json = new Gson().toJson(operationResult);
        reqInfo.setResponse(json);
        return reqInfo;
    }

    public static ReqInfo updateReq(boolean operationResult){
        ReqInfo reqInfo = new ReqInfo();
        if(operationResult){
            reqInfo.setResponse("Macro Updated.");
            reqInfo.setStatus(201);
        }else{
            reqInfo.setResponse("Macro not updated. Collision detected.");
            reqInfo.setStatus(409);
        }
        return reqInfo;
    }

    public static ReqInfo deleteReq(boolean operationResult){
        ReqInfo reqInfo = new ReqInfo();
        if(operationResult){
            reqInfo.setStatus(200);
            reqInfo.setResponse("Macro Deleted.");
        }else{
            reqInfo.setStatus(404);
            reqInfo.setResponse("Macro not deleted. Not found.");
        }
        return reqInfo;
    }
}
