//package unithon8th.somethingnew.api;
//
//import lombok.RequiredArgsConstructor;
//import org.json.JSONException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import unithon8th.somethingnew.domain.bell.Bell;
//import unithon8th.somethingnew.domain.bell.BellRepository;
//import unithon8th.somethingnew.domain.user.User;
//import unithon8th.somethingnew.service.BellService;
//import unithon8th.somethingnew.service.UserService;
//import unithon8th.somethingnew.service.fcm.AndroidPushBellNotifications;
//import unithon8th.somethingnew.service.fcm.AndroidPushNotificationsService;
//import unithon8th.somethingnew.service.fcm.AndroidPushPeriodicNotifications;
//
//import javax.transaction.Transactional;
//import java.util.Optional;
//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.ExecutionException;
//
//@RequiredArgsConstructor
//@Transactional
//@RequestMapping("/bell")
//@RestController
//public class BellController {
//    private final UserService userService;
//    private final BellRepository bellRepository;
//    private final AndroidPushNotificationsService androidPushNotificationsService;
//    Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @PutMapping("/push")
//    public ResponseEntity push(Long fromUserId, Long toUserId) throws JSONException, InterruptedException {
//        Optional<User> mainUser = userService.findUserByUserId(fromUserId);
//        Optional<User> subUser = userService.findUserByUserId(toUserId);
//        if (mainUser.isPresent()) {
//            Optional<Bell> optionalMainBell = bellRepository.findByFromUserAndToUserId(mainUser.get(), toUserId);
//            Optional<Bell> optionalSubBell = bellRepository.findByFromUserAndToUserId(subUser.get(), fromUserId);
//
//            if (!optionalMainBell.isPresent()) {
//                bellSave(fromUserId, toUserId, mainUser, subUser);
//            }
//
//            if (optionalMainBell.get().isCanPush() == true) {
//                bellRepository.turnCanPush(mainUser.get(), toUserId, false);
//                bellRepository.turnCanPush(subUser.get(), fromUserId, true);
//                bellRepository.upperCount(subUser.get(), toUserId, optionalSubBell.get().getCount() + 1);
//
//                AndroidPushBellNotifications androidPushBellNotifications = new AndroidPushBellNotifications(userService);
//                String notifications = androidPushBellNotifications.PeriodicNotificationJson(fromUserId, toUserId);
//
//                HttpEntity<String> request = new HttpEntity<>(notifications);
//
//                CompletableFuture<String> pushNotification = androidPushNotificationsService.send(request);
//                CompletableFuture.allOf(pushNotification).join();
//
//
//                try {
//                    String firebaseResponse = pushNotification.get();
//                    return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
//                } catch (InterruptedException e) {
//                    logger.debug("got interrupted!");
//                    throw new InterruptedException();
//                } catch (ExecutionException e) {
//                    logger.debug("execution error!");
//                }
//                return new ResponseEntity(HttpStatus.OK);
//            } else {
//                return new ResponseEntity("상대방이 누르지 않아 벨을 누를 수 없습니다.", HttpStatus.BAD_REQUEST);
//            }
//        } else return new ResponseEntity(HttpStatus.BAD_REQUEST);
//    }
//
//    private void bellSave(Long fromUserId, Long toUserId, Optional<User> mainUser, Optional<User> subUser) {
//        Bell mainBell = new Bell(mainUser.get(), toUserId, true, 0);
//        Bell subBell = new Bell(subUser.get(), fromUserId, false, 0);
//        bellRepository.save(mainBell);
//        bellRepository.save(subBell);
//    }
//}
//
