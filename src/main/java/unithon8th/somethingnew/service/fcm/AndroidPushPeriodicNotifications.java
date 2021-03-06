package unithon8th.somethingnew.service.fcm;

import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import unithon8th.somethingnew.domain.user.User;
import unithon8th.somethingnew.dto.nitification.NotificationRequestDto;
import unithon8th.somethingnew.service.UserService;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Optional;

@RequiredArgsConstructor
public class AndroidPushPeriodicNotifications {
    public final UserService userService;

    public String PeriodicNotificationJson(NotificationRequestDto notificationRequestDto) throws JSONException {
        Optional<User> optionalToUser = userService.findUserByUserId(notificationRequestDto.getToUserId());
        Optional<User> optionalFromUser = userService.findUserByUserId(notificationRequestDto.getFromUserId());

        JSONObject body = new JSONObject();

        JSONArray array = new JSONArray();
        array.put(optionalToUser.get().getFcmToken());

        body.put("registration_ids", array);

        JSONObject notification = new JSONObject();

        try {
            String pushMessage = optionalFromUser.get().getUsername() + "-" + optionalFromUser.get().getUserId() + "-" + optionalFromUser.get().getImgUrl();
            String titleMessage = "누군가 노크했어요!";

            pushMessage = URLEncoder.encode(pushMessage  ,"UTF-8");
            titleMessage = URLEncoder.encode(titleMessage, "UTF-8");
            notification.put("title", titleMessage);
            notification.put("body", pushMessage);
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        body.put("notification", notification);

        System.out.println(body.toString());

        return body.toString();

    }
}
