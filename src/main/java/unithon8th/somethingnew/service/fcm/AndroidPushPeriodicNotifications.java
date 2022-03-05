package unithon8th.somethingnew.service.fcm;

import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import unithon8th.somethingnew.domain.user.User;
import unithon8th.somethingnew.dto.nitification.NotificationRequestDto;
import unithon8th.somethingnew.service.UserService;

import java.util.Optional;

@RequiredArgsConstructor
public class AndroidPushPeriodicNotifications {
    public final UserService userService;

    public String PeriodicNotificationJson(NotificationRequestDto notificationRequestDto) throws JSONException {
        Optional<User> optionalToUser = userService.findUserByUserId(notificationRequestDto.getToUserId());
        Optional<User> optionalFromUser = userService.findUserByUserId(notificationRequestDto.getFromUserId());

        if (optionalFromUser.isPresent() && optionalFromUser.isPresent()) {
            JSONObject body = new JSONObject();

            JSONArray array = new JSONArray();
            array.put(optionalToUser.get().getFcmToken());

            body.put("registration_ids", array);

            JSONObject notification = new JSONObject();
            notification.put("title", optionalFromUser.get().getUsername() + "-" + optionalFromUser.get().getUserId() + "-" + optionalFromUser.get().getImgUrl());
            notification.put("body", "문을 열어 손님을 맞아주세요");

            body.put("notification", notification);

            System.out.println(body.toString());

            return body.toString();
        } else {
            return null;
        }
    }
}
