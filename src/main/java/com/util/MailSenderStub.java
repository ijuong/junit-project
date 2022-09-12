package com.util;

import org.springframework.stereotype.Component;

// 가짜
@Component //IoC 컨테이너에 등록
public class MailSenderStub implements MailSender {

    @Override
    public boolean send() {
        // TODO Auto-generated method stub
        return true;
    }
    
}
