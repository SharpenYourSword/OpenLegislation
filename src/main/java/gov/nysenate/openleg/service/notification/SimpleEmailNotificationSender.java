package gov.nysenate.openleg.service.notification;

import gov.nysenate.openleg.model.notification.NotificationSubscription;
import gov.nysenate.openleg.model.notification.NotificationTarget;
import gov.nysenate.openleg.model.notification.RegisteredNotification;
import gov.nysenate.openleg.service.mail.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SimpleEmailNotificationSender extends EmailNotificationSender {

    /**
     * {@inheritDoc}
     */
    @Override
    public NotificationTarget getTargetType() {
        return NotificationTarget.EMAIL_SIMPLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendNotification(RegisteredNotification registeredNotification, Collection<NotificationSubscription> subscriptions) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(registeredNotification.getSummary());
        message.setText("{notification view page url goes here}");

        sendNotificationEmail(message, subscriptions);
    }
}
