package com.ai.web.controller;

import com.ai.web.service.impl.OpenAiService;
import com.ai.web.utils.RespData;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @RequestMapping("/dialogue")
    public RespData dialogue(HttpServletRequest request){
        String contentStr = request.getParameter("content");
        if(StringUtils.isEmpty(contentStr)){
            return RespData.buildError("参数错误!");
        }

        OpenAiService service = new OpenAiService("");
        CompletionRequest completionRequest = CompletionRequest.builder()
                .model("text-davinci-003")
                .prompt(contentStr)
                .temperature(0.9)
                .maxTokens(1000)
                .topP(1D)
                .frequencyPenalty(0D)
                .presencePenalty(0.6)
                .echo(true)
                .build();
        StringBuffer stringBuffer = new StringBuffer();

        List<CompletionChoice> choices =  service.createCompletion(completionRequest).getChoices();
        for(CompletionChoice choice: choices){
            stringBuffer.append(choice.getText());
        }
        return RespData.buildSuccess(stringBuffer.toString());
    }

}
