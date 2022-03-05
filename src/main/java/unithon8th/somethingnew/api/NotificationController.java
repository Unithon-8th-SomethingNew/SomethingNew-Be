package unithon8th.somethingnew.api;

import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unithon8th.somethingnew.domain.user.User;
import unithon8th.somethingnew.dto.nitification.NotificationRequestDto;
import unithon8th.somethingnew.service.fcm.AndroidPushNotificationsService;
import unithon8th.somethingnew.service.fcm.AndroidPushPeriodicNotifications;
import unithon8th.somethingnew.service.UserService;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
@RestController
public class NotificationController {
    private final UserService userService;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AndroidPushNotificationsService androidPushNotificationsService;

    @PostMapping(value = "/knock")
    public @ResponseBody ResponseEntity<String> send(@RequestBody NotificationRequestDto notificationRequestDto) throws JSONException, InterruptedException  {
        Optional<User> optionalUser = userService.findUserByUserId(notificationRequestDto.getToUserId());
        if(optionalUser.isPresent() && optionalUser.get().isCanCall() == true) {
            AndroidPushPeriodicNotifications androidPushPeriodicNotifications = new AndroidPushPeriodicNotifications(userService);
            String notifications = androidPushPeriodicNotifications.PeriodicNotificationJson(notificationRequestDto);

            HttpEntity<String> request = new HttpEntity<>(notifications);

            CompletableFuture<String> pushNotification = androidPushNotificationsService.send(request);
            CompletableFuture.allOf(pushNotification).join();


            try {
                String firebaseResponse = pushNotification.get();
                return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
            } catch (InterruptedException e) {
                logger.debug("got interrupted!");
                throw new InterruptedException();
            } catch (ExecutionException e) {
                logger.debug("execution error!");
            }

            return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>("toUser's isCanCall is FALSE!",HttpStatus.NOT_FOUND);
    }
}
