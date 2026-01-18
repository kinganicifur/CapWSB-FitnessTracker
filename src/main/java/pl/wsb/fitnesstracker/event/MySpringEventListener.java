package pl.wsb.fitnesstracker.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.mail.api.EmailDto;
import pl.wsb.fitnesstracker.mail.api.JavaMailEmailSender;

@Component
@Slf4j
@RequiredArgsConstructor
public class MySpringEventListener implements ApplicationListener<MySpringEvent> {
    private final JavaMailEmailSender javaEmailSender;

    @Override
    public void onApplicationEvent(final MySpringEvent event) {

        log.info("Received spring custom event in Listener - " + event.getMyMessage());
        log.info("Sending email with Received E-mail Title " + event.getMyMessage());

        EmailDto emailDto = new EmailDto("to.address@mail.com",
                "fitnesstracker@cap.wsb.com",
                event.getMyMessage(),
                "E-Mail Content");

        javaEmailSender.send(emailDto);
    }
}
